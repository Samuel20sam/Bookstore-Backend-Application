package com.bridgelabz.bookstoreapplication.entity;

import com.bridgelabz.bookstoreapplication.dto.LoginDto;
import com.bridgelabz.bookstoreapplication.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int userId;
    String firstName;
    String lastName;
    String address;
    String email;
    LocalDate DOB;
    String password;

    public User(UserDto userdto) {
        this.firstName = userdto.getFirstName();
        this.lastName = userdto.getLastName();
        this.address = userdto.getAddress();
        this.email = userdto.getEmail();
        this.DOB = userdto.getDOB();
        this.password = userdto.getPassword();
    }

    public User(LoginDto logindto) {
        this.email = logindto.getEmail();
        this.password = logindto.getPassword();
    }
}