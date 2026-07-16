package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import co.edu.unbosque.model.CarritoVenta;
import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.GalletaDTO;
import co.edu.unbosque.model.LacteoDTO;
import co.edu.unbosque.model.MeseroDTO;

import co.edu.unbosque.model.PanDTO;
import co.edu.unbosque.model.PanaderoDTO;
import co.edu.unbosque.model.PostreDTO;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.ProveedorArtesanalDTO;
import co.edu.unbosque.model.ProveedorIndustrialDTO;
import co.edu.unbosque.model.ReposteroDTO;
import co.edu.unbosque.model.VentaDTO;
import co.edu.unbosque.model.persistence.FileHandler;
import co.edu.unbosque.model.persistence.GalletaDAO;
import co.edu.unbosque.model.persistence.LacteoDAO;
import co.edu.unbosque.model.persistence.MeseroDAO;
import co.edu.unbosque.model.persistence.PanDAO;
import co.edu.unbosque.model.persistence.PanaderoDAO;
import co.edu.unbosque.model.persistence.PostreDAO;
import co.edu.unbosque.model.persistence.ProveedorArtesanalDAO;
import co.edu.unbosque.model.persistence.ProveedorIndustrialDAO;
import co.edu.unbosque.model.persistence.ReportePDF;
import co.edu.unbosque.model.persistence.ReposteroDAO;
import co.edu.unbosque.model.persistence.VentaDAO;
import co.edu.unbosque.util.exception.EmptyFieldException;
import co.edu.unbosque.util.exception.ExceptionChecker;
import co.edu.unbosque.util.exception.InvalidNITNumberException;
import co.edu.unbosque.util.exception.InvalidNameException;
import co.edu.unbosque.util.exception.InvalidPhoneNumberException;
import co.edu.unbosque.util.exception.MaxValueExceededException;
import co.edu.unbosque.util.exception.NegativeNumberException;
import co.edu.unbosque.view.Consola;
import co.edu.unbosque.view.VentanaPrincipal;

public class Controller implements ActionListener {

	// CREACIONES - INSTACIONES
	private Consola con;

	private PanDAO pDAO;
	private PostreDAO ptDAO;
	private GalletaDAO gDAO;
	private LacteoDAO lDAO;
	private PanaderoDAO pdDAO;
	private ReposteroDAO rDAO;
	private MeseroDAO mDAO;
	private ProveedorArtesanalDAO aDAO;
	private ProveedorIndustrialDAO iDAO;
	private ArrayList<CarritoVenta> carrito;
	private VentaDAO ventaDAO;

	private Properties prop = new Properties();

	private static VentanaPrincipal vp;

	public Controller() {
		// INICIALIZACIONES
		con = new Consola();

		pDAO = new PanDAO();
		ptDAO = new PostreDAO();
		gDAO = new GalletaDAO();
		lDAO = new LacteoDAO();
		pdDAO = new PanaderoDAO();
		rDAO = new ReposteroDAO();
		mDAO = new MeseroDAO();
		aDAO = new ProveedorArtesanalDAO();
		iDAO = new ProveedorIndustrialDAO();
		carrito = new ArrayList<CarritoVenta>();
		ventaDAO = new VentaDAO();

		inicializarPropiedades();
		vp = new VentanaPrincipal();
		vp.getPa().aplicarIdioma(prop);
		vp.getPb().aplicarIdioma(prop);
		vp.getPc().aplicarIdioma(prop);
		vp.getPg().aplicarIdioma(prop);
		vp.getPl().aplicarIdioma(prop);
		vp.getPm().aplicarIdioma(prop);
		vp.getPms().aplicarIdioma(prop);
		vp.getPp().aplicarIdioma(prop);
		vp.getPpn().aplicarIdioma(prop);
		vp.getPpt().aplicarIdioma(prop);
		vp.getPpa().aplicarIdioma(prop);
		vp.getPpi().aplicarIdioma(prop);
		vp.getPr().aplicarIdioma(prop);
		asignarOyentes();
	}

	private void inicializarPropiedades() {
		try {
			String seleccion = JOptionPane
					.showInputDialog("\n1. Español \n2. English \n3. Italiano \n4. Russian \n5. French \n6. Japanese ");
			if (seleccion == null) {
				seleccion = "1";
			}
			String rutaArchivo;
			switch (seleccion) {
			case "2":
				rutaArchivo = "english.properties";
				break;
			case "3":
				rutaArchivo = "italian.properties";
				break;
			case "4":
				rutaArchivo = "russian.properties";
				break;
			case "5":
				rutaArchivo = "french.properties";
				break;
			case "6":
				rutaArchivo = "japanese.properties";
				break;
			default:
				rutaArchivo = "spanish.properties";
				break;
			}
			File archivo = new File(rutaArchivo);
			if (archivo.exists()) {
				prop.load(new InputStreamReader(new FileInputStream(archivo), "UTF-8"));
			} else {
				System.err.println("No se encontró el archivo en: " + archivo.getAbsolutePath());
			}
		} catch (FileNotFoundException e) {
			System.err.println("Problema al cargar archivo de propiedades: Archivo no encontrado");
		} catch (IOException e) {
			System.err.println("Error de lectura/escritura al cargar propiedades");
			e.printStackTrace();
		}
	}

	public void iniciarGui() {
		vp.setTitle(prop.getProperty("panaderia.aplicativo.consola.menu.tituloPrincipal"));
		vp.setVisible(true);
	}

