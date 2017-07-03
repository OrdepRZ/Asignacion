package mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion;

public class SistemaExcepcion extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SistemaExcepcion(String mensaje) {
		super(mensaje);
	}
	
	public SistemaExcepcion(String mensaje, Throwable lanzable) {
		super(mensaje, lanzable);
	}

}
