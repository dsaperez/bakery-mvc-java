package co.edu.unbosque.model;

public class Mesero extends Empleado{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7932634589291046615L;
	private boolean estaEstudianto;
	private boolean experienciaEventosGrandes;
	
	public Mesero() {
		// TODO Auto-generated constructor stub
	}

	public Mesero(boolean estaEstudianto, boolean experienciaEventosGrandes) {
		super();
		this.estaEstudianto = estaEstudianto;
		this.experienciaEventosGrandes = experienciaEventosGrandes;
	}

	public Mesero(String nombreCompleto, String genero, int edad, String numTelefono, float salario,
			boolean estaEstudianto, boolean experienciaEventosGrandes) {
		super(nombreCompleto, genero, edad, numTelefono, salario);
		this.estaEstudianto = estaEstudianto;
		this.experienciaEventosGrandes = experienciaEventosGrandes;
	}

	public Mesero(String nombreCompleto, String genero, int edad, String numTelefono, float salario) {
		super(nombreCompleto, genero, edad, numTelefono, salario);
		// TODO Auto-generated constructor stub
	}

	public boolean isEstaEstudianto() {
		return estaEstudianto;
	}

	public void setEstaEstudianto(boolean estaEstudianto) {
		this.estaEstudianto = estaEstudianto;
	}

	public boolean isExperienciaEventosGrandes() {
		return experienciaEventosGrandes;
	}

	public void setExperienciaEventosGrandes(boolean experienciaEventosGrandes) {
		this.experienciaEventosGrandes = experienciaEventosGrandes;
	}

	@Override
	public float calcularSalarioPorDia() {	
		return  getSalario()/30;
	}
	
	
	

}
