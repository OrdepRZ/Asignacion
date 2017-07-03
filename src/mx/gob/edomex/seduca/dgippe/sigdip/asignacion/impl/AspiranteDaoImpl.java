package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl;

import java.io.BufferedInputStream;
import java.io.IOException;
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

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.UploadedFile;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.AspiranteDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.ArchivoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.CifraDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.FilaAspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.HistoricoEstatusDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.RechazoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.VacanteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
//import mx.gob.edomex.seduca.dgippe.sigdip.util.encripcion.DesEncrypterDOCS;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;
//import mx.gob.edomex.seduca.dgippe.sigdip.ws.cliente.docenteService.DocenteService;
//import mx.gob.edomex.seduca.dgippe.sigdip.ws.cliente.docenteService.DocenteService_Service;
//import mx.gob.edomex.seduca.dgippe.sigdip.ws.cliente.mongodb.SalvarArchivoMongo;
//import mx.gob.edomex.seduca.dgippe.sigdip.ws.cliente.mongodb.SalvarArchivoMongoService;

public class AspiranteDaoImpl implements AspiranteDAO {
		
	static final String _MONGO_BASE = "test";

	@Override
	public String cargarArchivoAspitantes(UploadedFile archivo, String contentType) throws DBExcepcion, SistemaExcepcion {
		String resultado;
		String codErr = "01";
		String desErr = "Error en cargarArchivoAspitantes() de AspiranteDaoImpl.";
		resultado = codErr + "|" + desErr;
		
		try {
			if(contentType.trim().equals("application/vnd.ms-excel")) {
				resultado = cargarAspirantesXLS(archivo);
			} else if(contentType.trim().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				//resultado = cargarAspirantesXLSX( archivo);
			}
		} catch (Exception e) {
			throw new SistemaExcepcion("Error en el método cargarArchivoAspitantes() de AspiranteDaoImpl:\n" + e.getMessage(), e);
		}
		
		return resultado;
	}
	
	
	private String cargarAspirantesXLS(UploadedFile archivo) throws DBExcepcion, SistemaExcepcion {
		String resultado;
		String codErr = "01";
		String desErr = "Error en cargarArchivoAspitantes() de AspiranteDaoImpl.";
		
		try {
			FilaAspiranteDTO aspirante = new FilaAspiranteDTO();
			BufferedInputStream bis = new BufferedInputStream(archivo.getInputstream());
			HSSFWorkbook wb = new HSSFWorkbook(bis);
			HSSFSheet hoja = wb.getSheetAt(0);
			int filaIndice = 1;
			HSSFRow filaCero = hoja.getRow(0);
			
			if (filaCero != null ) {
				if(filaCero.getCell(0).toString().trim().equals("FOLIOFEDER_A") && filaCero.getCell(1).toString().trim().equals("ENT")
						&& filaCero.getCell(2).toString().trim().equals("CLAVEDECLA") && filaCero.getCell(51).toString().trim().equals("SUBSISTEMA")
						&& filaCero.getCell(52).toString().trim().equals("CVEASIGNATURA")) {
					
					boolean correcto = false;
					Conexion cnn = new Conexion();
					cnn.conectar();
					cnn.getConexion().setAutoCommit(false);
					PreparedStatement ps = null;

					try {
						//Recorrer renglones de datos.
						int registrosLeidos = 0;
						int registrosInsertados = 0;
						int insertado = -1;
						String sSql = "";
						for (filaIndice = 1; filaIndice < hoja.getLastRowNum(); filaIndice++) {
							HSSFRow fila = hoja.getRow(filaIndice);
							
							if(fila.getCell(0)==null||(fila.getCell(0)!=null&&fila.getCell(0).toString().isEmpty())){
								break;
							}
							
							aspirante.setFoliofeder(fila.getCell(0)!=null?fila.getCell(0).toString().trim():"");
							aspirante.setEnt(fila.getCell(1)!=null?fila.getCell(1).toString().trim():"");
							aspirante.setClavedecla(fila.getCell(2)!=null?fila.getCell(2).toString().trim():"");
							aspirante.setClavedecl2(fila.getCell(3)!=null?fila.getCell(3).toString().trim():"");
							aspirante.setExperienci(fila.getCell(4)!=null?fila.getCell(4).toString().trim():"");
							aspirante.setExperienc2(fila.getCell(5)!=null?fila.getCell(5).toString().trim():"");
							aspirante.setExperienc3(fila.getCell(6)!=null?fila.getCell(6).toString().trim():"");
							aspirante.setExperienc4(fila.getCell(7)!=null?fila.getCell(7).toString().trim():"");
							aspirante.setTexn(fila.getCell(8)!=null?fila.getCell(8).toString().trim():"");
							aspirante.setLengua(fila.getCell(9)!=null?fila.getCell(9).toString().trim():"");
							aspirante.setEstatal(fila.getCell(10)!=null?fila.getCell(10).toString().trim():"");
							aspirante.setEntidad(fila.getCell(11)!=null?fila.getCell(11).toString().trim():"");
							aspirante.setEvaladic(fila.getCell(12)!=null?fila.getCell(12).toString().trim():"");
							aspirante.setPlaza(fila.getCell(13)!=null?fila.getCell(13).toString().trim():"");
							aspirante.setCaps(fila.getCell(14)!=null?fila.getCell(14).toString().trim():"");
							aspirante.setTipconvoca_a(fila.getCell(15)!=null?fila.getCell(15).toString().trim():"");
							aspirante.setNom_texn(fila.getCell(16)!=null?fila.getCell(16).toString().trim():"");
							aspirante.setCapd(fila.getCell(17)!=null?fila.getCell(17).toString().trim():"");
							aspirante.setCenni(fila.getCell(18)!=null?fila.getCell(18).toString().trim():"");
							aspirante.setAdicional(fila.getCell(19)!=null?fila.getCell(19).toString().trim():"");
							aspirante.setTex2(fila.getCell(20)!=null?fila.getCell(20).toString().trim():"");
							aspirante.setLlave(fila.getCell(21)!=null?fila.getCell(21).toString().trim():"");
							aspirante.setCurp_a(fila.getCell(22)!=null?fila.getCell(22).toString().trim():"");
							aspirante.setNombre_a(fila.getCell(23)!=null?fila.getCell(23).toString().trim():"");
							aspirante.setP_s1(fila.getCell(24)!=null?fila.getCell(24).toString().trim():"");
							aspirante.setP_s1_1(fila.getCell(25)!=null?fila.getCell(25).toString().trim():"");
							aspirante.setP_s1_2(fila.getCell(26)!=null?fila.getCell(26).toString().trim():"");
							aspirante.setP_s2(fila.getCell(27)!=null?fila.getCell(27).toString().trim():"");
							aspirante.setP_s2_1(fila.getCell(28)!=null?fila.getCell(28).toString().trim():"");
							aspirante.setP_s2_2(fila.getCell(29)!=null?fila.getCell(29).toString().trim():"");
							aspirante.setP_s2_3(fila.getCell(30)!=null?fila.getCell(30).toString().trim():"");
							aspirante.setP_s3(fila.getCell(31)!=null?fila.getCell(31).toString().trim():"");
							aspirante.setP_pc1_p1(fila.getCell(32)!=null?fila.getCell(32).toString().trim():"");
							aspirante.setP_pc2_p1(fila.getCell(33)!=null?fila.getCell(33).toString().trim():"");
							aspirante.setP_pc1_p2(fila.getCell(34)!=null?fila.getCell(34).toString().trim():"");
							aspirante.setP_pc2_p2(fila.getCell(35)!=null?fila.getCell(35).toString().trim():"");
							aspirante.setP_pc1_p3(fila.getCell(36)!=null?fila.getCell(36).toString().trim():"");
							aspirante.setP_pc2_p3(fila.getCell(37)!=null?fila.getCell(37).toString().trim():"");
							aspirante.setNd_p1(fila.getCell(38)!=null?fila.getCell(38).toString().trim():"");
							aspirante.setNd_p2(fila.getCell(39)!=null?fila.getCell(39).toString().trim():"");
							aspirante.setNd_p3(fila.getCell(40)!=null?fila.getCell(40).toString().trim():"");
							aspirante.setNd_total(fila.getCell(41)!=null?fila.getCell(41).toString().trim():"");
							aspirante.setN_pruebas(fila.getCell(42)!=null?fila.getCell(42).toString().trim():"");
							aspirante.setNi(fila.getCell(43)!=null?fila.getCell(43).toString().trim():"");
							aspirante.setNii(fila.getCell(44)!=null?fila.getCell(44).toString().trim():"");
							aspirante.setNiii(fila.getCell(45)!=null?fila.getCell(45).toString().trim():"");
							aspirante.setGpo_desemp(fila.getCell(46)!=null?fila.getCell(46).toString().trim():"");
							aspirante.setPre_s1(fila.getCell(47)!=null?fila.getCell(47).toString().trim():"");
							aspirante.setPre_s2(fila.getCell(48)!=null?fila.getCell(48).toString().trim():"");
							aspirante.setPre_s3(fila.getCell(49)!=null?fila.getCell(49).toString().trim():"");
							aspirante.setPrelac(fila.getCell(50)!=null?fila.getCell(50).toString().trim():"");
							aspirante.setSubsistema(fila.getCell(51)!=null?fila.getCell(51).toString().trim():"");
							aspirante.setCveasignatura(fila.getCell(52)!=null?fila.getCell(52).toString().trim():"");
							aspirante.setBase(fila.getCell(53)!=null?fila.getCell(53).toString().trim():"");
							
							sSql = construirInsertAspirante(aspirante);
							ps = cnn.prepareStatement(sSql);
							insertado = -1;
							insertado = ps.executeUpdate();
							
							if (insertado == 1) {
								registrosInsertados++;
							}
							registrosLeidos++;
							
							//System.out.println("Registro="+registrosLeidos+";Query="+sSql);
						}
						
						codErr = "00";
						desErr = "Resumen|ArchivoLeído="+archivo.getFileName()+";RegistrosLeídos="+registrosLeidos+";RegistrosInsertados="+registrosInsertados;
						//System.out.println(desErr);

					} catch (Exception e) {
						throw new SistemaExcepcion("Exception en el método cargarAspirantesXLS() de AspiranteDaoImpl:\n" + e.getMessage(), e);
					} finally {
						if (correcto) {
							cnn.getConexion().commit();
						} else {
							cnn.getConexion().rollback();
						}						
						cnn.desconectar();
					} //try conexión
					
				} else {
					codErr = "03";
					desErr = "Formato de archivo incorrecto, las columnas no corresponden con el formato válido para Aspirantes.";					
				}
			} else {
				codErr = "02";
				desErr = "Formato de archivo incorrecto, el número de columnas no corresponden con el formato válido para Aspirantes.";
			}
			
		} catch (Exception e) {
			throw new SistemaExcepcion("Exception en el método cargarAspirantesXLS() de AspiranteDaoImpl:\n" + e.getMessage(), e);
		}
		
		resultado = codErr + "|" + desErr;
		
		return resultado;
	}
	
