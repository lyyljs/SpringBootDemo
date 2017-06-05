package com.lyyljs.demos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyyljs.demos.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByNameAndPasswd(String name, String passwd);
}
