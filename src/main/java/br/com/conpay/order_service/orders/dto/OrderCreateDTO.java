package br.com.conpay.order_service.orders.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDTO {

    @Min(value = 0)
    @Max(value = 100)
    @NotNull
    private Double discount;

    @Size(min = 1)
    @NotNull
    private List<ItemCreateDTO> items;

}
