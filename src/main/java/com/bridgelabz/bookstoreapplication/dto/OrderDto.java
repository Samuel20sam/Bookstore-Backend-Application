package com.bridgelabz.bookstoreapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Valid
public class OrderDto {
    int price;
    int quantity;
    String address;
    int userid;
    int bookId;
    boolean cancel;
    LocalDate date = LocalDate.now();
}
