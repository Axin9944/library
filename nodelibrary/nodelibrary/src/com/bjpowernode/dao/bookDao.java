package com.bjpowernode.dao;

import com.bjpowernode.bean.Book;

import java.util.List;

public interface bookDao {
    /*
    *   查询所有图书
    * */
    List<Book> selectBooks();
}
