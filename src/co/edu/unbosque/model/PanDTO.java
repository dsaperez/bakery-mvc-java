package co.edu.unbosque.model;

import java.util.ArrayList;

public class PanDTO extends Producto implements CapacidadAnejamiento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3909397366880616614L;
	private boolean tieneLevadura;
	private String tipoHarina;

	public PanDTO() {
		// TODO Auto-generated constructor stub
	}

	public PanDTO(boolean tieneLevadura, String tipoHarina) {
		super();
		this.tieneLevadura = tieneLevadura;
		this.tipoHarina = tipoHarina;
	}

	public PanDTO(String nombre, float cantidad, int peso, int precio, boolean tieneLevadura, String tipoHarina) {
		super(nombre, cantidad, peso, precio);
		this.tieneLevadura = tieneLevadura;
		this.tipoHarina = tipoHarina;
	}

	public PanDTO(String nombre, float cantidad, int peso, int precio) {
		super(nombre, cantidad, peso, precio);
		// TODO Auto-generated constructor stub
	}

	public boolean isTieneLevadura() {
		return tieneLevadura;
	}

	public void setTieneLevadura(boolean tieneLevadura) {
		this.tieneLevadura = tieneLevadura;
	}

	public String getTipoHarina() {
		return tipoHarina;
	}

	public void setTipoHarina(String tipoHarina) {
		this.tipoHarina = tipoHarina;
	}

	@Override
	public String vencerse(int horasDesdeFabricacion) {// LLAMAR A SUPERPLASE Y ESA LOGICA SE EJECUTE
		if (horasDesdeFabricacion < 72) {
			return " Este Pan aun esta bueno";
		} else {
			return " Este Pan ya se vencio";
		}

	}

	@Override
	public String endurecer(int horas) {
		if (horas > 10) {
			return " Este pan ya se endurecio";
		} else {
			return " Este pan aun no esta duro";
		}
	}

	@Override
	public String preparar(ArrayList<String> ingredientes) {
		String panIngredientes = "La cantidad de ingredientes para preparar este Pan son : ";
		for (String string : ingredientes) {
			panIngredientes += "\n" + string;
		}
		return panIngredientes;
	}

}
