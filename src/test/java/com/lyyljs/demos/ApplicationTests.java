package com.lyyljs.demos;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lyyljs.demos.common.Const;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void constTest(){
		Assert.assertEquals(Const.BASE_PATH, "http://localhost:8080/");
	}

}
