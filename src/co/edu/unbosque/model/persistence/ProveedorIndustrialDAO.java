package co.edu.unbosque.model.persistence;

import java.util.ArrayList;
import co.edu.unbosque.model.ProveedorIndustrial;
import co.edu.unbosque.model.ProveedorIndustrialDTO;

public class ProveedorIndustrialDAO implements OperacionCRUD<ProveedorIndustrial, ProveedorIndustrialDTO> {

	private ArrayList<ProveedorIndustrial> listaProveedoresIndustriales;
	private final String NOMBRE_ARCHIVO = "proveedorIndustrial.csv";
	private final String NOMBRE_ARCHIVO_SERIALIZADO = "proveedorIndustrial.bin";

	public ProveedorIndustrialDAO() {
		listaProveedoresIndustriales = new ArrayList<ProveedorIndustrial>();
		//leerArchivoDeTexto();
		leerArchivoSerializado();
	}

	@Override
	public void crear(ProveedorIndustrialDTO nuevoDato) {
		listaProveedoresIndustriales.add(DataMapper.convertirIndustriallDTOAIndustrial(nuevoDato));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
	}

	@Override
	public String mostrar() {
		int posicion = 0;
		String contenido = "";
		for (ProveedorIndustrial proveedorIndustrial : listaProveedoresIndustriales) {
			contenido += posicion + "\n Proveedor Artesanal en la posicion : " + posicion + "\n";
			contenido += " Nombre de la Empresa : " + proveedorIndustrial.getNombreEmpresa() + "\n";
			contenido += " NIT de la Empresa : " + proveedorIndustrial.getNit() + "\n";
			contenido += " Ciudad : " + proveedorIndustrial.getCiudad() + "\n";
			contenido += " Telefono de la Empresa : " + proveedorIndustrial.getTelefono() + "\n";
			contenido += " Tipo producto : " + proveedorIndustrial.getTipoProducto() + "\n";
			contenido += " certificado INVIMA : " + (proveedorIndustrial.isCertificado() ? "si" : "no") + "\n";
			posicion++;
		}
		return contenido;
	}

	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaProveedoresIndustriales.size()) {// POSICIONES NO VALIDAS
			return false;
		} else {
			listaProveedoresIndustriales.remove(index);
			escribirArchivoDeTexto();
			escribirArchivoSerializado();
			return true;
		}
	}

	@Override
	public boolean actualizar(int index, ProveedorIndustrialDTO datoActualizado) {
		listaProveedoresIndustriales.set(index, DataMapper.convertirIndustriallDTOAIndustrial(datoActualizado));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public int contar() {
		return listaProveedoresIndustriales.size();
	}

	public ArrayList<ProveedorIndustrial> getListaProveedoresIndustriales() {
		return listaProveedoresIndustriales;
	}

	public void setListaProveedoresIndustriales(ArrayList<ProveedorIndustrial> listaProveedoresIndustriales) {
		this.listaProveedoresIndustriales = listaProveedoresIndustriales;
	}

	public String getNOMBRE_ARCHIVO() {
		return NOMBRE_ARCHIVO;
	}

	public String getNOMBRE_ARCHIVO_SERIALIZADO() {
		return NOMBRE_ARCHIVO_SERIALIZADO;
	}

	public void escribirArchivoDeTexto() {
		StringBuilder sb = new StringBuilder();
		for (ProveedorIndustrial proveedorIndustrial : listaProveedoresIndustriales) {
			sb.append(proveedorIndustrial.getNombreEmpresa() + ";");
			sb.append(proveedorIndustrial.getNit() + ";");
			sb.append(proveedorIndustrial.getCiudad() + ";");
			sb.append(proveedorIndustrial.getTelefono() + ";");
			sb.append(proveedorIndustrial.getTipoProducto() + ";");
			sb.append(proveedorIndustrial.isCertificado() + "\n");
		}
		FileHandler.crearYEscribirArchivo(NOMBRE_ARCHIVO, sb.toString());
	}

	public void leerArchivoDeTexto() {
		String contenido = FileHandler.crearYLeerArchivo(NOMBRE_ARCHIVO);
		if (!contenido.isBlank() || !contenido.isEmpty()) {
			String[] filas = contenido.split("\n");// SEPARACION DE FILAS
			for (String filaActual : filas) {
				String[] columnas = filaActual.split(";");
				ProveedorIndustrial nuevoInd = new ProveedorIndustrial();
				nuevoInd.setNombreEmpresa(columnas[0]);
				nuevoInd.setNit(Integer.parseInt(columnas[1]));
				nuevoInd.setCiudad(columnas[2]);
				nuevoInd.setTelefono(columnas[3]);
				nuevoInd.setTipoProducto(columnas[4]);
				nuevoInd.setCertificado(Boolean.parseBoolean(columnas[5]));
				listaProveedoresIndustriales.add(nuevoInd);
			}
		}
	}

	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirSerializado(NOMBRE_ARCHIVO_SERIALIZADO, listaProveedoresIndustriales);
	}

	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerSerializado(NOMBRE_ARCHIVO_SERIALIZADO);
		if (contenido == null) {
			listaProveedoresIndustriales = new ArrayList<>();
		} else {
			listaProveedoresIndustriales = (ArrayList<ProveedorIndustrial>) contenido;// CASTEO
		}
	}
	
	public String sumarCantidad() {
		String total = "";
		for (ProveedorIndustrial indActual : listaProveedoresIndustriales) {
			total += "\n- " + indActual.getNombreEmpresa();
		}
		return total;
	}
}
