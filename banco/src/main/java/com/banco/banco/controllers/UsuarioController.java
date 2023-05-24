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

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

	@Autowired
	private UsuarioServicel usuarioServiceI;

	@Autowired
	private CuentaBancariaServicel cuentaBancariaServicel;
	
	@Autowired
	private OperacionBancariaServicel operacionBancariaServicel;

	@RequestMapping("/home")
	@ResponseBody
	public String home() {
		return "hello world";
	}

	@GetMapping("/showUsuariosView")
	public String mostrarUsuarios(Model model) {
		final List<Usuario> listaUsuarios = usuarioServiceI.obtenerUsuarios();
		model.addAttribute("usuariosListView", listaUsuarios);

		return "usuario";
	}

	@GetMapping("/showUsuarioMod")
	public String mostrarModUsuario(@RequestParam String usuarioNif, Model model) {

		// Obtención de empleado a modificar
		final Usuario usuarioToMod = usuarioServiceI.obtenerPorNIF(usuarioNif);

		// Carga de datos al modelo
		model.addAttribute("usuarioToMod", usuarioToMod);

		return "usuarioModificar";
	}

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

	@GetMapping("/actDropUsuario")
	public String eliminarUsuario(@RequestParam String usuarioNif, Model model) {

		// Eliminación de empleado
		usuarioServiceI.eliminarUsuario(usuarioServiceI.obtenerPorNIF(usuarioNif));

		return "redirect:showUsuariosView";

	}

	@GetMapping("/actDropCuentaBancariaUsuario")
	public String eliminarProyectoDeEmpleado(@RequestParam String usuarioNIF, @RequestParam String numeroCuenta, Model model) {
		Usuario usuario = usuarioServiceI.obtenerPorNIF(usuarioNIF);
		CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		usuario.getCuentasBancarias().remove(cuentaBancaria);
		usuarioServiceI.modificarUsaurio(usuario);

		return "redirect:showUsuarioCuentasBancarias?usuarioNif=" + usuario.getNIF();
	}

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
	
	@GetMapping("/showCuentaBancariaOperacionesBancarias")
	public String mostrarCuentasBancariasOperacionesBancarias(@RequestParam String numeroCuenta, Model model) {

		final CuentaBancaria cuentaBancaria = cuentaBancariaServicel.obtenerPorNumeroCuenta(numeroCuenta);
		List<OperacionBancaria> operacionesBancariasElegibles = cuentaBancaria.getOperacionesCuentaBancaria();
		
		// Carga de datos al modelo
		model.addAttribute("cuentaBancaria", cuentaBancaria);
		model.addAttribute("usuarios", cuentaBancaria.getUsuarios());

		return "cuentaBancariaOperacionBancaria";
	}



}
