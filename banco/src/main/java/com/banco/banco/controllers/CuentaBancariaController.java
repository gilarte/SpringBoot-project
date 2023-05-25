package com.banco.banco.controllers;

import java.util.ArrayList;
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
import com.banco.banco.models.Usuario;
import com.banco.banco.services.CuentaBancariaServicel;
import com.banco.banco.services.OperacionBancariaServicel;
import com.banco.banco.services.UsuarioServicel;

@Controller
public class CuentaBancariaController {

	@Autowired
	private CuentaBancariaServicel cuentaBancariaServicel;

	@Autowired
	private UsuarioServicel usuarioServicel;
	
	@SuppressWarnings("unused")
	@Autowired
	private OperacionBancariaServicel operacionBancariaServicel;

	@RequestMapping("/homeCuentaBancariaBancaria")
	@ResponseBody
	public String home() {
		return "hello world";
	}

	@GetMapping("/showCuentasBancariasView")
	public String mostrarCuentasBancarias(Model model) {
		final List<CuentaBancaria> listaCuentasBancarias = cuentaBancariaServicel.obtenerCuentasBancarias();
		model.addAttribute("cuentasBancariasListView", listaCuentasBancarias);

		return "cuentaBancaria";
	}

	@GetMapping("/showCuentaBancariaMod")
	public String mostrarModProyecto(@RequestParam String numeroCuenta, Model model) {
		// Obtención de empleado a modificar
		final CuentaBancaria cuentaBancariaToMod = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);

		// Carga de datos al modelo
		model.addAttribute("cuentaBancariaToMod", cuentaBancariaToMod);

		return "cuentaBancariaModificar";
	}
	

	@GetMapping("/showCuentaBancariaUsuarios")
	public String mostrarCuentasBancariasUsuarios(@RequestParam String numeroCuenta, Model model) {

		// Obtención de proyecto
		final CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		List<Usuario> usuariosElegibles = usuarioServicel.obtenerUsuarios();
		usuariosElegibles.removeAll(cuentaBancaria.getUsuarios());
		
		// Carga de datos al modelo
		model.addAttribute("cuentaBancaria", cuentaBancaria);
		model.addAttribute("usuariosElegibles", usuariosElegibles);

		return "cuentaBancariaIntegrantes";
	}
	
	@PostMapping("/actSearchCuentaBancaria")
	public String submitBuscarEmpleadoForm(@ModelAttribute CuentaBancaria searchedCuentaBancaria, Model model) throws Exception {
		List<CuentaBancaria> listaCuentasBancarias = new ArrayList<>();
		if (searchedCuentaBancaria.getNumeroCuenta().isBlank() && searchedCuentaBancaria.getFechaCreacion() == null) {
			throw new Exception("Debes de indicar (mínimo) un parámetro de búsqueda.");
		} else if (!searchedCuentaBancaria.getNumeroCuenta().isBlank()) {
			// Por DNI, resultado único
			final CuentaBancaria cuentaBancariaEncontrada = cuentaBancariaServicel.obtenerPorNumeroCuenta(searchedCuentaBancaria.getNumeroCuenta());
			listaCuentasBancarias.add(cuentaBancariaEncontrada);
			model.addAttribute("cuentasBancariasListView", listaCuentasBancarias);
			return "cuentaBancaria";
		} else if (searchedCuentaBancaria.getFechaCreacion() != null) {
			// Por nombre y apellidos
			listaCuentasBancarias = cuentaBancariaServicel.buscarPorFechasCreacion(searchedCuentaBancaria.getFechaCreacion());
		} 
		model.addAttribute("cuentasBancariasListView", listaCuentasBancarias);
		return "cuentaBancaria";
	}

	@PostMapping("/actAddCuentaBancaria")
	private String insertarProyecto(@Valid @ModelAttribute CuentaBancaria newCuentaBancaria, BindingResult result)
			throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de proyecto erróneos");
		} else {

			// Se añade el nuevo proyecto
			cuentaBancariaServicel.insertarCuentaBancaria(newCuentaBancaria);
		}
		return "redirect:showCuentasBancariasView";
	}

	@GetMapping("/actDropCuentaBancaria")
	public String eliminarCuentaBancaria(@RequestParam String numeroCuenta, Model model) {
		cuentaBancariaServicel.eliminarCunetaBancaria(cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta));
		return "redirect:showCuentasBancariasView";
	}

	@PostMapping("/actModCuentaBancaria")
	private String modificarProyecto(@Valid @ModelAttribute CuentaBancaria newCuentaBancaria, BindingResult result)
			throws Exception {
		CuentaBancaria cuentaBancariaToMod = cuentaBancariaServicel.obtenerPorNumeroCuenta(newCuentaBancaria.getNumeroCuenta());
		cuentaBancariaToMod.setSaldo(newCuentaBancaria.getSaldo());
		cuentaBancariaToMod.setFechaCreacion(newCuentaBancaria.getFechaCreacion());
		if (result.hasErrors()) {
			throw new Exception("Parámetros de cuenta bancaria erróneos");
		} else {
			cuentaBancariaServicel.modificarCuentaBancaria(newCuentaBancaria);
		}
		return "redirect:showCuentasBancariasView";
	}

	@GetMapping("/actDropUsuarioCuentaBancaria")
	public String eliminarUsuarioDeCuentaBancaria(@RequestParam String numeroCuenta, @RequestParam String usuarioNIF,
			Model model) {
		CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		Usuario usuarioAEliminar = usuarioServicel.obtenerPorNIF(usuarioNIF);
		cuentaBancaria.getUsuarios().remove(usuarioAEliminar);

		// Se actualiza el empleado ahora sin el proyecto seleccionado
		cuentaBancariaServicel.modificarCuentaBancaria(cuentaBancaria);

		return "redirect:showCuentaBancariaUsuarios?numeroCuenta=" + cuentaBancaria.getNumeroCuenta();
	}

	@GetMapping("/actAddUsuarioCuentaBancaria")
	public String insertarUsuarioDeCuentaBancaria(@RequestParam String numeroCuenta, @RequestParam String usuarioNIF, Model model) {
		CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		Usuario usuarioAInsertar = usuarioServicel.obtenerPorNIF(usuarioNIF);
		System.out.println(usuarioAInsertar.getNIF());
		cuentaBancaria.getUsuarios().add(usuarioAInsertar);

		// Se actualiza el proyecto ahora con el empleado seleccionado
		cuentaBancariaServicel.modificarCuentaBancaria(cuentaBancaria);

		return "redirect:showCuentaBancariaUsuarios?numeroCuenta=" + cuentaBancaria.getNumeroCuenta();
	}
	
	
	
	
}
