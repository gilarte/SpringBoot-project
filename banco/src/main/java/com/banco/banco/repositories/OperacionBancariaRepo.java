package com.banco.banco.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.banco.models.OperacionBancaria;


public interface OperacionBancariaRepo extends JpaRepository<OperacionBancaria, Long>{
	
	OperacionBancaria findByCodigoOperacion(Long codigoOperacion);
	
	List<OperacionBancaria> findByTipoOperacion(String tipoOperacion);
	
	List<OperacionBancaria> findByFechaOperacion(Date fechaOperacion);
	
	List<OperacionBancaria> findByCantidadInvolucrada(double cantidadInvolucrada);
	
	List<OperacionBancaria> findByNumeroCuentaInvolucrada(String numeroCuentasInvolucradas);
}
