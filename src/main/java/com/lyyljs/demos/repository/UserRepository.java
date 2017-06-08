package com.lyyljs.demos.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lyyljs.demos.domain.User;

@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {
	@Cacheable
	public User findByNameAndPasswd(String name, String passwd);
	
	@Cacheable
	public User findById(long id);
	
}
