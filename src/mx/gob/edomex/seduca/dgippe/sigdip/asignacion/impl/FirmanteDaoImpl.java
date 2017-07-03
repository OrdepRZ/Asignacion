package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.FirmanteDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.FirmanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public class FirmanteDaoImpl implements FirmanteDAO {

	@Override
	public FirmanteDTO getFirmante(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		FirmanteDTO firmante = new FirmanteDTO();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sSql = "SELECT ID_FIRMANTE, ID_SUBSISTEMA, USUARIO, PASSWORD, KEY_ID "
					+ "FROM FIRMANTES "
					+ "WHERE B_BAJA = 'F' ";
	
		if(lstFiltro != null && lstFiltro.size() > 0) {
			int ctdFiltro = 1;
			Iterator<CampoRptDTO> it = lstFiltro.iterator();
			while(it.hasNext()) {
				CampoRptDTO filtro = (CampoRptDTO) it.next();
				String clave = filtro.getClave();
				String valor = filtro.getValor();
				
				if (ctdFiltro == 0) {
					sSql += " WHERE ";
				} else {
					sSql += " AND ";
				}
				
				if (clave.equals("ID_SUBSISTEMA")) {
					sSql += " ID_SUBSISTEMA = " + valor.trim();
				}
	
				ctdFiltro++;
			}
		}
	
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				firmante.setId(rs.getString("ID_FIRMANTE"));
				firmante.setIdSubsistema(rs.getString("ID_SUBSISTEMA"));
				firmante.setUsuario(rs.getString("USUARIO"));
				firmante.setPassword(rs.getString("PASSWORD"));
				firmante.setKeyId(rs.getString("KEY_ID"));
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de FirmanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return firmante;
	}

}
