package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao;

import java.sql.SQLException;
import java.util.List;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.AspiranteDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dto.CitaDTO;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;
/**
 * Clase que contiene la firma de los metodos de cita
 * @author Administrador
 *
 */
public interface CitaDAO 
{
	//Metodos para basica
	public List<AspiranteDTO> consultaFolio(String folio) throws DBExcepcion, SistemaExcepcion, SQLException;
	public int insertaCita(String folio, String fecha, String curp) throws DBExcepcion, SistemaExcepcion, SQLException;
	public CitaDTO consultaCita(String folio, String curp) throws DBExcepcion, SistemaExcepcion, SQLException;
	public List<AspiranteDTO> consultaCitas(String fecha,String funcion) throws DBExcepcion, SistemaExcepcion, SQLException;
	public int actualizaCita(String folio, String fecha, String curp) throws DBExcepcion, SistemaExcepcion, SQLException; 
	//Metodos para media superior
	public  List<AspiranteDTO> consultaFolioMS(String folio) throws DBExcepcion, SistemaExcepcion, SQLException;
	public List<AspiranteDTO> consultaCitasMS(String fecha, String subsistema) throws DBExcepcion, SistemaExcepcion, SQLException;

}