package co.edu.unbosque.model.persistence;

import java.util.ArrayList;

import co.edu.unbosque.model.Galleta;
import co.edu.unbosque.model.GalletaDTO;
import co.edu.unbosque.model.Lacteo;
import co.edu.unbosque.model.LacteoDTO;
import co.edu.unbosque.model.Mesero;
import co.edu.unbosque.model.MeseroDTO;
import co.edu.unbosque.model.Pan;
import co.edu.unbosque.model.PanDTO;
import co.edu.unbosque.model.Panadero;
import co.edu.unbosque.model.PanaderoDTO;
import co.edu.unbosque.model.Postre;
import co.edu.unbosque.model.PostreDTO;
import co.edu.unbosque.model.ProveedorArtesanal;
import co.edu.unbosque.model.ProveedorArtesanalDTO;
import co.edu.unbosque.model.ProveedorIndustrial;
import co.edu.unbosque.model.ProveedorIndustrialDTO;
import co.edu.unbosque.model.Repostero;
import co.edu.unbosque.model.ReposteroDTO;
import co.edu.unbosque.model.Venta;
import co.edu.unbosque.model.VentaDTO;

public class DataMapper {

//venta
	public static Venta convertirVentaDTOAVenta(VentaDTO dto) {
		Venta entity = new Venta();
		entity.setProductos(dto.getProductos());
		entity.setClienteDTO(dto.getClienteDTO());
		entity.setMetodoPago(dto.getMetodoPago());
		entity.setTotal(dto.getTotal());
		return entity;
	}

	public static VentaDTO convertirVentaAVentaDTO(Venta entity) {
		VentaDTO dto = new VentaDTO();
		dto.setProductos(entity.getProductos());
		dto.setClienteDTO(entity.getClienteDTO());
		dto.setMetodoPago(entity.getMetodoPago());
		dto.setTotal(entity.getTotal());
		return dto;
	}

	public static ArrayList<VentaDTO> convertirListaVentaAListaDTO(ArrayList<Venta> listaEntidad) {
		ArrayList<VentaDTO> listaDTO = new ArrayList<>();
		for (Venta venta : listaEntidad) {
			listaDTO.add(convertirVentaAVentaDTO(venta));
		}
		return listaDTO;
	}

	public static ArrayList<Venta> convertirListaDTOAListaVenta(ArrayList<VentaDTO> listaDTO) {
		ArrayList<Venta> listaEntidad = new ArrayList<>();
		for (VentaDTO dto : listaDTO) {
			listaEntidad.add(convertirVentaDTOAVenta(dto));
		}
		return listaEntidad;
	}

	// pan
	public static Pan convertirPanDTOAPan(PanDTO dto) {
		Pan entity = new Pan();
		entity.setNombre(dto.getNombre());
		entity.setCantidad(dto.getCantidad());
		entity.setPeso(dto.getPeso());
		entity.setPrecio(dto.getPrecio());
		entity.setTieneLevadura(dto.isTieneLevadura());
		entity.setTipoHarina(dto.getTipoHarina());
		return entity;
	}

	public static PanDTO convertirPanAPanDTO(Pan entity) {
		PanDTO dto = new PanDTO();
		dto.setNombre(entity.getNombre());
		dto.setCantidad(entity.getCantidad());
		dto.setPeso(entity.getPeso());
		dto.setPrecio(entity.getPrecio());
		dto.setTieneLevadura(entity.isTieneLevadura());
		dto.setTipoHarina(entity.getTipoHarina());
		return dto;

	}

	public static ArrayList<PanDTO> convertirListaPanAListaDTO(ArrayList<Pan> listaEntidad) {
		ArrayList<PanDTO> listaDTO = new ArrayList<>();
		for (Pan pan : listaEntidad) {
			listaDTO.add(convertirPanAPanDTO(pan));
		}
		return listaDTO;
	}

	public static ArrayList<Pan> convertirListaDTOAListaPan(ArrayList<PanDTO> listaDTO) {
		ArrayList<Pan> listaEntidad = new ArrayList<>();
		for (PanDTO dto : listaDTO) {
			listaEntidad.add(convertirPanDTOAPan(dto));
		}
		return listaEntidad;
	}

	// postre
	public static Postre convertirPostreDTOAPostre(PostreDTO dto) {
		Postre entity = new Postre();
		entity.setNombre(dto.getNombre());
		entity.setCantidad(dto.getCantidad());
		entity.setPeso(dto.getPeso());
		entity.setPrecio(dto.getPrecio());
		entity.setTieneAzucar(dto.isTieneAzucar());
		entity.setTipoPostre(dto.getTipoPostre());
		return entity;
	}

