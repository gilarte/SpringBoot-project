package com.banco.banco.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.banco.banco.models.Usuario;
import com.banco.banco.repositories.UsuarioRepo;

@Controller
public class RegistroController {

    private final UsuarioRepo usuarioRepository;

    @Autowired
    public RegistroController(UsuarioRepo usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario()); // Agregar el objeto Usuario al modelo
        return "registration";
    }


    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }

        // Guardar el nuevo usuario en la base de datos
        usuarioRepository.save(usuario);

        // Redirigir a una página de éxito o realizar otras acciones necesarias
        return "redirect:/exito";
    }
}

