package mx.gob.edomex.seduca.dgippe.sigdip.catalogo.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dto.CatalogoDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.catalogo.dao.CatalogoDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.db.Conexion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

public class CatalogoDaoImpl implements CatalogoDAO {

	public List<CatalogoDTO> getCatalogoItems(String nomCatalogo)throws DBExcepcion, SistemaExcepcion{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		List<CatalogoDTO> catalogoItems = new ArrayList<CatalogoDTO>(); 
		
		try {
			Conexion conn = new Conexion();
			conn.conectar();
			
			sSql = "SELECT "
					+ " ID_CATALOGO, ID_CAT_PADRE, NOM_CAT, ID_ITEM, ID_ITEM || ' - ' || DESC_ITEM DESC_ITEM, DESC_ITEM DESCRIPCION, B_DESHABILITADO, B_BAJA "
					+ " FROM catalogos "
					+ " WHERE nom_cat = '"+ nomCatalogo +"' AND id_item <> 0" 
					+ " AND B_DESHABILITADO = 'F' AND B_BAJA = 'F'"
					+ " ORDER BY ID_ITEM ";
			
			ps = conn.prepareStatement(sSql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				CatalogoDTO item = new CatalogoDTO();
				item.setIdCatalogo(rs.getString("id_catalogo"));
				item.setNomCatalogo(rs.getString("nom_cat"));
				item.setIdItem(rs.getString("id_item"));
				item.setDescItem(rs.getString("desc_item"));
				item.setDescripcion(rs.getString("descripcion"));
				item.setbDeshabilitado(rs.getString("b_deshabilitado"));
				item.setbBaja(rs.getString("b_baja"));
				
				catalogoItems.add(item);
			}
			
			rs.close();
			ps.close();
			conn.desconectar();
			
		}catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getCatalogoItems() de CatalogoDaoImpl:\n" + sqle.getMessage(), sqle);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return catalogoItems;
	}
	
	public List<CatalogoDTO> getCatalogoSubItems(String nomCatalogo, String idCatPadre)throws DBExcepcion, SistemaExcepcion {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		List<CatalogoDTO> catalogoItems = new ArrayList<CatalogoDTO>(); 
		
		try {
			Conexion conn = new Conexion();
			conn.conectar();
			
			sSql = "SELECT  " 
					+ " ID_CATALOGO, ID_CAT_PADRE, NOM_CAT, ID_ITEM, ID_ITEM || ' - ' || DESC_ITEM DESC_ITEM, DESC_ITEM DESCRIPCION, B_DESHABILITADO, B_BAJA "
					+ " FROM catalogos " 
					+ " WHERE nom_cat = '"+ nomCatalogo +"' AND id_cat_padre = "+ idCatPadre
					+ " AND B_DESHABILITADO = 'F' AND B_BAJA = 'F'"
					+ "  ORDER BY ID_ITEM ";
			
			ps = conn.prepareStatement(sSql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				CatalogoDTO item = new CatalogoDTO();
				item.setIdCatalogo(rs.getString("id_catalogo"));
				item.setNomCatalogo(rs.getString("nom_cat"));
				item.setIdItem(rs.getString("id_item"));
				item.setDescItem(rs.getString("desc_item"));
				item.setDescripcion(rs.getString("descripcion"));
				item.setbDeshabilitado(rs.getString("b_deshabilitado"));
				item.setbBaja(rs.getString("b_baja"));
				
				catalogoItems.add(item);
			}
			
			rs.close();
			ps.close();
			conn.desconectar();
			
		}catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getCatalogoSubItems() de CatalogoDaoImpl:\n" + sqle.getMessage(), sqle);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return catalogoItems;
	}

