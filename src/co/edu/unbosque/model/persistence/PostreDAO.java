package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Postre;
import co.edu.unbosque.model.PostreDTO;

public class PostreDAO implements OperacionCRUD<Postre, PostreDTO> {

	private ArrayList<Postre> listaPostres;
	private final String NOMBRE_ARCHIVO = "postre.csv";
	private final String NOMBRE_ARCHIVO_SERIALIZADO = "postre.bin";

	public PostreDAO() {
		listaPostres = new ArrayList<Postre>();
		// leerArchivoDeTexto();
		leerArchivoSerializado();
	}

	@Override
	public void crear(PostreDTO nuevoDato) {
		listaPostres.add(DataMapper.convertirPostreDTOAPostre(nuevoDato));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();

	}

	@Override
	public String mostrar() {
		int posicion = 0;
		String contenido = "";// CARACTER NULL
		for (Postre postreActual : listaPostres) {
			contenido += posicion + "\n Postre en la posicion : " + posicion + "\n";
			contenido += " Nombre del postre : " + postreActual.getNombre() + "\n";
			contenido += " Cantidad de Postres : " + postreActual.getCantidad() + "\n";
			contenido += " Peso del postre en gramos : " + postreActual.getPeso() + "\n";
			contenido += " Precio del postre : " + postreActual.getPrecio() + "\n";
			contenido += " Tiene Azucar? : " + (postreActual.isTieneAzucar() ? "si" : "no") + "\n";
			contenido += " Tipo de postre " + postreActual.getTipoPostre() + "\n";
			posicion++;
		}
		return contenido;
	}

	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaPostres.size()) {// POSICIONES NO VALIDAS
			return false;
		} else {
			listaPostres.remove(index);
			escribirArchivoDeTexto();
			escribirArchivoSerializado();
			return true;
		}
	}

	@Override
	public boolean actualizar(int index, PostreDTO datoActualizado) {
		listaPostres.set(index, DataMapper.convertirPostreDTOAPostre(datoActualizado));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public int contar() {
		return listaPostres.size();
	}

	public String verificarVencimiento(int index, int horasDesdeFabricacion) {
		Postre postre = listaPostres.get(index);
		return postre.vencerse(horasDesdeFabricacion);
	}

	public String verificarDeterioro(int index, int motivo) {
		Postre postre = listaPostres.get(index);
		return postre.deteriorar(motivo);

	}

	public String prepararIngredientes(int index, ArrayList<String> ingredientes) {
		Postre postre = listaPostres.get(index);
		return postre.preparar(ingredientes);

	}

	public String buscarProducto(String letra) {
		String producto = "";
		float totalProducto = 0;
		int valorTotal = 0;
		String primeraLetra = letra.toLowerCase();

		for (Postre posActual : listaPostres) {
			if (posActual.getNombre().toLowerCase().startsWith(primeraLetra)) {
				producto += "\n Nombre: " + posActual.getNombre() + "\n";
				producto += " Precio: " + posActual.getPrecio() + "\n";
				producto += " Cantidad: " + posActual.getCantidad();
				totalProducto += posActual.getCantidad();
				valorTotal += posActual.getPrecio();
			}
		}

		if (producto.isEmpty()) {
			return "\n No se encontraron Postres con estas iniciales ";
		}

		producto += " \nNumero de productos encontrados de menu Postre : " + totalProducto;
		producto += " \n Valor total de los productos encontrados de menu Postre : " + valorTotal + "\n";
		return producto;
	}

	public String venderPostre(int index, float cantidad) {
		Postre postre = listaPostres.get(index);
		String resultado = postre.vender(cantidad);
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return resultado;
	}

	public ArrayList<Postre> getListaPostres() {
		return listaPostres;
	}

	public void setListaPostres(ArrayList<Postre> listaPostres) {
		this.listaPostres = listaPostres;
	}

	public String reporteListaCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre;Cantidad;Precio\n");
		for (Postre postre : listaPostres) {
			sb.append(postre.getNombre()).append(";");
			sb.append(postre.getCantidad()).append(";");
			sb.append(postre.getPrecio()).append("\n");
		}
		return sb.toString();
	}

	public void escribirArchivoDeTexto() {
		StringBuilder sb = new StringBuilder();// OBJETO DE JAVA QUE ES IGUAL QUE UN STRING PERO ES MUCHO MAS RAPIDO CON
												// LOS DATOS
		for (Postre postre : listaPostres) {
			sb.append(postre.getNombre() + ";");
			sb.append(postre.getCantidad() + ";");
			sb.append(postre.getPeso() + ";");
			sb.append(postre.getPrecio() + ";");
			sb.append(postre.isTieneAzucar() + ";");
			sb.append(postre.getTipoPostre() + "\n");
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
				Postre nuevoPostre = new Postre();// MODIFICAR POR CADA DATO
				nuevoPostre.setNombre(columnas[0]);
				nuevoPostre.setCantidad(Float.parseFloat(columnas[1]));
				nuevoPostre.setPeso(Integer.parseInt(columnas[2]));
				nuevoPostre.setPrecio(Integer.parseInt(columnas[3]));
				nuevoPostre.setTieneAzucar(Boolean.parseBoolean(columnas[4]));
				nuevoPostre.setTipoPostre(columnas[5]);
				listaPostres.add(nuevoPostre);

			}
		}
	}

	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirSerializado(NOMBRE_ARCHIVO_SERIALIZADO, listaPostres);
	}

	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerSerializado(NOMBRE_ARCHIVO_SERIALIZADO);
		if (contenido == null) {
			listaPostres = new ArrayList<>();
		} else {
			listaPostres = (ArrayList<Postre>) contenido;// CASTEO
		}
	}
	
	public int sumarCantidad() {
		int total = 0;
		for (Postre posActual : listaPostres) {
			total += posActual.getCantidad();
		}
		return total;
	}
}