	public void asignarOyentes() {

		// proveedor artesanal
		vp.getPpa().getBoton1().addActionListener(this);
		vp.getPpa().getBoton1().setActionCommand("boton1PanelArtesanal");

		vp.getPpa().getBoton2().addActionListener(this);
		vp.getPpa().getBoton2().setActionCommand("boton2PanelArtesanal");

		vp.getPpa().getBoton3().addActionListener(this);
		vp.getPpa().getBoton3().setActionCommand("boton3PanelArtesanal");

		vp.getPpa().getBoton4().addActionListener(this);
		vp.getPpa().getBoton4().setActionCommand("boton4PanelArtesanal");

		vp.getPpa().getBoton5().addActionListener(this);
		vp.getPpa().getBoton5().setActionCommand("boton5PanelArtesanal");

		vp.getPpa().getBoton6().addActionListener(this);
		vp.getPpa().getBoton6().setActionCommand("boton6PanelArtesanal");

		// proveedor industrial
		vp.getPpi().getBoton1().addActionListener(this);
		vp.getPpi().getBoton1().setActionCommand("boton1PanelIndustrial");

		vp.getPpi().getBoton2().addActionListener(this);
		vp.getPpi().getBoton2().setActionCommand("boton2PanelIndustrial");

		vp.getPpi().getBoton3().addActionListener(this);
		vp.getPpi().getBoton3().setActionCommand("boton3PanelIndustrial");

		vp.getPpi().getBoton4().addActionListener(this);
		vp.getPpi().getBoton4().setActionCommand("boton4PanelIndustrial");

		vp.getPpi().getBoton5().addActionListener(this);
		vp.getPpi().getBoton5().setActionCommand("boton5PanelIndustrial");

		vp.getPpi().getBoton6().addActionListener(this);
		vp.getPpi().getBoton6().setActionCommand("boton6PanelIndustrial");

		// administrativo
		vp.getPa().getBotonAdministrativo().addActionListener(this);
		vp.getPa().getBotonAdministrativo().setActionCommand("Administrativo");

		vp.getPa().getBotonCliente().addActionListener(this);
		vp.getPa().getBotonCliente().setActionCommand("Cliente");

		vp.getPa().getBotonSalir().addActionListener(this);
		vp.getPa().getBotonSalir().setActionCommand("CerrarAplicativo");
		// pan
		vp.getPm().getBotonPan().addActionListener(this);
		vp.getPm().getBotonPan().setActionCommand("Pan");

		vp.getPm().getBotonPostre().addActionListener(this);
		vp.getPm().getBotonPostre().setActionCommand("Postre");

		vp.getPm().getBotonGalleta().addActionListener(this);
		vp.getPm().getBotonGalleta().setActionCommand("Galleta");

		vp.getPm().getBotonLacteo().addActionListener(this);
		vp.getPm().getBotonLacteo().setActionCommand("Lacteo");

		vp.getPm().getBotonPanadero().addActionListener(this);
		vp.getPm().getBotonPanadero().setActionCommand("Panadero");

		vp.getPm().getBotonRepostero().addActionListener(this);
		vp.getPm().getBotonRepostero().setActionCommand("Repostero");

		vp.getPm().getBotonMesero().addActionListener(this);
		vp.getPm().getBotonMesero().setActionCommand("Mesero");

		vp.getPm().getBotonBuscar().addActionListener(this);
		vp.getPm().getBotonBuscar().setActionCommand("Buscar");

		vp.getPm().getBotonTotalInventario().addActionListener(this);
		vp.getPm().getBotonTotalInventario().setActionCommand("Total");

		vp.getPm().getBotonSalir().addActionListener(this);
		vp.getPm().getBotonSalir().setActionCommand("Salir");

		vp.getPm().getBotonReporte().addActionListener(this);
		vp.getPm().getBotonReporte().setActionCommand("Reporte");

		vp.getPm().getBotonArtesanal().addActionListener(this);
		vp.getPm().getBotonArtesanal().setActionCommand("Artesanal");

		vp.getPm().getBotonIndustrial().addActionListener(this);
		vp.getPm().getBotonIndustrial().setActionCommand("Industrial");
		
		vp.getPm().getBotonVerVentas().addActionListener(this);
		vp.getPm().getBotonVerVentas().setActionCommand("HistorialVentas");

		// cliente
		vp.getPc().getBoton1().addActionListener(this);
		vp.getPc().getBoton1().setActionCommand("boton1PanelCliente");

		vp.getPc().getBoton2().addActionListener(this);
		vp.getPc().getBoton2().setActionCommand("boton2PanelCliente");

		vp.getPc().getBoton3().addActionListener(this);
		vp.getPc().getBoton3().setActionCommand("boton3PanelCliente");

		vp.getPc().getBoton4().addActionListener(this);
		vp.getPc().getBoton4().setActionCommand("boton4PanelCliente");

		vp.getPc().getBoton5().addActionListener(this);
		vp.getPc().getBoton5().setActionCommand("boton5PanelCliente");

		vp.getPc().getBotonBuscar().addActionListener(this);
		vp.getPc().getBotonBuscar().setActionCommand("botonBuscarPanelCliente");
		
		vp.getPc().getBotonComprar().addActionListener(this);
		vp.getPc().getBotonComprar().setActionCommand("botonComprarPanelCliente");

		// buscar
		vp.getPb().getBoton1().addActionListener(this);
		vp.getPb().getBoton1().setActionCommand("boton1PanelBuscar");

		vp.getPb().getBoton2().addActionListener(this);
		vp.getPb().getBoton2().setActionCommand("boton2PanelBuscar");

		// pan
		vp.getPp().getBoton1().addActionListener(this);
		vp.getPp().getBoton1().setActionCommand("boton1PanelPan");

		vp.getPp().getBoton2().addActionListener(this);
		vp.getPp().getBoton2().setActionCommand("boton2PanelPan");

		vp.getPp().getBoton3().addActionListener(this);
		vp.getPp().getBoton3().setActionCommand("boton3PanelPan");

		vp.getPp().getBoton4().addActionListener(this);
		vp.getPp().getBoton4().setActionCommand("boton4PanelPan");

		vp.getPp().getBoton5().addActionListener(this);
		vp.getPp().getBoton5().setActionCommand("boton5PanelPan");

		vp.getPp().getBoton6().addActionListener(this);
		vp.getPp().getBoton6().setActionCommand("boton6PanelPan");

		vp.getPp().getBoton7().addActionListener(this);
		vp.getPp().getBoton7().setActionCommand("boton7PanelPan");

		vp.getPp().getBoton8().addActionListener(this);
		vp.getPp().getBoton8().setActionCommand("boton8PanelPan");

		vp.getPp().getBoton9().addActionListener(this);
		vp.getPp().getBoton9().setActionCommand("boton9PanelPan");

		vp.getPp().getBoton10().addActionListener(this);
		vp.getPp().getBoton10().setActionCommand("boton10PanelPan");

		// postre
		vp.getPpt().getBoton1().addActionListener(this);
		vp.getPpt().getBoton1().setActionCommand("boton1PanelPostre");

		vp.getPpt().getBoton2().addActionListener(this);
		vp.getPpt().getBoton2().setActionCommand("boton2PanelPostre");

		vp.getPpt().getBoton3().addActionListener(this);
		vp.getPpt().getBoton3().setActionCommand("boton3PanelPostre");

		vp.getPpt().getBoton4().addActionListener(this);
		vp.getPpt().getBoton4().setActionCommand("boton4PanelPostre");

		vp.getPpt().getBoton5().addActionListener(this);
		vp.getPpt().getBoton5().setActionCommand("boton5PanelPostre");

		vp.getPpt().getBoton6().addActionListener(this);
		vp.getPpt().getBoton6().setActionCommand("boton6PanelPostre");

		vp.getPpt().getBoton7().addActionListener(this);
		vp.getPpt().getBoton7().setActionCommand("boton7PanelPostre");

		vp.getPpt().getBoton8().addActionListener(this);
		vp.getPpt().getBoton8().setActionCommand("boton8PanelPostre");

		vp.getPpt().getBoton9().addActionListener(this);
		vp.getPpt().getBoton9().setActionCommand("boton9PanelPostre");

		vp.getPpt().getBoton10().addActionListener(this);
		vp.getPpt().getBoton10().setActionCommand("boton10PanelPostre");

		// galleta
		vp.getPg().getBoton1().addActionListener(this);
		vp.getPg().getBoton1().setActionCommand("boton1PanelGalleta");

		vp.getPg().getBoton2().addActionListener(this);
		vp.getPg().getBoton2().setActionCommand("boton2PanelGalleta");

		vp.getPg().getBoton3().addActionListener(this);
		vp.getPg().getBoton3().setActionCommand("boton3PanelGalleta");

		vp.getPg().getBoton4().addActionListener(this);
		vp.getPg().getBoton4().setActionCommand("boton4PanelGalleta");

		vp.getPg().getBoton5().addActionListener(this);
		vp.getPg().getBoton5().setActionCommand("boton5PanelGalleta");

		vp.getPg().getBoton6().addActionListener(this);
		vp.getPg().getBoton6().setActionCommand("boton6PanelGalleta");

		vp.getPg().getBoton7().addActionListener(this);
		vp.getPg().getBoton7().setActionCommand("boton7PanelGalleta");

		vp.getPg().getBoton8().addActionListener(this);
		vp.getPg().getBoton8().setActionCommand("boton8PanelGalleta");

		vp.getPg().getBoton9().addActionListener(this);
		vp.getPg().getBoton9().setActionCommand("boton9PanelGalleta");

		vp.getPg().getBoton10().addActionListener(this);
		vp.getPg().getBoton10().setActionCommand("boton10PanelGalleta");
		// lacteo
		vp.getPl().getBoton1().addActionListener(this);
		vp.getPl().getBoton1().setActionCommand("boton1PanelLacteo");

		vp.getPl().getBoton2().addActionListener(this);
		vp.getPl().getBoton2().setActionCommand("boton2PanelLacteo");

		vp.getPl().getBoton3().addActionListener(this);
		vp.getPl().getBoton3().setActionCommand("boton3PanelLacteo");

		vp.getPl().getBoton4().addActionListener(this);
		vp.getPl().getBoton4().setActionCommand("boton4PanelLacteo");

		vp.getPl().getBoton5().addActionListener(this);
		vp.getPl().getBoton5().setActionCommand("boton5PanelLacteo");

		vp.getPl().getBoton6().addActionListener(this);
		vp.getPl().getBoton6().setActionCommand("boton6PanelLacteo");

		vp.getPl().getBoton7().addActionListener(this);
		vp.getPl().getBoton7().setActionCommand("boton7PanelLacteo");

		vp.getPl().getBoton8().addActionListener(this);
		vp.getPl().getBoton8().setActionCommand("boton8PanelLacteo");

		vp.getPl().getBoton9().addActionListener(this);
		vp.getPl().getBoton9().setActionCommand("boton9PanelLacteo");

		vp.getPl().getBoton10().addActionListener(this);
		vp.getPl().getBoton10().setActionCommand("boton10PanelLacteo");

		// panadero
		vp.getPpn().getBoton1().addActionListener(this);
		vp.getPpn().getBoton1().setActionCommand("boton1PanelPanadero");

		vp.getPpn().getBoton2().addActionListener(this);
		vp.getPpn().getBoton2().setActionCommand("boton2PanelPanadero");

		vp.getPpn().getBoton3().addActionListener(this);
		vp.getPpn().getBoton3().setActionCommand("boton3PanelPanadero");

		vp.getPpn().getBoton4().addActionListener(this);
		vp.getPpn().getBoton4().setActionCommand("boton4PanelPanadero");

		vp.getPpn().getBoton5().addActionListener(this);
		vp.getPpn().getBoton5().setActionCommand("boton5PanelPanadero");

		vp.getPpn().getBoton6().addActionListener(this);
		vp.getPpn().getBoton6().setActionCommand("boton6PanelPanadero");

		vp.getPpn().getBoton7().addActionListener(this);
		vp.getPpn().getBoton7().setActionCommand("boton7PanelPanadero");

		// repostero
		vp.getPr().getBoton1().addActionListener(this);
		vp.getPr().getBoton1().setActionCommand("boton1PanelRepostero");

		vp.getPr().getBoton2().addActionListener(this);
		vp.getPr().getBoton2().setActionCommand("boton2PanelRepostero");

		vp.getPr().getBoton3().addActionListener(this);
		vp.getPr().getBoton3().setActionCommand("boton3PanelRepostero");

		vp.getPr().getBoton4().addActionListener(this);
		vp.getPr().getBoton4().setActionCommand("boton4PanelRepostero");

		vp.getPr().getBoton5().addActionListener(this);
		vp.getPr().getBoton5().setActionCommand("boton5PanelRepostero");

		vp.getPr().getBoton6().addActionListener(this);
		vp.getPr().getBoton6().setActionCommand("boton6PanelRepostero");

		vp.getPr().getBoton7().addActionListener(this);
		vp.getPr().getBoton7().setActionCommand("boton7PanelRepostero");

		// mesero
		vp.getPms().getBoton1().addActionListener(this);
		vp.getPms().getBoton1().setActionCommand("boton1PanelMesero");

		vp.getPms().getBoton2().addActionListener(this);
		vp.getPms().getBoton2().setActionCommand("boton2PanelMesero");

		vp.getPms().getBoton3().addActionListener(this);
		vp.getPms().getBoton3().setActionCommand("boton3PanelMesero");

		vp.getPms().getBoton4().addActionListener(this);
		vp.getPms().getBoton4().setActionCommand("boton4PanelMesero");

		vp.getPms().getBoton5().addActionListener(this);
		vp.getPms().getBoton5().setActionCommand("boton5PanelMesero");

		vp.getPms().getBoton6().addActionListener(this);
		vp.getPms().getBoton6().setActionCommand("boton6PanelMesero");

		vp.getPms().getBoton7().addActionListener(this);
		vp.getPms().getBoton7().setActionCommand("boton7PanelMesero");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String origen = e.getActionCommand();

		switch (origen) {
		case "Administrativo": {
			vp.mostrarPanel("MENU");
			break;
		}
		case "Cliente": {
			vp.mostrarPanel("CLIENTE");
			break;
		}
		case "CerrarAplicativo": {
			System.exit(0);
			break;
		}
		case "Pan": {
			vp.mostrarPanel("PAN");
			break;
		}
		case "Postre": {
			vp.mostrarPanel("POSTRE");
			break;
		}
		case "Galleta": {
			vp.mostrarPanel("GALLETA");
			break;
		}
		case "Lacteo": {
			vp.mostrarPanel("LACTEO");
			break;
		}
		case "Panadero": {
			vp.mostrarPanel("PANADERO");
			break;
		}
		case "Repostero": {
			vp.mostrarPanel("REPOSTERO");
			break;
		}
		case "Buscar": {
			vp.mostrarPanel("BUSCAR");
			break;
		}
		case "Mesero": {
			vp.mostrarPanel("MESERO");
			break;
		}
		case "Artesanal": {
			vp.mostrarPanel("PROVEEDOR ARTESANAL");
			break;
		}
		case "Industrial": {
			vp.mostrarPanel("PROVEEDOR INDUSTRIAL");
			break;
		}
		case "Reporte": {
			interfazGenerarReportePDF();
			interfazGenerarReporteExcel();
			break;
		}
		case "Salir": {
			vp.mostrarPanel("ADMINISTRATIVO");
			break;
		}
		case "Total": {
			sumarPrecioInventario();
			break;
		}
		case "HistorialVentas": {
			listaVentas();
			break;
		}
		

		// proveedor artesanal
		case "boton1PanelArtesanal": {
			registroProveedorArtesanal();
			break;
		}
		case "boton2PanelArtesanal": {
			eliminarProveedorArtesanal();
			break;
		}
		case "boton3PanelArtesanal": {
			actualizarProveedorArtesanal();
			break;
		}
		case "boton4PanelArtesanal": {
			listaProveedorArtesanal();
			break;
		}
		case "boton5PanelArtesanal": {
			contarProveedorArtesanal();
			break;
		}
		case "boton6PanelArtesanal": {
			vp.mostrarPanel("MENU");
			break;
		}

		// proveedor industrial

		case "boton1PanelIndustrial": {
			registroProveedorIndustrial();
			break;
		}
		case "boton2PanelIndustrial": {
			eliminarProveedorIndustrial();
			break;
		}
		case "boton3PanelIndustrial": {
			actualizarProveedorIndustrial();
			break;
		}
		case "boton4PanelIndustrial": {
			listaProveedorIndustrial();
			break;
		}
		case "boton5PanelIndustrial": {
			contarProveedorIndustrial();
			break;
		}
		case "boton6PanelIndustrial": {
			vp.mostrarPanel("MENU");
			break;
		}

		// buscar
		case "boton1PanelBuscar": {
			buscarProducto();
			break;
		}
		case "boton2PanelBuscar": {
			vp.mostrarPanel("MENU");
			break;
		}

		// cliente
		case "boton1PanelCliente": {
			comprarPan();
			break;
		}
		case "boton2PanelCliente": {
			comprarPostre();
			break;
		}
		case "boton3PanelCliente": {
			comprarGalleta();
			break;
		}
		case "boton5PanelCliente": {
			comprarLacteo();
			break;
		}
		case "botonBuscarPanelCliente": {
			vp.mostrarPanel("BUSCAR");
			break;
		}
		case "boton4PanelCliente": {
			vp.mostrarPanel("ADMINISTRATIVO");
			break;
		}
		case "botonComprarPanelCliente": {
			finalizarCompra();
			break;
		}

		// pan
		case "boton1PanelPan": {
			registroPan();
			break;
		}
		case "boton2PanelPan": {
			eliminarPan();
			break;
		}
		case "boton3PanelPan": {
			actualizarPan();
			break;
		}
		case "boton4PanelPan": {
			listaPan();
			break;
		}
		case "boton5PanelPan": {
			contarPan();
			break;
		}
		case "boton6PanelPan": {
			vp.mostrarPanel("MENU");
			break;
		}
		case "boton7PanelPan": {
			verificarVencimientoPan();
			break;
		}
		case "boton8PanelPan": {
			sumarPrecioPan();
			break;
		}
		case "boton9PanelPan": {
			verificarEndurecimientoPan();
			break;
		}
		case "boton10PanelPan": {
			prepararIngredientesPan();
			break;
		}

		// postre
		case "boton1PanelPostre": {
			registroPostre();
			break;
		}
		case "boton2PanelPostre": {
			eliminarPostre();
			break;
		}
		case "boton3PanelPostre": {
			actualizarPostre();
			break;
		}
		case "boton4PanelPostre": {
			listaPostre();
			break;
		}
		case "boton5PanelPostre": {
			contarPostre();
			break;
		}
		case "boton6PanelPostre": {
			vp.mostrarPanel("MENU");
			break;
		}
		case "boton7PanelPostre": {
			verificarVencimientoPostre();
			break;
		}
		case "boton8PanelPostre": {
			sumarPrecioPostre();
			break;
		}
		case "boton9PanelPostre": {
			verificarDeterioroPostre();
			break;
		}
		case "boton10PanelPostre": {
			prepararIngredientesPostre();
			break;
		}
		// galleta
		case "boton1PanelGalleta": {
			registroGalleta();
			break;
		}
		case "boton2PanelGalleta": {
			eliminarGalleta();
			break;
		}
		case "boton3PanelGalleta": {
			actualizarGalleta();
			break;
		}
		case "boton4PanelGalleta": {
			listaGalleta();
			break;
		}
		case "boton5PanelGalleta": {
			contarGalleta();
			break;
		}
		case "boton6PanelGalleta": {
			vp.mostrarPanel("MENU");
			break;
		}
		case "boton7PanelGalleta": {
			verificarVencimientoGalleta();
			break;
		}
		case "boton8PanelGalleta": {
			sumarPrecioGalleta();
			break;
		}
		case "boton9PanelGalleta": {
			verificarEndurecimientoGalleta();
			break;
		}
		case "boton10PanelGalleta": {
			prepararIngredientesGalleta();
			break;
		}
		// lacteo
		case "boton1PanelLacteo": {
			registroLacteo();
			break;
		}
		case "boton2PanelLacteo": {
			eliminarLacteo();
			break;
		}
		case "boton3PanelLacteo": {
			actualizarLacteo();
			break;
		}
		case "boton4PanelLacteo": {
			listaLacteo();
			break;
		}
		case "boton5PanelLacteo": {
			contarLacteo();
			break;
		}
		case "boton6PanelLacteo": {
			vp.mostrarPanel("MENU");
			break;
		}
		case "boton7PanelLacteo": {
			verificarVencimientoLacteo();
			break;
		}
		case "boton8PanelLacteo": {
			sumarPrecioLacteo();
			break;
		}
		case "boton9PanelLacteo": {
			verificarCaducacionLacteo();
			break;
		}
		case "boton10PanelLacteo": {
			prepararIngredientesLacteo();
			break;
		}
		// panadero
		case "boton1PanelPanadero": {
			registroPanadero();
			break;
		}
		case "boton2PanelPanadero": {
			eliminarPanadero();
			break;
		}
		case "boton3PanelPanadero": {
			actualizarPanadero();
			break;
		}
		case "boton4PanelPanadero": {
			listaPanadero();
			break;
		}
		case "boton5PanelPanadero": {
			contarPanadero();
			break;
		}
		case "boton6PanelPanadero": {
			vp.mostrarPanel("MENU");
			break;
		}
		case "boton7PanelPanadero": {
			calcularSalarioPanadero();
			break;
		}
		// repostero
		case "boton1PanelRepostero": {
			registroRepostero();
			break;
		}
		case "boton2PanelRepostero": {
			eliminarRepostero();
			break;
		}
		case "boton3PanelRepostero": {
			actualizarRepostero();
			break;
		}
		case "boton4PanelRepostero": {
			listaRepostero();
			break;
		}
		case "boton5PanelRepostero": {
			contarRepostero();
			break;
		}
		case "boton6PanelRepostero": {
			vp.mostrarPanel("MENU");
			break;
		}
		case "boton7PanelRepostero": {
			calcularSalarioRepostero();
			break;
		}
		// mesero
		case "boton1PanelMesero": {
			registroMesero();
			break;
		}
		case "boton2PanelMesero": {
			eliminarMesero();
			break;
		}
		case "boton3PanelMesero": {
			actualizarMesero();
			break;
		}
		case "boton4PanelMesero": {
			listaMesero();
			break;
		}
		case "boton5PanelMesero": {
			contarMesero();
			break;
		}
		case "boton6PanelMesero": {
			vp.mostrarPanel("MENU");
			break;
		}
		case "boton7PanelMesero": {
			calcularSalarioMesero();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + origen);
		}
	}
	
	private void listaVentas() {
	    mostrarLista(ventaDAO.mostrar(), prop.getProperty("panaderia.aplicativo.interfaz.venta.listaVentas"));
	}

	// pdf y lista excel
	private void interfazGenerarReportePDF() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		listaProductos.addAll(pDAO.getListaPanes());
		listaProductos.addAll(ptDAO.getListaPostres());
		listaProductos.addAll(gDAO.getListaGalletas());
		listaProductos.addAll(lDAO.getListaLacteos());

