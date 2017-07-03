package mx.gob.edomex.seduca.dgippe.sigdip.asignacion.bean;

import java.io.Serializable;

import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.dao.GraficoDAO;
import mx.gob.edomex.seduca.dgippe.sigdip.asignacion.impl.GraficoDaoImpl;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.DBExcepcion;
import mx.gob.edomex.seduca.dgippe.sigdip.util.excepcion.SistemaExcepcion;

//import org.json.simple.JSONObject;

public class AsignacionReportesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private JSONObject series;
	private String resultado;

	public AsignacionReportesBean(){
		//series = new JSONObject();
		resultado = "";
	}
	
	public String graflinallesfed(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		//GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.graflinallesfed();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String graflinallestal(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.graflinallestal();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public String grafdos(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafdos();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public String graftres(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.graftres();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafcanceladas(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafcanceladas();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafcuatro(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafcuatro();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafcinco(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafcinco();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafcanceladasestatal(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafcanceladasestatal();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafseis(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafseis();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafsiete(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafsiete();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafcanceladasfederal(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafcanceladasfederal();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafregunoest(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafregunoest();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public String grafregunofed(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafregunofed();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafregionuno(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafregionuno();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafregionunodos(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafregionunodos();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	public String grafcho(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
			resultado = vacanteDAO.grafcho();
		} catch (DBExcepcion e) {
			e.printStackTrace();
		} catch (SistemaExcepcion e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public String  queryreg0103 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0103();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0104 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0104();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;	
		 }
		public String  queryreg0105 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0105();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0106 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0106();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0107 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0107();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0108 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0108();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0203 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0203();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0204 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0204();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0205 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0205();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0206 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0206();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0207 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0207();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0208 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0208();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0303 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0303();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0304 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0304();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0305 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0305();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0306 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0306();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0307 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0307();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0308 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0308();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0403 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0403();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0404 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0404();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0405 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0405();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0406 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0406();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0407 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0407();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0408 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0408();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0503 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0503();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0504 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0504();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0505 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0505();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0506 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0506();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0507 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0507();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0508 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0508();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0603 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0603();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0604 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0604();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0605 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0605();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0606 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0606();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0607 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0607();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0608 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0608();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0703 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0703();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0704 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0704();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0705 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0705();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0706 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0706();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0707 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0707();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0708 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0708();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0803 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0803();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0804 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0804();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0805 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0805();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0806 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0806();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0807 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0807();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0808 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0808();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0903 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0903();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0904 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0904();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0905 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0905();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0906 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0906();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0907 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0907();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0908 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg0908();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1003 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1003();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1004 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1004();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1005 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1005();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1006 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1006();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1007 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1007();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1008 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1008();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1103 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1103();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1104 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1104();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1105 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1105();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1106 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1106();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1107 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1107();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1108 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1108();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1203 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1203();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1204 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1204();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1205 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1205();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1206 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1206();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1207 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1207();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1208 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1208();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1303 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1303();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1304 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1304();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1305 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1305();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1306 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1306();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1307 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1307();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1308 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1308();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1403 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1403();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1404 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1404();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1405 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1405();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1406 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1406();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1407 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1407();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1408 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1408();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1503 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1503();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1504 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1504();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1505 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1505();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1506 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1506();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1507 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1507();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1508 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1508();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1603 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1603();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1604 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1604();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1605 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1605();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1606 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1606();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1607 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1607();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg1608 (){
		  GraficoDAO vacanteDAO = new GraficoDaoImpl();
		  try {
		   resultado = vacanteDAO.queryreg1608();
		  } catch (DBExcepcion e) {
		   e.printStackTrace();
		  } catch (SistemaExcepcion e) {
		   e.printStackTrace();
		  }
		  return resultado;
		 }
		public String  queryreg0101(){
			  GraficoDAO vacanteDAO = new GraficoDaoImpl();
			  try {
			   resultado = vacanteDAO.queryreg0101();
			  } catch (DBExcepcion e) {
			   e.printStackTrace();
			  } catch (SistemaExcepcion e) {
			   e.printStackTrace();
			  }
			  return resultado;
			 }
		public String  queryreg0102(){
			  GraficoDAO vacanteDAO = new GraficoDaoImpl();
			  try {
			   resultado = vacanteDAO.queryreg0102();
			  } catch (DBExcepcion e) {
			   e.printStackTrace();
			  } catch (SistemaExcepcion e) {
			   e.printStackTrace();
			  }
			  return resultado;
			 }
		public String  queryreg0201(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0201();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0202(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0202();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0301(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0301();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0302(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0302();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0401(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0401();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0402(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0402();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0501(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0501();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0502(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0502();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0601(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0601();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0602(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0602();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0701(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0701();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0702(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0702();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0801(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0801();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0802(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0802();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0901(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0901();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg0902(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg0902();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1001(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1001();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1002(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1002();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1101(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1101();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1102(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1102();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1201(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1201();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1202(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1202();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1301(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1301();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1302(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1302();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1401(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1401();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1402(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1402();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1501(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1501();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1502(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1502();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1601(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1601();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String  queryreg1602(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1602();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		
		public String  queryreg1701(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1701();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String queryreg1701D(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryreg1701D();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String queryregallfed(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryregallfed();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
		public String queryregallest(){
		GraficoDAO vacanteDAO = new GraficoDaoImpl();
		try {
		resultado = vacanteDAO.queryregallest();
		} catch (DBExcepcion e) {
		 e.printStackTrace();
		} catch (SistemaExcepcion e) {
		e.printStackTrace();
		}
		return resultado;
		}
}