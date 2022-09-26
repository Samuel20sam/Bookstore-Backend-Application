package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.exception.UserException;
import com.bridgelabz.bookstoreapplication.repository.UserRepository;
import com.bridgelabz.bookstoreapplication.dto.LoginDto;
import com.bridgelabz.bookstoreapplication.dto.UserDto;
import com.bridgelabz.bookstoreapplication.entity.User;
import com.bridgelabz.bookstoreapplication.utility.EmailSenderService;
import com.bridgelabz.bookstoreapplication.utility.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public String registerRecord(UserDto userDto) throws UserException {
        User user = new User(userDto);
        userRepository.save(user);
        String token = tokenUtility.createToken(user.getUserId());
        emailSenderService.sendEmail(user.getEmail(), user.getFirstName(), user. getLastName(),
                "Added Your Details", "http://localhost:8080/user/retrieve/" + token);
        return token;
    }


    @Override
    public User FindById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User editByEmail(UserDto userDTO, String email) {
        User editData = userRepository.findByEmail(email);
        if (editData != null) {
            editData.setFirstName(userDTO.getFirstName());
            editData.setLastName(userDTO.getLastName());
            editData.setEmail(userDTO.getEmail());
            editData.setAddress(userDTO.getAddress());
            editData.setDOB(userDTO.getDOB());
            editData.setPassword(userDTO.getPassword());
            User user = userRepository.save(editData);
            String token = tokenUtility.createToken(editData.getUserId());
            emailSenderService.sendEmail(editData.getEmail(),
                    editData.getFirstName(), editData.getLastName(),
                    "Added Your Details", "http://localhost:8080/user/retrieve/" + token);
            return user;
        } else {
            throw new UserException("email:" + email + " is not present ");
        }

    }

    @Override
    public User getDataByToken(String token) {
        int Userid = tokenUtility.decodeToken(token);
        Optional<User> existingData = userRepository.findById(Userid);
        if (existingData.isPresent()) {
            return existingData.get();
        } else
            throw new UserException("Invalid Token");
    }

    @Override
    public User loginUser(LoginDto loginDto) {
        Optional<User> userDetails = Optional.ofNullable(userRepository.findByEmail(loginDto.getEmail()));
        if (userDetails.isPresent()) {
            if (userDetails.get().getPassword().equals(loginDto.getPassword())) {
                emailSenderService.sendEmail(userDetails.get().getEmail(),
                        userDetails.get().getFirstName(), userDetails.get().getLastName(),
                        "You have Logged in", "Login Successful!");
                return userDetails.get();
            } else
                emailSenderService.sendEmail(userDetails.get().getEmail(),
                        userDetails.get().getFirstName(), userDetails.get().getLastName(),
                        "About Login", "Invalid password!");
            throw new UserException("Wrong Password!");
        } else
            throw new UserException("Login Failed, Wrong email or password!!!");
    }

    @Override
    public String forgotPassword(String email) {
        User editData = userRepository.findByEmail(email);
        if (editData != null) {
            emailSenderService.sendEmail(editData.getEmail(), editData.getFirstName(), editData.getLastName(),
                    "About Login", "http://localhost:8080/user/resetPassword/" + email);
            return "Reset link sent successfully";
        } else
            throw new UserException("Login Failed, Wrong email or password!!!");
    }

    @Override
    public String resetPassword(LoginDto loginDTO) {
        Optional<User> userDetails = Optional.ofNullable(userRepository.findByEmail(loginDTO.getEmail()));
        String password = loginDTO.getPassword();
        if (userDetails.isPresent()) {
            userDetails.get().setPassword(password);
            userRepository.save(userDetails.get());
            return "Password Changed";
        } else
            return "Invalid Email Address";
    }

    @Override
    public User findAll() {
        List<User> user = userRepository.findAll();
        return user.get(0);
    }

    @Override
    public String deleteById(int id, String token) {
        Optional<User> user = userRepository.findById(id);
        int userid = tokenUtility.decodeToken(token);
        Optional<User> userToken = userRepository.findById(userid);
        if (user.get().getFirstName().equals(userToken.get().getFirstName())) {
            userRepository.deleteById(id);
            return user.get() + "User is deleted for this ID";
        } else
            throw new UserException("Data is not match");

    }
}