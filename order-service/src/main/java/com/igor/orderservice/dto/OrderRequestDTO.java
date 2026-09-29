package com.igor.orderservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequestDTO(
        @NotNull Long customerId,
        @NotNull @Positive BigDecimal amount) {

}
