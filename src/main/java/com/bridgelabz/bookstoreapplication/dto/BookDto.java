package com.bridgelabz.bookstoreapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;

@Data
@NoArgsConstructor
@Valid
public class BookDto {
    String bookName;
    String authorName;
    String bookDescription;
    String bookImg;
    int price;
    int quantity;
}