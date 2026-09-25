package com.igor.customerservice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.igor.customerservice.dto.CustomerRequestDTO;
import com.igor.customerservice.dto.CustomerResponseDTO;
import com.igor.customerservice.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping()
  public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO request) {
    CustomerResponseDTO customer = customerService.createCustomer(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(customer);
  }

  @GetMapping("{id}")
  public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
    CustomerResponseDTO customer = customerService.getCustomerById(id);

    return ResponseEntity.ok().body(customer);
  }

  @PutMapping("{id}")
  public ResponseEntity<CustomerResponseDTO> updateCustomer(@Valid @PathVariable Long id,
      @RequestBody CustomerRequestDTO request) {
    CustomerResponseDTO customer = customerService.updateCustomerById(id, request);

    return ResponseEntity.ok().body(customer);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id,
      @RequestBody CustomerRequestDTO request) {
    customerService.deleteCustomer(id);

    return ResponseEntity.noContent().build();
  }

}
