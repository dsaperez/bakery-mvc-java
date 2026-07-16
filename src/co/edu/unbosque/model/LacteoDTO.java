package co.edu.unbosque.model;

import java.util.ArrayList;

public class LacteoDTO extends Producto implements TipoCaducacionLactea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1803263549239296270L;
	private String marca;
	private boolean tieneCambio;

	public LacteoDTO() {
		// TODO Auto-generated constructor stub
	}

	public LacteoDTO(String marca, boolean tieneCambio) {
		super();
		this.marca = marca;
		this.tieneCambio = tieneCambio;
	}

	public LacteoDTO(String nombre, float cantidad, int peso, int precio, String marca, boolean tieneCambio) {
		super(nombre, cantidad, peso, precio);
		this.marca = marca;
		this.tieneCambio = tieneCambio;
	}

	public LacteoDTO(String nombre, float cantidad, int peso, int precio) {
		super(nombre, cantidad, peso, precio);
		// TODO Auto-generated constructor stub
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public boolean isTieneCambio() {
		return tieneCambio;
	}

	public void setTieneCambio(boolean tieneCambio) {
		this.tieneCambio = tieneCambio;
	}

	@Override
	public String vencerse(int horasDesdeFabricacion) {
		if (horasDesdeFabricacion < 200) {
			return " Este Lacteo aun esta bueno";
		} else {
			return " Este Lacteo ya se vencio";
		}
	}

	@Override
	public String cortarse(int motivo) {
		switch (motivo) {
		case 1: {
			return " Su lacteo se ha cortado debido a las bacterias que descomponen la lactosa en ácido láctico.";
		}
		case 2: {
			return " Su lacteo se ha dañado debido a que las las altas temperaturas degrada los componentes eléctricos mediante la dilatación de materiales, la fusión de plásticos protectores y la oxidación acelerada.";
		}
		case 3: {
			return " Su lacteo se ha dañado debido a que se hizo un uso de utensilios sucios o dejar el empaque mal cerrado que permite que introduscan bacterias que aceleran la descomposición del lacteo.";
		}
		case 4: {
			return " Su lacteo se ha dañado por alguna razon desconocida ";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + motivo);
		}
	}

	@Override
	public String preparar(ArrayList<String> ingredientes) {
		String lacteoIngredientes = "La cantidad de ingredientes para preparar este Lacteo son : ";
		for (String string : ingredientes) {
			lacteoIngredientes += "\n" + string;
		}
		return lacteoIngredientes;
	}
}
