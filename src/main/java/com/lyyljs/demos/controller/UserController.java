package com.lyyljs.demos.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lyyljs.demos.common.Const;
import com.lyyljs.demos.common.logger.MethodDescription;
import com.lyyljs.demos.common.utils.MD5Util;
import com.lyyljs.demos.domain.User;
import com.lyyljs.demos.domain.result.Response;
import com.lyyljs.demos.domain.result.ResponseData;
import com.lyyljs.demos.domain.result.ResponseMsg;
import com.lyyljs.demos.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/getAll")
	@MethodDescription(description="getAll")
	public ResponseData getAllUsers(){
		List<User> list = userService.getAllUsers();
		return new ResponseData(ResponseMsg.SUCCESS, list);
	}
	
	@RequestMapping(value="/login")
	@MethodDescription(description="login")
	public Response login(User user, HttpServletResponse response){
		User loginUser = userService.haveUser(user.getName(), MD5Util.encrypt(user.getPasswd()));
		if (loginUser == null){
			return new Response(ResponseMsg.LoginNameOrPassWordError);
		}
		getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginUser);
		return new Response(ResponseMsg.SUCCESS);
	}
}
