package co.edu.unbosque.model;

public class ProveedorArtesanal extends Proveedor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3835493297064556549L;

	public ProveedorArtesanal(String nombreEmpresa, int nit, String ciudad, String telefono, String tipoProducto,
			boolean certificado) {
		super(nombreEmpresa, nit, ciudad, telefono, tipoProducto, certificado);
	}

	public ProveedorArtesanal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
