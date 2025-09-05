package com.in28minutes.microservices.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "currency-exchange", url = "localhost:8000") // this will only connect to currency-exchange service using the port 8000
@FeignClient(value = "currency-exchange")   // only using currency-exchange as app name will talk to all instances of currency-exchange using load balancing factor
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);


    }
