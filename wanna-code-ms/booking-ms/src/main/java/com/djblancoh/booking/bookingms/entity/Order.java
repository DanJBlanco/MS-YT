package com.djblancoh.booking.bookingms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "order-yt")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;
    String orderNo;

    @OneToMany(cascade = CascadeType.ALL)
    List<OrderItem> orderItemList;
}
