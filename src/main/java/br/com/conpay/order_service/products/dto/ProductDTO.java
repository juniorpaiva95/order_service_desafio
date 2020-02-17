package br.com.conpay.order_service.products.dto;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.conpay.order_service.products.model.Product;
import br.com.conpay.order_service.products.model.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private UUID id;

    @NotBlank
    private String title;

    @Min(value = 0)
    @NotNull
    private Double price;

    private ProductStatus status;

    public ProductDTO(Product product) {
        this(product.getId(), product.getDescription(), product.getPrice(), product.getStatus());
    }

    public Product toEntity() {
        return new Product(getId(), getTitle(), getPrice(), getStatus());
    }

}