	public static PostreDTO convertirPostreAPostreDTO(Postre entity) {
		PostreDTO dto = new PostreDTO();
		dto.setNombre(entity.getNombre());
		dto.setCantidad(entity.getCantidad());
		dto.setPeso(entity.getPeso());
		dto.setPrecio(entity.getPrecio());
		dto.setTieneAzucar(entity.isTieneAzucar());
		dto.setTipoPostre(entity.getTipoPostre());
		return dto;

	}

	public static ArrayList<PostreDTO> convertirListaPostreAListaDTO(ArrayList<Postre> listaEntidad) {
		ArrayList<PostreDTO> listaDTO = new ArrayList<>();
		for (Postre postre : listaEntidad) {
			listaDTO.add(convertirPostreAPostreDTO(postre));
		}
		return listaDTO;
	}

	public static ArrayList<Postre> convertirListaDTOAListaPostre(ArrayList<PostreDTO> listaDTO) {
		ArrayList<Postre> listaEntidad = new ArrayList<>();
		for (PostreDTO dto : listaDTO) {
			listaEntidad.add(convertirPostreDTOAPostre(dto));
		}
		return listaEntidad;
	}

	// galleta
	public static Galleta convertirGalletaDTOAGalleta(GalletaDTO dto) {
		Galleta entity = new Galleta();
		entity.setNombre(dto.getNombre());
		entity.setCantidad(dto.getCantidad());
		entity.setPeso(dto.getPeso());
		entity.setPrecio(dto.getPrecio());
		entity.setTieneTopings(dto.isTieneTopings());
		entity.setTieneRelleno(dto.isTieneRelleno());
		return entity;
	}

	public static GalletaDTO convertirGalletaAGalletaDTO(Galleta entity) {
		GalletaDTO dto = new GalletaDTO();
		dto.setNombre(entity.getNombre());
		dto.setCantidad(entity.getCantidad());
		dto.setPeso(entity.getPeso());
		dto.setPrecio(entity.getPrecio());
		dto.setTieneTopings(entity.isTieneTopings());
		dto.setTieneRelleno(entity.isTieneRelleno());
		return dto;

	}

	public static ArrayList<GalletaDTO> convertirListaGalletaAListaDTO(ArrayList<Galleta> listaEntidad) {
		ArrayList<GalletaDTO> listaDTO = new ArrayList<>();
		for (Galleta galleta : listaEntidad) {
			listaDTO.add(convertirGalletaAGalletaDTO(galleta));
		}
		return listaDTO;
	}

	public static ArrayList<Galleta> convertirListaDTOAListaGalleta(ArrayList<GalletaDTO> listaDTO) {
		ArrayList<Galleta> listaEntidad = new ArrayList<>();
		for (GalletaDTO dto : listaDTO) {
			listaEntidad.add(convertirGalletaDTOAGalleta(dto));
		}
		return listaEntidad;
	}

	// lacteo
	public static Lacteo convertirLacteoDTOALacteo(LacteoDTO dto) {
		Lacteo entity = new Lacteo();
		entity.setNombre(dto.getNombre());
		entity.setCantidad(dto.getCantidad());
		entity.setPeso(dto.getPeso());
		entity.setPrecio(dto.getPrecio());
		entity.setMarca(dto.getMarca());
		entity.setTieneCambio(dto.isTieneCambio());
		return entity;
	}

	public static LacteoDTO convertirLacteoALacteoDTO(Lacteo entity) {
		LacteoDTO dto = new LacteoDTO();
		dto.setNombre(entity.getNombre());
		dto.setCantidad(entity.getCantidad());
		dto.setPeso(entity.getPeso());
		dto.setPrecio(entity.getPrecio());
		dto.setMarca(entity.getMarca());
		dto.setTieneCambio(entity.isTieneCambio());
		return dto;

	}

	public static ArrayList<LacteoDTO> convertirListaLacteoAListaDTO(ArrayList<Lacteo> listaEntidad) {
		ArrayList<LacteoDTO> listaDTO = new ArrayList<>();
		for (Lacteo lacteo : listaEntidad) {
			listaDTO.add(convertirLacteoALacteoDTO(lacteo));
		}
		return listaDTO;
	}

	public static ArrayList<Lacteo> convertirListaDTOAListaLacteo(ArrayList<LacteoDTO> listaDTO) {
		ArrayList<Lacteo> listaEntidad = new ArrayList<>();
		for (LacteoDTO dto : listaDTO) {
			listaEntidad.add(convertirLacteoDTOALacteo(dto));
		}
		return listaEntidad;
	}

	// mesero
	public static Mesero convertirMeseroDTOAMesero(MeseroDTO dto) {
		Mesero entity = new Mesero();
		entity.setNombreCompleto(dto.getNombreCompleto());
		entity.setGenero(dto.getGenero());
		entity.setEdad(dto.getEdad());
		entity.setNumTelefono(dto.getNumTelefono());
		entity.setSalario(dto.getSalario());
		entity.setEstaEstudianto(dto.isEstaEstudianto());
		entity.setExperienciaEventosGrandes(dto.isExperienciaEventosGrandes());
		return entity;
	}

