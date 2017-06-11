package com.lyyljs.demos.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.lyyljs.demos.common.Const;
import com.lyyljs.demos.service.UserService;

public class SecurityFilter implements Filter {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	private static Set<String> GreenUrlSet = new HashSet<String>();
	
	@Resource
	private UserService userService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpServletResponse response = (HttpServletResponse) sresponse;
		String uri = request.getRequestURI();
		if (request.getSession().getAttribute(Const.LOGIN_SESSION_KEY) == null) {
			if (GreenUrlSet.contains(uri) || containsSuffix(uri) || containsKey(uri)){
				logger.debug("url in white list :" + uri);
				filterChain.doFilter(srequest, sresponse);
				return;
			}
			logger.debug("url not in white list :" + uri);
			response.sendRedirect("/"); 
			return;
		}
		filterChain.doFilter(srequest, sresponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		GreenUrlSet.add("/");
		GreenUrlSet.add("/user/login");
	}
	
	private boolean containsKey(String url) {

		if (url.contains("/swagger-resources")
				|| url.contains("/v2/api-docs")
				|| url.contains("/file")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean containsSuffix(String url) {
		if (url.endsWith(".js")
				|| url.endsWith(".css")
				|| url.endsWith(".jpg")
				|| url.endsWith(".gif")
				|| url.endsWith(".png")
				|| url.endsWith(".html")
				|| url.endsWith(".eot")
				|| url.endsWith(".svg")
				|| url.endsWith(".ttf")
				|| url.endsWith(".woff")
				|| url.endsWith(".ico")
				|| url.endsWith(".woff2")) {
			return true;
		} else {
			return false;
		}
	}
	
}
