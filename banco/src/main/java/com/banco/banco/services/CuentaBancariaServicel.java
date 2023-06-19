package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import com.banco.banco.models.CuentaBancaria;

/**
 * Servicio de cuentas bancarias.
 * Proporciona métodos para gestionar las cuentas bancarias.
 */
public interface CuentaBancariaServicel {
    List<CuentaBancaria> findAllByUsuariosNIF(String nif);

	
	//public List<CuentaBancaria> getCuentasBancariasPorUsuario(String nif);

	/**
	 * Obtiene todas las cuentas bancarias.
	 *
	 * @return Lista de cuentas bancarias
	 */
	public List<CuentaBancaria> obtenerCuentasBancarias();

	/**
	 * Obtiene una cuenta bancaria por su número de cuenta.
	 *
	 * @param numeroCuenta Número de cuenta de la cuenta bancaria
	 * @return Cuenta bancaria correspondiente al número de cuenta especificado
	 */
	public CuentaBancaria obtenerPorNumeroCuenta(String numeroCuenta);

	/**
	 * Busca cuentas bancarias por fecha de creación.
	 *
	 * @param fechaCreacion Fecha de creación de las cuentas bancarias
	 * @return Lista de cuentas bancarias con la fecha de creación especificada
	 */
	public List<CuentaBancaria> buscarPorFechasCreacion(Date fechaCreacion);
	
	/**
	 * Obtiene cuentas bancarias por número de cuenta y fecha de creación.
	 *
	 * @param numeroCuenta Número de cuenta de las cuentas bancarias
	 * @param fechaCreacion Fecha de creación de las cuentas bancarias
	 * @return Lista de cuentas bancarias con el número de cuenta y fecha de creación especificados
	 */
	public List<CuentaBancaria> obtenerPorNumeroCuentaYFechaCreacion(String numeroCuenta, Date fechacreacion);
	
	/**
	 * Inserta una nueva cuenta bancaria.
	 *
	 * @param cuentaBancaria Cuenta bancaria a insertar
	 */
	public void insertarCuentaBancaria(CuentaBancaria cuentaBancaria);
	
	/**
	 * Modifica una cuenta bancaria existente.
	 *
	 * @param cuentaBancaria Cuenta bancaria a modificar
	 */
	public void modificarCuentaBancaria(CuentaBancaria cuentaBancaria);
	
	/**
	 * Elimina una cuenta bancaria existente.
	 *
	 * @param cuentaBancaria Cuenta bancaria a eliminar
	 */
	public void eliminarCunetaBancaria(CuentaBancaria cuentaBancaria);
}

