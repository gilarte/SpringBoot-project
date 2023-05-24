package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.banco.models.Usuario;
import com.banco.banco.repositories.UsuarioRepo;


@Service
public class UsuarioServicelmpl implements UsuarioServicel{
	 @Autowired
	    private UsuarioRepo usuarioRepository;

	    @Override
	    public List<Usuario> obtenerUsuarios() {
	        return usuarioRepository.findAll();
	    }
	    
	    @Override
		public Usuario obtenerPorNIF(String NIF) {
			return usuarioRepository.findByNIF(NIF);
		}
	    
	    @Override
	    public List<Usuario> obtenerPorNombre(String nombre) {
	        return usuarioRepository.findByNombre(nombre);
	    }
	    
	    @Override
		public List<Usuario> obtenerPorApellidos(String apellidos) {
			return usuarioRepository.findByApellidos(apellidos);
		}
	    
	    @Override
		public List<Usuario> obtenerPorAnyoNacimiento(Date anyoNacimiento) {
			return usuarioRepository.findByAnyoNacimiento(anyoNacimiento);
		}
	    
	    @Override
		public List<Usuario> obtenerNombreYApellidos(String nombre, String apellido) {
			return usuarioRepository.findByNombreAndApellidos(nombre, apellido);
		}
	    
	    @Override
		public List<Usuario> obtenerPorApellidosYAnyoNacimiento(String nombre, Date anyoNacimiento) {
			return usuarioRepository.findByApellidosAndAnyoNacimiento(nombre, anyoNacimiento);
		}

		@Override
		public List<Usuario> obtenerPorNombreYApellidosYAnyoNacimiento(String nombre, String apellido,Date anyoNacimiento) {
			return usuarioRepository.findByNombreAndApellidosAndAnyoNacimiento(nombre, apellido, anyoNacimiento);
		}

		
		@Override
		public void insertarUsuario(Usuario usuario) {
			usuarioRepository.save(usuario);
		}
		
		@Override
		public void modificarUsaurio(Usuario usuario) {
			usuarioRepository.save(usuario);
			
		}

		@Override
		public void eliminarUsuario(Usuario usuario) {
			usuarioRepository.delete(usuario);
			
		}
	}