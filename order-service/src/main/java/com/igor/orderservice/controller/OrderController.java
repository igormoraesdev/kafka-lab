package com.igor.orderservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.orderservice.dto.OrderRequestDTO;
import com.igor.orderservice.dto.OrderResponseDTO;
import com.igor.orderservice.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/orders")
@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping()
  public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO request) {
    OrderResponseDTO order = orderService.createOrder(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(order);
  }

  @GetMapping()
  public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
    List<OrderResponseDTO> orders = orderService.getAllOrders();

    return ResponseEntity.ok().body(orders);
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
    OrderResponseDTO order = orderService.getOrderById(id);

    return ResponseEntity.ok().body(order);
  }

  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<OrderResponseDTO>> getOrdersByCustomerId(@PathVariable Long customerId) {
    List<OrderResponseDTO> orders = orderService.getOrdersByCustomerId(customerId);

    return ResponseEntity.ok().body(orders);
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Long id,
      @Valid @RequestBody OrderRequestDTO request) {
    OrderResponseDTO order = orderService.updateOrderById(id, request);

    return ResponseEntity.ok().body(order);
  }

  @PatchMapping("/{id}/cancel")
  public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Long id) {
    return ResponseEntity.ok().body(orderService.cancelOrder(id));
  }

}
