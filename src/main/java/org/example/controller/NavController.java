package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

    @GetMapping("/")
    public String index (){
        return "index";
    }

    @GetMapping("/donate")
    public String donate(){
        return "donate";
    }
}
