package br.com.conpay.order_service.orders.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.conpay.order_service.orders.dto.OrderCreateDTO;
import br.com.conpay.order_service.orders.dto.OrderDTO;
import br.com.conpay.order_service.orders.service.OrderService;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderCreateDTO order) {
        return ResponseEntity.ok().body(service.create(order));
    }

}