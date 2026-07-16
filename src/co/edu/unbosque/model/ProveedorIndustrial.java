package co.edu.unbosque.model;

public class ProveedorIndustrial extends Proveedor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9110591383539553402L;

	public ProveedorIndustrial(String nombreEmpresa, int nit, String ciudad, String telefono, String tipoProducto,
			boolean certificado) {
		super(nombreEmpresa, nit, ciudad, telefono, tipoProducto, certificado);
	}

	public ProveedorIndustrial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
