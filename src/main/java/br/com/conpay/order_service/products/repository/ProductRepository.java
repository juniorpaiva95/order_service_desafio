package br.com.conpay.order_service.products.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conpay.order_service.products.model.Product;
import br.com.conpay.order_service.products.model.ProductStatus;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByStatus(ProductStatus status);

}