package com.cinsec.dmc.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cinsec.dmc.user.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionInterceptor implements Interceptor {

    /**
     * 
     */
    private static final long serialVersionUID = -2857927334377108032L;

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public String intercept(ActionInvocation actioninvocation) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        HttpServletRequest request =
                (HttpServletRequest) actioninvocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response =
                (HttpServletResponse) actioninvocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
        if (user == null) {
            {
                if (request.getHeader("x-requested-with") != null
                        && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))// 如果是ajax请求响应头会有，x-requested-with；
                {
                    response.setHeader("sessionstatus", "timeout");// 在响应头设置session状态
                }

            }
        }
        return actioninvocation.invoke();
    }

}
