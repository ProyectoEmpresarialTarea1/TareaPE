package pe.edu.cibertec.proyemp.jpa.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.edu.cibertec.proyemp.jpa.listener.EmpleadoListener;

@Entity
//@EntityListeners(EmpleadoListener.class)
@Table (name="tb_empleado")
public class Empleado {	
	
	@Id
	@GeneratedValue
	@Column(name="EMP_ID", nullable = false, unique = true)
	private Long id;
	
	@Column(name="EMP_NOMBRE", nullable = false, length = 200)
	private String nombre;	
	
	@Column(name="EMP_APELLIDO", length = 200)
	private String apellido;	
	
	@Column(name="EMP_DNI",unique=true,columnDefinition="CHAR(8)")
	private String dni;	

	@ManyToOne	
	@JoinColumn(name="DEP_ID")
	private Departamento departamento;	
	
	@Column(name="EMP_SALARIO", precision=10, scale=2)
	private BigDecimal salario;		
	
	
	@Column(name="EMP_FEC_ING")
	@Temporal(value = TemporalType.DATE)
	private Date fechaIngreso;

	
	@Column(name="EMP_FEC_NAC")
	@Temporal(value=TemporalType.DATE)
	private Date fechaNacimiento;





	public Empleado(){}	

	
	
	
	public Empleado(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Empleado(String nombre, Departamento departamento) {
		super();
		this.nombre = nombre;
		this.departamento = departamento;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + "]";
	}

	
}
