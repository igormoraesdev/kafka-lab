package com.igor.customerservice.service;

import org.springframework.stereotype.Service;

import com.igor.customerservice.dto.CustomerRequestDTO;
import com.igor.customerservice.dto.CustomerResponseDTO;
import com.igor.customerservice.entity.CustomerEntity;
import com.igor.customerservice.exception.CustomerNotFoundException;
import com.igor.customerservice.repository.CustomerRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerResponseDTO createCustomer(CustomerRequestDTO request) {
    CustomerEntity customer = new CustomerEntity();
    customer.setEmail(request.email());
    customer.setName(request.name());

    CustomerEntity customerSaved = customerRepository.save(customer);

    return CustomerResponseDTO.from(customerSaved);
  }

  public CustomerResponseDTO getCustomerById(Long customerId) {
    CustomerEntity customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer não encontrado"));

    return CustomerResponseDTO.from(customer);
  }

  public CustomerResponseDTO updateCustomerById(Long customerId, CustomerRequestDTO request) {
    CustomerEntity customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer não encontrado"));

    if (request != null && customerId != null) {
      customer.setEmail(request.email());
      customer.setName(request.name());
    }
    return CustomerResponseDTO.from(customerRepository.save(customer));
  }

  public void deleteCustomer(Long customerId) {
    if (!customerRepository.existsById(customerId)) {
      throw new CustomerNotFoundException(customerId);
    }
    customerRepository.deleteById(customerId);
  }
}
