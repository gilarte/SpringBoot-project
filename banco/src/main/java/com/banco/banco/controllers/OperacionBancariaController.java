package com.banco.banco.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.banco.banco.models.CuentaBancaria;
import com.banco.banco.models.OperacionBancaria;
import com.banco.banco.models.OperacionBancaria.TipoOperacion;
import com.banco.banco.services.CuentaBancariaServicel;
import com.banco.banco.services.JpaUserDetailsService;
import com.banco.banco.services.OperacionBancariaServicel;
import com.banco.banco.services.UsuarioServicel;

@Controller
public class OperacionBancariaController {
	
	@Autowired
	private	OperacionBancariaServicel operacionBancariaServicel;
	
	@Autowired
	private	CuentaBancariaServicel cuentaBancariaServicel;
	
	@SuppressWarnings("unused")
	@Autowired
	private	UsuarioServicel usuarioServicel;
	
	/**
	 * Controlador de la página principal de operaciones bancarias.
	 * @return "hello world" como respuesta en formato texto.
	 */
	@RequestMapping("/homeOperacionBancaria")
	@ResponseBody
	public String home() {
		return "hello world";
	}
	
	/**
	 * Muestra la vista de cuentas bancarias con operaciones.
	 * @param model El modelo de la vista.
	 * @return La vista "operacionBancaria" con la lista de cuentas bancarias.
	 */
	@GetMapping("/showCuentasBancariasOperacionesView")
	public String mostrarCuentasBancarias(Model model) {
		final List<CuentaBancaria> listaCuentasBancarias = cuentaBancariaServicel.findAllByUsuariosNIF(JpaUserDetailsService.AuthNif);
		model.addAttribute("cuentasBancariasListView", listaCuentasBancarias);

		return "operacionBancaria";
	}
	
	/**
	 * Muestra la vista de operaciones bancarias para una cuenta bancaria específica.
	 * @param numeroCuenta El número de cuenta de la cuenta bancaria.
	 * @param model El modelo de la vista.
	 * @return La vista "cuentaBancariaOperacionBancaria" con los detalles de la cuenta bancaria y sus operaciones.
	 */
	@GetMapping("/showOperacionBancariaCuentasBancarias")
	public String mostrarProyectoEmpleados(@RequestParam String numeroCuenta, Model model) {
		final CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		
		List<OperacionBancaria> operacionesBancarias = operacionBancariaServicel.obtenerPorNumeroCuentas(numeroCuenta);
		
		// Carga de datos al modelo
		model.addAttribute("cuentaBancaria", cuentaBancaria);
		model.addAttribute("listaOperaciones", operacionesBancarias);

		return "cuentaBancariaOperacionBancaria";
	}
	
	
	/**
	 * Procesa el formulario de inserción de una nueva operación bancaria.
	 * @param newOperacionBancaria La nueva operación bancaria a insertar.
	 * @param usuarioNIF El NIF del usuario que realiza la operación.
	 * @param result Los resultados de la validación del formulario.
	 * @return Redirige a la vista "showCuentasBancariasOperacionesView" después de realizar la inserción.
	 * @throws Exception Si hay errores en los parámetros de la operación bancaria.
	 */
	@PostMapping("/actAddOperacionBancaria")
	private String insertarProyecto(@Valid @ModelAttribute OperacionBancaria newOperacionBancaria, @RequestParam("usuario") String usuarioNIF, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de la operacion bancaria erróneos");
		} else {
			if(newOperacionBancaria.getTipoOperacion() == TipoOperacion.Ingresar) {
				CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(newOperacionBancaria.getNumeroCuentaInvolucrada());
				cuentaBancaria.setSaldo(cuentaBancaria.getSaldo()+ newOperacionBancaria.getCantidadInvolucrada());
				operacionBancariaServicel.insertarOperacionBancaria(newOperacionBancaria);
			}
			
			if(newOperacionBancaria.getTipoOperacion() == TipoOperacion.Retirar) {
				CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(newOperacionBancaria.getNumeroCuentaInvolucrada());
				cuentaBancaria.setSaldo(cuentaBancaria.getSaldo() - newOperacionBancaria.getCantidadInvolucrada());
				operacionBancariaServicel.insertarOperacionBancaria(newOperacionBancaria);
			}
		}
		return "redirect:showCuentasBancariasOperacionesView";
	}
}