	public static MeseroDTO convertirMeseroAMeseroDTO(Mesero entity) {
		MeseroDTO dto = new MeseroDTO();
		dto.setNombreCompleto(entity.getNombreCompleto());
		dto.setGenero(entity.getGenero());
		dto.setEdad(entity.getEdad());
		dto.setNumTelefono(entity.getNumTelefono());
		dto.setSalario(entity.getSalario());
		dto.setEstaEstudianto(entity.isEstaEstudianto());
		dto.setExperienciaEventosGrandes(entity.isExperienciaEventosGrandes());
		return dto;

	}

	public static ArrayList<MeseroDTO> convertirListaMeseroAListaDTO(ArrayList<Mesero> listaEntidad) {
		ArrayList<MeseroDTO> listaDTO = new ArrayList<>();
		for (Mesero mesero : listaEntidad) {
			listaDTO.add(convertirMeseroAMeseroDTO(mesero));
		}
		return listaDTO;
	}

	public static ArrayList<Mesero> convertirListaDTOAListaMesero(ArrayList<MeseroDTO> listaDTO) {
		ArrayList<Mesero> listaEntidad = new ArrayList<>();
		for (MeseroDTO dto : listaDTO) {
			listaEntidad.add(convertirMeseroDTOAMesero(dto));
		}
		return listaEntidad;
	}

	// panadero
	public static Panadero convertirPanaderoDTOAPanadero(PanaderoDTO dto) {
		Panadero entity = new Panadero();
		entity.setNombreCompleto(dto.getNombreCompleto());
		entity.setGenero(dto.getGenero());
		entity.setEdad(dto.getEdad());
		entity.setNumTelefono(dto.getNumTelefono());
		entity.setSalario(dto.getSalario());
		entity.setPanesHorneadosPorDia(dto.getPanesHorneadosPorDia());
		entity.setEspecialidadPan(dto.getEspecialidadPan());
		return entity;
	}

	public static PanaderoDTO convertirPanaderoAPanaderoDTO(Panadero entity) {
		PanaderoDTO dto = new PanaderoDTO();
		dto.setNombreCompleto(entity.getNombreCompleto());
		dto.setGenero(entity.getGenero());
		dto.setEdad(entity.getEdad());
		dto.setNumTelefono(entity.getNumTelefono());
		dto.setSalario(entity.getSalario());
		dto.setPanesHorneadosPorDia(entity.getPanesHorneadosPorDia());
		dto.setEspecialidadPan(entity.getEspecialidadPan());
		return dto;

	}

	public static ArrayList<PanaderoDTO> convertirListaPanaderoAListaDTO(ArrayList<Panadero> listaEntidad) {
		ArrayList<PanaderoDTO> listaDTO = new ArrayList<>();
		for (Panadero panadero : listaEntidad) {
			listaDTO.add(convertirPanaderoAPanaderoDTO(panadero));
		}
		return listaDTO;
	}

	public static ArrayList<Panadero> convertirListaDTOAListaPanadero(ArrayList<PanaderoDTO> listaDTO) {
		ArrayList<Panadero> listaEntidad = new ArrayList<>();
		for (PanaderoDTO dto : listaDTO) {
			listaEntidad.add(convertirPanaderoDTOAPanadero(dto));
		}
		return listaEntidad;
	}

	// repostero
	public static Repostero convertirReposteroDTOARepostero(ReposteroDTO dto) {
		Repostero entity = new Repostero();
		entity.setNombreCompleto(dto.getNombreCompleto());
		entity.setGenero(dto.getGenero());
		entity.setEdad(dto.getEdad());
		entity.setNumTelefono(dto.getNumTelefono());
		entity.setSalario(dto.getSalario());
		entity.setNivelCreatividad(dto.getNivelCreatividad());
		entity.setManejaTecnicasAvanzadas(dto.isManejaTecnicasAvanzadas());
		return entity;
	}

	public static ReposteroDTO convertirReposteroAReposteroDTO(Repostero entity) {
		ReposteroDTO dto = new ReposteroDTO();
		dto.setNombreCompleto(entity.getNombreCompleto());
		dto.setGenero(entity.getGenero());
		dto.setEdad(entity.getEdad());
		dto.setNumTelefono(entity.getNumTelefono());
		dto.setSalario(entity.getSalario());
		dto.setNivelCreatividad(entity.getNivelCreatividad());
		dto.setManejaTecnicasAvanzadas(entity.isManejaTecnicasAvanzadas());
		return dto;

	}

