package co.edu.unbosque.model;

import java.io.Serializable;

public abstract class Proveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7351897245230088654L;
	private String nombreEmpresa;
	private int nit;
	private String ciudad;
	private String telefono;
	private String tipoProducto;
	private boolean certificado;

	public Proveedor() {
		// TODO Auto-generated constructor stub
	}

	public Proveedor(String nombreEmpresa, int nit, String ciudad, String telefono, String tipoProducto,
			boolean certificado) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.nit = nit;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.tipoProducto = tipoProducto;
		this.certificado = certificado;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public boolean isCertificado() {
		return certificado;
	}

	public void setCertificado(boolean certificado) {
		this.certificado = certificado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
