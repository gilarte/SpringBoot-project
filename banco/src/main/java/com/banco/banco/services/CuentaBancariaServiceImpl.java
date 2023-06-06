package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.banco.models.CuentaBancaria;
import com.banco.banco.repositories.CuentaBancariaRepo;


/**
 * Implementación de los métodos de servicio para la gestión de cuentas bancarias.
 */
@Service
public class CuentaBancariaServiceImpl implements CuentaBancariaServicel{

	@Autowired 
	CuentaBancariaRepo cuentaBancariaRepository;

	/**
     * Obtiene todas las cuentas bancarias.
     *
     * @return Lista de cuentas bancarias
     */
	@Override
	public List<CuentaBancaria> obtenerCuentasBancarias() {
		return cuentaBancariaRepository.findAll();
	}

	/**
     * Obtiene una cuenta bancaria por su número de cuenta.
     *
     * @param numeroCuenta Número de cuenta bancaria
     * @return Cuenta bancaria correspondiente al número de cuenta especificado
     */
	@Override
	public CuentaBancaria obtenerPorNumeroCuenta(String numeroCuenta) {
		return cuentaBancariaRepository.findByNumeroCuenta(numeroCuenta);
	}

	/**
     * Busca cuentas bancarias por fecha de creación.
     *
     * @param fechaCreacion Fecha de creación de las cuentas bancarias
     * @return Lista de cuentas bancarias creadas en la fecha especificada
     */
	@Override
	public List<CuentaBancaria> buscarPorFechasCreacion(Date fechaCreacion) {
		return cuentaBancariaRepository.findByFechaCreacion(fechaCreacion);
	}

	/**
     * Obtiene cuentas bancarias por número de cuenta y fecha de creación.
     *
     * @param numeroCuenta  Número de cuenta bancaria
     * @param fechacreacion Fecha de creación de las cuentas bancarias
     * @return Lista de cuentas bancarias que coinciden con el número de cuenta y la fecha de creación especificados
     */
	@Override
	public List<CuentaBancaria> obtenerPorNumeroCuentaYFechaCreacion(String numeroCuenta, Date fechacreacion) {
		return cuentaBancariaRepository.findByNumeroCuentaAndFechaCreacion(numeroCuenta, fechacreacion);
	}

	/**
     * Inserta una cuenta bancaria en el repositorio.
     *
     * @param cuentaBancaria Cuenta bancaria a insertar
     */
	@Override
	public void insertarCuentaBancaria(CuentaBancaria cuentaBancaria) {
		cuentaBancariaRepository.save(cuentaBancaria);
		
	}

	/**
     * Modifica una cuenta bancaria en el repositorio.
     *
     * @param cuentaBancaria Cuenta bancaria a modificar
     */
	@Override
	public void modificarCuentaBancaria(CuentaBancaria cuentaBancaria) {
		cuentaBancariaRepository.save(cuentaBancaria);
		
	}

	/**
     * Elimina una cuenta bancaria del repositorio.
     *
     * @param cuentaBancaria Cuenta bancaria a eliminar
     */
	@Override
	public void eliminarCunetaBancaria(CuentaBancaria cuentaBancaria) {
		cuentaBancariaRepository.delete(cuentaBancaria);
		
	}

}
