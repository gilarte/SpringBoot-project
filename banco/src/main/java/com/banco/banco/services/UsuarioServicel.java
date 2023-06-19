package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import com.banco.banco.models.Usuario;


/**
 * Servicio de usuario.
 * Proporciona métodos para gestionar usuarios.
 */
public interface UsuarioServicel {
	
	/**
	 * Obtiene todos los usuarios.
	 *
	 * @return Lista de usuarios
	 */
	public List<Usuario> obtenerUsuarios();

	/**
	 * Obtiene un usuario por su NIF.
	 *
	 * @param NIF NIF del usuario
	 * @return Usuario correspondiente al NIF especificado
	 */
	public Usuario obtenerPorNIF(String NIF);

	/**
	 * Obtiene usuarios por nombre.
	 *
	 * @param nombre Nombre de los usuarios
	 * @return Lista de usuarios con el nombre especificado
	 */
	public List<Usuario> obtenerPorNombre(String nombre);
	
	public Usuario obtenerPorNombreU(String nombre);

	
	/**
	 * Obtiene usuarios por año de nacimiento.
	 *
	 * @param anyoNacimiento Año de nacimiento de los usuarios
	 * @return Lista de usuarios con el año de nacimiento especificado
	 */
	public List<Usuario> obtenerPorAnyoNacimiento(Date anyoNacimiento);
	
	/**
	 * Obtiene usuarios por apellidos.
	 *
	 * @param apellidos Apellidos de los usuarios
	 * @return Lista de usuarios con los apellidos especificados
	 */
	public List<Usuario> obtenerPorApellidos(String apellidos);
	
	/**
	 * Obtiene usuarios por nombre y apellidos.
	 *
	 * @param nombre   Nombre de los usuarios
	 * @param apellido Apellido de los usuarios
	 * @return Lista de usuarios que coinciden con el nombre y apellidos especificados
	 */
	public List<Usuario> obtenerNombreYApellidos(String nombre, String apellido);
	
	/**
	 * Obtiene usuarios por apellidos y año de nacimiento.
	 *
	 * @param nombre         Nombre de los usuarios
	 * @param anyoNacimiento Año de nacimiento de los usuarios
	 * @return Lista de usuarios que coinciden con los apellidos y año de nacimiento especificados
	 */
	public List<Usuario> obtenerPorApellidosYAnyoNacimiento(String nombre, Date anyoNacimiento);
	
	/**
	 * Obtiene usuarios por nombre, apellidos y año de nacimiento.
	 *
	 * @param nombre         Nombre de los usuarios
	 * @param apellido       Apellido de los usuarios
	 * @param anyoNacimiento Año de nacimiento de los usuarios
	 * @return Lista de usuarios que coinciden con el nombre, apellidos y año de nacimiento especificados
	 */
	public List<Usuario> obtenerPorNombreYApellidosYAnyoNacimiento(String nombre, String apellido, Date anyoNacimiento);
	
	/**
	 * Inserta un nuevo usuario.
	 *
	 * @param usuario Usuario a insertar
	 */
	public void insertarUsuario(Usuario usuario);

	/**
	 * Elimina un usuario existente.
	 *
	 * @param usuario Usuario a eliminar
	 */
	public void eliminarUsuario(Usuario usuario);
	
	/**
	 * Modifica un usuario existente.
	 *
	 * @param usuario Usuario a modificar
	 */
	public void modificarUsaurio (Usuario usuario);
}
