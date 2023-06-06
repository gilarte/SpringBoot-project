package com.banco.banco.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.banco.models.Usuario;

/**
 * Repositorio para la entidad Usuario.
 * Proporciona métodos para realizar consultas en la base de datos relacionadas con los usuarios.
 */
public interface UsuarioRepo extends JpaRepository<Usuario, String>{
	
	/**
     * Busca un usuario por su NIF.
     *
     * @param NIF El NIF del usuario a buscar.
     * @return El usuario encontrado, o null si no se encuentra ningún usuario con el NIF especificado.
     */
	Usuario findByNIF(String NIF);
	
	/**
     * Busca usuarios por su nombre.
     *
     * @param nombre El nombre de los usuarios a buscar.
     * @return Una lista de usuarios que tienen el nombre especificado.
     */
	List<Usuario> findByNombre(String nombre);
	
	/**
     * Busca usuarios por sus apellidos.
     *
     * @param apellidos Los apellidos de los usuarios a buscar.
     * @return Una lista de usuarios que tienen los apellidos especificados.
     */
	List<Usuario> findByApellidos(String apellidos);
	
	/**
     * Busca usuarios por su email.
     *
     * @param email El email de los usuarios a buscar.
     * @return Una lista de usuarios que tienen el email especificado.
     */
	List<Usuario> findByEmail(String email);
	
	/**
     * Busca usuarios por su dirección.
     *
     * @param direccion La dirección de los usuarios a buscar.
     * @return Una lista de usuarios que tienen la dirección especificada.
     */
	List<Usuario> findByDireccion(String direccion);
	
	/**
     * Busca usuarios por su año de nacimiento.
     *
     * @param anyoNacimiento El año de nacimiento de los usuarios a buscar.
     * @return Una lista de usuarios que tienen el año de nacimiento especificado.
     */
	List<Usuario> findByAnyoNacimiento(Date anyoNacimiento);
	
	/**
     * Busca usuarios por su nombre y apellidos.
     *
     * @param nombre    El nombre de los usuarios a buscar.
     * @param apellidos Los apellidos de los usuarios a buscar.
     * @return Una lista de usuarios que tienen el nombre y apellidos especificados.
     */
	List<Usuario> findByNombreAndApellidos(String nombre, String apellidos);

	/**
     * Busca usuarios por sus apellidos y año de nacimiento.
     *
     * @param apellidos       Los apellidos de los usuarios a buscar.
     * @param anyoNacimiento El año de nacimiento de los usuarios a buscar.
     * @return Una lista de usuarios que tienen los apellidos y año de nacimiento especificados.
     */
	List<Usuario> findByApellidosAndAnyoNacimiento(String nombre, Date anyoNacimiento);
	
	/**
     * Busca usuarios por su nombre, apellidos y año de nacimiento.
     *
     * @param nombre         El nombre de los usuarios a buscar.
     * @param apellidos       Los apellidos de los usuarios a buscar.
     * @param fechaNacimiento La fecha de nacimiento de los usuarios a buscar.
     * @return Una lista de usuarios que tienen el nombre, apellidos y año de nacimiento especificados.
     */
	List<Usuario> findByNombreAndApellidosAndAnyoNacimiento(String nombre, String apellidos, Date fechaNacimiento);
	
}