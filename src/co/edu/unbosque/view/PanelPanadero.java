package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPanadero extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel titulo;
	private JLabel labelNombre;
	private JLabel labelGenero;
	private JLabel labelEdad;
	private JLabel labelTelefono;
	private JLabel labelSalario;
	private JLabel labelDia;
	private JLabel labelEspecialidad;

	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;
	private JButton boton6;
	private JButton boton7;

	private TextField campoTextoNombre;
	private TextField campoTextoGenero;
	private TextField campoTextoEdad;
	private TextField campoTextoTelefono;
	private TextField campoTextoSalario;
	private TextField campoTextoDia;
	private TextField campoTextoEspecialidad;

	public PanelPanadero() {

		this.setBounds(0, 0, 800, 700);
		this.setBackground(new Color(189, 179, 143));
		setLayout(null);

		titulo = new JLabel("ADMINISTRACION DE PANADEROS");
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

		labelGenero = new JLabel("Genero:");
		labelGenero.setBounds(200, 110, 100, 20);
		add(labelGenero);
		campoTextoGenero = new TextField();
		campoTextoGenero.setBounds(200, 130, 150, 25);
		add(campoTextoGenero);

		labelEdad = new JLabel("Edad:");
		labelEdad.setBounds(370, 110, 100, 20);
		add(labelEdad);
		campoTextoEdad = new TextField();
		campoTextoEdad.setBounds(370, 130, 150, 25);
		add(campoTextoEdad);

		labelTelefono = new JLabel("Numero Telefono:");
		labelTelefono.setBounds(540, 110, 150, 20);
		add(labelTelefono);
		campoTextoTelefono = new TextField();
		campoTextoTelefono.setBounds(540, 130, 150, 25);
		add(campoTextoTelefono);

		labelSalario = new JLabel("Salario mensual:");
		labelSalario.setBounds(30, 170, 150, 20);
		add(labelSalario);
		campoTextoSalario = new TextField();
		campoTextoSalario.setBounds(30, 190, 150, 25);
		add(campoTextoSalario);

		labelDia = new JLabel("Panes hechos por dia:");
		labelDia.setBounds(200, 170, 150, 20);
		add(labelDia);
		campoTextoDia = new TextField();
		campoTextoDia.setBounds(200, 190, 150, 25);
		add(campoTextoDia);

		labelEspecialidad = new JLabel("Especialidad en cocina:");
		labelEspecialidad.setBounds(370, 170, 150, 20);
		add(labelEspecialidad);
		campoTextoEspecialidad = new TextField();
		campoTextoEspecialidad.setBounds(370, 190, 150, 25);
		add(campoTextoEspecialidad);

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

		boton7 = new JButton(" Calcular Dia Trabajo ");
		boton7.setBounds(410, 320, 170, 50);
		boton7.setBackground(new Color(133, 79, 11));
		boton7.setForeground(Color.WHITE);
		boton7.putClientProperty("JButton.buttonType", "roundRect");
		add(boton7);
	}

	public void aplicarIdioma(Properties prop) {
		titulo.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.titulo"));
		labelNombre.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.labelNombre"));
		labelGenero.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.labelGenero"));
		labelEdad.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.labelEdad"));
		labelTelefono.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.labelTelefono"));
		labelSalario.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.labelSalario"));
		labelDia.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.labelDia"));
		labelEspecialidad.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.labelEspecialidad"));
		boton1.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.botonRegistro"));
		boton2.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.botonEliminar"));
		boton3.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.botonActualizar"));
		boton4.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.botonLista"));
		boton5.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.botonContar"));
		boton6.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.botonVolver"));
		boton7.setText(prop.getProperty("panaderia.aplicativo.interfaz.panadero.botonCalcularDiaTrabajo"));
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

	public JButton getBoton7() {
		return boton7;
	}

	public void setBoton7(JButton boton7) {
		this.boton7 = boton7;
	}

	public TextField getCampoTextoNombre() {
		return campoTextoNombre;
	}

	public void setCampoTextoNombre(TextField campoTextoNombre) {
		this.campoTextoNombre = campoTextoNombre;
	}

	public TextField getCampoTextoGenero() {
		return campoTextoGenero;
	}

	public void setCampoTextoGenero(TextField campoTextoGenero) {
		this.campoTextoGenero = campoTextoGenero;
	}

	public TextField getCampoTextoEdad() {
		return campoTextoEdad;
	}

	public void setCampoTextoEdad(TextField campoTextoEdad) {
		this.campoTextoEdad = campoTextoEdad;
	}

	public TextField getCampoTextoTelefono() {
		return campoTextoTelefono;
	}

	public void setCampoTextoTelefono(TextField campoTextoTelefono) {
		this.campoTextoTelefono = campoTextoTelefono;
	}

	public TextField getCampoTextoSalario() {
		return campoTextoSalario;
	}

	public void setCampoTextoSalario(TextField campoTextoSalario) {
		this.campoTextoSalario = campoTextoSalario;
	}

	public TextField getCampoTextoDia() {
		return campoTextoDia;
	}

	public void setCampoTextoDia(TextField campoTextoDia) {
		this.campoTextoDia = campoTextoDia;
	}

	public TextField getCampoTextoEspecialidad() {
		return campoTextoEspecialidad;
	}

	public void setCampoTextoEspecialidad(TextField campoTextoEspecialidad) {
		this.campoTextoEspecialidad = campoTextoEspecialidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}