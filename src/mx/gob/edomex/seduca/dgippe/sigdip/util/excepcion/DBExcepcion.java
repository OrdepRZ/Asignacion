package mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion;

public class DBExcepcion extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DBExcepcion(String mensaje) {
		super(mensaje);
	}
	
	public DBExcepcion(String mensaje, Throwable lanzable) {
		super(mensaje, lanzable);
	}

}
