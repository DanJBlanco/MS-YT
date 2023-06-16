package com.djblancoh.booking.bookingms.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

public interface StockClient extends WebClient {

    @RequestMapping("/api/stock/{code}")
    boolean stockAvailable(@PathVariable String code);

}
