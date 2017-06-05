package com.lyyljs.demos.service;

import java.util.List;

import com.lyyljs.demos.domain.User;

public interface UserService {
	public List<User> getAllUsers();
	
	public User haveUser(String name, String passwd);
}
