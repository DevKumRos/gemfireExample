package com.hello;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.Region;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.mockito.Mockito;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes={ HelloWorldController.class, HelloWorldApplication.class })
@AutoConfigureMockMvc
public class HelloWorldControllerTest {

    @Autowired
    private Cache gemfireCache;

    @Resource
    private Region<Object, Object> helloRegion;

    @Autowired
    MockMvc mvc;

    @SpyBean
    HelloWorldService helloWorldService;

    @Before
    public void setup(){
        helloRegion.put("NINE", "NINE");
    }

    @After
    public void tearDown(){
        helloRegion.clear();

    }

    @Test
    public void nonCacheKey() throws Exception {
        //System.out.println("Six key present ="+helloRegion.get("Six"));
        mvc.perform(MockMvcRequestBuilders.get("/hello/Six").accept("text/html"))
                .andExpect(status().isOk());
        Mockito.verify(helloWorldService, Mockito.times(1)).getHelloValue("Six");
    }

    @Test
    public void existingCacheKey() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello/NINE").accept("text/html"))
                .andExpect(status().isOk());
        Mockito.verify(helloWorldService, Mockito.never()).getHelloValue("NINE");

    }
}
