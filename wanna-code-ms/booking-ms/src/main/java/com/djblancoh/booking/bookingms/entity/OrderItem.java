package com.djblancoh.booking.bookingms.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE)
public class OrderItem {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    BigDecimal price;
    Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

}
