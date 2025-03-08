package com.bjpowernode.service;

import com.bjpowernode.bean.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> selectBook();
    void addBook(Book book);
    List<Book> selectBook(String bookName, String isbn);
    void deleteBook(Book book);
    void updateBook(Book book);
    Map<String, Integer> statisticsBook();
}
