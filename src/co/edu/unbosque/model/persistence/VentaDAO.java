package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.CarritoVenta;
import co.edu.unbosque.model.Venta;
import co.edu.unbosque.model.VentaDTO;

public class VentaDAO implements OperacionCRUD<Venta, VentaDTO> {

	private ArrayList<Venta> listaVentas;
	private final String NOMBRE_ARCHIVO_SERIALIZADO = "ventas.bin";

	public VentaDAO() {
		listaVentas = new ArrayList<Venta>();
		leerArchivoSerializado();
	}

	@Override
	public void crear(VentaDTO nuevoDato) {
		listaVentas.add(DataMapper.convertirVentaDTOAVenta(nuevoDato));
		escribirArchivoSerializado();
	}

	@Override
	public String mostrar() {
		int posicion = 0;
		String contenido = "";
		for (Venta ventaActual : listaVentas) {
			ClienteDTO cliente = ventaActual.getClienteDTO();
			contenido += "\n Venta en la posicion : " + posicion + "\n";
			contenido += " Cliente : " + cliente.getNombre() + " " + cliente.getApellido() + "\n";
			contenido += " Cedula : " + cliente.getCedula() + "\n";
			contenido += " Telefono : " + cliente.getTelefono() + "\n";
			contenido += " Productos comprados : \n";
			for (CarritoVenta producto : ventaActual.getProductos()) {
				contenido += "   - " + producto.getNombreProducto() + " | Cantidad : " + producto.getCantidad()
						+ " | Precio Unitario : " + producto.getPrecioUnitario() + " | Subtotal : " + producto.getSubtotal()
						+ "\n";
			}
			contenido += " Metodo de Pago : " + ventaActual.getMetodoPago() + "\n";
			contenido += " Total de la Venta : " + ventaActual.getTotal() + "\n";
			posicion++;
		}
		return contenido;
	}

	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaVentas.size()) {
			return false;
		} else {
			listaVentas.remove(index);
			escribirArchivoSerializado();
			return true;
		}
	}

	@Override
	public boolean actualizar(int index, VentaDTO datoActualizado) {
		if (index < 0 || index >= listaVentas.size()) {
			return false;
		}
		listaVentas.set(index, DataMapper.convertirVentaDTOAVenta(datoActualizado));
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public int contar() {
		return listaVentas.size();
	}

	public ArrayList<Venta> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(ArrayList<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}

	public int sumarTotalVentas() {
		int total = 0;
		for (Venta ventaActual : listaVentas) {
			total += ventaActual.getTotal();
		}
		return total;
	}

	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirSerializado(NOMBRE_ARCHIVO_SERIALIZADO, listaVentas);
	}

	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerSerializado(NOMBRE_ARCHIVO_SERIALIZADO);
		if (contenido == null) {
			listaVentas = new ArrayList<>();
		} else {
			listaVentas = (ArrayList<Venta>) contenido;
		}
	}
}