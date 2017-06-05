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
	public void testInsert() throws Exception {
		userRepository.save(new User("a", "111111", Gender.MALE));
		userRepository.save(new User("b", "222222", Gender.FEMALE));
		userRepository.save(new User("c", "333333", Gender.FEMALE));

		Assert.assertEquals(3, userRepository.findAll().size());
	}

	@Test
	public void testQuery() throws Exception {
		List<User> users = userRepository.findAll();
		System.out.println(users.toString());
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		User user = userRepository.getOne(3l);
		System.out.println(user.toString());
		user.setName("demo");
		userRepository.save(user);
		Assert.assertTrue(("demo".equals(userRepository.getOne(3l).getName())));
	}
}
