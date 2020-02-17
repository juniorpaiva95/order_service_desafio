package br.com.conpay.order_service.orders.dto;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateDTO {

    @NotNull
    private UUID productId;

    @Min(value = 0)
    @NotNull
    private Double quantity;

}
