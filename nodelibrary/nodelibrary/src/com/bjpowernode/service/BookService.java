package com.bjpowernode.service;

import com.bjpowernode.bean.Book;

import java.util.List;

public interface BookService {
    List<Book> selectBook();
    void addBook(Book book);
}