	public static ArrayList<ReposteroDTO> convertirListaReposteroAListaDTO(ArrayList<Repostero> listaEntidad) {
		ArrayList<ReposteroDTO> listaDTO = new ArrayList<>();
		for (Repostero repostero : listaEntidad) {
			listaDTO.add(convertirReposteroAReposteroDTO(repostero));
		}
		return listaDTO;
	}

	public static ArrayList<Repostero> convertirListaDTOAListaRepostero(ArrayList<ReposteroDTO> listaDTO) {
		ArrayList<Repostero> listaEntidad = new ArrayList<>();
		for (ReposteroDTO dto : listaDTO) {
			listaEntidad.add(convertirReposteroDTOARepostero(dto));
		}
		return listaEntidad;
	}

	// proveedor Artesanal
	public static ProveedorArtesanal convertirArtesanalDTOAArtesanal(ProveedorArtesanalDTO dto) {
		ProveedorArtesanal entity = new ProveedorArtesanal();
		entity.setNombreEmpresa(dto.getNombreEmpresa());
		entity.setNit(dto.getNit());
		entity.setCiudad(dto.getCiudad());
		entity.setTelefono(dto.getTelefono());
		entity.setTipoProducto(dto.getTipoProducto());
		entity.setCertificado(dto.isCertificado());
		return entity;
	}

	public static ProveedorArtesanalDTO convertirArtesanalAArtesanalDTO(ProveedorArtesanal entity) {
		ProveedorArtesanalDTO dto = new ProveedorArtesanalDTO();
		dto.setNombreEmpresa(entity.getNombreEmpresa());
		dto.setNit(entity.getNit());
		dto.setCiudad(entity.getCiudad());
		dto.setTelefono(entity.getTelefono());
		dto.setTipoProducto(entity.getTipoProducto());
		dto.setCertificado(entity.isCertificado());
		return dto;
	}

	public static ArrayList<ProveedorArtesanalDTO> convertirListaArtesanalAListaDTO(
			ArrayList<ProveedorArtesanal> listaEntidad) {
		ArrayList<ProveedorArtesanalDTO> listaDTO = new ArrayList<>();
		for (ProveedorArtesanal artesanal : listaEntidad) {
			listaDTO.add(convertirArtesanalAArtesanalDTO(artesanal));
		}
		return listaDTO;
	}

	public static ArrayList<ProveedorArtesanal> convertirListaDTOAListaArtesanal(
			ArrayList<ProveedorArtesanalDTO> listaDTO) {
		ArrayList<ProveedorArtesanal> listaEntidad = new ArrayList<>();
		for (ProveedorArtesanalDTO dto : listaDTO) {
			listaEntidad.add(convertirArtesanalDTOAArtesanal(dto));
		}
		return listaEntidad;
	}

	// proveedor Industrial
	public static ProveedorIndustrial convertirIndustriallDTOAIndustrial(ProveedorIndustrialDTO dto) {
		ProveedorIndustrial entity = new ProveedorIndustrial();
		entity.setNombreEmpresa(dto.getNombreEmpresa());
		entity.setNit(dto.getNit());
		entity.setCiudad(dto.getCiudad());
		entity.setTelefono(dto.getTelefono());
		entity.setTipoProducto(dto.getTipoProducto());
		entity.setCertificado(dto.isCertificado());
		return entity;
	}

	public static ProveedorIndustrialDTO convertirIndustrialAIndustrialDTO(ProveedorIndustrial entity) {
		ProveedorIndustrialDTO dto = new ProveedorIndustrialDTO();
		dto.setNombreEmpresa(entity.getNombreEmpresa());
		dto.setNit(entity.getNit());
		dto.setCiudad(entity.getCiudad());
		dto.setTelefono(entity.getTelefono());
		dto.setTipoProducto(entity.getTipoProducto());
		dto.setCertificado(entity.isCertificado());
		return dto;
	}

	public static ArrayList<ProveedorIndustrialDTO> convertirListaIndustrialAListaDTO(
			ArrayList<ProveedorIndustrial> listaEntidad) {
		ArrayList<ProveedorIndustrialDTO> listaDTO = new ArrayList<>();
		for (ProveedorIndustrial industrial : listaEntidad) {
			listaDTO.add(convertirIndustrialAIndustrialDTO(industrial));
		}
		return listaDTO;
	}

	public static ArrayList<ProveedorIndustrial> convertirListaDTOAListaIndustrial(
			ArrayList<ProveedorIndustrialDTO> listaDTO) {
		ArrayList<ProveedorIndustrial> listaEntidad = new ArrayList<>();
		for (ProveedorIndustrialDTO dto : listaDTO) {
			listaEntidad.add(convertirIndustriallDTOAIndustrial(dto));
		}
		return listaEntidad;
	}
}
