package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.CartDto;
import com.bridgelabz.bookstoreapplication.exception.CartException;
import com.bridgelabz.bookstoreapplication.entity.Book;
import com.bridgelabz.bookstoreapplication.entity.Cart;
import com.bridgelabz.bookstoreapplication.entity.User;
import com.bridgelabz.bookstoreapplication.repository.BookRepository;
import com.bridgelabz.bookstoreapplication.repository.CartRepository;
import com.bridgelabz.bookstoreapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartServiceInterface {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public Cart addCart(CartDto cartDto) {
        Optional<User> user = userRepository.findById(cartDto.getUserid());
        Optional<Book> book = bookRepository.findById(cartDto.getBookId());
        if (user.isPresent() && book.isPresent()) {
            Cart cartDetails = new Cart(user.get(), book.get(), cartDto.getQuantity());
            cartRepository.save(cartDetails);
            return cartDetails;

        } else
            throw new CartException(" userid and book-id is invalid");
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart FindById(int id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.get();
    }

    @Override
    public void deleteById(int id) {
        Optional<Cart> findById = cartRepository.findById(id);
        if (findById.isPresent()) {
            cartRepository.deleteById(id);
        } else throw new CartException("Id:" + id + " not present");
    }

    @Override
    public Cart editById(int id, CartDto cartDto) {
        Optional<Book> book = bookRepository.findById(cartDto.getBookId());
        Optional<User> user = userRepository.findById(cartDto.getUserid());
        Cart editData = cartRepository.findById(id).orElse(null);
        if (editData != null) {
            editData.setBook(book.get());
            editData.setUser(user.get());
            editData.setQuantity(cartDto.getQuantity());
            return cartRepository.save(editData);
        } else
            throw new CartException("Id:" + id + " is not present ");
    }

    @Override
    public Cart changeCartQty(int cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new CartException("id is not found");
        }
        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }
}