package mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.impl;

//import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.ArchivoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.CifraDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.HistoricoEstatusDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.RechazoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacionms.dao.AspiranteMSDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public class AspiranteMSDaoImpl implements AspiranteMSDAO {

	static final String _MONGO_BASE = "test";

	@Override
	public List<AspiranteDTO> getAspirantes(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		List<AspiranteDTO> lstAspirante = new ArrayList<AspiranteDTO>();
		
		String sSql = "SELECT OPO.CVESUBSIST, OPO.DES_SUBSISTEMA, OPO.ID_CONVOCATORIA, OPO.FOLIOFEDER, OPO.CURP_A, OPO.NOMBRE_A, OPO.ASIGNATURA, OPO.ASIASOCEST, "
					+ "OPO.TEX CVEASIGNATURA, OPO.GPO_DESEMP, OPO.PRELAC, OPO.STATUS, OPO.CVEOPOUSU, OPO.ID_FUNCION, FUN.DESC_ITEM DESC_FUNCION, "
					+ "DECODE(TO_CHAR(OPO.STATUS), '7', STA.DESC_ITEM || ' ' || CONV.DESC_ITEM,  STA.DESC_ITEM) DESC_STATUS, "
					+ "PKGSIAPRODEM.F_GET_BND_ASIGNAR_MS(OPO.FOLIOFEDER, OPO.ID_CONVOCATORIA, OPO.ID_FUNCION) BND_ASIGNAR, "
					+ "PKGSIAPRODEM.F_GET_BND_REGSISFED_MS(OPO.FOLIOFEDER, OPO.ID_CONVOCATORIA, OPO.ID_FUNCION) BND_REGSISFED, "
					+ "CONV.DESC_ITEM DESC_CONV "
					+ "FROM TBLOPOSI_MS OPO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOSTAT' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') STA, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMSFUNASP' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') FUN, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMSCONVO' AND ID_ITEM <> 0) CONV "
					+ "WHERE OPO.STATUS = STA.ID_ITEM (+) "
					+ "AND OPO.ID_FUNCION = FUN.ID_ITEM (+) "
					+ "AND OPO.ID_CONVOCATORIA = CONV.ID_ITEM (+) "
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

				if (clave.equals("INGRESO")) {
					sSql += " OPO.ID_FUNCION = 1 ";
				}
	
				if (clave.equals("PROMOCION")) {
					sSql += " OPO.ID_FUNCION <> 1 ";
				}

				if (clave.equals("ID_FUNCION")) {
					sSql += " OPO.ID_FUNCION = " + valor.trim();
				}
				
				if (clave.equals("ID_CONVOCATORIA")) {
					sSql += " OPO.ID_CONVOCATORIA = " + valor.trim();
				}

				if (clave.equals("ID_SUBSISTEMA")) {
					sSql += " OPO.CVESUBSIST = " + valor.trim();
				}

				if (clave.equals("CVEASIGNATURA")) {
					sSql += " OPO.TEX = " + valor.trim();
				}

				if (clave.equals("STATUS")) {
					sSql += " OPO.STATUS = " + valor.trim();
				}

				if (clave.equals("FOLIO")) {
					sSql += " OPO.FOLIOFEDER = " + valor.trim();
				}

				if (clave.equals("CURP")) {
					sSql += " UPPER(OPO.CURP_A) = '" + valor.trim().toUpperCase() + "'";
				}

				if (clave.equals("NOMBRE")) {
					sSql += " UPPER(OPO.NOMBRE_A) LIKE '%" + valor.trim().toUpperCase() + "%'";
				}

				if (clave.equals("GPO_DESEMP")) {
					sSql += " OPO.GPO_DESEMP = '" + valor.trim() + "'";
				}
				


				ctdFiltro++;
			}
		}
		
		sSql = sSql + " ORDER BY OPO.ID_CONVOCATORIA, OPO.CVESUBSIST, OPO.TEX, OPO.GPO_DESEMP, OPO.PRELAC ";

		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				AspiranteDTO aspiranteDTO = new AspiranteDTO();

				aspiranteDTO.setSubsistema(rs.getString("CVESUBSIST"));
				aspiranteDTO.setDescSubsistema(rs.getString("DES_SUBSISTEMA"));
				aspiranteDTO.setIdConvocatoria(rs.getString("ID_CONVOCATORIA"));
				aspiranteDTO.setDescConvocatoria(rs.getString("DESC_CONV"));
				aspiranteDTO.setFolioFederal(rs.getString("FOLIOFEDER"));
				aspiranteDTO.setCurp(rs.getString("CURP_A"));
				aspiranteDTO.setNombre(rs.getString("NOMBRE_A"));
				aspiranteDTO.setPlaza(rs.getString("ASIGNATURA"));
				aspiranteDTO.setNomTexn(rs.getString("ASIASOCEST"));
				aspiranteDTO.setCveAsignatura(rs.getString("CVEASIGNATURA"));
				aspiranteDTO.setGpoDesmo(rs.getString("GPO_DESEMP"));
				aspiranteDTO.setPrelac(rs.getString("PRELAC"));
				aspiranteDTO.setStatus(rs.getString("STATUS"));
				aspiranteDTO.setCveOpoUsu(rs.getString("CVEOPOUSU"));
				aspiranteDTO.setDescStatus(rs.getString("DESC_STATUS"));
				aspiranteDTO.setBndAsignar(rs.getString("BND_ASIGNAR"));
				aspiranteDTO.setIdFuncion(rs.getString("ID_FUNCION"));
				aspiranteDTO.setFuncion(rs.getString("DESC_FUNCION"));
				aspiranteDTO.setBndRegSisFed(rs.getString("BND_REGSISFED"));
				
				lstAspirante.add(aspiranteDTO);
			}

			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getAspirantes() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}

		return lstAspirante;
	}

	@Override
	public List<AspiranteDTO> getDetalleAspirantes(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		List<AspiranteDTO> lstAspirante = new ArrayList<AspiranteDTO>();
		
		String sSql = "SELECT OPO.CVESUBSIST, OPO.DES_SUBSISTEMA, OPO.ID_CONVOCATORIA, OPO.FOLIOFEDER, OPO.CURP_A, OPO.NOMBRE_A, OPO.ASIGNATURA, OPO.ASIASOCEST, "
					+ "OPO.TEX CVEASIGNATURA, OPO.GPO_DESEMP, OPO.PRELAC, OPO.STATUS, OPO.CVEOPOUSU, STA.DESC_ITEM DESC_STATUS, OPO.ID_FUNCION, FUN.DESC_ITEM DESC_FUNCION, "
					+ "VAC.ID, VAC.TIPOFUNCION, VAC.TIPOVACANTE, VAC.CVEASIGNATURA CVEASIGNATURA_VAC, VAC.ASIGNATURA, VAC.PZAJORNADA, VAC.NUMHORAS, "
					+ "NVL(VAC.CCT, 'N/A') CCT, NVL(CCT.NOMBRE, 'N/A') CCTNOMBRE, CCT.CVEMUN, MPO.DESC_ITEM MUNICIPIO, CCT.LOCALIDAD, CCT.DOMICILIO, CCT.TURNO, "
					+ "CCT.SUBSISTEMA, CCT.ZONAESCOLAR, CCT.SUBDIRREG, "
					+ "VAC.FECINICIO, DECODE(VAC.FECTERMINO, '31/12/2100', 'DEFINITIVO', NULL, 'N/A', VAC.FECTERMINO) FECTERMINO,  "
					+ "NVL(VAC.FOLIO, 0) FOLIO, VAC.ID_FUNCION ID_FUNCION_VAC, "
					+ "TO_CHAR(VAC.FECASIGNACION, 'DD/MM/YYYY') FECASIGNACION, VAC.ID_CONVOCATORIA ID_CONVOCATORIA_VAC, "
					+ "VAC.TIPO_NOMBRAMIENTO MOTIVO_VACANTE, NVL(VAC.CVE_PRESUPUESTAL, 'N/A') CVE_PRESUPUESTAL, VAC.CVEOPOUSU, "
					+ "TPO.DESC_ITEM DESC_TPO_VAC, SUB.DESC_ITEM DESC_SUBSISTEMA, "
					+ "REC.ID_MOTIVO, MVO.DESC_ITEM DESC_MOTIVO_RECHAZO, TO_CHAR(REC.FEC_RECHAZO, 'DD/MM/YYYY') FEC_RECHAZO, "
					+ "DECODE(OPO.STATUS, 3, NVL(REC.OBSERVACION, 'SIN OBSERVACION'), 'N/A') OBSERVACION "
					+ " "
					+ "FROM TBLOPOSI_MS OPO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOSTAT' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') STA, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMSFUNASP' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') FUN, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMSSUBSIS' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') SUB, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOVACA' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') TPO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMTVRECH' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') MVO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMPO' AND ID_ITEM <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') MPO, "
					+ "TBPZSVAC_MS VAC, TBLCCT CCT, TBLMVORECHAZO_MS REC "
					+ "WHERE OPO.STATUS = STA.ID_ITEM (+) "
					+ "AND OPO.ID_FUNCION = FUN.ID_ITEM (+) "
					+ "AND OPO.FOLIOFEDER = VAC.FOLIO (+) "
					+ "AND OPO.ID_CONVOCATORIA = VAC.ID_CONVOCATORIA (+) "
					+ "AND OPO.ID_FUNCION = VAC.ID_FUNCION (+) "
					+ "AND VAC.CCT = CCT.CCT (+) "
					+ "AND CCT.CVEMUN = MPO.ID_ITEM (+) "
					+ "AND OPO.CVESUBSIST = SUB.ID_ITEM (+) "
					+ "AND VAC.PZAJORNADA = TPO.ID_ITEM (+) "
					+ "AND OPO.FOLIOFEDER = REC.FOLIOFEDER (+) "
					+ "AND OPO.ID_FUNCION = REC.ID_FUNCION (+) "
					+ "AND OPO.ID_CONVOCATORIA = REC.ID_CONVOCATORIA (+) "
					+ "AND REC.ID_MOTIVO = MVO.ID_ITEM (+) ";
					
		
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

				if (clave.equals("INGRESO")) {
					sSql += " OPO.ID_FUNCION = 1 ";
				}
	
				if (clave.equals("PROMOCION")) {
					sSql += " OPO.ID_FUNCION <> 1 ";
				}

				if (clave.equals("ID_FUNCION")) {
					sSql += " OPO.ID_FUNCION = " + valor.trim();
				}
				
				if (clave.equals("ID_CONVOCATORIA")) {
					sSql += " OPO.ID_CONVOCATORIA = " + valor.trim();
				}

				if (clave.equals("ID_SUBSISTEMA")) {
					sSql += " OPO.CVESUBSIST = " + valor.trim();
				}

				if (clave.equals("CVEASIGNATURA")) {
					sSql += " OPO.TEX = " + valor.trim();
				}

				if (clave.equals("STATUS")) {
					sSql += " OPO.STATUS = " + valor.trim();
				}

				if (clave.equals("FOLIO")) {
					sSql += " OPO.FOLIOFEDER = " + valor.trim();
				}

				if (clave.equals("CURP")) {
					sSql += " UPPER(OPO.CURP_A) = '" + valor.trim().toUpperCase() + "'";
				}

				if (clave.equals("NOMBRE")) {
					sSql += " UPPER(OPO.NOMBRE_A) LIKE '%" + valor.trim().toUpperCase() + "%'";
				}

				if (clave.equals("GPO_DESEMP")) {
					sSql += " OPO.GPO_DESEMP = '" + valor.trim() + "'";
				}

				if (clave.equals("CCT")) {
					sSql += " VAC.CCT = '" + valor.trim().toUpperCase() + "'";
				}
				
				if (clave.equals("FECASIGNACION_INICIAL")) {
					sSql += " TRUNC(VAC.FECASIGNACION) >= TRUNC(TO_DATE('" + valor.trim().toUpperCase() + "', 'DD/MM/YYYY'))";
				}
	
				if (clave.equals("FECASIGNACION_FINAL")) {
					sSql += " TRUNC(VAC.FECASIGNACION) <= TRUNC(TO_DATE('" + valor.trim().toUpperCase() + "', 'DD/MM/YYYY'))";
				}
				
				if (clave.equals("MOTIVO_RECHAZO")) {
					sSql += " REC.ID_MOTIVO = " + valor.trim();
				}
				
				if (clave.equals("FECRECHAZO_INICIAL")) {
					sSql += " TRUNC(REC.FEC_RECHAZO) >= TRUNC(TO_DATE('" + valor.trim().toUpperCase() + "', 'DD/MM/YYYY'))";
				}
	
				if (clave.equals("FECRECHAZO_FINAL")) {
					sSql += " TRUNC(REC.FEC_RECHAZO) <= TRUNC(TO_DATE('" + valor.trim().toUpperCase() + "', 'DD/MM/YYYY'))";
				}

				ctdFiltro++;
			}
		}
		
		sSql = sSql + " ORDER BY OPO.ID_CONVOCATORIA, OPO.CVESUBSIST, OPO.TEX, OPO.GPO_DESEMP, OPO.PRELAC ";

		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				AspiranteDTO aspiranteDTO = new AspiranteDTO();
				VacanteDTO vacante = new VacanteDTO();
				RechazoDTO rechazo = new RechazoDTO();

				aspiranteDTO.setSubsistema(rs.getString("CVESUBSIST"));
				aspiranteDTO.setDescSubsistema(rs.getString("DES_SUBSISTEMA"));
				aspiranteDTO.setIdConvocatoria(rs.getString("ID_CONVOCATORIA"));
				aspiranteDTO.setFolioFederal(rs.getString("FOLIOFEDER"));
				aspiranteDTO.setCurp(rs.getString("CURP_A"));
				aspiranteDTO.setNombre(rs.getString("NOMBRE_A"));
				aspiranteDTO.setPlaza(rs.getString("ASIGNATURA"));
				aspiranteDTO.setNomTexn(rs.getString("ASIASOCEST"));
				aspiranteDTO.setCveAsignatura(rs.getString("CVEASIGNATURA"));
				aspiranteDTO.setGpoDesmo(rs.getString("GPO_DESEMP"));
				aspiranteDTO.setPrelac(rs.getString("PRELAC"));
				aspiranteDTO.setStatus(rs.getString("STATUS"));
				aspiranteDTO.setCveOpoUsu(rs.getString("CVEOPOUSU"));
				aspiranteDTO.setDescStatus(rs.getString("DESC_STATUS"));
				
				aspiranteDTO.setIdFuncion(rs.getString("ID_FUNCION"));
				aspiranteDTO.setFuncion(rs.getString("DESC_FUNCION"));
				
				//Datos de la Vacante Asignada, en su caso.
				vacante.setCve(rs.getString("ID"));
				vacante.setFuncion(rs.getString("TIPOFUNCION"));
				vacante.setTpoVacante(rs.getString("TIPOVACANTE"));
				vacante.setCveAsignatura(rs.getString("CVEASIGNATURA_VAC"));
				vacante.setAsginatura(rs.getString("ASIGNATURA"));
				vacante.setPzajornada(rs.getString("PZAJORNADA"));
				vacante.setHoras(rs.getString("NUMHORAS"));
				vacante.setCmpCCT(rs.getString("CCT"));
				vacante.setFolio(rs.getString("FOLIO"));
				vacante.setNomCCT(rs.getString("CCTNOMBRE"));
				vacante.setMunicipio(rs.getString("MUNICIPIO"));
				vacante.setDomicilio(rs.getString("DOMICILIO"));
				vacante.setLocalidad(rs.getString("LOCALIDAD"));
				vacante.setTurno(rs.getString("TURNO"));
				vacante.setFechaInicio(rs.getString("FECINICIO"));
				vacante.setFechaTermino(rs.getString("FECTERMINO"));
				vacante.setDescTpoVacante(rs.getString("DESC_TPO_VAC"));
				vacante.setIdSubsistema(rs.getString("SUBSISTEMA"));
				vacante.setDescSubsistema(rs.getString("DESC_SUBSISTEMA"));				
				vacante.setFecAsignacion(rs.getString("FECASIGNACION"));
				vacante.setIdConvocatoria(rs.getString("ID_CONVOCATORIA_VAC"));
				vacante.setMotivoVacante(rs.getString("MOTIVO_VACANTE"));
				vacante.setCvePresupuestal(rs.getString("CVE_PRESUPUESTAL"));
				vacante.setUsuarioAsignacion(rs.getString("CVEOPOUSU"));
				vacante.setCveMun(rs.getString("CVEMUN"));
				vacante.setZonaEscolar(rs.getString("ZONAESCOLAR"));
				vacante.setSubDirRegional(rs.getString("SUBDIRREG"));
				vacante.setIdFuncion(rs.getString("ID_FUNCION_VAC"));
				
				aspiranteDTO.setVacanteAsignada(vacante);
				
				//Datos del rechazo, en su caso.
				rechazo.setFolioFeder(rs.getString("FOLIOFEDER"));
				rechazo.setIdConvocatoria(rs.getString("ID_CONVOCATORIA"));
				rechazo.setIdFuncion(rs.getString("ID_FUNCION"));
				rechazo.setIdMotivo(rs.getString("ID_MOTIVO"));
				rechazo.setDescMotivo(rs.getString("DESC_MOTIVO_RECHAZO"));
				rechazo.setFecRechazo(rs.getString("FEC_RECHAZO"));
				rechazo.setObservacion(rs.getString("OBSERVACION"));
				
				aspiranteDTO.setMotivoRechazo(rechazo);
				
				lstAspirante.add(aspiranteDTO);
			}

			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getDetalleAspirantes() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}

		return lstAspirante;
	}

	@Override
	public List<HistoricoEstatusDTO> getHistoricoEstatus(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		List<HistoricoEstatusDTO> lstHistoricoEstatus = new ArrayList<HistoricoEstatusDTO>();
		
		String sSql = "SELECT HST.ID_HISTORICO, HST.FOLIOFEDER, HST.ID_CONVOCATORIA, HST.ID_FUNCION,  "
					+ "HST.STATUS, HST.ID_TBPZSVAC_MS, HST.ID_MOTIVO_RECHAZO, HST.OBSERVACION, "
					+ "TO_CHAR(HST.FEC_STATUS, 'DD/MM/YYYY HH24:MI:SS') FEC_STATUS, HST.USU_STATUS, STA.DESC_ITEM DESC_STATUS, "
					+ "DECODE(HST.STATUS, 2, VAC.CCT, 3, MVO.DESC_ITEM, 'N/A') DETALLE "
					+ "FROM HISTORICO_TBLOPOSI_MS HST, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOSTAT' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') STA, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMTVRECH' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') MVO, "
					+ "TBPZSVAC_MS VAC "
					+ "WHERE HST.STATUS = STA.ID_ITEM (+) "
					+ "AND HST.ID_MOTIVO_RECHAZO = MVO.ID_ITEM (+) "
					+ "AND HST.ID_TBPZSVAC_MS = VAC.ID (+) ";
					
	
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
	
				if (clave.equals("INGRESO")) {
					sSql += " HST.ID_FUNCION = 1 ";
				}
	
				if (clave.equals("PROMOCION")) {
					sSql += " HST.ID_FUNCION <> 1 ";
				}
	
				if (clave.equals("ID_FUNCION")) {
					sSql += " HST.ID_FUNCION = " + valor.trim();
				}
				
				if (clave.equals("ID_CONVOCATORIA")) {
					sSql += " HST.ID_CONVOCATORIA = " + valor.trim();
				}
			
				if (clave.equals("FOLIO")) {
					sSql += " HST.FOLIOFEDER = " + valor.trim();
				}
	
				ctdFiltro++;
			}
		}
		
		sSql = sSql + " ORDER BY HST.FOLIOFEDER, HST.ID_CONVOCATORIA, HST.ID_FUNCION, HST.ID_HISTORICO ";
	
		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();
	
			while(rs.next()) {
				HistoricoEstatusDTO historicoDTO = new HistoricoEstatusDTO();

				historicoDTO.setIdHistorico(rs.getString("ID_HISTORICO"));
				historicoDTO.setFolioFederal(rs.getString("FOLIOFEDER"));
				historicoDTO.setIdConvocatoria(rs.getString("ID_CONVOCATORIA"));
				historicoDTO.setIdFuncion(rs.getString("ID_FUNCION"));
				historicoDTO.setIdEstatus(rs.getString("STATUS"));
				historicoDTO.setDescEstatus(rs.getString("DESC_STATUS"));
				historicoDTO.setDetalleEstatus(rs.getString("DETALLE"));
				historicoDTO.setObservacion(rs.getString("OBSERVACION"));
				historicoDTO.setFechaEstatus(rs.getString("FEC_STATUS"));
				historicoDTO.setUsuarioEstatus(rs.getString("USU_STATUS"));
	
				lstHistoricoEstatus.add(historicoDTO);
			}
	
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getHistoricoEstatus() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}		
		
		return lstHistoricoEstatus;
	}
	
	/*
	@Override
	public ArchivoDTO getNombramiento(String folioFeder, String idConvocatoria, String idFuncion, String idVacante) throws DBExcepcion, SistemaExcepcion {
		ArchivoDTO nombramiento = null;
		
		String sSql = "SELECT *" 
					+ " FROM TBLNOMBRAMIENTO_MS"
					+ " WHERE FOLIOFEDER = " + folioFeder
					+ " AND ID_CONVOCATORIA = " + idConvocatoria
					+ " AND ID_FUNCION = " + idFuncion
					+ " AND ID_VACANTE = " + idVacante
					+ " AND B_BAJA = 'F'";
		
		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();

			
			if (rs.next()) {
				nombramiento = new ArchivoDTO();
				nombramiento.setFolioFeder(rs.getString("FOLIOFEDER"));
				nombramiento.setIdConvocatoria(rs.getString("ID_CONVOCATORIA"));
				nombramiento.setIdVacante(rs.getString("ID_VACANTE"));
				nombramiento.setNombreArchivo(rs.getString("NOMBRE_ARCHIVO"));
				Blob blobArchivo = rs.getBlob("ARCHIVO");
				nombramiento.setArhivo(blobArchivo.getBytes(1, (int) blobArchivo.length()));
				nombramiento.setFecAlta(rs.getString("FECHA_ALTA"));
				nombramiento.setUsuAlta(rs.getString("USUARIO_ALTA"));
				nombramiento.setIdFuncion(rs.getString("ID_FUNCION"));
			}

			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		
		return nombramiento;
	}
	*/
	
	@Override
	public ArchivoDTO getNombramiento(String folioFeder, String idConvocatoria, String idFuncion, String idVacante) throws DBExcepcion, SistemaExcepcion {
		ArchivoDTO nombramiento = null;
		
		String sSql = "SELECT *" 
					+ " FROM TBLNOMBRAMIENTO_MS"
					+ " WHERE FOLIOFEDER = " + folioFeder
					+ " AND ID_CONVOCATORIA = " + idConvocatoria
					+ " AND ID_FUNCION = " + idFuncion
					+ " AND ID_VACANTE = " + idVacante
					+ " AND B_BAJA = 'F'";
		
		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();

			
			if (rs.next()) {
				nombramiento = new ArchivoDTO();
				nombramiento.setFolioFeder(rs.getString("FOLIOFEDER"));
				nombramiento.setIdConvocatoria(rs.getString("ID_CONVOCATORIA"));
				nombramiento.setNombreArchivo(rs.getString("NOMBRE_ARCHIVO"));
				//Blob blobArchivo = rs.getBlob("ARCHIVO");
				//nombramiento.setArhivo(blobArchivo.getBytes(1, (int) blobArchivo.length()));
				nombramiento.setFecAlta(rs.getString("FECHA_ALTA"));
				nombramiento.setUsuAlta(rs.getString("USUARIO_ALTA"));
				nombramiento.setIdVacante(rs.getString("ID_VACANTE"));
				nombramiento.setIdFuncion(rs.getString("ID_FUNCION"));
				nombramiento.setSecuencia(rs.getString("SECUENCIA"));
				nombramiento.setColeccionMongo(rs.getString("COLECCION_MONGO"));
				nombramiento.setLlaveMongo(rs.getString("LLAVE_MONGO"));
				
				if (nombramiento.coleccionMongo != null && !nombramiento.coleccionMongo.equals("")
						&& nombramiento.llaveMongo != null && !nombramiento.llaveMongo.equals("")) {
					/*
					DesEncrypterDOCS encrypter = new DesEncrypterDOCS(_MONGO_KEY);
					
					String nombre = encrypter.encrypt(nombramiento.getLlaveMongo());
					String coleccion = encrypter.encrypt(nombramiento.getColeccionMongo());
					String base = encrypter.encrypt(_MONGO_BASE);
					*/

					String nombre = nombramiento.getLlaveMongo();
					String coleccion = nombramiento.getColeccionMongo();
					String base = _MONGO_BASE;

					nombramiento.setUrl("http://siase2.edomex.gob.mx/DespachadorArchivos/verArchivo?param="+ nombre +"&param1="+ coleccion +"&param2="+base);
				          
				} else {
					throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteMSDaoImpl." +
							"\n[El documento ya existe pero no ha sido almacenado en el repositorio.]" +
							"\nNotifica al Administrador del Sistema.");
				}
			}

			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return nombramiento;
	}
	

	/*
	@Override
	public String almacenarNombramiento(ArchivoDTO archivoDTO) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "INSERT INTO TBLNOMBRAMIENTO_MS"
					+ " (ID_DOCUMENTO, FOLIOFEDER, ID_CONVOCATORIA, ID_VACANTE, NOMBRE_ARCHIVO, ARCHIVO, FECHA_ALTA, USUARIO_ALTA, B_BAJA, ID_FUNCION, SECUENCIA)"
					+ " VALUES ((SELECT NVL(MAX(ID_DOCUMENTO),0)+1 FROM TBLNOMBRAMIENTO_MS),?,?,?,?,?,SYSDATE,?,'F',?,?)";
		
		try {
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			cnn.getConexion().setAutoCommit(false);
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			
			ps.setString(1, archivoDTO.getFolioFeder());
			ps.setString(2, archivoDTO.getIdConvocatoria());
			ps.setString(3, archivoDTO.getIdVacante());
			ps.setString(4, archivoDTO.getNombreArchivo());
			ps.setBytes(5, archivoDTO.getArhivo());
			ps.setString(6, archivoDTO.getUsuAlta());
			ps.setString(7, archivoDTO.getIdFuncion());
			ps.setString(8, archivoDTO.getSecuencia());
			
			int resIns = ps.executeUpdate();
			
			if(resIns > 0){
				resultado = "00";
				cnn.getConexion().commit();
			}

			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	*/
	
	@Override
	public String almacenarNombramiento(ArchivoDTO archivoDTO) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		
		try {
			Date fecha = new Date();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy");
			
			String respuestaWS = "";
			String llaveMongo = "";
			String coleccionMongo = "SIAPRODEM_NOMBRAMIENTOS_MS_" + formatoFecha.format(fecha).toUpperCase();

			/*
			SalvarArchivoMongoService servicioMongo = new SalvarArchivoMongoService();
			SalvarArchivoMongo salvarArchivoMongo = servicioMongo.getSalvarArchivoMongoPort();
			respuestaWS = salvarArchivoMongo.salvarArchivo(archivoDTO.getArhivo(), "pdf", coleccionMongo);
			*/
			
			respuestaWS = "FAIL";
			
			if (respuestaWS.startsWith("OK")) {
				//Guardado en MongoDB
				llaveMongo = respuestaWS.substring(3);
				

				String sSql = "INSERT INTO TBLNOMBRAMIENTO_MS"
						+ " (ID_DOCUMENTO, FOLIOFEDER, ID_CONVOCATORIA, NOMBRE_ARCHIVO, ARCHIVO, FECHA_ALTA, USUARIO_ALTA, B_BAJA, ID_VACANTE, ID_FUNCION, SECUENCIA, LLAVE_MONGO, COLECCION_MONGO)"
						+ " VALUES ((SELECT NVL(MAX(ID_DOCUMENTO),0)+1 FROM TBLNOMBRAMIENTO_MS),?,?,?,?,SYSDATE,?,'F', ?, ?, ?, ?, ?)";
				
				Conexion cnn = new Conexion();
				cnn.conectar();
				cnn.getConexion().setAutoCommit(false);
				
				PreparedStatement ps = cnn.prepareStatement(sSql);
				
				ps.setString(1, archivoDTO.getFolioFeder());
				ps.setString(2, archivoDTO.getIdConvocatoria());
				ps.setString(3, archivoDTO.getNombreArchivo());
				//ps.setBytes(4, archivoDTO.getArhivo());
				ps.setString(4, "");
				ps.setString(5, archivoDTO.getUsuAlta());
				ps.setString(6, archivoDTO.getIdVacante());
				ps.setString(7, archivoDTO.getIdFuncion());
				ps.setString(8, archivoDTO.getSecuencia());
				ps.setString(9, llaveMongo);
				ps.setString(10, coleccionMongo);
				
				int resIns = ps.executeUpdate();
				
				if(resIns > 0){
					resultado = "00";
					cnn.getConexion().commit();
				}

				ps.close();
				cnn.desconectar();
				
			} else {
				//Tratar de guardar en NFS
				resultado = "01|Ocurrió un error en el guardado del archivo en MongoDB. " + respuestaWS;
				
			}
						
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	

	@Override
	public String asignarVacante(String folioFederal, String idConvocatoria, String idFuncion, String idVacante, String subsistema, String usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN PKGSIAPRODEM.P_ASIGNAR_VACANTE_MS(?,?,?,?,?,?,?,?); END;";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			CallableStatement sto = cnn.prepareCall(sSql);

			sto.setString(1, folioFederal);
			sto.setString(2, idConvocatoria);
			sto.setString(3, idFuncion);
			sto.setString(4, idVacante);
			sto.setString(5, subsistema);
			sto.setString(6, usuario);
						
			sto.registerOutParameter(7, 12);
			sto.registerOutParameter(8, 12);
			
			sto.execute();
			
			String cveError = sto.getString(7);
			String descError = sto.getString(8);
			
			sto.close();
			cnn.desconectar();
			
			resultado = cveError + "|" + descError;

		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método asignarVacante() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	
	@Override
	public String registrarRenuncia(String folioFederal, String idConvocatoria, String idFuncion, String idMotivo, String observacion, String usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN PKGSIAPRODEM.P_REGISTRAR_RECHAZO_MS(?,?,?,?,?,?,?,?); END;";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			CallableStatement sto = cnn.prepareCall(sSql);

			sto.setString(1, folioFederal);
			sto.setString(2, idConvocatoria);
			sto.setString(3, idFuncion);
			sto.setString(4, idMotivo);
			sto.setString(5, observacion.trim().toUpperCase());
			sto.setString(6, usuario);
						
			sto.registerOutParameter(7, 12);
			sto.registerOutParameter(8, 12);
			
			sto.execute();
			
			String cveError = sto.getString(7);
			String descError = sto.getString(8);
			
			sto.close();
			cnn.desconectar();
			
			resultado = cveError + "|" + descError;

		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método registrarRenuncia() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}

	@Override
	public String ejecutarReversaStatus(String folioFederal,  String idConvocatoria, String idFuncion, String observacionReversa, String usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN PKGSIAPRODEM.P_REVERSA_STATUS_MS(?,?,?,?,?,?,?); END;";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			CallableStatement sto = cnn.prepareCall(sSql);

			sto.setString(1, folioFederal);
			sto.setString(2, idConvocatoria);
			sto.setString(3, idFuncion);
			sto.setString(4, observacionReversa);
			sto.setString(5, usuario);
						
			sto.registerOutParameter(6, 12);
			sto.registerOutParameter(7, 12);
			
			sto.execute();
			
			String cveError = sto.getString(6);
			String descError = sto.getString(7);
			
			sto.close();
			cnn.desconectar();
			
			resultado = cveError + "|" + descError;

		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método ejecutarReversaStatus() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;

	}

	@Override
	public String getIdMotivoRechazoAspirante(String folioFederal, String idConvocatoria, String idFuncion) throws DBExcepcion, SistemaExcepcion {
		String idMotivoRechazo = null;

		String sSql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			sSql = "SELECT ID_MOTIVO FROM TBLMVORECHAZO_MS WHERE FOLIOFEDER = " + folioFederal + " AND ID_CONVOCATORIA = " + idConvocatoria + " AND ID_FUNCION = " + idFuncion;
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				idMotivoRechazo = rs.getString("ID_MOTIVO");
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getIdMotivoRechazoAspirante de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}

		return idMotivoRechazo;
	}

	@Override
	public String desasignarVacante(String folioFederal, String idConvocatoria, String idFuncion, String idVacante, String subsistema, String usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN PKGSIAPRODEM.P_DESASIGNAR_VACANTE_MS(?,?,?,?,?,?,?,?); END;";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			CallableStatement sto = cnn.prepareCall(sSql);

			sto.setString(1, folioFederal);
			sto.setString(2, idConvocatoria);
			sto.setString(3, idFuncion);
			sto.setString(4, idVacante);
			sto.setString(5, subsistema);
			sto.setString(6, usuario);
						
			sto.registerOutParameter(7, 12);
			sto.registerOutParameter(8, 12);
			
			sto.execute();
			
			String cveError = sto.getString(7);
			String descError = sto.getString(8);
			
			sto.close();
			cnn.desconectar();
			
			resultado = cveError + "|" + descError;

		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método desasignarVacante() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}

	@Override
	public List<CifraDTO> getCifrasAsignacion(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		// TODO Auto-generated method stub
		List<CifraDTO> lstCifraAsignacion = new ArrayList<CifraDTO>();

		String sSql = "SELECT OPO.ID_FUNCION, FUN.DESC_ITEM FUNCION, OPO.TEX, ASG.DESC_ITEM ASIGNATURA, "
					+ "SUM(DECODE(CCT.SUBSISTEMA, 1013, 1, 0)) CECYTEM, "
					+ "SUM(DECODE(CCT.SUBSISTEMA, 1014, 1, 0)) COBAEM, "
					+ "SUM(DECODE(CCT.SUBSISTEMA, 1069, 1, 1134, 1, 1069, 1, 0)) BG, "
					+ "SUM(DECODE(CCT.SUBSISTEMA, 1068, 1, 1167, 1, 1068, 1, 0)) BT, "
					+ "SUM(DECODE(CCT.SUBSISTEMA, 1185, 1, 0)) TB, "
					+ "COUNT(*) TOTAL "
					+ "FROM TBLOPOSI_MS OPO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMSASIGN' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') ASG, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMSFUNASP' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') FUN, "
					+ "TBPZSVAC_MS VAC, TBLCCT CCT "
					+ "WHERE OPO.FOLIOFEDER = VAC.FOLIO (+) "
					+ "AND OPO.ID_CONVOCATORIA = VAC.ID_CONVOCATORIA (+) "
					+ "AND OPO.ID_FUNCION = VAC.ID_FUNCION (+) "
					+ "AND VAC.CCT = CCT.CCT (+) "
					+ "AND OPO.TEX = ASG.ID_ITEM (+) "
					+ "AND OPO.ID_FUNCION = FUN.ID_ITEM (+) "
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
	
				if (clave.equals("INGRESO")) {
					sSql += " OPO.ID_FUNCION = 1 ";
				}
	
				if (clave.equals("PROMOCION")) {
					sSql += " OPO.ID_FUNCION <> 1 ";
				}
	
				if (clave.equals("ID_FUNCION")) {
					sSql += " OPO.ID_FUNCION = " + valor.trim();
				}
	
				if (clave.equals("ID_CONVOCATORIA")) {
					sSql += " OPO.ID_CONVOCATORIA = " + valor.trim();
				}
	
				if (clave.equals("CVEASIGNATURA")) {
					sSql += " OPO.TEX = " + valor.trim();
				}
	
				if (clave.equals("STATUS")) {
					sSql += " OPO.STATUS = " + valor.trim();
				}
	
				if (clave.equals("ID_SUBSISTEMA")) {
					sSql += " OPO.CVESUBSIST = " + valor.trim();
				}
				if (clave.equals("CCT")) {
					sSql += " VAC.CCT = '" + valor.trim().toUpperCase() + "'";
				}
				
				if (clave.equals("FECASIGNACION_INICIAL")) {
					sSql += " TRUNC(VAC.FECASIGNACION) >= TRUNC(TO_DATE('" + valor.trim().toUpperCase() + "', 'DD/MM/YYYY'))";
				}
	
				if (clave.equals("FECASIGNACION_FINAL")) {
					sSql += " TRUNC(VAC.FECASIGNACION) <= TRUNC(TO_DATE('" + valor.trim().toUpperCase() + "', 'DD/MM/YYYY'))";
				}
	
				ctdFiltro++;
			}
		}
		
		sSql = sSql + " GROUP BY OPO.ID_FUNCION, FUN.DESC_ITEM, OPO.TEX, ASG.DESC_ITEM "
					+ " ORDER BY OPO.ID_FUNCION, FUN.DESC_ITEM, OPO.TEX, ASG.DESC_ITEM ";
	
		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();
	
			while(rs.next()) {
				CifraDTO cifraDTO = new CifraDTO();
				
				cifraDTO.setIdFuncion(rs.getString("ID_FUNCION"));
				cifraDTO.setFuncion(rs.getString("FUNCION"));
				cifraDTO.setCveAsignatura(rs.getString("TEX"));
				cifraDTO.setAsignatura(rs.getString("ASIGNATURA"));
				cifraDTO.setTotCecytem(rs.getString("CECYTEM"));
				cifraDTO.setTotCobaem(rs.getString("COBAEM"));
				cifraDTO.setTotBachGen(rs.getString("BG"));
				cifraDTO.setTotBachTec(rs.getString("BT"));
				cifraDTO.setTotTelebach(rs.getString("TB"));
				cifraDTO.setTotal(rs.getString("TOTAL"));
				
				lstCifraAsignacion.add(cifraDTO);
			}
	
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getCifrasAsignacion() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return lstCifraAsignacion;
	}

	@Override
	public List<CifraDTO> getCifrasRechazo(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		// TODO Auto-generated method stub
		List<CifraDTO> lstCifraAsignacion = new ArrayList<CifraDTO>();

		String sSql = "SELECT OPO.ID_FUNCION, FUN.DESC_ITEM FUNCION, OPO.TEX, ASG.DESC_ITEM ASIGNATURA, "
					+ "SUM(DECODE(REC.ID_MOTIVO, 2, 1, 0)) RENUNCIA, "
					+ "SUM(DECODE(REC.ID_MOTIVO, 4, 1, 0)) INASISTENCIA, "
					+ "SUM(DECODE(REC.ID_MOTIVO, 9, 1, 0)) DOCUMENTACION, "
					+ "SUM(DECODE(REC.ID_MOTIVO, 14, 1, 0)) NEGATIVA, "
					+ "COUNT(*) TOTAL "
					+ "FROM TBLOPOSI_MS OPO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMSASIGN' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') ASG, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMSFUNASP' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') FUN, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMTVRECH' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') MVO, "
					+ "TBLMVORECHAZO_MS REC "
					+ "WHERE OPO.FOLIOFEDER = REC.FOLIOFEDER (+) "
					+ "AND OPO.ID_CONVOCATORIA = REC.ID_CONVOCATORIA (+) "
					+ "AND OPO.ID_FUNCION = REC.ID_FUNCION (+) "
					+ "AND OPO.TEX = ASG.ID_ITEM (+) "					
					+ "AND OPO.ID_FUNCION = FUN.ID_ITEM (+) "
					+ "AND REC.ID_MOTIVO = MVO.ID_ITEM (+) "
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
	
				if (clave.equals("INGRESO")) {
					sSql += " OPO.ID_FUNCION = 1 ";
				}
	
				if (clave.equals("PROMOCION")) {
					sSql += " OPO.ID_FUNCION <> 1 ";
				}
	
				if (clave.equals("ID_FUNCION")) {
					sSql += " OPO.ID_FUNCION = " + valor.trim();
				}
	
				if (clave.equals("ID_CONVOCATORIA")) {
					sSql += " OPO.ID_CONVOCATORIA = " + valor.trim();
				}
	
				if (clave.equals("CVEASIGNATURA")) {
					sSql += " OPO.TEX = " + valor.trim();
				}
	
				if (clave.equals("STATUS")) {
					sSql += " OPO.STATUS = " + valor.trim();
				}
	
				if (clave.equals("ID_SUBSISTEMA")) {
					sSql += " OPO.CVESUBSIST = " + valor.trim();
				}
				
				if (clave.equals("MOTIVO_RECHAZO")) {
					sSql += " REC.ID_MOTIVO = " + valor.trim();
				}
				
				if (clave.equals("FECRECHAZO_INICIAL")) {
					sSql += " TRUNC(REC.FEC_RECHAZO) >= TRUNC(TO_DATE('" + valor.trim().toUpperCase() + "', 'DD/MM/YYYY'))";
				}
	
				if (clave.equals("FECRECHAZO_FINAL")) {
					sSql += " TRUNC(REC.FEC_RECHAZO) <= TRUNC(TO_DATE('" + valor.trim().toUpperCase() + "', 'DD/MM/YYYY'))";
				}
	
				ctdFiltro++;
			}
		}
		
		sSql = sSql + " GROUP BY OPO.ID_FUNCION, FUN.DESC_ITEM, OPO.TEX, ASG.DESC_ITEM "
					+ " ORDER BY OPO.ID_FUNCION, FUN.DESC_ITEM, OPO.TEX, ASG.DESC_ITEM ";
	
		try {
			//System.out.println(sSql);
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			ResultSet rs = ps.executeQuery();
	
			while(rs.next()) {
				CifraDTO cifraDTO = new CifraDTO();
				
				cifraDTO.setIdFuncion(rs.getString("ID_FUNCION"));
				cifraDTO.setFuncion(rs.getString("FUNCION"));
				cifraDTO.setCveAsignatura(rs.getString("TEX"));
				cifraDTO.setAsignatura(rs.getString("ASIGNATURA"));
				cifraDTO.setTot2(rs.getString("RENUNCIA"));
				cifraDTO.setTot4(rs.getString("INASISTENCIA"));
				cifraDTO.setTot9(rs.getString("DOCUMENTACION"));
				cifraDTO.setTot14(rs.getString("NEGATIVA"));
				cifraDTO.setTotal(rs.getString("TOTAL"));
				
				lstCifraAsignacion.add(cifraDTO);
			}
	
			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getCifrasRechazo() de AspiranteMSDaoImpl:\n" + sqle.getMessage(), sqle);
		}

		
		return lstCifraAsignacion;
	}

	@Override
	public String registroFederal(AspiranteDTO aspirante, String observacion, String usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN PKGSIAPRODEM.P_REGISTRO_FEDERAL_MS(?,?,?,?,?,?,?); END;";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			CallableStatement sto = cnn.prepareCall(sSql);

			sto.setString(1, aspirante.getFolioFederal());
			sto.setString(2, aspirante.getIdConvocatoria());
			sto.setString(3, aspirante.getIdFuncion());
			sto.setString(4, observacion);
			sto.setString(5, usuario);
						
			sto.registerOutParameter(6, 12);
			sto.registerOutParameter(7, 12);
			
			sto.execute();
			
			String cveError = sto.getString(6);
			String descError = sto.getString(7);
			
			sto.close();
			cnn.desconectar();
			
			resultado = cveError + "|" + descError;

		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método registroFederal() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}

}
