package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Lacteo;
import co.edu.unbosque.model.LacteoDTO;

public class LacteoDAO implements OperacionCRUD<Lacteo, LacteoDTO> {

	private ArrayList<Lacteo> listaLacteos;
	private final String NOMBRE_ARCHIVO = "lacteo.csv";
	private final String NOMBRE_ARCHIVO_SERIALIZADO = "lacteo.bin";

	public LacteoDAO() {
		listaLacteos = new ArrayList<Lacteo>();
		// leerArchivoDeTexto();
		leerArchivoSerializado();
	}

	@Override
	public void crear(LacteoDTO nuevoDato) {
		listaLacteos.add(DataMapper.convertirLacteoDTOALacteo(nuevoDato));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();

	}

	@Override
	public String mostrar() {
		int posicion = 0;
		String contenido = "";// CARACTER NULL
		for (Lacteo lacteoActual : listaLacteos) {
			contenido += posicion + "\n Lacteo en la posicion : " + posicion + "\n";
			contenido += " Nombre del lacteo : " + lacteoActual.getNombre() + "\n";
			contenido += " cantidad de contenido : " + lacteoActual.getCantidad() + "\n";
			contenido += " Peso del lacteo : " + lacteoActual.getPeso() + "\n";
			contenido += " Precio del lacteo : " + lacteoActual.getPrecio() + "\n";
			contenido += " Marca del Lacteo : " + lacteoActual.getMarca() + "\n";
			contenido += " Tiene cambio? " + (lacteoActual.isTieneCambio() ? "si" : "no") + "\n";
			posicion++;
		}
		return contenido;
	}

	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaLacteos.size()) {// POSICIONES NO VALIDAS
			return false;
		} else {
			listaLacteos.remove(index);
			escribirArchivoDeTexto();
			escribirArchivoSerializado();
			return true;
		}
	}

	@Override
	public boolean actualizar(int index, LacteoDTO datoActualizado) {
		listaLacteos.set(index, DataMapper.convertirLacteoDTOALacteo(datoActualizado));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public int contar() {
		return listaLacteos.size();
	}

	public String verificarVencimiento(int index, int horasDesdeFabricacion) {
		Lacteo lacteo = listaLacteos.get(index);
		return lacteo.vencerse(horasDesdeFabricacion);
	}

	public String verificarCortamiento(int index, int motivo) {
		Lacteo lacteo = listaLacteos.get(index);
		return lacteo.cortarse(motivo);

	}

	public String prepararIngredientes(int index, ArrayList<String> ingredientes) {
		Lacteo lacteo = listaLacteos.get(index);
		return lacteo.preparar(ingredientes);

	}

	public String buscarProducto(String letra) {
		String producto = "";
		float totalProducto = 0;
		int valorTotal = 0;
		String primeraLetra = letra.toLowerCase();

		for (Lacteo lacActual : listaLacteos) {
			if (lacActual.getNombre().toLowerCase().startsWith(primeraLetra)) {
				producto += " \n Nombre: " + lacActual.getNombre() + "\n";
				producto += " Precio: " + lacActual.getPrecio() + "\n";
				producto += " Cantidad: " + lacActual.getCantidad();
				totalProducto += lacActual.getCantidad();
				valorTotal += lacActual.getPrecio();
			}
		}

		if (producto.isEmpty()) {
			return " \n No se encontraron Lacteos con estas iniciales ";
		}

		producto += "\n Numero de productos encontrados de menu Lacteo : " + totalProducto;
		producto += " \n Valor total de los productos encontrados de menu Lacteo : " + valorTotal + "\n";
		return producto;
	}

	public String venderLacteo(int index, float cantidad) {
		Lacteo lacteo = listaLacteos.get(index);
		String resultado = lacteo.vender(cantidad);
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return resultado;
	}

	public ArrayList<Lacteo> getListaLacteos() {
		return listaLacteos;
	}

	public void setListaLacteos(ArrayList<Lacteo> listaLacteos) {
		this.listaLacteos = listaLacteos;
	}

	public String reporteListaCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre;Cantidad;Precio\n");
		for (Lacteo lacteo : listaLacteos) {
			sb.append(lacteo.getNombre()).append(";");
			sb.append(lacteo.getCantidad()).append(";");
			sb.append(lacteo.getPrecio()).append("\n");
		}
		return sb.toString();
	}

	public void escribirArchivoDeTexto() {
		StringBuilder sb = new StringBuilder();// OBJETO DE JAVA QUE ES IGUAL QUE UN STRING PERO ES MUCHO MAS RAPIDO CON
												// LOS DATOS
		for (Lacteo lacteo : listaLacteos) {
			sb.append(lacteo.getNombre() + ";");
			sb.append(lacteo.getCantidad() + ";");
			sb.append(lacteo.getPeso() + ";");
			sb.append(lacteo.getPrecio() + ";");
			sb.append(lacteo.getMarca() + ";");
			sb.append(lacteo.isTieneCambio() + "\n");
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
				Lacteo nuevoLacteo = new Lacteo();// MODIFICAR POR CADA DATO
				nuevoLacteo.setNombre(columnas[0]);
				nuevoLacteo.setCantidad(Float.parseFloat(columnas[1]));
				nuevoLacteo.setPeso(Integer.parseInt(columnas[2]));
				nuevoLacteo.setPrecio(Integer.parseInt(columnas[3]));
				nuevoLacteo.setMarca(columnas[4]);
				nuevoLacteo.setTieneCambio(Boolean.parseBoolean(columnas[5]));
				listaLacteos.add(nuevoLacteo);

			}
		}
	}

	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirSerializado(NOMBRE_ARCHIVO_SERIALIZADO, listaLacteos);
	}

	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerSerializado(NOMBRE_ARCHIVO_SERIALIZADO);
		if (contenido == null) {
			listaLacteos = new ArrayList<>();
		} else {
			listaLacteos = (ArrayList<Lacteo>) contenido;// CASTEO
		}
	}

	public int sumarCantidad() {
		int total = 0;
		for (Lacteo lacteoActual : listaLacteos) {
			total += lacteoActual.getCantidad();
		}
		return total;
	}
}
