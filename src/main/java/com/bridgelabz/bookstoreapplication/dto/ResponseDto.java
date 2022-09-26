package com.bridgelabz.bookstoreapplication.dto;

import com.bridgelabz.bookstoreapplication.entity.Book;
import com.bridgelabz.bookstoreapplication.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {
    private String message;
    private Object object;

    public ResponseDto(String s, String response) {
        this.message = s;
        this.object = response;
    }

    public ResponseDto(String s, User details) {
        this.message = s;
        this.object = details;
    }

    public ResponseDto(String s, Book response) {
        this.message = s;
        this.object = response;
    }

    public ResponseDto(String s, Object response) {
        this.message = s;
        this.object = response;
    }
}