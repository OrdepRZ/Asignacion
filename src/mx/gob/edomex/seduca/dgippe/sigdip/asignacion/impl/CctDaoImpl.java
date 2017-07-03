package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.CctDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.CctDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public class CctDaoImpl implements CctDAO
{	
	@Override
	public CctDTO getCCT(String idCCT,String educacion) throws DBExcepcion, SistemaExcepcion 
	{
		CctDTO cct = new CctDTO();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		String lineaSub="";
		
		if(educacion.equals("BASICA"))
		{
			lineaSub="(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGSUBSIS' AND ID_ITEM <> 0) SUB";
		}
		else if(educacion.equals("MS"))
		{
			lineaSub="(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMSSUBSIS' AND ID_ITEM <> 0) SUB";
		}
		
		try 
		{
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			sSql = "SELECT" 
				+"	CCT.CCT, CCT.NOMBRE, CCT.DOMICILIO, CCT.COLONIA, CCT.LOCALIDAD, CCT.TURNO, CCT.CVEMUN, CCT.ZONAESCOLAR, CCT.SUBDIRREG,"
				+"  CCT.REGION, CCT.SUBSISTEMA, CCT.LONGITUD, CCT.LATITUD, MPO.DESC_ITEM DESC_MPO, SUB.DESC_ITEM DESC_SUB"	
				+"  FROM TBLCCT CCT,"
				+"  (SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMPO' AND ID_ITEM <> 0) MPO,"
				+" " + lineaSub
				+"  WHERE CCT.CVEMUN = MPO.ID_ITEM (+)"
				+"  AND CCT.SUBSISTEMA = SUB.ID_ITEM (+)" 
				+"  AND CCT = '"+ idCCT+"'";
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				cct.setCmpCCT(rs.getString("cct").trim());
				cct.setNomCCT(rs.getString("nombre").trim());
				cct.setDomicilio(rs.getString("domicilio").trim());
				cct.setColonia(rs.getString("colonia").trim());				
				cct.setLocalidad(rs.getString("localidad").trim());
				cct.setTurno(rs.getString("turno").trim());
				cct.setIdMunicipio(rs.getString("cvemun").trim());
				cct.setZonaEscolar(rs.getString("zonaescolar").trim());
				cct.setSubdirRegional(rs.getString("subdirreg").trim());
				cct.setRegion(rs.getString("region").trim());
				cct.setIdSubsistema(rs.getString("subsistema").trim());
				cct.setSubsistema(rs.getString("desc_sub").trim());
				cct.setLongitud(rs.getString("longitud").trim());
				cct.setLatitud(rs.getString("latitud").trim());
				cct.setMunicipio(rs.getString("desc_mpo").trim());
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getCct de CctDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return cct;
	}	

}
