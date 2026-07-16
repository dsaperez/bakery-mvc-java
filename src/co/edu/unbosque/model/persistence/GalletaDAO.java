package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Galleta;
import co.edu.unbosque.model.GalletaDTO;

public class GalletaDAO implements OperacionCRUD<Galleta, GalletaDTO> {

	private ArrayList<Galleta> listaGalletas;
	private final String NOMBRE_ARCHIVO = "galleta.csv";
	private final String NOMBRE_ARCHIVO_SERIALIZADO = "galleta.bin";

	public GalletaDAO() {
		listaGalletas = new ArrayList<Galleta>();
		//leerArchivoDeTexto();
		leerArchivoSerializado();
		
	}

	@Override
	public void crear(GalletaDTO nuevoDato) {
		listaGalletas.add(DataMapper.convertirGalletaDTOAGalleta(nuevoDato));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
	}

	@Override
	public String mostrar() {
		int posicion = 0;
		String contenido = "";// CARACTER NULL
		for (Galleta galletaActual : listaGalletas) {
			contenido += posicion + "\n Galleta en la posicion : " + posicion + "\n";
			contenido += " Nombre de la galleta : " + galletaActual.getNombre() + "\n";
			contenido += " cantidad de contenido : " + galletaActual.getCantidad() + "\n";
			contenido += " Peso de la galleta en gramos : " + galletaActual.getPeso() + "\n";
			contenido += " Precio de la galleta : " + galletaActual.getPrecio() + "\n";
			contenido += " Tiene topings? : " + (galletaActual.isTieneTopings() ? "si" : "no") + "\n";
			contenido += " Tiene relleno? : " + (galletaActual.isTieneRelleno() ? "si" : "no") + "\n";
			posicion++;
		}
		return contenido;
	}

	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaGalletas.size()) {// POSICIONES NO VALIDAS
			return false;
		} else {
			listaGalletas.remove(index);
			escribirArchivoDeTexto();
			escribirArchivoSerializado();
			return true;
		}
	}

	@Override
	public boolean actualizar(int index, GalletaDTO datoActualizado) {
		listaGalletas.set(index, DataMapper.convertirGalletaDTOAGalleta(datoActualizado));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public int contar() {
		return listaGalletas.size();
	}

	public String verificarVencimiento(int index, int horasDesdeFabricacion) {
		Galleta galleta = listaGalletas.get(index);
		return galleta.vencerse(horasDesdeFabricacion);
	}

	public String verificarEndurecimiento(int index, int horas) {
		Galleta galleta = listaGalletas.get(index);
		return galleta.endurecer(horas);

	}

	public String prepararIngredientes(int index, ArrayList<String> ingredientes) {
		Galleta galleta = listaGalletas.get(index);
		return galleta.preparar(ingredientes);

	}

	public String buscarProducto(String letra) {
		String producto = "";
		float totalProducto = 0;
		int valorTotal = 0;
		String primeraLetra = letra.toLowerCase();

		for (Galleta galActual : listaGalletas) {
			if (galActual.getNombre().toLowerCase().startsWith(primeraLetra)) {
				producto += " \n Nombre: " + galActual.getNombre() + "\n";
				producto += " Precio: " + galActual.getPrecio() + "\n";
				producto += " Cantidad: " + galActual.getCantidad();
				totalProducto += galActual.getCantidad();
				valorTotal += galActual.getPrecio();
			}
		}

		if (producto.isEmpty()) {
			return " \n No se encontraron Galletas con estas iniciales ";
		}

		producto += "\n Numero de productos encontrados de menu Galleta : " + totalProducto;
		producto += " \n Valor total de los productos encontrados de menu Galleta : " + valorTotal + "\n";
		return producto;
	}
	
	public String venderGalleta(int index, float cantidad) {
	    Galleta galleta = listaGalletas.get(index);
	    String resultado = galleta.vender(cantidad);
	    escribirArchivoDeTexto();
	    escribirArchivoSerializado();
		return resultado;
	}


	public ArrayList<Galleta> getListaGalletas() {
		return listaGalletas;
	}

	public void setListaGalletas(ArrayList<Galleta> listaGalletas) {
		this.listaGalletas = listaGalletas;
	}
	
	public String reporteListaCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre;Cantidad;Precio\n");
		for (Galleta galleta : listaGalletas) {
			sb.append(galleta.getNombre()).append(";");
			sb.append(galleta.getCantidad()).append(";");
			sb.append(galleta.getPrecio()).append("\n");
		}
		return sb.toString();
	}

	public void escribirArchivoDeTexto() {
		StringBuilder sb = new StringBuilder();// OBJETO DE JAVA QUE ES IGUAL QUE UN STRING PERO ES MUCHO MAS RAPIDO CON
												// LOS DATOS
		for (Galleta galleta : listaGalletas) {
			sb.append(galleta.getNombre() + ";");
			sb.append(galleta.getCantidad() + ";");
			sb.append(galleta.getPeso() + ";");
			sb.append(galleta.getPrecio() + ";");
			sb.append(galleta.isTieneTopings() + ";");
			sb.append(galleta.isTieneRelleno() + "\n");
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
				Galleta nuevaGalleta = new Galleta();// MODIFICAR POR CADA DATO
				nuevaGalleta.setNombre(columnas[0]);
				nuevaGalleta.setCantidad(Float.parseFloat(columnas[1]));
				nuevaGalleta.setPeso(Integer.parseInt(columnas[2]));
				nuevaGalleta.setPrecio(Integer.parseInt(columnas[3]));
				nuevaGalleta.setTieneTopings(Boolean.parseBoolean(columnas[4]));
				nuevaGalleta.setTieneRelleno(Boolean.parseBoolean(columnas[5]));
				listaGalletas.add(nuevaGalleta);

			}
		}
	}
	
	
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirSerializado(NOMBRE_ARCHIVO_SERIALIZADO, listaGalletas);
	}
	
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerSerializado(NOMBRE_ARCHIVO_SERIALIZADO);
		if(contenido == null ) {
			listaGalletas = new ArrayList<>();
		} else {
			listaGalletas = (ArrayList<Galleta>) contenido;//CASTEO
		}
	}

	public int sumarCantidad() {
		int total = 0;
		for (Galleta galletaActual : listaGalletas) {
			total +=  galletaActual.getCantidad();
		}
		return total;
	}

}
