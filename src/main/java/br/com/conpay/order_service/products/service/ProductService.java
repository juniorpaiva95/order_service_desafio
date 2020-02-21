package br.com.conpay.order_service.products.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conpay.order_service.products.dto.ProductDTO;
import br.com.conpay.order_service.products.model.ProductStatus;
import br.com.conpay.order_service.products.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductDTO getOne(UUID id) {
        log.info("Product -> getOne");

        return new ProductDTO(repository.getOne(id));
    }

    public List<ProductDTO> getAll() {
        log.info("Product -> getAll");

        return repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public List<ProductDTO> getFromStatus(ProductStatus status) {
        log.info("Product -> getFromStatus");

        return repository.findByStatus(status).stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public ProductDTO create(ProductDTO product) {
        log.info("Product -> create");

        product.setId(null);
        product.setStatus(ProductStatus.ACTIVATED);

        return new ProductDTO(repository.save(product.toEntity()));
    }

    public ProductDTO update(ProductDTO product) {
        log.info("Product -> update");

        return new ProductDTO(repository.save(product.toEntity()));
    }

    public ProductDTO delete(ProductDTO product) {
        log.info("Product -> delete");

        repository.delete(product.toEntity());
        return new ProductDTO(product.toEntity());
    }

}