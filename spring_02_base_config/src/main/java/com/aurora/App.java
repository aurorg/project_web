package com.aurora;

import com.aurora.service.BookService;
import com.aurora.service.impl.BookServiceImpl;

public class App {
    public static void main(String[] args){
        BookService bookService = new BookServiceImpl();
        bookService.save();
    }
}
