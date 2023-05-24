package com.banco.banco.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.banco.banco.models.CuentaBancaria;
import com.banco.banco.models.OperacionBancaria;
import com.banco.banco.models.OperacionBancaria.TipoOperacion;
import com.banco.banco.services.CuentaBancariaServicel;
import com.banco.banco.services.OperacionBancariaServicel;
import com.banco.banco.services.UsuarioServicel;

@Controller
public class OperacionBancariaController {
	
	@Autowired
	private	OperacionBancariaServicel operacionBancariaServicel;
	
	@Autowired
	private	CuentaBancariaServicel cuentaBancariaServicel;
	
	@Autowired
	private	UsuarioServicel usuarioServicel;
	
	@RequestMapping("/homeOperacionBancaria")
	@ResponseBody
	public String home() {
		return "hello world";
	}
	
	@GetMapping("/showCuentasBancariasOperacionesView")
	public String mostrarCuentasBancarias(Model model) {
		final List<CuentaBancaria> listaCuentasBancarias = cuentaBancariaServicel.obtenerCuentasBancarias();
		model.addAttribute("cuentasBancariasListView", listaCuentasBancarias);

		return "operacionBancaria";
	}
	
	@GetMapping("/showOperacionBancariaCuentasBancarias")
	public String mostrarProyectoEmpleados(@RequestParam String numeroCuenta, Model model) {
		final CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		
		List<OperacionBancaria> operacionesBancarias = operacionBancariaServicel.obtenerOperacionesBancarias();
		operacionesBancarias.removeAll(cuentaBancaria.getOperacionesCuentaBancaria());
		
		// Carga de datos al modelo
		model.addAttribute("cuentaBancaria", cuentaBancaria);
		model.addAttribute("listaOperaciones", operacionesBancarias);

		return "cuentaBancariaOperacionBancaria";
	}
	
	
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