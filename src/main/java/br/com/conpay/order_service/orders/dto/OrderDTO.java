package br.com.conpay.order_service.orders.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.conpay.order_service.orders.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private UUID id;

    private Double amount;

    private Double amountPayable;

    private Double discount;

    private List<ItemDTO> items;

    public OrderDTO(Order order) {
        this(order.getId(), order.getAmount(), order.getAmountPayable(), order.getDiscount(),
                order.getItems().stream().map(ItemDTO::new).collect(Collectors.toList()));
    }

}
