package com.banco.banco.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.banco.banco.models.Usuario;
import com.banco.banco.services.CuentaBancariaServicel;
import com.banco.banco.services.OperacionBancariaServicel;
import com.banco.banco.services.UsuarioServicel;


@Controller
public class UsuarioController {
	
	@GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }
	
	@GetMapping("/user")
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            // El usuario ha sido autenticado correctamente
            return "Usuario autenticado: " + userDetails.getUsername();
        } else {
            // El usuario no ha sido autenticado
            return "Usuario no autenticado";
        }
    }

	/*
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
*/

	@Autowired
	private UsuarioServicel usuarioServiceI;

	@Autowired
	private CuentaBancariaServicel cuentaBancariaServicel;
	
	@SuppressWarnings("unused")
	@Autowired
	private OperacionBancariaServicel operacionBancariaServicel;

	/**
	 * Muestra la lista de usuarios.
	 * @param model El modelo de la vista.
	 * @return La vista "usuario" con la lista de usuarios.
	 */
	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "hello world";
	}

	/**
	 * Muestra la lista de usuarios.
	 * @param model El modelo de la vista.
	 * @return La vista "usuario" con la lista de usuarios.
	 */
	@GetMapping("/showUsuariosView")
	public String mostrarUsuarios(Model model) {
		final List<Usuario> listaUsuarios = usuarioServiceI.obtenerUsuarios();
		model.addAttribute("usuariosListView", listaUsuarios);

		return "usuario";
	}

	/**
	 * Muestra la vista para modificar un usuario específico.
	 * @param usuarioNif El NIF del usuario a modificar.
	 * @param model El modelo de la vista.
	 * @return La vista "usuarioModificar" con los datos del usuario a modificar.
	 */
	@GetMapping("/showUsuarioMod")
	public String mostrarModUsuario(@RequestParam String usuarioNif, Model model) {

		// Obtención de empleado a modificar
		final Usuario usuarioToMod = usuarioServiceI.obtenerPorNIF(usuarioNif);

		// Carga de datos al modelo
		model.addAttribute("usuarioToMod", usuarioToMod);

		return "usuarioModificar";
	}

	/**
	 * Muestra la vista de cuentas bancarias de un usuario específico.
	 * @param usuarioNif El NIF del usuario.
	 * @param model El modelo de la vista.
	 * @return La vista "usuarioCuentasBancarias" con las cuentas bancarias del usuario.
	 */
	@GetMapping("/showUsuarioCuentasBancarias")
	public String mostrarUsuarioCuentasBancarias(@RequestParam String usuarioNif, Model model) {

		// Obtención de empleado a modificar
		final Usuario usuario = usuarioServiceI.obtenerPorNIF(usuarioNif);
		List<CuentaBancaria> cuentasBancariasElegibles = cuentaBancariaServicel.obtenerCuentasBancarias();
		cuentasBancariasElegibles.removeAll(usuario.getCuentasBancarias());

		// Carga de datos al modelo
		model.addAttribute("usuario", usuario);
		model.addAttribute("cuentasBancariasElegibles", cuentasBancariasElegibles);

		return "usuarioCuentasBancarias";
	}

	/**
	 * Procesa el formulario de búsqueda de usuarios.
	 * @param searchedUsuario El usuario a buscar.
	 * @param model El modelo de la vista.
	 * @return La vista "usuario" con la lista de usuarios encontrados.
	 * @throws Exception Si no se proporciona al menos un parámetro de búsqueda.
	 */
	@PostMapping("/actSearchUsuario")
	public String submitBuscarEmpleadoForm(@ModelAttribute Usuario searchedUsuario, Model model) throws Exception {
		List<Usuario> listaUsuarios = new ArrayList<>();
		if (searchedUsuario.getNIF().isBlank() && searchedUsuario.getNombre().isBlank() && searchedUsuario.getApellidos().isBlank()) {
			throw new Exception("Debes de indicar (mínimo) un parámetro de búsqueda.");
		} else if (!searchedUsuario.getNIF().isBlank()) {
			// Por DNI, resultado único
			final Usuario usuarioEncontrado = usuarioServiceI.obtenerPorNIF(searchedUsuario.getNIF());
			listaUsuarios.add(usuarioEncontrado);
			model.addAttribute("usuariosListView", listaUsuarios);
			return "usuario";
		} else if (!searchedUsuario.getNombre().isBlank() && !searchedUsuario.getApellidos().isBlank()) {
			// Por nombre y apellidos
			listaUsuarios = usuarioServiceI.obtenerNombreYApellidos(searchedUsuario.getNombre(), searchedUsuario.getApellidos());
		} else if (!searchedUsuario.getNombre().isBlank()) {
			listaUsuarios = usuarioServiceI.obtenerPorNombre(searchedUsuario.getNombre());
		} else if (!searchedUsuario.getApellidos().isBlank()) {
			listaUsuarios = usuarioServiceI.obtenerPorApellidos(searchedUsuario.getApellidos());
		}
		model.addAttribute("usuariosListView", listaUsuarios);
		return "usuario";
	}

	/**
	 * Procesa el formulario para insertar un nuevo usuario.
	 * @param newUsuario El nuevo usuario a insertar.
	 * @param result El resultado de la validación.
	 * @return La vista "redirect:showUsuariosView" si el formulario se procesa correctamente.
	 * @throws Exception Si hay errores de validación en los parámetros del usuario.
	 */
	@PostMapping("/actAddUsuario")
	private String insertarUsuario(@Valid @ModelAttribute Usuario newUsuario, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			throw new Exception("Parámetros de usuario erróneos");
		} else {
			// Se añade el nuevo empleado
			usuarioServiceI.insertarUsuario(newUsuario);
		}

		return "redirect:showUsuariosView";
	}

	/**
	 * Procesa el formulario para modificar un usuario existente.
	 * @param newUsuario El usuario modificado.
	 * @param result El resultado de la validación.
	 * @return La vista "redirect:showUsuariosView" si el formulario se procesa correctamente.
	 * @throws Exception Si hay errores de validación en los parámetros del usuario o si no se encuentra el usuario a modificar.
	 */
	@PostMapping("/actModUsuario")
	private String modificarUsuario(@Valid @ModelAttribute Usuario newUsuario, BindingResult result) throws Exception {
		Usuario usuarioToMod = usuarioServiceI.obtenerPorNIF(newUsuario.getNIF());
		
		if (usuarioToMod == null) {
		    throw new Exception("No se ha encontrado un usuario con el NIF proporcionado");
		}
		
		usuarioToMod.setNombre(newUsuario.getNombre());
		usuarioToMod.setApellidos(newUsuario.getApellidos());
		usuarioToMod.setDireccion(newUsuario.getDireccion());
		usuarioToMod.setEmail(newUsuario.getEmail());
		usuarioToMod.setTelefono(newUsuario.getTelefono());
		usuarioToMod.setAnyoNacimiento(newUsuario.getAnyoNacimiento());

		if (result.hasErrors()) {
			throw new Exception("Parámetros de usuario erróneos");
		} else {
			usuarioServiceI.modificarUsaurio(usuarioToMod);
		}

		return "redirect:showUsuariosView";
	}

	/**
	 * Elimina un usuario existente.
	 * @param usuarioNif El NIF del usuario a eliminar.
	 * @param model El modelo.
	 * @return La vista "redirect:showUsuariosView".
	 */
	@GetMapping("/actDropUsuario")
	public String eliminarUsuario(@RequestParam String usuarioNif, Model model) {

		// Eliminación de empleado
		usuarioServiceI.eliminarUsuario(usuarioServiceI.obtenerPorNIF(usuarioNif));

		return "redirect:showUsuariosView";

	}
	
	/**
	 * Elimina una cuenta bancaria de un usuario.
	 * @param usuarioNIF El NIF del usuario.
	 * @param numeroCuenta El número de cuenta bancaria.
	 * @param model El modelo.
	 * @return La vista "redirect:showUsuarioCuentasBancarias?usuarioNif=" + usuario.getNIF().
	 */
	@GetMapping("/actDropCuentaBancariaUsuario")
	public String eliminarProyectoDeEmpleado(@RequestParam String usuarioNIF, @RequestParam String numeroCuenta, Model model) {
		Usuario usuario = usuarioServiceI.obtenerPorNIF(usuarioNIF);
		CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		usuario.getCuentasBancarias().remove(cuentaBancaria);
		usuarioServiceI.modificarUsaurio(usuario);

		return "redirect:showUsuarioCuentasBancarias?usuarioNif=" + usuario.getNIF();
	}

	/**
	 * Agrega una cuenta bancaria a un usuario.
	 * @param usuarioNif El NIF del usuario.
	 * @param numeroCuenta El número de cuenta bancaria.
	 * @param model El modelo.
	 * @return La vista "redirect:showUsuarioCuentasBancarias?usuarioNif=" + usuario.getNIF().
	 */
	@GetMapping("/actAddCuentaBancariaUsuario")
	public String insertarProyectoDeEmpleado(@RequestParam String usuarioNif, @RequestParam String numeroCuenta,
			Model model) {
		Usuario usuario = usuarioServiceI.obtenerPorNIF(usuarioNif);
		CuentaBancaria cuentaBancariaAInsertar = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		usuario.getCuentasBancarias().add(cuentaBancariaAInsertar);

		// Se actualiza el empleado ahora con el proyecto seleccionado
		usuarioServiceI.modificarUsaurio(usuario);

		return "redirect:showUsuarioCuentasBancarias?usuarioNif=" + usuario.getNIF();
	}
	
	/**
	 * Muestra las operaciones bancarias asociadas a una cuenta bancaria.
	 * @param numeroCuenta El número de cuenta bancaria.
	 * @param model El modelo.
	 * @return La vista "cuentaBancariaOperacionBancaria".
	 */
	@GetMapping("/showCuentaBancariaOperacionesBancarias")
	public String mostrarCuentasBancariasOperacionesBancarias(@RequestParam String numeroCuenta, Model model) {

		final CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		@SuppressWarnings("unused")
		List<OperacionBancaria> operacionesBancariasElegibles = cuentaBancaria.getOperacionesCuentaBancaria();
		
		// Carga de datos al modelo
		model.addAttribute("cuentaBancaria", cuentaBancaria);
		model.addAttribute("usuarios", cuentaBancaria.getUsuarios());

		return "cuentaBancariaOperacionBancaria";
	}



}
