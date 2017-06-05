package com.lyyljs.demos.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lyyljs.demos.domain.User;
import com.lyyljs.demos.domain.enums.Gender;
import com.lyyljs.demos.service.RedisService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	@Autowired
    private RedisService redisService;
	
	@Test
	public void test(){
		redisService.set("aaaa", "test");
		Assert.assertEquals("test",redisService.get("aaaa"));
	}
	
	@Test
	public void testObject(){
		redisService.setObject("user", new User("a", "111111", Gender.MALE));
		System.out.println(redisService.getObject("user").toString());
	}
}
