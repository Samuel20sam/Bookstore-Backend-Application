package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.CartDto;
import com.bridgelabz.bookstoreapplication.entity.Cart;

import java.util.List;

public interface CartServiceInterface {
    Cart addCart(CartDto cartDto);

    List<Cart> getAll();

    Cart FindById(int id);

    void deleteById(int id);

    Cart editById(int id, CartDto cardDto);

    Cart changeCartQty(int cardId, int quantity);
}
