package com.bjpowernode.util;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.bean.Lend;
import com.bjpowernode.bean.User;
import com.bjpowernode.service.BookService;
import com.bjpowernode.service.UserService;
import com.bjpowernode.service.impl.BookServiceImpl;
import com.bjpowernode.service.impl.UserServiceImpl;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
*  初始化数据的工具类
* */
public class initDatautil {
    public static void main(String[] args) {
        initUser();
        initBook();
        initLend();
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

    /*
    *   初始化借书数据
    * */
    public static void initLend(){
        File durectiry = null;
        File file = null;
        ObjectOutputStream oos = null;
        ObjectOutputStream userOos = null;
        ObjectOutputStream bookOos = null;
        try{
            durectiry = new File(Constant.Lend_PATH.split("/")[0] + "/");
            if(!durectiry.exists()){
                durectiry.mkdir();
            }
            file = new File(Constant.Lend_PATH);
            if (!file.exists()){
                file.createNewFile();
                Lend lend = new Lend();
                UserService userService = new UserServiceImpl();
                List<User> userList = userService.selectUser();
                BookService bookService = new BookServiceImpl();
                List<Book> bookList = bookService.selectBook();
                List<Lend> lendList = new ArrayList<>();
                // 设置借书用户
                lend.setUser(userList.get(0));
                // 将该用户是否已借书的状态改为true
                userList.get(0).setLend(true);
                // 设置借的图书
                lend.setBook(bookList.get(0));
                // 将该图书的状态改为出借
                bookList.get(0).setStatus(Constant.STATUS_LEND);
                // 设置订单ID
                lend.setId(UUID.randomUUID().toString());
                // 设置状态
                lend.setStatus(Constant.STATUS_LEND);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 获取当前时间
                LocalDate localDate = LocalDate.now();
                // 将当前时间设置借书时间
                lend.setLendDate(localDate);
                // 将当前时间加7天为借书还书时间
                lend.setReturnDate(localDate.plusDays(7));

                lendList.add(lend);
                oos = new ObjectOutputStream(new FileOutputStream(file));
                userOos = new ObjectOutputStream(new FileOutputStream(Constant.USER_PATH));
                bookOos = new ObjectOutputStream(new FileOutputStream(Constant.BOOK_PATH));
                oos.writeObject(lendList);
                userOos.writeObject(userList);
                bookOos.writeObject(bookList);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(oos!=null){
                    oos.close();
                }
                if(userOos!=null){
                    userOos.close();
                }
                if(bookOos!=null){
                    bookOos.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
