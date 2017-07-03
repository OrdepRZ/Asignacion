package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao;

import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.FirmanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public interface FirmanteDAO {
	public FirmanteDTO getFirmante(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion;
}
