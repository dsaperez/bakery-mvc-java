package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelCliente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8106755723863503120L;

	private JLabel titulo;
	private JLabel texto;
	private JLabel labelBuscar;
	private TextField campoTextoLetras;
	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;
	private JButton botonBuscar;
	private JButton botonComprar;

	public PanelCliente() {
		this.setBounds(0, 0, 800, 700);
		this.setBackground(new Color(189, 179, 143));
		setLayout(null);

		titulo = new JLabel(" ELIJA UN PRODUCTO DE SU INTERES ");
		titulo.setBounds(200, 50, 550, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setForeground(new Color(133, 79, 11));
		add(titulo);

		texto = new JLabel(" ! Verifique la posicion del producto de su eleccion ¡ ");
		texto.setBounds(171, 110, 550, 30);
		texto.setForeground(new Color(133, 79, 11));
		add(texto);

		labelBuscar = new JLabel("Ingrese las letras a buscar el Producto:");
		labelBuscar.setBounds(100, 180, 200, 20);
		add(labelBuscar);

		campoTextoLetras = new TextField();
		campoTextoLetras.setBounds(100, 230, 200, 25);
		add(campoTextoLetras);

		boton1 = new JButton(" Comprar Pan ");
		boton1.setBounds(390, 180, 170, 50);
		boton1.setBackground(new Color(133, 79, 11));
		boton1.setForeground(Color.WHITE);
		boton1.putClientProperty("JButton.buttonType", "roundRect");
		add(boton1);

		boton2 = new JButton(" Comprar Postre ");
		boton2.setBounds(580, 260, 170, 50);
		boton2.setBackground(new Color(133, 79, 11));
		boton2.setForeground(Color.WHITE);
		boton2.putClientProperty("JButton.buttonType", "roundRect");
		add(boton2);

		boton3 = new JButton(" Comprar Galleta ");
		boton3.setBounds(390, 260, 170, 50);
		boton3.setBackground(new Color(133, 79, 11));
		boton3.setForeground(Color.WHITE);
		boton3.putClientProperty("JButton.buttonType", "roundRect");
		add(boton3);

		boton5 = new JButton(" Comprar Lacteo ");
		boton5.setBounds(580, 180, 170, 50);
		boton5.setBackground(new Color(133, 79, 11));
		boton5.setForeground(Color.WHITE);
		boton5.putClientProperty("JButton.buttonType", "roundRect");
		add(boton5);

		boton4 = new JButton(" Volver ");
		boton4.setBounds(70, 360, 280, 50);
		boton4.setBackground(new Color(133, 79, 11));
		boton4.setForeground(Color.WHITE);
		boton4.putClientProperty("JButton.buttonType", "roundRect");
		add(boton4);

		botonBuscar = new JButton("Buscar Producto");
		botonBuscar.setBounds(70, 280, 280, 50);
		botonBuscar.setBackground(new Color(133, 79, 11));
		botonBuscar.setForeground(Color.WHITE);
		botonBuscar.putClientProperty("JButton.buttonType", "roundRect");
		add(botonBuscar);

		botonComprar = new JButton("Finalizar Compra");
		botonComprar.setBounds(485, 340, 170, 50);
		botonComprar.setBackground(new Color(133, 79, 11));
		botonComprar.setForeground(Color.WHITE);
		botonComprar.putClientProperty("JButton.buttonType", "roundRect");
		add(botonComprar);
	}

	public void aplicarIdioma(Properties prop) {
		titulo.setText(prop.getProperty("panaderia.aplicativo.interfaz.cliente.titulo"));
		texto.setText(prop.getProperty("panaderia.aplicativo.interfaz.cliente.texto"));
		labelBuscar.setText(prop.getProperty("panaderia.aplicativo.interfaz.buscar.labelBuscar"));
		boton1.setText(prop.getProperty("panaderia.aplicativo.interfaz.cliente.botonComprarPan"));
		boton2.setText(prop.getProperty("panaderia.aplicativo.interfaz.cliente.botonComprarPostre"));
		boton3.setText(prop.getProperty("panaderia.aplicativo.interfaz.cliente.botonComprarGalleta"));
		boton5.setText(prop.getProperty("panaderia.aplicativo.interfaz.cliente.botonComprarLacteo"));
		boton4.setText(prop.getProperty("panaderia.aplicativo.interfaz.buscar.botonVolver"));
		botonBuscar.setText(prop.getProperty("panaderia.aplicativo.interfaz.cliente.botonBuscarProducto"));
		botonComprar.setText(prop.getProperty("panaderia.aplicativo.interfaz.cliente.botonFinalizarCompra"));
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getBoton5() {
		return boton5;
	}

	public void setBoton5(JButton boton5) {
		this.boton5 = boton5;
	}

	public JButton getBotonBuscar() {
		return botonBuscar;
	}

	public void setBotonBuscar(JButton botonBuscar) {
		this.botonBuscar = botonBuscar;
	}

	public TextField getCampoTextoLetras() {
		return campoTextoLetras;
	}

	public void setCampoTextoLetras(TextField campoTextoLetras) {
		this.campoTextoLetras = campoTextoLetras;
	}

	public JLabel getTitulo() {
		return titulo;
	}

	public void setTitulo(JLabel titulo) {
		this.titulo = titulo;
	}

	public JLabel getTexto() {
		return texto;
	}

	public void setTexto(JLabel texto) {
		this.texto = texto;
	}

	public JLabel getLabelBuscar() {
		return labelBuscar;
	}

	public void setLabelBuscar(JLabel labelBuscar) {
		this.labelBuscar = labelBuscar;
	}

	public JButton getBotonComprar() {
		return botonComprar;
	}

	public void setBotonComprar(JButton botonComprar) {
		this.botonComprar = botonComprar;
	}

}
