package com.bjpowernode.service.impl;

import com.bjpowernode.bean.User;
import com.bjpowernode.dao.impl.userDaoImpl;
import com.bjpowernode.service.UserService;
import com.bjpowernode.dao.userDao;

import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {
    private userDao userDao = new userDaoImpl();

    /*
    *  查询所有用户
    * */
    @Override
    public List<User> selectUser() {
        return userDao.selectUser();
    }

    /*
    * 增加用户
    * */
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    /*
    *   更新用户信息
    * */
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    /*
    *  删除用户
    * */

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
