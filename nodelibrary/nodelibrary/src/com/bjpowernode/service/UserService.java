package com.bjpowernode.service;

import com.bjpowernode.bean.User;

import java.util.List;

public interface UserService {
    List<User> selectUser();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    void updateStatus(String id, String status);
    List<User> selectUserToLend();
}
