package com.bjpowernode.service.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.dao.impl.bookImpl;
import com.bjpowernode.dao.bookDao;
import com.bjpowernode.service.BookService;

import java.util.Collections;
import java.util.List;

public class BookServiceImpl implements BookService {
    private bookDao bookDao = new bookImpl();

    /*
    *   查询所有图书
    * */
    @Override
    public List<Book> selectBook() {
       return bookDao.selectBooks();
    }

    /*
    *   添加图书至文件
    * */
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }
}
