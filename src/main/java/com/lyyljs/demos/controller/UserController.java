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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="USER") 
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	@MethodDescription(description="getAll")
	public ResponseData getAllUsers(){
		List<User> list = userService.getAllUsers();
		return new ResponseData(ResponseMsg.SUCCESS, list);
	}
	
	@ApiOperation(value="用户登陆", notes="账号密码登陆")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userName", value = "账号", paramType = "form", required = true, dataType = "String"),
        @ApiImplicitParam(name = "userPasswd", value = "密码", paramType = "form",required = true, dataType = "String")
	})
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@MethodDescription(description="login")
	public Response login(String userName, String userPasswd, HttpServletResponse response){
		User loginUser = userService.haveUser(userName, MD5Util.encrypt(userPasswd));
		if (loginUser == null){
			return new Response(ResponseMsg.LoginNameOrPassWordError);
		}
		getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginUser);
		return new Response(ResponseMsg.SUCCESS);
	}
}
