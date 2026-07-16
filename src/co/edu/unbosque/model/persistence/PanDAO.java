package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Pan;
import co.edu.unbosque.model.PanDTO;

public class PanDAO implements OperacionCRUD<Pan, PanDTO> {

	private ArrayList<Pan> listaPanes;
	private final String NOMBRE_ARCHIVO = "panes.csv";
	private final String NOMBRE_ARCHIVO_SERIALIZADO = "panes.bin";

	public PanDAO() {
		listaPanes = new ArrayList<Pan>();
		// leerArchivoDeTexto();
		leerArchivoSerializado();
	}

	@Override
	public void crear(PanDTO nuevoDato) {
		listaPanes.add(DataMapper.convertirPanDTOAPan(nuevoDato));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
	}

	@Override
	public String mostrar() {
		int posicion = 0;
		String contenido = "";// CARACTER NULL
		for (Pan panActual : listaPanes) {
			contenido += posicion + "\n Pan en la posicion : " + posicion + "\n";
			contenido += " Nombre del pan : " + panActual.getNombre() + "\n";
			contenido += " Ingrese la cantidad de pan " + panActual.getCantidad() + "\n";
			contenido += " Peso del pan en gramos : " + panActual.getPeso() + "\n";
			contenido += " Precio del pan : " + panActual.getPrecio() + "\n";
			contenido += " Tiene levadura? : " + (panActual.isTieneLevadura() ? "si" : "no") + "\n";// OPE.
																									// TERNARIO
			contenido += " Tipo Harina? : " + panActual.getTipoHarina() + "\n";// OPE. TERNARIO
			// LE SUMA 1 A LA POSICION : SIGNIFICA QUE YA NO ES CERO Y PASA A LA SIGUIENTE
			posicion++;
		}
		return contenido;
	}

	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaPanes.size()) {// POSICIONES NO VALIDAS
			return false;
		} else {
			listaPanes.remove(index);
			escribirArchivoDeTexto();
			escribirArchivoSerializado();
			return true;
		}
	}

	@Override
	public boolean actualizar(int index, PanDTO datoActualizado) {
		listaPanes.set(index, DataMapper.convertirPanDTOAPan(datoActualizado));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public int contar() {
		return listaPanes.size();
	}

	public String verificarVencimiento(int index, int horasDesdeFabricacion) {
		Pan pan = listaPanes.get(index);
		return pan.vencerse(horasDesdeFabricacion);
	}

	public String verificarEndurecimiento(int index, int horas) {
		Pan pan = listaPanes.get(index);
		return pan.endurecer(horas);

	}

	public String prepararIngredientes(int index, ArrayList<String> ingredientes) {
		Pan pan = listaPanes.get(index);
		return pan.preparar(ingredientes);

	}

	public String buscarProducto(String letra) {
		String producto = "";
		float totalProducto = 0;
		int valorTotal = 0;
		String primeraLetra = letra.toLowerCase();

		for (Pan panActual : listaPanes) {
			if (panActual.getNombre().toLowerCase().startsWith(primeraLetra)) {
				producto += "\n Nombre: " + panActual.getNombre() + "\n";
				producto += " Precio: " + panActual.getPrecio() + "\n";
				producto += " Cantidad: " + panActual.getCantidad();
				totalProducto += panActual.getCantidad();
				valorTotal += panActual.getPrecio();
			}
		}

		if (producto.isEmpty()) {
			return " \n No se encontraron Panes con estas iniciales ";
		}

		producto += "\n Numero de productos encontrados de menu Pan : " + totalProducto;
		producto += "\n Valor total de los productos encontrados de menu pan : " + valorTotal + "\n";
		return producto;
	}

	public String venderPan(int index, float cantidad) {
		Pan pan = listaPanes.get(index);
		String resultado = pan.vender(cantidad);
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return resultado;
	}

	public ArrayList<Pan> getListaPanes() {
		return listaPanes;
	}

	public void setListaPanes(ArrayList<Pan> listaPanes) {
		this.listaPanes = listaPanes;
	}

	public String reporteListaCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre;Cantidad;Precio\n");
		for (Pan pan : listaPanes) {
			sb.append(pan.getNombre()).append(";");
			sb.append(pan.getCantidad()).append(";");
			sb.append(pan.getPrecio()).append("\n");
		}
		return sb.toString();
	}

	public void escribirArchivoDeTexto() {
		StringBuilder sb = new StringBuilder();// OBJETO DE JAVA QUE ES IGUAL QUE UN STRING PERO ES MUCHO MAS RAPIDO CON
												// LOS DATOS
		for (Pan pan : listaPanes) {
			sb.append(pan.getNombre() + ";");
			sb.append(pan.getCantidad() + ";");
			sb.append(pan.getPeso() + ";");
			sb.append(pan.getPrecio() + ";");
			sb.append(pan.isTieneLevadura() + ";");
			sb.append(pan.getTipoHarina() + "\n");
		}
		FileHandler.crearYEscribirArchivo(NOMBRE_ARCHIVO, sb.toString());// CONVIERTE EL OBJETO STRINGBUIDER A CADENA DE
																			// CARACTERES
	}

	public void leerArchivoDeTexto() {
		String contenido = FileHandler.crearYLeerArchivo(NOMBRE_ARCHIVO);
		if (!contenido.isBlank() || !contenido.isEmpty()) {
			String[] filas = contenido.split("\n");// SEPARACION DE FILAS
			for (String filaActual : filas) {
				String[] columnas = filaActual.split(";");
				Pan nuevoPan = new Pan();// MODIFICAR POR CADA DATO
				nuevoPan.setNombre(columnas[0]);
				nuevoPan.setCantidad(Float.parseFloat(columnas[1]));
				nuevoPan.setPeso(Integer.parseInt(columnas[2]));
				nuevoPan.setPrecio(Integer.parseInt(columnas[3]));
				nuevoPan.setTieneLevadura(Boolean.parseBoolean(columnas[4]));
				nuevoPan.setTipoHarina(columnas[5]);
				listaPanes.add(nuevoPan);

			}
		}
	}

	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirSerializado(NOMBRE_ARCHIVO_SERIALIZADO, listaPanes);
	}

	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerSerializado(NOMBRE_ARCHIVO_SERIALIZADO);
		if (contenido == null) {
			listaPanes = new ArrayList<>();
		} else {
			listaPanes = (ArrayList<Pan>) contenido;// CASTEO
		}
	}

	public int sumarCantidad() {
		int total = 0;
		for (Pan panActual : listaPanes) {
			total += panActual.getCantidad();
		}
		return total;
	}
}
