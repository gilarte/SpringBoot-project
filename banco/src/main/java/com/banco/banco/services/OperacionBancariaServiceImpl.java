package com.banco.banco.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.banco.models.OperacionBancaria;
import com.banco.banco.repositories.OperacionBancariaRepo;


@Service
public class OperacionBancariaServiceImpl implements OperacionBancariaServicel{
	@Autowired
	private OperacionBancariaRepo operacionBancariaRepo;

	@Override
	public List<OperacionBancaria> obtenerOperacionesBancarias() {
		return operacionBancariaRepo.findAll();
	}

	@Override
	public OperacionBancaria obtenerPorCodigo(Long codigoOperacion) {
		return operacionBancariaRepo.findByCodigoOperacion(codigoOperacion);
	}

	@Override
	public List<OperacionBancaria> obtenerPorTipo(String tipoOperacion) {
		return operacionBancariaRepo.findByTipoOperacion(tipoOperacion);
	}

	@Override
	public List<OperacionBancaria> obtenerPorFecha(Date fechaOperacion) {
		return operacionBancariaRepo.findByFechaOperacion(fechaOperacion);
	}

	@Override
	public List<OperacionBancaria> obtenerPorCantidad(double cantidadInvolucrada) {
		return operacionBancariaRepo.findByCantidadInvolucrada(cantidadInvolucrada);
	}

	@Override
	public List<OperacionBancaria> obtenerPorNumeroCuentas(String numeroCuentasInvolucradas) {
		return operacionBancariaRepo.findByNumeroCuentaInvolucrada(numeroCuentasInvolucradas);
	}

	@Override
	public void insertarOperacionBancaria(OperacionBancaria operacionBancaria) {
		operacionBancariaRepo.save(operacionBancaria);
	}
	
	@Override
	public void modificarOperacionBancaria(OperacionBancaria operacionBancaria) {
		operacionBancariaRepo.save(operacionBancaria);
	}

	@Override
	public void eliminarOperacionBancaria(OperacionBancaria operacionBancaria) {
		operacionBancariaRepo.delete(operacionBancaria);
	}	
}
