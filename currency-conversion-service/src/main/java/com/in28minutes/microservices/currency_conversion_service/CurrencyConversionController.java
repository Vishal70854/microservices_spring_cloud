package com.in28minutes.microservices.currency_conversion_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    @Autowired
    private RestTemplate restTemplate;

    // this controller will be using Feign Client
    // to communicate with currency-exchange from currency-conversion service

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
            ){

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to",to);
        // create new RestTemplate bean inorder to connect to currency-exchange-service and get details from that service to this currency-conversion-service
//        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
        ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                uriVariables);// uriVariables will be reading values from uri paths

        CurrencyConversion currencyConversion = responseEntity.getBody();// the actual value received from above responseBody

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " " + "rest template");
    }


    // this controller will be using Feign Client
    // to communicate with currency-exchange from currency-conversion service

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){


        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to); // the actual value received from above responseBody

        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment() + " " + "feign-client");
    }

}
