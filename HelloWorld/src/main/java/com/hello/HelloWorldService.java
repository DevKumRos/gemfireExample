package com.hello;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.query.SelectResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class HelloWorldService {

    public static Integer customerNumber = 111;
   /* @Autowired
    Region helloRegion;

    public void getRegionDetails(){
        Set<String> keys = helloRegion.keySet();
        keys.forEach(key -> System.out.println("Key : "+key+", Value : "+helloRegion.get(key)));

    }*/

    @Autowired
    Region helloRegion;

   /* @Autowired
    ClientCache helloClientCache;*/
    public Map<String, String> getRegionDetails()  {
        System.out.println("Entered getRegionDetails");
        Map<String, String> map = new HashMap<>();
        SelectResults results;
        try {
            /*results = helloRegion.query("SELECT * FROM /Hello");
            if (!results.isEmpty()){
                for (Iterator iter = results.iterator(); iter.hasNext();) {
                    System.out.println("Value: "+iter.next());
                }
            }*/
            Set<String> keysServer = helloRegion.keySetOnServer();
            keysServer.forEach(key -> {
                System.out.println("Key :" + key + ", Value : " + helloRegion.get(key));
                map.put(key, (String) helloRegion.get(key));
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Cacheable("Hello")
    // This method will not be invoked if the key is in the cache
    public Customer getHelloValue(String key) {
        System.out.println("HelloWorldService - getHelloValue - key-"+key);
        simulateSlowDataStore();

        String value = getTimeOfInitialLookup();
        Customer customer = new Customer();
        customer.setKey(key);
        customer.setCustomerNumber(customerNumber++);
        customer.setFirstName("FirstName - "+key);
        customer.setLastName("LastName - "+key);
        return customer;
    }

    private void simulateSlowDataStore() {
        try {
            long artificialDelay = 3000L;
            Thread.sleep(artificialDelay);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    private String getTimeOfInitialLookup() {
        Instant instant =
                Instant.ofEpochMilli(System.currentTimeMillis());

        LocalDateTime localDateTime =
                LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        return localDateTime.toString();
    }
}
