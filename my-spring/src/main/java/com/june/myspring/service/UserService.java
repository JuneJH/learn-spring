package com.june.myspring.service;

import com.june.myspring.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> queryUserList(Map<String,Object> params);
}
