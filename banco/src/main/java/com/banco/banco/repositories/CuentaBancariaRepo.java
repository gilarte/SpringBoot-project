package com.banco.banco.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.banco.models.CuentaBancaria;


public interface CuentaBancariaRepo extends JpaRepository<CuentaBancaria, String>{
	
	CuentaBancaria findByNumeroCuenta(String numeroCuenta);

	List<CuentaBancaria> findBySaldo(double saldo);

	List<CuentaBancaria> findByFechaCreacion(Date fechaCreacion);
	
	List<CuentaBancaria> findByNumeroCuentaAndFechaCreacion(String numeroCuenta, Date fechaCreacion);
}