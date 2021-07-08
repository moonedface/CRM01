package com.example.crm.workbench.web.controller;

import com.example.crm.settings.domain.User;
import com.example.crm.settings.service.UserService;
import com.example.crm.settings.service.impl.UserServiceImpl;
import com.example.crm.utils.PrintJson;
import com.example.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ActivityController extends HttpServlet {
    @Override
    public void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");
        String path=requset.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){
            getUserList(requset,response);
        }else if("/workbench/activity/xx.do".equals(path)){

        }
    }

    private void getUserList(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("取出用户信息列表");
        UserService us= (UserService)ServiceFactory.getService(new UserServiceImpl());
        List<User> list=us.getUserList();
        //解析用户列表
        PrintJson.printJsonObj(response,list);
    }
}
