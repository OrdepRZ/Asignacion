package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.VacanteDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.MunicipioDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public class VacanteDaoImpl implements VacanteDAO {
	
	@Override
	public List<VacanteDTO> getVacantes(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		List<VacanteDTO> lstVacante = new ArrayList<VacanteDTO>();
		
		String sSql = "SELECT "
					+ "PZA.ID, PZA.TIPOFUNCION, PZA.TIPOVACANTE, PZA.CVEASIGNATURA, PZA.ASIGNATURA, PZA.PZAJORNADA, "
					+ "PZA.NUMHORAS, PZA.FECINICIO, PZA.FECTERMINO, NVL(PZA.FOLIO, 0) FOLIO, PZA.ID_FUNCION, "
					+ "TO_CHAR(PZA.FECASIGNACION, 'DD/MM/YYYY') FECASIGNACION, PZA.ID_CONVOCATORIA, "
					+ "PZA.TIPO_NOMBRAMIENTO MOTIVO_VACANTE, PZA.CVE_PRESUPUESTAL, PZA.CVEOPOUSU, "
					+ "VAC.DESC_ITEM DESC_TPO_VAC, "
					+ "PZA.CCT, CCT.NOMBRE NOMBRE_CCT, CCT.DOMICILIO DOMICILIO_CCT, CCT.COLONIA COLONIA_CCT, "
					+ "CCT.LOCALIDAD LOCALIDAD_CCT, CCT.CVEMUN, CCT.TURNO TURNO, CCT.ZONAESCOLAR, CCT.SUBDIRREG, "
					+ "CCT.SUBSISTEMA , SUB.DESC_ITEM DESC_SUBSISTEMA, "
					+ "NVL(CCT.LONGITUD,0) LONGITUD_CCT, NVL(CCT.LATITUD,0) LATITUD_CCT, "
					+ "MPO.DESC_ITEM DESC_MPO, NVL(GLM.LATITUD,0) LATITUD_MPO, NVL(GLM.LONGITUD,0) LONGITUD_MPO, "
					+ "CONV.DESC_ITEM DESC_CONV "
					+ "FROM "
					+ "TBPZSVAC PZA, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGSUBSIS' AND ID_ITEM <> 0) SUB, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOVACA' AND ID_ITEM <> 0) VAC, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGCONVOC' AND ID_ITEM <> 0) CONV, "
					+ "TBLCCT CCT, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMPO' AND ID_ITEM <> 0) MPO, "
					+ "GEOREF_MPO GLM "
					+ "WHERE PZA.CCT = CCT.CCT (+) "
					+ "AND   CCT.SUBSISTEMA = SUB.ID_ITEM (+) "
					+ "AND   CCT.CVEMUN = MPO.ID_ITEM (+) "
					+ "AND   PZA.PZAJORNADA = VAC.ID_ITEM (+) "
					+ "AND   MPO.ID_ITEM = GLM.CVE_MPO (+) "
					+ "AND 	 PZA.ID_CONVOCATORIA = CONV.ID_ITEM(+) "	
					;
		
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

				if (clave.equals("TIPOVACANTE")) {
					sSql += " PZA.TIPOVACANTE = '" + valor.trim() + "'" ;
				}
				
				if (clave.equals("CVE_ASIGNATURA")) {
					sSql += " PZA.CVEASIGNATURA = " + valor.trim();
				}

				if (clave.equals("GPO_ASIGNATURAS")) {
					sSql += " PZA.CVEASIGNATURA IN (" + valor.trim() + ")";
				}

				if (clave.equals("CVE_MUNICIPIO")) {
					sSql += " CCT.CVEMUN = " + valor.trim();
				}

				if (clave.equals("SUBSISTEMA")) {
					sSql += " CCT.SUBSISTEMA = " + valor.trim();
				}

				if (clave.equals("DISPONIBLE")) {
					sSql += " PZA.FOLIO = 0";
				}

				if (clave.equals("NODISPONIBLE")) {
					sSql += " PZA.FOLIO <> 0";
				}

				if (clave.equals("FOLIO")) {
					sSql += " PZA.FOLIO = " + valor.trim();
				}

				if (clave.equals("CCT")) {
					sSql += " PZA.CCT = '" + valor.trim() + "'";
				}

				if (clave.equals("ID_CONVOCATORIA")) {
					sSql += " PZA.ID_CONVOCATORIA = " + valor.trim();
				}

				if (clave.equals("ID_FUNCION")) {
					sSql += " PZA.ID_FUNCION = " + valor.trim();
				}

				if (clave.equals("ID")) {
					sSql += " PZA.ID = " + valor.trim();
				}

				if (clave.equals("CVE_PRESUPUESTAL")) {
					sSql += " PZA.CVE_PRESUPUESTAL = '" + valor.trim() + "'";
				}				
				
				ctdFiltro++;
			}
		}
		
		sSql = sSql + " ORDER BY PZA.TIPOVACANTE, MPO.DESC_ITEM, PZA.CVEASIGNATURA, CCT.SUBSISTEMA, PZA.CCT, PZA.FOLIO, PZA.ID ";

		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();

			//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			while(rs.next()) {
				VacanteDTO vacante = new VacanteDTO();
								
				vacante.setCve(rs.getString("ID"));
				vacante.setFuncion(rs.getString("TIPOFUNCION"));
				vacante.setTpoVacante(rs.getString("TIPOVACANTE"));
				vacante.setCveAsignatura(rs.getString("CVEASIGNATURA"));
				vacante.setAsginatura(rs.getString("ASIGNATURA"));
				vacante.setPzajornada(rs.getString("PZAJORNADA"));
				vacante.setHoras(rs.getString("NUMHORAS"));
				vacante.setCmpCCT(rs.getString("CCT"));
				vacante.setFolio(rs.getString("FOLIO"));
				vacante.setNomCCT(rs.getString("NOMBRE_CCT"));
				vacante.setMunicipio(rs.getString("DESC_MPO"));
				vacante.setDomicilio(rs.getString("DOMICILIO_CCT"));
				vacante.setLocalidad(rs.getString("LOCALIDAD_CCT"));
				vacante.setColonia(rs.getString("COLONIA_CCT"));
				vacante.setTurno(rs.getString("TURNO"));
				vacante.setFechaInicio(rs.getString("FECINICIO"));
				vacante.setFechaTermino(rs.getString("FECTERMINO"));
				vacante.setDescTpoVacante(rs.getString("DESC_TPO_VAC"));
				vacante.setLatitudCct(rs.getString("LATITUD_CCT"));
				vacante.setLongitudCct(rs.getString("LONGITUD_CCT"));
				vacante.setLatitudMpo(rs.getString("LATITUD_MPO"));
				vacante.setLongitudMpo(rs.getString("LONGITUD_MPO"));
				vacante.setIdSubsistema(rs.getString("SUBSISTEMA"));
				vacante.setDescSubsistema(rs.getString("DESC_SUBSISTEMA"));
				
				vacante.setFecAsignacion(rs.getString("FECASIGNACION"));
				vacante.setIdConvocatoria(rs.getString("ID_CONVOCATORIA"));
				vacante.setDescConvocatoria(rs.getString("DESC_CONV"));
				vacante.setMotivoVacante(rs.getString("MOTIVO_VACANTE"));
				vacante.setCvePresupuestal(rs.getString("CVE_PRESUPUESTAL"));
				vacante.setUsuarioAsignacion(rs.getString("CVEOPOUSU"));
				vacante.setCveMun(rs.getString("CVEMUN"));
				vacante.setZonaEscolar(rs.getString("ZONAESCOLAR"));
				vacante.setSubDirRegional(rs.getString("SUBDIRREG"));
				vacante.setIdFuncion(rs.getString("ID_FUNCION"));
				
				/*
                if(rs.getString("FECINICIO") != null && rs.getString("FECINICIO") != ""){
                    vacante.setFechaInicio(rs.getString("FECINICIO"));
                    vacante.setdFechaInicio(sdf.parse(vacante.getFechaInicio()));
                }else{
                    vacante.setdFechaInicio(null);
                }
                
                if(rs.getString("FECTERMINO") != null && rs.getString("FECTERMINO") != ""){
                    vacante.setdFechaTermino(sdf.parse(vacante.getFechaTermino()));
                    if(rs.getString("FECTERMINO").equals("31/12/2100")) {
                    	vacante.setFechaTermino("DEFINITIVO");
                    } else {
                    	vacante.setFechaTermino(rs.getString("FECTERMINO"));
                    }
                } else {
                	vacante.setdFechaTermino(null);
                }
                */
                
				lstVacante.add(vacante);
			}

			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getVacantes() de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		} catch (Exception e) {
			throw new SistemaExcepcion("Error en el método getVacantes() de VacanteDaoImpl:\n" + e.getMessage(), e);
		}

		return lstVacante;
	}
	
	@Override
	public List<VacanteDTO> getVacantesGeoref(String idMunicipio)throws DBExcepcion, SistemaExcepcion {
		List<VacanteDTO> vacantes = new ArrayList<VacanteDTO>();
		String sSql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			sSql = "SELECT p.asignatura, p.cct, mpo.desc_item municipio, gc.latitud, gc.longitud, iconos.desc_item icono "
				+"FROM tbpzsvac p, tblcct gc, catalogos asig, catalogos iconos, "
				+"(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMPO' AND ID_ITEM <> 0) mpo "
				+"WHERE p.cct = gc.cct "
				+"AND gc.cvemun = mpo.id_item "
				+"AND p.folio = 0 "
				+"AND asig.nom_cat = 'ASGTPOEVAL' "
				+"AND UPPER(p.asignatura) = UPPER(asig.desc_item) "
				+"AND iconos.nom_cat = 'CATICONOSG' "
				+"AND asig.id_item = iconos.id_item "
				+"AND gc.cvemun = "+ idMunicipio;
			
			//System.out.println(sSql);
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				VacanteDTO vacante = new VacanteDTO();
				vacante.setAsginatura(rs.getString("asignatura"));
				vacante.setCmpCCT(rs.getString("cct"));
				vacante.setMunicipio(rs.getString("municipio"));
				vacante.setLatitudCct(rs.getString("latitud"));
				vacante.setLongitudCct(rs.getString("longitud"));
				vacante.setNombreIcono(rs.getString("icono"));
				
				vacantes.add(vacante);
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getVacantesGeoref() de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		return vacantes;
	}

	@Override
	public List<MunicipioDTO> getMunicipiosVacantes() throws DBExcepcion,SistemaExcepcion {
		List<MunicipioDTO> municipios = new ArrayList<MunicipioDTO>();
		String sSql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			sSql = "SELECT mun.id_item id_municipio, mun.desc_item nom_municipio, gm.latitud, gm.longitud "
				+"FROM (SELECT id_item, desc_item FROM catalogos WHERE nom_cat='ASGMPO' AND id_item <> 0) mun, georef_mpo gm "
				+"WHERE mun.id_item = gm.cve_mpo "
				+"AND mun.id_item IN (select distinct(cct.cvemun) from tbpzsvac vac, tblcct cct where vac.cct = cct.cct and vac.folio = 0) "
				+"ORDER BY mun.desc_item ";
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				MunicipioDTO municipio = new MunicipioDTO();
				municipio.setIdMunicipio(rs.getString("id_municipio"));
				municipio.setNomMunicipio(rs.getString("nom_municipio"));
				municipio.setLatitud(rs.getString("latitud"));
				municipio.setLongitud(rs.getString("longitud"));
				
				municipios.add(municipio);
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getMunicipiosVacantes() de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return municipios;
	}

	@Override
	public List<VacanteDTO> getAsignaturasGeoref(String idMunicipio)throws DBExcepcion, SistemaExcepcion {
		List<VacanteDTO> vacantes = new ArrayList<VacanteDTO>();
		String sSql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			/*sSql = "SELECT DISTINCT(p.asignatura) asignatura, iconos.desc_item icono "
				+"FROM tbpzsvac p, catalogos asig, catalogos iconos "
				+"WHERE p.folio = 0 "
				+"AND p.cvemun = "+ idMunicipio
				+" AND asig.nom_cat = 'ASGTPOEVAL' "
				+"AND UPPER(p.asignatura) = UPPER(asig.desc_item) "
				+"AND iconos.nom_cat = 'CATICONOSG' "
				+"AND asig.id_item = iconos.id_item";*/
			
			sSql = "SELECT COUNT(p.asignatura) conteo, p.asignatura, iconos.desc_item icono "
					+"FROM tbpzsvac p, tblcct cct, catalogos asig, catalogos iconos "
					+"WHERE p.folio = 0 "
					+"AND p.cct = cct.cct "
					+"AND cct.cvemun = "+ idMunicipio
					+" AND asig.nom_cat = 'ASGTPOEVAL' "
					+"AND UPPER(p.asignatura) = UPPER(asig.desc_item) "
					+"AND iconos.nom_cat = 'CATICONOSG' "
					+"AND asig.id_item = iconos.id_item "
					+"GROUP BY p.asignatura, iconos.desc_item";
			
			//System.out.println(sSql);
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				VacanteDTO vacante = new VacanteDTO();
				vacante.setAsginatura(rs.getString("asignatura")+" ("+rs.getString("conteo")+")");
				vacante.setNombreIcono(rs.getString("icono"));
				
				vacantes.add(vacante);
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getAsignaturasGeoref de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		return vacantes;
	}

	@Override
	public AspiranteDTO getVacanteAspirante(String folioFederal) throws DBExcepcion, SistemaExcepcion {
		AspiranteDTO aspirante = new AspiranteDTO();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			sSql = "SELECT foliofeder, nombre_a, curp_a, tipconvoca_a, prelac, cveasignatura, asig.desc_item asignatura, conv.desc_item convocatoria  "
				+"  FROM tbloposi p "
				+" 		LEFT OUTER JOIN catalogos asig ON p.cveasignatura = asig.id_item AND asig.nom_cat = 'ASGTPOEVAL' "
				+"		LEFT OUTER JOIN catalogos conv ON p.id_convocatoria = conv.id_item AND conv.nom_cat = 'ASGCONVOC' "
				+"  WHERE foliofeder = " + folioFederal;
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				aspirante.setFolioFederal(rs.getString("foliofeder"));
				aspirante.setNombre(rs.getString("nombre_a"));
				aspirante.setCurp(rs.getString("curp_a"));
				aspirante.setTipoConvocatoria(rs.getString("tipconvoca_a"));
				aspirante.setPrelac(rs.getString("prelac"));
				aspirante.setCveAsignatura(rs.getString("cveasignatura"));
				aspirante.setAsignatura(rs.getString("asignatura"));
				aspirante.setIdConvocatoria(rs.getString("convocatoria"));
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getFirmante de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return aspirante;
	}

	@Override
	public String actualizaVacante(VacanteDTO vacante) throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		CallableStatement sto = null;
		String sSql = "";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			sSql = "BEGIN P_ACTUALIZAR_VACANTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
			sto = cnn.prepareCall(sSql);
			
			sto.setString(1, vacante.getCve());
			sto.setString(2, vacante.getCmpCCT());
			sto.setString(3, vacante.getNomCCT());
			sto.setString(4, vacante.getTurno());
			sto.setString(5, vacante.getCveMun());
			sto.setString(6, vacante.getLocalidad());
			sto.setString(7, vacante.getDomicilio());
			sto.setString(8, vacante.getZonaEscolar());
			sto.setString(9, vacante.getSubDirRegional());
			sto.setString(10, vacante.getIdSubsistema());
			sto.setString(11, vacante.getTpoVacante());
			sto.setString(12, vacante.getHoras());
			sto.setString(13, vacante.getFechaInicio());
			sto.setString(14, vacante.getFechaTermino());
			sto.setString(15, vacante.getMotivoVacante());
			sto.setString(16, vacante.getPzajornada());
			sto.setString(17, vacante.getCvePresupuestal());
			sto.setString(18, vacante.getCveAsignatura());
			sto.setString(19, vacante.getFuncion());
			
			sto.registerOutParameter(20, 12);
			sto.registerOutParameter(21, 12);
			
			sto.execute();
			
			String cveError = sto.getString(20);
			String descError = sto.getString(21);
			
			sto.close();
			cnn.desconectar();
			
			resultado = cveError + "|" + descError;

		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método asignarVacante() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		return resultado;
	}

	@Override
	public List<VacanteDTO> getVacantesConcluir() throws DBExcepcion,SistemaExcepcion {
		List<VacanteDTO> vacantes = new ArrayList<VacanteDTO>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			sSql = "SELECT COUNT(cveasignatura) cont, asignatura, fectermino, subsistema "+
					"FROM "+
					"    (SELECT id, CONTADOR_DIAS(trunc(to_date(fectermino,'dd/MM/yyyy'))) dias, fectermino, cveasignatura, asignatura, sub.desc_item subsistema "+
					"    FROM tbpzsvac pza, tblcct cct, catalogos sub "+
					"    WHERE pza.cct = cct.cct (+)"+
					"    AND   cct.subsistema = sub.id_item (+) "+
					"    AND   sub.nom_cat = 'ASGSUBSIS' "+
					"    AND   trunc(to_date(fectermino,'dd/MM/yyyy')) > trunc(SYSDATE)) "+
					"WHERE dias = 15 "+
					"GROUP BY cveasignatura, asignatura, fectermino,subsistema "+
					"ORDER BY subsistema,asignatura";
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				VacanteDTO vacante = new VacanteDTO();
				
				vacante.setCveAsignatura(rs.getString("cont"));
				vacante.setAsginatura(rs.getString("asignatura"));
				vacante.setFechaTermino(rs.getString("fectermino"));
				vacante.setDescSubsistema(rs.getString("subsistema"));
				
				vacantes.add(vacante);
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getVacantesConcluir de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		return vacantes;
	}

	@Override
	public String actualizaVacanteNivel(VacanteDTO vacante, String cvePresuOriginal, String usuario) throws DBExcepcion, SistemaExcepcion, SQLException 
	{
		Conexion cnn = null;
		CallableStatement sto = null;		
		String resultado = "";
		String sSql = "";
		
		try 
		{
			cnn = new Conexion();
			cnn.conectar();
			
			sSql = "BEGIN PKGSIAPRODEM.P_ACTUALIZAR_VACANTE_NIVEL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
			sto = cnn.prepareCall(sSql);
			
			sto.setString(1, vacante.getCve());
			sto.setString(2, vacante.getCmpCCT());
			sto.setString(3, vacante.getNomCCT());
			sto.setString(4, vacante.getTurno());
			sto.setString(5, vacante.getCveMun());
			sto.setString(6, vacante.getLocalidad());
			sto.setString(7, vacante.getDomicilio());
			sto.setString(8, vacante.getZonaEscolar());
			sto.setString(9, vacante.getSubDirRegional());
			sto.setString(10, vacante.getIdSubsistema());
			sto.setString(11, vacante.getTpoVacante());
			sto.setString(12, vacante.getHoras().trim());
			sto.setString(13, vacante.getFechaInicio());
			sto.setString(14, vacante.getFechaTermino());
			sto.setString(15, vacante.getMotivoVacante());
			sto.setString(16, vacante.getPzajornada());
			sto.setString(17, vacante.getCvePresupuestal().trim());
			sto.setString(18, vacante.getCveAsignatura());
			sto.setString(19, vacante.getIdFuncion());
			sto.setString(20, cvePresuOriginal);
			sto.setString(21, usuario);
			
			sto.registerOutParameter(22, 12);
			sto.registerOutParameter(23, 12);
			
			sto.execute();
			
			String cveError = sto.getString(22);
			String descError = sto.getString(23);
			
			sto.close();
			cnn.desconectar();
			
			resultado = cveError + "|" + descError;

		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método actualizaVacanteNivel() de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}finally {
            if(sto != null){
            	sto.close();
            }
            if(cnn != null){
                cnn.desconectar();
            }
        }
		
		return resultado;
	}	
	
	
	@Override
	public String registraVacante(VacanteDTO vacante, String usuario) throws DBExcepcion, SistemaExcepcion, SQLException 
	{
		Conexion cnn = null;
		CallableStatement sto = null;		
		String resultado = "";
		String sSql = "";
		
		try {
			cnn = new Conexion();
			cnn.conectar();
			
			sSql = "BEGIN PKGSIAPRODEM.P_REGISTRAR_VACANTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;";
			sto = cnn.prepareCall(sSql);	
			
			sto.setString(1, vacante.getCmpCCT());
			sto.setString(2, vacante.getNomCCT());
			sto.setString(3, vacante.getTurno());
			sto.setString(4, vacante.getCveMun());
			sto.setString(5, vacante.getMunicipio());
			sto.setString(6, vacante.getLocalidad());
			sto.setString(7, vacante.getDomicilio());
			
			sto.setString(8, vacante.getColonia());
			sto.setString(9, vacante.getZonaEscolar());
			sto.setString(10, vacante.getSubDirRegional());
			sto.setString(11, vacante.getHoras());
			sto.setString(12, vacante.getCvePresupuestal());
				
			sto.setString(13, vacante.getFechaInicio());
			sto.setString(14, vacante.getFechaTermino());
			
			sto.setString(15, vacante.getIdSubsistema());
			sto.setString(16, vacante.getTpoVacante());
			sto.setString(17, vacante.getMotivoVacante());
			sto.setString(18, vacante.getPzajornada());
			sto.setString(19, vacante.getCveAsignatura());
			sto.setString(20, vacante.getIdFuncion());
			sto.setString(21, usuario);			
			
			sto.registerOutParameter(22, 12);
			sto.registerOutParameter(23, 12);
			
			sto.execute();
			
			String cveError = sto.getString(22);
			String descError = sto.getString(23);
			
			sto.close();
			cnn.desconectar();
			
			resultado = cveError + "|" + descError;

		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método registrarVacante() de VacanteDaoImpl:\n" + sqle.getMessage(), sqle);
		}finally {
            if(sto != null){
            	sto.close();
            }
            if(cnn != null){
                cnn.desconectar();
            }
        }
		
		return resultado;
	}

}
