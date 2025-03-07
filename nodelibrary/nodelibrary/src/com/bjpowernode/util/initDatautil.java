package com.bjpowernode.util;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.User;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
*  初始化数据的工具类
* */
public class initDatautil {
    public static void main(String[] args) {
        initUser();
        initBook();
    }

    /*
    *  初始化用户数据
    * */
    public static void initUser(){
        // 创建相关文件夹及文件
        File durectiry = null;
        File file = null;
        ObjectOutputStream oos = null;
        try{
            durectiry = new File(Constant.USER_PATH.split("/")[0] + "/");
            // 判断文件夹是否存在，不存在则创建
            if (!durectiry.exists()){
                durectiry.mkdir();
            }
            file = new File(Constant.USER_PATH);
            // 判断文件是否存在，不存在则创建
            if (!file.exists()){
                file.createNewFile();
                List<User> userList = new ArrayList<>();
                // 使用 GBK 编码将字符串转换为字节数组
                String name = "陈麻子";
                System.out.println(name);
                /*System.out.println(Constant.USER_OK);
                byte[] GBKBytes = name.getBytes(Charset.forName("GBK"));
                // 使用 GBK 编码将字节数组转换为字符串
                name = new String(GBKBytes, Charset.forName("GBK"));*/
                //System.out.println(name);
                userList.add(
                        new User(name, Constant.USER_OK,
                                UUID.randomUUID().toString(),
                                new BigDecimal(10), false));
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(userList);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /*
    *  初始化图书数据
    * */
    public static void initBook(){
        // 创建相关文件夹及文件
        File durectiry = null;
        File file = null;
        ObjectOutputStream oos = null;
        try{
            durectiry = new File(Constant.BOOK_PATH.split("/")[0] + "/");
            // 判断文件夹是否存在，不存在则创建
            if (!durectiry.exists()){
                durectiry.mkdir();
            }
            file = new File(Constant.BOOK_PATH);
            // 判断文件是否存在，不存在则创建
            if (!file.exists()){
                file.createNewFile();
                List<Book> bookList = new ArrayList<>();
                // 创建图书对象添加至集合中
                bookList.add(new Book(UUID.randomUUID().toString(), "编码", "查尔斯", Constant.TYPE_COMPUTER, "978712",
                        "电子工业出版社", Constant.STATUS_STORAGE));
                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(bookList);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
