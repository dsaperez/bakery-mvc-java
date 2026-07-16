package co.edu.unbosque.model;

public class ProveedorArtesanalDTO extends Proveedor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3835493297064556549L;

	public ProveedorArtesanalDTO(String nombreEmpresa, int nit, String ciudad, String telefono, String tipoProducto,
			boolean certificado) {
		super(nombreEmpresa, nit, ciudad, telefono, tipoProducto, certificado);
	}

	public ProveedorArtesanalDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
