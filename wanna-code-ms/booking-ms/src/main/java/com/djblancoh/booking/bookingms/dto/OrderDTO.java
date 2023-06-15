package com.djblancoh.booking.bookingms.dto;

import com.djblancoh.booking.bookingms.entity.OrderItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE)
public class OrderDTO {

    List<OrderItem> orderItemList;


}
