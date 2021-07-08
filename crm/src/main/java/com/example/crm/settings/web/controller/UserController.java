package com.example.crm.settings.web.controller;

import com.example.crm.settings.domain.User;
import com.example.crm.settings.service.UserService;
import com.example.crm.settings.service.impl.UserServiceImpl;
import com.example.crm.utils.MD5Util;
import com.example.crm.utils.PrintJson;
import com.example.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到用户控制器");

        String path=request.getServletPath();
        if("/settings/user/login.do".equals(path)){
            login(request,response);
        }else if("/settings/user/xxx.do".equals(path)){

        }

    }
    private void login(HttpServletRequest requset, HttpServletResponse response){
        //
        System.out.println("进入验证登陆操作");
        String loginAct=requset.getParameter("loginAct");

        String loginPwd=requset.getParameter("loginPwd");
        //将密码转换成MD5
        System.out.println("密码"+loginPwd);
        loginPwd= MD5Util.getMD5(loginPwd);
        System.out.println("密码"+loginPwd);
        //接收IP地址
        String ip=requset.getRemoteAddr();
        System.out.println("---------ip:"+ip);
        //创建service对象
        //业务层开发，统一使用代理类形态的接口对象
        UserService us= (UserService) ServiceFactory.getService(new UserServiceImpl());
        System.out.println("weisha ");
        try{
            //登陆成功，请求对象获取当前用户的session
            //将user存入会话作用域对象
            User user=us.login(loginAct,loginPwd,ip);
            requset.getSession().setAttribute("user",user);
            //如果程序执行到此处，说明业务层没有为conroller抛出任何异常
            //表示登陆成功---返回true
            System.out.println("weisha ");
            PrintJson.printJsonFlag(response,true);

        }catch (Exception e){
            e.printStackTrace();
            //一旦程序执行了catch块的信息，说明业务层为我们验证登陆失败，为controller抛出了异常
           String msg=e.getMessage();
           //我们作为controller需要为ajax请求提供多项信息
            //可以有两种手段来处理：
            //1）将多项信息打包成map，将map解析成json串
            //2）创建一个Vo
            //如果对于展现的信息将来还会大量使用，我们创建一个vo类，使用方便。
            //这里使用map
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}
