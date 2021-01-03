package com.testtask.exchangerate.controller;

import com.testtask.exchangerate.service.GifService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@AllArgsConstructor
public class GifController {

    private final GifService gifService;

    @PostMapping("/gif")
    public String getGif(@RequestParam String code, Model model) {
        String gifUrl = gifService.getGif(code).getOriginal().getUrl();
        model.addAttribute("gifUrl", gifUrl);
        log.info("ExchangeController getGif code - {}, gifUrl-{}", code, gifUrl);
        return "gif";
    }
}
