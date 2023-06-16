package com.djblancoh.booking.bookingms.controller;

import com.djblancoh.booking.bookingms.client.StockClient;
import com.djblancoh.booking.bookingms.dto.OrderDTO;
import com.djblancoh.booking.bookingms.entity.Order;
import com.djblancoh.booking.bookingms.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    public static final String ORDER_SERVICE = "orderService";

    private final OrderRepository orderRepository;
    private final StockClient stockClient;

    public BookingController(OrderRepository orderRepository, StockClient stockClient) {
        this.orderRepository = orderRepository;
        this.stockClient = stockClient;
    }

    @PostMapping
    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "setDisconnectMessage")
    public String saveOrder(@RequestBody OrderDTO orderDTO) {
        boolean isStock = orderDTO.getOrderItemList().stream()
                .allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));

        if(!isStock){
            return  "Order cannot be saved";
        }

        Order order = Order.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderItemList(orderDTO.getOrderItemList())
                .build();

        orderRepository.save(order);

        return "Order Saved";
    }

    public String setDisconnectMessage(Exception e){
        return "Order Service down: " + e.getMessage();
    }
}
