package mx.gob.edomex.seduca.dgippe.sigdip.util.validador;

import java.io.Serializable;
import java.lang.NumberFormatException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validador implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public boolean isLetrasNumeros(String cadena) {
	
		Pattern patron = Pattern.compile("[A-Z0-9]+");
		Matcher comparador = patron.matcher(cadena);
		
		if (!comparador.matches()) {
			return false;
		}
		
		return true;
	}

	public boolean isNumeros(String cadena) {
		
		Pattern patron = Pattern.compile("[0-9]+");
		Matcher comparador = patron.matcher(cadena);
		
		if (!comparador.matches()) {
			return false;
		}
		
		return true;
	}

	public boolean isEntero(String entero) {
		
		try {
			@SuppressWarnings("unused")
			int  iValor = Integer.parseInt(entero);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean isDouble(String doble) {
		
		try {
			//Si no es válido lanzará NumberFormatException
			@SuppressWarnings("unused")
			double dValor = Double.parseDouble(doble);
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean isFecha(String fecha, String formato) {
		if(fecha == null){
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		sdf.setLenient(false);
		
		try{
			//Si no es válida, lanzará ParseException
			@SuppressWarnings("unused")
			Date date = sdf.parse(fecha);
			//System.out.println(date);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public String formatearCantidad(String strNumero) {
		String resultado = "0.00";
		
		try {
			NumberFormat formatter = new DecimalFormat("#,###,###,##0.00");
			double dCantidad = Double.parseDouble(strNumero);
			resultado = formatter.format(dCantidad);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return resultado;
	}
	
	public boolean isPassword(String password) {
		/*
		 * (					# Start of group
		 * 		(?=.*\d)		#   must contains one digit from 0-9
		 * 		(?=.*[a-z])		#   must contains one lowercase characters
		 * 		(?=.*[A-Z])		#   must contains one uppercase characters
		 *		(?=.*[@#$%])	#   must contains one special symbols in the list "@#$%"
		 * 				.		#   match anything with previous condition checking
		 * 				{8,15}	#   length at least 8 characters and maximum of 15
		 * )					# End of group
		 * */		
		boolean resultado = false;
		
		//String sPatron = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
		String sPatron = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15})";
		
		Pattern patron = Pattern.compile(sPatron);
		Matcher comparador = patron.matcher(password);
		
		resultado = comparador.matches();
		
		return resultado;
	}
	
	public boolean isEmail(String email) {
		boolean resultado = false;
		
		String sPatron = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern patron = Pattern.compile(sPatron);
		Matcher comparador = patron.matcher(email);
		
		resultado = comparador.matches();
		
		return resultado;
	}
}
