package com.bridgelabz.bookstoreapplication.exception;

import com.bridgelabz.bookstoreapplication.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ApplicationExceptions {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> error_message = errorList.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ResponseDto responseDTO = new ResponseDto("Exception while processing REST request", error_message.toString());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResponseDto> handleAddressBookException(UserException exception) {
        ResponseDto resDTO = new ResponseDto("Exception while processing REST request", exception.getMessage());
        return new ResponseEntity<>(resDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ResponseDto> handleAddressBookException(BookException exception) {
        ResponseDto resDTO = new ResponseDto("Exception while processing REST request", exception.getMessage());
        return new ResponseEntity<>(resDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ResponseDto> handleAddressBookException(CartException exception) {
        ResponseDto resDTO = new ResponseDto("Exception while processing REST request for Book", exception.getMessage());
        return new ResponseEntity<>(resDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ResponseDto> handleAddressBookException(OrderException exception) {
        ResponseDto resDTO = new ResponseDto("Exception while processing REST request for cart", exception.getMessage());
        return new ResponseEntity<>(resDTO, HttpStatus.BAD_REQUEST);
    }
}