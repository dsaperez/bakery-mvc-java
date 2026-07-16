package co.edu.unbosque.view;

import java.awt.CardLayout;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PanelAdministrativo pa;
	private PanelPan pp;
	private PanelPostre ppt;
	private PanelGalleta pg;
	private PanelLacteo pl;
	private PanelMenu pm;

	private PanelPanadero ppn;
	private PanelRepostero pr;
	private PanelMesero pms;
	private PanelCliente pc;

	private PanelProveedorArtesanal ppa;
	private PanelProveedorIndustrial ppi;

	private PanelBuscar pb;

	private CardLayout cardLayout;
	private JPanel panelContenedor;

	public VentanaPrincipal() {
		inicializarComponentes();
	}

	public void inicializarComponentes() {
		// CARACTERISTICAS PRINCIPALES :
		// TITULO, CARACTERISTICA DE CERRADO X DEFECTO, ANCHO Y ALTO, UBICACION CON
		// RESPECTO A LA PANTALLA
		cardLayout = new CardLayout();
		panelContenedor = new JPanel(cardLayout);
		panelContenedor.setBounds(0, 0, 900, 800);

		setBounds(20, 20, 830, 800);
		setTitle(" Bienvenido a la Panaderia Connor ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);// ENUMERADOR: contien solo constantes y sirve como traduccion
		setLayout(null);
		// Se inicializa despues de la configuracion de la pantalla principal

		ppa = new PanelProveedorArtesanal();
		ppa.setVisible(true);

		ppi = new PanelProveedorIndustrial();
		ppi.setVisible(true);

		pa = new PanelAdministrativo();
		pa.setVisible(true);

		pm = new PanelMenu();
		pm.setVisible(true);

		pp = new PanelPan();
		pp.setVisible(true);

		ppt = new PanelPostre();
		ppt.setVisible(true);

		pg = new PanelGalleta();
		pg.setVisible(true);

		pl = new PanelLacteo();
		pl.setVisible(true);

		ppn = new PanelPanadero();
		ppn.setVisible(true);

		pr = new PanelRepostero();
		pr.setVisible(true);

		pms = new PanelMesero();
		pms.setVisible(true);

		pb = new PanelBuscar();
		pb.setVisible(true);

		pc = new PanelCliente();
		pc.setVisible(true);

		add(ppa);
		add(ppi);
		add(pa);
		add(pp);
		add(ppt);
		add(pg);
		add(pl);
		add(ppn);
		add(pr);
		add(pms);
		add(pb);
		add(pc);
		add(panelContenedor);

		panelContenedor.add(ppa, "PROVEEDOR ARTESANAL");
		panelContenedor.add(ppi, "PROVEEDOR INDUSTRIAL");
		panelContenedor.add(pa, "ADMINISTRATIVO");
		panelContenedor.add(pm, "MENU");
		panelContenedor.add(pp, "PAN");
		panelContenedor.add(ppt, "POSTRE");
		panelContenedor.add(pg, "GALLETA");
		panelContenedor.add(pl, "LACTEO");
		panelContenedor.add(ppn, "PANADERO");
		panelContenedor.add(pr, "REPOSTERO");
		panelContenedor.add(pms, "MESERO");
		panelContenedor.add(pb, "BUSCAR");
		panelContenedor.add(pc, "CLIENTE");

		mostrarPanel("ADMINISTRATIVO");
	}
	
	public void aplicarIdioma(Properties prop) {
		setTitle(prop.getProperty("panaderia.aplicativo.ventana.titulo"));
	}

	public void mostrarPanel(String nombre) {
		cardLayout.show(panelContenedor, nombre);
	}

	public PanelPan getPp() {
		return pp;
	}

	public void setPp(PanelPan pp) {
		this.pp = pp;
	}

	public PanelPostre getPpt() {
		return ppt;
	}

	public void setPpt(PanelPostre ppt) {
		this.ppt = ppt;
	}

	public PanelGalleta getPg() {
		return pg;
	}

	public void setPg(PanelGalleta pg) {
		this.pg = pg;
	}

	public PanelLacteo getPl() {
		return pl;
	}

	public void setPl(PanelLacteo pl) {
		this.pl = pl;
	}

	public PanelMenu getPm() {
		return pm;
	}

	public void setPm(PanelMenu pm) {
		this.pm = pm;
	}

	public PanelPanadero getPpn() {
		return ppn;
	}

	public void setPpn(PanelPanadero ppn) {
		this.ppn = ppn;
	}

	public PanelRepostero getPr() {
		return pr;
	}

	public void setPr(PanelRepostero pr) {
		this.pr = pr;
	}

	public PanelMesero getPms() {
		return pms;
	}

	public void setPms(PanelMesero pms) {
		this.pms = pms;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JPanel getPanelContenedor() {
		return panelContenedor;
	}

	public void setPanelContenedor(JPanel panelContenedor) {
		this.panelContenedor = panelContenedor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PanelBuscar getPb() {
		return pb;
	}

	public void setPb(PanelBuscar pb) {
		this.pb = pb;
	}

	public PanelCliente getPc() {
		return pc;
	}

	public void setPc(PanelCliente pc) {
		this.pc = pc;
	}

	public PanelAdministrativo getPa() {
		return pa;
	}

	public void setPa(PanelAdministrativo pa) {
		this.pa = pa;
	}

	public PanelProveedorArtesanal getPpa() {
		return ppa;
	}

	public void setPpa(PanelProveedorArtesanal ppa) {
		this.ppa = ppa;
	}

	public PanelProveedorIndustrial getPpi() {
		return ppi;
	}

	public void setPpi(PanelProveedorIndustrial ppi) {
		this.ppi = ppi;
	}

}
