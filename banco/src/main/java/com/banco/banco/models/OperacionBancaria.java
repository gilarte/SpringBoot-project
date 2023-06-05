package com.banco.banco.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operaciones_bancarias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperacionBancaria  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CodidoOperacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoOperacion;

	@Column(name = "tipo_operacion", nullable = false)
	private TipoOperacion tipoOperacion;

	@NotNull(message = "debe registrar la fecha de nacimiento")
	@Column(name = "fecha_operacion", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaOperacion;

	@Min(value = 1, message = "Debe haber al menos una cuenta involucrada")
	@Column(name = "cantidad_involucrada", nullable = false)
	private double cantidadInvolucrada;

	@Column(name = "numeroCuentaInvolucrada", nullable = false)
	private String numeroCuentaInvolucrada; 
	
	private String usuario;
	
	public enum TipoOperacion {
		Ingresar,
		Retirar
	}

	public Long getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(Long codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public Date getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public double getCantidadInvolucrada() {
		return cantidadInvolucrada;
	}

	public void setCantidadInvolucrada(double cantidadInvolucrada) {
		this.cantidadInvolucrada = cantidadInvolucrada;
	}

	public String getNumeroCuentaInvolucrada() {
		return numeroCuentaInvolucrada;
	}

	public void setNumeroCuentaInvolucrada(String numeroCuentaInvolucrada) {
		this.numeroCuentaInvolucrada = numeroCuentaInvolucrada;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	};
	
	
}
