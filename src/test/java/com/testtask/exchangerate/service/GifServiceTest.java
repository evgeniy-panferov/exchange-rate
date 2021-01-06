package com.testtask.exchangerate.service;

import com.testtask.exchangerate.GifTestData;
import com.testtask.exchangerate.api.ExchangeApi;
import com.testtask.exchangerate.api.GifApi;
import com.testtask.exchangerate.model.Gif;
import com.testtask.exchangerate.model.Rate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(value = {"classpath:application.properties"})
class GifServiceTest {

    @Autowired
    private GifService gifService;

    @MockBean
    private GifApi gifApi;

    @MockBean
    private ExchangeApi exchangeApi;

    @Value("${feign.openexchangerates.symbols}")
    private String symbol;

    @Value("${feign.openexchangerates.base}")
    private String baseCurrency;

    @Test
    void getGifIsFalseBroke() {

        Rate rateNow = new Rate(1609372757L, baseCurrency, new HashMap<>() {{
            put(symbol.toUpperCase(), 74.177102);
        }});

        Rate rateYesterday = new Rate(1609311554L, baseCurrency, new HashMap<>() {{
            put(symbol.toUpperCase(), 74.377102);
        }});

        when(gifApi.getGif(false)).thenReturn(GifTestData.gifIsFalseBroke);
        when(exchangeApi.getExchange(baseCurrency, LocalDate.now().toString() + ".json"))
                .thenReturn(rateNow);
        when(exchangeApi.getExchange(baseCurrency, LocalDate.now().minusDays(1).toString() + ".json"))
                .thenReturn(rateYesterday);

        Gif gif = gifService.getGif(baseCurrency);

        assertTrue(GifTestData.gifIsFalseBroke.contains(gif));

        verify(exchangeApi, Mockito.times(1))
                .getExchange(baseCurrency, (LocalDate.now().toString() + ".json"));
        verify(exchangeApi, Mockito.times(1))
                .getExchange(baseCurrency, (LocalDate.now().minusDays(1).toString() + ".json"));
        verify(gifApi, Mockito.times(1))
                .getGif(false);

    }


    @Test
    void getGifIsTrueRich() {
        Rate rateNow = new Rate(1609372757L, baseCurrency.toUpperCase(), new HashMap<>() {{
            put(symbol.toUpperCase(), 74.377102);
        }});

        Rate rateYesterday = new Rate(1609311554L, baseCurrency.toUpperCase(), new HashMap<>() {{
            put(symbol.toUpperCase(), 74.177102);
        }});

        when(gifApi.getGif(true)).thenReturn(GifTestData.gifIsTrueRich);
        when(exchangeApi.getExchange(baseCurrency.toUpperCase(), LocalDate.now().toString() + ".json"))
                .thenReturn(rateNow);
        when(exchangeApi.getExchange(baseCurrency.toUpperCase(), LocalDate.now().minusDays(1).toString() + ".json"))
                .thenReturn(rateYesterday);

        Gif gif = gifService.getGif(baseCurrency.toUpperCase());

        assertTrue(GifTestData.gifIsTrueRich.contains(gif));

        verify(exchangeApi, Mockito.times(1))
                .getExchange(baseCurrency.toUpperCase(), (LocalDate.now().toString() + ".json"));
        verify(exchangeApi, Mockito.times(1))
                .getExchange(baseCurrency.toUpperCase(), (LocalDate.now().minusDays(1).toString() + ".json"));
        verify(gifApi, Mockito.times(1))
                .getGif(true);
    }
}