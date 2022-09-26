package com.bridgelabz.bookstoreapplication.controller;

import com.bridgelabz.bookstoreapplication.dto.BookDto;
import com.bridgelabz.bookstoreapplication.dto.ResponseDto;
import com.bridgelabz.bookstoreapplication.entity.Book;
import com.bridgelabz.bookstoreapplication.service.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookServiceInterface bookServiceInterface;

    /**
     * Post API for insert Book dta
     */
    @PostMapping("/insert")
    public ResponseEntity<ResponseDto> insertBookDetails(@RequestBody BookDto bookDto) {
        String response = bookServiceInterface.insertBookDetails(bookDto);
        ResponseDto responseDto = new ResponseDto("Book Details Added successfully", response);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * Get API for get all Book in list
     */
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> GetAllBookDetails() {
        List<Book> response = bookServiceInterface.getAll();
        ResponseDto responseDto = new ResponseDto("All Book with Details", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Get API for retrieve Book details using id
     */
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable int Id) {
        Book response = bookServiceInterface.FindById(Id);
        ResponseDto responseDto = new ResponseDto("All Details of Book using Id", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Get API for Book details by book name
     */
    @GetMapping("/Book/{bookName}")
    public ResponseEntity<ResponseDto> getDataByBook(@PathVariable String bookName) {
        Book response = bookServiceInterface.getByBook(bookName);
        ResponseDto respDTO = new ResponseDto("Data by using email", response);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * Delete API to delete BOok details using Book id
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int id) {
        bookServiceInterface.getBookById(id);
        ResponseDto respDTO = new ResponseDto("Data deleted successfully\n",
                "Id:" + id + " is deleted");
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * Put API for update data by Book id
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> editData(@PathVariable int id, @Valid @RequestBody BookDto BookDto) {
        String response = bookServiceInterface.editById(id, BookDto);
        ResponseDto responseDTO = new ResponseDto("Updated Book Details Successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Get API for sort data by accenting Order with price
     */
    @GetMapping("/sortByPriceAsc")
    public ResponseEntity<ResponseDto> getBookByPriceAsc() {
        List<Book> bookData = bookServiceInterface.sortPriceLowToHigh();
        ResponseDto responseDTO = new ResponseDto("Sorted all books by price low to high", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Get API for Sort data in descending order
     */
    @GetMapping("/sortByPriceDsc")
    public ResponseEntity<ResponseDto> getBookByPriceDsc() {
        List<Book> bookData = bookServiceInterface.sortPriceHighToLow();
        ResponseDto responseDTO = new ResponseDto("Sorted all books by price high to low", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Post API for Change quantity of book
     */
    @PostMapping("/change-qty")
    public ResponseEntity<ResponseDto> changeBookQuantity(@RequestParam int id, @RequestParam int quantity) {
        Book response = bookServiceInterface.changeBookQty(id, quantity);
        ResponseDto responseDTO = new ResponseDto("Book quantity changed successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}