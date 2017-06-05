package com.lyyljs.demos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyyljs.demos.domain.User;
import com.lyyljs.demos.repository.UserRepository;
import com.lyyljs.demos.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User haveUser(String name, String passwd) {
		return userRepository.findByNameAndPasswd(name, passwd);
	}

}
