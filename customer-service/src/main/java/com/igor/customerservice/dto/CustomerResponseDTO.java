package com.igor.customerservice.dto;

import java.time.LocalDateTime;

import com.igor.customerservice.entity.CustomerEntity;

public record CustomerResponseDTO(
    Long id,
    String name,
    String email,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {

  public static CustomerResponseDTO from(CustomerEntity customer) {
    return new CustomerResponseDTO(customer.getId(), customer.getName(), customer.getEmail(),
        customer.getCreatedAt(), customer.getUpdatedAt());
  }
}
