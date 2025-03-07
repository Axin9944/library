package com.bjpowernode.dao;

import com.bjpowernode.bean.Book;

import java.util.List;

public interface bookDao {
    /*
    *   查询所有图书
    * */
    List<Book> selectBooks();

    /*
    *   根据图书名字、ISBN号查询图书
    * */
    List<Book> selectBooks(String bookName, String isbn);

    /*
    *  添加图书
    * */
    void addBook(Book book);

    /*
    *   删除图书
    * */
    void deleteBook(Book book);
}
