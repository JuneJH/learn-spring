package com.june.myspring.test;

import com.june.myspring.dao.UserDaoImpl;
import com.june.myspring.pojo.User;
import com.june.myspring.service.UserServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        UserDaoImpl userDao = new UserDaoImpl();

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://121.36.51.141:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        userDao.setDataSource(dataSource);
        userService.setUserDao(userDao);

        //实现用户查询功能
        Map<String, Object> map = new HashMap<>();
        map.put("name","june");
        List<User> users = userService.queryUserList(map);
        System.out.println("===="+users);
    }
}
