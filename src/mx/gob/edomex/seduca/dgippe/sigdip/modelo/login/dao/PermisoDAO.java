package mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dao;

import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto.PermisoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public interface PermisoDAO {

	public List<PermisoDTO> getPermisos() throws DBExcepcion, SistemaExcepcion;
	public String guardar(PermisoDTO permiso) throws DBExcepcion, SistemaExcepcion;
	
}
