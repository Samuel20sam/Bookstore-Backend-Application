package com.bridgelabz.bookstoreapplication.controller;

import com.bridgelabz.bookstoreapplication.dto.LoginDto;
import com.bridgelabz.bookstoreapplication.dto.ResponseDto;
import com.bridgelabz.bookstoreapplication.dto.UserDto;
import com.bridgelabz.bookstoreapplication.entity.User;
import com.bridgelabz.bookstoreapplication.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceInterface service;

    /**
     * Post API for insert user data
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> AddUserDetails(@Valid @RequestBody UserDto userDto) {
        String token = service.registerRecord(userDto);
        ResponseDto responseDTO = new ResponseDto("Data Added successfully", token);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Post API for Login for particular user
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> loginUser(@RequestBody LoginDto loginDTO) {
        User response = service.loginUser(loginDTO);
        ResponseDto responseDTO = new ResponseDto("Login Successful", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Put API for update data by email
     */
    @PutMapping("/edit/{email}")
    public ResponseEntity<ResponseDto> updateById(@Valid @RequestBody UserDto userDTO, @PathVariable String email) {
        User response = service.editByEmail(userDTO, email);
        ResponseDto respDTO = new ResponseDto("User details is updated", response);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * Get API for Forgot password with email
     */
    @GetMapping("/forgotPassword/{email}")
    public ResponseEntity<ResponseDto> forgotPasswordByEmail(@PathVariable String email) {
        String response = service.forgotPassword(email);
        ResponseDto respDTO = new ResponseDto("Reset Link sent successfully", response);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * Post API for resetPassword user data
     */
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody LoginDto loginDto) {
        String response = service.resetPassword(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Get API for getting All users
     */
    @GetMapping("/findAll")
    public ResponseEntity<ResponseDto> findAllUsers() {
        User response = service.findAll();
        ResponseDto responseDTO = new ResponseDto("All Users List", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Get API for retrieve user by id
     */
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable int Id) {
        User response = service.FindById(Id);
        ResponseDto responseDto = new ResponseDto("All the details of the user with the Id" + Id, response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Get API for retrieve user data by email
     */
    @GetMapping("/retrieve/{email}")
    public ResponseEntity<ResponseDto> getDataByEmail(@PathVariable String email) {
        User response = service.getByEmail(email);
        ResponseDto respDTO = new ResponseDto("Data by using email", response);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     * Post API for retrieve user by token
     */
    @GetMapping("/retrieve/{token}")
    public ResponseEntity<ResponseDto> getUserDetails(@Valid @PathVariable String token) {
        User response = service.getDataByToken(token);
        ResponseDto respDTO = new ResponseDto("Data retrieved successfully", response);
        return new ResponseEntity<>(respDTO, HttpStatus.CREATED);
    }

    /**
     * Delete API for deleting data using token and id match with first name
     */
    @DeleteMapping("/delete/{id}/{token}")
    public ResponseEntity<ResponseDto> retrieveData(@PathVariable int id, @PathVariable String token) {
        String user = service.deleteById(id, token);
        ResponseDto response = new ResponseDto("Delete data with the token" + token, user);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}