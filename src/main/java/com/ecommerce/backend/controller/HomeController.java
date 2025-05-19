package com.ecommerce.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirigirAPrincipal() {
        return "redirect:/html/principal.html";
    }
}
