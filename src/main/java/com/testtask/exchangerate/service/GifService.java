package com.testtask.exchangerate.service;

import com.testtask.exchangerate.api.ExchangeApi;
import com.testtask.exchangerate.api.GifApi;
import com.testtask.exchangerate.model.Gif;
import com.testtask.exchangerate.model.Rate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class GifService {

    private final GifApi gifApi;
    private final ExchangeApi exchangeApi;

    @Value("${feign.openexchangerates.symbols}")
    private String exchange;

    public Gif getGif(String code) {
        boolean b = rateIsUp(code);
        List<Gif> gifs = gifApi.getGif(b);
        log.info("GifService getGif code - {}, isUp-{}", code, b);
        return gifs
                .stream()
                .skip(new Random().nextInt(gifs.size()))
                .findFirst()
                .orElseThrow();
    }

    private boolean rateIsUp(String code) {
        String now = LocalDate.now().toString() + ".json";
        String yesterday = LocalDate.now().minusDays(1).toString() + ".json";
        Rate todayExchange = exchangeApi.getExchange(code, now);
        Rate yesterdayExchange = exchangeApi.getExchange(code, yesterday);
        Double rubToday = todayExchange.getRates().get(exchange);
        Double rubYesterday = yesterdayExchange.getRates().get(exchange);
        log.info("GifService rubToday - {}, rubYesterday - {}", rubToday, rubYesterday);
        return rubToday > rubYesterday;
    }

}
