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

	/**
	 * Redirecciona al controlador de usuarios.
	 * @return Redirige a la vista "showUsuariosView" del controlador de usuarios.
	 */	@GetMapping("/usuariosView")
	public String redirectToUsuarioController() {
		return "redirect:showUsuariosView";
	}

	 /**
	 * Redirecciona a la plantilla de inserción de usuario.
	 * @return La vista "usuarioInsertar" para insertar un nuevo usuario.
	 */	@GetMapping("/newUsuariosView")
	public String redirectToNewUsuarioTemplate() {
		return "usuarioInsertar";
	}

	 /**
	 * Redirecciona al controlador de cuentas bancarias.
	 * @return Redirige a la vista "showCuentasBancariasView" del controlador de cuentas bancarias.
	 */
	 @GetMapping("/cuentasBancariasView")
	public String redirectToCuentaBancariaController() {
		return "redirect:showCuentasBancariasView";
	}

	 /**
	 * Redirecciona a la plantilla de inserción de cuenta bancaria.
	 * @return La vista "cuentaBancariaInsertar" para insertar una nueva cuenta bancaria.
	 */	@GetMapping("/newCuentaBancariaView")
	public String redirectToNewCuentaBancariaTemplate() {
		return "cuentaBancariaInsertar";
	}
	
	 /**
	 * Redirecciona a la plantilla de inserción de operación bancaria.
	 * @return La vista "operacionBancariaInsertar" para insertar una nueva operación bancaria.
	 */	@GetMapping("/newOperacionBancariaInsert")
	public String redirectToNewOperacionBancariaTemplate() {
		return "operacionBancariaInsertar";
	}

	 /**
	 * Redirecciona al controlador de operaciones bancarias.
	 * @return Redirige a la vista "showCuentasBancariasOperacionesView" del controlador de operaciones bancarias.
	 */	@GetMapping("/operacionesBancariasView")
	public String redirectToOperacionancariaController() {
		return "redirect:showCuentasBancariasOperacionesView";
	}

	 	/**
		 * Redirecciona a la plantilla de inserción de operación bancaria.
		 * @param numeroCuenta El número de cuenta bancaria asociada a la operación.
		 * @param model El modelo de la vista.
		 * @return La vista "operacionBancariaInsertar" para insertar una nueva operación bancaria.
		 */
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
