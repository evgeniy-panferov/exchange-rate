package com.testtask.exchangerate.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(value = {"classpath:application-test.properties"})
@AutoConfigureWireMock(port = 0)
class GifControllerTest {

    @Value("${feign.openexchangerates.url}")
    String url;

    @Test
    void getGif() throws IOException {

        stubFor(post(urlEqualTo("/gif"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withStatus(200)
                        .withBody("Gif")));

        Response post = RestAssured.given()
                .contentType("text/html")
                .post(url + "/gif");

        assertEquals("Gif", post.getBody().asString());
    }

}