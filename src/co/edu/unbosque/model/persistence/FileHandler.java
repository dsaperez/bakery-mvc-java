package co.edu.unbosque.model.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {

	// ESTOS 3 SON PARA LOS ARCHIVOS DE TEXTO
	private static File archivo;
	private static Scanner lectorArchivo;
	private static PrintWriter escritorArchivo;
	// ESTOS SON PARA SERIALIZACION
	private static FileInputStream fis;// lienzo entrada datos del aplicativo = lectura
	private static ObjectInputStream ois;// pintor = escritor
	private static FileOutputStream fos;// lienzo entrada datos del aplicativo = lectura
	private static ObjectOutputStream oos;// pintor = escritor

	public static void crearYEscribirArchivo(String urlArchivo, String contenido) {

		try {
			archivo = new File(urlArchivo);// URL A LA CUAL ESTA APUNTANDO
			if (!archivo.exists()) {// SI NO EXISTE VA Y CREA UNA
				archivo.createNewFile();
			}
			escritorArchivo = new PrintWriter(archivo);// EL PINTOR PINTA EL LIENZO
			escritorArchivo.println(contenido);
			escritorArchivo.close();// EL PINTOR GUARDA SUS PINTURAS Y DEJA EL ARCHIVO LIBRE
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(" Error al crear el archivo de texto 😒 ");
			e.printStackTrace();
		}
	}

	public static String crearYLeerArchivo(String urlArchivo) {
		String contenido = "";
		try {
			archivo = new File(urlArchivo);
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
			lectorArchivo = new Scanner(archivo);
			while (lectorArchivo.hasNext()) { // SIGUE LEYENDO LAS LINEAS HASTA QUE LLEGUE A ESE CARACTER EOF
				contenido += lectorArchivo.nextLine() + "\n";
			}

		} catch (IOException e) {
			System.err.println(" Error al Leer el archivo de texto 😒 ");
			e.printStackTrace();
		}
		return contenido;
	}

	public static void crearYEscribirSerializado(String url, Object contenido) {
		try {
			archivo = new File(url);// URL A LA CUAL ESTA APUNTANDO
			if (!archivo.exists()) {// SI NO EXISTE VA Y CREA UNA
				archivo.createNewFile();
			}
			fos = new FileOutputStream(archivo);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(contenido);
			oos.close();
			fos.close();
		} catch (IOException e) {
			System.err.println(" Error al escribir el Archivo Serializado ");
			e.printStackTrace();
		}
	}

	public static Object crearYLeerSerializado(String url) {
		Object contenido = null;
		try {
			archivo = new File(url);// URL A LA CUAL ESTA APUNTANDO
			if (!archivo.exists()) {// SI NO EXISTE VA Y CREA UNA
				archivo.createNewFile();
			}
			fis = new FileInputStream(archivo);
			ois = new ObjectInputStream(fis);
			contenido = ois.readObject();
			ois.close();//PRIMERO QUITO EL PINTOR LUEGO EL LIENZO
			fis.close();
		} catch (IOException e) {
			System.err.println(" Error al Leer el Archivo Serializado ");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {//SERIALIZADO NO COINCIDE O LEE UN ARCHIVO DONDE NO EXISTE EN EL PROYECTO
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contenido;
	}
}
