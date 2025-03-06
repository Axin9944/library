package com.bjpowernode.dao;

import com.bjpowernode.bean.User;

import java.util.List;

public interface userDao {
    List<User> selectUser();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
