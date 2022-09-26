package com.bridgelabz.bookstoreapplication.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cartId", nullable = false)
    int cartId;

    @OneToOne
    @JoinColumn(name = "userId")
    User user;

    @ManyToOne
    @JoinColumn(name = "id")
    Book book;
    int quantity;

    public Cart(User user, Book book, int quantity) {
        this.user=user;
        this.book=book;
        this.quantity=quantity;
    }
}
