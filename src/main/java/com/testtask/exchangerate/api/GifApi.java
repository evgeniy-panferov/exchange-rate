package com.testtask.exchangerate.api;

import com.testtask.exchangerate.client.GifClient;
import com.testtask.exchangerate.model.Gif;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GifApi {

    private Environment environment;

    private final GifClient gifClient;

    public List<Gif> getGif(boolean isUp) {
        String apiKey = environment.getProperty("giphy.api_key");
        String q = isUp ? environment.getProperty("giphy.q.rich") : environment.getProperty("giphy.q.broke");
        log.info("GifService getGif apiKey - {}, q - {}", apiKey, q);
        return gifClient.getGif(apiKey, q).getData();
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
