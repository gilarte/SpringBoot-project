package com.banco.banco.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Table(name = "cuentas_bancarias")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"usuarios"})
public class CuentaBancaria implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "numero_cuenta", nullable = false)
	private String numeroCuenta;

	@Column(name = "saldo", nullable = false)
	private double saldo;

	@NotNull(message = "Debe registrar la fecha de creación")
	@Column(name = "Fecha_creación", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaCreacion;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "usuarios_cuentasBancarias", 
		joinColumns = @JoinColumn(name = "cuentaBancaria_numeroCuenta"), 
		inverseJoinColumns = @JoinColumn(name = "usuario_NIF"))
	private List<Usuario> usuarios;

	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "operacionBancaria_codigoOperacion")
	private List<OperacionBancaria> operacionesCuentaBancaria;


	public String getNumeroCuenta() {
		return numeroCuenta;
	}


	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public List<OperacionBancaria> getOperacionesCuentaBancaria() {
		return operacionesCuentaBancaria;
	}


	public void setOperacionesCuentaBancaria(List<OperacionBancaria> operacionesCuentaBancaria) {
		this.operacionesCuentaBancaria = operacionesCuentaBancaria;
	}
	
	
}
