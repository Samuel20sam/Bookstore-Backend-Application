package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.BookDto;
import com.bridgelabz.bookstoreapplication.exception.BookException;
import com.bridgelabz.bookstoreapplication.entity.Book;
import com.bridgelabz.bookstoreapplication.repository.BookRepository;
import com.bridgelabz.bookstoreapplication.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookServiceInterface {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TokenUtility tokenUtility;

    @Override
    public String insertBookDetails(BookDto bookDto) {
        Book book = new Book(bookDto);
        bookRepository.save(book);
        return tokenUtility.createToken(book.getId());
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book FindById(int cartId) {
        Optional<Book> book = bookRepository.findById(cartId);
        return book.get();
    }

    @Override
    public Book getByBook(String bookName) {
        Book bookList = bookRepository.findByName(bookName);
        if (bookList != null) {
            bookRepository.findByName("book-name");
            return bookList;
        } else throw new BookException(" Book with name is found!");
    }

    @Override
    public void getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
        } else throw new BookException("Id:" + id + " not present");
    }

    @Override
    public String editById(int id, BookDto Bookdto) {
        Book editBook = bookRepository.findById(id).orElse(null);
        if (editBook != null) {
            editBook.setBookName(Bookdto.getBookName());
            editBook.setAuthorName(Bookdto.getAuthorName());
            editBook.setBookDescription(Bookdto.getBookDescription());
            editBook.setBookImg(Bookdto.getBookImg());
            editBook.setPrice(Bookdto.getPrice());
            editBook.setQuantity(Bookdto.getQuantity());
            bookRepository.save(editBook);
            return tokenUtility.createToken(editBook.getId());
        } else
            throw new BookException("Id:" + id + " is not present ");
    }

    @Override
    public List<Book> sortPriceLowToHigh() {
        return bookRepository.getSortedListOfBooksInAsc();
    }

    @Override
    public List<Book> sortPriceHighToLow() {
        return bookRepository.getSortedListOfBooksInDesc();
    }

    @Override
    public Book changeBookQty(int id, int quantity) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new BookException("Id is not found");
        }
        book.setQuantity(quantity);
        return bookRepository.save(book);
    }
}