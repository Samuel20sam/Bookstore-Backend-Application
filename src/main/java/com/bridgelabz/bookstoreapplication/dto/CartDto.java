package com.bridgelabz.bookstoreapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {
    int userid;
    int bookId;
    int quantity;
}
