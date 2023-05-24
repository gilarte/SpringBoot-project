package com.banco.banco.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.banco.models.Usuario;


public interface UsuarioRepo extends JpaRepository<Usuario, String>{
	
	Usuario findByNIF(String NIF);
	
	List<Usuario> findByNombre(String nombre);
	
	List<Usuario> findByApellidos(String apellidos);
	
	List<Usuario> findByEmail(String email);
	
	List<Usuario> findByDireccion(String direccion);
	
	List<Usuario> findByAnyoNacimiento(Date anyoNacimiento);
	
	List<Usuario> findByNombreAndApellidos(String nombre, String apellidos);

	List<Usuario> findByApellidosAndAnyoNacimiento(String nombre, Date anyoNacimiento);
	
	List<Usuario> findByNombreAndApellidosAndAnyoNacimiento(String nombre, String apellidos, Date fechaNacimiento);
	
}