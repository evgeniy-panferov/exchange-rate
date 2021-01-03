package com.testtask.exchangerate.client;

import com.testtask.exchangerate.model.dto.GifDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign.giphy.name}", url = "${feign.giphy.url}")
public interface GifClient {

    @GetMapping(value = "${giphy.search}")
    GifDto getGif(@RequestParam(name = "api_key") String apiKey, @RequestParam String q);
}
