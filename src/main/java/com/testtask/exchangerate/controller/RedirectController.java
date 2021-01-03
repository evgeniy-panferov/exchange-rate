package com.testtask.exchangerate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
    @RequestMapping("/{path:[^\\.]+}")
    public String forward() {
        return "forward:/";
    }
}
