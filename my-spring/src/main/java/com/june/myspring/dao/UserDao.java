package com.june.myspring.dao;

import com.june.myspring.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> queryUserList(Map<String,Object> params);
}
