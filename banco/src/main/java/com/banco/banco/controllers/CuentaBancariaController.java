package com.banco.banco.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.banco.banco.services.JpaUserDetailsService;
import com.banco.banco.services.OperacionBancariaServicel;
import com.banco.banco.services.UsuarioServicel;

/**
 * Controlador que maneja las operaciones relacionadas con las cuentas bancarias.
 */
@Controller
public class CuentaBancariaController {
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);	

	@Autowired
	private CuentaBancariaServicel cuentaBancariaServicel;

	@Autowired
	private UsuarioServicel usuarioServicel;
	
	
	
	@SuppressWarnings("unused")
	@Autowired
	private OperacionBancariaServicel operacionBancariaServicel;

	/**
	 * Página de inicio del controlador de cuentas bancarias.
	 * @return Un mensaje de saludo en formato JSON.
	 */
	@RequestMapping("/homeCuentaBancariaBancaria")
	@ResponseBody
	public String home() {
		return "hello world";
	}

	/**
	 * Muestra la vista con la lista de cuentas bancarias.
	 * @param model El modelo de la vista.
	 * @return La vista "cuentaBancaria" con la lista de cuentas bancarias.
	 */
	@GetMapping("/showCuentasBancariasView")
	public String mostrarCuentasBancarias(Model model) {
		
		final List<CuentaBancaria> listaCuentasBancarias = cuentaBancariaServicel.findAllByUsuariosNIF(JpaUserDetailsService.AuthNif);
		model.addAttribute("cuentasBancariasListView", listaCuentasBancarias);
		logger.info("Este es el nif logueado: "+JpaUserDetailsService.AuthNif);

		return "cuentaBancaria";
	}
	@GetMapping("/showCuentasBancariasViewAdmin")
	public String mostrarCuentasBancariasAdmin(Model model) {
		
		final List<CuentaBancaria> listaCuentasBancarias = cuentaBancariaServicel.obtenerCuentasBancarias();
		model.addAttribute("cuentasBancariasListView", listaCuentasBancarias);
		logger.info("Este es el nif logueado: "+JpaUserDetailsService.AuthNif);

		return "secureCuentas";
	}

	/**
	 * Muestra la vista de modificación de una cuenta bancaria.
	 * @param numeroCuenta El número de cuenta de la cuenta bancaria a modificar.
	 * @param model El modelo de la vista.
	 * @return La vista "cuentaBancariaModificar" con los datos de la cuenta bancaria a modificar.
	 */
	@GetMapping("/showCuentaBancariaMod")
	public String mostrarModProyecto(@RequestParam String numeroCuenta, Model model) {
		// Obtención de empleado a modificar
		final CuentaBancaria cuentaBancariaToMod = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);

		// Carga de datos al modelo
		model.addAttribute("cuentaBancariaToMod", cuentaBancariaToMod);

		return "cuentaBancariaModificar";
	}
	@GetMapping("/showCuentaBancariaModAdmin")
	public String mostrarModProyectoAdmin(@RequestParam String numeroCuenta, Model model) {
		// Obtención de empleado a modificar
		final CuentaBancaria cuentaBancariaToMod = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);

		// Carga de datos al modelo
		model.addAttribute("cuentaBancariaToMod", cuentaBancariaToMod);

		return "secureCuentasModificar";
	}
	

	/**
	 * Muestra la vista de cuentas bancarias relacionadas con un usuario.
	 * @param numeroCuenta El número de cuenta de la cuenta bancaria.
	 * @param model El modelo de la vista.
	 * @return La vista "cuentaBancariaIntegrantes" con los datos de la cuenta bancaria y los usuarios relacionados.
	 */
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
	@GetMapping("/showCuentaBancariaUsuariosAdmin")
	public String mostrarCuentasBancariasUsuariosAdmin(@RequestParam String numeroCuenta, Model model) {

		// Obtención de proyecto
		final CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		List<Usuario> usuariosElegibles = usuarioServicel.obtenerUsuarios();
		usuariosElegibles.removeAll(cuentaBancaria.getUsuarios());
		
		// Carga de datos al modelo
		model.addAttribute("cuentaBancaria", cuentaBancaria);
		model.addAttribute("usuariosElegibles", usuariosElegibles);

		return "secureCuentasIntegrantes";
	}
	
	/**
	 * Procesa el formulario de búsqueda de cuentas bancarias.
	 * @param searchedCuentaBancaria La cuenta bancaria a buscar.
	 * @param model El modelo de la vista.
	 * @return La vista "cuentaBancaria" con los resultados de la búsqueda.
	 * @throws Exception Si los parámetros de búsqueda son incorrectos.
	 */
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

	/**
	 * Procesa el formulario de creación de una cuenta bancaria.
	 * @param newCuentaBancaria La nueva cuenta bancaria a insertar.
	 * @param result El resultado del proceso de validación.
	 * @return Redirecciona a la vista "showCuentasBancariasView".
	 * @throws Exception Si los parámetros de la cuenta bancaria son incorrectos.
	 */
	@PostMapping("/actAddCuentaBancaria")
	private String insertarProyecto(@Valid @ModelAttribute CuentaBancaria newCuentaBancaria, BindingResult result)
			throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de proyecto erróneos");
		} else {
			//añadimos a esa nueva cuenta el usuario que la ha creado
			List<Usuario> aux = new ArrayList<>();
			aux.add(usuarioServicel.obtenerPorNIF(JpaUserDetailsService.AuthNif));
			newCuentaBancaria.setUsuarios(aux);

			// Se añade la nueva cuenta
			cuentaBancariaServicel.insertarCuentaBancaria(newCuentaBancaria);
		}
		return "redirect:showCuentasBancariasView";
	}
	@PostMapping("/actAddCuentaBancariaAdmin")
	private String insertarProyectoAdmin(@Valid @ModelAttribute CuentaBancaria newCuentaBancaria, BindingResult result)
			throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de proyecto erróneos");
		} else {
			//añadimos a esa nueva cuenta el usuario que la ha creado
			List<Usuario> aux = new ArrayList<>();
			aux.add(usuarioServicel.obtenerPorNIF(JpaUserDetailsService.AuthNif));
			newCuentaBancaria.setUsuarios(aux);

			// Se añade la nueva cuenta
			cuentaBancariaServicel.insertarCuentaBancaria(newCuentaBancaria);
		}
		return "redirect:showCuentasBancariasViewAdmin";
	}

	/**
	 * Elimina una cuenta bancaria.
	 * @param numeroCuenta El número de cuenta de la cuenta bancaria a eliminar.
	 * @param model El modelo de la vista.
	 * @return Redirecciona a la vista "showCuentasBancariasView".
	 */
	@GetMapping("/actDropCuentaBancaria")
	public String eliminarCuentaBancaria(@RequestParam String numeroCuenta, Model model) {
		cuentaBancariaServicel.eliminarCunetaBancaria(cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta));
		return "redirect:showCuentasBancariasView";
	}
	@GetMapping("/actDropCuentaBancariaAdmin")
	public String eliminarCuentaBancariaAdmin(@RequestParam String numeroCuenta, Model model) {
		cuentaBancariaServicel.eliminarCunetaBancaria(cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta));
		return "redirect:showCuentasBancariasViewAdmin";
	}

	/**
	 * Procesa el formulario de modificación de una cuenta bancaria.
	 * @param newCuentaBancaria La cuenta bancaria modificada.
	 * @param result El resultado del proceso de validación.
	 * @return Redirecciona a la vista "showCuentasBancariasView".
	 * @throws Exception Si los parámetros de la cuenta bancaria son incorrectos.
	 */
	@PostMapping("/actModCuentaBancaria")
	private String modificarProyecto(@Valid @ModelAttribute CuentaBancaria newCuentaBancaria, BindingResult result)
			throws Exception {
		CuentaBancaria cuentaBancariaToMod = cuentaBancariaServicel.obtenerPorNumeroCuenta(newCuentaBancaria.getNumeroCuenta());
		cuentaBancariaToMod.setSaldo(newCuentaBancaria.getSaldo());
		cuentaBancariaToMod.setFechaCreacion(newCuentaBancaria.getFechaCreacion());
		newCuentaBancaria.setUsuarios(cuentaBancariaToMod.getUsuarios());
		if (result.hasErrors()) {
			throw new Exception("Parámetros de cuenta bancaria erróneos");
		} else {
			cuentaBancariaServicel.modificarCuentaBancaria(newCuentaBancaria);
		}
		return "redirect:showCuentasBancariasView";
	}

	/**
	 * Procesa el formulario de modificación de una cuenta bancaria.
	 * @param newCuentaBancaria La cuenta bancaria modificada.
	 * @param result El resultado del proceso de validación.
	 * @return Redirecciona a la vista "showCuentasBancariasView".
	 * @throws Exception Si los parámetros de la cuenta bancaria son incorrectos.
	 */
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

	/**
	 * Agrega un usuario a una cuenta bancaria.
	 * @param numeroCuenta El número de cuenta de la cuenta bancaria.
	 * @param usuarioNIF El NIF del usuario a agregar.
	 * @param model El modelo de la vista.
	 * @return Redirecciona a la vista "showCuentaBancariaUsuarios" con los datos actualizados.
	 */
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