	private String construirInsertAspirante(FilaAspiranteDTO aspirante) {
		String insert = "INSERT INTO TBLOPOSI ("
					 	+ "FOLIOFEDER, CURP_A, NOMBRE_A, ENT, CLAVEDECLA, CLAVEDECL2, EXPERIENCI, EXPERIENC2, EXPERIENC3, EXPERIENC4, "
					 	+ "TEXN, LENGUA, ESTATAL, ENTIDAD, EVALADIC, PLAZA, CAPS, TIPCONVOCA_A, NOM_TEXN, CAPD, CENNI, TEX2, LLAVE, "
					 	+ "P_S1, P_S1_1, P_S1_2, P_S2, P_S2_1, P_S2_2, P_S2_3, P_PC1_P1, P_PC2_P1, P_PC1_P2, P_PC2_P2, P_S3, ND_P1, "
					 	+ "ND_P2, ND_P3, ND_TOTAL, N_PRUEBAS, NI, NII, NIII, GPO_DESEMP, PRELAC, ADICIONAL, P_PC1_P3, P_PC2_P3, "
					 	+ "PRE_S1, PRE_S2, PRE_S3, SUBSISTEMA, CVEASIGNATURA, BASE, STATUS ) VALUES (";
		
		insert += "'" + aspirante.getFoliofeder() + "',";
		insert += "'" + aspirante.getCurp_a() + "',";
		insert += "'" + aspirante.getNombre_a() + "',";
		insert += "'" + aspirante.getEnt() + "',";
		insert += "'" + aspirante.getClavedecla() + "',";
		insert += "'" + aspirante.getClavedecl2() + "',";
		insert += "'" + aspirante.getExperienci() + "',";
		insert += "'" + aspirante.getExperienc2() + "',";
		insert += "'" + aspirante.getExperienc3() + "',";
		insert += "'" + aspirante.getExperienc4() + "',";
		insert += "'" + aspirante.getTexn() + "',";
		insert += "'" + aspirante.getLengua() + "',";
		insert += "'" + aspirante.getEstatal() + "',";
		insert += "'" + aspirante.getEntidad() + "',";
		insert += "'" + aspirante.getEvaladic() + "',";
		insert += "'" + aspirante.getPlaza() + "',";
		insert += "'" + aspirante.getCaps() + "',";
		insert += "'" + aspirante.getTipconvoca_a() + "',";
		insert += "'" + aspirante.getNom_texn() + "',";
		insert += "'" + aspirante.getCapd() + "',";
		insert += "'" + aspirante.getCenni() + "',";
		insert += "'" + aspirante.getTex2() + "',";
		insert += "'" + aspirante.getLlave() + "',";
		insert += "'" + aspirante.getP_s1() + "',";
		insert += "'" + aspirante.getP_s1_1() + "',";
		insert += "'" + aspirante.getP_s1_2() + "',";
		insert += "'" + aspirante.getP_s2() + "',";
		insert += "'" + aspirante.getP_s2_1() + "',";
		insert += "'" + aspirante.getP_s2_2() + "',";
		insert += "'" + aspirante.getP_s2_3() + "',";
		insert += "'" + aspirante.getP_pc1_p1() + "',";
		insert += "'" + aspirante.getP_pc2_p1() + "',";
		insert += "'" + aspirante.getP_pc1_p2() + "',";
		insert += "'" + aspirante.getP_pc2_p2() + "',";
		insert += "'" + aspirante.getP_s3() + "',";
		insert += "'" + aspirante.getNd_p1() + "',";
		insert += "'" + aspirante.getNd_p2() + "',";
		insert += "'" + aspirante.getNd_p3() + "',";
		insert += "'" + aspirante.getNd_total() + "',";
		insert += "'" + aspirante.getN_pruebas() + "',";
		insert += "'" + aspirante.getNi() + "',";
		insert += "'" + aspirante.getNii() + "',";
		insert += "'" + aspirante.getNiii() + "',";
		insert += "'" + aspirante.getGpo_desemp() + "',";
		insert += "'" + aspirante.getPrelac() + "',";
		insert += "'" + aspirante.getAdicional() + "',";
		insert += "'" + aspirante.getP_pc1_p3() + "',";
		insert += "'" + aspirante.getP_pc2_p3() + "',";
		insert += "'" + aspirante.getPre_s1() + "',";
		insert += "'" + aspirante.getPre_s2() + "',";
		insert += "'" + aspirante.getPre_s3() + "',";
		insert += "'" + aspirante.getSubsistema() + "',";
		insert += "'" + aspirante.getCveasignatura() + "',";
		insert += "'" + aspirante.getBase() + "',";
		insert += "'0'";
		insert += ")";
		
		return insert;
	}

