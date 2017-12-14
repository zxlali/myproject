package com.zxl.test.myproject.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Properties加载类
 * <p/>
 */
public final class PropertiesLoader {

    private static final ConcurrentHashMap<String, FutureTask<Configuration>> cached = new ConcurrentHashMap<>(5);

    private PropertiesLoader() {}

    /**
     * load properties file
     *
     * @param filePath the path of properties file to load
     * @return {@link Configuration}
     * @throws ExecutionException,InterruptedException
     */
    private static Configuration loadProperties(final String filePath) throws ExecutionException, InterruptedException {

        FutureTask<Configuration> configuration = cached.get(filePath);
        if (configuration == null) {
            FutureTask<Configuration> future = new FutureTask<>(()->
                    findProperties(Thread.currentThread().getContextClassLoader(), filePath)
            );
            configuration = cached.putIfAbsent(filePath, future);//can't be null
            if (null == configuration) {
                configuration = cached.get(filePath);
                future.run();
            }
        }

        return configuration.get();
    }

    private static Configuration findProperties(ClassLoader classLoader, final String filePath)
            throws ConfigurationException, FileNotFoundException {
        URL url = classLoader.getResource(filePath);
        if (url == null)
            throw new FileNotFoundException("The resource '" + filePath + "' is not found");

        PropertiesConfiguration configuration = new PropertiesConfiguration(url);
        configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
        configuration.setThrowExceptionOnMissing(false);

        return configuration;
    }

    /**
     * load properties file
     *
     * @param filePath the path of properties file to load
     * @return {@link Configuration}
     */
    public static Configuration load(final String filePath) {
        try {
            return loadProperties(filePath);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
