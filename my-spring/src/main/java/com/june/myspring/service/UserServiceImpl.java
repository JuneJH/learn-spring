package com.june.myspring.service;

import com.june.myspring.dao.UserDao;
import com.june.myspring.pojo.User;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> queryUserList(Map<String, Object> params) {
        return userDao.queryUserList(params);
    }
}
