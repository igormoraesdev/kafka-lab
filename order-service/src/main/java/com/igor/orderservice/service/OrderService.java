package com.igor.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.igor.orderservice.dto.OrderRequestDTO;
import com.igor.orderservice.dto.OrderResponseDTO;
import com.igor.orderservice.dto.OrderStatusEnum;
import com.igor.orderservice.entity.OrderEntity;
import com.igor.orderservice.exception.OrderNotFoundException;
import com.igor.orderservice.repository.OrderRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderResponseDTO createOrder(OrderRequestDTO request) {
    OrderEntity order = new OrderEntity();
    order.setAmount(request.amount());
    order.setCustomerId(request.customerId());
    order.setStatus(OrderStatusEnum.CREATED);

    OrderEntity orderSaved = orderRepository.save(order);

    return OrderResponseDTO.from(orderSaved);
  }

  public List<OrderResponseDTO> getAllOrders() {
    return orderRepository.findAll().stream()
        .map(OrderResponseDTO::from)
        .toList();
  }

  public OrderResponseDTO getOrderById(Long orderId) {
    OrderEntity order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException("Order %d não econtrada".formatted(orderId)));

    return OrderResponseDTO.from(order);
  }

  public List<OrderResponseDTO> getOrdersByCustomerId(Long customerId) {
    List<OrderEntity> orders = orderRepository.findByCustomerId(customerId);

    return orders.stream().map(OrderResponseDTO::from).toList();
  }

  @Transactional
  public OrderResponseDTO updateOrderById(Long orderId,
      OrderRequestDTO request) {
    OrderEntity order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException("Order %d não econtrada".formatted(orderId)));

    if (order.getStatus() != OrderStatusEnum.CREATED) {
      throw new IllegalStateException("Só é possível alterar pedidos com status CREATED");
    }

    order.setAmount(request.amount());

    return OrderResponseDTO.from(orderRepository.saveAndFlush(order));
  }

  @Transactional
  public OrderResponseDTO cancelOrder(Long orderId) {
    OrderEntity order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException("Order %d não econtrada".formatted(orderId)));

    if (order.getStatus() == OrderStatusEnum.COMPLETED || order.getStatus() == OrderStatusEnum.CANCELLED) {
      throw new IllegalStateException("Só é possível cancelar pedidos com status diferente de COMPLETADO e CANCELADO");
    }
    order.setStatus(OrderStatusEnum.CANCELLED);

    return OrderResponseDTO.from(orderRepository.saveAndFlush(order));
  }
}
