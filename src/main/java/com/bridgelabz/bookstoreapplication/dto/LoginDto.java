package com.bridgelabz.bookstoreapplication.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class LoginDto {
    String email;
    @NotEmpty(message = "Password Cannot be Empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&*()-+=])([a-zA-Z0-9@._-]).{8,}$",
            message = """
                    Invalid Password
                    1. Upper case character that must occur at least once.
                    2. A digit must occur at least once.
                    3. Special symbol at least once.
                    4. lower case character or number must occur at least once.
                    5. Represents at least 8 or more characters.""")
    String password;
}