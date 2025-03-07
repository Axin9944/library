package com.bjpowernode.service.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.dao.impl.bookImpl;
import com.bjpowernode.dao.bookDao;
import com.bjpowernode.service.BookService;

import java.util.Collections;
import java.util.List;

public class BookServiceImpl implements BookService {
    private bookDao bookDao = new bookImpl();

    @Override
    public List<Book> selectBook() {
       return bookDao.selectBooks();
    }
}