	@Override
	public List<AspiranteDTO> getAspirantes(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		List<AspiranteDTO> lstAspirante = new ArrayList<AspiranteDTO>();
		
		String sSql = "SELECT OPO.ID_CONVOCATORIA, OPO.FOLIOFEDER, OPO.CURP_A, OPO.NOMBRE_A, OPO.ENT, OPO.PLAZA, OPO.TIPCONVOCA_A, OPO.NOM_TEXN, OPO.GPO_DESEMP, OPO.PRELAC, "
					+ "OPO.SUBSISTEMA, OPO.CVEASIGNATURA, ASG.DESC_ITEM DESC_ASIGNATURA, OPO.BASE, OPO.STATUS, OPO.CVEOPOUSU, "
					+ "DECODE(TO_CHAR(OPO.STATUS), '7', STA.DESC_ITEM || ' ' || CONV.DESC_ITEM,  STA.DESC_ITEM) DESC_STATUS, "
					+ "OPO.ID_FUNCION, FUN.DESC_ITEM DESC_FUNCION, PKGSIAPRODEM.F_GET_BND_REGSISFED(OPO.FOLIOFEDER, OPO.ID_CONVOCATORIA, OPO.ID_FUNCION) BND_REGSISFED, "
					+ "VAC.ID, VAC.TIPOFUNCION, VAC.TIPOVACANTE, VAC.CVEASIGNATURA CVEASIGNATURA_VAC, VAC.ASIGNATURA, VAC.PZAJORNADA, VAC.NUMHORAS, "
					+ "NVL(VAC.CCT, 'N/A') CCT, NVL(CCT.NOMBRE, 'N/A') CCTNOMBRE, CCT.CVEMUN, MPO.DESC_ITEM MUNICIPIO, CCT.LOCALIDAD, CCT.DOMICILIO, CCT.TURNO, "
					+ "VAC.FECINICIO, DECODE(VAC.FECTERMINO, '31/12/2100', 'DEFINITIVO', NULL, 'N/A', VAC.FECTERMINO) FECTERMINO, CCT.SUBSISTEMA, "
					+ "NVL(VAC.FOLIO, 0) FOLIO, CCT.ZONAESCOLAR, CCT.SUBDIRREG, VAC.ID_FUNCION ID_FUNCION_VAC, "
					+ "TO_CHAR(VAC.FECASIGNACION, 'DD/MM/YYYY') FECASIGNACION, VAC.ID_CONVOCATORIA ID_CONVOCATORIA_VAC, "
					+ "VAC.TIPO_NOMBRAMIENTO MOTIVO_VACANTE, NVL(VAC.CVE_PRESUPUESTAL, 'N/A') CVE_PRESUPUESTAL, VAC.CVEOPOUSU, "
					+ "TPO.DESC_ITEM DESC_TPO_VAC, SUB.DESC_ITEM DESC_SUBSISTEMA, "
					+ "REC.ID_MOTIVO, MVO.DESC_ITEM DESC_MOTIVO_RECHAZO, TO_CHAR(REC.FEC_RECHAZO, 'DD/MM/YYYY') FEC_RECHAZO, "
					+ "DECODE(OPO.STATUS, 3, NVL(REC.OBSERVACION, 'SIN OBSERVACION'), 'N/A') OBSERVACION, "
					+ "CONV.DESC_ITEM DESC_CONV "
					+ "FROM TBLOPOSI OPO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOEVAL' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') ASG, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOSTAT' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') STA, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGFUNASP' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') FUN, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGSUBSIS' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') SUB, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOVACA' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') TPO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMTVRECH' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') MVO,"
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMPO' AND ID_ITEM <> 0) MPO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGCONVOC' AND ID_ITEM <> 0) CONV, "
					+ "TBPZSVAC VAC, TBLCCT CCT, TBLMVORECHAZO REC "
					+ "WHERE OPO.CVEASIGNATURA = ASG.ID_ITEM (+) "
					+ "AND OPO.STATUS = STA.ID_ITEM (+) "
					+ "AND OPO.ID_FUNCION = FUN.ID_ITEM (+) "
					+ "AND OPO.FOLIOFEDER = VAC.FOLIO (+) "
					+ "AND OPO.ID_CONVOCATORIA = VAC.ID_CONVOCATORIA (+) "
					+ "AND OPO.ID_FUNCION = VAC.ID_FUNCION (+) "
					+ "AND VAC.CCT = CCT.CCT (+)"
					+ "AND CCT.SUBSISTEMA = SUB.ID_ITEM (+) "
					+ "AND CCT.CVEMUN = MPO.ID_ITEM (+) "
					+ "AND VAC.PZAJORNADA = TPO.ID_ITEM (+) "
					+ "AND OPO.FOLIOFEDER = REC.FOLIOFEDER (+) "
					+ "AND OPO.ID_FUNCION = REC.ID_FUNCION (+) "
					+ "AND OPO.ID_CONVOCATORIA = REC.ID_CONVOCATORIA (+) "
					+ "AND REC.ID_MOTIVO = MVO.ID_ITEM (+) "
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

				if (clave.equals("CVEASIGNATURA")) {
					sSql += " OPO.CVEASIGNATURA = " + valor.trim();
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
					sSql += " UPPER(VAC.CCT) = '" + valor.trim().toUpperCase() + "'";
				}

