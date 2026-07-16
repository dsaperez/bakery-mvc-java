package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class PanelLacteo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel titulo;
	private JLabel labelNombre;
	private JLabel labelCantidad;
	private JLabel labelPeso;
	private JLabel labelPrecio;
	private JLabel labelMarca;

	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;
	private JButton boton6;
	private JButton boton7;
	private JButton boton8;
	private JButton boton9;
	private JButton boton10;

	private TextField campoTextoNombre;
	private TextField campoTextoCantidad;
	private TextField campoTextoPeso;
	private TextField campoTextoPrecio;
	private TextField campoTextoMarca;
	private JCheckBox campoTextoCambio;

	public PanelLacteo() {

		this.setBounds(0, 0, 800, 700);
		this.setBackground(new Color(189, 179, 143));
		setLayout(null);

		titulo = new JLabel("ADMINISTRACION DE LACTEOS");
		titulo.setBounds(250, 30, 400, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setForeground(new Color(133, 79, 11));
		add(titulo);

		labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(30, 110, 100, 20);
		add(labelNombre);

		campoTextoNombre = new TextField();
		campoTextoNombre.setBounds(30, 130, 150, 25);
		add(campoTextoNombre);

		labelCantidad = new JLabel("Cantidad:");
		labelCantidad.setBounds(200, 110, 100, 20);
		add(labelCantidad);

		campoTextoCantidad = new TextField();
		campoTextoCantidad.setBounds(200, 130, 150, 25);
		add(campoTextoCantidad);

		labelPeso = new JLabel("Peso (g):");
		labelPeso.setBounds(370, 110, 100, 20);
		add(labelPeso);

		campoTextoPeso = new TextField();
		campoTextoPeso.setBounds(370, 130, 150, 25);
		add(campoTextoPeso);

		labelPrecio = new JLabel("Precio:");
		labelPrecio.setBounds(540, 110, 100, 20);
		add(labelPrecio);

		campoTextoPrecio = new TextField();
		campoTextoPrecio.setBounds(540, 130, 150, 25);
		add(campoTextoPrecio);

		campoTextoCambio = new JCheckBox(" Tiene cambio? ");
		campoTextoCambio.setBounds(30, 170, 150, 25);
		campoTextoCambio.setBackground(new Color(189, 179, 143));
		add(campoTextoCambio);

		labelMarca = new JLabel("Marca del Lacteo:");
		labelMarca.setBounds(200, 165, 150, 20);
		add(labelMarca);

		campoTextoMarca = new TextField();
		campoTextoMarca.setBounds(200, 185, 150, 25);
		add(campoTextoMarca);

		boton1 = new JButton(" Registro ");
		boton1.setBounds(30, 240, 170, 50);
		boton1.setBackground(new Color(133, 79, 11));
		boton1.setForeground(Color.WHITE);
		boton1.putClientProperty("JButton.buttonType", "roundRect");
		add(boton1);

		boton2 = new JButton(" Eliminar ");
		boton2.setBounds(220, 240, 170, 50);
		boton2.setBackground(new Color(133, 79, 11));
		boton2.setForeground(Color.WHITE);
		boton2.putClientProperty("JButton.buttonType", "roundRect");
		add(boton2);

		boton3 = new JButton(" Actualizar ");
		boton3.setBounds(410, 240, 170, 50);
		boton3.setBackground(new Color(133, 79, 11));
		boton3.setForeground(Color.WHITE);
		boton3.putClientProperty("JButton.buttonType", "roundRect");
		add(boton3);

		boton4 = new JButton(" Lista ");
		boton4.setBounds(600, 240, 170, 50);
		boton4.setBackground(new Color(133, 79, 11));
		boton4.setForeground(Color.WHITE);
		boton4.putClientProperty("JButton.buttonType", "roundRect");
		add(boton4);

		boton5 = new JButton(" Contar ");
		boton5.setBounds(30, 310, 170, 50);
		boton5.setBackground(new Color(133, 79, 11));
		boton5.setForeground(Color.WHITE);
		boton5.putClientProperty("JButton.buttonType", "roundRect");
		add(boton5);

		boton6 = new JButton(" Volver ");
		boton6.setBounds(410, 380, 170, 50);
		boton6.setBackground(new Color(133, 79, 11));
		boton6.setForeground(Color.WHITE);
		boton6.putClientProperty("JButton.buttonType", "roundRect");
		add(boton6);

		boton7 = new JButton(" Verificar Vencimiento ");
		boton7.setBounds(410, 310, 170, 50);
		boton7.setBackground(new Color(133, 79, 11));
		boton7.setForeground(Color.WHITE);
		boton7.putClientProperty("JButton.buttonType", "roundRect");
		add(boton7);

		boton8 = new JButton(" Precio Total ");
		boton8.setBounds(600, 310, 170, 50);
		boton8.setBackground(new Color(133, 79, 11));
		boton8.setForeground(Color.WHITE);
		boton8.putClientProperty("JButton.buttonType", "roundRect");
		add(boton8);

		boton9 = new JButton(" Verificar Caducacion ");
		boton9.setBounds(220, 310, 170, 50);
		boton9.setBackground(new Color(133, 79, 11));
		boton9.setForeground(Color.WHITE);
		boton9.putClientProperty("JButton.buttonType", "roundRect");
		add(boton9);

		boton10 = new JButton(" Registro de Ingredientes ");
		boton10.setBounds(220, 380, 170, 50);
		boton10.setBackground(new Color(133, 79, 11));
		boton10.setForeground(Color.WHITE);
		boton10.putClientProperty("JButton.buttonType", "roundRect");
		add(boton10);
	}

	public void aplicarIdioma(Properties prop) {
		titulo.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.titulo"));
		labelNombre.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.labelNombre"));
		labelCantidad.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.labelCantidad"));
		labelPeso.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.labelPeso"));
		labelPrecio.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.labelPrecio"));
		labelMarca.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.labelMarca"));
		campoTextoCambio.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.checkCambio"));
		boton1.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.botonRegistro"));
		boton2.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.botonEliminar"));
		boton3.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.botonActualizar"));
		boton4.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.botonLista"));
		boton5.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.botonContar"));
		boton6.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.botonVolver"));
		boton7.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.botonVerificarVencimiento"));
		boton8.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.botonPrecioTotal"));
		boton9.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.botonVerificarCaducacion"));
		boton10.setText(prop.getProperty("panaderia.aplicativo.interfaz.lacteo.botonRegistroIngredientes"));
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

	public TextField getCampoTextoCantidad() {
		return campoTextoCantidad;
	}

	public void setCampoTextoCantidad(TextField campoTextoCantidad) {
		this.campoTextoCantidad = campoTextoCantidad;
	}

	public TextField getCampoTextoPeso() {
		return campoTextoPeso;
	}

	public void setCampoTextoPeso(TextField campoTextoPeso) {
		this.campoTextoPeso = campoTextoPeso;
	}

	public TextField getCampoTextoPrecio() {
		return campoTextoPrecio;
	}

	public void setCampoTextoPrecio(TextField campoTextoPrecio) {
		this.campoTextoPrecio = campoTextoPrecio;
	}

	public TextField getCampoTextoMarca() {
		return campoTextoMarca;
	}

	public void setCampoTextoMarca(TextField campoTextoMarca) {
		this.campoTextoMarca = campoTextoMarca;
	}

	public JCheckBox getCampoTextoCambio() {
		return campoTextoCambio;
	}

	public void setCampoTextoCambio(JCheckBox campoTextoCambio) {
		this.campoTextoCambio = campoTextoCambio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getBoton7() {
		return boton7;
	}

	public void setBoton7(JButton boton7) {
		this.boton7 = boton7;
	}

	public JButton getBoton8() {
		return boton8;
	}

	public void setBoton8(JButton boton8) {
		this.boton8 = boton8;
	}

	public JButton getBoton9() {
		return boton9;
	}

	public void setBoton9(JButton boton9) {
		this.boton9 = boton9;
	}

	public JButton getBoton10() {
		return boton10;
	}

	public void setBoton10(JButton boton10) {
		this.boton10 = boton10;
	}

}
