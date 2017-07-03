package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao;

import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.NombramientoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public interface RespaldoNombMongoDAO {
	public List<NombramientoDTO> getNombramientosBasica() throws DBExcepcion, SistemaExcepcion;
	public String respaldarNombramientosBasica(List<NombramientoDTO> nombramientos)throws DBExcepcion, SistemaExcepcion;
	public String actualizarNombramientoBasica(NombramientoDTO nombramiento) throws DBExcepcion, SistemaExcepcion;
	public List<NombramientoDTO> getNombramientosMS() throws DBExcepcion, SistemaExcepcion;
	public String respaldarNombramientosMS(List<NombramientoDTO> nombramientos)throws DBExcepcion, SistemaExcepcion;
	public String actualizarNombramientoMS(NombramientoDTO nombramiento) throws DBExcepcion, SistemaExcepcion;
}
