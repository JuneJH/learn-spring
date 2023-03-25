package com.june.myspring.test;

import com.june.myspring.dao.UserDaoImpl;
import com.june.myspring.pojo.User;
import com.june.myspring.service.UserServiceImpl;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        UserDaoImpl userDao = new UserDaoImpl();

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/Test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        userDao.setDataSource(dataSource);
        userService.setUserDao(userDao);

        //实现用户查询功能
        Map<String, Object> map = new HashMap<>();
        map.put("name","测试是否开启");
        List<User> users = userService.queryUserList(map);
        System.out.println("===="+users);

    }
}
