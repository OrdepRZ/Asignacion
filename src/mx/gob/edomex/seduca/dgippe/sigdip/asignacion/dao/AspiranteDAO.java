package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao;

import java.util.List;

import org.primefaces.model.UploadedFile;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.ArchivoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.CifraDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.HistoricoEstatusDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public interface AspiranteDAO {
	public String cargarArchivoAspitantes(UploadedFile archivo, String contentType) throws DBExcepcion, SistemaExcepcion;
	public List<AspiranteDTO> getAspirantes (List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion;
	public String asignarVacante(String folioFederal, String idConvocatoria, String idFuncion, String idVacante, String subsistema, String usuario) throws DBExcepcion, SistemaExcepcion;
	public String registrarRenuncia(String folioFederal, String idConvocatoria, String idFuncion, String idMotivo, String observacion, String usuario) throws DBExcepcion, SistemaExcepcion;
	public List<AspiranteDTO> getAspirantesSede(String idSede, String idGrupo, String fechaAplicacion, String idTurno) throws DBExcepcion, SistemaExcepcion;
	public List<HistoricoEstatusDTO> getHistoricoEstatus (List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion;
	public AspiranteDTO getAspCambioSede(String folioFederal,String idConvocatoria, String idFuncion)throws DBExcepcion, SistemaExcepcion;
	public String actualizarSede(AspiranteDTO aspirante)throws DBExcepcion, SistemaExcepcion;
	public String getIdMotivoRechazoAspirante(String folioFederal, String idConvocatoria, String idFuncion) throws DBExcepcion, SistemaExcepcion;
	public String confirmarAspirante(String folioFederal,String curp)throws DBExcepcion, SistemaExcepcion;
	public AspiranteDTO consultarAspCurp(String curp)throws DBExcepcion, SistemaExcepcion;
	public String cargarArchivosCurp(List<UploadedFile> listaArchivos, String curp, String usuario) throws DBExcepcion, SistemaExcepcion;
	public ArchivoDTO getNombramiento (String folioFeder, String idConvocatoria, String idFuncion, String idVacante) throws DBExcepcion, SistemaExcepcion;
	public String almacenarNombramiento (ArchivoDTO archivoDTO) throws DBExcepcion, SistemaExcepcion;
	public String ejecutarReversaStatus(String folioFederal, String idConvocatoria, String idFuncion, String observacionReversa, String usuario) throws DBExcepcion, SistemaExcepcion;
	public List<CifraDTO> getCifrasAsignacion(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion;
	public List<CifraDTO> getCifrasRechazo(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion;
	public String registroFederal(AspiranteDTO aspirante, String observacion, String usuario)throws DBExcepcion, SistemaExcepcion;
	public String asignarVacanteDefinitiva(String folioFederal, String idConvocatoria, String idFuncion, String idVacanteDefinitiva, String subsistema, String usuario, String idVacanteLiberar, String idMovimiento, String observacion) throws DBExcepcion, SistemaExcepcion;
	public List<AspiranteDTO> getAspirantesSede(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion;
	public List<CatalogoDTO> getGrupos(List<CampoRptDTO> lstFiltro)throws DBExcepcion, SistemaExcepcion;	
}
