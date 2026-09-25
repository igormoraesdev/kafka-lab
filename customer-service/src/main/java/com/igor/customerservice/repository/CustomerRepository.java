package com.igor.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.customerservice.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
