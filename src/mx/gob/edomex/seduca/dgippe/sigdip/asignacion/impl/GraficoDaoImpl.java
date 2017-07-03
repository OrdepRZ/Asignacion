package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.GraficoDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.MunicipioDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public class GraficoDaoImpl implements GraficoDAO {

	public String graflinallesfed() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		 
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where x.subsistema = 34 and x.folio not in (999999999999) and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2, x.subsistema "
				+"order by b.nom_region2";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	
	//--Grafica (2) Geneal - Total de Plazas por RegiÃ³n Estatal
	
	public String graflinallestal() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where x.subsistema = 15 and x.folio not in (999999999999) and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2, x.subsistema "
				+"order by b.nom_region2";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	
	//--Grafica (3) Geneal ---Total de Plazas Asignadas General
	
	public String grafdos() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where folio not in (0, 999999999999) and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2 "
				+"order by b.nom_region2 ";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	
	//Grafica (3) General ---Total de Plazas Disponibles General
	
	public String graftres() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where folio= 0 and folio not in (999999999999) and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2 "
				+"order by b.nom_region2 ";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	//Grafica (3) General ---Total de Plazas Canceladas General
	public String grafcanceladas() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where folio in (999999999999) and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2 "
				+"order by b.nom_region2 ";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	//--Grafica (4) Geneal ---Total de Plazas Disponibles Estatal
	
	public String grafcuatro() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where x.subsistema = 15 and folio= 0 and folio not in (999999999999) and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2 "
				+"order by b.nom_region2 ";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	
	//--Grafica (4) Geneal ---Total de Plazas Asignadas Estatal
	
	public String grafcinco() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where x.subsistema = 15 and folio not in (0,999999999999) and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2 "
				+"order by b.nom_region2 ";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		return resultado;
	}
	//Grafica (4) ---Total de Plazas Canceladas Estatal
	
	public String grafcanceladasestatal() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where x.subsistema = 15 and folio in (999999999999) and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2 "
				+"order by b.nom_region2 ";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}	
	//Query Total Plazas Federales, Disponibles //
	public String grafseis() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where x.subsistema = 34 and folio not in (0,999999999999) and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2 "
				+"order by b.nom_region2 ";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		return resultado;
	}
	//Query Total Plazas Federales, Disponibles //
	public String grafsiete() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where x.subsistema = 34 and x.folio= 0 and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2 "
				+"order by b.nom_region2 ";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		return resultado;
	}
	//Grafica (4) ---Total de Plazas Canceladas Federal
	
	public String grafcanceladasfederal() throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
				+"where x.subsistema = 34 and folio in (999999999999) and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
				+"group by b.nom_region2 "
				+"order by b.nom_region2 ";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			resultado = "[";
			while(rs.next()){
				resultado += rs.getString("cont") + ",";
			}
			
			resultado = resultado.substring(0,resultado.length()-1);
			
			resultado = resultado + "]";
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}	
	
	//Query Total Plazas Federales, Disponibles //
		public String grafcho() throws DBExcepcion, SistemaExcepcion {
			String resultado = "";
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sSql = "";
			
			sSql = "select b.nom_region2, count(*) cont  from TBPZSVAC X , MUNICIPIOS Z, REGIONES B "
					+"where x.subsistema = 15 and x.folio= 0 and X.cvemun = Z.id_municipio and z.id_region=b.id_region "
					+"group by b.nom_region2 "
					+"order by b.nom_region2 ";
			
			try {
				Conexion cnn = new Conexion();
				cnn.conectar();
				
				ps = cnn.prepareStatement(sSql);
				rs = ps.executeQuery();
				
				resultado = "[";
				while(rs.next()){
					resultado += rs.getString("cont") + ",";
				}
				
				resultado = resultado.substring(0,resultado.length()-1);
				
				resultado = resultado + "]";
				
				rs.close();
				ps.close();
				cnn.desconectar();
			} catch (DBExcepcion dbe) {
				throw dbe;
			} catch (SQLException sqle) {
				throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			}
			return resultado;
		}
		//Query Total Plazas Region 01 Estatales //
		public String grafregunoest() throws DBExcepcion, SistemaExcepcion {
			String resultado = "";
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sSql = "";
			
			sSql = "select count(*) count from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					+ "where x.subsistema = 15 and b.nom_region2 = 'REGIÃ“N 01 AMECAMECA'"
					+ "group by x.subsistema "; 
			
			try {
				Conexion cnn = new Conexion();
				cnn.conectar();
				
				ps = cnn.prepareStatement(sSql);
				rs = ps.executeQuery();
				
				resultado = "[";
				while(rs.next()){
					resultado += rs.getString("cont") + ",";
				}
				
				resultado = resultado.substring(0,resultado.length()-1);
				
				resultado = resultado + "]";
				
				rs.close();
				ps.close();
				cnn.desconectar();
			} catch (DBExcepcion dbe) {
				throw dbe;
			} catch (SQLException sqle) {
				throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			}
			return resultado;
		}
		//Query Total Plazas Region 01 Federales //
		public String grafregunofed() throws DBExcepcion, SistemaExcepcion {
			String resultado = "";
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sSql = "";
			
			sSql = "select count(*) count from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					+ "where x.subsistema = 34 and b.nom_region2 = 'REGIÃ“N 01 AMECAMECA'"
					+ "group by x.subsistema ";
			
			try {
				Conexion cnn = new Conexion();
				cnn.conectar();
				
				ps = cnn.prepareStatement(sSql);
				rs = ps.executeQuery();
				
				resultado = "[";
				while(rs.next()){
					resultado += rs.getString("cont") + ",";
				}
				
				resultado = resultado.substring(0,resultado.length()-1);
				
				resultado = resultado + "]";
				
				rs.close();
				ps.close();
				cnn.desconectar();
			} catch (DBExcepcion dbe) {
				throw dbe;
			} catch (SQLException sqle) {
				throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			}
			return resultado;
		}
		//Query Total Plazas Federales, Disponibles //
		public String grafregionuno() throws DBExcepcion, SistemaExcepcion {
			String resultado = "";
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sSql = "";
			
			sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"  FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 1 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, "
					+"  (SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC "
					+"  WHERE SUBSISTEMA = 15 "
					+"  GROUP BY CVEMUN) VACS "
					+"  WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) "
					+"  ORDER BY MPOS.NOM_MUNICIPIO "; 
			
			try {
				Conexion cnn = new Conexion();
				cnn.conectar();
				
				ps = cnn.prepareStatement(sSql);
				rs = ps.executeQuery();
				
				resultado = "[";
				while(rs.next()){
					resultado += rs.getString("cont") + ",";
				}
				
				resultado = resultado.substring(0,resultado.length()-1);
				
				resultado = resultado + "]";
				
				rs.close();
				ps.close();
				cnn.desconectar();
			} catch (DBExcepcion dbe) {
				throw dbe;
			} catch (SQLException sqle) {
				throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			}
			return resultado;
		}
		//Query Total Plazas Federales, Disponibles //
		public String grafregionunodos() throws DBExcepcion, SistemaExcepcion {
			String resultado = "";
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sSql = "";
			
			sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"  FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 1 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, "
					+"  (SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC "
					+"  WHERE SUBSISTEMA = 34 "
					+"  GROUP BY CVEMUN) VACS "
					+"  WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) "
					+"  ORDER BY MPOS.NOM_MUNICIPIO "; 
			try {
				Conexion cnn = new Conexion();
				cnn.conectar();
				
				ps = cnn.prepareStatement(sSql);
				rs = ps.executeQuery();
				
				resultado = "[";
				while(rs.next()){
					resultado += rs.getString("cont") + ",";
				}
				resultado = resultado.substring(0,resultado.length()-1);
				resultado = resultado + "]";
				rs.close();
				ps.close();
				cnn.desconectar();
			} catch (DBExcepcion dbe) {
				throw dbe;
			} catch (SQLException sqle) {
				throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			}
			return resultado;
		}
		
		public String queryreg0103() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
				  +"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
				  +"FROM REGIONES REG, MUNICIPIOS MPO "
			      +"WHERE REG.ID_REGION = MPO.ID_REGION "
				  +"AND REG.ID_REGION = 1 "
			      +"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
				  +"SELECT cvemun, COUNT (*) REGS "
				  +"FROM TBPZSVAC "
			      +"WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
				  +"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0104() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
				  +"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+ "FROM REGIONES REG, MUNICIPIOS MPO "
					+ "WHERE REG.ID_REGION = MPO.ID_REGION "
					+ "AND REG.ID_REGION = 1 "
					+ "GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+ "SELECT cvemun, COUNT (*) REGS "
					+ "FROM TBPZSVAC "
					+ "WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+ "GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0105() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
				  +"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+  "FROM REGIONES REG, MUNICIPIOS MPO "
					+  "WHERE REG.ID_REGION = MPO.ID_REGION "
					+  "AND REG.ID_REGION = 1 "
					+  "GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+  "SELECT cvemun, COUNT (*) REGS "
					+  "FROM TBPZSVAC " 
					+  "WHERE SUBSISTEMA = 15 and folio = 0 "
					+  "GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0106() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
				  +"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					 +"WHERE REG.ID_REGION = MPO.ID_REGION "
					  +"AND REG.ID_REGION = 1 "
					  +"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					  +"SELECT cvemun, COUNT (*) REGS "
					  +"FROM TBPZSVAC "
					  +"WHERE SUBSISTEMA = 34 and folio = 0 "
					  +"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0107() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 1 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0108() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 1 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0203() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					  +"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					  +"FROM REGIONES REG, MUNICIPIOS MPO "
					  +"WHERE REG.ID_REGION = MPO.ID_REGION "
					  +"AND REG.ID_REGION = 2 "
					  +"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					  +"SELECT cvemun, COUNT (*) REGS "
					  +"FROM TBPZSVAC "
					  +"WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					  +"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0204() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
				    +"AND REG.ID_REGION = 2 "
				    +"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
				    +"SELECT cvemun, COUNT (*) REGS "
				    +"FROM TBPZSVAC "
				    +"WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
				    +"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0205() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 2 "
				    +"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
				    +"SELECT cvemun, COUNT (*) REGS "
				    +"FROM TBPZSVAC "
			        +"WHERE SUBSISTEMA = 15 and folio = 0 "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0206() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 2 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 34 and folio = 0 "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0207() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
				 +"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
				 +"FROM REGIONES REG, MUNICIPIOS MPO "
				 +"WHERE REG.ID_REGION = MPO.ID_REGION "
				 +"AND REG.ID_REGION = 2 "
				 +"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
				 +"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
				 +"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0208() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
				+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
				+"FROM REGIONES REG, MUNICIPIOS MPO "
				+"WHERE REG.ID_REGION = MPO.ID_REGION "
                +"AND REG.ID_REGION = 2 "
				+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
				+"WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
				+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0303() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 3 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0304() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 3 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0305() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT " 
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 3 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 15 and folio = 0 "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0306() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 3 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 34 and folio = 0 "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0307() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 3 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0308() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT"
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 3 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+"WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0403() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
+"FROM REGIONES REG, MUNICIPIOS MPO "
+"WHERE REG.ID_REGION = MPO.ID_REGION "
+"AND REG.ID_REGION = 4"
+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
+"SELECT cvemun, COUNT (*) REGS "
+"FROM TBPZSVAC "
+"WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0404() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 4 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0405() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 4 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC  "
					+"WHERE SUBSISTEMA = 15 and folio = 0 "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0406() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 4 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0407() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
				+"	FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
				+"	FROM REGIONES REG, MUNICIPIOS MPO "
				+"	WHERE REG.ID_REGION = MPO.ID_REGION "
				+"	AND REG.ID_REGION = 4 "
				+"	GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
				+"	WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
				+"	GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0408() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
				+"	FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
				+"	FROM REGIONES REG, MUNICIPIOS MPO "
				+"	WHERE REG.ID_REGION = MPO.ID_REGION "
 				+"	AND REG.ID_REGION = 4 "
				+"	GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
				+"	WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
				+"	GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0503() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION in (5,6) "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0504() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION in (5,6) "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0505() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
				+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
				+"  FROM REGIONES REG, MUNICIPIOS MPO "
				+"  WHERE REG.ID_REGION = MPO.ID_REGION "
				+"  AND REG.ID_REGION in (5,6) "
				+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
				+"  SELECT cvemun, COUNT (*) REGS "
				+"  FROM TBPZSVAC  "
				+"  WHERE SUBSISTEMA = 15 and folio = 0 "
				+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0506() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION in (5,6) "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC  "
					+"WHERE SUBSISTEMA = 34 and folio = 0 "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0507() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION in (5,6) "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0508() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION in (5,6) "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0603() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 7 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC "
					+"  WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0604() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 7 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0605() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 7 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0606() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 7 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0607() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 7 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0608() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 7 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0703() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 8 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0704() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 8 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0705() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 8 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0706() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 8 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0707() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 8 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC  "
					+"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0708() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 8 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC  "
					+"WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0803() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 9 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0804() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO " 
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 9 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC  "
					+"WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0805() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 9 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"SELECT cvemun, COUNT (*) REGS "
					+"FROM TBPZSVAC  "
					+"WHERE SUBSISTEMA = 15 and folio = 0 "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0806() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 9 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0807() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 9 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					+"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0808() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 9 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+"WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0903() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 10 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0904() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 10 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0905() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 10 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0906() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 10 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0907() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 10 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0908() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = " SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+" FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION = 10 "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+" WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1003() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 11 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1004() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 11 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1005() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 11 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1006() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"  FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 11 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1007() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 11 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1008() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+" FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION = 11 "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC  "
					+" WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO "; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1103() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 12 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1104() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 12 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1105() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 12 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1106() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 12 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1107() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION = 12 "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "  
					+" WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1108() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 12 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+"WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1203() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 13 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1204() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 13 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1205() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 13 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1206() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 13 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1207() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 13 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1208() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+" FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION = 13 "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+" WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1303() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION IN (14,15) "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1304() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION IN (14,15) "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1305() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
