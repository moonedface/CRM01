package com.example.crm.settings.dao;

import com.example.crm.settings.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    User login(@Param("loginact") String loginAct, @Param("loginpwd") String loginPwd);

    List<User> getUserList();
}
