package com.testtask.exchangerate.api;

import com.testtask.exchangerate.GifTestData;
import com.testtask.exchangerate.model.Gif;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource(value = {"classpath:application-test.properties"})
@AutoConfigureWireMock(port = 0)
class GifApiTest {

    @Autowired
    private GifApi gifApi;

    @Value("${giphy.api_key}")
    private String apiKey;

    @Test
    void getGifIsFalseBroke() throws IOException {

        String json = new String(Files
                .readAllBytes(Paths.get("src/test/resources/json/GiphyResponseIsBroke.json")));

        stubFor(get(urlPathMatching("/v1/gifs/search"))
                .withQueryParam("q", equalTo("broke"))
                .withQueryParam("api_key", equalTo(apiKey))
                .willReturn(okJson(json)));

        List<Gif> gifsFalse = gifApi.getGif(false);

        assertEquals(GifTestData.gifIsFalseBroke, gifsFalse);
        verify(1, getRequestedFor(urlPathMatching("/v1/gifs/search"))
                .withQueryParam("q", equalTo("broke"))
                .withQueryParam("api_key", equalTo(apiKey)));
    }

    @Test
    void getGifIsTrueRich() throws IOException {

        String json = new String(Files
                .readAllBytes(Paths.get("src/test/resources/json/GiphyResponseIsRich.json")));

        stubFor(get(urlPathMatching("/v1/gifs/search"))
                .withQueryParam("q", equalTo("rich"))
                .withQueryParam("api_key", equalTo(apiKey))
                .willReturn(okJson(json)));

        List<Gif> gifsFalse = gifApi.getGif(true);

        assertEquals(GifTestData.gifIsTrueRich, gifsFalse);
        verify(1, getRequestedFor(urlPathMatching("/v1/gifs/search"))
                .withQueryParam("q", equalTo("rich"))
                .withQueryParam("api_key", equalTo(apiKey)));
    }

}