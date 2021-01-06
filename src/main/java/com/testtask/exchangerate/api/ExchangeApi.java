package com.testtask.exchangerate.api;

import com.testtask.exchangerate.client.ExchangeRateClient;
import com.testtask.exchangerate.model.Rate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeApi {

    private final ExchangeRateClient exchangeRateClient;

    @Value("${feign.openexchangerates.symbols}")
    private String symbol;

    public Rate getExchange(String code, String date) {
        log.info("ExchangeApi getExchange base - {}, symbol- {}, date - {}", code, symbol.toUpperCase(), date);
        return exchangeRateClient.getExchangeRate(code, symbol.toUpperCase(), date);
    }
}
