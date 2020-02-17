package br.com.conpay.order_service.orders.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conpay.order_service.orders.dto.ItemCreateDTO;
import br.com.conpay.order_service.orders.dto.OrderCreateDTO;
import br.com.conpay.order_service.orders.dto.OrderDTO;
import br.com.conpay.order_service.orders.model.Item;
import br.com.conpay.order_service.orders.model.Order;
import br.com.conpay.order_service.orders.repository.OrderRepository;
import br.com.conpay.order_service.products.model.Product;
import br.com.conpay.order_service.products.model.ProductStatus;
import br.com.conpay.order_service.products.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductService productService;

    public OrderDTO getOne(UUID id) {
        log.info("Order -> getOne");

        return new OrderDTO(repository.getOne(id));
    }

    public List<OrderDTO> getAll() {
        log.info("Order -> getAll");

        return repository.findAll().stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    public OrderDTO create(OrderCreateDTO orderCreate) {
        log.info("Order -> create");

        // Produtos para itens do pedido
        List<Item> items = getItems(orderCreate.getItems());

        // Soma de valores do pedido
        Double amount = getAmount(items);

        // Calcula o valor a pagar com o desconto
        Double amountPayable = getAmountPayable(orderCreate.getDiscount(), amount);

        return new OrderDTO(repository.save(new Order(null, amount, amountPayable, orderCreate.getDiscount(), items)));
    }

    // Produtos para itens do pedido
    private List<Item> getItems(List<ItemCreateDTO> items) {
        log.info("Order -> create -> getItems");

        return items.stream().map((item) -> {
            // Buscar cadastro do produto
            Product product = productService.getOne(item.getProductId()).toEntity();

            if (product.getStatus() == ProductStatus.ACTIVATED) {
                return new Item(product, item.getQuantity(), product.getPrice());
            } else {
                throw new RuntimeException("Produto selecionado não ativado");
            }
        }).collect(Collectors.toList());
    }

    // Soma de valores do pedido
    private Double getAmount(List<Item> items) {
        log.info("Order -> create -> getTotal");

        return items.stream().map((item) -> {
            return item.getPrice() * item.getQuantity();
        }).reduce(0d, Double::sum);
    }

    // Calcula o valor a pagar com o desconto
    private Double getAmountPayable(Double discount, Double amount) {
        log.info("Order -> create -> getAmountPayable");

        return ((100d - discount) * amount) / 100d;
    }

}