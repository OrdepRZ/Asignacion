package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao;

import java.sql.SQLException;
import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.MunicipioDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public interface VacanteDAO {
	public List<VacanteDTO> getVacantes (List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion;
	public List<MunicipioDTO> getMunicipiosVacantes() throws DBExcepcion, SistemaExcepcion;
	public List<VacanteDTO> getVacantesGeoref(String idMunicipio)throws DBExcepcion, SistemaExcepcion;
	public List<VacanteDTO> getAsignaturasGeoref(String idMunicipio)throws DBExcepcion, SistemaExcepcion;
	public List<VacanteDTO> getVacantesConcluir() throws DBExcepcion, SistemaExcepcion;
	public AspiranteDTO getVacanteAspirante(String folioFederal) throws DBExcepcion, SistemaExcepcion;
	public String actualizaVacante(VacanteDTO vacante) throws DBExcepcion, SistemaExcepcion;
	public String actualizaVacanteNivel(VacanteDTO vacante, String cvePresuOriginal, String usuario) throws DBExcepcion, SistemaExcepcion, SQLException;//MODIFICADO
	public String registraVacante(VacanteDTO vacante, String usuario) throws DBExcepcion, SistemaExcepcion, SQLException;//MODIFICADO
}
