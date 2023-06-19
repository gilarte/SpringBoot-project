package com.banco.banco.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(RegistroController.class);


	/**
	 * Mapea la ruta /login y devuelve la vista correspondiente
	 * @return
	 */
    @GetMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required = false) String logout,
			Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal != null) {
			flash.addFlashAttribute("info", "Ya ha inciado sesión anteriormente");
			return "redirect:/";
		}
		
		if(error != null) {
			model.addAttribute("error", "Error en el login: Nombre de usuario o contraseña incorrecta, por favor vuelva a intentarlo!");
		}
		
		if(logout != null) {
			model.addAttribute("success", "Ha cerrado sesión con éxito!");
		}
        return "login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam("username") String NIF,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {
    	logger.info("HA ENTRADO EN EL METODO");
        // Aquí debes implementar la lógica de autenticación del usuario
        // Puedes utilizar Spring Security, autenticar manualmente o cualquier otra opción

        if (NIF.equals("53911960Y") && password.equals("admin123")) {
            // Las credenciales son válidas, redirige al usuario a una página segura
            return "redirect:/secure";
        } else {
            // Las credenciales son inválidas, muestra un mensaje de error y redirige al formulario de inicio de sesión
            redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
            return "redirect:/";
        }
    }


}
