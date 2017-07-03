package mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dao;

import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto.AvisoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public interface AvisoDAO {
	public String guardar(AvisoDTO aviso) throws DBExcepcion, SistemaExcepcion;
	public List<AvisoDTO> getAvisos(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion;
}
