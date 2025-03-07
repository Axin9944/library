package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.dao.bookDao;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class bookImpl implements bookDao {

    /*
    *   查询所有图书
    * */
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

    /*
    *   添加图书
    * */
    @Override
    public void addBook(Book book) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try{
            ois = new ObjectInputStream(new FileInputStream(Constant.BOOK_PATH));
            List<Book> bookList = (List<Book>) ois.readObject();
            bookList.add(book);

            oos = new ObjectOutputStream(new FileOutputStream(Constant.BOOK_PATH));
            oos.writeObject(bookList);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            try{
                if(ois != null){
                    ois.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
