package com.djblancoh.booking.bookingms.repository;

import com.djblancoh.booking.bookingms.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
