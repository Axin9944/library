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
    *   根据图书姓名 isbn号筛选图书
    * */

    @Override
    public List<Book> selectBook(String bookName, String isbn) {
        return bookDao.selectBooks(bookName, isbn);
    }

    /*
    *   添加图书至文件
    * */
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    /*
    *   删除图书
    * */
    @Override
    public void deleteBook(Book book) {
        bookDao.deleteBook(book);
    }
}
