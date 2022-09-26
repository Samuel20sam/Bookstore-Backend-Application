package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.OrderDto;
import com.bridgelabz.bookstoreapplication.exception.OrderException;
import com.bridgelabz.bookstoreapplication.entity.Book;
import com.bridgelabz.bookstoreapplication.entity.Order;
import com.bridgelabz.bookstoreapplication.entity.User;
import com.bridgelabz.bookstoreapplication.repository.BookRepository;
import com.bridgelabz.bookstoreapplication.repository.OrderRepository;
import com.bridgelabz.bookstoreapplication.repository.UserRepository;
import com.bridgelabz.bookstoreapplication.utility.EmailSenderService;
import com.bridgelabz.bookstoreapplication.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public String addOrder(OrderDto orderdto) {
        Optional<User> user = userRepository.findById(orderdto.getUserid());
        Optional<Book> book = bookRepository.findById(orderdto.getBookId());
        if (user.isPresent() && book.isPresent()) {
            Order orderDetails = new Order(orderdto.getDate(),orderdto.getPrice(), orderdto.getQuantity(),
                    orderdto.getAddress(),user.get(), book.get()
                    ,orderdto.isCancel());
            orderRepository.save(orderDetails);
            String token = tokenUtility.createToken(orderDetails.getOrderID());
            emailSenderService.sendEmail(user.get().getEmail(),
                    user.get().getFirstName(), user.get().getLastName(),
                    "Added Your Details", "http://localhost:8080/user/retrieve/" + token);
            return token;
        } else
            throw new OrderException(" userid and book-id is invalid");
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order FindById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.get();
    }

    @Override
    public void getOrderById(int id) {
        Optional<Order> findById = orderRepository.findById(id);
        if (findById.isPresent()){
            orderRepository.deleteById(id);
        } else throw new OrderException("Id:"+id+" not present");
    }

    @Override
    public String editById(int id, OrderDto orderDto) {
        Order order = orderRepository.findById(id).orElse(null);
        Optional<Book> book = bookRepository.findById(orderDto.getBookId());
        Optional<User> user = userRepository.findById(orderDto.getUserid());
        if (order != null) {
            order.setPrice(orderDto.getPrice());
            order.setQuantity(orderDto.getQuantity());
            order.setAddress(orderDto.getAddress());
            order.setUser(user.get());
            order.setBook(book.get());
            order.setCancel(orderDto.isCancel());
            orderRepository.save(order);
            String token = tokenUtility.createToken(order.getOrderID());
            emailSenderService.sendEmail(user.get().getEmail(),
                    user.get().getFirstName(), user.get().getLastName(),
                    "Added Your Details", "http://localhost:8080/user/retrieve/" + token);
            return token;
        } else
            throw new OrderException("Id:" + id + " is not present ");
    }
}