package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.banco.models.OperacionBancaria;
import com.banco.banco.repositories.OperacionBancariaRepo;

/**
 * Implementación del servicio de operaciones bancarias.
 * Proporciona métodos para gestionar las operaciones bancarias.
 */
@Service
public class OperacionBancariaServiceImpl implements OperacionBancariaServicel{
	@Autowired
	private OperacionBancariaRepo operacionBancariaRepo;

	/**
	 * Obtiene todas las operaciones bancarias.
	 *
	 * @return Lista de operaciones bancarias
	 */
	@Override
	public List<OperacionBancaria> obtenerOperacionesBancarias() {
		return operacionBancariaRepo.findAll();
	}

	/**
	 * Obtiene una operación bancaria por su código.
	 *
	 * @param codigoOperacion Código de la operación bancaria
	 * @return Operación bancaria correspondiente al código especificado
	 */
	@Override
	public OperacionBancaria obtenerPorCodigo(Long codigoOperacion) {
		return operacionBancariaRepo.findByCodigoOperacion(codigoOperacion);
	}

	/**
	 * Obtiene operaciones bancarias por tipo de operación.
	 *
	 * @param tipoOperacion Tipo de operación de las operaciones bancarias
	 * @return Lista de operaciones bancarias con el tipo de operación especificado
	 */
	@Override
	public List<OperacionBancaria> obtenerPorTipo(String tipoOperacion) {
		return operacionBancariaRepo.findByTipoOperacion(tipoOperacion);
	}

	/**
	 * Obtiene operaciones bancarias por fecha de operación.
	 *
	 * @param fechaOperacion Fecha de operación de las operaciones bancarias
	 * @return Lista de operaciones bancarias con la fecha de operación especificada
	 */
	@Override
	public List<OperacionBancaria> obtenerPorFecha(Date fechaOperacion) {
		return operacionBancariaRepo.findByFechaOperacion(fechaOperacion);
	}

	/**
	 * Obtiene operaciones bancarias por cantidad involucrada.
	 *
	 * @param cantidadInvolucrada Cantidad involucrada en las operaciones bancarias
	 * @return Lista de operaciones bancarias con la cantidad involucrada especificada
	 */
	@Override
	public List<OperacionBancaria> obtenerPorCantidad(double cantidadInvolucrada) {
		return operacionBancariaRepo.findByCantidadInvolucrada(cantidadInvolucrada);
	}

	/**
	 * Obtiene operaciones bancarias por número de cuentas involucradas.
	 *
	 * @param numeroCuentasInvolucradas Números de cuentas involucradas en las operaciones bancarias
	 * @return Lista de operaciones bancarias con los números de cuentas involucradas especificados
	 */
	@Override
	public List<OperacionBancaria> obtenerPorNumeroCuentas(String numeroCuentasInvolucradas) {
		return operacionBancariaRepo.findByNumeroCuentaInvolucrada(numeroCuentasInvolucradas);
	}

	/**
	 * Inserta una nueva operación bancaria.
	 *
	 * @param operacionBancaria Operación bancaria a insertar
	 */
	@Override
	public void insertarOperacionBancaria(OperacionBancaria operacionBancaria) {
		operacionBancariaRepo.save(operacionBancaria);
	}
	
	/**
	 * Modifica una operación bancaria existente.
	 *
	 * @param operacionBancaria Operación bancaria a modificar
	 */
	@Override
	public void modificarOperacionBancaria(OperacionBancaria operacionBancaria) {
		operacionBancariaRepo.save(operacionBancaria);
	}

	/**
	 * Elimina una operación bancaria existente.
	 *
	 * @param operacionBancaria Operación bancaria a eliminar
	 */
	@Override
	public void eliminarOperacionBancaria(OperacionBancaria operacionBancaria) {
		operacionBancariaRepo.delete(operacionBancaria);
	}	
}