		ReportePDF.crearReporteInventario("reporte_inventario.pdf", listaProductos);
		JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.pdfExitoConsola"));
	}

	private void consolaGenerarReportePDF() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		listaProductos.addAll(pDAO.getListaPanes());
		listaProductos.addAll(ptDAO.getListaPostres());
		listaProductos.addAll(gDAO.getListaGalletas());
		listaProductos.addAll(lDAO.getListaLacteos());

		ReportePDF.crearReporteInventario("reporte_inventario.pdf", listaProductos);
		con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.pdfExitoConsola"));

	}

	private void interfazGenerarReporteExcel() {
		String contenido = pDAO.reporteListaCSV() + ptDAO.reporteListaCSV() + gDAO.reporteListaCSV()
				+ lDAO.reporteListaCSV();
		FileHandler.crearYEscribirArchivo("reporte_inventario.csv", contenido);
		JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.excelExitoConsola"));
	}

	private void consolaGenerarReporteExcel() {
		String contenido = pDAO.reporteListaCSV() + ptDAO.reporteListaCSV() + gDAO.reporteListaCSV()
				+ lDAO.reporteListaCSV();
		FileHandler.crearYEscribirArchivo("reporte_inventario.csv", contenido);
		con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.excelExitoConsola"));
	}

	// comprar
	private void comprarPan() {
		if (pDAO.getListaPanes().isEmpty()) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.panVacio"));
			return;
		}
		mostrarLista(pDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaPanesDisponibles") + " - "
				+ prop.getProperty("panaderia.aplicativo.interfaz.menu.notaPosicionCero"));
		String posicion = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionComprarPan"));
		try {
			int index = Integer.parseInt(posicion);
			if (index < 0 || index >= pDAO.getListaPanes().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String cantidadProducto = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.ingreseCantidadComprar"));
			float cantidad = Float.parseFloat(cantidadProducto);

			String nombreProducto = pDAO.getListaPanes().get(index).getNombre();
			int precioUnitario = pDAO.getListaPanes().get(index).getPrecio();

			String resultado = pDAO.venderPan(index, cantidad);
			JOptionPane.showMessageDialog(vp, resultado);

			if (resultado.contains("Añadido al carrito")) {
				CarritoVenta prod = new CarritoVenta();
				prod.setNombreProducto(nombreProducto);
				prod.setCantidad(cantidad);
				prod.setPrecioUnitario(precioUnitario);
				prod.setSubtotal((int) (cantidad * precioUnitario));
				carrito.add(prod);
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void comprarPostre() {
		if (ptDAO.getListaPostres().isEmpty()) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.postreVacio"));
			return;
		}
		mostrarLista(ptDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaPostresDisponibles")
				+ " - " + prop.getProperty("panaderia.aplicativo.interfaz.menu.notaPosicionCero"));
		String posicion = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionComprarPostre"));
		try {
			int index = Integer.parseInt(posicion);
			if (index < 0 || index >= ptDAO.getListaPostres().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String cantidadProducto = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.ingreseCantidadComprar"));
			float cantidad = Float.parseFloat(cantidadProducto);

			String nombreProducto = ptDAO.getListaPostres().get(index).getNombre();
			int precioUnitario = ptDAO.getListaPostres().get(index).getPrecio();

			String resultado = ptDAO.venderPostre(index, cantidad);
			JOptionPane.showMessageDialog(vp, resultado);

			if (resultado.contains("Añadido al carrito")) {
				CarritoVenta prod = new CarritoVenta();
				prod.setNombreProducto(nombreProducto);
				prod.setCantidad(cantidad);
				prod.setPrecioUnitario(precioUnitario);
				prod.setSubtotal((int) (cantidad * precioUnitario));
				carrito.add(prod);
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void comprarGalleta() {
		if (gDAO.getListaGalletas().isEmpty()) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.galletaVacio"));
			return;
		}
		mostrarLista(gDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaGalletasDisponibles")
				+ " - " + prop.getProperty("panaderia.aplicativo.interfaz.menu.notaPosicionCero"));
		String posicion = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionComprarGalleta"));
		try {
			int index = Integer.parseInt(posicion);
			if (index < 0 || index >= gDAO.getListaGalletas().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String cantidadProducto = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.ingreseCantidadComprar"));
			float cantidad = Float.parseFloat(cantidadProducto);

			String nombreProducto = gDAO.getListaGalletas().get(index).getNombre();
			int precioUnitario = gDAO.getListaGalletas().get(index).getPrecio();

			String resultado = gDAO.venderGalleta(index, cantidad);
			JOptionPane.showMessageDialog(vp, resultado);

			if (resultado.contains("Añadido al carrito")) {
				CarritoVenta prod = new CarritoVenta();
				prod.setNombreProducto(nombreProducto);
				prod.setCantidad(cantidad);
				prod.setPrecioUnitario(precioUnitario);
				prod.setSubtotal((int) (cantidad * precioUnitario));
				carrito.add(prod);
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void comprarLacteo() {
		if (lDAO.getListaLacteos().isEmpty()) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.lacteoVacio"));
			return;
		}
		mostrarLista(lDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaLacteosDisponibles")
				+ " - " + prop.getProperty("panaderia.aplicativo.interfaz.menu.notaPosicionCero"));
		String posicion = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionComprarLacteo"));
		try {
			int index = Integer.parseInt(posicion);
			if (index < 0 || index >= lDAO.getListaLacteos().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String cantidadProducto = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.ingreseCantidadComprar"));
			float cantidad = Float.parseFloat(cantidadProducto);

			String nombreProducto = lDAO.getListaLacteos().get(index).getNombre();
			int precioUnitario = lDAO.getListaLacteos().get(index).getPrecio();

			String resultado = lDAO.venderLacteo(index, cantidad);
			JOptionPane.showMessageDialog(vp, resultado);

			if (resultado.contains("Añadido al carrito")) {
				CarritoVenta prod = new CarritoVenta();
				prod.setNombreProducto(nombreProducto);
				prod.setCantidad(cantidad);
				prod.setPrecioUnitario(precioUnitario);
				prod.setSubtotal((int) (cantidad * precioUnitario));
				carrito.add(prod);
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void finalizarCompra() {
		if (carrito.isEmpty()) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.venta.carritoVacio"));
			return;
		}

		try {
			String nombre = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.venta.nombreCliente"));
			ExceptionChecker.verificarCampoVacio(nombre);
			ExceptionChecker.verificarSoloLetras(nombre);

			String apellido = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.venta.apellidoCliente"));
			ExceptionChecker.verificarCampoVacio(apellido);
			ExceptionChecker.verificarSoloLetras(apellido);

			int cedula = Integer.parseInt(
					JOptionPane.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.venta.cedulaCliente")));
			ExceptionChecker.verificarNumeroNegativo(cedula);

			String telefono = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.venta.telefonoCliente"));
			ExceptionChecker.verificarFormatoTelefono(telefono);

			ClienteDTO clienteDTO = new ClienteDTO(nombre, apellido, cedula, telefono);

			String metodoPago = seleccionarMetodoPago();
			if (metodoPago == null) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.interfaz.venta.pagoNoSeleccionado"));
				return;
			}

			int total = calcularTotalCarrito();

			ArrayList<CarritoVenta> copiaCarrito = new ArrayList<CarritoVenta>(carrito);
			VentaDTO nuevaVenta = new VentaDTO(copiaCarrito, clienteDTO, metodoPago, total);
			ventaDAO.crear(nuevaVenta);

			String archivoPdf = "recibo_venta_" + ventaDAO.contar() + ".pdf";
			ReportePDF.crearReciboVenta(archivoPdf, clienteDTO, copiaCarrito, metodoPago, total);

			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.venta.compraExitosa") + " " + total + "\n"
							+ prop.getProperty("panaderia.aplicativo.interfaz.venta.reciboGuardado") + " "
							+ archivoPdf);

			carrito.clear();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private String seleccionarMetodoPago() {
		String seleccion = JOptionPane.showInputDialog(" Seleccione el metodo de pago: \n" + " 1. Efectivo \n"
				+ " 2. Tarjeta Debito \n" + " 3. Tarjeta Credito \n" + " 4. Transferencia \n" + " 5. Nequi ");

		if (seleccion == null) {
			return null;
		}

		switch (seleccion) {
		case "1":
			return "Efectivo";
		case "2":
			return "Tarjeta Debito";
		case "3":
			return "Tarjeta Credito";
		case "4":
			return "Transferencia";
		case "5":
			return "Nequi";
		default:
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.seleccionNoValida"));
			return null;
		}
	}

	private int calcularTotalCarrito() {
		int total = 0;
		for (CarritoVenta item : carrito) {
			total += item.getSubtotal();
		}
		return total;
	}

	// buscar
	private void buscarProducto() {
		String letra = vp.getPb().getCampoTextoLetras().getText();
		String resultado = pDAO.buscarProducto(letra) + ptDAO.buscarProducto(letra) + gDAO.buscarProducto(letra)
				+ lDAO.buscarProducto(letra);
		JOptionPane.showMessageDialog(vp, resultado);
	}
	// valor total inventario

	private void sumarPrecioInventario() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		listaProductos.addAll(pDAO.getListaPanes());
		listaProductos.addAll(ptDAO.getListaPostres());
		listaProductos.addAll(gDAO.getListaGalletas());
		listaProductos.addAll(lDAO.getListaLacteos());
		sumarPreciosGUI(listaProductos);

	}

	// proveedor artesanal

	private void registroProveedorArtesanal() {
		try {
			String nombreEmpresa = vp.getPpa().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombreEmpresa);
			ExceptionChecker.verificarSoloLetras(nombreEmpresa);

			int nit = Integer.parseInt(vp.getPpa().getCampoTextoNIT().getText());
			ExceptionChecker.verificarNumeroNegativo(nit);
			ExceptionChecker.verificarNIT(nit);

			String ciudad = vp.getPpa().getCampoTextoCiudad().getText();
			ExceptionChecker.verificarCampoVacio(ciudad);
			ExceptionChecker.verificarSoloLetras(ciudad);

			String telefono = vp.getPpa().getCampoTextoTelefono().getText();
			ExceptionChecker.verificarFormatoTelefono(telefono);

			String tipoProducto = vp.getPpa().getCampoTextoProducto().getText();
			ExceptionChecker.verificarCampoVacio(tipoProducto);
			ExceptionChecker.verificarSoloLetras(tipoProducto);

			boolean certificado = vp.getPpa().getCampoTextoInvima().isSelected();

			ProveedorArtesanalDTO nuevoArt = new ProveedorArtesanalDTO(nombreEmpresa, nit, ciudad, telefono,
					tipoProducto, certificado);
			aDAO.crear(nuevoArt);
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.proveedor.registroExito"));
			limpiarCamposProveedorArtesanal();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNITNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.nitInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCamposProveedorArtesanal() {
		vp.getPpa().getCampoTextoNombre().setText("");
		vp.getPpa().getCampoTextoNIT().setText("");
		vp.getPpa().getCampoTextoCiudad().setText("");
		vp.getPpa().getCampoTextoTelefono().setText("");
		vp.getPpa().getCampoTextoProducto().setText("");
		vp.getPpa().getCampoTextoInvima().setSelected(false);
	}

	private void eliminarProveedorArtesanal() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.eliPosicion"));
		try {
			int index = Integer.parseInt(input);
			if (index < 0 || index >= aDAO.getListaProveedoresArtesanales().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
			} else {
				aDAO.eliminar(index);
				JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.proveedor.eliExito"));
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarProveedorArtesanal() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actPosicion"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= aDAO.getListaProveedoresArtesanales().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String nombreEmpresa = vp.getPpa().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombreEmpresa);
			ExceptionChecker.verificarSoloLetras(nombreEmpresa);

			int nit = Integer.parseInt(vp.getPpa().getCampoTextoNIT().getText());
			ExceptionChecker.verificarNumeroNegativo(nit);
			ExceptionChecker.verificarNIT(nit);

			String ciudad = vp.getPpa().getCampoTextoCiudad().getText();
			ExceptionChecker.verificarCampoVacio(ciudad);
			ExceptionChecker.verificarSoloLetras(ciudad);

			String telefono = vp.getPpa().getCampoTextoTelefono().getText();
			ExceptionChecker.verificarFormatoTelefono(telefono);

			String tipoProducto = vp.getPpa().getCampoTextoProducto().getText();
			ExceptionChecker.verificarCampoVacio(tipoProducto);
			ExceptionChecker.verificarSoloLetras(tipoProducto);

			boolean certificado = vp.getPpa().getCampoTextoInvima().isSelected();

			ProveedorArtesanalDTO artActualizado = new ProveedorArtesanalDTO(nombreEmpresa, nit, ciudad, telefono,
					tipoProducto, certificado);
			aDAO.actualizar(index, artActualizado);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actExito"));
			limpiarCamposProveedorArtesanal();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNITNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.nitInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listaProveedorArtesanal() {
		mostrarLista(aDAO.mostrar(), prop.getProperty("panaderia.aplicativo.interfaz.proveedor.listaArt"));
	}

	private void contarProveedorArtesanal() {
		JOptionPane.showMessageDialog(vp,
				prop.getProperty("panaderia.aplicativo.interfaz.proveedor.numeroArt") + " " + aDAO.contar() + "\n"
						+ prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nombreArt") + aDAO.sumarCantidad());
	}

	// proveedor industrial

	private void registroProveedorIndustrial() {
		try {
			String nombreEmpresa = vp.getPpi().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombreEmpresa);
			ExceptionChecker.verificarSoloLetras(nombreEmpresa);

			int nit = Integer.parseInt(vp.getPpi().getCampoTextoNIT().getText());
			ExceptionChecker.verificarNumeroNegativo(nit);
			ExceptionChecker.verificarNIT(nit);

			String ciudad = vp.getPpi().getCampoTextoCiudad().getText();
			ExceptionChecker.verificarCampoVacio(ciudad);
			ExceptionChecker.verificarSoloLetras(ciudad);

			String telefono = vp.getPpi().getCampoTextoTelefono().getText();
			ExceptionChecker.verificarFormatoTelefono(telefono);

			String tipoProducto = vp.getPpi().getCampoTextoProducto().getText();
			ExceptionChecker.verificarCampoVacio(tipoProducto);
			ExceptionChecker.verificarSoloLetras(tipoProducto);

			boolean certificado = vp.getPpi().getCampoTextoInvima().isSelected();

			ProveedorIndustrialDTO nuevoInd = new ProveedorIndustrialDTO(nombreEmpresa, nit, ciudad, telefono,
					tipoProducto, certificado);
			iDAO.crear(nuevoInd);
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.proveedor.registroExito"));
			limpiarCamposProveedorIndustrial();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNITNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.nitInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCamposProveedorIndustrial() {
		vp.getPpi().getCampoTextoNombre().setText("");
		vp.getPpi().getCampoTextoNIT().setText("");
		vp.getPpi().getCampoTextoCiudad().setText("");
		vp.getPpi().getCampoTextoTelefono().setText("");
		vp.getPpi().getCampoTextoProducto().setText("");
		vp.getPpi().getCampoTextoInvima().setSelected(false);
	}

	private void eliminarProveedorIndustrial() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.eliPosicion"));
		try {
			int index = Integer.parseInt(input);
			if (index < 0 || index >= iDAO.getListaProveedoresIndustriales().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
			} else {
				iDAO.eliminar(index);
				JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.proveedor.eliExito"));
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarProveedorIndustrial() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actPosicion"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= iDAO.getListaProveedoresIndustriales().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}

			String nombreEmpresa = vp.getPpi().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombreEmpresa);
			ExceptionChecker.verificarSoloLetras(nombreEmpresa);

			int nit = Integer.parseInt(vp.getPpi().getCampoTextoNIT().getText());
			ExceptionChecker.verificarNumeroNegativo(nit);
			ExceptionChecker.verificarNIT(nit);

			String ciudad = vp.getPpi().getCampoTextoCiudad().getText();
			ExceptionChecker.verificarCampoVacio(ciudad);
			ExceptionChecker.verificarSoloLetras(ciudad);

			String telefono = vp.getPpi().getCampoTextoTelefono().getText();
			ExceptionChecker.verificarFormatoTelefono(telefono);

			String tipoProducto = vp.getPpi().getCampoTextoProducto().getText();
			ExceptionChecker.verificarCampoVacio(tipoProducto);
			ExceptionChecker.verificarSoloLetras(tipoProducto);

			boolean certificado = vp.getPpi().getCampoTextoInvima().isSelected();

			ProveedorIndustrialDTO indActualizado = new ProveedorIndustrialDTO(nombreEmpresa, nit, ciudad, telefono,
					tipoProducto, certificado);
			iDAO.actualizar(index, indActualizado);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actExito"));
			limpiarCamposProveedorIndustrial();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNITNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.nitInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listaProveedorIndustrial() {
		mostrarLista(iDAO.mostrar(), prop.getProperty("panaderia.aplicativo.interfaz.proveedor.listaInd"));
	}

	private void contarProveedorIndustrial() {
		JOptionPane.showMessageDialog(vp,
				prop.getProperty("panaderia.aplicativo.interfaz.proveedor.numeroInd") + " " + iDAO.contar() + "\n"
						+ prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nombreInd") + iDAO.sumarCantidad());
	}

	// pan
	private void registroPan() {
		try {
			String nombre = vp.getPp().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombre);
			ExceptionChecker.verificarSoloLetras(nombre);

			float cantidad = Float.parseFloat(vp.getPp().getCampoTextoCantidad().getText());
			ExceptionChecker.verificarNumeroNegativo(cantidad);

			int peso = Integer.parseInt(vp.getPp().getCampoTextoPeso().getText());
			ExceptionChecker.verificarNumeroNegativo(peso);

			int precio = Integer.parseInt(vp.getPp().getCampoTextoPrecio().getText());
			ExceptionChecker.verificarNumeroNegativo(precio);

			boolean tieneLevadura = vp.getPp().getCampoTextoLevadura().isSelected();

			String tipoHarina = vp.getPp().getCampoTextoHarina().getText();
			ExceptionChecker.verificarCampoVacio(tipoHarina);
			ExceptionChecker.verificarSoloLetras(tipoHarina);

			PanDTO nuevoPan = new PanDTO(nombre, cantidad, peso, precio, tieneLevadura, tipoHarina);
			pDAO.crear(nuevoPan);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.exitoPan"));
			limpiarCamposPan();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.menu.errorCantidadPesoPrecio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCamposPan() {
		vp.getPp().getCampoTextoNombre().setText("");
		vp.getPp().getCampoTextoCantidad().setText("");
		vp.getPp().getCampoTextoPeso().setText("");
		vp.getPp().getCampoTextoPrecio().setText("");
		vp.getPp().getCampoTextoLevadura().setSelected(false);
		vp.getPp().getCampoTextoHarina().setText("");
	}

	private void eliminarPan() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= pDAO.getListaPanes().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
			} else {
				pDAO.eliminar(index);
				JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoPan"));
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarPan() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= pDAO.getListaPanes().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String nombre = vp.getPp().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombre);
			ExceptionChecker.verificarSoloLetras(nombre);

			float cantidad = Float.parseFloat(vp.getPp().getCampoTextoCantidad().getText());
			ExceptionChecker.verificarNumeroNegativo(cantidad);

			int peso = Integer.parseInt(vp.getPp().getCampoTextoPeso().getText());
			ExceptionChecker.verificarNumeroNegativo(peso);

			int precio = Integer.parseInt(vp.getPp().getCampoTextoPrecio().getText());
			ExceptionChecker.verificarNumeroNegativo(precio);

			boolean tieneLevadura = vp.getPp().getCampoTextoLevadura().isSelected();

			String tipoHarina = vp.getPp().getCampoTextoHarina().getText();
			ExceptionChecker.verificarCampoVacio(tipoHarina);
			ExceptionChecker.verificarSoloLetras(tipoHarina);

			PanDTO actPan = new PanDTO(nombre, cantidad, peso, precio, tieneLevadura, tipoHarina);
			pDAO.actualizar(index, actPan);

			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoPan"));
			limpiarCamposPan();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.menu.errorCantidadPesoPrecio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listaPan() {
		mostrarLista(pDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaPan"));
	}

	private void contarPan() {
		JOptionPane.showMessageDialog(vp,
				prop.getProperty("panaderia.aplicativo.consola.menu.tipoPanesInventario") + " " + pDAO.contar() + "\n"
						+ prop.getProperty("panaderia.aplicativo.consola.menu.numeroPanesInventario") + " "
						+ pDAO.sumarCantidad());
	}

	private void verificarVencimientoPan() {
		String inputPos = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPan"));
		try {
			int index = Integer.parseInt(inputPos);
			if (index < 0 || index >= pDAO.getListaPanes().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String inputHoras = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
			int horas = Integer.parseInt(inputHoras);

			String resultado = pDAO.verificarVencimiento(index, horas);
			JOptionPane.showMessageDialog(vp, resultado);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void sumarPrecioPan() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		listaProductos.addAll(pDAO.getListaPanes());
		sumarPreciosGUI(listaProductos);
	}

	private void verificarEndurecimientoPan() {
		String inputPos = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPan"));
		try {
			int index = Integer.parseInt(inputPos);
			if (index < 0 || index >= pDAO.getListaPanes().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String inputHoras = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
			int horas = Integer.parseInt(inputHoras);

			String resultado = pDAO.verificarEndurecimiento(index, horas);
			JOptionPane.showMessageDialog(vp, resultado);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void prepararIngredientesPan() {
		ArrayList<String> ingredientes = new ArrayList<>();

		if (pDAO.getListaPanes().isEmpty()) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.listaVaciaPanes"));
			return;
		}
		try {
			int index = Integer.parseInt(
					JOptionPane.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPan")));
			if (index < 0 || index >= pDAO.getListaPanes().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				return;
			}
			int cantidad = Integer.parseInt(JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadIngredientes")));
			for (int i = 0; i < cantidad; i++) {
				ingredientes.add(JOptionPane
						.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.menu.promptIngrediente")));
			}
			JOptionPane.showMessageDialog(vp, pDAO.prepararIngredientes(index, ingredientes));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"));
		}
	}

	// postre
	private void registroPostre() {
		try {
			String nombre = vp.getPpt().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombre);
			ExceptionChecker.verificarSoloLetras(nombre);

			float cantidad = Float.parseFloat(vp.getPpt().getCampoTextoCantidad().getText());
			ExceptionChecker.verificarNumeroNegativo(cantidad);

			int peso = Integer.parseInt(vp.getPpt().getCampoTextoPeso().getText());
			ExceptionChecker.verificarNumeroNegativo(peso);

			int precio = Integer.parseInt(vp.getPpt().getCampoTextoPrecio().getText());
			ExceptionChecker.verificarNumeroNegativo(precio);

			boolean tieneAzucar = vp.getPpt().getCampoTextoAzucar().isSelected();

			String tipoPostre = vp.getPpt().getCampoTextoTipoPostre().getText();
			ExceptionChecker.verificarCampoVacio(tipoPostre);
			ExceptionChecker.verificarSoloLetras(tipoPostre);

			PostreDTO nuevoPostre = new PostreDTO(nombre, cantidad, peso, precio, tieneAzucar, tipoPostre);
			ptDAO.crear(nuevoPostre);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.exitoPostre"));
			limpiarCamposPostre();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.menu.errorCantidadPesoPrecio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCamposPostre() {
		vp.getPpt().getCampoTextoNombre().setText("");
		vp.getPpt().getCampoTextoCantidad().setText("");
		vp.getPpt().getCampoTextoPeso().setText("");
		vp.getPpt().getCampoTextoPrecio().setText("");
		vp.getPpt().getCampoTextoAzucar().setSelected(false);
		vp.getPpt().getCampoTextoTipoPostre().setText("");
	}

	private void eliminarPostre() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= ptDAO.getListaPostres().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
			} else {
				ptDAO.eliminar(index);
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoPostre"));
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarPostre() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= ptDAO.getListaPostres().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String nombre = vp.getPpt().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombre);
			ExceptionChecker.verificarSoloLetras(nombre);

			float cantidad = Float.parseFloat(vp.getPpt().getCampoTextoCantidad().getText());
			ExceptionChecker.verificarNumeroNegativo(cantidad);

			int peso = Integer.parseInt(vp.getPpt().getCampoTextoPeso().getText());
			ExceptionChecker.verificarNumeroNegativo(peso);

			int precio = Integer.parseInt(vp.getPpt().getCampoTextoPrecio().getText());
			ExceptionChecker.verificarNumeroNegativo(precio);

			boolean tieneAzucar = vp.getPpt().getCampoTextoAzucar().isSelected();

			String tipoPostre = vp.getPpt().getCampoTextoTipoPostre().getText();
			ExceptionChecker.verificarCampoVacio(tipoPostre);
			ExceptionChecker.verificarSoloLetras(tipoPostre);

			PostreDTO actPostre = new PostreDTO(nombre, cantidad, peso, precio, tieneAzucar, tipoPostre);
			ptDAO.actualizar(index, actPostre);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoPostre"));
			limpiarCamposPostre();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.menu.errorCantidadPesoPrecio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listaPostre() {
		mostrarLista(ptDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaPostre"));
	}

	private void contarPostre() {
		JOptionPane.showMessageDialog(vp,
				prop.getProperty("panaderia.aplicativo.interfaz.menu.tipoPostresInventario") + " " + ptDAO.contar()
						+ "\n" + prop.getProperty("panaderia.aplicativo.consola.menu.numeroPostresInventario") + " "
						+ ptDAO.sumarCantidad());
	}

	private void verificarVencimientoPostre() {
		String inputPos = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPostre"));
		try {
			int index = Integer.parseInt(inputPos);
			if (index < 0 || index >= ptDAO.getListaPostres().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String inputHoras = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
			int horas = Integer.parseInt(inputHoras);

			String resultado = ptDAO.verificarVencimiento(index, horas);
			JOptionPane.showMessageDialog(vp, resultado);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void sumarPrecioPostre() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		listaProductos.addAll(ptDAO.getListaPostres());
		sumarPreciosGUI(listaProductos);
	}

	private void verificarDeterioroPostre() {
		String inputPos = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPostre"));
		try {
			int index = Integer.parseInt(inputPos);
			if (index < 0 || index >= ptDAO.getListaPostres().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String inputMot = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.motivoDaniPostre") + "\n"
							+ prop.getProperty("panaderia.aplicativo.consola.menu.opcionesMotivoPostre"));
			int motivo = Integer.parseInt(inputMot);

			String resultado = ptDAO.verificarDeterioro(index, motivo);
			JOptionPane.showMessageDialog(vp, resultado);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(vp, "Opción no válida",
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void prepararIngredientesPostre() {
		ArrayList<String> ingredientes = new ArrayList<>();

		if (ptDAO.getListaPostres().isEmpty()) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.listaVaciaPostres"));
			return;
		}
		try {
			int index = Integer.parseInt(
					JOptionPane.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPostre")));
			if (index < 0 || index >= ptDAO.getListaPostres().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				return;
			}
			int cantidad = Integer.parseInt(JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadIngredientes")));

			for (int i = 0; i < cantidad; i++) {
				ingredientes.add(JOptionPane
						.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.menu.promptIngrediente")));
			}
			JOptionPane.showMessageDialog(vp, ptDAO.prepararIngredientes(index, ingredientes));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"));
		}
	}

	// galleta
	private void registroGalleta() {
		try {
			String nombre = vp.getPg().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombre);
			ExceptionChecker.verificarSoloLetras(nombre);

			float cantidad = Float.parseFloat(vp.getPg().getCampoTextoCantidad().getText());
			ExceptionChecker.verificarNumeroNegativo(cantidad);

			int peso = Integer.parseInt(vp.getPg().getCampoTextoPeso().getText());
			ExceptionChecker.verificarNumeroNegativo(peso);

			int precio = Integer.parseInt(vp.getPg().getCampoTextoPrecio().getText());
			ExceptionChecker.verificarNumeroNegativo(precio);

			boolean tieneTopings = vp.getPg().getCampoTextoTopings().isSelected();
			boolean tieneRelleno = vp.getPg().getCampoTextoRelleno().isSelected();

			GalletaDTO nuevaGalleta = new GalletaDTO(nombre, cantidad, peso, precio, tieneTopings, tieneRelleno);
			gDAO.crear(nuevaGalleta);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.exitoGalleta"));
			limpiarCamposGalleta();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.menu.errorCantidadPesoPrecio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCamposGalleta() {
		vp.getPg().getCampoTextoNombre().setText("");
		vp.getPg().getCampoTextoCantidad().setText("");
		vp.getPg().getCampoTextoPeso().setText("");
		vp.getPg().getCampoTextoPrecio().setText("");
		vp.getPg().getCampoTextoTopings().setSelected(false);
		vp.getPg().getCampoTextoRelleno().setSelected(false);
	}

	private void eliminarGalleta() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= gDAO.getListaGalletas().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
			} else {
				gDAO.eliminar(index);
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoGalleta"));
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarGalleta() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= gDAO.getListaGalletas().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String nombre = vp.getPg().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombre);
			ExceptionChecker.verificarSoloLetras(nombre);

			float cantidad = Float.parseFloat(vp.getPg().getCampoTextoCantidad().getText());
			ExceptionChecker.verificarNumeroNegativo(cantidad);

			int peso = Integer.parseInt(vp.getPg().getCampoTextoPeso().getText());
			ExceptionChecker.verificarNumeroNegativo(peso);

			int precio = Integer.parseInt(vp.getPg().getCampoTextoPrecio().getText());
			ExceptionChecker.verificarNumeroNegativo(precio);

			boolean tieneTopings = vp.getPg().getCampoTextoTopings().isSelected();
			boolean tieneRelleno = vp.getPg().getCampoTextoRelleno().isSelected();

			GalletaDTO galActualizada = new GalletaDTO(nombre, cantidad, peso, precio, tieneTopings, tieneRelleno);
			gDAO.actualizar(index, galActualizada);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoGalleta"));
			limpiarCamposGalleta();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.menu.errorCantidadPesoPrecio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listaGalleta() {
		mostrarLista(gDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaGalleta"));
	}

	private void contarGalleta() {
		JOptionPane.showMessageDialog(vp,
				prop.getProperty("panaderia.aplicativo.interfaz.menu.tipoGalletasInventario") + " " + gDAO.contar()
						+ "\n" + prop.getProperty("panaderia.aplicativo.consola.menu.numeroGalletasInventario") + " "
						+ gDAO.sumarCantidad());
	}

	private void verificarVencimientoGalleta() {
		String inputPos = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionGalleta"));
		try {
			int index = Integer.parseInt(inputPos);
			if (index < 0 || index >= gDAO.getListaGalletas().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String inputHoras = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
			int horas = Integer.parseInt(inputHoras);

			String resultado = gDAO.verificarVencimiento(index, horas);
			JOptionPane.showMessageDialog(vp, resultado);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void sumarPrecioGalleta() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		listaProductos.addAll(gDAO.getListaGalletas());
		sumarPreciosGUI(listaProductos);
	}

	private void verificarEndurecimientoGalleta() {
		String inputPos = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionGalleta"));
		try {
			int index = Integer.parseInt(inputPos);
			if (index < 0 || index >= gDAO.getListaGalletas().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String inputHoras = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
			int horas = Integer.parseInt(inputHoras);

			String resultado = gDAO.verificarEndurecimiento(index, horas);
			JOptionPane.showMessageDialog(vp, resultado);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void prepararIngredientesGalleta() {
		ArrayList<String> ingredientes = new ArrayList<>();

		if (gDAO.getListaGalletas().isEmpty()) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.listaVaciaGalletas"));
			return;
		}
		try {
			int index = Integer.parseInt(
					JOptionPane.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionGalleta")));
			if (index < 0 || index >= gDAO.getListaGalletas().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				return;
			}
			int cantidad = Integer.parseInt(JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadIngredientes")));

			for (int i = 0; i < cantidad; i++) {
				ingredientes.add(JOptionPane
						.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.menu.promptIngrediente")));
			}
			JOptionPane.showMessageDialog(vp, gDAO.prepararIngredientes(index, ingredientes));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"));
		}
	}

	// lacteo
	private void registroLacteo() {
		try {
			String nombre = vp.getPl().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombre);
			ExceptionChecker.verificarSoloLetras(nombre);

			float cantidad = Float.parseFloat(vp.getPl().getCampoTextoCantidad().getText());
			ExceptionChecker.verificarNumeroNegativo(cantidad);

			int peso = Integer.parseInt(vp.getPl().getCampoTextoPeso().getText());
			ExceptionChecker.verificarNumeroNegativo(peso);

			int precio = Integer.parseInt(vp.getPl().getCampoTextoPrecio().getText());
			ExceptionChecker.verificarNumeroNegativo(precio);

			String marca = vp.getPl().getCampoTextoMarca().getText();
			ExceptionChecker.verificarCampoVacio(marca);
			ExceptionChecker.verificarSoloLetras(marca);

			boolean tieneCambio = vp.getPl().getCampoTextoCambio().isSelected();

			LacteoDTO nuevoLacteo = new LacteoDTO(nombre, cantidad, peso, precio, marca, tieneCambio);
			lDAO.crear(nuevoLacteo);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.exitoLacteo"));
			limpiarCamposLacteo();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.menu.errorCantidadPesoPrecio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCamposLacteo() {
		vp.getPl().getCampoTextoNombre().setText("");
		vp.getPl().getCampoTextoCantidad().setText("");
		vp.getPl().getCampoTextoPeso().setText("");
		vp.getPl().getCampoTextoPrecio().setText("");
		vp.getPl().getCampoTextoMarca().setText("");
		vp.getPl().getCampoTextoCambio().setSelected(false);
	}

	private void eliminarLacteo() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= lDAO.getListaLacteos().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
			} else {
				lDAO.eliminar(index);
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoLacteo"));
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarLacteo() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= lDAO.getListaLacteos().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String nombre = vp.getPl().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombre);
			ExceptionChecker.verificarSoloLetras(nombre);

			float cantidad = Float.parseFloat(vp.getPl().getCampoTextoCantidad().getText());
			ExceptionChecker.verificarNumeroNegativo(cantidad);

			int peso = Integer.parseInt(vp.getPl().getCampoTextoPeso().getText());
			ExceptionChecker.verificarNumeroNegativo(peso);

			int precio = Integer.parseInt(vp.getPl().getCampoTextoPrecio().getText());
			ExceptionChecker.verificarNumeroNegativo(precio);

			String marca = vp.getPl().getCampoTextoMarca().getText();
			ExceptionChecker.verificarCampoVacio(marca);
			ExceptionChecker.verificarSoloLetras(marca);

			boolean tieneCambio = vp.getPl().getCampoTextoCambio().isSelected();

			LacteoDTO lacActualizado = new LacteoDTO(nombre, cantidad, peso, precio, marca, tieneCambio);
			lDAO.actualizar(index, lacActualizado);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoLacteo"));
			limpiarCamposLacteo();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.interfaz.menu.errorCantidadPesoPrecio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listaLacteo() {
		mostrarLista(lDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaLacteo"));
	}

	private void contarLacteo() {
		JOptionPane.showMessageDialog(vp,
				prop.getProperty("panaderia.aplicativo.interfaz.menu.tipoLacteosInventario") + " " + lDAO.contar()
						+ "\n" + prop.getProperty("panaderia.aplicativo.consola.menu.numeroLacteosInventario") + " "
						+ lDAO.sumarCantidad());
	}

	private void verificarVencimientoLacteo() {
		String inputPos = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionLacteo"));
		try {
			int index = Integer.parseInt(inputPos);
			if (index < 0 || index >= lDAO.getListaLacteos().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String inputHoras = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
			int horas = Integer.parseInt(inputHoras);

			String resultado = lDAO.verificarVencimiento(index, horas);
			JOptionPane.showMessageDialog(vp, resultado);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void sumarPrecioLacteo() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		listaProductos.addAll(lDAO.getListaLacteos());
		sumarPreciosGUI(listaProductos);
	}

	private void verificarCaducacionLacteo() {
		String inputPos = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionLacteo"));
		try {
			int index = Integer.parseInt(inputPos);
			if (index < 0 || index >= lDAO.getListaLacteos().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String inputMot = JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.motivoDaniLacteo") + "\n"
							+ prop.getProperty("panaderia.aplicativo.consola.menu.opcionesMotivoLacteo"));
			int motivo = Integer.parseInt(inputMot);

			String resultado = lDAO.verificarCortamiento(index, motivo);
			JOptionPane.showMessageDialog(vp, resultado);

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(vp, "Opción no válida",
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void prepararIngredientesLacteo() {
		ArrayList<String> ingredientes = new ArrayList<>();

		if (lDAO.getListaLacteos().isEmpty()) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.listaVaciaLacteos"));
			return;
		}
		try {
			int index = Integer.parseInt(
					JOptionPane.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionLacteo")));
			if (index < 0 || index >= lDAO.getListaLacteos().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				return;
			}
			int cantidad = Integer.parseInt(JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadIngredientes")));

			for (int i = 0; i < cantidad; i++) {
				ingredientes.add(JOptionPane
						.showInputDialog(prop.getProperty("panaderia.aplicativo.interfaz.menu.promptIngrediente")));
			}
			JOptionPane.showMessageDialog(vp, lDAO.prepararIngredientes(index, ingredientes));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"));
		}
	}

	// panadero
	private void registroPanadero() {
		try {
			String nombreCompleto = vp.getPpn().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombreCompleto);
			ExceptionChecker.verificarSoloLetras(nombreCompleto);

			String genero = vp.getPpn().getCampoTextoGenero().getText();
			ExceptionChecker.verificarCampoVacio(genero);
			ExceptionChecker.verificarSoloLetras(genero);

			int edad = Integer.parseInt(vp.getPpn().getCampoTextoEdad().getText());
			ExceptionChecker.verificarNumeroNegativo(edad);
			ExceptionChecker.verificarEdadMaxima(edad);

			String numTelefono = vp.getPpn().getCampoTextoTelefono().getText();
			ExceptionChecker.verificarFormatoTelefono(numTelefono);

			float salario = Float.parseFloat(vp.getPpn().getCampoTextoSalario().getText());
			ExceptionChecker.verificarNumeroNegativo(salario);

			int panesHorneadosPorDia = Integer.parseInt(vp.getPpn().getCampoTextoDia().getText());
			ExceptionChecker.verificarNumeroNegativo(panesHorneadosPorDia);

			String especialidadPan = vp.getPpn().getCampoTextoEspecialidad().getText();
			ExceptionChecker.verificarCampoVacio(especialidadPan);
			ExceptionChecker.verificarSoloLetras(especialidadPan);

			PanaderoDTO nuevoPana = new PanaderoDTO(nombreCompleto, genero, edad, numTelefono, salario,
					panesHorneadosPorDia, especialidadPan);
			pdDAO.crear(nuevoPana);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.exitoPanadero"));
			limpiarCamposPanadero();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorEdadSalario"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (MaxValueExceededException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCamposPanadero() {
		vp.getPpn().getCampoTextoNombre().setText("");
		vp.getPpn().getCampoTextoGenero().setText("");
		vp.getPpn().getCampoTextoEdad().setText("");
		vp.getPpn().getCampoTextoTelefono().setText("");
		vp.getPpn().getCampoTextoSalario().setText("");
		vp.getPpn().getCampoTextoDia().setText("");
		vp.getPpn().getCampoTextoEspecialidad().setText("");
	}

	private void eliminarPanadero() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= pdDAO.getListaPanaderos().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
			} else {
				pdDAO.eliminar(index);
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoPanadero"));
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarPanadero() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= pdDAO.getListaPanaderos().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String nombreCompleto = vp.getPpn().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombreCompleto);
			ExceptionChecker.verificarSoloLetras(nombreCompleto);

			String genero = vp.getPpn().getCampoTextoGenero().getText();
			ExceptionChecker.verificarCampoVacio(genero);
			ExceptionChecker.verificarSoloLetras(genero);

			int edad = Integer.parseInt(vp.getPpn().getCampoTextoEdad().getText());
			ExceptionChecker.verificarNumeroNegativo(edad);
			ExceptionChecker.verificarEdadMaxima(edad);

			String numTelefono = vp.getPpn().getCampoTextoTelefono().getText();
			ExceptionChecker.verificarFormatoTelefono(numTelefono);

			float salario = Float.parseFloat(vp.getPpn().getCampoTextoSalario().getText());
			ExceptionChecker.verificarNumeroNegativo(salario);

			int panesHorneadosPorDia = Integer.parseInt(vp.getPpn().getCampoTextoDia().getText());
			ExceptionChecker.verificarNumeroNegativo(panesHorneadosPorDia);

			String especialidadPan = vp.getPpn().getCampoTextoEspecialidad().getText();
			ExceptionChecker.verificarCampoVacio(especialidadPan);
			ExceptionChecker.verificarSoloLetras(especialidadPan);

			PanaderoDTO panaActualizado = new PanaderoDTO(nombreCompleto, genero, edad, numTelefono, salario,
					panesHorneadosPorDia, especialidadPan);
			pdDAO.actualizar(index, panaActualizado);
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoPanadero"));
			limpiarCamposPanadero();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorEdadSalario"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (MaxValueExceededException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listaPanadero() {
		mostrarLista(pdDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaPanadero"));

	}

	private void contarPanadero() {
		JOptionPane.showMessageDialog(vp,
				prop.getProperty("panaderia.aplicativo.consola.menu.numeroPanaderos") + " " + pdDAO.contar() + " \n"
						+ prop.getProperty("panaderia.aplicativo.consola.menu.nombresPanaderos")
						+ pdDAO.sumarCantidad());
	}

	private void calcularSalarioPanadero() {
		try {
			int index = Integer.parseInt(JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionSalarioPanadero")));
			if (index < 0 || index >= pdDAO.getListaPanaderos().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaCorta"));
			} else {
				JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.salarioPorDia")
						+ " " + pdDAO.calcularSalarioPorDia(index));
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"));
		}
	}

	// repostero
	private void registroRepostero() {
		try {
			String nombreCompleto = vp.getPr().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombreCompleto);
			ExceptionChecker.verificarSoloLetras(nombreCompleto);

			String genero = vp.getPr().getCampoTextoGenero().getText();
			ExceptionChecker.verificarCampoVacio(genero);
			ExceptionChecker.verificarSoloLetras(genero);

			int edad = Integer.parseInt(vp.getPr().getCampoTextoEdad().getText());
			ExceptionChecker.verificarNumeroNegativo(edad);
			ExceptionChecker.verificarEdadMaxima(edad);

			String numTelefono = vp.getPr().getCampoTextoTelefono().getText();
			ExceptionChecker.verificarFormatoTelefono(numTelefono);

			float salario = Float.parseFloat(vp.getPr().getCampoTextoSalario().getText());
			ExceptionChecker.verificarNumeroNegativo(salario);

			int nivelCreatividad = Integer.parseInt(vp.getPr().getCampoTextoCreatividad().getText());
			ExceptionChecker.verificarNumeroNegativo(nivelCreatividad);

			boolean manejaTecnicasAvanzadas = vp.getPr().getCampoTextoTecnicas().isSelected();

			ReposteroDTO nuevoRepo = new ReposteroDTO(nombreCompleto, genero, edad, numTelefono, salario,
					nivelCreatividad, manejaTecnicasAvanzadas);
			rDAO.crear(nuevoRepo);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.exitoRepostero"));
			limpiarCamposRepostero();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorEdadSalario"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (MaxValueExceededException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCamposRepostero() {
		vp.getPr().getCampoTextoNombre().setText("");
		vp.getPr().getCampoTextoGenero().setText("");
		vp.getPr().getCampoTextoEdad().setText("");
		vp.getPr().getCampoTextoTelefono().setText("");
		vp.getPr().getCampoTextoSalario().setText("");
		vp.getPr().getCampoTextoCreatividad().setText("");
		vp.getPr().getCampoTextoTecnicas().setSelected(false);
	}

	private void eliminarRepostero() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= rDAO.getListaReposteros().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
			} else {
				rDAO.eliminar(index);
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoRepostero"));
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarRepostero() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= rDAO.getListaReposteros().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String nombreCompleto = vp.getPr().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombreCompleto);
			ExceptionChecker.verificarSoloLetras(nombreCompleto);

			String genero = vp.getPr().getCampoTextoGenero().getText();
			ExceptionChecker.verificarCampoVacio(genero);
			ExceptionChecker.verificarSoloLetras(genero);

			int edad = Integer.parseInt(vp.getPr().getCampoTextoEdad().getText());
			ExceptionChecker.verificarNumeroNegativo(edad);
			ExceptionChecker.verificarEdadMaxima(edad);

			String numTelefono = vp.getPr().getCampoTextoTelefono().getText();
			ExceptionChecker.verificarFormatoTelefono(numTelefono);

			float salario = Float.parseFloat(vp.getPr().getCampoTextoSalario().getText());
			ExceptionChecker.verificarNumeroNegativo(salario);

			int nivelCreatividad = Integer.parseInt(vp.getPr().getCampoTextoCreatividad().getText());
			ExceptionChecker.verificarNumeroNegativo(nivelCreatividad);

			boolean manejaTecnicasAvanzadas = vp.getPr().getCampoTextoTecnicas().isSelected();

			ReposteroDTO repoActualizado = new ReposteroDTO(nombreCompleto, genero, edad, numTelefono, salario,
					nivelCreatividad, manejaTecnicasAvanzadas);
			rDAO.actualizar(index, repoActualizado);
			JOptionPane.showMessageDialog(vp,
					prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoRepostero"));
			limpiarCamposRepostero();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorEdadSalario"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (MaxValueExceededException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listaRepostero() {
		mostrarLista(rDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaRepostero"));
	}

	private void contarRepostero() {
		JOptionPane.showMessageDialog(vp,
				prop.getProperty("panaderia.aplicativo.consola.menu.numeroReposteros") + " " + rDAO.contar() + " \n"
						+ prop.getProperty("panaderia.aplicativo.consola.menu.nombresReposteros")
						+ rDAO.sumarCantidad());
	}

	private void calcularSalarioRepostero() {
		try {
			int index = Integer.parseInt(JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionSalarioRepostero")));
			if (index < 0 || index >= rDAO.getListaReposteros().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaCorta"));
			} else {
				JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.salarioPorDia")
						+ " " + rDAO.calcularSalarioPorDia(index));
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"));
		}
	}

	// mesero
	private void registroMesero() {
		try {
			String nombreCompleto = vp.getPms().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombreCompleto);
			ExceptionChecker.verificarSoloLetras(nombreCompleto);

			String genero = vp.getPms().getCampoTextoGenero().getText();
			ExceptionChecker.verificarCampoVacio(genero);
			ExceptionChecker.verificarSoloLetras(genero);

			int edad = Integer.parseInt(vp.getPms().getCampoTextoEdad().getText());
			ExceptionChecker.verificarNumeroNegativo(edad);
			ExceptionChecker.verificarEdadMaxima(edad);

			String numTelefono = vp.getPms().getCampoTextoTelefono().getText();
			ExceptionChecker.verificarFormatoTelefono(numTelefono);

			float salario = Float.parseFloat(vp.getPms().getCampoTextoSalario().getText());
			ExceptionChecker.verificarNumeroNegativo(salario);

			boolean estaEstudianto = vp.getPms().getCampoTextoEstudia().isSelected();
			boolean experienciaEventosGrandes = vp.getPms().getCampoTextoEvento().isSelected();

			MeseroDTO nuevoMese = new MeseroDTO(nombreCompleto, genero, edad, numTelefono, salario, estaEstudianto,
					experienciaEventosGrandes);
			mDAO.crear(nuevoMese);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.exitoMesero"));
			limpiarCamposMesero();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorEdadSalario"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (MaxValueExceededException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCamposMesero() {
		vp.getPms().getCampoTextoNombre().setText("");
		vp.getPms().getCampoTextoGenero().setText("");
		vp.getPms().getCampoTextoEdad().setText("");
		vp.getPms().getCampoTextoTelefono().setText("");
		vp.getPms().getCampoTextoSalario().setText("");
		vp.getPms().getCampoTextoEstudia().setSelected(false);
		vp.getPms().getCampoTextoEvento().setSelected(false);
	}

	private void eliminarMesero() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= mDAO.getListaMeseros().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
			} else {
				mDAO.eliminar(index);
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoMesero"));
			}

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarMesero() {
		String input = JOptionPane
				.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));

		try {
			int index = Integer.parseInt(input);

			if (index < 0 || index >= mDAO.getListaMeseros().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"),
						prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
				return;
			}
			String nombreCompleto = vp.getPms().getCampoTextoNombre().getText();
			ExceptionChecker.verificarCampoVacio(nombreCompleto);
			ExceptionChecker.verificarSoloLetras(nombreCompleto);

			String genero = vp.getPms().getCampoTextoGenero().getText();
			ExceptionChecker.verificarCampoVacio(genero);
			ExceptionChecker.verificarSoloLetras(genero);

			int edad = Integer.parseInt(vp.getPms().getCampoTextoEdad().getText());
			ExceptionChecker.verificarNumeroNegativo(edad);
			ExceptionChecker.verificarEdadMaxima(edad);

			String numTelefono = vp.getPms().getCampoTextoTelefono().getText();
			ExceptionChecker.verificarFormatoTelefono(numTelefono);

			float salario = Float.parseFloat(vp.getPms().getCampoTextoSalario().getText());
			ExceptionChecker.verificarNumeroNegativo(salario);

			boolean estaEstudianto = vp.getPms().getCampoTextoEstudia().isSelected();
			boolean experienciaEventosGrandes = vp.getPms().getCampoTextoEvento().isSelected();

			MeseroDTO meseActualizado = new MeseroDTO(nombreCompleto, genero, edad, numTelefono, salario,
					estaEstudianto, experienciaEventosGrandes);
			mDAO.actualizar(index, meseActualizado);
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoMesero"));
			limpiarCamposMesero();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorEdadSalario"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (NegativeNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (EmptyFieldException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidPhoneNumberException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (MaxValueExceededException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		} catch (InvalidNameException ex) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"),
					prop.getProperty("panaderia.aplicativo.interfaz.menu.tituloError"), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void listaMesero() {
		mostrarLista(mDAO.mostrar(), prop.getProperty("panaderia.aplicativo.consola.menu.listaMesero"));
	}

	private void contarMesero() {
		JOptionPane.showMessageDialog(vp,
				prop.getProperty("panaderia.aplicativo.consola.menu.numeroMeseros") + " " + mDAO.contar() + " \n"
						+ prop.getProperty("panaderia.aplicativo.consola.menu.nombresMeseros") + mDAO.sumarCantidad());
	}

	private void calcularSalarioMesero() {
		try {
			int index = Integer.parseInt(JOptionPane
					.showInputDialog(prop.getProperty("panaderia.aplicativo.consola.menu.posicionSalarioMesero")));
			if (index < 0 || index >= mDAO.getListaMeseros().size()) {
				JOptionPane.showMessageDialog(vp,
						prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaCorta"));
			} else {
				JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.salarioPorDia")
						+ " " + mDAO.calcularSalarioPorDia(index));
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(vp, prop.getProperty("panaderia.aplicativo.interfaz.menu.errorNumero"));
		}
	}

	// consola
	public void iniciar() {
		con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.tituloPrincipal"));
		// CICLO NOMBRADO
		cicloPrincipalMenu: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.subtituloPrincipal"));
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.seleccioneOpcion"));
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesPrincipal"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();
			switch (opcion) {
			case 1: {
				administrarCliente();
				break;
			}
			case 2: {
				administrarPanaderia();
				break;
			}
			case 3: {
				administarPersonal();
				break;
			}
			case 4: {
				break cicloPrincipalMenu;
			}
			default:
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.seleccionNoValida"));
			}
		}

	}

	private void administrarCliente() {
		con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.tituloPrincipal"));
		cicloPrincipalMenu: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.subtituloPrincipal"));
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.seleccioneOpcion"));
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesCliente"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();
			switch (opcion) {
			case 1: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.comprarPanTitulo"));
				if (pDAO.getListaPanes().isEmpty()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.panVacio"));
					break;
				}
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaPanesDisponibles"));
				con.escribirConSalto(pDAO.mostrar());
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionComprarPan"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= pDAO.getListaPanes().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.ingreseCantidadComprar"));
					float cantidad = con.leerNumeroFlotante();
					con.quemarLinea();
					con.escribirConSalto(pDAO.venderPan(index, cantidad));
				}
				break;
			}
			case 2: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.comprarPostreTitulo"));
				if (ptDAO.getListaPostres().isEmpty()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.postreVacio"));
					break;
				}
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaPostresDisponibles"));
				con.escribirConSalto(ptDAO.mostrar());
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionComprarPostre"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= ptDAO.getListaPostres().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.ingreseCantidadComprar"));
					float cantidad = con.leerNumeroFlotante();
					con.quemarLinea();
					con.escribirConSalto(ptDAO.venderPostre(index, cantidad));
				}
				break;
			}
			case 3: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.comprarGalletaTitulo"));
				if (gDAO.getListaGalletas().isEmpty()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.galletaVacio"));
					break;
				}
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaGalletasDisponibles"));
				con.escribirConSalto(gDAO.mostrar());
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionComprarGalleta"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= gDAO.getListaGalletas().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.ingreseCantidadComprar"));
					float cantidad = con.leerNumeroFlotante();
					con.quemarLinea();
					con.escribirConSalto(gDAO.venderGalleta(index, cantidad));
				}
				break;
			}
			case 4: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.comprarLacteoTitulo"));
				if (lDAO.getListaLacteos().isEmpty()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.lacteoVacio"));
					break;
				}
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaLacteosDisponibles"));
				con.escribirConSalto(lDAO.mostrar());
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionComprarLacteo"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= lDAO.getListaLacteos().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.ingreseCantidadComprar"));
					float cantidad = con.leerNumeroFlotante();
					con.quemarLinea();
					con.escribirConSalto(lDAO.venderLacteo(index, cantidad));
				}
				break;
			}
			case 99: {
				return;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}
		}
	}

	private void administrarPanaderia() {
		con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.tituloPrincipal"));
		// CICLO NOMBRADO
		cicloPrincipal: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.tituloProductos"));
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.seleccioneOpcion"));
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesProductos"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();

			switch (opcion) {
			case 1: {
				administrarPan();
				break;
			}
			case 2: {
				administrarPostre();
				break;
			}
			case 3: {
				administrarGalleta();
				break;
			}
			case 4: {
				administrarLacteo();
				break;
			}
			case 5: {
				ArrayList<Producto> listaProductos = new ArrayList<Producto>();
				listaProductos.addAll(pDAO.getListaPanes());
				listaProductos.addAll(ptDAO.getListaPostres());
				listaProductos.addAll(gDAO.getListaGalletas());
				listaProductos.addAll(lDAO.getListaLacteos());
				sumarPreciosConsola(listaProductos);
				break;
			}
			case 6: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.buscarIngreseLetras"));
				String letra = con.leerLinea();
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.buscarResultados"));
				con.escribirConSalto(pDAO.buscarProducto(letra));
				con.escribirConSalto(ptDAO.buscarProducto(letra));
				con.escribirConSalto(gDAO.buscarProducto(letra));
				con.escribirConSalto(lDAO.buscarProducto(letra));
				break;
			}
			case 7: {
				consolaGenerarReportePDF();
				break;
			}
			case 8: {
				consolaGenerarReporteExcel();
				break;
			}
			case 9: {
				administrarProveedorArtesanal();
				break;
			}
			case 10: {
				administrarProveedorIndustrial();
				break;
			}
			case 11: {
				return;
			}
			default:
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.seleccionNoValida"));
			}
		}
	}

	private void administrarProveedorArtesanal() {
		cicloArtesanal: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.opcionArt"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();

			switch (opcion) {
			case 1: {
				try {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.agregar"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nombre"));
					String nombreEmpresa = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(nombreEmpresa);
					ExceptionChecker.verificarSoloLetras(nombreEmpresa);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nit"));
					int nit = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(nit);
					ExceptionChecker.verificarNIT(nit);
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.ciudad"));
					String ciudad = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(ciudad);
					ExceptionChecker.verificarSoloLetras(ciudad);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.telefono"));
					String telefono = con.leerLinea();
					ExceptionChecker.verificarFormatoTelefono(telefono);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.producto"));
					String tipoProducto = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(tipoProducto);
					ExceptionChecker.verificarSoloLetras(tipoProducto);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.invima"));
					boolean certificado = con.leerBooleano();

					ProveedorArtesanalDTO artNuevo = new ProveedorArtesanalDTO(nombreEmpresa, nit, ciudad, telefono,
							tipoProducto, certificado);
					aDAO.crear(artNuevo);
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.registroExito"));

				} catch (EmptyFieldException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
				} catch (NegativeNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
				} catch (InvalidPhoneNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"));
				} catch (InvalidNITNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.nitInvalido"));
				} catch (InvalidNameException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
				}
				break;
			}
			case 2: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.eliminar"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.eliPosicion"));
				int index = con.leerNumeroEntero();
				if (aDAO.eliminar(index)) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.eliExito"));
				} else {
					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"));
				}
				break;
			}
			case 3: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actualizar"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actPosicion"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= aDAO.getListaProveedoresArtesanales().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					try {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actualizar"));
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nombre"));
						String nombreEmpresa = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(nombreEmpresa);
						ExceptionChecker.verificarSoloLetras(nombreEmpresa);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nit"));
						int nit = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(nit);
						ExceptionChecker.verificarNIT(nit);
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.ciudad"));
						String ciudad = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(ciudad);
						ExceptionChecker.verificarSoloLetras(ciudad);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.telefono"));
						String telefono = con.leerLinea();
						ExceptionChecker.verificarFormatoTelefono(telefono);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.producto"));
						String tipoProducto = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(tipoProducto);
						ExceptionChecker.verificarSoloLetras(tipoProducto);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.invima"));
						boolean certificado = con.leerBooleano();

						ProveedorArtesanalDTO artActualizado = new ProveedorArtesanalDTO(nombreEmpresa, nit, ciudad,
								telefono, tipoProducto, certificado);
						aDAO.actualizar(index, artActualizado);
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actExito"));

					} catch (EmptyFieldException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
					} catch (NegativeNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
					} catch (InvalidPhoneNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"));
					} catch (InvalidNITNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.nitInvalido"));
					} catch (InvalidNameException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
					}
				}
				break;
			}
			case 4: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.listaArt"));
				con.escribirConSalto(aDAO.mostrar());
				break;
			}
			case 5: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.listaArt"));
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.interfaz.proveedor.numeroArt") + aDAO.contar());
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nombreArt") + aDAO.sumarCantidad());
				break;
			}
			case 99: {
				return;
			}
			default:
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.seleccionNoValida"));
			}
		}
	}

	private void administrarProveedorIndustrial() {
		cicloArtesanal: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.opcionInd"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();

			switch (opcion) {
			case 1: {
				try {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.agregar"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nombre"));
					String nombreEmpresa = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(nombreEmpresa);
					ExceptionChecker.verificarSoloLetras(nombreEmpresa);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nit"));
					int nit = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(nit);
					ExceptionChecker.verificarNIT(nit);
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.ciudad"));
					String ciudad = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(ciudad);
					ExceptionChecker.verificarSoloLetras(ciudad);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.telefono"));
					String telefono = con.leerLinea();
					ExceptionChecker.verificarFormatoTelefono(telefono);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.producto"));
					String tipoProducto = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(tipoProducto);
					ExceptionChecker.verificarSoloLetras(tipoProducto);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.invima"));
					boolean certificado = con.leerBooleano();

					ProveedorIndustrialDTO indNuevo = new ProveedorIndustrialDTO(nombreEmpresa, nit, ciudad, telefono,
							tipoProducto, certificado);
					iDAO.crear(indNuevo);
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.registroExito"));

				} catch (EmptyFieldException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
				} catch (NegativeNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
				} catch (InvalidPhoneNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"));
				} catch (InvalidNITNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.nitInvalido"));
				} catch (InvalidNameException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
				}
				break;
			}
			case 2: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.eliminar"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.eliPosicion"));
				int index = con.leerNumeroEntero();
				if (iDAO.eliminar(index)) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.eliExito"));
				} else {
					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"));
				}
				break;
			}
			case 3: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actualizar"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actPosicion"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= iDAO.getListaProveedoresIndustriales().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					try {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actualizar"));
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nombre"));
						String nombreEmpresa = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(nombreEmpresa);
						ExceptionChecker.verificarSoloLetras(nombreEmpresa);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nit"));
						int nit = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(nit);
						ExceptionChecker.verificarNIT(nit);
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.ciudad"));
						String ciudad = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(ciudad);
						ExceptionChecker.verificarSoloLetras(ciudad);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.telefono"));
						String telefono = con.leerLinea();
						ExceptionChecker.verificarFormatoTelefono(telefono);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.producto"));
						String tipoProducto = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(tipoProducto);
						ExceptionChecker.verificarSoloLetras(tipoProducto);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.invima"));
						boolean certificado = con.leerBooleano();

						ProveedorIndustrialDTO indActualizado = new ProveedorIndustrialDTO(nombreEmpresa, nit, ciudad,
								telefono, tipoProducto, certificado);
						iDAO.actualizar(index, indActualizado);
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.actExito"));

					} catch (EmptyFieldException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
					} catch (NegativeNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
					} catch (InvalidPhoneNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"));
					} catch (InvalidNITNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.nitInvalido"));
					} catch (InvalidNameException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
					}
				}
				break;
			}
			case 4: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.listaInd"));
				con.escribirConSalto(iDAO.mostrar());
				break;
			}
			case 5: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.proveedor.listaInd"));
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.interfaz.proveedor.numeroInd") + iDAO.contar());
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.interfaz.proveedor.nombreInd") + iDAO.sumarCantidad());
				break;
			}
			case 99: {
				return;
			}
			default:
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.seleccionNoValida"));
			}
		}
	}

	private void administarPersonal() {
		con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.tituloPrincipal"));
		// CICLO NOMBRADO
		cicloPrincipal: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.tituloPersonal"));
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.seleccioneOpcion"));
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesPersonal"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();

			switch (opcion) {
			case 1: {
				administrarPanadero();
				break;
			}
			case 2: {
				administrarRepostero();
				break;
			}
			case 3: {
				administrarMesero();
				break;
			}
			case 4: {
				return;
			}
			default:
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.seleccionNoValida"));
			}
		}

	}

	private void administrarPanadero() {
		cicloAdministrarPanadero: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesPanadero"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();
			switch (opcion) {
			case 1: {
				try {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.agregarPanadero"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreCompleto"));
					String nombreCompleto = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(nombreCompleto);
					ExceptionChecker.verificarSoloLetras(nombreCompleto);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.genero"));
					String genero = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(genero);
					ExceptionChecker.verificarSoloLetras(genero);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.edad"));
					int edad = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(edad);
					ExceptionChecker.verificarEdadMaxima(edad);
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.numTelefono"));
					String numTelefono = con.leerLinea();
					ExceptionChecker.verificarFormatoTelefono(numTelefono);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.salario"));
					float salario = con.leerNumeroFlotante();
					ExceptionChecker.verificarNumeroNegativo(salario);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.panPorDiaPanadero"));
					int panesHorneadosPorDia = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(panesHorneadosPorDia);
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.especialidadPanadero"));
					String especialidadPan = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(especialidadPan);
					ExceptionChecker.verificarSoloLetras(especialidadPan);

					PanaderoDTO nuevoPanadero = new PanaderoDTO(nombreCompleto, genero, edad, numTelefono, salario,
							panesHorneadosPorDia, especialidadPan);
					pdDAO.crear(nuevoPanadero);
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.exitoPanadero"));

				} catch (EmptyFieldException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
				} catch (NegativeNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
				} catch (InvalidPhoneNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"));
				} catch (MaxValueExceededException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"));
				} catch (InvalidNameException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
				}
				break;
			}
			case 2: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminarPanadero"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));
				int index = con.leerNumeroEntero();
				if (pdDAO.eliminar(index)) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoPanadero"));
				} else {
					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"));
				}
				break;
			}
			case 3: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= pdDAO.getListaPanaderos().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					try {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizarPanadero"));
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreCompleto"));
						String nombreCompleto = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(nombreCompleto);
						ExceptionChecker.verificarSoloLetras(nombreCompleto);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.genero"));
						String genero = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(genero);
						ExceptionChecker.verificarSoloLetras(genero);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.edad"));
						int edad = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(edad);
						ExceptionChecker.verificarEdadMaxima(edad);
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.numTelefono"));
						String numTelefono = con.leerLinea();
						ExceptionChecker.verificarFormatoTelefono(numTelefono);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.salario"));
						float salario = con.leerNumeroFlotante();
						ExceptionChecker.verificarNumeroNegativo(salario);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.panPorDiaPanadero"));
						int panesHorneadosPorDia = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(panesHorneadosPorDia);
						con.quemarLinea();

						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.especialidadPanadero"));
						String especialidadPan = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(especialidadPan);
						ExceptionChecker.verificarSoloLetras(especialidadPan);

						PanaderoDTO panaderoActualizado = new PanaderoDTO(nombreCompleto, genero, edad, numTelefono,
								salario, panesHorneadosPorDia, especialidadPan);
						pdDAO.actualizar(index, panaderoActualizado);
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoPanadero"));

					} catch (EmptyFieldException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
					} catch (NegativeNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
					} catch (InvalidPhoneNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"));
					} catch (MaxValueExceededException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"));
					} catch (InvalidNameException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
					}
				}
				break;
			}
			case 4: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaPanadero"));
				con.escribirConSalto(pdDAO.mostrar());
				break;
			}
			case 5: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaPanadero"));
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.consola.menu.numeroPanaderos") + pdDAO.contar());
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.consola.menu.nombresPanaderos") + pdDAO.sumarCantidad());
				break;
			}
			case 6: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionSalarioPanadero"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= pdDAO.getListaPanaderos().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaCorta"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.salarioPanadero")
							+ pdDAO.getListaPanaderos().get(index).calcularSalarioPorDia());
				}
				break;
			}
			case 99: {
				return;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}
		}
	}

	private void administrarRepostero() {
		cicloAdministrarRepostero: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesRepostero"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();
			switch (opcion) {
			case 1: {
				try {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.agregarRepostero"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreCompleto"));
					String nombreCompleto = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(nombreCompleto);
					ExceptionChecker.verificarSoloLetras(nombreCompleto);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.genero"));
					String genero = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(genero);
					ExceptionChecker.verificarSoloLetras(genero);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.edad"));
					int edad = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(edad);
					ExceptionChecker.verificarEdadMaxima(edad);
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.numTelefono"));
					String numTelefono = con.leerLinea();
					ExceptionChecker.verificarFormatoTelefono(numTelefono);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.salario"));
					float salario = con.leerNumeroFlotante();
					ExceptionChecker.verificarNumeroNegativo(salario);

					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.nivelCreatividadRepostero"));
					int nivelCreatividad = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(nivelCreatividad);
					con.quemarLinea();

					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.tecnicasAvanzadasRepostero"));
					boolean manejaTecnicasAvanzadas = con.leerBooleano();

					ReposteroDTO nuevoRepostero = new ReposteroDTO(nombreCompleto, genero, edad, numTelefono, salario,
							nivelCreatividad, manejaTecnicasAvanzadas);
					rDAO.crear(nuevoRepostero);
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.exitoRepostero"));

				} catch (EmptyFieldException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
				} catch (NegativeNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
				} catch (InvalidPhoneNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"));
				} catch (MaxValueExceededException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"));
				} catch (InvalidNameException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
				}
				break;
			}
			case 2: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminarRepostero"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));
				int index = con.leerNumeroEntero();
				if (rDAO.eliminar(index)) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoRepostero"));
				} else {
					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"));
				}
				break;
			}
			case 3: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= rDAO.getListaReposteros().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					try {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizarRepostero"));
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreCompleto"));
						String nombreCompleto = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(nombreCompleto);
						ExceptionChecker.verificarSoloLetras(nombreCompleto);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.genero"));
						String genero = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(genero);
						ExceptionChecker.verificarSoloLetras(genero);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.edad"));
						int edad = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(edad);
						ExceptionChecker.verificarEdadMaxima(edad);
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.numTelefono"));
						String numTelefono = con.leerLinea();
						ExceptionChecker.verificarFormatoTelefono(numTelefono);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.salario"));
						float salario = con.leerNumeroFlotante();
						ExceptionChecker.verificarNumeroNegativo(salario);

						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.nivelCreatividadRepostero"));
						int nivelCreatividad = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(nivelCreatividad);
						con.quemarLinea();

						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.tecnicasAvanzadasRepostero"));
						boolean manejaTecnicasAvanzadas = con.leerBooleano();

						ReposteroDTO reposteroActualizado = new ReposteroDTO(nombreCompleto, genero, edad, numTelefono,
								salario, nivelCreatividad, manejaTecnicasAvanzadas);
						rDAO.actualizar(index, reposteroActualizado);
						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoRepostero"));

					} catch (EmptyFieldException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
					} catch (NegativeNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
					} catch (InvalidPhoneNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"));
					} catch (MaxValueExceededException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"));
					} catch (InvalidNameException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
					}
				}
				break;
			}
			case 4: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaRepostero"));
				con.escribirConSalto(rDAO.mostrar());
				break;
			}
			case 5: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaRepostero"));
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.consola.menu.numeroReposteros") + rDAO.contar());
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.consola.menu.nombresReposteros") + rDAO.sumarCantidad());
				break;
			}
			case 6: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionSalarioRepostero"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= rDAO.getListaReposteros().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaCorta"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.salarioRepostero")
							+ rDAO.getListaReposteros().get(index).calcularSalarioPorDia());
				}
				break;
			}
			case 99: {
				return;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}
		}
	}

	private void administrarMesero() {
		cicloAdministrarMesero: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesMesero"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();
			switch (opcion) {
			case 1: {
				try {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.agregarMesero"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreCompleto"));
					String nombreCompleto = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(nombreCompleto);
					ExceptionChecker.verificarSoloLetras(nombreCompleto);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.genero"));
					String genero = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(genero);
					ExceptionChecker.verificarSoloLetras(genero);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.edad"));
					int edad = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(edad);
					ExceptionChecker.verificarEdadMaxima(edad);
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.numTelefono"));
					String numTelefono = con.leerLinea();
					ExceptionChecker.verificarFormatoTelefono(numTelefono);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.salario"));
					float salario = con.leerNumeroFlotante();
					ExceptionChecker.verificarNumeroNegativo(salario);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.estudiaMesero"));
					boolean estaEstudianto = con.leerBooleano();
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eventoMesero"));
					boolean experienciaEventosGrandes = con.leerBooleano();

					MeseroDTO nuevoMesero = new MeseroDTO(nombreCompleto, genero, edad, numTelefono, salario,
							estaEstudianto, experienciaEventosGrandes);
					mDAO.crear(nuevoMesero);
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.exitoMesero"));

				} catch (EmptyFieldException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
				} catch (NegativeNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
				} catch (InvalidPhoneNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"));
				} catch (MaxValueExceededException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"));
				} catch (InvalidNameException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
				}
				break;
			}
			case 2: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminarMesero"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));
				int index = con.leerNumeroEntero();
				if (mDAO.eliminar(index)) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoMesero"));
				} else {
					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"));
				}
				break;
			}
			case 3: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= mDAO.getListaMeseros().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					try {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizarMesero"));
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreCompleto"));
						String nombreCompleto = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(nombreCompleto);
						ExceptionChecker.verificarSoloLetras(nombreCompleto);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.genero"));
						String genero = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(genero);
						ExceptionChecker.verificarSoloLetras(genero);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.edad"));
						int edad = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(edad);
						ExceptionChecker.verificarEdadMaxima(edad);
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.numTelefono"));
						String numTelefono = con.leerLinea();
						ExceptionChecker.verificarFormatoTelefono(numTelefono);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.salario"));
						float salario = con.leerNumeroFlotante();
						ExceptionChecker.verificarNumeroNegativo(salario);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.estudiaMesero"));
						boolean estaEstudianto = con.leerBooleano();
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eventoMesero"));
						boolean experienciaEventosGrandes = con.leerBooleano();

						MeseroDTO meseroActualizado = new MeseroDTO(nombreCompleto, genero, edad, numTelefono, salario,
								estaEstudianto, experienciaEventosGrandes);
						mDAO.actualizar(index, meseroActualizado);
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoMesero"));

					} catch (EmptyFieldException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
					} catch (NegativeNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
					} catch (InvalidPhoneNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.telefonoInvalido"));
					} catch (MaxValueExceededException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.edadLimite"));
					} catch (InvalidNameException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
					}
				}
				break;
			}
			case 4: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaMesero"));
				con.escribirConSalto(mDAO.mostrar());
				break;
			}
			case 5: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaMesero"));
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.consola.menu.numeroMeseros") + mDAO.contar());
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.consola.menu.nombresMeseros") + mDAO.sumarCantidad());
				break;
			}
			case 6: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionSalarioMesero"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= mDAO.getListaMeseros().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaCorta"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.salarioMesero")
							+ mDAO.getListaMeseros().get(index).calcularSalarioPorDia());
				}
				break;
			}
			case 99: {
				return;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}
		}
	}

	private void administrarPan() {
		cicloAdministrarPan: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesPan"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();

			switch (opcion) {
			case 1: {
				try {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.agregarPan"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombrePan"));
					String nombre = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(nombre);
					ExceptionChecker.verificarSoloLetras(nombre);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadPan"));
					float cantidad = con.leerNumeroFlotante();
					ExceptionChecker.verificarNumeroNegativo(cantidad);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.pesoPan"));
					int peso = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(peso);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.precioPan"));
					int precio = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(precio);
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.levaduraPan"));
					boolean tieneLevadura = con.leerBooleano();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.tipoHarinaPan"));
					String tipoHarina = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(tipoHarina);
					ExceptionChecker.verificarSoloLetras(tipoHarina);

					PanDTO nuevoPan = new PanDTO(nombre, cantidad, peso, precio, tieneLevadura, tipoHarina);
					pDAO.crear(nuevoPan);
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.exitoPan"));

				} catch (EmptyFieldException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
				} catch (NegativeNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
				} catch (InvalidNameException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
				}
				break;
			}
			case 2: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminarPan"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));
				int index = con.leerNumeroEntero();
				if (pDAO.eliminar(index)) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoPan"));
				} else {
					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"));
				}

				break;
			}
			case 3: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= pDAO.getListaPanes().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					try {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizarPan"));
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombrePan"));
						String nombre = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(nombre);
						ExceptionChecker.verificarSoloLetras(nombre);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadPan"));
						float cantidad = con.leerNumeroFlotante();
						ExceptionChecker.verificarNumeroNegativo(cantidad);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.pesoPan"));
						int peso = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(peso);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.precioPan"));
						int precio = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(precio);
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.levaduraPan"));
						boolean tieneLevadura = con.leerBooleano();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.tipoHarinaPan"));
						String tipoHarina = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(tipoHarina);
						ExceptionChecker.verificarSoloLetras(tipoHarina);

						PanDTO panActualizado = new PanDTO(nombre, cantidad, peso, precio, tieneLevadura, tipoHarina);
						pDAO.actualizar(index, panActualizado);
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoPan"));

					} catch (EmptyFieldException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
					} catch (NegativeNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
					} catch (InvalidNameException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
					}
				}
				break;
			}
			case 4: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaPan"));
				con.escribirConSalto(pDAO.mostrar());
				break;
			}
			case 5: {
				int posicion = 0;
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaPan"));
				con.escribirConSalto(
						prop.getProperty("panaderia.aplicativo.consola.menu.tipoPanesInventario") + pDAO.contar());
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.numeroPanesInventario")
						+ pDAO.sumarCantidad());
				break;
			}
			case 6: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPan"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= pDAO.getListaPanes().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
					int horas = con.leerNumeroEntero();
					con.quemarLinea();
					con.escribirConSalto(pDAO.verificarVencimiento(index, horas));
				}
				break;
			}
			case 7: {
				ArrayList<Producto> listaProductos = new ArrayList<Producto>();
				listaProductos.addAll(pDAO.getListaPanes());
				sumarPreciosConsola(listaProductos);
				break;
			}
			case 8: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPan"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= pDAO.getListaPanes().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
					int horas = con.leerNumeroEntero();
					con.quemarLinea();
					con.escribirConSalto(pDAO.verificarEndurecimiento(index, horas));
				}
				break;
			}
			case 9: {
				ArrayList<String> listaIngredientes = new ArrayList<String>();

				if (pDAO.getListaPanes().isEmpty()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaVaciaPanes"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPan"));
					int index = con.leerNumeroEntero();
					con.quemarLinea();
					if (index < 0 || index >= pDAO.getListaPanes().size()) {
						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
					} else {
						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.cantidadIngredientes"));
						int cantidadIngredientes = con.leerNumeroEntero();
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreIngredientes"));
						for (int i = 0; i < cantidadIngredientes; i++) {
							listaIngredientes.add(con.leerLinea());
						}
						con.escribirConSalto(pDAO.prepararIngredientes(index, listaIngredientes));
					}
				}
				break;
			}
			case 99: {
				return;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}

		}

	}

	private void administrarPostre() {
		cicloAdministrarPostre: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesPostre"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();

			switch (opcion) {
			case 1: {
				try {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.agregarPostre"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombrePostre"));
					String nombre = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(nombre);
					ExceptionChecker.verificarSoloLetras(nombre);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadPostre"));
					float cantidad = con.leerNumeroFlotante();
					ExceptionChecker.verificarNumeroNegativo(cantidad);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.pesoPostre"));
					int peso = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(peso);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.precioPostre"));
					int precio = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(precio);
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.azucarPostre"));
					boolean tieneAzucar = con.leerBooleano();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.tipoPostre"));
					String tipoPostre = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(tipoPostre);
					ExceptionChecker.verificarSoloLetras(tipoPostre);

					PostreDTO nuevoPostre = new PostreDTO(nombre, cantidad, peso, precio, tieneAzucar, tipoPostre);
					ptDAO.crear(nuevoPostre);
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.exitoPostre"));

				} catch (EmptyFieldException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
				} catch (NegativeNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
				} catch (InvalidNameException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
				}
				break;
			}
			case 2: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminarPostre"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));
				int index = con.leerNumeroEntero();
				if (ptDAO.eliminar(index)) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoPostre"));
				} else {
					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"));
				}

				break;
			}
			case 3: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= ptDAO.getListaPostres().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					try {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizarPostre"));
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombrePostre"));
						String nombre = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(nombre);
						ExceptionChecker.verificarSoloLetras(nombre);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadPostre"));
						float cantidad = con.leerNumeroFlotante();
						ExceptionChecker.verificarNumeroNegativo(cantidad);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.pesoPostre"));
						int peso = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(peso);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.precioPostre"));
						int precio = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(precio);
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.azucarPostre"));
						boolean tieneAzucar = con.leerBooleano();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.tipoPostre"));
						String tipoPostre = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(tipoPostre);
						ExceptionChecker.verificarSoloLetras(tipoPostre);

						PostreDTO postreActualizado = new PostreDTO(nombre, cantidad, peso, precio, tieneAzucar,
								tipoPostre);
						ptDAO.actualizar(index, postreActualizado);
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoPostre"));

					} catch (EmptyFieldException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
					} catch (NegativeNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
					} catch (InvalidNameException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
					}
				}
				break;
			}
			case 4: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaPostre"));
				con.escribirConSalto(ptDAO.mostrar());
				break;
			}
			case 5: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaPostre"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.numeroPostresInventario")
						+ ptDAO.sumarCantidad());
				break;
			}
			case 6: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPostre"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= ptDAO.getListaPostres().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
					int horas = con.leerNumeroEntero();
					con.quemarLinea();
					con.escribirConSalto(ptDAO.verificarVencimiento(index, horas));
				}
				break;
			}
			case 7: {
				ArrayList<Producto> listaProductos = new ArrayList<Producto>();
				listaProductos.addAll(ptDAO.getListaPostres());
				sumarPreciosConsola(listaProductos);
				break;
			}
			case 8: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPostre"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= ptDAO.getListaPostres().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.motivoDaniPostre"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesMotivoPostre"));
					int motivo = con.leerNumeroEntero();
					con.quemarLinea();
					con.escribirConSalto(ptDAO.verificarDeterioro(index, motivo));
				}
				break;
			}
			case 9: {
				ArrayList<String> listaIngredientes = new ArrayList<String>();

				if (ptDAO.getListaPostres().isEmpty()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaVaciaPostres"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionPostre"));
					int index = con.leerNumeroEntero();
					con.quemarLinea();
					if (index < 0 || index >= ptDAO.getListaPostres().size()) {
						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
					} else {
						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.cantidadIngredientes"));
						int cantidadIngredientes = con.leerNumeroEntero();
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreIngredientes"));
						for (int i = 0; i < cantidadIngredientes; i++) {
							listaIngredientes.add(con.leerLinea());
						}
						con.escribirConSalto(ptDAO.prepararIngredientes(index, listaIngredientes));
					}
				}
				break;
			}
			case 99: {
				return;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}

		}
	}

	private void administrarGalleta() {
		cicloAdministrarGalleta: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesGalleta"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();

			switch (opcion) {
			case 1: {
				try {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.agregarGalleta"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreGalleta"));
					String nombre = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(nombre);
					ExceptionChecker.verificarSoloLetras(nombre);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadGalleta"));
					float cantidad = con.leerNumeroFlotante();
					ExceptionChecker.verificarNumeroNegativo(cantidad);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.pesoGalleta"));
					int peso = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(peso);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.precioGalleta"));
					int precio = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(precio);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.topingsGalleta"));
					boolean tieneTopings = con.leerBooleano();
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.rellenoGalleta"));
					boolean tieneRelleno = con.leerBooleano();

					GalletaDTO nuevaGalleta = new GalletaDTO(nombre, cantidad, peso, precio, tieneTopings,
							tieneRelleno);
					gDAO.crear(nuevaGalleta);
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.exitoGalleta"));

				} catch (EmptyFieldException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
				} catch (NegativeNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
				} catch (InvalidNameException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
				}
				break;
			}
			case 2: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminarGalleta"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));
				int index = con.leerNumeroEntero();
				if (gDAO.eliminar(index)) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoGalleta"));
				} else {
					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"));
				}

				break;
			}
			case 3: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= gDAO.getListaGalletas().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					try {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizarGalleta"));
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreGalleta"));
						String nombre = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(nombre);
						ExceptionChecker.verificarSoloLetras(nombre);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadGalleta"));
						float cantidad = con.leerNumeroFlotante();
						ExceptionChecker.verificarNumeroNegativo(cantidad);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.pesoGalleta"));
						int peso = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(peso);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.precioGalleta"));
						int precio = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(precio);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.topingsGalleta"));
						boolean tieneTopings = con.leerBooleano();
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.rellenoGalleta"));
						boolean tieneRelleno = con.leerBooleano();

						GalletaDTO galletaActualizada = new GalletaDTO(nombre, cantidad, peso, precio, tieneTopings,
								tieneRelleno);
						gDAO.actualizar(index, galletaActualizada);
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoGalleta"));

					} catch (EmptyFieldException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
					} catch (NegativeNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
					} catch (InvalidNameException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
					}
				}
				break;
			}
			case 4: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaGalleta"));
				con.escribirConSalto(gDAO.mostrar());
				break;
			}
			case 5: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaGalleta"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.numeroGalletasInventario")
						+ gDAO.sumarCantidad());
				break;
			}
			case 6: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionGalleta"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= gDAO.getListaGalletas().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
					int horas = con.leerNumeroEntero();
					con.quemarLinea();
					con.escribirConSalto(gDAO.verificarVencimiento(index, horas));
				}
				break;
			}
			case 7: {
				ArrayList<Producto> listaProductos = new ArrayList<Producto>();
				listaProductos.addAll(gDAO.getListaGalletas());
				sumarPreciosConsola(listaProductos);
				break;
			}
			case 8: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionGalleta"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= gDAO.getListaGalletas().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
					int horas = con.leerNumeroEntero();
					con.quemarLinea();
					con.escribirConSalto(gDAO.verificarEndurecimiento(index, horas));
				}
				break;
			}
			case 9: {
				ArrayList<String> listaIngredientes = new ArrayList<String>();

				if (gDAO.getListaGalletas().isEmpty()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaVaciaGalletas"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionGalleta"));
					int index = con.leerNumeroEntero();
					con.quemarLinea();
					if (index < 0 || index >= gDAO.getListaGalletas().size()) {
						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
					} else {
						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.cantidadIngredientes"));
						int cantidadIngredientes = con.leerNumeroEntero();
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreIngredientes"));
						for (int i = 0; i < cantidadIngredientes; i++) {
							listaIngredientes.add(con.leerLinea());
						}
						con.escribirConSalto(gDAO.prepararIngredientes(index, listaIngredientes));
					}
				}
				break;
			}
			case 99: {
				return;
			}

			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);

			}

		}
	}

	private void administrarLacteo() {
		cicloAdministrarLacteo: while (true) {
			con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesLacteo"));
			int opcion = con.leerNumeroEntero();
			con.quemarLinea();
			switch (opcion) {
			case 1: {
				try {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.agregarLacteo"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreLacteo"));
					String nombre = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(nombre);
					ExceptionChecker.verificarSoloLetras(nombre);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadLacteo"));
					float cantidad = con.leerNumeroFlotante();
					ExceptionChecker.verificarNumeroNegativo(cantidad);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.pesoLacteo"));
					int peso = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(peso);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.precioLacteo"));
					int precio = con.leerNumeroEntero();
					ExceptionChecker.verificarNumeroNegativo(precio);
					con.quemarLinea();

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.marcaLacteo"));
					String marca = con.leerLinea();
					ExceptionChecker.verificarCampoVacio(marca);
					ExceptionChecker.verificarSoloLetras(marca);

					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.cambioLacteo"));
					boolean tieneCambio = con.leerBooleano();

					LacteoDTO nuevoLacteo = new LacteoDTO(nombre, cantidad, peso, precio, marca, tieneCambio);
					lDAO.crear(nuevoLacteo);
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.exitoLacteo"));

				} catch (EmptyFieldException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
				} catch (NegativeNumberException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
				} catch (InvalidNameException ex) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
				}
				break;
			}
			case 2: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminarLacteo"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionEliminar"));
				int index = con.leerNumeroEntero();
				if (lDAO.eliminar(index)) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.eliminadoLacteo"));
				} else {
					con.escribirConSalto(
							prop.getProperty("panaderia.aplicativo.consola.menu.posicionInvalidaEliminar"));
				}

				break;
			}
			case 3: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionActualizar"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();

				if (index < 0 || index >= lDAO.getListaLacteos().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					try {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizarLacteo"));
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreLacteo"));
						String nombre = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(nombre);
						ExceptionChecker.verificarSoloLetras(nombre);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.cantidadLacteo"));
						float cantidad = con.leerNumeroFlotante();
						ExceptionChecker.verificarNumeroNegativo(cantidad);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.pesoLacteo"));
						int peso = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(peso);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.precioLacteo"));
						int precio = con.leerNumeroEntero();
						ExceptionChecker.verificarNumeroNegativo(precio);
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.marcaLacteo"));
						String marca = con.leerLinea();
						ExceptionChecker.verificarCampoVacio(marca);
						ExceptionChecker.verificarSoloLetras(marca);

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.cambioLacteo"));
						boolean tieneCambio = con.leerBooleano();

						LacteoDTO lacteoActualizado = new LacteoDTO(nombre, cantidad, peso, precio, marca, tieneCambio);
						lDAO.actualizar(index, lacteoActualizado);
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.actualizadoLacteo"));

					} catch (EmptyFieldException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.campoVacio"));
					} catch (NegativeNumberException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.numeroNegativo"));
					} catch (InvalidNameException ex) {
						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.interfaz.menu.noNumeros"));
					}
				}
				break;
			}
			case 4: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaLacteo"));
				con.escribirConSalto(lDAO.mostrar());
				break;
			}
			case 5: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaLacteo"));
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.numeroLacteosInventario")
						+ lDAO.sumarCantidad());
				break;
			}
			case 6: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionLacteo"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= lDAO.getListaLacteos().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.horasFabricacion"));
					int horas = con.leerNumeroEntero();
					con.quemarLinea();
					con.escribirConSalto(lDAO.verificarVencimiento(index, horas));
				}
				break;
			}
			case 7: {
				ArrayList<Producto> listaProductos = new ArrayList<Producto>();
				listaProductos.addAll(lDAO.getListaLacteos());
				sumarPreciosConsola(listaProductos);
				break;
			}
			case 8: {
				con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionLacteo"));
				int index = con.leerNumeroEntero();
				con.quemarLinea();
				if (index < 0 || index >= lDAO.getListaLacteos().size()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.motivoDaniLacteo"));
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.opcionesMotivoLacteo"));
					int motivo = con.leerNumeroEntero();
					con.quemarLinea();
					con.escribirConSalto(lDAO.verificarCortamiento(index, motivo));
				}
				break;
			}
			case 9: {
				ArrayList<String> listaIngredientes = new ArrayList<String>();

				if (lDAO.getListaLacteos().isEmpty()) {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.listaVaciaLacteos"));
				} else {
					con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.posicionLacteo"));
					int index = con.leerNumeroEntero();
					con.quemarLinea();
					if (index < 0 || index >= lDAO.getListaLacteos().size()) {
						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.posicionNoValidaIntente"));
					} else {
						con.escribirConSalto(
								prop.getProperty("panaderia.aplicativo.consola.menu.cantidadIngredientes"));
						int cantidadIngredientes = con.leerNumeroEntero();
						con.quemarLinea();

						con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.nombreIngredientes"));
						for (int i = 0; i < cantidadIngredientes; i++) {
							listaIngredientes.add(con.leerLinea());
						}
						con.escribirConSalto(lDAO.prepararIngredientes(index, listaIngredientes));
					}
				}
				break;
			}
			case 99: {
				return;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);
			}

		}
	}

	private void mostrarLista(String contenido, String titulo) {
		JTextArea area = new JTextArea(contenido, 20, 50);
		area.setEditable(false);
		JOptionPane.showMessageDialog(vp, new JScrollPane(area), titulo, JOptionPane.PLAIN_MESSAGE);
	}

	public void sumarPreciosGUI(ArrayList<Producto> listaProductos) {// POLIMORFISMO : SOBRECARGA DE METODOS
		int sumarPrecios = 0;
		for (Producto producto : listaProductos) {
			sumarPrecios += producto.getCantidad() * producto.getPrecio();
		}
		JOptionPane.showMessageDialog(vp,
				prop.getProperty("panaderia.aplicativo.consola.menu.valorTotalInventario") + sumarPrecios);

	}

	public void sumarPreciosConsola(ArrayList<Producto> listaProductos) {// POLIMORFISMO : SOBRECARGA DE METODOS
		int sumarPrecios = 0;
		for (Producto producto : listaProductos) {
			sumarPrecios += producto.getCantidad() * producto.getPrecio();
		}
		con.escribirConSalto(prop.getProperty("panaderia.aplicativo.consola.menu.valorTotalInventario") + sumarPrecios);

	}

}