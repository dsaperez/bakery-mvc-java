package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Repostero;
import co.edu.unbosque.model.ReposteroDTO;

public class ReposteroDAO implements OperacionCRUD<Repostero, ReposteroDTO> {

	private ArrayList<Repostero> listaReposteros;
	private final String NOMBRE_ARCHIVO = "repostero.csv";
	private final String NOMBRE_ARCHIVO_SERIALIZADO = "repostero.bin";

	public ReposteroDAO() {
		listaReposteros = new ArrayList<>();
		//leerArchivoDeTexto();
		leerArchivoSerializado();
	}

	@Override
	public void crear(ReposteroDTO nuevoDato) {
		listaReposteros.add(DataMapper.convertirReposteroDTOARepostero(nuevoDato));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
	}

	@Override
	public String mostrar() {
		int posicion = 0;
		String contenido = "";// CARACTER NULL
		for (Repostero reposteroActual : listaReposteros) {
			contenido += posicion + "\n Repostero en la posicion : " + posicion + "\n";
			contenido += " Nombre : " + reposteroActual.getNombreCompleto() + "\n";
			contenido += " Genero : " + reposteroActual.getGenero() + "\n";
			contenido += " edad : " + reposteroActual.getEdad() + "\n";
			contenido += " numero telefonico : " + reposteroActual.getNumTelefono() + "\n";
			contenido += " salario : " + reposteroActual.getSalario() + "\n";
			contenido += " Nivel de creatividad (1 al 10) : " + reposteroActual.getNivelCreatividad() + "\n";
			contenido += " Maneja tecnicas avanzadas? " + (reposteroActual.isManejaTecnicasAvanzadas() ? "si" : "no")
					+ "\n";
			posicion++;
		}
		return contenido;
	}

	@Override
	public boolean eliminar(int index) {
		if (index < 0 || index >= listaReposteros.size()) {// POSICIONES NO VALIDAS
			return false;
		} else {
			listaReposteros.remove(index);
			escribirArchivoDeTexto();
			escribirArchivoSerializado();
			return true;
		}
	}

	@Override
	public boolean actualizar(int index, ReposteroDTO datoActualizado) {
		listaReposteros.set(index, DataMapper.convertirReposteroDTOARepostero(datoActualizado));
		escribirArchivoDeTexto();
		escribirArchivoSerializado();
		return true;
	}

	@Override
	public int contar() {
		return listaReposteros.size();
	}

	public float calcularSalarioPorDia(int index) {
		Repostero repostero = listaReposteros.get(index);
		return repostero.calcularSalarioPorDia();
	}

	public ArrayList<Repostero> getListaReposteros() {
		return listaReposteros;
	}

	public void setListaReposteros(ArrayList<Repostero> listaReposteros) {
		this.listaReposteros = listaReposteros;
	}
	

	public void escribirArchivoDeTexto() {
		StringBuilder sb = new StringBuilder();//OBJETO DE JAVA QUE ES IGUAL QUE UN STRING PERO ES MUCHO MAS RAPIDO CON LOS DATOS
		for (Repostero repo : listaReposteros) {
		sb.append(repo.getNombreCompleto() + ";");	
		sb.append(repo.getGenero() + ";");	
		sb.append(repo.getEdad() + ";");	
		sb.append(repo.getNumTelefono() + ";");	
		sb.append(repo.getSalario() + ";");	
		sb.append(repo.getNivelCreatividad() + ";");	
		sb.append(repo.isManejaTecnicasAvanzadas() + "\n");	
		}
		FileHandler.crearYEscribirArchivo(NOMBRE_ARCHIVO, sb.toString());//CONVIERTE EL OBJETO STRINGBUIDER A CADENA DE CARACTERES
	}
	
	public void leerArchivoDeTexto() {
		String contenido = FileHandler.crearYLeerArchivo(NOMBRE_ARCHIVO);
		if (!contenido.isBlank() || !contenido.isEmpty()) {
			String[] filas = contenido.split("\n");// SEPARACION DE FILAS
			for (String filaActual : filas) {
				String[] columnas = filaActual.split(";");
				Repostero nuevoRepo = new Repostero();// MODIFICAR POR CADA DATO
				nuevoRepo.setNombreCompleto(columnas[0]);
				nuevoRepo.setGenero(columnas[1]);
				nuevoRepo.setEdad(Integer.parseInt(columnas[2]));
				nuevoRepo.setNumTelefono(columnas[3]);
				nuevoRepo.setSalario(Float.parseFloat(columnas[4]));
				nuevoRepo.setNivelCreatividad(Integer.parseInt(columnas[5]));
				nuevoRepo.setManejaTecnicasAvanzadas(Boolean.parseBoolean(columnas[6]));
				listaReposteros.add(nuevoRepo);
			

			}
		}
	}
	
	public void escribirArchivoSerializado() {
		FileHandler.crearYEscribirSerializado(NOMBRE_ARCHIVO_SERIALIZADO, listaReposteros);
	}
	
	public void leerArchivoSerializado() {
		Object contenido = FileHandler.crearYLeerSerializado(NOMBRE_ARCHIVO_SERIALIZADO);
		if(contenido == null ) {
			listaReposteros = new ArrayList<>();
		} else {
			listaReposteros = (ArrayList<Repostero>) contenido;//CASTEO
		}
	}
	
	public String sumarCantidad() {
		String total = "";
		for (Repostero repActual : listaReposteros) {
			total += "\n- " + repActual.getNombreCompleto();
		}
		return total;
	}
}
