package com.example.Concurrente2;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        // Redirige cualquier ruta no encontrada a index.html
        return "forward:/index.html";
    }
}

