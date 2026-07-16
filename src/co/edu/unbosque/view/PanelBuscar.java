package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBuscar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel titulo;
	private JLabel labelBuscar;
	private TextField campoTextoLetras;
	private JButton boton1;
	private JButton boton2;

	public PanelBuscar() {

		this.setBounds(0, 0, 800, 700);
		this.setBackground(new Color(189, 179, 143));
		setLayout(null);

		titulo = new JLabel("BUSCAR PRODUCTO");
		titulo.setBounds(300, 50, 400, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setForeground(new Color(133, 79, 11));
		add(titulo);

		labelBuscar = new JLabel("Ingrese las letras a buscar el Producto:");
		labelBuscar.setBounds(300, 110, 320, 20);
		add(labelBuscar);

		campoTextoLetras = new TextField();
		campoTextoLetras.setBounds(300, 130, 200, 25);
		add(campoTextoLetras);

		boton1 = new JButton("Buscar");
		boton1.setBounds(315, 200, 170, 50);
		boton1.setBackground(new Color(133, 79, 11));
		boton1.setForeground(Color.WHITE);
		boton1.putClientProperty("JButton.buttonType", "roundRect");
		add(boton1);

		boton2 = new JButton("Volver");
		boton2.setBounds(315, 280, 170, 50);
		boton2.setBackground(new Color(133, 79, 11));
		boton2.setForeground(Color.WHITE);
		boton2.putClientProperty("JButton.buttonType", "roundRect");
		add(boton2);
	}

	public void aplicarIdioma(Properties prop) {
		titulo.setText(prop.getProperty("panaderia.aplicativo.interfaz.buscar.titulo"));
		labelBuscar.setText(prop.getProperty("panaderia.aplicativo.interfaz.buscar.labelBuscar"));
		boton1.setText(prop.getProperty("panaderia.aplicativo.interfaz.buscar.botonBuscar"));
		boton2.setText(prop.getProperty("panaderia.aplicativo.interfaz.buscar.botonVolver"));
	}

	public TextField getCampoTextoLetras() {
		return campoTextoLetras;
	}

	public void setCampoTextoLetras(TextField campoTextoLetras) {
		this.campoTextoLetras = campoTextoLetras;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
