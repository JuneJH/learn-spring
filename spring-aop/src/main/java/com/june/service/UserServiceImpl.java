package com.june.service;

public class UserServiceImpl implements UserService{
    public UserServiceImpl() {
    }

    @Override
    public boolean add() {
        System.out.println("增加了一个用户！！！！");
        return  true;
    }

    @Override
    public void save() {
        System.out.println("保存了一个用户！！！！");
    }

    @Override
    public void update() {
        System.out.println("更新了一个用户！！！！");
    }

    @Override
    public void delete() {
        System.out.println("删除了一个用户！！！！");
    }
}
