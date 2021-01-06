package com.testtask.exchangerate.api;

import com.testtask.exchangerate.model.Rate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(value = {"classpath:application-test.properties"})
@AutoConfigureWireMock(port = 0)
class ExchangeApiTest {

    @Autowired
    private ExchangeApi exchangeApi;

    @Value("${feign.openexchangerates.base}")
    private String baseCurrency;

    @Value("${feign.openexchangerates.symbols}")
    private String symbol;

    @Value("${feign.openexchangerates.authorization}")
    private String token;

    @Test
    void getExchange() throws IOException {

        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/json/ExchangeResponse.json")));

        stubFor(get(urlPathMatching("/api/historical/[0-9]{4}-[0-9]{2}-[0-9]{2}.json"))
                .withQueryParam("base", matching("[a-zA-Z]{3}"))
                .withQueryParam("symbols", matching("[a-zA-Z]{3}"))
                .withHeader("Authorization", equalTo(token))
                .willReturn(okJson(json)));

        String todayExchange = LocalDate.now().toString() + ".json";
        Rate exchange = exchangeApi.getExchange(baseCurrency.toUpperCase(), todayExchange);

        Rate rate = new Rate(1609372757L, baseCurrency.toUpperCase(), new HashMap<>() {{
            put(symbol.toUpperCase(), 74.377102);
        }});

        assertEquals(rate, exchange);
        verify(1, getRequestedFor(urlPathMatching("/api/historical/[0-9]{4}-[0-9]{2}-[0-9]{2}.json"))
                .withQueryParam("base", matching("[a-zA-Z]{3}"))
                .withQueryParam("symbols", matching("[a-zA-Z]{3}"))
                .withHeader("Authorization", equalTo(token)));
    }
}