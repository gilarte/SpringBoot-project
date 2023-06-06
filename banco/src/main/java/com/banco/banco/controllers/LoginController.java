package com.banco.banco.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	/**
	 * Mapea la ruta /login y devuelve la vista correspondiente
	 * @return
	 */
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

}
