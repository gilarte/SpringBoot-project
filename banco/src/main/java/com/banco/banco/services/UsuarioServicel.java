package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import com.banco.banco.models.Usuario;


/**
 * 
 * Servicio de usuario
 *
 */
public interface UsuarioServicel {
	
	
	public List<Usuario> obtenerUsuarios();

	public Usuario obtenerPorNIF(String NIF);

	public List<Usuario> obtenerPorNombre(String nombre);
	
	public List<Usuario> obtenerPorAnyoNacimiento(Date anyoNacimiento);
	
	public List<Usuario> obtenerPorApellidos(String apellidos);
	
	public List<Usuario> obtenerNombreYApellidos(String nombre, String apellido);
	
	public List<Usuario> obtenerPorApellidosYAnyoNacimiento(String nombre, Date anyoNacimiento);
	
	public List<Usuario> obtenerPorNombreYApellidosYAnyoNacimiento(String nombre, String apellido, Date anyoNacimiento);
	
	public void insertarUsuario(Usuario usuario);

	public void eliminarUsuario(Usuario usuario);
	
	public void modificarUsaurio (Usuario usuario);
}
