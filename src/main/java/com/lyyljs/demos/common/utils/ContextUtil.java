package com.lyyljs.demos.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ContextUtil {
	
	public static HttpServletRequest getRequest()
	  {
	    HttpServletRequest request = null;
	    if ((RequestContextHolder.getRequestAttributes() != null) && 
	      (((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest() != null)) {
	      request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	    }
	    return request;
	  }
	
	public static void writeJson(HttpServletResponse response, Object obj) throws IOException{
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("application/json; charset=utf-8");
	    PrintWriter print = response.getWriter();
	    ObjectMapper mapper = new ObjectMapper();
	    String json=mapper.writeValueAsString(obj);
	    print.print(json);
	}
}
