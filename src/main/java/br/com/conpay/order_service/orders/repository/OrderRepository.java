package br.com.conpay.order_service.orders.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.conpay.order_service.orders.model.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