				if (clave.equals("SUBSISTEMA")) {
					sSql += " CCT.SUBSISTEMA = " + valor.trim();
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
		
		sSql = sSql + " ORDER BY OPO.ID_CONVOCATORIA, OPO.CVEASIGNATURA, OPO.TIPCONVOCA_A, OPO.GPO_DESEMP, OPO.PRELAC ";

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

				aspiranteDTO.setIdConvocatoria(rs.getString("ID_CONVOCATORIA"));
				aspiranteDTO.setDescConvocatoria(rs.getString("DESC_CONV"));
				aspiranteDTO.setFolioFederal(rs.getString("FOLIOFEDER"));
				aspiranteDTO.setCurp(rs.getString("CURP_A"));
				aspiranteDTO.setNombre(rs.getString("NOMBRE_A"));
				aspiranteDTO.setEnt(rs.getString("ENT"));
				aspiranteDTO.setPlaza(rs.getString("PLAZA"));
				aspiranteDTO.setTipoConvocatoria(rs.getString("TIPCONVOCA_A"));
				aspiranteDTO.setNomTexn(rs.getString("NOM_TEXN"));
				aspiranteDTO.setGpoDesmo(rs.getString("GPO_DESEMP"));
				aspiranteDTO.setPrelac(rs.getString("PRELAC"));
				aspiranteDTO.setSubsistema(rs.getString("SUBSISTEMA"));
				aspiranteDTO.setCveAsignatura(rs.getString("CVEASIGNATURA"));
				aspiranteDTO.setAsignatura(rs.getString("DESC_ASIGNATURA"));
				aspiranteDTO.setBase(rs.getString("BASE"));
				aspiranteDTO.setStatus(rs.getString("STATUS"));
				aspiranteDTO.setCveOpoUsu(rs.getString("CVEOPOUSU"));
				aspiranteDTO.setDescStatus(rs.getString("DESC_STATUS"));
				aspiranteDTO.setCct(rs.getString("CCT"));
				aspiranteDTO.setNombreCCT(rs.getString("CCTNOMBRE"));
				aspiranteDTO.setIdFuncion(rs.getString("ID_FUNCION"));
				aspiranteDTO.setFuncion(rs.getString("DESC_FUNCION"));
				aspiranteDTO.setCvePresupuestal(rs.getString("CVE_PRESUPUESTAL"));
				aspiranteDTO.setBndRegSisFed(rs.getString("BND_REGSISFED"));

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
			throw new SistemaExcepcion("Error en el método getAspirantes() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}

		return lstAspirante;
	}

	@Override
	public List<HistoricoEstatusDTO> getHistoricoEstatus(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		List<HistoricoEstatusDTO> lstHistoricoEstatus = new ArrayList<HistoricoEstatusDTO>();
		
		String sSql = "SELECT HST.ID_HISTORICO, HST.FOLIOFEDER, HST.ID_CONVOCATORIA, HST.ID_FUNCION,  "
					+ "HST.STATUS, HST.ID_TBPZSVAC, HST.ID_MOTIVO_RECHAZO, HST.OBSERVACION, "
					+ "TO_CHAR(HST.FEC_STATUS, 'DD/MM/YYYY HH24:MI:SS') FEC_STATUS, HST.USU_STATUS, STA.DESC_ITEM DESC_STATUS, "
					+ "DECODE(HST.STATUS, 2, VAC.CCT, 3, MVO.DESC_ITEM, 'N/A') DETALLE "
					+ "FROM HISTORICO_TBLOPOSI HST, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOSTAT' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') STA, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMTVRECH' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') MVO, "
					+ "TBPZSVAC VAC "
					+ "WHERE HST.STATUS = STA.ID_ITEM (+) "
					+ "AND HST.ID_MOTIVO_RECHAZO = MVO.ID_ITEM (+) "
					+ "AND HST.ID_TBPZSVAC = VAC.ID (+) ";
					
	
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

	@Override
	public String asignarVacante(String folioFederal, String idConvocatoria, String idFuncion, String idVacante, String subsistema, String usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN PKGSIAPRODEM.P_ASIGNAR_VACANTE(?,?,?,?,?,?,?,?); END;";
		
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
			throw new SistemaExcepcion("Error en el método asignarVacante() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	
	@Override
	public String registrarRenuncia(String folioFederal, String idConvocatoria, String idFuncion, String idMotivo, String observacion, String usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN PKGSIAPRODEM.P_REGISTRAR_RECHAZO(?,?,?,?,?,?,?,?); END;";
		
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
			throw new SistemaExcepcion("Error en el método registrarRenuncia() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	
	@Override
	public List<AspiranteDTO> getAspirantesSede(String idSede, String idGrupo, String fechaAplicacion, String idTurno)throws DBExcepcion, SistemaExcepcion {
		List<AspiranteDTO> listaAspirantes = new ArrayList<AspiranteDTO>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			sSql = "SELECT a.*, asis.bandera_asistencia "
				+"FROM "
				+"	( "
				+"		SELECT folio_federal, id_convocatoria, funcion_aspirante, nombre, primer_apellido, segundo_apellido "
				+"		FROM aspirantes "
				+"		WHERE id_estatus = 4 " 
				+"		AND id_sede_aplicacion = " + idSede
				+"		AND grupo = "+ idGrupo
				+"		AND TO_DATE(TO_CHAR(fecha_aplicacion,'dd/MM/yyyy'),'dd/MM/yyyy') = to_date('"+ fechaAplicacion +"','dd/MM/yyyy') "
				+"		UNION "
				+"		SELECT folio_federal, id_convocatoria, funcion_aspirante, nombre, primer_apellido, segundo_apellido "
				+"		FROM aspirantes "
				+"		WHERE id_estatus = 4 "
				+"		AND id_sede_aplicacion = " + idSede 
				+"		AND grupo = " + idGrupo 
				+"		AND TO_DATE(TO_CHAR(fecha_aplicacion_adicional,'dd/MM/yyyy'),'dd/MM/yyyy') = to_date('"+ fechaAplicacion +"','dd/MM/yyyy') "
				+"	) a "
				+"LEFT OUTER JOIN  asistencias asis "
				+"ON a.folio_federal = asis.folio_federal "
				+"AND a.id_convocatoria = asis.id_convocatoria "
				+"AND a.funcion_aspirante = asis.funcion_aspirante "
				+"AND TO_DATE(TO_CHAR(asis.fecha_asistencia,'dd/MM/yyyy'),'dd/MM/yyyy') = to_date('"+ fechaAplicacion +"','dd/MM/yyyy')"
				+"AND asis.turno = " + idTurno
				+" ORDER BY a.primer_apellido, a.segundo_apellido, a.nombre";
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AspiranteDTO aspirante = new AspiranteDTO();
				aspirante.setFolioFederal(rs.getString("folio_federal"));
				aspirante.setIdConvocatoria(rs.getString("id_convocatoria"));
				aspirante.setIdFuncion(rs.getString("funcion_aspirante"));
				aspirante.setNombre(rs.getString("nombre"));
				aspirante.setPrimerApellido(rs.getString("primer_apellido"));
				aspirante.setSegundoApellido(rs.getString("segundo_apellido"));
				
				if(rs.getString("bandera_asistencia") != null && rs.getString("bandera_asistencia")!= ""){
					if(rs.getString("bandera_asistencia").equals("true")){
						aspirante.setBndAsistencia(true);
					}else{
						aspirante.setBndAsistencia(false);
					}
				}else{
					aspirante.setBndAsistencia(false);
				}
				
				listaAspirantes.add(aspirante);
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getAspirantesSede de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return listaAspirantes;
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
			
			sSql = "SELECT ID_MOTIVO FROM TBLMVORECHAZO WHERE FOLIOFEDER = " + folioFederal + " AND ID_CONVOCATORIA = " + idConvocatoria + " AND ID_FUNCION = " + idFuncion;
			
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
	public String confirmarAspirante(String folioFederal, String curp) throws DBExcepcion,SistemaExcepcion {
		String sResultado = "";
		String sSql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			sSql = "SELECT * FROM aspirantes WHERE folio_federal = '"+ folioFederal +"' AND curp = '"+ curp +"'";
			
			//System.out.println(sSql);
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				sSql = "UPDATE aspirantes SET bnd_conf_correo = 'true', fec_conf_correo = CURRENT_TIMESTAMP WHERE folio_federal = '"+ folioFederal +"' AND curp = '"+ curp +"'";
				
				ps = cnn.prepareStatement(sSql);
				int iResAct = ps.executeUpdate();
				
				if(iResAct != 1){
					sResultado = "incorrecto";
				}else{
					sResultado = "correcto";
				}
			}else{
				sResultado = "noExisteFolio";
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método confirmarAspirante de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return sResultado;
	}

	@Override
	public AspiranteDTO consultarAspCurp(String curp) throws DBExcepcion,SistemaExcepcion {
		AspiranteDTO aspirante = new AspiranteDTO();
		String sSql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			sSql = "select distinct(curp) curp, nombre, primer_apellido, segundo_apellido from aspirantes where curp = upper('"+ curp +"')";
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				aspirante.setCurp(rs.getString("curp"));
				aspirante.setNombre(rs.getString("nombre"));
				aspirante.setPrimerApellido(rs.getString("primer_apellido"));
				aspirante.setSegundoApellido(rs.getString("segundo_apellido"));
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método consultarAspCurp de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return aspirante;
	}


	@Override
	public String cargarArchivosCurp(List<UploadedFile> listaArchivos,String curp, String usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "";
		String sSql = "";
		PreparedStatement ps = null;
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			cnn.getConexion().setAutoCommit(false);
			
			for(int i = 0; i < listaArchivos.size(); i++){
				sSql = "INSERT INTO documentos_expediente VALUES ((SELECT NVL(MAX(id_documento),0) +1 FROM documentos_expediente),"
					+ "upper('"+curp+"'),?,'"+ usuario +"', CURRENT_TIMESTAMP)";
				
				ps = cnn.prepareStatement(sSql);
				
				ps.setBinaryStream(1, listaArchivos.get(i).getInputstream(),1000000);
				
				int resIns = ps.executeUpdate();
				
				if(resIns <= 0){
					resultado = "error";
					break;
				}
			}
			
			if(resultado.equals("error")){
				cnn.getConexion().rollback();
			}else{
				cnn.getConexion().commit();
				resultado = "correcto";
			}
			
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método cargarArchivosCurp de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	/*
	@Override
	public ArchivoDTO getNombramiento(String folioFeder, String idConvocatoria, String idFuncion, String idVacante) throws DBExcepcion, SistemaExcepcion {
		ArchivoDTO nombramiento = null;
		
		String sSql = "SELECT *" 
					+ " FROM TBLNOMBRAMIENTO"
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
				Blob blobArchivo = rs.getBlob("ARCHIVO");
				nombramiento.setArhivo(blobArchivo.getBytes(1, (int) blobArchivo.length()));
				nombramiento.setFecAlta(rs.getString("FECHA_ALTA"));
				nombramiento.setUsuAlta(rs.getString("USUARIO_ALTA"));
				nombramiento.setIdVacante(rs.getString("ID_VACANTE"));
				nombramiento.setIdFuncion(rs.getString("ID_FUNCION"));
				nombramiento.setSecuencia(rs.getString("SECUENCIA"));
			}

			rs.close();
			ps.close();
			cnn.desconectar();
			
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		
		return nombramiento;
	}
 	*/
	
	@Override
	public ArchivoDTO getNombramiento(String folioFeder, String idConvocatoria, String idFuncion, String idVacante) throws DBExcepcion, SistemaExcepcion {
		ArchivoDTO nombramiento = null;
		
		String sSql = "SELECT *" 
					+ " FROM TBLNOMBRAMIENTO"
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
					 * Encripción de parametros para recuperación del repositorio
					DesEncrypterDOCS encrypter = new DesEncrypterDOCS(_MONGO_KEY);
					
					String nombre = encrypter.encrypt(nombramiento.getLlaveMongo());
					String coleccion = encrypter.encrypt(nombramiento.getColeccionMongo());
					String base = encrypter.encrypt(_MONGO_BASE);
					*/

					String nombre = nombramiento.getLlaveMongo();
					String coleccion = nombramiento.getColeccionMongo();
					String base = _MONGO_BASE;

					nombramiento.setUrl("http://siase2.edomex.gob.mx/DespachadorArchivos/verArchivo?param="+ nombre +"&param1="+ coleccion +"&param2="+base);
					
					//System.out.println(nombramiento.getUrl());
					
					/*
					 * NO FUNCIONAL ya que al establecer la conexión con el despachador, caduca el tiempo de espera.
					try {
						//System.out.print("Connecting to " + url.toString() + " ... ");
						URLConnection urlConn  = url.openConnection();

						//System.out.print("Contenido: " + urlConn.getContentType() + ". ");
						
						if (!urlConn.getContentType().equalsIgnoreCase("application/pdf")) {
							throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteDaoImpl." +
														"\n[La liga de recuperación no es un PDF.]" +
														"\nCopia y pega manualmente en tu navegador la siguiente liga: " +
														"\n"+url.toString());
						} else {
							byte[] buffer = new byte[1024];
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							
							int bytesLeidos;

							InputStream is = url.openStream();
							while ((bytesLeidos = is.read(buffer)) != -1) {
								baos.write(buffer, 0, bytesLeidos);
							}
							is.close();
							
							nombramiento.setArhivo(baos.toByteArray());
						}

					} catch (Exception e) {
						throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteDaoImpl." +
								"\n[No se pudo recuperar el archivo del repositorio.]" +
								"\nCopia y pega manualmente en tu navegador la siguiente liga: " +
								"\n"+url.toString());
					}
					*/
				          
				} else {
					throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteDaoImpl." +
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
			throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
 
		
		
		return nombramiento;
	}
	
	/*
	@Override
	public String almacenarNombramiento(ArchivoDTO archivoDTO) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "INSERT INTO TBLNOMBRAMIENTO"
					+ " (ID_DOCUMENTO, FOLIOFEDER, ID_CONVOCATORIA, NOMBRE_ARCHIVO, ARCHIVO, FECHA_ALTA, USUARIO_ALTA, B_BAJA, ID_VACANTE, ID_FUNCION, SECUENCIA)"
					+ " VALUES ((SELECT NVL(MAX(ID_DOCUMENTO),0)+1 FROM TBLNOMBRAMIENTO),?,?,?,?,SYSDATE,?,'F', ?, ?, ?)";
		
		try {
			
			Conexion cnn = new Conexion();
			cnn.conectar();
			cnn.getConexion().setAutoCommit(false);
			
			PreparedStatement ps = cnn.prepareStatement(sSql);
			
			ps.setString(1, archivoDTO.getFolioFeder());
			ps.setString(2, archivoDTO.getIdConvocatoria());
			ps.setString(3, archivoDTO.getNombreArchivo());
			ps.setBytes(4, archivoDTO.getArhivo());
			ps.setString(5, archivoDTO.getUsuAlta());
			ps.setString(6, archivoDTO.getIdVacante());
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
			throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
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
			String coleccionMongo = "SIAPRODEM_NOMBRAMIENTOS_BA_" + formatoFecha.format(fecha).toUpperCase();

			/*
			DocenteService_Service docenteWS = new DocenteService_Service();
			DocenteService docenteServicio = docenteWS.getDocenteServicePort();
			respuestaWS = docenteServicio.registraEmpleadoConNombramientoArchivo(curp, nombre, apellidoPaterno, apellidoMaterno, correoElectronico, nombramiento, coleccion, descripcionNombramientoProperties, usuario);
			*/
			
			/*
			 * Servicio de almacenado
			SalvarArchivoMongoService servicioMongo = new SalvarArchivoMongoService();
			SalvarArchivoMongo salvarArchivoMongo = servicioMongo.getSalvarArchivoMongoPort();
			respuestaWS = salvarArchivoMongo.salvarArchivo(archivoDTO.getArhivo(), "pdf", coleccionMongo);
			*/
			
			respuestaWS = "FAIL";
			if (respuestaWS.startsWith("OK")) {
				//Guardado en MongoDB
				llaveMongo = respuestaWS.substring(3);
				

				String sSql = "INSERT INTO TBLNOMBRAMIENTO"
						+ " (ID_DOCUMENTO, FOLIOFEDER, ID_CONVOCATORIA, NOMBRE_ARCHIVO, ARCHIVO, FECHA_ALTA, USUARIO_ALTA, B_BAJA, ID_VACANTE, ID_FUNCION, SECUENCIA, LLAVE_MONGO, COLECCION_MONGO)"
						+ " VALUES ((SELECT NVL(MAX(ID_DOCUMENTO),0)+1 FROM TBLNOMBRAMIENTO),?,?,?,?,SYSDATE,?,'F', ?, ?, ?, ?, ?)";
				
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
			throw new SistemaExcepcion("Error en el método getNombramiento() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}
	
	
	@Override
	public String ejecutarReversaStatus(String folioFederal,  String idConvocatoria, String idFuncion, String observacionReversa, String usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN PKGSIAPRODEM.P_REVERSA_STATUS(?,?,?,?,?,?,?); END;";
		
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
			throw new SistemaExcepcion("Error en el método ejecutarReversaStatus() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;

	}
	
	@Override
	public List<CifraDTO> getCifrasAsignacion(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		// TODO Auto-generated method stub
		List<CifraDTO> lstCifraAsignacion = new ArrayList<CifraDTO>();

		String sSql = "SELECT VAC.ID_FUNCION, FUN.DESC_ITEM FUNCION, VAC.CVEASIGNATURA, ASG.DESC_ITEM ASIGNATURA, "
					+ "SUM(DECODE(CCT.SUBSISTEMA, 15, 1, 0)) ESTATAL, "
					+ "SUM(DECODE(CCT.SUBSISTEMA, 34, 1, 0)) FEDERALIZADO, "
					+ "COUNT(*) TOTAL "
					+ "FROM TBLOPOSI OPO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOEVAL' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') ASG, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGFUNASP' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') FUN, "
					+ "TBPZSVAC VAC, TBLCCT CCT "
					+ "WHERE OPO.FOLIOFEDER = VAC.FOLIO (+) "
					+ "AND OPO.ID_CONVOCATORIA = VAC.ID_CONVOCATORIA (+) "
					+ "AND OPO.ID_FUNCION = VAC.ID_FUNCION (+) "
					+ "AND VAC.CCT = CCT.CCT (+) "
					+ "AND VAC.CVEASIGNATURA = ASG.ID_ITEM (+) "					
					+ "AND VAC.ID_FUNCION = FUN.ID_ITEM (+) "
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
				sSql += " VAC.ID_FUNCION = 1 ";
			}

			if (clave.equals("PROMOCION")) {
				sSql += " VAC.ID_FUNCION <> 1 ";
			}

			if (clave.equals("ID_FUNCION")) {
				sSql += " VAC.ID_FUNCION = " + valor.trim();
			}

			if (clave.equals("ID_CONVOCATORIA")) {
				sSql += " OPO.ID_CONVOCATORIA = " + valor.trim();
			}

			if (clave.equals("CVEASIGNATURA")) {
				sSql += " VAC.CVEASIGNATURA = " + valor.trim();
			}

			if (clave.equals("STATUS")) {
				sSql += " OPO.STATUS = " + valor.trim();
			}
			if (clave.equals("CCT")) {
				sSql += " UPPER(VAC.CCT) = '" + valor.trim().toUpperCase() + "'";
			}

			if (clave.equals("SUBSISTEMA")) {
				sSql += " CCT.SUBSISTEMA = " + valor.trim();
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
	
	sSql = sSql + " GROUP BY VAC.ID_FUNCION, FUN.DESC_ITEM, VAC.CVEASIGNATURA, ASG.DESC_ITEM "
				+ " ORDER BY VAC.ID_FUNCION, FUN.DESC_ITEM, VAC.CVEASIGNATURA, ASG.DESC_ITEM ";

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
			cifraDTO.setCveAsignatura(rs.getString("CVEASIGNATURA"));
			cifraDTO.setAsignatura(rs.getString("ASIGNATURA"));
			cifraDTO.setTotEstatal(rs.getString("ESTATAL"));
			cifraDTO.setTotFederal(rs.getString("FEDERALIZADO"));
			cifraDTO.setTotal(rs.getString("TOTAL"));
			
			lstCifraAsignacion.add(cifraDTO);
		}

		rs.close();
		ps.close();
		cnn.desconectar();
		
	} catch (DBExcepcion dbe) {
		throw dbe;
	} catch (SQLException sqle) {
		throw new SistemaExcepcion("Error en el método getCifrasAsignacion() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
	}

		
		return lstCifraAsignacion;
	}

	@Override
	public List<CifraDTO> getCifrasRechazo(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		// TODO Auto-generated method stub
		List<CifraDTO> lstCifraAsignacion = new ArrayList<CifraDTO>();

		String sSql = "SELECT OPO.ID_FUNCION, FUN.DESC_ITEM FUNCION, OPO.CVEASIGNATURA, ASG.DESC_ITEM ASIGNATURA, "
					+ "SUM(DECODE(REC.ID_MOTIVO, 2, 1, 0)) RENUNCIA, "
					+ "SUM(DECODE(REC.ID_MOTIVO, 4, 1, 0)) INASISTENCIA, "
					+ "SUM(DECODE(REC.ID_MOTIVO, 9, 1, 0)) DOCUMENTACION, "
					+ "SUM(DECODE(REC.ID_MOTIVO, 14, 1, 0)) NEGATIVA, "
					+ "COUNT(*) TOTAL "
					+ "FROM TBLOPOSI OPO, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGTPOEVAL' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') ASG, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGFUNASP' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') FUN, "
					+ "(SELECT ID_ITEM, DESC_ITEM FROM CATALOGOS WHERE NOM_CAT = 'ASGMTVRECH' AND ID_CAT_PADRE <> 0 AND B_DESHABILITADO = 'F' AND B_BAJA = 'F') MVO, "
					+ "TBLMVORECHAZO REC "
					+ "WHERE OPO.FOLIOFEDER = REC.FOLIOFEDER (+) "
					+ "AND OPO.ID_CONVOCATORIA = REC.ID_CONVOCATORIA (+) "
					+ "AND OPO.ID_FUNCION = REC.ID_FUNCION (+) "
					+ "AND OPO.CVEASIGNATURA = ASG.ID_ITEM (+) "					
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
				sSql += " OPO.CVEASIGNATURA = " + valor.trim();
			}

			if (clave.equals("STATUS")) {
				sSql += " OPO.STATUS = " + valor.trim();
			}

			if (clave.equals("SUBSISTEMA")) {
				sSql += " OPO.SUBSISTEMA = " + valor.trim();
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
	
	sSql = sSql + " GROUP BY OPO.ID_FUNCION, FUN.DESC_ITEM, OPO.CVEASIGNATURA, ASG.DESC_ITEM "
				+ " ORDER BY OPO.ID_FUNCION, FUN.DESC_ITEM, OPO.CVEASIGNATURA, ASG.DESC_ITEM ";

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
			cifraDTO.setCveAsignatura(rs.getString("CVEASIGNATURA"));
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
		throw new SistemaExcepcion("Error en el método getCifrasRechazo() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
	}

		
		return lstCifraAsignacion;
	}

	@Override
	public String registroFederal(AspiranteDTO aspirante, String observacion, String usuario) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN PKGSIAPRODEM.P_REGISTRO_FEDERAL(?,?,?,?,?,?,?); END;";
		
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


	@Override
	public String asignarVacanteDefinitiva(String folioFederal, String idConvocatoria, String idFuncion, String idVacanteDefinitiva, String subsistema, String usuario, String idVacanteLiberar, String idMovimiento, String observacion) throws DBExcepcion, SistemaExcepcion {
		String resultado = "01";
		
		String sSql = "BEGIN PKGSIAPRODEM.P_ASIGNAR_VACANTE_DEFINITIVA(?,?,?,?,?,?,?,?,?,?,?); END;";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			CallableStatement sto = cnn.prepareCall(sSql);

			sto.setString(1, folioFederal);
			sto.setString(2, idConvocatoria);
			sto.setString(3, idFuncion);
			sto.setString(4, idVacanteDefinitiva);
			sto.setString(5, subsistema);
			sto.setString(6, usuario);
			sto.setString(7, idVacanteLiberar);
			sto.setString(8, idMovimiento);
			sto.setString(9, observacion);
						
			sto.registerOutParameter(10, 12);
			sto.registerOutParameter(11, 12);
			
			sto.execute();
			
			String cveError = sto.getString(10);
			String descError = sto.getString(11);
			
			sto.close();
			cnn.desconectar();
			
			resultado = cveError + "|" + descError;

		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método asignarVacanteDefinitiva() de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return resultado;
	}

	@Override
	public List<AspiranteDTO> getAspirantesSede(List<CampoRptDTO> lstFiltro)throws DBExcepcion, SistemaExcepcion {
		List<AspiranteDTO> aspirantes = new ArrayList<AspiranteDTO>();
		String idSede = ""; 
		String idGrupo = "";
		String fechaAplicacion = "";
		String idTurno = "";
		String idNivel = "";
		String tipoEvaluacion = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		
		for(CampoRptDTO filtro: lstFiltro){
			if(filtro.getClave().equals("ID_SEDE")){
				idSede = filtro.getValor();
			}
			
			if(filtro.getClave().equals("GRUPO")){
				idGrupo = filtro.getValor();
			}
			
			if(filtro.getClave().equals("FECHA_APLICACION")){
				fechaAplicacion = filtro.getValor();
			}
			
			if(filtro.getClave().equals("TURNO")){
				idTurno = filtro.getValor();
			}
			
			if(filtro.getClave().equals("NIVEL_EDUCATIVO")){
				idNivel = filtro.getValor();
			}
			
			if(filtro.getClave().equals("TIPO_EVALUACION")){
				tipoEvaluacion = filtro.getValor();
			}
		}
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			if(tipoEvaluacion.equals("1")){//ingreso
				
				sSql = "SELECT a.*, asis.bandera_asistencia "
						+"FROM "
						+"	( "
						+"		SELECT folio_federal, id_convocatoria, funcion_aspirante, nombre, primer_apellido, segundo_apellido ";
					
					if(idNivel != null && !idNivel.isEmpty()){
						if(idNivel.equals("1")){ //basica
							sSql += "		FROM aspirantes ";
						}else if(idNivel.equals("2")){//ms
							sSql += "		FROM aspirantes_ms ";
						}
					}
						
					sSql +="	WHERE id_estatus = 4 " 
						+"		AND id_sede_aplicacion = " + idSede
						+"		AND grupo = "+ idGrupo
						+"		AND TO_DATE(TO_CHAR(fecha_aplicacion,'dd/MM/yyyy'),'dd/MM/yyyy') = to_date('"+ fechaAplicacion +"','dd/MM/yyyy') "
						+"		AND funcion_aspirante = 1 "
						+"		UNION "
						+"		SELECT folio_federal, id_convocatoria, funcion_aspirante, nombre, primer_apellido, segundo_apellido ";
						
					if(idNivel != null && !idNivel.isEmpty()){
						if(idNivel.equals("1")){ //basica
							sSql += "		FROM aspirantes ";
						}else if(idNivel.equals("2")){//ms
							sSql += "		FROM aspirantes_ms ";
						}
					}
					
					sSql +="	WHERE id_estatus = 4 "
						+"		AND id_sede_aplicacion = " + idSede 
						+"		AND grupo = " + idGrupo 
						+"		AND TO_DATE(TO_CHAR(fecha_aplicacion_adicional,'dd/MM/yyyy'),'dd/MM/yyyy') = to_date('"+ fechaAplicacion +"','dd/MM/yyyy') "
						+"		AND funcion_aspirante = 1 "
						+"	) a ";
					
					if(idNivel != null && !idNivel.isEmpty()){
						if(idNivel.equals("1")){ //basica
							sSql += "LEFT OUTER JOIN  asistencias asis ";
						}else if(idNivel.equals("2")){//ms
							sSql += "LEFT OUTER JOIN  asistencias_ms asis ";
						}
					}
					
					sSql +="ON a.folio_federal = asis.folio_federal "
						+"AND a.id_convocatoria = asis.id_convocatoria "
						+"AND a.funcion_aspirante = asis.funcion_aspirante "
						+"AND TO_DATE(TO_CHAR(asis.fecha_asistencia,'dd/MM/yyyy'),'dd/MM/yyyy') = to_date('"+ fechaAplicacion +"','dd/MM/yyyy')"
						+"AND asis.turno = " + idTurno
						+" ORDER BY a.primer_apellido, a.segundo_apellido, a.nombre";
				
			}else if(tipoEvaluacion.equals("2")){//diagnostica
				
			}else if(tipoEvaluacion.equals("3")){//promocion
				sSql = "SELECT a.*, asis.bandera_asistencia "
						+"FROM "
						+"	( "
						+"		SELECT folio_federal, id_convocatoria, funcion_aspirante, nombre, primer_apellido, segundo_apellido ";
					
					if(idNivel != null && !idNivel.isEmpty()){
						if(idNivel.equals("1")){ //basica
							sSql += "		FROM aspirantes ";
						}else if(idNivel.equals("2")){//ms
							sSql += "		FROM aspirantes_ms ";
						}
					}
						
					sSql +="	WHERE id_estatus = 4 " 
						+"		AND id_sede_aplicacion = " + idSede
						+"		AND grupo = "+ idGrupo
						+"		AND TO_DATE(TO_CHAR(fecha_aplicacion,'dd/MM/yyyy'),'dd/MM/yyyy') = to_date('"+ fechaAplicacion +"','dd/MM/yyyy') "
						+"		AND funcion_aspirante <> 1 "
						+"		UNION "
						+"		SELECT folio_federal, id_convocatoria, funcion_aspirante, nombre, primer_apellido, segundo_apellido ";
						
					if(idNivel != null && !idNivel.isEmpty()){
						if(idNivel.equals("1")){ //basica
							sSql += "		FROM aspirantes ";
						}else if(idNivel.equals("2")){//ms
							sSql += "		FROM aspirantes_ms ";
						}
					}
					
					sSql +="	WHERE id_estatus = 4 "
						+"		AND id_sede_aplicacion = " + idSede 
						+"		AND grupo = " + idGrupo 
						+"		AND TO_DATE(TO_CHAR(fecha_aplicacion_adicional,'dd/MM/yyyy'),'dd/MM/yyyy') = to_date('"+ fechaAplicacion +"','dd/MM/yyyy') "
						+"		AND funcion_aspirante <> 1 "
						+"	) a ";
					
					if(idNivel != null && !idNivel.isEmpty()){
						if(idNivel.equals("1")){ //basica
							sSql += "LEFT OUTER JOIN  asistencias asis ";
						}else if(idNivel.equals("2")){//ms
							sSql += "LEFT OUTER JOIN  asistencias_ms asis ";
						}
					}
					
					sSql +="ON a.folio_federal = asis.folio_federal "
						+"AND a.id_convocatoria = asis.id_convocatoria "
						+"AND a.funcion_aspirante = asis.funcion_aspirante "
						+"AND TO_DATE(TO_CHAR(asis.fecha_asistencia,'dd/MM/yyyy'),'dd/MM/yyyy') = to_date('"+ fechaAplicacion +"','dd/MM/yyyy')"
						+"AND asis.turno = " + idTurno
						+" ORDER BY a.primer_apellido, a.segundo_apellido, a.nombre";
			}
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				AspiranteDTO aspirante = new AspiranteDTO();
				aspirante.setFolioFederal(rs.getString("folio_federal"));
				aspirante.setIdConvocatoria(rs.getString("id_convocatoria"));
				aspirante.setIdFuncion(rs.getString("funcion_aspirante"));
				aspirante.setNombre(rs.getString("nombre"));
				aspirante.setPrimerApellido(rs.getString("primer_apellido"));
				aspirante.setSegundoApellido(rs.getString("segundo_apellido"));
				
				if(rs.getString("bandera_asistencia") != null && rs.getString("bandera_asistencia")!= ""){
					if(rs.getString("bandera_asistencia").equals("true")){
						aspirante.setBndAsistencia(true);
					}else{
						aspirante.setBndAsistencia(false);
					}
				}else{
					aspirante.setBndAsistencia(false);
				}
				
				aspirantes.add(aspirante);
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el mÃƒÂ©todo getAspirantesSede de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return aspirantes;
	}


	@Override
	public List<CatalogoDTO> getGrupos(List<CampoRptDTO> lstFiltro)throws DBExcepcion, SistemaExcepcion {
		List<CatalogoDTO> grupos = new ArrayList<CatalogoDTO>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String idSede = "";
		String fechaAplicacion = "";
		String idNivel = "";
		String tipoEvaluacion = "";
		String sSql = "";
		
		try {
			Conexion cnn = new Conexion();
			cnn.conectar();
			
			for(CampoRptDTO filtro: lstFiltro){
				if(filtro.getClave().equals("ID_SEDE")){
					idSede = filtro.getValor();
				}
				
				if(filtro.getClave().equals("FECHA_APLICACION")){
					fechaAplicacion = filtro.getValor();
				}
				
				if(filtro.getClave().equals("NIVEL_EDUCATIVO")){
					idNivel = filtro.getValor();
				}
				
				if(filtro.getClave().equals("TIPO_EVALUACION")){
					tipoEvaluacion = filtro.getValor();
				}
			}
			
			if(tipoEvaluacion.equals("1")){//ingreso
				if(idNivel != null && !idNivel.isEmpty()){
					if(idNivel.equals("1")){ //basica
						sSql = "SELECT DISTINCT(grupo) grupo FROM aspirantes ";
					}else if(idNivel.equals("2")){//ms
						sSql = "SELECT DISTINCT(grupo) grupo FROM aspirantes_ms ";
					}
				}
				
				sSql +=" WHERE id_sede_aplicacion = " + idSede 
					+" AND TO_DATE(TO_CHAR(fecha_aplicacion,'dd/MM/yyyy'),'dd/MM/yyyy') = TO_DATE('"+ fechaAplicacion +"','dd/MM/yyyy') "
					+" AND funcion_aspirante = 1 ";
				
				sSql +="UNION ";
				
				if(idNivel != null && !idNivel.isEmpty()){
					if(idNivel.equals("1")){ //basica
						sSql += "SELECT DISTINCT(grupo) grupo FROM aspirantes ";
					}else if(idNivel.equals("2")){//ms
						sSql += "SELECT DISTINCT(grupo) grupo FROM aspirantes_ms ";
					}
				}
				
				sSql += "WHERE id_sede_aplicacion = " + idSede 
					+" AND TO_DATE(TO_CHAR(fecha_aplicacion_adicional,'dd/MM/yyyy'),'dd/MM/yyyy') = TO_DATE('"+ fechaAplicacion +"','dd/MM/yyyy') "
					+" AND funcion_aspirante = 1 "
					+"ORDER BY grupo";
				
			}else if(tipoEvaluacion.equals("2")){//diagnostica  ajustar la consulta de grupos
				
			}else if(tipoEvaluacion.equals("3")){//promocion
				if(idNivel != null && !idNivel.isEmpty()){
					if(idNivel.equals("1")){ //basica
						sSql = "SELECT DISTINCT(grupo) grupo FROM aspirantes ";
					}else if(idNivel.equals("2")){//ms
						sSql = "SELECT DISTINCT(grupo) grupo FROM aspirantes_ms ";
					}
				}
				
				sSql +=" WHERE id_sede_aplicacion = " + idSede 
					+" AND TO_DATE(TO_CHAR(fecha_aplicacion,'dd/MM/yyyy'),'dd/MM/yyyy') = TO_DATE('"+ fechaAplicacion +"','dd/MM/yyyy') "
					+" AND funcion_aspirante <> 1 ";
				
				sSql +="UNION ";
				
				if(idNivel != null && !idNivel.isEmpty()){
					if(idNivel.equals("1")){ //basica
						sSql += "SELECT DISTINCT(grupo) grupo FROM aspirantes ";
					}else if(idNivel.equals("2")){//ms
						sSql += "SELECT DISTINCT(grupo) grupo FROM aspirantes_ms ";
					}
				}
				
				sSql += "WHERE id_sede_aplicacion = " + idSede 
					+" AND TO_DATE(TO_CHAR(fecha_aplicacion_adicional,'dd/MM/yyyy'),'dd/MM/yyyy') = TO_DATE('"+ fechaAplicacion +"','dd/MM/yyyy') "
					+" AND funcion_aspirante <> 1 "
					+"ORDER BY grupo";
			}
			
			ps = cnn.prepareStatement(sSql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				CatalogoDTO grupo = new CatalogoDTO();
				grupo.setIdItem(rs.getString("grupo"));
				grupo.setDescItem("grupo " + rs.getString("grupo"));
				grupos.add(grupo);
			}
			
			rs.close();
			ps.close();
			cnn.desconectar();
		} catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el metodo getGrupos de AspiranteDaoImpl:\n" + sqle.getMessage(), sqle);
		}
		
		return grupos;
	}


	@Override
	public AspiranteDTO getAspCambioSede(String folioFederal,
			String idConvocatoria, String idFuncion) throws DBExcepcion,
			SistemaExcepcion {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String actualizarSede(AspiranteDTO aspirante) throws DBExcepcion,
			SistemaExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

}
