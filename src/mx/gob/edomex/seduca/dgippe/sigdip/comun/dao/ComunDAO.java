package mx.gob.edomex.seduca.dgippe.sigdip.comun.dao;

import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public interface ComunDAO {
	public List<CampoRptDTO> getCamposReporte(String idReporte)throws DBExcepcion, SistemaExcepcion;
}
