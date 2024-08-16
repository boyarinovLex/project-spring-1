package com.javarush.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {

    @GetMapping
    public String showHomePage() {
        return "redirect:/tasks/page/1";
    }
}
