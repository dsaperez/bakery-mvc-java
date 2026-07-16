package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelProveedorArtesanal extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5743265717425901471L;

	private JLabel titulo;
	private JLabel labelNombre;
	private JLabel labelNIT;
	private JLabel labelCiudad;
	private JLabel labelTelefono;
	private JLabel labelProducto;

	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;
	private JButton boton6;

	private TextField campoTextoNombre;
	private TextField campoTextoNIT;
	private TextField campoTextoCiudad;
	private TextField campoTextoTelefono;
	private TextField campoTextoProducto;
	private JCheckBox campoTextoInvima;

	public PanelProveedorArtesanal() {

		this.setBounds(0, 0, 800, 700);
		this.setBackground(new Color(189, 179, 143));
		setLayout(null);

		titulo = new JLabel("PROVEEDORES ARTESANALES");
		titulo.setBounds(230, 30, 400, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setForeground(new Color(133, 79, 11));
		add(titulo);

		labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(30, 110, 100, 20);
		add(labelNombre);

		campoTextoNombre = new TextField();
		campoTextoNombre.setBounds(30, 130, 150, 25);
		add(campoTextoNombre);

		labelNIT = new JLabel("NIT:");
		labelNIT.setBounds(200, 110, 100, 20);
		add(labelNIT);

		campoTextoNIT = new TextField();
		campoTextoNIT.setBounds(200, 130, 150, 25);
		add(campoTextoNIT);

		labelCiudad = new JLabel("Ciudad:");
		labelCiudad.setBounds(370, 110, 100, 20);
		add(labelCiudad);

		campoTextoCiudad = new TextField();
		campoTextoCiudad.setBounds(370, 130, 150, 25);
		add(campoTextoCiudad);

		labelTelefono = new JLabel("Numero Telefono:");
		labelTelefono.setBounds(540, 110, 150, 20);
		add(labelTelefono);

		campoTextoTelefono = new TextField();
		campoTextoTelefono.setBounds(540, 130, 150, 25);
		add(campoTextoTelefono);

		labelProducto = new JLabel("Producto:");
		labelProducto.setBounds(30, 170, 150, 20);
		add(labelProducto);

		campoTextoProducto = new TextField();
		campoTextoProducto.setBounds(30, 190, 150, 25);
		add(campoTextoProducto);

		campoTextoInvima = new JCheckBox("Registrado en el Invima?");
		campoTextoInvima.setBounds(200, 185, 150, 25);
		campoTextoInvima.setBackground(new Color(189, 179, 143));
		add(campoTextoInvima);

		boton1 = new JButton(" Registro ");
		boton1.setBounds(30, 250, 170, 50);
		boton1.setBackground(new Color(133, 79, 11));
		boton1.setForeground(Color.WHITE);
		boton1.putClientProperty("JButton.buttonType", "roundRect");
		add(boton1);

		boton2 = new JButton(" Eliminar ");
		boton2.setBounds(220, 250, 170, 50);
		boton2.setBackground(new Color(133, 79, 11));
		boton2.setForeground(Color.WHITE);
		boton2.putClientProperty("JButton.buttonType", "roundRect");
		add(boton2);

		boton3 = new JButton(" Actualizar ");
		boton3.setBounds(410, 250, 170, 50);
		boton3.setBackground(new Color(133, 79, 11));
		boton3.setForeground(Color.WHITE);
		boton3.putClientProperty("JButton.buttonType", "roundRect");
		add(boton3);

		boton4 = new JButton(" Lista ");
		boton4.setBounds(600, 250, 170, 50);
		boton4.setBackground(new Color(133, 79, 11));
		boton4.setForeground(Color.WHITE);
		boton4.putClientProperty("JButton.buttonType", "roundRect");
		add(boton4);

		boton5 = new JButton(" Contar ");
		boton5.setBounds(30, 320, 170, 50);
		boton5.setBackground(new Color(133, 79, 11));
		boton5.setForeground(Color.WHITE);
		boton5.putClientProperty("JButton.buttonType", "roundRect");
		add(boton5);

		boton6 = new JButton(" Volver ");
		boton6.setBounds(220, 320, 170, 50);
		boton6.setBackground(new Color(133, 79, 11));
		boton6.setForeground(Color.WHITE);
		boton6.putClientProperty("JButton.buttonType", "roundRect");
		add(boton6);
	}

	public void aplicarIdioma(Properties prop) {
		titulo.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.titulo"));
		labelNombre.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.labelNombre"));
		labelNIT.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.labelNIT"));
		labelCiudad.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.labelCiudad"));
		labelTelefono.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.labelTelefono"));
		labelProducto.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.labelProducto"));
		campoTextoInvima.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.checkInvima"));
		boton1.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.botonRegistro"));
		boton2.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.botonEliminar"));
		boton3.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.botonActualizar"));
		boton4.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.botonLista"));
		boton5.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.botonContar"));
		boton6.setText(prop.getProperty("panaderia.aplicativo.interfaz.proveedorArtesanal.botonVolver"));
	}

	public JButton getBoton1() {
		return boton1;
	}

	public void setBoton1(JButton boton1) {
		this.boton1 = boton1;
	}

	public JButton getBoton2() {
		return boton2;
	}

	public void setBoton2(JButton boton2) {
		this.boton2 = boton2;
	}

	public JButton getBoton3() {
		return boton3;
	}

	public void setBoton3(JButton boton3) {
		this.boton3 = boton3;
	}

	public JButton getBoton4() {
		return boton4;
	}

	public void setBoton4(JButton boton4) {
		this.boton4 = boton4;
	}

	public JButton getBoton5() {
		return boton5;
	}

	public void setBoton5(JButton boton5) {
		this.boton5 = boton5;
	}

	public JButton getBoton6() {
		return boton6;
	}

	public void setBoton6(JButton boton6) {
		this.boton6 = boton6;
	}

	public TextField getCampoTextoNombre() {
		return campoTextoNombre;
	}

	public void setCampoTextoNombre(TextField campoTextoNombre) {
		this.campoTextoNombre = campoTextoNombre;
	}

	public TextField getCampoTextoNIT() {
		return campoTextoNIT;
	}

	public void setCampoTextoNIT(TextField campoTextoNIT) {
		this.campoTextoNIT = campoTextoNIT;
	}

	public TextField getCampoTextoCiudad() {
		return campoTextoCiudad;
	}

	public void setCampoTextoCiudad(TextField campoTextoCiudad) {
		this.campoTextoCiudad = campoTextoCiudad;
	}

	public TextField getCampoTextoTelefono() {
		return campoTextoTelefono;
	}

	public void setCampoTextoTelefono(TextField campoTextoTelefono) {
		this.campoTextoTelefono = campoTextoTelefono;
	}

	public TextField getCampoTextoProducto() {
		return campoTextoProducto;
	}

	public void setCampoTextoProducto(TextField campoTextoProducto) {
		this.campoTextoProducto = campoTextoProducto;
	}

	public JCheckBox getCampoTextoInvima() {
		return campoTextoInvima;
	}

	public void setCampoTextoInvima(JCheckBox campoTextoInvima) {
		this.campoTextoInvima = campoTextoInvima;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
