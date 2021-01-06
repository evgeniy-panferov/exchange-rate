package com.testtask.exchangerate.controller;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
@TestPropertySource(value = {"classpath:application-test.properties"})
class RedirectControllerTest {

    @Value("${feign.openexchangerates.url}")
    String url;

    private static final Logger log = LoggerFactory.getLogger(RedirectControllerTest.class);

    @Test
    void forward() {
        stubFor(get(urlMatching("/.*"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withStatus(200)
                        .withBody("main controller")));

        Response post = RestAssured.given()
                .contentType("text/html")
                .get(url + "/abc");

        assertEquals("main controller", post.getBody().asString());
    }
}