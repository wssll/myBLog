package com.sll.blog.interceptor;

import org.slf4j.Logger;
import org.springframework.orm.hibernate4.support.OpenSessionInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user")==null){
            response.sendRedirect("/user");
            return false;
        }
        return true;
    }
}
