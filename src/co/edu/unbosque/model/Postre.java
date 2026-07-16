package co.edu.unbosque.model;

import java.util.ArrayList;

public class Postre extends Producto implements CapacidadDeDeterioro {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4386269174967752162L;
	private boolean tieneAzucar;
	private String tipoPostre;

	public Postre() {
		// TODO Auto-generated constructor stub
	}

	public Postre(boolean tieneAzucar, String tipoPostre) {
		super();
		this.tieneAzucar = tieneAzucar;
		this.tipoPostre = tipoPostre;
	}

	public Postre(String nombre, float cantidad, int peso, int precio, boolean tieneAzucar, String tipoPostre) {
		super(nombre, cantidad, peso, precio);
		this.tieneAzucar = tieneAzucar;
		this.tipoPostre = tipoPostre;
	}

	public Postre(String nombre, float cantidad, int peso, int precio) {
		super(nombre, cantidad, peso, precio);
		// TODO Auto-generated constructor stub
	}

	public boolean isTieneAzucar() {
		return tieneAzucar;
	}

	public void setTieneAzucar(boolean tieneAzucar) {
		this.tieneAzucar = tieneAzucar;
	}

	public String getTipoPostre() {
		return tipoPostre;
	}

	public void setTipoPostre(String tipoPostre) {
		this.tipoPostre = tipoPostre;
	}

	@Override
	public String vencerse(int horasDesdeFabricacion) {
		if (horasDesdeFabricacion < 12) {
			return " Este Postre aun esta bueno ";
		} else {
			return " Este Postre ya esta vencido";
		}
	}

	@Override
	public String deteriorar(int motivo) {
		switch (motivo) {
		case 1: {
			return " Ingredientes como la leche o las cremas son altamente propensos a generar bacterias si no se refrigeran correctamente.";
		}
		case 2: {
			return "  Sucede cuando las grasas (mantequilla, nata) y el agua se separan, por ejemplo, al batir de más  o al descongelar un postre .";
		}
		case 3: {
			return " Los cambios drásticos de temperatura aceleran las reacciones químicas de descomposición  y provocan condensación (sudor) en pasteles fríos ";
		}
		case 4: {
			return "  Un exceso de levadura o abrir el horno antes de tiempo hace que el bizcocho se hunda o se desmorone .";
		}
		case 5: {
			return " Su Postre se ha dañado por alguna razon desconocida ";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + motivo);
		}
	}

	@Override
	public String preparar(ArrayList<String> ingredientes) {
		String postreIngredientes = "La cantidad de ingredientes para preparar este Postre son : ";
		for (String string : ingredientes) {
			postreIngredientes += "\n" + string;
		}
		return postreIngredientes;
	}
}
