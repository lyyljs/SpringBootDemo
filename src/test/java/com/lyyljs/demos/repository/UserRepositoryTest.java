package com.lyyljs.demos.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lyyljs.demos.domain.User;
import com.lyyljs.demos.domain.enums.Gender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testCache() throws Exception{
		userRepository.findById(1l);
		userRepository.findById(1l);
		userRepository.findById(1l);
		userRepository.findByNameAndPasswd("a", "111111");
		userRepository.findByNameAndPasswd("a", "111111");
	}
	
	@Test
	public void testInsert() throws Exception {
//		User newUser = new User("a", "111111", Gender.MALE);
//		userRepository.save(newUser);
//		Assert.assertNotNull(userRepository.findByNameAndPasswd(newUser.getName(), newUser.getPasswd()));
	}

	@Test
	public void testQuery() throws Exception {
//		List<User> users = userRepository.findAll();
//		System.out.println(users.toString());
	}
	
	@Test
	public void testUpdate() throws Exception {
//		User user = userRepository.getOne(3l);
//		System.out.println(user.toString());
//		user.setName("demo");
//		userRepository.save(user);
//		Assert.assertTrue(("demo".equals(userRepository.getOne(3l).getName())));
	}
}
