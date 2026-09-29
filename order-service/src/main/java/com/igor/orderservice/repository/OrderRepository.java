package com.igor.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.orderservice.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
