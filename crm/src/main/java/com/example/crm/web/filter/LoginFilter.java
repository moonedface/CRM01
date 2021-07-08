package com.example.crm.web.filter;


import com.example.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入到验证有没有登录过的过滤器");
        //向下转型
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse)resp;
        //获取路径
        String path=request.getServletPath();
        if("/login.jsp".equals(path)||"/settings/user/login.do".equals(path)){
            chain.doFilter(req,resp);
        }else {
            //获取session
            HttpSession session=request.getSession();
            User user=(User)session.getAttribute("user");
            //如果可以从session中获取到user，说明登陆过，放行
            if(user!=null){
                chain.doFilter(req,resp);
            }else {
                //验证有没有登陆过
                //重定向到登录页
            /*
            重定向的路径怎么写？
            在实际项目开发中，对于路径的使用，前端和后端都一律使用绝对路径
            关于转发和重定向的路径写法如下：
            请求转发：
            使用的是一种特殊的绝对路径的使用方式，这种绝对路径前面不加/项目名，被称之为内部路径
            /login.jsp
            重定向：
            使用的是传统写法的绝对路径:项目名开头
            /crm/login.jsp"
             */
            /*
            为什么要使用重定向？不用转发？
            转发之后，路径会停留在老路径上，而不是跳转到最新资源的路径
            我们应该为用户跳转到登陆页面的同时，将浏览器的地址栏自动设置为当前登录页的路径
             */
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }

    }
}
