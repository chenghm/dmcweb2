package com.cinsec.dmc.base.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

	private String accessDeniedUrl;
	 
	public MyAccessDeniedHandler() {
	}
 
	public MyAccessDeniedHandler(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
 
	@Override
	public void handle(HttpServletRequest request,
		HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException,
		ServletException {
		/* boolean isAjax = isAjaxRequest(request);
		 if(!isAjax){
	            request.setAttribute("isAjaxRequest", isAjax);
	            request.setAttribute("message", accessDeniedException.getMessage());
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/authNotPass.jsp");
//	            response.sendRedirect(request.getContextPath()+"/authNotPass.jsp");
	            dispatcher.forward(request, response);
	        }else{
	            response.setCharacterEncoding("UTF-8");
	            response.setContentType("text/plain");
	            response.getWriter().write("权限不足");
	            response.getWriter().close();
	        }*/
 
	   response.sendRedirect(accessDeniedUrl);
	   request.getSession().setAttribute("message",
		"You do not have permission to access this page!");
 
	}
 
	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}
 
	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}
	
	private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
       return false;
    }

}
