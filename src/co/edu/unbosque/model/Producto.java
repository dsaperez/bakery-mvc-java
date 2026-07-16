package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8119453756800197790L;
	private String nombre;
	private float cantidad;
	private int peso;
	private int precio;

	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public Producto(String nombre, float cantidad, int peso, int precio) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.peso = peso;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String vencerse(int horasDesdeFabricacion) {
		if (horasDesdeFabricacion < 24) {
			return " Este Producto aun esta bueno ";
		} else {
			return " Este Producto ya esta vencido";
		}
	}
	
	public String vender(float cantidad) {
		if (cantidad <= 0) {
			return " La cantidad debe ser mayor a cero ";
		}
		if (cantidad > getCantidad()) {
			return " Producto insuficiente unicamente hay  " + getCantidad() + " unidades disponibles ";
		}
		setCantidad(getCantidad() - cantidad);
		float total = cantidad * getPrecio();
		return " Añadido al carrito: " + cantidad + " productos de " + getNombre() + " y el costo total es : " + total
				+ "\n Producto aun en inventario : " + getCantidad() + "\n Para finalizar la compra oprima el boton finalizar";
	}
	
	public abstract String preparar (ArrayList<String>ingredientes);//FUNCION ABSTRACTA NO PUEDE TENER CORCHETES
}
