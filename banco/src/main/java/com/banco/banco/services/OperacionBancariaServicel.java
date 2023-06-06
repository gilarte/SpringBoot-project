package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import com.banco.banco.models.OperacionBancaria;

/**
 * Servicio de operaciones bancarias.
 * Proporciona métodos para gestionar las operaciones bancarias.
 */
public interface OperacionBancariaServicel {

	/**
	 * Obtiene todas las operaciones bancarias.
	 *
	 * @return Lista de operaciones bancarias
	 */
	public List<OperacionBancaria> obtenerOperacionesBancarias();

	/**
	 * Obtiene una operación bancaria por su código.
	 *
	 * @param codigoOperacion Código de la operación bancaria
	 * @return Operación bancaria correspondiente al código especificado
	 */
	public OperacionBancaria obtenerPorCodigo(Long codigoOperacion);

	/**
	 * Obtiene operaciones bancarias por tipo.
	 *
	 * @param tipoOperacion Tipo de las operaciones bancarias
	 * @return Lista de operaciones bancarias con el tipo especificado
	 */
	public List<OperacionBancaria> obtenerPorTipo(String tipoOperacion);

	/**
	 * Obtiene operaciones bancarias por fecha.
	 *
	 * @param fechaOperacion Fecha de las operaciones bancarias
	 * @return Lista de operaciones bancarias con la fecha especificada
	 */
	public List<OperacionBancaria> obtenerPorFecha(Date fechaOperacion);

	/**
	 * Obtiene operaciones bancarias por cantidad involucrada.
	 *
	 * @param cantidadInvolucrada Cantidad involucrada en las operaciones bancarias
	 * @return Lista de operaciones bancarias con la cantidad involucrada especificada
	 */
	public List<OperacionBancaria> obtenerPorCantidad(double cantidadInvolucrada);

	/**
	 * Obtiene operaciones bancarias por número de cuentas involucradas.
	 *
	 * @param numeroCuentasInvolucradas Números de cuentas involucradas en las operaciones bancarias
	 * @return Lista de operaciones bancarias con los números de cuentas involucradas especificados
	 */
	public List<OperacionBancaria> obtenerPorNumeroCuentas(String numeroCuentasInvolucradas);
	
	/**
	 * Inserta una nueva operación bancaria.
	 *
	 * @param operacionBancaria Operación bancaria a insertar
	 */
	public void insertarOperacionBancaria(OperacionBancaria operacionBancaria);

	/**
	 * Elimina una operación bancaria existente.
	 *
	 * @param operacionBancaria Operación bancaria a eliminar
	 */
	public void eliminarOperacionBancaria(OperacionBancaria operacionBancaria);
	
	/**
	 * Modifica una operación bancaria existente.
	 *
	 * @param operacionBancaria Operación bancaria a modificar
	 */
	public void modificarOperacionBancaria(OperacionBancaria operacionBancaria);
}
