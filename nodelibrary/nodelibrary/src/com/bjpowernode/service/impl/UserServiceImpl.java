package com.bjpowernode.service.impl;

import com.bjpowernode.bean.User;
import com.bjpowernode.dao.impl.userDaoImpl;
import com.bjpowernode.service.UserService;
import com.bjpowernode.dao.userDao;

import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {
    private userDao userDao = new userDaoImpl();

    @Override
    public List<User> selectUser() {
        return userDao.selectUser();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
}
