package com.bridgelabz.bookstoreapplication.controller;


import com.bridgelabz.bookstoreapplication.dto.OrderDto;
import com.bridgelabz.bookstoreapplication.dto.ResponseDto;
import com.bridgelabz.bookstoreapplication.entity.Order;
import com.bridgelabz.bookstoreapplication.service.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceInterface orderServiceInterface;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody OrderDto Orderdto) {
        String response = orderServiceInterface.addOrder(Orderdto);
        ResponseDto responseDTO = new ResponseDto("order details added", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDto> GetAllDetails() {
        List<Order> response = orderServiceInterface.getAll();
        ResponseDto responseDto = new ResponseDto(" All order Details", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDto> FindById(@PathVariable int Id) {
        Order response = orderServiceInterface.FindById(Id);
        ResponseDto responseDto = new ResponseDto("***All Details of order on this id using Id***", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable int id) {
        orderServiceInterface.getOrderById(id);
        ResponseDto respDTO = new ResponseDto("Data deleted successfully\n",
                "Id:" + id + " is deleted");
        return new ResponseEntity<>(respDTO, HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> editData(@PathVariable int id, @Valid @RequestBody OrderDto orderDto) {
        String response = orderServiceInterface.editById(id, orderDto);
        ResponseDto responseDTO = new ResponseDto("Updated Book Details Successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}