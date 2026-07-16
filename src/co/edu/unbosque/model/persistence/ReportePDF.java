package co.edu.unbosque.model.persistence;

import java.io.FileOutputStream;
import java.util.ArrayList;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import co.edu.unbosque.model.CarritoVenta;
import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.Producto;

public class ReportePDF {

	/**
	 * @param urlArchivo
	 * @param listaProductos
	 */
	public static void crearReporteInventario(String urlArchivo, ArrayList<Producto> listaProductos) {
		Document documento = new Document();
		try {
			PdfWriter.getInstance(documento, new FileOutputStream(urlArchivo));
			documento.open();

			Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
			Paragraph titulo = new Paragraph("Reporte de Inventario - Panaderia Connor", fontTitulo);
			titulo.setAlignment(Element.ALIGN_CENTER);
			documento.add(titulo);
			documento.add(new Paragraph(" "));

			PdfPTable tabla = new PdfPTable(3);
			tabla.setWidthPercentage(100);

			Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
			tabla.addCell(new PdfPCell(new Paragraph("Nombre", fontHeader)));
			tabla.addCell(new PdfPCell(new Paragraph("Cantidad", fontHeader)));
			tabla.addCell(new PdfPCell(new Paragraph("Precio", fontHeader)));

			Font fontCelda = FontFactory.getFont(FontFactory.HELVETICA, 11);
			int valorTotal = 0;
			for (Producto p : listaProductos) {
				tabla.addCell(new PdfPCell(new Paragraph(p.getNombre(), fontCelda)));
				tabla.addCell(new PdfPCell(new Paragraph(String.valueOf(p.getCantidad()), fontCelda)));
				tabla.addCell(new PdfPCell(new Paragraph(String.valueOf(p.getPrecio()), fontCelda)));
				valorTotal += p.getCantidad() * p.getPrecio();
			}

			documento.add(tabla);
			documento.add(new Paragraph(" "));

			Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13);
			documento.add(new Paragraph("Valor total del inventario: " + valorTotal, fontTotal));

			documento.close();

		} catch (Exception e) {
			System.err.println(" Error al generar el PDF ");
			e.printStackTrace();
		}

	}

	public static void crearReciboVenta(String urlArchivo, ClienteDTO cliente, ArrayList<CarritoVenta> productos,
			String metodoPago, int total) {
		Document documento = new Document();
		try {
			PdfWriter.getInstance(documento, new FileOutputStream(urlArchivo));
			documento.open();

			Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
			Paragraph titulo = new Paragraph("Recibo de Compra - Panaderia Connor", fontTitulo);
			titulo.setAlignment(Element.ALIGN_CENTER);
			documento.add(titulo);
			documento.add(new Paragraph(" "));

			Font fontDatos = FontFactory.getFont(FontFactory.HELVETICA, 12);
			documento.add(new Paragraph("Cliente: " + cliente.getNombre() + " " + cliente.getApellido(), fontDatos));
			documento.add(new Paragraph("Cedula: " + cliente.getCedula(), fontDatos));
			documento.add(new Paragraph("Telefono: " + cliente.getTelefono(), fontDatos));
			documento.add(new Paragraph(" "));

			PdfPTable tabla = new PdfPTable(4);
			tabla.setWidthPercentage(100);
			Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
			tabla.addCell(new PdfPCell(new Paragraph("Producto", fontHeader)));
			tabla.addCell(new PdfPCell(new Paragraph("Cantidad", fontHeader)));
			tabla.addCell(new PdfPCell(new Paragraph("Precio Unit.", fontHeader)));
			tabla.addCell(new PdfPCell(new Paragraph("Subtotal", fontHeader)));

			Font fontCelda = FontFactory.getFont(FontFactory.HELVETICA, 11);
			for (CarritoVenta item : productos) {
				tabla.addCell(new PdfPCell(new Paragraph(item.getNombreProducto(), fontCelda)));
				tabla.addCell(new PdfPCell(new Paragraph(String.valueOf(item.getCantidad()), fontCelda)));
				tabla.addCell(new PdfPCell(new Paragraph(String.valueOf(item.getPrecioUnitario()), fontCelda)));
				tabla.addCell(new PdfPCell(new Paragraph(String.valueOf(item.getSubtotal()), fontCelda)));
			}
			documento.add(tabla);
			documento.add(new Paragraph(" "));

			Font fontDatosPago = FontFactory.getFont(FontFactory.HELVETICA, 12);
			documento.add(new Paragraph("Metodo de Pago: " + metodoPago, fontDatosPago));

			Font fontTotal = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13);
			documento.add(new Paragraph("Total a Pagar: " + total, fontTotal));

			documento.close();
		} catch (Exception e) {
			System.err.println(" Error al generar el recibo en PDF ");
			e.printStackTrace();
		}
	}

}