+"	FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
+"  FROM REGIONES REG, MUNICIPIOS MPO "
+"  WHERE REG.ID_REGION = MPO.ID_REGION "
+"  AND REG.ID_REGION IN (14,15) "
+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
+"  SELECT cvemun, COUNT (*) REGS "
+"  FROM TBPZSVAC  "
+"  WHERE SUBSISTEMA = 15 and folio = 0 "
+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1306() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION IN (14,15) "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1307() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+" FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION IN (14,15) "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+" WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1308() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+" FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION IN (14,15) "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+" WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1403() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 16 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1404() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 16 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1405() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 16 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1406() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 16 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1407() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+" FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION = 16 "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+" WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1408() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+" FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION = 16 "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					+" WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1503() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"   FROM REGIONES REG, MUNICIPIOS MPO "
					+"   WHERE REG.ID_REGION = MPO.ID_REGION "
					+"   AND REG.ID_REGION = 17  "
					+"   GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"   SELECT cvemun, COUNT (*) REGS "
					+"   FROM TBPZSVAC  "
					+"   WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					+"   GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1504() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 17 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1505() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 17 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1506() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 17 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1507() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+" FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION = 17 "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+" WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1508() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT"
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+" FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION = 17 "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC  "
					+" WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1603() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
