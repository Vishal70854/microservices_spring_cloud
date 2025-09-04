package com.in28minutes.microservices.currency_exchange_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    // BELOW METHOD WILL SEARCH THE currency_exchange table in db and fetch the records/rows matching 'from' and 'to'
    CurrencyExchange findByFromAndTo(String from, String to);
}
