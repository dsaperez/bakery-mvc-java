package co.edu.unbosque.model;

import java.util.ArrayList;

public class GalletaDTO extends Producto implements CapacidadAnejamiento {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6815203441015566420L;
	private boolean tieneTopings;
	private boolean tieneRelleno;

	public GalletaDTO() {
		// TODO Auto-generated constructor stub
	}

	public GalletaDTO(boolean tieneTopings, boolean tieneRelleno) {
		super();
		this.tieneTopings = tieneTopings;
		this.tieneRelleno = tieneRelleno;
	}

	public GalletaDTO(String nombre, float cantidad, int peso, int precio, boolean tieneTopings, boolean tieneRelleno) {
		super(nombre, cantidad, peso, precio);
		this.tieneTopings = tieneTopings;
		this.tieneRelleno = tieneRelleno;
	}

	public GalletaDTO(String nombre, float cantidad, int peso, int precio) {
		super(nombre, cantidad, peso, precio);
		// TODO Auto-generated constructor stub
	}

	public boolean isTieneTopings() {
		return tieneTopings;
	}

	public void setTieneTopings(boolean tieneTopings) {
		this.tieneTopings = tieneTopings;
	}

	public boolean isTieneRelleno() {
		return tieneRelleno;
	}

	public void setTieneRelleno(boolean tieneRelleno) {
		this.tieneRelleno = tieneRelleno;
	}

	@Override
	public String vencerse(int horasDesdeFabricacion) {
		if (horasDesdeFabricacion < 12) {
			return " Esta Galleta aun esta buena";
		} else {
			return " Esta Galleta ya se vencio ";
		}
	}

	@Override
	public String endurecer(int horas) {
		if (horas > 10) {
			return " Esta Galleta ya esta dura";
		} else {
			return " Esta Galleta aun no esta dura";
		}
	}

	@Override
	public String preparar(ArrayList<String> ingredientes) {
		String galletaIngredientes = "La cantidad de ingredientes para preparar esta Galleta son : ";
		for (String string : ingredientes) {
			galletaIngredientes += "\n" + string;
		}
		return galletaIngredientes;
	}

}
