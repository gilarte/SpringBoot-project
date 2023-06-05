package com.banco.banco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banco.banco.models.CuentaBancaria;
import com.banco.banco.models.OperacionBancaria;
import com.banco.banco.models.Usuario;
import com.banco.banco.services.CuentaBancariaServicel;
import com.banco.banco.services.UsuarioServicel;


/**
 * Controlador de las vistas
 *
 */
@Controller
@RequestMapping("*")
public class SystemController {
	
	@Autowired
	private CuentaBancariaServicel cuentaBancariaServicel;
	
	@Autowired
	private	UsuarioServicel usuarioServicel;

	/**
	 * Redireccionamiento principal
	 * 
	 * @return index.html
	 */
	@GetMapping
	public String showIndex() {
		return "index";
	}

	// Listado
	@GetMapping("/usuariosView")
	public String redirectToUsuarioController() {
		return "redirect:showUsuariosView";
	}

	// Insertar
	@GetMapping("/newUsuariosView")
	public String redirectToNewUsuarioTemplate() {
		return "usuarioInsertar";
	}

	// Listado
	@GetMapping("/cuentasBancariasView")
	public String redirectToCuentaBancariaController() {
		return "redirect:showCuentasBancariasView";
	}

	// Insertar
	@GetMapping("/newCuentaBancariaView")
	public String redirectToNewCuentaBancariaTemplate() {
		return "cuentaBancariaInsertar";
	}
	
	// Insertar
	@GetMapping("/newOperacionBancariaInsert")
	public String redirectToNewOperacionBancariaTemplate() {
		return "operacionBancariaInsertar";
	}

	// Listado
	@GetMapping("/operacionesBancariasView")
	public String redirectToOperacionancariaController() {
		return "redirect:showCuentasBancariasOperacionesView";
	}
	
	// Insertar
		@SuppressWarnings("static-access")
		@GetMapping("/newOperacionBancariaView")
		public String redirectToNewOperacionBancariaTemplate(@RequestParam String numeroCuenta, Model model) {
			final CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
			
			List<Usuario> usuariosElegibles = usuarioServicel.obtenerUsuarios();
			usuariosElegibles.removeAll(cuentaBancaria.getUsuarios());
			
			OperacionBancaria operacionBancaria = new OperacionBancaria();
			
			model.addAttribute("listaUsuarios", cuentaBancaria.getUsuarios());
			model.addAttribute("valores", operacionBancaria.getTipoOperacion().values());
			model.addAttribute("numeroCuenta", cuentaBancaria.getNumeroCuenta());
			return "operacionBancariaInsertar";
		}

}
