package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel titulo;

	private JButton botonPan;
	private JButton botonPostre;
	private JButton botonGalleta;
	private JButton botonLacteo;
	private JButton botonTotalInventario;
	private JButton botonPanadero;
	private JButton botonRepostero;
	private JButton botonMesero;
	private JButton botonBuscar;
	private JButton botonSalir;
	private JButton botonReporte;
	private JButton botonArtesanal;
	private JButton botonIndustrial;
	private JButton botonVerVentas;

	public PanelMenu() {
		this.setBounds(0, 0, 800, 700);
		this.setBackground(new Color(189, 179, 143));
		setLayout(null);

		titulo = new JLabel(" AREA ADMINISTRATIVA DE LA PANADERIA CONNOR  ");
		titulo.setBounds(150, 50, 550, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setForeground(new Color(133, 79, 11));
		add(titulo);

		botonPan = new JButton("Administrar Panes");
		botonPan.setBounds(80, 150, 280, 50);
		botonPan.setBackground(new Color(133, 79, 11));
		botonPan.setForeground(Color.WHITE);
		botonPan.putClientProperty("JButton.buttonType", "roundRect");
		add(botonPan);

		botonPostre = new JButton("Administrar Postres");
		botonPostre.setBounds(80, 230, 280, 50);
		botonPostre.setBackground(new Color(133, 79, 11));
		botonPostre.setForeground(Color.WHITE);
		botonPostre.putClientProperty("JButton.buttonType", "roundRect");
		add(botonPostre);

		botonGalleta = new JButton("Administrar Galletas");
		botonGalleta.setBounds(80, 310, 280, 50);
		botonGalleta.setBackground(new Color(133, 79, 11));
		botonGalleta.setForeground(Color.WHITE);
		botonGalleta.putClientProperty("JButton.buttonType", "roundRect");
		add(botonGalleta);

		botonLacteo = new JButton("Administrar Lacteos");
		botonLacteo.setBounds(80, 390, 280, 50);
		botonLacteo.setBackground(new Color(133, 79, 11));
		botonLacteo.setForeground(Color.WHITE);
		botonLacteo.putClientProperty("JButton.buttonType", "roundRect");
		add(botonLacteo);

		botonTotalInventario = new JButton("Valor Total Inventario");
		botonTotalInventario.setBounds(440, 470, 280, 50);
		botonTotalInventario.setBackground(new Color(133, 79, 11));
		botonTotalInventario.setForeground(Color.WHITE);
		botonTotalInventario.putClientProperty("JButton.buttonType", "roundRect");
		add(botonTotalInventario);

		botonPanadero = new JButton("Administrar Panadero");
		botonPanadero.setBounds(440, 230, 280, 50);
		botonPanadero.setBackground(new Color(133, 79, 11));
		botonPanadero.setForeground(Color.WHITE);
		botonPanadero.putClientProperty("JButton.buttonType", "roundRect");
		add(botonPanadero);

		botonRepostero = new JButton("Administrar Repostero");
		botonRepostero.setBounds(440, 310, 280, 50);
		botonRepostero.setBackground(new Color(133, 79, 11));
		botonRepostero.setForeground(Color.WHITE);
		botonRepostero.putClientProperty("JButton.buttonType", "roundRect");
		add(botonRepostero);

		botonMesero = new JButton("Administrar Mesero");
		botonMesero.setBounds(440, 390, 280, 50);
		botonMesero.setBackground(new Color(133, 79, 11));
		botonMesero.setForeground(Color.WHITE);
		botonMesero.putClientProperty("JButton.buttonType", "roundRect");
		add(botonMesero);

		botonBuscar = new JButton("Buscar Producto");
		botonBuscar.setBounds(80, 470, 280, 50);
		botonBuscar.setBackground(new Color(133, 79, 11));
		botonBuscar.setForeground(Color.WHITE);
		botonBuscar.putClientProperty("JButton.buttonType", "roundRect");
		add(botonBuscar);

		botonReporte = new JButton(" Reporte Excel y PDF");
		botonReporte.setBounds(440, 150, 280, 50);
		botonReporte.setBackground(new Color(133, 79, 11));
		botonReporte.setForeground(Color.WHITE);
		botonReporte.putClientProperty("JButton.buttonType", "roundRect");
		add(botonReporte);

		botonSalir = new JButton("Salir");
		botonSalir.setBounds(440, 630, 280, 50);
		botonSalir.setBackground(new Color(133, 79, 11));
		botonSalir.setForeground(Color.WHITE);
		botonSalir.putClientProperty("JButton.buttonType", "roundRect");
		add(botonSalir);

		botonVerVentas = new JButton("Historial de Ventas");
		botonVerVentas.setBounds(80, 630, 280, 50);
		botonVerVentas.setBackground(new Color(133, 79, 11));
		botonVerVentas.setForeground(Color.WHITE);
		botonVerVentas.putClientProperty("JButton.buttonType", "roundRect");
		add(botonVerVentas);

		botonArtesanal = new JButton("Proveedor Artesanal");
		botonArtesanal.setBounds(80, 550, 280, 50);
		botonArtesanal.setBackground(new Color(133, 79, 11));
		botonArtesanal.setForeground(Color.WHITE);
		botonArtesanal.putClientProperty("JButton.buttonType", "roundRect");
		add(botonArtesanal);

		botonIndustrial = new JButton("Proveedor Industrial");
		botonIndustrial.setBounds(440, 550, 280, 50);
		botonIndustrial.setBackground(new Color(133, 79, 11));
		botonIndustrial.setForeground(Color.WHITE);
		botonIndustrial.putClientProperty("JButton.buttonType", "roundRect");
		add(botonIndustrial);
	}

	public void aplicarIdioma(Properties prop) {
		titulo.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.titulo"));
		botonPan.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonPan"));
		botonPostre.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonPostre"));
		botonGalleta.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonGalleta"));
		botonLacteo.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonLacteo"));
		botonTotalInventario.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonTotalInventario"));
		botonPanadero.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonPanadero"));
		botonRepostero.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonRepostero"));
		botonMesero.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonMesero"));
		botonBuscar.setText(prop.getProperty("panaderia.aplicativo.interfaz.cliente.botonBuscarProducto"));
		botonSalir.setText(prop.getProperty("panaderia.aplicativo.interfaz.administrativo.botonSalir"));
		botonReporte.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonReporte"));
		botonArtesanal.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonArtesanal"));
		botonIndustrial.setText(prop.getProperty("panaderia.aplicativo.interfaz.menu.botonIndustrial"));
		botonVerVentas.setText(prop.getProperty("panaderia.aplicativo.interfaz.administrativo.botonHistorialVentas"));
	}

	public JButton getBotonPan() {
		return botonPan;
	}

	public void setBotonPan(JButton botonPan) {
		this.botonPan = botonPan;
	}

	public JButton getBotonPostre() {
		return botonPostre;
	}

	public void setBotonPostre(JButton botonPostre) {
		this.botonPostre = botonPostre;
	}

	public JButton getBotonGalleta() {
		return botonGalleta;
	}

	public void setBotonGalleta(JButton botonGalleta) {
		this.botonGalleta = botonGalleta;
	}

	public JButton getBotonLacteo() {
		return botonLacteo;
	}

	public void setBotonLacteo(JButton botonLacteo) {
		this.botonLacteo = botonLacteo;
	}

	public JButton getBotonTotalInventario() {
		return botonTotalInventario;
	}

	public JButton getBotonPanadero() {
		return botonPanadero;
	}

	public void setBotonPanadero(JButton botonPanadero) {
		this.botonPanadero = botonPanadero;
	}

	public JButton getBotonRepostero() {
		return botonRepostero;
	}

	public void setBotonRepostero(JButton botonRepostero) {
		this.botonRepostero = botonRepostero;
	}

	public JButton getBotonMesero() {
		return botonMesero;
	}

	public void setBotonMesero(JButton botonMesero) {
		this.botonMesero = botonMesero;
	}

	public JButton getBotonSalir() {
		return botonSalir;
	}

	public void setBotonSalir(JButton botonSalir) {
		this.botonSalir = botonSalir;
	}

	public void setBotonTotalInventario(JButton botonTotalInventario) {
		this.botonTotalInventario = botonTotalInventario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getBotonBuscar() {
		return botonBuscar;
	}

	public void setBotonBuscar(JButton botonBuscar) {
		this.botonBuscar = botonBuscar;
	}

	public JButton getBotonReporte() {
		return botonReporte;
	}

	public void setBotonReporte(JButton botonReporte) {
		this.botonReporte = botonReporte;
	}

	public JButton getBotonArtesanal() {
		return botonArtesanal;
	}

	public void setBotonArtesanal(JButton botonArtesanal) {
		this.botonArtesanal = botonArtesanal;
	}

	public JButton getBotonIndustrial() {
		return botonIndustrial;
	}

	public void setBotonIndustrial(JButton botonIndustrial) {
		this.botonIndustrial = botonIndustrial;
	}

	public JLabel getTitulo() {
		return titulo;
	}

	public void setTitulo(JLabel titulo) {
		this.titulo = titulo;
	}

	public JButton getBotonVerVentas() {
		return botonVerVentas;
	}

	public void setBotonVerVentas(JButton botonVerVentas) {
		this.botonVerVentas = botonVerVentas;
	}

}
