package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import com.banco.banco.models.OperacionBancaria;


public interface OperacionBancariaServicel {

	public List<OperacionBancaria> obtenerOperacionesBancarias();

	public OperacionBancaria obtenerPorCodigo(Long codigoOperacion);

	public List<OperacionBancaria> obtenerPorTipo(String tipoOperacion);

	public List<OperacionBancaria> obtenerPorFecha(Date fechaOperacion);

	public List<OperacionBancaria> obtenerPorCantidad(double cantidadInvolucrada);

	public List<OperacionBancaria> obtenerPorNumeroCuentas(String numeroCuentasInvolucradas);
	
	public void insertarOperacionBancaria(OperacionBancaria operacionBancaria);

	public void eliminarOperacionBancaria(OperacionBancaria operacionBancaria);
	
	public void modificarOperacionBancaria(OperacionBancaria operacionBancaria);
}