	@Override
	public List<CatalogoDTO> getCatalogo(List<CampoRptDTO> lstFiltro) throws DBExcepcion, SistemaExcepcion {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		List<CatalogoDTO> catalogoItems = new ArrayList<CatalogoDTO>(); 
		
		try {
			Conexion conn = new Conexion();
			conn.conectar();
			
			sSql = "SELECT "
				 + " ID_CATALOGO, ID_CAT_PADRE, NOM_CAT, ID_ITEM, ID_ITEM || ' - ' || DESC_ITEM DESC_ITEM, DESC_ITEM DESCRIPCION, B_DESHABILITADO, B_BAJA "
				 + " FROM CATALOGOS "
				 + " WHERE B_DESHABILITADO = 'F' AND B_BAJA = 'F' AND ID_ITEM <> 0 "; 

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

					if (clave.equals("NOM_CAT")) {
						sSql += " NOM_CAT = '" + valor.trim() + "'";
					}

					if (clave.equals("ID_IGUAL_A")) {
						sSql += " ID_ITEM = " + valor.trim();
					}
					
					if (clave.equals("ID_DIFERENTE_DE")) {
						sSql += " ID_ITEM <> " + valor.trim();
					}

					if (clave.equals("ID_MENOR_IGUAL_QUE")) {
						sSql += " ID_ITEM <= " + valor.trim();
					}

					if (clave.equals("ID_MAYOR_IGUAL_QUE")) {
						sSql += " ID_ITEM >= " + valor.trim();
					}

					if (clave.equals("DESC_LIKE")) {
						sSql += " UPPER(DESC_ITEM) LIKE '%" + valor.trim() + "%'";
					}
					
					ctdFiltro++;
				}
			}
			
			sSql += " ORDER BY ID_ITEM ";
			
			//System.out.println(sSql);
			
			ps = conn.prepareStatement(sSql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				CatalogoDTO item = new CatalogoDTO();
				item.setIdCatalogo(rs.getString("id_catalogo"));
				item.setNomCatalogo(rs.getString("nom_cat"));
				item.setIdItem(rs.getString("id_item"));
				item.setDescItem(rs.getString("desc_item"));
				item.setDescripcion(rs.getString("descripcion"));
				item.setbDeshabilitado(rs.getString("b_deshabilitado"));
				item.setbBaja(rs.getString("b_baja"));
				
				catalogoItems.add(item);
			}
			
			rs.close();
			ps.close();
			conn.desconectar();
			
		}catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getCatalogo() de CatalogoDaoImpl:\n" + sqle.getMessage(), sqle);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return catalogoItems;

	}
		
	/*------------------------------------AGREGADO------------------------------------------*/
	public List<CatalogoDTO> getCatalogoFormatoAsign()throws DBExcepcion, SistemaExcepcion{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sSql = "";
		List<CatalogoDTO> catalogoItems = new ArrayList<CatalogoDTO>(); 
		
		try {
			Conexion conn = new Conexion();
			conn.conectar();
			
			sSql = "SELECT DISTINCT"
					+ " ID_FORMATO, ID_FORMATO || ' - ' || DESC_FORMATO DESC_FORMATO"
					+ " FROM formato_asignacion "
					+ " ORDER BY ID_FORMATO ";
			
			ps = conn.prepareStatement(sSql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				CatalogoDTO item = new CatalogoDTO();
				item.setIdCatalogo("");
				item.setNomCatalogo("");
				item.setIdItem(rs.getString("id_formato"));
				item.setDescItem(rs.getString("desc_formato"));
				item.setDescripcion("");
				item.setbDeshabilitado("");
				item.setbBaja("");
				
				catalogoItems.add(item);
			}
			
			rs.close();
			ps.close();
			conn.desconectar();
			
		}catch (DBExcepcion dbe) {
			throw dbe;
		} catch (SQLException sqle) {
			throw new SistemaExcepcion("Error en el método getCataloFormatoAsign() de CatalogoDaoImpl:\n" + sqle.getMessage(), sqle);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return catalogoItems;
	}
	
	/*-----------------------------------------------------------------------------------------------------------------------------------*/
}
