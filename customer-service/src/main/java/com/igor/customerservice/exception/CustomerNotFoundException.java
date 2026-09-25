package com.igor.customerservice.exception;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(Long customerId) {
    super("Customer " + customerId + " não encontrado");
  }
}
