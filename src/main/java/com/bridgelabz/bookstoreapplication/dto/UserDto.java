package com.bridgelabz.bookstoreapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Valid
public class UserDto {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",
            message = "Invalid First Name(First Letter Should be in Upper Case and min 3 Characters.)")
    String firstName;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{1,}$",
            message = "Invalid Last Name(First Letter Should be in Upper Case")
    String lastName;

    String address;

    String email;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Start Date cannot be Empty")
    @PastOrPresent(message = "Start Date should be past or present date")
    LocalDate DOB;

    @NotEmpty(message = "Address Cannot be Empty")
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


