package com.igor.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.igor.orderservice.entity.OrderEntity;

public record OrderResponseDTO(
    Long id,
    Long customerId,
    BigDecimal amount,
    OrderStatusEnum status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {

  public static OrderResponseDTO from(OrderEntity order) {
    return new OrderResponseDTO(
        order.getId(), order.getCustomerId(), order.getAmount(), order.getStatus(), order.getCreatedAt(),
        order.getUpdatedAt());
  }
}
