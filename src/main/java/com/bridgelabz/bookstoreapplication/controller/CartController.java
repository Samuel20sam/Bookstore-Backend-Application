package com.bridgelabz.bookstoreapplication.controller;

import com.bridgelabz.bookstoreapplication.dto.CartDto;
import com.bridgelabz.bookstoreapplication.dto.ResponseDto;
import com.bridgelabz.bookstoreapplication.entity.Cart;
import com.bridgelabz.bookstoreapplication.service.CartServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartServiceInterface cartServiceInterface;

    /**
     * Post Api for insert card details
     */
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody CartDto cartDto) {
        Cart cart = cartServiceInterface.addCart(cartDto);
        ResponseDto responseDTO = new ResponseDto("cart details added", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Get Api to get All list of cart
     */
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> GetAllBookDetails() {
        List<Cart> response = cartServiceInterface.getAll();
        ResponseDto responseDto = new ResponseDto("All Book with Details", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Get Api for get Al  card details of particular id
     */
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable int Id) {
        Cart response = cartServiceInterface.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details of Book using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Delete particular card details if given id
     */
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int Id) {
        cartServiceInterface.deleteById(Id);
        ResponseDto responseDto = new ResponseDto("Cart Data deleted successfully\n",
                "Id:" + Id + " is deleted");
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    /**
     * Put Api to update particular Data by id
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> editData(@PathVariable int id, @Valid @RequestBody CartDto cardDto) {
        Cart response = cartServiceInterface.editById(id, cardDto);
        ResponseDto responseDTO = new ResponseDto("Updated Book Details Successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Post Api to update Quantity
     */
    @PostMapping("/update-qty")
    public ResponseEntity<ResponseDto> changeBookQuantity(@RequestParam int cartId, @RequestParam int quantity) {
        Cart cart = cartServiceInterface.changeCartQty(cartId, quantity);
        ResponseDto responseDTO = new ResponseDto("Cart quantity changed successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}