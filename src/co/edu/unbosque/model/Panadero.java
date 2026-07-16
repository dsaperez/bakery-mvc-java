package co.edu.unbosque.model;

public class Panadero extends Empleado {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6452453295644501230L;
	private int panesHorneadosPorDia;
	private String especialidadPan;
	
	public Panadero() {
		// TODO Auto-generated constructor stub
	}

	public Panadero(int panesHorneadosPorDia, String especialidadPan) {
		super();
		this.panesHorneadosPorDia = panesHorneadosPorDia;
		this.especialidadPan = especialidadPan;
	}

	public Panadero(String nombreCompleto, String genero, int edad, String numTelefono, float salario,
			int panesHorneadosPorDia, String especialidadPan) {
		super(nombreCompleto, genero, edad, numTelefono, salario);
		this.panesHorneadosPorDia = panesHorneadosPorDia;
		this.especialidadPan = especialidadPan;
	}

	public Panadero(String nombreCompleto, String genero, int edad, String numTelefono, float salario) {
		super(nombreCompleto, genero, edad, numTelefono, salario);
		// TODO Auto-generated constructor stub
	}

	public int getPanesHorneadosPorDia() {
		return panesHorneadosPorDia;
	}

	public void setPanesHorneadosPorDia(int panesHorneadosPorDia) {
		this.panesHorneadosPorDia = panesHorneadosPorDia;
	}

	public String getEspecialidadPan() {
		return especialidadPan;
	}

	public void setEspecialidadPan(String especialidadPan) {
		this.especialidadPan = especialidadPan;
	}
	
	@Override
	public float calcularSalarioPorDia() {	
		return  getSalario()/30;
	}
	

}
