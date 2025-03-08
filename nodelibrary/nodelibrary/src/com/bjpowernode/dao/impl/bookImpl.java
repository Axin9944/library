package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Book;
import com.bjpowernode.bean.Constant;
import com.bjpowernode.dao.bookDao;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
    *   根据图书名、isbn号查询图书
    * */
    public List<Book> selectBooks(String bookName, String isbn) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.BOOK_PATH))){
            List<Book> bookList = (List<Book>) ois.readObject();

            if (bookName != null && isbn != null) {
                // 查询图书名字是否包含传入的名字
                List<Book> collect = bookList.stream().filter(book -> book.getBookName().contains(bookName))
                        .collect(Collectors.toList());
                // 判断图书ISBN号是否包含传入的isbn号
                return collect.stream().filter(book -> book.getIsbn().contains(isbn))
                        .collect(Collectors.toList());

            // 根据isbn号筛选
            }else if(isbn != null){
                return bookList.stream().filter(book -> book.getIsbn().contains(isbn))
                        .collect(Collectors.toList());

            // 根据图书姓名筛选
            }else {
                return bookList.stream().filter(book -> book.getBookName().contains(bookName))
                        .collect(Collectors.toList());
            }
        }catch (Exception e){
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

    /*
    *   删除图书
    * */
    @Override
    public void deleteBook(Book book){
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try{
            ois = new ObjectInputStream(new FileInputStream(Constant.BOOK_PATH));
            List<Book> bookList = (List<Book>)ois.readObject();
            // 根据图书 ID 找到图书并删除
            bookList.remove(bookList.stream().
                    filter(bk -> bk.getId().equals(book.getId())).findFirst().get());

            oos = new ObjectOutputStream(new FileOutputStream(Constant.BOOK_PATH));
            oos.writeObject(bookList);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /*
    *   修改图书基础信息
    * */
    @Override
    public void updateBook(Book book){
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try{
            ois = new ObjectInputStream(new FileInputStream(Constant.BOOK_PATH));
            List<Book> bookList = (List<Book>)ois.readObject();

            Book originBook = bookList.stream()
                    .filter(bk -> bk.getId().equals(book.getId())).findFirst().get();
            originBook.setBookName(book.getBookName());
            originBook.setIsbn(book.getIsbn());
            originBook.setAuthor(book.getAuthor());
            originBook.setPublisher(book.getPublisher());
            originBook.setType(book.getType());

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
                if(oos != null){
                    oos.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /*
    *   统计不同图书类型的数量
    * */
    @Override
    public Map<String, Integer> statisticsBook() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constant.BOOK_PATH))){
            List<Book> bookList = (List<Book>)ois.readObject();

            Map<String, List<Book>> bookTypeCollect = bookList.stream().collect(Collectors.groupingBy(Book::getType));
            Map<String, Integer> map = new HashMap<>();
            Iterator<Map.Entry<String, List<Book>>> iterator = bookTypeCollect.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, List<Book>> next = iterator.next();
                map.put(next.getKey(), next.getValue() == null ? 0 : next.getValue().size());
            }
            return map;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
