package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.BookDto;
import com.bridgelabz.bookstoreapplication.entity.Book;

import java.util.List;

public interface BookServiceInterface {
    String insertBookDetails(BookDto bookDto);

    List<Book> getAll();

    Book FindById(int id);

    Book getByBook(String bookName);

    void getBookById(int id);

    String editById(int id, BookDto bookDTO);

    List<Book> sortPriceLowToHigh();

    List<Book> sortPriceHighToLow();

    Book changeBookQty(int cartId, int quantity);
}