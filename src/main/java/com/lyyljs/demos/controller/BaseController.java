package com.lyyljs.demos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lyyljs.demos.common.Const;
import com.lyyljs.demos.domain.User;
import com.lyyljs.demos.domain.result.Response;
import com.lyyljs.demos.domain.result.ResponseMsg;

public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected Response result(ResponseMsg msg){
		return new Response(msg);
	}
	
	protected Response result(){
    	return new Response();
    }
	
	protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    protected HttpSession getSession() {
        return getRequest().getSession();
    }
    
    protected User getUser() {
        return (User) getSession().getAttribute(Const.LOGIN_SESSION_KEY);
    }
    
    protected String getUserIp() {
    	String value = getRequest().getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
            return value;
        } else {
            return getRequest().getRemoteAddr();
        }
    }
}
