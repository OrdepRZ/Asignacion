package mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dao;

import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto.UsuarioDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public interface UsuarioDAO {

	public String guardarUsuario(UsuarioDTO usuario) throws DBExcepcion, SistemaExcepcion;
	public String actualizar(UsuarioDTO usuario) throws DBExcepcion, SistemaExcepcion;
	public UsuarioDTO getUsuario(String cveUsuario) throws DBExcepcion, SistemaExcepcion;
	public boolean compararPassword(String pwdUsuario, String encriptado) throws DBExcepcion, SistemaExcepcion;
	public String cambiarPassword(String cveUsuario, String password) throws DBExcepcion, SistemaExcepcion;
	public List<UsuarioDTO> getUsuarioFiltrado(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion;
	public String deshabilitar(UsuarioDTO usuario) throws DBExcepcion, SistemaExcepcion;
}
