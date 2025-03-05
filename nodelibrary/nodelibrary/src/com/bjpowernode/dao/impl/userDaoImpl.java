package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.userDao;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

public class userDaoImpl implements userDao {

    /*
    * 从硬盘中读取user数据
    * */
    @Override
    public List<User> selectUser() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.USER_PATH))){
            List<User> list = (List<User>) ois.readObject();
            return list;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /*
    * 添加用户
    * */
    @Override
    public void addUser(User user) {

    }
}
