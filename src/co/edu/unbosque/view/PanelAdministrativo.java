package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAdministrativo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1028962006038554574L;

	private JLabel titulo;
	private JButton botonAdministrativo;
	private JButton botonCliente;
	private JButton botonSalir;

	public PanelAdministrativo() {
		// TODO Auto-generated constructor stub

		this.setBounds(0, 0, 800, 700);
		this.setBackground(new Color(189, 179, 143));
		setLayout(null);

	    titulo = new JLabel(" BIENVENIDO A LA PANADERIA CONNOR  ");
		titulo.setBounds(200, 100, 550, 30);
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		titulo.setForeground(new Color(133, 79, 11));
		add(titulo);
		
		botonAdministrativo = new JButton("Administrativo");
		botonAdministrativo.setBounds(250, 200, 280, 50);
		botonAdministrativo.setBackground(new Color(133, 79, 11));
		botonAdministrativo.setForeground(Color.WHITE);
		botonAdministrativo.putClientProperty("JButton.buttonType", "roundRect");
		add(botonAdministrativo);
		
		botonCliente = new JButton("Cliente");
		botonCliente.setBounds(250, 280, 280, 50);
		botonCliente.setBackground(new Color(133, 79, 11));
		botonCliente.setForeground(Color.WHITE);
		botonCliente.putClientProperty("JButton.buttonType", "roundRect");
		add(botonCliente);
		
		botonSalir = new JButton("Salir");
		botonSalir.setBounds(250, 360, 280, 50);
		botonSalir.setBackground(new Color(133, 79, 11));
		botonSalir.setForeground(Color.WHITE);
		botonSalir.putClientProperty("JButton.buttonType", "roundRect");
		add(botonSalir);

	}
	public void aplicarIdioma(Properties prop) {
		titulo.setText(prop.getProperty("panaderia.aplicativo.consola.menu.tituloPrincipal"));
		botonAdministrativo.setText(prop.getProperty("panaderia.aplicativo.interfaz.administrativo.botonAdministrativo"));
		botonCliente.setText(prop.getProperty("panaderia.aplicativo.interfaz.administrativo.botonCliente"));
		botonSalir.setText(prop.getProperty("panaderia.aplicativo.interfaz.administrativo.botonSalir"));
	}

	public JButton getBotonAdministrativo() {
		return botonAdministrativo;
	}

	public void setBotonAdministrativo(JButton botonAdministrativo) {
		this.botonAdministrativo = botonAdministrativo;
	}

	public JButton getBotonCliente() {
		return botonCliente;
	}

	public void setBotonCliente(JButton botonCliente) {
		this.botonCliente = botonCliente;
	}

	public JButton getBotonSalir() {
		return botonSalir;
	}

	public void setBotonSalir(JButton botonSalir) {
		this.botonSalir = botonSalir;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}