package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.userDao;

import java.io.*;
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
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try{
            ois = new ObjectInputStream(new FileInputStream(Constant.USER_PATH));
            List<User> list = (List<User>) ois.readObject();
            list.add(user);

            oos = new ObjectOutputStream(new FileOutputStream(Constant.USER_PATH));
            oos.writeObject(list);
        }catch(Exception e){}finally{
            try{
                if(ois != null){
                    ois.close();
                }
                if(oos != null){
                    oos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /*
    *  修改用户数据
    * */
    @Override
    public void updateUser(User user) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try{
            ois = new ObjectInputStream(new FileInputStream(Constant.USER_PATH));
            List<User> userList = (List<User>)ois.readObject();
            User originUser = userList.stream().filter(u -> u.getId().equals(user.getId())).findFirst().get();

            originUser.setName(user.getName());
            originUser.setMoney(user.getMoney());

            oos = new ObjectOutputStream(new FileOutputStream(Constant.USER_PATH));
            oos.writeObject(userList);

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            try {
                if(oos != null){
                ois.close();
                }
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    /*
    * 删除用户
    * */

    @Override
    public void deleteUser(User user) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try{
            ois = new ObjectInputStream(new FileInputStream(Constant.USER_PATH));
            List<User> userList = (List<User>)ois.readObject();
            User deleteUser = userList.stream().filter(u -> u.getId().equals(user.getId())).findFirst().get();
            userList.remove(deleteUser);
            oos = new ObjectOutputStream(new FileOutputStream(Constant.USER_PATH));
            oos.writeObject(userList);

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            try{
                if(oos != null){
                    oos.close();
                }
                if(ois != null){
                    ois.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
