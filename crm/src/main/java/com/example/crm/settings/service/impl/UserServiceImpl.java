package com.example.crm.settings.service.impl;

import com.example.crm.exception.LoginException;
import com.example.crm.settings.dao.UserDao;
import com.example.crm.settings.domain.User;
import com.example.crm.settings.service.UserService;
import com.example.crm.utils.DateTimeUtil;
import com.example.crm.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao= SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
    public User login(String loginAct,String loginPwd,String ip) throws LoginException {
        System.out.println("是否执行了serrvice方法");
        User user=userDao.login(loginAct,loginPwd);
        if(user==null){
            //数据库查找失败，抛出异常
            throw new LoginException("账号密码错误");
        }
        //如果程序继续执行，验证其他信息
        //验证失效时间
        String expireTime =user.getExpireTime();
        String currentTime= DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currentTime)<0){
            //账号已失效
            throw new LoginException("账号已失效");
        }
        //验证锁定状态
        String lockState=user.getLockState();
        if("0".equals(lockState)){
            throw new LoginException("账号已锁定");
        }
        //判断ip地址
        String allowIps=user.getAllowIps();
        if(!allowIps.contains(ip)){
            throw new LoginException("ip地址异常");
        }
        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> list=userDao.getUserList();
        return list;
    }
}
