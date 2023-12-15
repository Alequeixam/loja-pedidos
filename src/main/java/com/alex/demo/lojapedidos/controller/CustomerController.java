package com.alex.demo.lojapedidos.controller;

import com.alex.demo.lojapedidos.dto.CustomerDTO;
import com.alex.demo.lojapedidos.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(service.create(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        return ResponseEntity.ok().body(service.getCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customer) {
        return new ResponseEntity<>(service.updateCustomer(id, customer), HttpStatus.OK);
    }


}
