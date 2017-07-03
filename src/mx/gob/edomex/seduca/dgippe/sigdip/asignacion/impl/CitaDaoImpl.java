package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.CitaDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.CitaDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;
/**
 * Clase para implementar los metodos de cita
 * @author Administrador
 *
 */
public class CitaDaoImpl implements CitaDAO
{
	/**
	 * Metodo para extraer la lista de folios con estatus igual a 1
	 * @return List <AspiranteDTO> 
	 * @throws SQLException 
	 */
	@Override
	public List<AspiranteDTO> consultaFolio(String folio) throws DBExcepcion, SistemaExcepcion, SQLException 
	{
		Conexion cnn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		String sql = "SELECT foliofeder,TIPCONVOCA_A,nom_texn,plaza,curp_a,nombre_a,GPO_DESEMP,prelac  FROM tbloposi WHERE status =1 AND foliofeder = "+folio;
		AspiranteDTO asp=null;
		List<AspiranteDTO> folios = new ArrayList<AspiranteDTO>();
		
		try
		{
			cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				asp= new AspiranteDTO();
				asp.setFolioFederal(rs.getString("foliofeder"));
				if(rs.getString("TIPCONVOCA_A")!=null)
					asp.setTipoConvocatoria(rs.getString("TIPCONVOCA_A").toUpperCase());
				if(rs.getString("nom_texn")==null)
					asp.setNomTexn(rs.getString("plaza"));
				else
				{
					if(rs.getString("nom_texn")!=null)
						asp.setNomTexn(rs.getString("nom_texn").toUpperCase());
				}
				if(rs.getString("curp_a")!=null)
					asp.setCurp(rs.getString("curp_a").toUpperCase());
				if(rs.getString("nombre_a")!=null)
				asp.setNombre(rs.getString("nombre_a").toUpperCase());
				if(rs.getString("GPO_DESEMP")!=null)
					asp.setGpoDesmo(rs.getString("GPO_DESEMP").toUpperCase());
				if(rs.getString("prelac")!=null)
					asp.setPrelac(rs.getString("prelac"));
				folios.add(asp);
			}
		}
		catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método consultaFolio() de CitaDaoImpl:\n" + sqle.getMessage(), sqle);
		}finally {
            if(rs != null){
            	rs.close();
            }
            if(ps != null){
            	ps.close();
            }            
            if(cnn != null){
                cnn.desconectar();
            }
        }
		
		return folios;
	}
	/**
	 * Metodo para insertar una el folio, fecha y curp a una cita 
	 * @throws SQLException 
	 */
	@Override
	public int insertaCita(String folio, String fecha, String curp) throws DBExcepcion, SistemaExcepcion, SQLException
	{
		Conexion cnn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		String sql="";
		int resIns=0;
		
		try
		{
			cnn = new Conexion();
			cnn.conectar();
			String values = folio+",'"+curp+"',TO_DATE('"+fecha+"','dd/mm/yyyy hh24:mi:ss')";
			sql = "INSERT INTO cita (foliofeder,curp,fecha) values("+values+")";
			ps=cnn.prepareStatement(sql);
			resIns=ps.executeUpdate();
			ps.close();
			cnn.desconectar();
		}
		catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método insertarCita() de CitaDaoImpl:\n" + sqle.getMessage(), sqle);
		}finally {
            if(rs != null){
            	rs.close();
            }
            if(ps != null){
            	ps.close();
            }            
            if(cnn != null){
                cnn.desconectar();
            }
        }
		
		return resIns;
	}
	/**
	 * Metodo para actualizar la fecha de una cita que coincida con el folio y el curp
	 */
	@Override
	public int actualizaCita(String folio,String fecha, String curp) throws DBExcepcion, SistemaExcepcion, SQLException
	{
		Conexion cnn = null;
		PreparedStatement ps = null;	
		String sql="";
		int resIns=0;
		
		try
		{
			cnn = new Conexion();
			cnn.conectar();
			String values = "foliofeder="+folio+",fecha=TO_DATE('"+fecha+"','dd/mm/yyyy hh24:mi:ss')";
			sql = "UPDATE  cita SET "+values+" WHERE foliofeder="+folio+" AND curp = '"+curp+"'";

			ps=cnn.prepareStatement(sql);
			resIns=ps.executeUpdate();
			ps.close();
			cnn.desconectar();
		}
		catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método actualizaCita() de CitaDaoImpl:\n" + sqle.getMessage(), sqle);
		}finally {
            if(ps != null){
            	ps.close();
            }            
            if(cnn != null){
                cnn.desconectar();
            }
        }
		
		return resIns;
	}
	/**
	 * Metodo para consultar una cita por folio y curp
	 */
	@Override
	public CitaDTO consultaCita(String folio, String curp) throws DBExcepcion, SistemaExcepcion, SQLException
	{
		Conexion cnn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;			
		String sql = "SELECT foliofeder,fecha  FROM cita WHERE foliofeder = "+folio+" AND curp='"+curp+"'";
		CitaDTO cita =new CitaDTO();
		
		try
		{
			cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next())
			{
				cita.setFolio(rs.getString("foliofeder"));
				cita.setFecha(rs.getDate("fecha"));
			}
		}
		catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método consultaCita() de CitaDaoImpl:\n" + sqle.getMessage(), sqle);
		}finally {
            if(rs != null){
            	rs.close();
            }
            if(ps != null){
            	ps.close();
            }            
            if(cnn != null){
                cnn.desconectar();
            }
        }
		
		return cita;
	}
	/**
	 * Metodo que recupera la lista de citas de una determinada fecha, agrupando por promoción o ingreso
	 */
	public List<AspiranteDTO> consultaCitas(String fecha,String funcion) throws DBExcepcion, SistemaExcepcion, SQLException
	{
		Conexion cnn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		String sql ="";
		//Si funcion es igual a 1, es de ingreso, si es mayor a 1 es promoción
		if(funcion.equals("1"))
			sql 	= "SELECT a.foliofeder,a.TIPCONVOCA_A,a.nom_texn,a.plaza,a.curp_a,nombre_a,a.GPO_DESEMP,a.prelac, c.fecha, TO_CHAR(fecha,'HH24:MI') as hora  "
					+ " FROM tbloposi a, cita c"
					+ " WHERE a.foliofeder = c.foliofeder"
					+ " AND a.curp_a = c.curp"
					+ " AND a.id_funcion = 1"
					+ "	AND TO_DATE(TO_CHAR(fecha,'dd/MM/yyyy'),'dd/MM/yyyy') = TO_DATE('"+ fecha +"','dd/MM/yyyy') "
					+ " ORDER BY gpo_desemp ASC, prelac ASC";
		else
			sql = "SELECT a.foliofeder,a.TIPCONVOCA_A,a.nom_texn,a.plaza,a.curp_a,nombre_a,a.GPO_DESEMP,a.prelac, c.fecha, TO_CHAR(fecha,'HH24:MI') as hora  "
					+ " FROM tbloposi a, cita c"
					+ " WHERE a.foliofeder = c.foliofeder"
					+ " AND a.curp_a = c.curp"
					+ " AND a.id_funcion >1"
					+ "	AND TO_DATE(TO_CHAR(fecha,'dd/MM/yyyy'),'dd/MM/yyyy') = TO_DATE('"+ fecha +"','dd/MM/yyyy') "
					+ " ORDER BY gpo_desemp ASC, prelac ASC";
		
		AspiranteDTO asp =null;
		List<AspiranteDTO>citas=new ArrayList<AspiranteDTO>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		try
		{
			cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				asp= new AspiranteDTO();
				asp.setFolioFederal(rs.getString("foliofeder"));
				
				if(rs.getString("TIPCONVOCA_A")!=null)
					asp.setTipoConvocatoria(rs.getString("TIPCONVOCA_A").toUpperCase());
				if(funcion.equals("1"))
					asp.setNomTexn(rs.getString("plaza").toUpperCase());
				else
				{
					if(rs.getString("nom_texn")!=null)
						asp.setNomTexn(rs.getString("nom_texn").toUpperCase());
				}
				if(rs.getString("curp_a")!=null)
					asp.setCurp(rs.getString("curp_a").toUpperCase());
				if(rs.getString("nombre_a")!=null)
					asp.setNombre(rs.getString("nombre_a").toUpperCase());
				if(rs.getString("GPO_DESEMP")!=null)
					asp.setGpoDesmo(rs.getString("GPO_DESEMP").toUpperCase());
				if(rs.getString("prelac")!=null)
					asp.setPrelac(rs.getString("prelac"));
				asp.setFechaAplicacion(df.format(rs.getDate("fecha")));
				asp.setPlaza(rs.getString("hora"));
				citas.add(asp);
			}
		}
		catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método consultaCitas() de CitaDaoImpl:\n" + sqle.getMessage(), sqle);
		}finally {
            if(rs != null){
            	rs.close();
            }
            if(ps != null){
            	ps.close();
            }            
            if(cnn != null){
                cnn.desconectar();
            }
        }
		
		return citas;
	}

	@Override
	public  List<AspiranteDTO> consultaFolioMS(String folio) throws DBExcepcion, SistemaExcepcion, SQLException
	{
		Conexion cnn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT foliofeder,CLAS_CONVO,ASIGNATURA,curp_a,nombre_a,GPO_DESEMP,prelac  FROM tbloposi_ms WHERE status =1 AND foliofeder = "+folio;
		List<AspiranteDTO> folios = new ArrayList<AspiranteDTO>();
		AspiranteDTO asp=null;
		
		try
		{
			cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{

				asp= new AspiranteDTO();
				asp.setFolioFederal(rs.getString("foliofeder"));
				if(rs.getString("CLAS_CONVO")!=null)
					asp.setTipoConvocatoria(rs.getString("CLAS_CONVO").toUpperCase());
				if(rs.getString("ASIGNATURA")!=null)
					asp.setNomTexn(rs.getString("ASIGNATURA").toUpperCase());
				asp.setCurp(rs.getString("curp_a").toUpperCase());
				asp.setNombre(rs.getString("nombre_a").toUpperCase());
				asp.setGpoDesmo(rs.getString("GPO_DESEMP").toUpperCase());
				asp.setPrelac(rs.getString("prelac"));
				folios.add(asp);
			}
		}
		catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método consultaFolioMS() de CitaDaoImpl:\n" + sqle.getMessage(), sqle);
		}finally {
            if(rs != null){
            	rs.close();
            }
            if(ps != null){
            	ps.close();
            }            
            if(cnn != null){
                cnn.desconectar();
            }
        }
		
		return folios;
	}


	@Override
	public List<AspiranteDTO> consultaCitasMS(String fecha, String subsistema)throws DBExcepcion, SistemaExcepcion, SQLException 
	{
		Conexion cnn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT a.foliofeder,a.CLAS_CONVO,a.ASIGNATURA,a.curp_a,nombre_a,a.GPO_DESEMP,a.prelac, c.fecha, TO_CHAR(fecha,'HH24:MI') as hora  "
				+ " FROM tbloposi_ms a, cita c"
				+ " WHERE a.foliofeder = c.foliofeder"
				+ " AND a.curp_a = c.curp"
				+ " AND a.CVESUBSIST = "+subsistema
				+ "	AND TO_DATE(TO_CHAR(fecha,'dd/MM/yyyy'),'dd/MM/yyyy') = TO_DATE('"+ fecha +"','dd/MM/yyyy') "
				+ " ORDER BY gpo_desemp ASC, prelac ASC";
		AspiranteDTO asp =null;
		List<AspiranteDTO>citas=new ArrayList<AspiranteDTO>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		try
		{
			cnn = new Conexion();
			cnn.conectar();
			
			ps = cnn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				try
				{
				asp= new AspiranteDTO();
				asp.setFolioFederal(rs.getString("foliofeder"));
				if(rs.getString("CLAS_CONVO")!=null)
					asp.setTipoConvocatoria(rs.getString("CLAS_CONVO").toUpperCase());
				if(rs.getString("ASIGNATURA")!=null)
					asp.setNomTexn(rs.getString("ASIGNATURA").toUpperCase());
				asp.setCurp(rs.getString("curp_a").toUpperCase());
				asp.setNombre(rs.getString("nombre_a").toUpperCase());
				asp.setGpoDesmo(rs.getString("GPO_DESEMP").toUpperCase());
				asp.setPrelac(rs.getString("prelac"));
				asp.setFechaAplicacion(df.format(rs.getDate("fecha")));
				asp.setPlaza(rs.getString("hora"));
				citas.add(asp);
				}
				catch(NullPointerException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método onsultaCitasMS() de CitaDaoImpl:\n" + sqle.getMessage(), sqle);
		}finally {
            if(rs != null){
            	rs.close();
            }
            if(ps != null){
            	ps.close();
            }            
            if(cnn != null){
                cnn.desconectar();
            }
        }
		
		return citas;
	}

}