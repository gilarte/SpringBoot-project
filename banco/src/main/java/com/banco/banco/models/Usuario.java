package com.banco.banco.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;


import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"cuentasBancarias"})
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Size(max = 9, min = 9, message = "El NIF tiene que tener 9 caracteres")
	@NotEmpty(message = "El NIF es obligatorio")
	@Pattern(regexp = "[0-9]{8}[A-Z]{1}", message = "NIF no es válido")
	@Column(name = "NIF", nullable = false, unique = true)
	private String NIF;

	@NotEmpty(message = "El nombre es obligatorio")
	@Size(min = 4, max = 12, message = "el tamaño debe ser entre 4 y 12")
	@Column(name = "Nombre", nullable = false)
	private String nombre;

	
	@NotEmpty(message = "El apellido es obligatorio")
	@Size(max = 50)
	@Column(name = "Apellidos", nullable = false)
	private String apellidos;

	@NotEmpty(message = "El numero es obligatorio")
	@Column(name = "Telefono", nullable = false)
	private String telefono;

	@Size(max = 50)
	@NotEmpty(message = "El email es obligatorio")
	@Email(message = "dirección invalida")
	@Column(name = "Email", nullable = false)
	private String email;

	@Size(max = 50)
	@Column(name = "Direccion", nullable = false)
	private String direccion;
	
	@Size(min = 1)
	@Column(name = "password", nullable = false)
	private String password;

	@NotNull(message = "la fecha de nacimiento es obligatoria")
	@Column(name = "Anyo_de_Nacimiento", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date anyoNacimiento;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "usuarios_cuentasBancarias", 
		joinColumns = @JoinColumn(name = "usuario_NIF"), 
		inverseJoinColumns = @JoinColumn(name = "cuentaBancaria_numeroCuenta"))
	private List<CuentaBancaria> cuentasBancarias;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "NIF")
	private List<Role> roles;

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getAnyoNacimiento() {
		return anyoNacimiento;
	}

	public void setAnyoNacimiento(Date anyoNacimiento) {
		this.anyoNacimiento = anyoNacimiento;
	}

	public List<CuentaBancaria> getCuentasBancarias() {
		return cuentasBancarias;
	}

	public void setCuentasBancarias(List<CuentaBancaria> cuentasBancarias) {
		this.cuentasBancarias = cuentasBancarias;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Usuario(
			@Size(max = 9, min = 9, message = "El NIF tiene que tener 9 caracteres") @NotEmpty(message = "El NIF es obligatorio") @Pattern(regexp = "[0-9]{8}[A-Z]{1}", message = "NIF no es válido") String nIF,
			@NotEmpty(message = "El nombre es obligatorio") @Size(min = 4, max = 12, message = "el tamaño debe ser entre 4 y 12") String nombre,
			@NotEmpty(message = "El apellido es obligatorio") @Size(max = 50) String apellidos,
			@NotEmpty(message = "El numero es obligatorio") String telefono,
			@Size(max = 50) @NotEmpty(message = "El email es obligatorio") @Email(message = "dirección invalida") String email,
			@Size(max = 50) String direccion, @Size(max = 50) String password,
			@NotNull(message = "la fecha de nacimiento es obligatoria") Date anyoNacimiento,
			List<CuentaBancaria> cuentasBancarias, List<Role> roles) {
		super();
		NIF = nIF;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.password = password;
		this.anyoNacimiento = anyoNacimiento;
		this.cuentasBancarias = cuentasBancarias;
		this.roles = roles;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(
			@NotEmpty(message = "El nombre es obligatorio") @Size(min = 4, max = 12, message = "el tamaño debe ser entre 4 y 12") String nombre,
			@Size(max = 50) String password) {
		super();
		this.nombre = nombre;
		this.password = password;
	}
	
	
}

