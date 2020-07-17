package com.hello;

import org.apache.geode.cache.GemFireCache;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.cache.client.Pool;
import org.apache.geode.pdx.PdxSerializer;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.client.ClientCacheFactoryBean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.client.PoolFactoryBean;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;
import org.springframework.data.gemfire.config.annotation.EnableClusterConfiguration;
import org.springframework.data.gemfire.config.xml.GemfireConstants;
import org.springframework.data.gemfire.support.ConnectionEndpoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {

    @Bean
    ClientCacheFactoryBean gemfireCacheClient() {
        return new ClientCacheFactoryBean();
    }

    @Bean(name = GemfireConstants.DEFAULT_GEMFIRE_POOL_NAME)
    PoolFactoryBean gemfirePool() {

        PoolFactoryBean gemfirePool = new PoolFactoryBean();

        gemfirePool.addLocators(Collections.singletonList(new ConnectionEndpoint("192.168.1.3", 10334)));
        gemfirePool.setName(GemfireConstants.DEFAULT_GEMFIRE_POOL_NAME);
        gemfirePool.setKeepAlive(false);
        gemfirePool.setPingInterval(TimeUnit.SECONDS.toMillis(5));
        gemfirePool.setRetryAttempts(1);
        gemfirePool.setSubscriptionEnabled(true);
        gemfirePool.setThreadLocalConnections(false);

        return gemfirePool;
    }

 /*   @Bean
    ClientRegionFactoryBean<String, String> getRegion(ClientCache gemfireCache, Pool gemfirePool) {
        ClientRegionFactoryBean<String, String> region = new ClientRegionFactoryBean<>();
        region.setName("Hello");
        region.setLookupEnabled(true);
        region.setCache(gemfireCache);
        region.setPool(gemfirePool);
        region.setShortcut(ClientRegionShortcut.PROXY);

        return region;
    }*/

    @Bean("helloRegion")
    Region helloRegion() {
        List<String> list = new ArrayList<>();
        list.add("com.hello.Customer");
        PdxSerializer pdxSerializer = new ReflectionBasedAutoSerializer(list);


        ClientCacheFactory ccf = new ClientCacheFactory();
        ccf.addPoolLocator("192.168.1.2", 10334);
        ccf.setPdxReadSerialized(true);
        ccf.setPdxSerializer(pdxSerializer);




        ClientCache client = ccf.create();
        return client.createClientRegionFactory(ClientRegionShortcut.PROXY).create("Hello");
    }

}
