package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Panadero;
import co.edu.unbosque.model.PanaderoDTO;

public class PanaderoDAO implements OperacionCRUD<Panadero, PanaderoDTO> {

	private ArrayList<Panadero> listaPanaderos;
	private final String NOMBRE_ARCHIVO = "panadero.csv";
	private final String NOMBRE_ARCHIVO_SERIALIZADO = "panadero.bin";

	public PanaderoDAO() {
		listaPanaderos = new ArrayList<>();
		//leerArchivoDeTexto();
		leerArchivoSerializado();
	}

	@Override
	public void crear(PanaderoDTO nuevoDato) {
		listaPanaderos.add(DataMapper.convertirPanaderoDTOAPanadero(nuevoDato));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
	}

	@Override
	public String mostrar() {
		int posicion = 0;
		String contenido = "";// CARACTER NULL
		for (Panadero panaderoActual : listaPanaderos) {
			contenido += posicion + "\n Panadero en la posicion : " + posicion + "\n";
			contenido += " Nombre : " + panaderoActual.getNombreCompleto() + "\n";
			contenido += " Genero : " + panaderoActual.getGenero() + "\n";
			contenido += " edad : " + panaderoActual.getEdad() + "\n";
			contenido += " numero telefonico : " + panaderoActual.getNumTelefono() + "\n";
			contenido += " salario : " + panaderoActual.getSalario() + "\n";
			contenido += " Panes horneados por dia : " + panaderoActual.getPanesHorneadosPorDia() + "\n";
			contenido += " Especialidad pan : " + panaderoActual.getEspecialidadPan() + "\n";
			posicion++;
		}
		return contenido;
	}

	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaPanaderos.size()) {// POSICIONES NO VALIDAS
			return false;
		} else {
			listaPanaderos.remove(index);
			escribirArchivoDeTexto();
			escribirArchivoSerializado();
			return true;
		}
	}

	@Override
	public boolean actualizar(int index, PanaderoDTO datoActualizado) {
		listaPanaderos.set(index, DataMapper.convertirPanaderoDTOAPanadero(datoActualizado));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public int contar() {
		return listaPanaderos.size();
	}

	public float calcularSalarioPorDia(int index) {
		Panadero panadero = listaPanaderos.get(index);
		return panadero.calcularSalarioPorDia();
	}
	
	public ArrayList<Panadero> getListaPanaderos() {
		return listaPanaderos;
	}

	public void setListaPanaderos(ArrayList<Panadero> listaPanaderos) {
		this.listaPanaderos = listaPanaderos;
	}
	

	public void escribirArchivoDeTexto() {
		StringBuilder sb = new StringBuilder();//OBJETO DE JAVA QUE ES IGUAL QUE UN STRING PERO ES MUCHO MAS RAPIDO CON LOS DATOS
		for (Panadero pana : listaPanaderos) {
		sb.append(pana.getNombreCompleto() + ";");	
		sb.append(pana.getGenero() + ";");	
		sb.append(pana.getEdad() + ";");	
		sb.append(pana.getNumTelefono() + ";");	
		sb.append(pana.getSalario() + ";");	
		sb.append(pana.getPanesHorneadosPorDia() + ";");	
		sb.append(pana.getEspecialidadPan() + "\n");	
		}
		FileHandler.crearYEscribirArchivo(NOMBRE_ARCHIVO, sb.toString());//CONVIERTE EL OBJETO STRINGBUIDER A CADENA DE CARACTERES
	}
	
	public void leerArchivoDeTexto() {
		String contenido = FileHandler.crearYLeerArchivo(NOMBRE_ARCHIVO);
		if (!contenido.isBlank() || !contenido.isEmpty()) {
			String[] filas = contenido.split("\n");// SEPARACION DE FILAS
			for (String filaActual : filas) {
				String[] columnas = filaActual.split(";");
				Panadero nuevoPana = new Panadero();// MODIFICAR POR CADA DATO
				nuevoPana.setNombreCompleto(columnas[0]);
				nuevoPana.setGenero(columnas[1]);
				nuevoPana.setEdad(Integer.parseInt(columnas[2]));
				nuevoPana.setNumTelefono(columnas[3]);
				nuevoPana.setSalario(Float.parseFloat(columnas[4]));
				nuevoPana.setPanesHorneadosPorDia(Integer.parseInt(columnas[5]));
				nuevoPana.setEspecialidadPan(columnas[6]);
				listaPanaderos.add(nuevoPana);			

			}
		}
	}
	
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirSerializado(NOMBRE_ARCHIVO_SERIALIZADO, listaPanaderos);
	}
	
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerSerializado(NOMBRE_ARCHIVO_SERIALIZADO);
		if(contenido == null ) {
			listaPanaderos = new ArrayList<>();
		} else {
			listaPanaderos = (ArrayList<Panadero>) contenido;//CASTEO
		}
	}
	
	public String sumarCantidad() {
		String total = "";
		for (Panadero panaActual : listaPanaderos) {
			total += "\n- " + panaActual.getNombreCompleto();
		}
		return total;
	}

}
