package com.zxl.test.myproject.locker;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Alex on 17/6/29 下午3:40.
 */
public class DistributedLock implements Watcher {

    private ZooKeeper zk;
    private String selfPath;
    private String waitPath;
    private static final int SESSION_TIMEOUT = 10000;
    private static final String GROUP_PATH = "/disLocks";
    private static final String SUB_PATH = "/disLocks/sub";
    private static final String CONNECTION_STRING = "127.0.0.1:2181";

    private static final Logger logger = LoggerFactory.getLogger(DistributedLock.class);

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static final CountDownLatch threadSemaphore = new CountDownLatch(10);


    @Override public void process(WatchedEvent event) {
        if (event == null)
            return;
        Event.KeeperState keeperState = event.getState();
        Event.EventType eventType = event.getType();
        if (Event.KeeperState.SyncConnected == keeperState) {
            connectedSemaphore.countDown();
        } else if (eventType == Event.EventType.NodeDeleted && event.getPath().equals(waitPath)) {
            logger.info("当前结点前面的结点已被释放锁.");
            try {
                if (checkMinPath())
                    getLockSuccess();
            } catch (KeeperException | InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }

    }

    /**
     * 获取锁
     */
    private void getLock() throws KeeperException, InterruptedException {
        selfPath = zk.create(SUB_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        if (checkMinPath()) {
            getLockSuccess();
        }
    }

    /**
     * 检查当前结点是否是最小
     *
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    private boolean checkMinPath() throws KeeperException, InterruptedException {
        List<String> subNodes = zk.getChildren(GROUP_PATH, false);
        Collections.sort(subNodes);
        int index = subNodes.indexOf(selfPath.substring(GROUP_PATH.length() + 1));
        if (index == 0) {
            return true;
        }

        if (index == -1) {
            return false;
        }

        this.waitPath = GROUP_PATH + "/" + subNodes.get(index - 1);
        try {
            zk.getData(waitPath, true, new Stat());
            return false;
        } catch (KeeperException e) {
            if (zk.exists(waitPath, false) == null)
                return checkMinPath();
            throw e;
        }
    }

    public void getLockSuccess() throws KeeperException, InterruptedException {
        if (zk.exists(this.selfPath, false) == null)
            return;

        Thread.sleep(2000);
        zk.delete(this.selfPath, -1);
        releaseConnection();
        threadSemaphore.countDown();
    }

    public void releaseConnection() {
        if (this.zk != null) {
            try {
                this.zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void createConnection(String connectString, int sessionTimeout) throws IOException, InterruptedException {
        zk = new ZooKeeper(connectString, sessionTimeout, this);
        connectedSemaphore.await();
    }

    public boolean createPath(String path, String data, boolean needWatch) throws KeeperException, InterruptedException {
        if (zk.exists(path, needWatch) == null) {
            this.zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        }
        return true;
    }

    private int threadId;
    public DistributedLock(int threadId) {
        this.threadId = threadId;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int threadId = i + 1;
            new Thread(()-> {
                try {
                    DistributedLock dc = new DistributedLock(threadId);
                    dc.createConnection(CONNECTION_STRING, SESSION_TIMEOUT);

                    synchronized (threadSemaphore) {
                        dc.createPath(GROUP_PATH, "该节点由线程" + threadId + "创建", true);
                    }
                    dc.getLock();
                } catch (IOException  | InterruptedException | KeeperException e) {
                    logger.error("【第"+threadId+"个线程】 抛出的异常：");
                }
            }).start();
        }
        try {
            threadSemaphore.wait();
            logger.info("所有线程运行结束!");
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
