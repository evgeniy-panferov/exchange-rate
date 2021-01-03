package com.testtask.exchangerate.client;

import com.testtask.exchangerate.model.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.openexchangerates.name}", url = "${feign.openexchangerates.url}")
public interface ExchangeRateClient {

    @GetMapping(path = "${feign.openexchangerates.historical}/{date}", headers = "Authorization = ${feign.openexchangerates.authorization}")
    Rate getExchangeRate(@RequestParam String base, @RequestParam String symbols, @PathVariable String date);
}
