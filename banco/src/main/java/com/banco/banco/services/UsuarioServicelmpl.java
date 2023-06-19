package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.banco.models.Usuario;
import com.banco.banco.repositories.UsuarioRepo;

/**
 * Implementación de los métodos de servicio para la gestión de usuarios.
 */
@Service
public class UsuarioServicelmpl implements UsuarioServicel{
	 @Autowired
	    private UsuarioRepo usuarioRepository;

	 	/**
	     * Obtiene todos los usuarios.
	     *
	     * @return Lista de usuarios
	     */
	    @Override
	    public List<Usuario> obtenerUsuarios() {
	        return usuarioRepository.findAll();
	    }
	    
	    /**
	     * Obtiene un usuario por su NIF.
	     *
	     * @param NIF NIF del usuario
	     * @return Usuario correspondiente al NIF especificado
	     */
	    @Override
		public Usuario obtenerPorNIF(String NIF) {
			return usuarioRepository.findByNIF(NIF);
		}
	    
	    /**
	     * Obtiene usuarios por nombre.
	     *
	     * @param nombre Nombre de los usuarios
	     * @return Lista de usuarios con el nombre especificado
	     */
	    @Override
	    public List<Usuario> obtenerPorNombre(String nombre) {
	        return usuarioRepository.findByNombre(nombre);
	    }
	    
	    @Override
		public Usuario obtenerPorNombreU(String nombre) {
			// TODO Auto-generated method stub
			return (Usuario) usuarioRepository.findByNombre(nombre);
		}
	    
	    /**
	     * Obtiene usuarios por apellidos.
	     *
	     * @param apellidos Apellidos de los usuarios
	     * @return Lista de usuarios con los apellidos especificados
	     */
	    @Override
		public List<Usuario> obtenerPorApellidos(String apellidos) {
			return usuarioRepository.findByApellidos(apellidos);
		}
	    
	    /**
	     * Obtiene usuarios por año de nacimiento.
	     *
	     * @param anyoNacimiento Año de nacimiento de los usuarios
	     * @return Lista de usuarios con el año de nacimiento especificado
	     */
	    @Override
		public List<Usuario> obtenerPorAnyoNacimiento(Date anyoNacimiento) {
			return usuarioRepository.findByAnyoNacimiento(anyoNacimiento);
		}
	    
	    /**
	     * Obtiene usuarios por nombre y apellidos.
	     *
	     * @param nombre   Nombre de los usuarios
	     * @param apellido Apellido de los usuarios
	     * @return Lista de usuarios que coinciden con el nombre y apellidos especificados
	     */
	    @Override
		public List<Usuario> obtenerNombreYApellidos(String nombre, String apellido) {
			return usuarioRepository.findByNombreAndApellidos(nombre, apellido);
		}
	    
	    /**
	     * Obtiene usuarios por apellidos y año de nacimiento.
	     *
	     * @param nombre         Nombre de los usuarios
	     * @param anyoNacimiento Año de nacimiento de los usuarios
	     * @return Lista de usuarios que coinciden con los apellidos y año de nacimiento especificados
	     */
	    @Override
		public List<Usuario> obtenerPorApellidosYAnyoNacimiento(String nombre, Date anyoNacimiento) {
			return usuarioRepository.findByApellidosAndAnyoNacimiento(nombre, anyoNacimiento);
		}

	    /**
	     * Obtiene usuarios por nombre, apellidos y año de nacimiento.
	     *
	     * @param nombre         Nombre de los usuarios
	     * @param apellido       Apellido de los usuarios
	     * @param anyoNacimiento Año de nacimiento de los usuarios
	     * @return Lista de usuarios que coinciden con el nombre, apellidos y año de nacimiento especificados
	     */
		@Override
		public List<Usuario> obtenerPorNombreYApellidosYAnyoNacimiento(String nombre, String apellido,Date anyoNacimiento) {
			return usuarioRepository.findByNombreAndApellidosAndAnyoNacimiento(nombre, apellido, anyoNacimiento);
		}

		/**
	     * Inserta un nuevo usuario.
	     *
	     * @param usuario Usuario a insertar
	     */
		@Override
		public void insertarUsuario(Usuario usuario) {
			usuarioRepository.save(usuario);
		}
		
		/**
	     * Modifica un usuario existente.
	     *
	     * @param usuario Usuario a modificar
	     */
		@Override
		public void modificarUsaurio(Usuario usuario) {
			usuarioRepository.save(usuario);
			
		}

		/**
	     * Elimina un usuario existente.
	     *
	     * @param usuario Usuario a eliminar
	     */
		@Override
		public void eliminarUsuario(Usuario usuario) {
			usuarioRepository.delete(usuario);
			
		}

		
	}