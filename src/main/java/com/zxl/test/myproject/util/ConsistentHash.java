package com.zxl.test.myproject.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性哈希算法简单实现
 * @author zhangxianli
 * @date 2016年4月21日下午6:09:11
 */
public class ConsistentHash {
  private static final Logger LOGGER = LoggerFactory.getLogger(ConsistentHash.class);

  private static String[] servers = {"192.168.0.1", "192.168.0.2", "192.168.0.3"};

  private static List<String> realNodes = new LinkedList<>();

  private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

  private static final int VIRTUALNODES = 5;

  static {
    for (int i = 0; i < servers.length; i++) {
      realNodes.add(servers[i]);
    }

    for (String node : realNodes) {
      for (int i = 0; i < VIRTUALNODES; i++) {
        String virtualName = String.format("%s&VN%s", node, i);
        int hash = getHash(virtualName);
        LOGGER.info(String.format("虚拟节点[%s]被添加, hash值为%s", virtualName, hash));
        virtualNodes.put(hash, virtualName);
      }
    }
  }

  private static Integer getHash(String key) {
    // 默认使用FNV1hash算法
    final int p = 16777619;
    int hash = (int) 2166136261L;
    for (int i = 0; i < key.length(); i++)
      hash = (hash ^ key.charAt(i)) * p;
    hash += hash << 13;
    hash ^= hash >> 7;
    hash += hash << 3;
    hash ^= hash >> 17;
    hash += hash << 5;
    if (hash < 0)
      hash = Math.abs(hash);
    return hash;
  }

  private static String getServer(String node) {
    int hash = getHash(node);
    SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
    Integer i = subMap.firstKey();
    String virtualNode = subMap.get(i);
    return virtualNode.substring(0, virtualNode.indexOf("&"));
  }

//  public static void main(String[] args) {
//    String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
//    for (int i = 0; i < nodes.length; i++) {
//      LOGGER.info(String.format("[%s]的hash值为%s, 被路由到结点[%s]", nodes[i], getHash(nodes[i]), getServer(nodes[i])));
//    }
//  }
}