+"  FROM REGIONES REG, MUNICIPIOS MPO "
+"  WHERE REG.ID_REGION = MPO.ID_REGION "
+"  AND REG.ID_REGION = 18 "
+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
+"  SELECT cvemun, COUNT (*) REGS "
+"  FROM TBPZSVAC  "
+"  WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1604() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 18 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio not in (999999999999) "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1605() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 18 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 15 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1606() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM ( SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"  FROM REGIONES REG, MUNICIPIOS MPO "
					+"  WHERE REG.ID_REGION = MPO.ID_REGION "
					+"  AND REG.ID_REGION = 18 "
					+"  GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, ( "
					+"  SELECT cvemun, COUNT (*) REGS "
					+"  FROM TBPZSVAC  "
					+"  WHERE SUBSISTEMA = 34 and folio = 0 "
					+"  GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1607() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+"FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+"FROM REGIONES REG, MUNICIPIOS MPO "
					+"WHERE REG.ID_REGION = MPO.ID_REGION "
					+"AND REG.ID_REGION = 18 "
					+"GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC " 
					+"WHERE SUBSISTEMA = 15 and folio not in (0,999999999999) "
					+"GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg1608() throws DBExcepcion, SistemaExcepcion {
		   String resultado = "";
		   PreparedStatement ps = null;
		   ResultSet rs = null;
		   String sSql = "";
		   
		   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT "
					+" FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO  "
					+" FROM REGIONES REG, MUNICIPIOS MPO "
					+" WHERE REG.ID_REGION = MPO.ID_REGION "
					+" AND REG.ID_REGION = 18 "
					+" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC  "
					+" WHERE SUBSISTEMA = 34 and folio not in (0,999999999999) "
					+" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
		   try {
		    Conexion cnn = new Conexion();
		    cnn.conectar();
		    
		    ps = cnn.prepareStatement(sSql);
		    rs = ps.executeQuery();
		    
		    resultado = "[";
		    while(rs.next()){
		     resultado += rs.getString("cont") + ",";
		    }
		    resultado = resultado.substring(0,resultado.length()-1);
		    resultado = resultado + "]";
		    rs.close();
		    ps.close();
		    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
		   return resultado;
		  }
		public String queryreg0101() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = '1' and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0102() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = '1' and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0201() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = '2' and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0202() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = '2' and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0301() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = '3' and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0302() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = '3' and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }	
		
		public String queryreg0401() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = '4' and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0402() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = '4' and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }		
		public String queryreg0501() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION in (5,6) and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0502() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION in (5,6) and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }			
		public String queryreg0601() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = 7 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0602() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = 7 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }			
		public String queryreg0701() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = 8 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0702() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = 8 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }			
		public String queryreg0801() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = 9 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0802() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = 9 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }				
		public String queryreg0901() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = 10 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg0902() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = 10 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }				
		public String queryreg1001() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = 11 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg1002() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = 11 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }				
		public String queryreg1101() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = 12 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg1102() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = 12 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }				
		public String queryreg1201() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = 13 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg1202() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = 13 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }				
		public String queryreg1301() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION in (14,15) and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg1302() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION in (14,15) and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }			
		public String queryreg1401() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = 16 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg1402() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = 16 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }		
		public String queryreg1501() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = 17 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg1502() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = 17 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }		
		public String queryreg1601() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 34 and b.ID_REGION = 18 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }
		public String queryreg1602() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count(*) cont from TBPZSVAC X left outer join MUNICIPIOS E on x.cvemun = E.id_municipio left outer join REGIONES B on E.id_region=b.id_region "
					  +" where x.subsistema = 15 and b.ID_REGION = 18 and x.folio not in (999999999999) "
				      +" group by x.subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }	
		public List<MunicipioDTO> obtenerMunicipios(String idRegion)throws DBExcepcion, SistemaExcepcion {
			List<MunicipioDTO> listMunicipios2 = new ArrayList<MunicipioDTO>();
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    String sSql = "SELECT * FROM municipios WHERE id_region2 = 	" + idRegion;
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    System.out.println(sSql);
			    while(rs.next()){
			    	MunicipioDTO municipio = new MunicipioDTO();
			    	municipio.setIdMunicipio(rs.getString("id_municipio"));
			    	municipio.setNomMunicipio(rs.getString("nom_municipio"));
			    	
			    	listMunicipios2.add(municipio);
			    }
				rs.close();
			    ps.close();
			    cnn.desconectar();
		   } catch (DBExcepcion dbe) {
		    throw dbe;
		   } catch (SQLException sqle) {
		    throw new SistemaExcepcion("Error en el método obtenerMunicipios de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		   }
			return listMunicipios2;
		}	
		
		public String queryreg1701() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "SELECT MPOS.NOM_MUNICIPIO, NVL(VACS.REGS, 0) CONT FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					    +" FROM REGIONES REG, MUNICIPIOS MPO "
					    +" WHERE REG.ID_REGION = MPO.ID_REGION "
					    +" AND MPO.nom_municipio in ('AMECAMECA', 'ATLAUTLA', 'AYAPANGO', 'CHALCO', 'CHICOLOAPAN', " 
					    +" 'CHIMALHUACÃN','IXTAPALUCA','JUCHITEPEC','NEZAHUALCOYOTL','OZUMBA','LA PAZ','TEMAMATLA','TENANGO DEL AIRE','TEPETLIXPA', "
					    +" 'TEXCOCO','TLALMANALCO','VALLE DE CHALCO SOLIDARIDAD') "
					    +" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					    +" WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					    +" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }	
		
		public String queryreg1701D() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "SELECT MPOS.NOM_MUNICIPIO FROM (SELECT MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO "
					    +" FROM REGIONES REG, MUNICIPIOS MPO "
					    +" WHERE REG.ID_REGION = MPO.ID_REGION "
					    +" AND MPO.nom_municipio in ('AMECAMECA', 'ATLAUTLA', 'AYAPANGO', 'CHALCO', 'CHICOLOAPAN', " 
					    +" 'CHIMALHUACÃN','IXTAPALUCA','JUCHITEPEC','NEZAHUALCOYOTL','OZUMBA','LA PAZ','TEMAMATLA','TENANGO DEL AIRE','TEPETLIXPA', "
					    +" 'TEXCOCO','TLALMANALCO','VALLE DE CHALCO SOLIDARIDAD') "
					    +" GROUP BY MPO.ID_MUNICIPIO, MPO.NOM_MUNICIPIO ) MPOS, (SELECT cvemun, COUNT (*) REGS FROM TBPZSVAC "
					    +" WHERE SUBSISTEMA = 34 and folio not in (999999999999) "
					    +" GROUP BY CVEMUN) VACS WHERE MPOS.ID_MUNICIPIO = VACS.CVEMUN (+) ORDER BY MPOS.NOM_MUNICIPIO"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }		
		
		public String queryregallfed() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count (*) cont from TBPZSVAC where subsistema = 34 and folio not in (999999999999) group by subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }	
		
		public String queryregallest() throws DBExcepcion, SistemaExcepcion {
			   String resultado = "";
			   PreparedStatement ps = null;
			   ResultSet rs = null;
			   String sSql = "";
			   
			   sSql = "select count (*) cont from TBPZSVAC where subsistema = 15 and folio not in (999999999999)group by subsistema"; 
			   try {
			    Conexion cnn = new Conexion();
			    cnn.conectar();
			    
			    ps = cnn.prepareStatement(sSql);
			    rs = ps.executeQuery();
			    
			    resultado = "[";
			    while(rs.next()){
			     resultado += rs.getString("cont") + ",";
			    }
			    resultado = resultado.substring(0,resultado.length()-1);
			    resultado = resultado + "]";
			    rs.close();
			    ps.close();
			    cnn.desconectar();
			   } catch (DBExcepcion dbe) {
			    throw dbe;
			   } catch (SQLException sqle) {
			    throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
			   }
			   return resultado;
			  }	

}
