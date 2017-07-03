package mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dao.UsuarioDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto.PermisoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.modelo.login.dto.UsuarioDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public class UsuarioDaoImpl implements UsuarioDAO {

	private String _Key = "TEMPORAL";
	private String _genPwd = "TEMPORAL";

	
	@Override
	public String guardarUsuario(UsuarioDTO usuario) throws DBExcepcion,
	SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN P_INSERTAR_USUARIO_SIGDIP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
		
		try {
			//Recuperar los permisos seleccionados;
			String lstPermisos = "";
			if(usuario.getLstPermiso() != null && !usuario.getLstPermiso().isEmpty()) {
				Iterator<String> iterator = usuario.getLstPermiso().iterator();
				int i = 0;
				while (iterator.hasNext()) {
					String string = (String) iterator.next();
					if (string != null && !string.trim().equals("")) {
						if (i > 0) {
							lstPermisos = lstPermisos + "|";
						}
						lstPermisos = lstPermisos + string;
					}
					i++;
				}
			}
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			CallableStatement cStmt = cnn.prepareCall(sSql);

			cStmt.setString(1,usuario.getCveUsuario().trim().toLowerCase());
			cStmt.setString(2,usuario.getNombre().trim().toUpperCase());
			cStmt.setString(3,usuario.getApePaterno().trim().toUpperCase());
			cStmt.setString(4,usuario.getApeMaterno().trim().toUpperCase());
			cStmt.setString(5,usuario.getEmail().trim());
			cStmt.setString(6,usuario.getTelefono1().trim());
			cStmt.setString(7,usuario.getTelefono2().trim());
			cStmt.setString(8,_genPwd);
			cStmt.setString(9,usuario.getSysAdmin().trim());
			cStmt.setString(10,usuario.getUsrAlta());
			
			if(usuario.getIdSubsistema() != null && usuario.getIdSubsistema() != ""){
				cStmt.setInt(11, Integer.parseInt(usuario.getIdSubsistema()));
			}else{
				cStmt.setInt(11, 0);
			}
			
			if(usuario.getIdAsignatura() != null && usuario.getIdAsignatura() != ""){
				cStmt.setInt(12, Integer.parseInt(usuario.getIdAsignatura()));
			}else{
				cStmt.setInt(12, 0);
			}
			
			if(usuario.getIdSede() != null && usuario.getIdSede() != ""){
				cStmt.setInt(13, Integer.parseInt(usuario.getIdSede()));
			}else{
				cStmt.setInt(13, 0);
			}
			
			
			cStmt.setString(14,((!lstPermisos.trim().equals("")) ? lstPermisos : "0"));

			cStmt.registerOutParameter(15, Types.VARCHAR);
			cStmt.registerOutParameter(16, Types.VARCHAR);
			
			cStmt.execute();
			
			String cveError = cStmt.getString(15);
			String desError = cStmt.getString(16);
			
			resultado = cveError + "|" + desError;
			
			cStmt.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método guardarUsuario() de UsuarioDaoImpl:\n" + sqle.getMessage(), sqle);
		}

		return resultado;
	}

	@Override
	public String actualizar(UsuarioDTO usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN P_ACTUALIZAR_USUARIO_SIGDIP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
		
		try {
			//Recuperar los permisos seleccionados;
			String lstPermisos = "";
			if(usuario.getLstPermisoAdmon() != null && !usuario.getLstPermisoAdmon().isEmpty()) {
				int i = 0;
				Iterator<PermisoDTO> iterator = usuario.getLstPermisoAdmon().iterator();
				while (iterator.hasNext()) {
					PermisoDTO permisoDTO = (PermisoDTO) iterator.next();
					if(permisoDTO != null && permisoDTO.getId() != null && !permisoDTO.getId().trim().equals("")) {
						if (i > 0) {
							lstPermisos = lstPermisos + "|";
						}
						lstPermisos = lstPermisos + permisoDTO.getId();						
					}
					i++;
				}
			}
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			CallableStatement cStmt = cnn.prepareCall(sSql);

			cStmt.setString(1,usuario.getCveUsuario().trim().toLowerCase());
			cStmt.setString(2,usuario.getNombre().trim().toUpperCase());
			cStmt.setString(3,usuario.getApePaterno().trim().toUpperCase());
			cStmt.setString(4,usuario.getApeMaterno().trim().toUpperCase());
			cStmt.setString(5,usuario.getEmail().trim());
			cStmt.setString(6,usuario.getTelefono1().trim());
			cStmt.setString(7,usuario.getTelefono2().trim());
			cStmt.setString(8,_genPwd);
			cStmt.setString(9,usuario.getSysAdmin().trim());
			cStmt.setString(10,usuario.getUsrUMO());
			
			
			if(usuario.getIdSubsistema() != null && usuario.getIdSubsistema() != ""){
				cStmt.setInt(11, Integer.parseInt(usuario.getIdSubsistema()));
			}else{
				cStmt.setInt(11, 0);
			}
			
			if(usuario.getIdAsignatura() != null && usuario.getIdAsignatura() != ""){
				cStmt.setInt(12, Integer.parseInt(usuario.getIdAsignatura()));
			}else{
				cStmt.setInt(12, 0);
			}
			
			if(usuario.getIdSede() != null && usuario.getIdSede() != ""){
				cStmt.setInt(13, Integer.parseInt(usuario.getIdSede()));
			}else{
				cStmt.setInt(13, 0);
			}
				
			cStmt.setString(14,((!lstPermisos.trim().equals("")) ? lstPermisos : "0"));
			
			cStmt.registerOutParameter(15, Types.VARCHAR);
			cStmt.registerOutParameter(16, Types.VARCHAR);
			
			cStmt.execute();
			
			String cveError = cStmt.getString(15);
			String desError = cStmt.getString(16);
			
			resultado = cveError + "|" + desError;
			
			cStmt.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método actualizar() de UsuarioDaoImpl:\n" + sqle.getMessage(), sqle);
		}

		return resultado;
	}
	
	public UsuarioDTO getUsuario(String cveUsuario) throws DBExcepcion, SistemaExcepcion {
		UsuarioDTO usuario = null;
		
		String sSql = "SELECT "
					+ "	USU.ID_USUARIO, "
					+ " USU.CVE_USUARIO, " 
					+ " TRIM(TRIM(USU.NOMBRE) || ' ' || TRIM(USU.APE_PATERNO) || ' ' || NVL(USU.APE_MATERNO, '')) AS NOM_COMPLETO,"
					+ " USU.PWD_USUARIO, "
					+ " USU.ID_SEDE, "
					+ " USU.ID_ASIGNATURA, "
					+ " USU.ID_SUBSISTEMA, "
					+ " USU.GPO_ASIGNATURAS, "
					+ " USU.SYS_ADMIN, "
					+ " SED.NOMBRE_SEDE "
					+ "FROM "
					+ " USUARIO_SIGDIP USU, SEDES SED "
					+ "WHERE "
					+ " USU.ID_SEDE = SED.ID_SEDE (+) "
					+ " AND USU.B_BAJA = 'F' "
					+ "	AND USU.CVE_USUARIO = '" + cveUsuario + "'";
		
		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				usuario = new UsuarioDTO();
				usuario.setId(rs.getString("ID_USUARIO"));
				usuario.setCveUsuario(rs.getString("CVE_USUARIO"));
				usuario.setNombreCompleto(rs.getString("NOM_COMPLETO"));
				usuario.setPwdUsuario(rs.getString("PWD_USUARIO"));
				usuario.setSysAdmin(rs.getString("SYS_ADMIN"));
				usuario.setIdSede(rs.getString("id_sede"));
				usuario.setIdAsignatura(rs.getString("id_asignatura"));
				usuario.setIdSubsistema(rs.getString("id_subsistema"));
				usuario.setGpoAsignaturas(rs.getString("GPO_ASIGNATURAS"));
				usuario.setNombreSede(rs.getString("NOMBRE_SEDE"));

				String sSql2 = "SELECT CVE_PERMISO "
						 + "FROM USUARIO_PERMISO_SIGDIP UP, PERMISO_SIGDIP PE " 
						 + "WHERE UP.ID_PERMISO = PE.ID_PERMISO "
						 + "AND ID_USUARIO = " + rs.getString("ID_USUARIO");
			
				//System.out.println(sSql2);
			
				PreparedStatement ps2 = cnn.prepareStatement(sSql2);
				ResultSet rs2 = ps2.executeQuery();
				List<String> lstPermiso = new ArrayList<String>();
				while(rs2.next()){
					lstPermiso.add(rs2.getString("CVE_PERMISO"));
				}
			
				rs2.close();
				ps2.close();
			
				usuario.setLstPermiso(lstPermiso);

			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getUsuario() de UsuarioDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return usuario;
	}
	
	public boolean compararPassword(String pwdUsuario, String encriptado) throws DBExcepcion, SistemaExcepcion {
		boolean resultado = false;
		
		String sSql = "SELECT PKG_ENCRIPCION_SIGDIP.F_ENCRIPTAR('" + pwdUsuario + "') AS ENCRIPTADO FROM DUAL";
		
		try {
			//System.out.println(sSql);

			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				String encripPwd = rs.getString("ENCRIPTADO");
				
				
				//System.out.println(encriptado + " - " + encripPwd);
				
				if(encriptado.equals(encripPwd)) {
					resultado = true;
				}
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();

		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método compararPassword() de UsuarioDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	
	@Override
	public String cambiarPassword(String cveUsuario, String password)
			throws DBExcepcion, SistemaExcepcion {
		String resultado = "error";
		
		//Reset
		if (password == null || password.trim().equals("")) {
			password = _genPwd;
		}
		
		String sSql = "UPDATE USUARIO_SIGDIP SET PWD_USUARIO = PKG_ENCRIPCION_SIGDIP.F_ENCRIPTAR('" + password + "') WHERE CVE_USUARIO = '" + cveUsuario + "'";
		
		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			cnn.getConexion().setAutoCommit(false);
			
			int rstIns = 0;
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			rstIns = ps.executeUpdate();
			
			if (rstIns > 0) {
				cnn.getConexion().commit();
				resultado = "exito";
			} else {
				cnn.getConexion().rollback();
			}
			
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método cambiarPassword() de UsuarioDaoImpl:\n" + sqle.getMessage(), sqle);
		}

		return resultado;
	}
	
	@Override
	public List<UsuarioDTO> getUsuarioFiltrado(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		List<UsuarioDTO> lstUsuario = new ArrayList<UsuarioDTO>();
		
		String sSql = "SELECT "
					+ " ID_USUARIO,"
					+ " CVE_USUARIO,"
					+ " NOMBRE,"
					+ " APE_PATERNO,"
					+ " APE_MATERNO,"
					+ " TRIM(TRIM(NOMBRE) || ' ' || TRIM(APE_PATERNO) || ' ' || NVL(APE_MATERNO, '')) AS NOM_COMPLETO,"
					+ " TELEFONO1,"
					+ " TELEFONO2,"
					+ " ID_SEDE, "
					+ " ID_ASIGNATURA, "
					+ " ID_SUBSISTEMA, "
					+ " GPO_ASIGNATURAS, "
					+ " EMAIL,"
					+ " SYS_ADMIN"
					+ " FROM USUARIO_SIGDIP ";
		
		if(lstFiltro != null && lstFiltro.size() > 0) {
			int ctdFiltro = 0;
			Iterator<CampoRptDTO> it = lstFiltro.iterator();
			while(it.hasNext()) {
				CampoRptDTO filtro = (CampoRptDTO) it.next();
				String clave = filtro.getClave();
				String valor = filtro.getValor();
				/*
				boolean isCadena = filtro.isCadena();
				boolean isNumero = filtro.isNumero();
				boolean isFecha = filtro.isFecha();
				*/
				boolean isLike = filtro.isbLike();
				
				if (ctdFiltro == 0) {
					sSql += " WHERE ";
				} else {
					sSql += " AND ";
				}

				if (clave.equals("CVE_USUARIO")) {
					sSql += " CVE_USUARIO = '" + valor + "'";
				}
				if (clave.equals("NOMBRE")) {
					sSql += " NOMBRE " + ((isLike) ? "LIKE '%" + valor.trim().toUpperCase() + "%'" : "= '" + valor + "'");
				}
				if (clave.equals("APE_PATERNO")) {
					sSql += " APE_PATERNO " + ((isLike) ? "LIKE '%" + valor.trim().toUpperCase() + "%'" : "= '" + valor + "'");
				}
				if (clave.equals("APE_MATERNO")) {
					sSql += " APE_MATERNO " + ((isLike) ? "LIKE '%" + valor.trim().toUpperCase() + "%'" : "= '" + valor + "'");
				}
				if (clave.equals("B_BAJA")) {
					sSql += " B_BAJA = '" + valor + "'";
				}
				
				ctdFiltro++;
			}
		}
		
		sSql += " ORDER BY CVE_USUARIO";

		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				UsuarioDTO usuario = new UsuarioDTO();
				
				usuario.setId(rs.getString("ID_USUARIO"));
				usuario.setCveUsuario(rs.getString("CVE_USUARIO"));
				usuario.setNombre(rs.getString("NOMBRE"));
				usuario.setApePaterno(rs.getString("APE_PATERNO"));
				usuario.setApeMaterno(rs.getString("APE_MATERNO"));
				usuario.setTelefono1(rs.getString("TELEFONO1"));
				usuario.setTelefono2(rs.getString("TELEFONO2"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setSysAdmin(rs.getString("SYS_ADMIN"));
				usuario.setIdSede(rs.getString("id_sede"));
				usuario.setIdAsignatura(rs.getString("id_asignatura"));
				usuario.setIdSubsistema(rs.getString("id_subsistema"));
				usuario.setGpoAsignaturas(rs.getString("GPO_ASIGNATURAS"));

				String sSql2 = "SELECT PE.ID_PERMISO, PE.CVE_PERMISO, PE.DESC_PERMISO "
						 + "FROM USUARIO_PERMISO_SIGDIP UP, PERMISO_SIGDIP PE " 
						 + "WHERE UP.ID_PERMISO = PE.ID_PERMISO "
						 + "AND ID_USUARIO =" + rs.getString("ID_USUARIO");
			
				//System.out.println(sSql2);
			
				PreparedStatement ps2 = cnn.prepareStatement(sSql2);
				ResultSet rs2 = ps2.executeQuery();
				
				List<PermisoDTO> lstPermiso = new ArrayList<PermisoDTO>();
				while(rs2.next()){
					PermisoDTO permiso = new PermisoDTO();
					permiso.setId(rs2.getString("ID_PERMISO"));
					permiso.setClave(rs2.getString("CVE_PERMISO"));
					permiso.setDescripcion(rs2.getString("DESC_PERMISO"));
					
					lstPermiso.add(permiso);
				}
			
				rs2.close();
				ps2.close();
			
				usuario.setLstPermisoAdmon(lstPermiso);

				lstUsuario.add(usuario);
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getUsuarioFiltrado() de UsuarioDaoImpl:\n" + sqle.getMessage(), sqle);
		}

		return lstUsuario;
	}

	@Override
	public String deshabilitar(UsuarioDTO usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "error";
		
		String sSql = "UPDATE USUARIO_SIGDIP SET B_BAJA = 'V', FEC_UMO = SYSDATE, USU_UMO = '" + usuario.getUsrUMO() + "'"
						+ " WHERE CVE_USUARIO = '" + usuario.getCveUsuario() + "'";
		
		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			cnn.getConexion().setAutoCommit(false);
			
			int rstIns = 0;
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			rstIns = ps.executeUpdate();
			
			if (rstIns > 0) {
				cnn.getConexion().commit();
				resultado = "exito";
			} else {
				cnn.getConexion().rollback();
			}
			
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método deshabilitar() de UsuarioDaoImpl:\n" + sqle.getMessage(), sqle);
		}

		return resultado;
	}

}
