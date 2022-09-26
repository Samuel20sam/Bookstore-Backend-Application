package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.dto.LoginDto;
import com.bridgelabz.bookstoreapplication.dto.UserDto;
import com.bridgelabz.bookstoreapplication.entity.User;

public interface UserServiceInterface {
    String registerRecord(UserDto addressDto);

    User FindById(int id);

    User getByEmail(String email);

    User editByEmail(UserDto userDTO, String email_address);

    User getDataByToken(String token);

    User loginUser(LoginDto loginDto);

    String forgotPassword(String email);

    String resetPassword(LoginDto loginDto);

    User findAll();

    String deleteById(int id, String token);

}


