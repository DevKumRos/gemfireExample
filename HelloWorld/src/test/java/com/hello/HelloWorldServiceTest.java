package com.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class HelloWorldServiceTest {

    @InjectMocks
    HelloWorldService helloWorldService;

    @Test
    public void getHelloValue(){
        Customer time = helloWorldService.getHelloValue("TWO");
        assertNotNull(time);
    }
}
