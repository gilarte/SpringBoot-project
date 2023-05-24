package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import com.banco.banco.models.CuentaBancaria;


public interface CuentaBancariaServicel {


	public List<CuentaBancaria> obtenerCuentasBancarias();

	public CuentaBancaria obtenerPorNumeroCuenta(String numeroCuenta);

	public List<CuentaBancaria> buscarPorFechasCreacion(Date fechaCreacion);
	
	public List<CuentaBancaria> obtenerPorNumeroCuentaYFechaCreacion(String numeroCuenta, Date fechacreacion);
	
	
	public void insertarCuentaBancaria(CuentaBancaria cuentaBancaria);
	
	public void modificarCuentaBancaria(CuentaBancaria cuentaBancaria);
	
	public void eliminarCunetaBancaria(CuentaBancaria cuentaBancaria);
}

