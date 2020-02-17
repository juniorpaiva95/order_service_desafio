package br.com.conpay.order_service.orders.dto;

import java.util.UUID;

import br.com.conpay.order_service.orders.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private UUID productId;

    private String description;

    private Double quantity;

    private Double price;

    ItemDTO(Item item) {
        this(item.getProduct().getId(), item.getProduct().getDescription(), item.getQuantity(), item.getPrice());
    }

}
