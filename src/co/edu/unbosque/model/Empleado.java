package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Empleado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7875908698754277112L;
	private String nombreCompleto;
	private String genero;
	private int edad;
	private String numTelefono;
	private float salario;

	public Empleado() {
		// TODO Auto-generated constructor stub
	}

	public Empleado(String nombreCompleto, String genero, int edad, String numTelefono, float salario) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.genero = genero;
		this.edad = edad;
		this.numTelefono = numTelefono;
		this.salario = salario;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	public float calcularSalarioPorDia() {
	    return salario / 30;
	}

}
