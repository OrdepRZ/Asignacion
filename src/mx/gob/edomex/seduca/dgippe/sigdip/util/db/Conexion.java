package mx.gob.edomex.seduca.dgippe.sigdip.util.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
public class Conexion {
	Connection conexion;
	Statement statement;
	PreparedStatement ps;
	CallableStatement cs;
	
	public void conectar () throws DBExcepcion {
		try {

			InitialContext context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/seirDS");
			//DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/sigdipDS");
			conexion = dataSource.getConnection();
			
		} catch (Exception se) {
			se.printStackTrace();
			throw new DBExcepcion("Error en el método conectar() de mx.gob.edomex.seduca.dgippe.sigdip.util.db:\n" + se.getMessage(), se);
		} 
	}

	public Connection getConexion() {
		return conexion;
	}
	
	public PreparedStatement prepareStatement(String string) throws DBExcepcion {
		try{
			ps = conexion.prepareStatement(string);
		} catch (Exception se) {
			se.printStackTrace();
			throw new DBExcepcion("Error en el método prepareStatement() de mx.gob.edomex.seduca.dgippe.sigdip.util.db:\n" + se.getMessage(), se);
		}
		return ps;
	}
	
	public CallableStatement prepareCall(String string) throws DBExcepcion {
		try {
			cs = conexion.prepareCall(string);
		} catch (Exception se) {
			se.printStackTrace();
			throw new DBExcepcion("Error en el método prepareCall() de mx.gob.edomex.seduca.dgippe.sigdip.util.db:\n" + se.getMessage(), se);
		}
		return cs;
	}
	
	public Statement createStatement () throws DBExcepcion {
		try {
			statement = conexion.createStatement();			
		} catch (Exception se ) {
			se.printStackTrace();
			throw new DBExcepcion("Error en el método createStatement() de mx.gob.edomex.seduca.dgippe.sigdip.util.db:\n" + se.getMessage(), se);
		}
		return statement;
	}
	
	public void desconectar () throws DBExcepcion {
		try {
			conexion.close();
		} catch (Exception se) {
			se.printStackTrace();
			throw new DBExcepcion("Error en el método desconectar() de mx.gob.edomex.seduca.dgippe.sigdip.util.db:\n" + se.getMessage(), se);
		}
	}	
}
