package com.hello;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;

import org.apache.geode.cache.Cache;
import org.apache.geode.cache.DataPolicy;
import org.apache.geode.cache.Region;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { HelloWorldApplication.class })
public class HelloWorldApplicationTests {

	@Autowired
	private Cache gemfireCache;

	@Resource
	private Region<Object, Object> helloRegion;

	@Before
	public void setup(){
		helloRegion.put("ONE", "ONE");
	}

	@After
	public void tearDown(){
		helloRegion.remove("ONE", "ONE");
	}

	@Test
	public void testGemFireCacheConfiguration() {
		assertNotNull("The GemFire Cache was not properly configured and initialized!", gemfireCache);
		assertEquals("Hello", helloRegion.getName());
		assertNotNull(helloRegion.getAttributes());

	}

	@Test
	public void isKeyExistRegion() {
		assertEquals(helloRegion.get("ONE"),"ONE");

	}

	@Test
	public void isKeyNotExistRegion() {
		assertNotEquals(helloRegion.get("TWO"),"TWO");
	}

}
