package com.banco.banco.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
	/**
	 * Mapea la ruta /dashboard y devuelve la vista correspondiente
	 * @return
	 */
    @GetMapping("/dashboard")
    public String LoginOk() {
        return "dashboard";
    }
    
    @GetMapping("/secure")
    public String admin() {
        return "secure";
    }
}
