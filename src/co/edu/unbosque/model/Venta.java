package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Venta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2410861358945298610L;
	private ArrayList<CarritoVenta> productos;
	private ClienteDTO clienteDTO;
	private String metodoPago;
	private int total;

	public Venta() {
		// TODO Auto-generated constructor stub
	}

	public Venta(ArrayList<CarritoVenta> productos, ClienteDTO clienteDTO, String metodoPago, int total) {
		super();
		this.productos = productos;
		this.clienteDTO = clienteDTO;
		this.metodoPago = metodoPago;
		this.total = total;
	}

	public ArrayList<CarritoVenta> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<CarritoVenta> productos) {
		this.productos = productos;
	}

	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}

	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
