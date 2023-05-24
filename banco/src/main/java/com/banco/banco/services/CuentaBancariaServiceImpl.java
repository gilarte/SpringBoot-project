package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.banco.models.CuentaBancaria;
import com.banco.banco.repositories.CuentaBancariaRepo;



@Service
public class CuentaBancariaServiceImpl implements CuentaBancariaServicel{

	@Autowired 
	CuentaBancariaRepo cuentaBancariaRepository;

	@Override
	public List<CuentaBancaria> obtenerCuentasBancarias() {
		return cuentaBancariaRepository.findAll();
	}

	@Override
	public CuentaBancaria obtenerPorNumeroCuenta(String numeroCuenta) {
		return cuentaBancariaRepository.findByNumeroCuenta(numeroCuenta);
	}

	@Override
	public List<CuentaBancaria> buscarPorFechasCreacion(Date fechaCreacion) {
		return cuentaBancariaRepository.findByFechaCreacion(fechaCreacion);
	}

	@Override
	public List<CuentaBancaria> obtenerPorNumeroCuentaYFechaCreacion(String numeroCuenta, Date fechacreacion) {
		return cuentaBancariaRepository.findByNumeroCuentaAndFechaCreacion(numeroCuenta, fechacreacion);
	}

	@Override
	public void insertarCuentaBancaria(CuentaBancaria cuentaBancaria) {
		cuentaBancariaRepository.save(cuentaBancaria);
		
	}

	@Override
	public void modificarCuentaBancaria(CuentaBancaria cuentaBancaria) {
		cuentaBancariaRepository.save(cuentaBancaria);
		
	}

	@Override
	public void eliminarCunetaBancaria(CuentaBancaria cuentaBancaria) {
		cuentaBancariaRepository.delete(cuentaBancaria);
		
	}

}
