package com.banco.banco.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Clase para controlar las excepciones.
 *
 */
@ControllerAdvice
public class ErrorController {
	/**
	 * @param req HttpServletRequest
	 * @param e excepción que ha saltado.
	 * @param model modelo de la aplicación.
	 * @return error.html
	 */
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest req, Exception e, Model model) {
		// Respuesta
		model.addAttribute("errorMsg", e.getMessage());
		return "error";
	}

}
