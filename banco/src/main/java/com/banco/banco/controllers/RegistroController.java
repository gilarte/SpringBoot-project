package com.banco.banco.controllers;

import java.util.Collections;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.banco.banco.models.Role;
import com.banco.banco.models.Usuario;
import com.banco.banco.repositories.UsuarioRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class RegistroController {

	private static final Logger logger = LoggerFactory.getLogger(RegistroController.class);
	private final UsuarioRepo usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegistroController(UsuarioRepo usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    /**
     * Muestra el formulario de registro.
     * @param model El modelo de la vista.
     * @return La vista "registration" con el formulario de registro.
     */
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario()); // Agregar el objeto Usuario al modelo
        return "registration";
    }

    /**
     * Registra un nuevo usuario.
     * @param usuario El objeto Usuario que se va a registrar.
     * @param result Los resultados de la validación del formulario.
     * @return Redirige a la página de éxito o a una acción adicional después de registrar al usuario.
     */
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result) {
        logger.info("Valor de passwordEncoder: " + passwordEncoder);
        if (result.hasErrors()) {
            return "registration";
        }
        System.out.println("Valor de passwordEncoder: " + passwordEncoder);

     // Encriptar la contraseña antes de guardarla
        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptada);
        
     // Crear el objeto Role para el rol "ROLE_USER"
        Role roleUser = new Role();
        roleUser.setAuthority("ROLE_USER");

        // Asignar el rol "ROLE_USER" al nuevo usuario
        usuario.setRoles(Collections.singletonList(roleUser));
        // Guardar el nuevo usuario en la base de datos
        usuarioRepository.save(usuario);

        // Redirigir a una página de éxito o realizar otras acciones necesarias
        return "redirect:/exito";
    }
}
