package com.lyyljs.demos.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyyljs.demos.common.Const;
import com.lyyljs.demos.common.logger.MethodDescription;
import com.lyyljs.demos.common.utils.MD5Util;
import com.lyyljs.demos.common.utils.ParamsUtil;
import com.lyyljs.demos.domain.User;
import com.lyyljs.demos.domain.result.Response;
import com.lyyljs.demos.domain.result.ResponseData;
import com.lyyljs.demos.domain.result.ResponseMsg;
import com.lyyljs.demos.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "USER", description="user controller test") 
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/getAll", method = RequestMethod.GET)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = User.class)
	})
	public ResponseData getAllUsers(){
		List<User> list = userService.getAllUsers();
		return new ResponseData(ResponseMsg.SUCCESS, list);
	}
	
	@ApiOperation(value="用户登陆", notes="账号密码登陆")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userName", value = "账号", paramType = "query", required = true, dataType = "string"),
        @ApiImplicitParam(name = "userPasswd", value = "密码", paramType = "query", 
        			required = true, dataType = "string", 
        			regPassPattern = {ParamsUtil.NumberRegPattern}, 
        			regFilterPattern = {ParamsUtil.NonnegativeIntegerRegPattern}),
        @ApiImplicitParam(name = "testInteger", value = "密码", paramType = "query", dataType = "int", allowableValues="range[ins,22]"),
        @ApiImplicitParam(name = "testDouble", value = "密码", paramType = "query", dataType = "double", allowableValues="1, 2, 3.3"),
        @ApiImplicitParam(name = "testB", value = "密码", paramType = "query", dataType = "boolean", defaultValue="true"),
	})
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public Response login(String userName, String userPasswd, 
			Integer testInteger, Double testDouble, Boolean testB, 
			HttpServletResponse response){
		User loginUser = userService.haveUser(userName, MD5Util.encrypt(userPasswd));
		if (loginUser == null){
			return new Response(ResponseMsg.LoginNameOrPassWordError);
		}
		getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginUser);
		return new Response(ResponseMsg.SUCCESS);
	}
}
