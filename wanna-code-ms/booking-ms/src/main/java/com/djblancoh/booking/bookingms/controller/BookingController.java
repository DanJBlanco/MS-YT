package com.djblancoh.booking.bookingms.controller;

import com.djblancoh.booking.bookingms.dto.OrderDTO;
import com.djblancoh.booking.bookingms.entity.Order;
import com.djblancoh.booking.bookingms.repository.OrderRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final OrderRepository orderRepository;

    public BookingController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public String saveOrder(@RequestBody OrderDTO orderDTO) {
        Order order = Order.builder()
                .orderNo(UUID.randomUUID().toString())
                .orderItemList(orderDTO.getOrderItemList())
                .build();

        orderRepository.save(order);

        return "Order Saved";
    }
}
