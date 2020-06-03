package com.tasks.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorizerInterceptor extends HandlerInterceptorAdapter{
	
	@Override 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception{
		String uri = request.getRequestURI(); 
		if(uri.endsWith("loginForm") || uri.endsWith("Login") || uri.contains("resources")) 
			return true; 
		if(uri.replaceAll("/", "").equals(request.getContextPath().replaceAll("/", ""))) {
			response.sendRedirect("listTasks"); 
			return false; 
		}
		if(request.getSession().getAttribute("userOnline") != null) 
			return true; 
		response.sendRedirect("loginForm"); 
		return false;
	}

}