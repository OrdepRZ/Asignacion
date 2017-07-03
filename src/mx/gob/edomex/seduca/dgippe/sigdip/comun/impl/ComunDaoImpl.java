package mx.gob.edomex.seduca.dgippe.sigdip.comun.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.comun.dao.ComunDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public class ComunDaoImpl implements ComunDAO {

	@Override
	public List<CampoRptDTO> getCamposReporte(String idReporte)throws DBExcepcion, SistemaExcepcion {
		List<CampoRptDTO> campos = new ArrayList<CampoRptDTO>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		try {
			Conexion conn = new Conexion();
			conn.conectar();
			
			sSql = "SELECT ID_CAMPO, NOM_COLUMNA, DESC_COLUMNA, PROPIEDAD_OBJETO FROM CAMPOS_REPORTES WHERE ID_REPORTE = '"+ idReporte +"'";
			
			ps = conn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CampoRptDTO campo = new CampoRptDTO();
				campo.setId(rs.getInt("ID_CAMPO"));
				campo.setClave(rs.getString("NOM_COLUMNA"));
				campo.setDescripcion(rs.getString("DESC_COLUMNA"));
				campo.setPropiedadObj(rs.getString("PROPIEDAD_OBJETO"));
				
				campos.add(campo);
			}
			
			rs.close();
			ps.close();
			conn.desconectar();
			
		}catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getCamposReporte() de ComunDaoImpl:\n" + sqle.getMessage(), sqle);
		}catch(Exception e){
			e.printStackTrace();
		}
		return campos;
	}

}
