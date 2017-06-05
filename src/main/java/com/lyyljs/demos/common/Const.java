package com.lyyljs.demos.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Const {
	
	public static String BASE_PATH;
	
	public static String LOGIN_SESSION_KEY = "Login_User";
	
	public static String PASSWORD_KEY = "C$94Y9CaC%";
	
	public static String DEFAULT_ERROR_VIEW = "error";
	
	@Autowired(required = true)
	public void setBasePath(@Value("${demos.base.path}")String basePath) {
		Const.BASE_PATH = basePath;
	}
	
}
