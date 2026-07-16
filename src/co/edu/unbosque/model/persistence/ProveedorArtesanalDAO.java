package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.ProveedorArtesanal;
import co.edu.unbosque.model.ProveedorArtesanalDTO;

public class ProveedorArtesanalDAO implements OperacionCRUD<ProveedorArtesanal, ProveedorArtesanalDTO> {

	private ArrayList<ProveedorArtesanal> listaProveedoresArtesanales;
	private final String NOMBRE_ARCHIVO = "proveedorArtesanal.csv";
	private final String NOMBRE_ARCHIVO_SERIALIZADO = "proveedorArtesanal.bin";

	public ProveedorArtesanalDAO() {
		listaProveedoresArtesanales = new ArrayList<ProveedorArtesanal>();
		//leerArchivoDeTexto();
		leerArchivoSerializado();

	}

	@Override
	public void crear(ProveedorArtesanalDTO nuevoDato) {
		listaProveedoresArtesanales.add(DataMapper.convertirArtesanalDTOAArtesanal(nuevoDato));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
	}

	@Override
	public String mostrar() {
		int posicion = 0;
		String contenido = "";
		for (ProveedorArtesanal proveedorArtesanal : listaProveedoresArtesanales) {
			contenido += posicion + "\n Proveedor Artesanal en la posicion : " + posicion + "\n";
			contenido += " Nombre de la Empresa : " + proveedorArtesanal.getNombreEmpresa() + "\n";
			contenido += " NIT de la Empresa : " + proveedorArtesanal.getNit() + "\n";
			contenido += " Ciudad : " + proveedorArtesanal.getCiudad() + "\n";
			contenido += " Telefono de la Empresa : " + proveedorArtesanal.getTelefono() + "\n";
			contenido += " Tipo producto : " + proveedorArtesanal.getTipoProducto() + "\n";
			contenido += " certificado INVIMA : " + (proveedorArtesanal.isCertificado() ? "si" : "no") + "\n";
			posicion++;
		}
		return contenido;
	}

	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaProveedoresArtesanales.size()) {// POSICIONES NO VALIDAS
			return false;
		} else {
			listaProveedoresArtesanales.remove(index);
			escribirArchivoDeTexto();
			escribirArchivoSerializado();
			return true;
		}
	}

	@Override
	public boolean actualizar(int index, ProveedorArtesanalDTO datoActualizado) {
		listaProveedoresArtesanales.set(index, DataMapper.convertirArtesanalDTOAArtesanal(datoActualizado));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public int contar() {
		return listaProveedoresArtesanales.size();
	}

	public ArrayList<ProveedorArtesanal> getListaProveedoresArtesanales() {
		return listaProveedoresArtesanales;
	}

	public void setListaProveedoresArtesanales(ArrayList<ProveedorArtesanal> listaProveedoresArtesanales) {
		this.listaProveedoresArtesanales = listaProveedoresArtesanales;
	}

	public String getNOMBRE_ARCHIVO() {
		return NOMBRE_ARCHIVO;
	}

	public String getNOMBRE_ARCHIVO_SERIALIZADO() {
		return NOMBRE_ARCHIVO_SERIALIZADO;
	}

	public void escribirArchivoDeTexto() {
		StringBuilder sb = new StringBuilder();
		for (ProveedorArtesanal proveedorArtesanal : listaProveedoresArtesanales) {
			sb.append(proveedorArtesanal.getNombreEmpresa() + ";");
			sb.append(proveedorArtesanal.getNit() + ";");
			sb.append(proveedorArtesanal.getCiudad() + ";");
			sb.append(proveedorArtesanal.getTelefono() + ";");
			sb.append(proveedorArtesanal.getTipoProducto() + ";");
			sb.append(proveedorArtesanal.isCertificado() + "\n");
		}
		FileHandler.crearYEscribirArchivo(NOMBRE_ARCHIVO, sb.toString());
	}

	public void leerArchivoDeTexto() {
		String contenido = FileHandler.crearYLeerArchivo(NOMBRE_ARCHIVO);
		if (!contenido.isBlank() || !contenido.isEmpty()) {
			String[] filas = contenido.split("\n");// SEPARACION DE FILAS
			for (String filaActual : filas) {
				String[] columnas = filaActual.split(";");
				ProveedorArtesanal nuevoArt = new ProveedorArtesanal();
				nuevoArt.setNombreEmpresa(columnas[0]);
				nuevoArt.setNit(Integer.parseInt(columnas[1]));
				nuevoArt.setCiudad(columnas[2]);
				nuevoArt.setTelefono(columnas[3]);
				nuevoArt.setTipoProducto(columnas[4]);
				nuevoArt.setCertificado(Boolean.parseBoolean(columnas[5]));
				listaProveedoresArtesanales.add(nuevoArt);
			}
		}
	}

	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirSerializado(NOMBRE_ARCHIVO_SERIALIZADO, listaProveedoresArtesanales);
	}

	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerSerializado(NOMBRE_ARCHIVO_SERIALIZADO);
		if (contenido == null) {
			listaProveedoresArtesanales = new ArrayList<>();
		} else {
			listaProveedoresArtesanales = (ArrayList<ProveedorArtesanal>) contenido;// CASTEO
		}
	}
	
	public String sumarCantidad() {
		String total = "";
		for (ProveedorArtesanal artActual : listaProveedoresArtesanales) {
			total += "\n- " + artActual.getNombreEmpresa();
		}
		return total;
	}
}
