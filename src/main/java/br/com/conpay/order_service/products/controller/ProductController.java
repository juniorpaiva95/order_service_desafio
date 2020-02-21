package br.com.conpay.order_service.products.controller;

import org.springframework.web.bind.annotation.*;

import br.com.conpay.order_service.products.dto.ProductDTO;
import br.com.conpay.order_service.products.model.ProductStatus;
import br.com.conpay.order_service.products.service.ProductService;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.getOne(id));
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<ProductDTO>> getFromStatus(@RequestParam(required = true) ProductStatus status) {
        return ResponseEntity.ok().body(service.getFromStatus(status));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO product) {
        return ResponseEntity.ok().body(service.create(product));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO product) {
        return ResponseEntity.ok().body(service.update(product));
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<ProductDTO> delete(ProductDTO product) {
        return ResponseEntity.ok().body(service.delete(product));
    }

}