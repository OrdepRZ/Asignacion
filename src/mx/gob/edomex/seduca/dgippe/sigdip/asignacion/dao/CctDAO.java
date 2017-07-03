package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.CctDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public interface CctDAO 
{
	public CctDTO getCCT (String idCCT,String educacion) throws DBExcepcion, SistemaExcepcion;

}
