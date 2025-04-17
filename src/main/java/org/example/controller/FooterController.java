package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterController {
    @GetMapping("/privacy")
    public String privacy(){
        return "privacy";
    }

    @GetMapping("/about_us")
    public String about_us(){
        return "about_us";
    }

    @GetMapping("/terms")
    public String terms(){
        return "terms";
    }

    @GetMapping("/faq")
    public String faq(){
        return "faq";
    }

}
