package co.edu.unbosque.model;

import java.io.Serializable;

public class CarritoVenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3547747858637972481L;
	private String nombreProducto;
	private float cantidad;
	private int precioUnitario;
	private int subtotal;

	public CarritoVenta() {
		// TODO Auto-generated constructor stub
	}

	public CarritoVenta(String nombreProducto, float cantidad, int precioUnitario, int subtotal) {
		super();
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.subtotal = subtotal;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public int getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(int precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int subtotal) {
		this.subtotal = subtotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
