package mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dao;

import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public interface CatalogoDAO {
	public List<CatalogoDTO> getCatalogoItems(String nomCatalogo)throws DBExcepcion, SistemaExcepcion;
	public List<CatalogoDTO> getCatalogoSubItems(String nomCatalogo, String idCatPadre)throws DBExcepcion, SistemaExcepcion;
	public List<CatalogoDTO> getCatalogo(List<CampoRptDTO> lstFiltro)throws DBExcepcion, SistemaExcepcion;
	public List<CatalogoDTO> getCatalogoFormatoAsign()throws DBExcepcion, SistemaExcepcion;	
}
