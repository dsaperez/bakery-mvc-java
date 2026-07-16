package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Mesero;
import co.edu.unbosque.model.MeseroDTO;
import co.edu.unbosque.model.Pan;

public class MeseroDAO implements OperacionCRUD<Mesero, MeseroDTO> {

	private ArrayList<Mesero> listaMeseros;
	private final String NOMBRE_ARCHIVO = "mesero.csv";
	private final String NOMBRE_ARCHIVO_SERIALIZADO = "mesero.bin";

	public MeseroDAO() {
		listaMeseros = new ArrayList<>();
		//leerArchivoDeTexto();
		leerArchivoSerializado();
	}

	@Override
	public void crear(MeseroDTO nuevoDato) {
		listaMeseros.add(DataMapper.convertirMeseroDTOAMesero(nuevoDato));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
	}

	@Override
	public String mostrar() {
		int posicion = 0;
		String contenido = "";// CARACTER NULL
		for (Mesero meseroActual : listaMeseros) {
			contenido += posicion + "\n Mesero en la posicion : " + posicion + "\n";
			contenido += " Nombre : " + meseroActual.getNombreCompleto() + "\n";
			contenido += " Genero : " + meseroActual.getGenero() + "\n";
			contenido += " edad : " + meseroActual.getEdad() + "\n";
			contenido += " numero telefonico : " + meseroActual.getNumTelefono() + "\n";
			contenido += " salario : " + meseroActual.getSalario() + "\n";
			contenido += " Esta estudiando? " + (meseroActual.isEstaEstudianto() ? "si" : "no") + "\n";
			contenido += " Participacion en un evento grande? "
					+ (meseroActual.isExperienciaEventosGrandes() ? "si" : "no") + "\n";
			posicion++;
		}
		return contenido;
	}

	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaMeseros.size()) {// POSICIONES NO VALIDAS
			return false;
		} else {
			listaMeseros.remove(index);
			escribirArchivoDeTexto();
			escribirArchivoSerializado();
			return true;
		}
	}

	@Override
	public boolean actualizar(int index, MeseroDTO datoActualizado) {
		listaMeseros.set(index, DataMapper.convertirMeseroDTOAMesero(datoActualizado));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public int contar() {
		return listaMeseros.size();
	}

	public float calcularSalarioPorDia(int index) {
		Mesero mesero = listaMeseros.get(index);
		return mesero.calcularSalarioPorDia();
	}

	public ArrayList<Mesero> getListaMeseros() {
		return listaMeseros;
	}

	public void setListaMeseros(ArrayList<Mesero> listaMeseros) {
		this.listaMeseros = listaMeseros;
	}

	public void escribirArchivoDeTexto() {
		StringBuilder sb = new StringBuilder();// OBJETO DE JAVA QUE ES IGUAL QUE UN STRING PERO ES MUCHO MAS RAPIDO CON
												// LOS DATOS
		for (Mesero mesero : listaMeseros) {
			sb.append(mesero.getNombreCompleto() + ";");
			sb.append(mesero.getGenero() + ";");
			sb.append(mesero.getEdad() + ";");
			sb.append(mesero.getNumTelefono() + ";");
			sb.append(mesero.getSalario() + ";");
			sb.append(mesero.isEstaEstudianto() + ";");
			sb.append(mesero.isExperienciaEventosGrandes() + "\n");
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
				Mesero nuevoMesero = new Mesero();// MODIFICAR POR CADA DATO
				nuevoMesero.setNombreCompleto(columnas[0]);
				nuevoMesero.setGenero(columnas[1]);
				nuevoMesero.setEdad(Integer.parseInt(columnas[2]));
				nuevoMesero.setNumTelefono(columnas[3]);
				nuevoMesero.setSalario(Float.parseFloat(columnas[4]));
				nuevoMesero.setEstaEstudianto(Boolean.parseBoolean(columnas[5]));
				nuevoMesero.setExperienciaEventosGrandes(Boolean.parseBoolean(columnas[6]));
				listaMeseros.add(nuevoMesero);

			}
		}
	}
	
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirSerializado(NOMBRE_ARCHIVO_SERIALIZADO, listaMeseros);
	}
	
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerSerializado(NOMBRE_ARCHIVO_SERIALIZADO);
		if(contenido == null ) {
			listaMeseros = new ArrayList<>();
		} else {
			listaMeseros = (ArrayList<Mesero>) contenido;//CASTEO
		}
	}

	public String sumarCantidad() {
		String total = "";
		for (Mesero mesActual : listaMeseros) {
			total += "\n- " + mesActual.getNombreCompleto();
		}
		return total;
	}
}
