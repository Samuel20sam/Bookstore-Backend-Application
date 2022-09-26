package com.bridgelabz.bookstoreapplication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Book_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", nullable = false)
    int orderID;
    LocalDate date = LocalDate.now();
    int price;
    int quantity;
    String address;

    @OneToOne
    @JoinColumn(name = "User_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "book_Id")
    Book book;

    boolean cancel;

    public Order(LocalDate date, int price, int quantity, String address, User user, Book book, boolean cancel) {
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.user = user;
        this.book = book;
        this.cancel = cancel;
    }
}
