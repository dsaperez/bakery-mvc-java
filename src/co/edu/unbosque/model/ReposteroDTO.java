package co.edu.unbosque.model;

public class ReposteroDTO extends Empleado {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1043770115294208229L;
	private int nivelCreatividad;
	private boolean manejaTecnicasAvanzadas;// 
	
	public ReposteroDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReposteroDTO(int nivelCreatividad, boolean manejaTecnicasAvanzadas) {
		super();
		this.nivelCreatividad = nivelCreatividad;
		this.manejaTecnicasAvanzadas = manejaTecnicasAvanzadas;
	}

	public ReposteroDTO(String nombreCompleto, String genero, int edad, String numTelefono, float salario,
			int nivelCreatividad, boolean manejaTecnicasAvanzadas) {
		super(nombreCompleto, genero, edad, numTelefono, salario);
		this.nivelCreatividad = nivelCreatividad;
		this.manejaTecnicasAvanzadas = manejaTecnicasAvanzadas;
	}

	public ReposteroDTO(String nombreCompleto, String genero, int edad, String numTelefono, float salario) {
		super(nombreCompleto, genero, edad, numTelefono, salario);
		// TODO Auto-generated constructor stub
	}

	public int getNivelCreatividad() {
		return nivelCreatividad;
	}

	public void setNivelCreatividad(int nivelCreatividad) {
		this.nivelCreatividad = nivelCreatividad;
	}

	public boolean isManejaTecnicasAvanzadas() {
		return manejaTecnicasAvanzadas;
	}

	public void setManejaTecnicasAvanzadas(boolean manejaTecnicasAvanzadas) {
		this.manejaTecnicasAvanzadas = manejaTecnicasAvanzadas;
	}
	
	@Override
	public float calcularSalarioPorDia() {	
		return  getSalario()/30;
	}

}
