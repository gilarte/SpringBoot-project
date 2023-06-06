package com.banco.banco.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.banco.models.CuentaBancaria;

/**
 * Repositorio para la entidad CuentaBancaria.
 * Proporciona métodos para realizar consultas en la base de datos relacionadas con las cuentas bancarias.
 */
public interface CuentaBancariaRepo extends JpaRepository<CuentaBancaria, String>{
	
	/**
     * Busca una cuenta bancaria por su número de cuenta.
     *
     * @param numeroCuenta El número de cuenta a buscar.
     * @return La cuenta bancaria encontrada, o null si no se encuentra ninguna cuenta con el número especificado.
     */
	CuentaBancaria findByNumeroCuenta(String numeroCuenta);

	/**
     * Busca cuentas bancarias por su saldo.
     *
     * @param saldo El saldo de las cuentas bancarias a buscar.
     * @return Una lista de cuentas bancarias que tienen el saldo especificado.
     */
	List<CuentaBancaria> findBySaldo(double saldo);

	 /**
     * Busca cuentas bancarias por su fecha de creación.
     *
     * @param fechaCreacion La fecha de creación de las cuentas bancarias a buscar.
     * @return Una lista de cuentas bancarias que fueron creadas en la fecha especificada.
     */
	List<CuentaBancaria> findByFechaCreacion(Date fechaCreacion);
	
	/**
     * Busca cuentas bancarias por su número de cuenta y fecha de creación.
     *
     * @param numeroCuenta  El número de cuenta a buscar.
     * @param fechaCreacion La fecha de creación de las cuentas bancarias a buscar.
     * @return Una lista de cuentas bancarias que tienen el número de cuenta y fecha de creación especificados.
     */
	List<CuentaBancaria> findByNumeroCuentaAndFechaCreacion(String numeroCuenta, Date fechaCreacion);
}