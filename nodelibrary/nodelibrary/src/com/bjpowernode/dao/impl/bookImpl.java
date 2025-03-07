package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.dao.bookDao;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;

public class bookImpl implements bookDao {
    @Override
    public List<Book> selectBooks() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.BOOK_PATH))){
            List<Book> bookList = (List<Book>) ois.readObject();
            return bookList;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
