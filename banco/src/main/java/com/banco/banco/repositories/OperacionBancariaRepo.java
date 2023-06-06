package com.banco.banco.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.banco.models.OperacionBancaria;

/**
 * Repositorio para la entidad OperacionBancaria.
 * Proporciona métodos para realizar consultas en la base de datos relacionadas con las operaciones bancarias.
 */
public interface OperacionBancariaRepo extends JpaRepository<OperacionBancaria, Long>{
	
	 /**
     * Busca una operación bancaria por su código de operación.
     *
     * @param codigoOperacion El código de operación de la operación bancaria a buscar.
     * @return La operación bancaria encontrada, o null si no se encuentra ninguna operación con el código especificado.
     */
	OperacionBancaria findByCodigoOperacion(Long codigoOperacion);
	
	/**
     * Busca operaciones bancarias por su tipo de operación.
     *
     * @param tipoOperacion El tipo de operación de las operaciones bancarias a buscar.
     * @return Una lista de operaciones bancarias que tienen el tipo de operación especificado.
     */
	List<OperacionBancaria> findByTipoOperacion(String tipoOperacion);
	
	/**
     * Busca operaciones bancarias por su fecha de operación.
     *
     * @param fechaOperacion La fecha de operación de las operaciones bancarias a buscar.
     * @return Una lista de operaciones bancarias que tienen la fecha de operación especificada.
     */
	List<OperacionBancaria> findByFechaOperacion(Date fechaOperacion);
	
	/**
     * Busca operaciones bancarias por la cantidad involucrada.
     *
     * @param cantidadInvolucrada La cantidad involucrada en las operaciones bancarias a buscar.
     * @return Una lista de operaciones bancarias que tienen la cantidad involucrada especificada.
     */
	List<OperacionBancaria> findByCantidadInvolucrada(double cantidadInvolucrada);
	
	/**
     * Busca operaciones bancarias por el número de cuenta involucrada.
     *
     * @param numeroCuentasInvolucradas El número de cuenta involucrada en las operaciones bancarias a buscar.
     * @return Una lista de operaciones bancarias que tienen el número de cuenta involucrada especificado.
     */
	List<OperacionBancaria> findByNumeroCuentaInvolucrada(String numeroCuentasInvolucradas);
}
