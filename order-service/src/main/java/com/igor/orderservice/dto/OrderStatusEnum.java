package com.igor.orderservice.dto;

public enum OrderStatusEnum {
  CREATED,
  PAYMENT_PENDING,
  PAYMENT_APPROVED,
  PAYMENT_REJECTED,
  INVENTORY_RESERVED,
  COMPLETED,
  CANCELLED
}
