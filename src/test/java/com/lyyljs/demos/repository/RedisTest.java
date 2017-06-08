package com.lyyljs.demos.repository;

import java.util.HashMap;
import java.util.Map;

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
	
	@Test
	public void testMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("auth", "aaaaa");
		map.put("user", new User("a", "111111", Gender.MALE));
		redisService.setObject("testmap", map);
		Map<String, Object> resultMap = (Map<String, Object>) redisService.getObject("testmap");
		System.out.println(resultMap);
		resultMap.put("auth", "bbbb");
		redisService.setObject("testmap", resultMap);
	}
	
	@Test
	public void testNone(){
		System.out.println("no key result:" + redisService.get("aavbv"));;
	}
}
