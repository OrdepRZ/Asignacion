package mx.gob.edomex.seduca.dgippe.sigdip.util.convertidor;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import mx.gob.edomex.seduca.dgippe.sigdip.comun.dto.CampoRptDTO;

@FacesConverter("convertidorCampoRpt")
public class ConvertidorCampoRpt implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		//System.out.println("String value: {"+ value + "}");
		
		return getObjectFromUIPickListComponent(component,value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		// TODO Auto-generated method stub
		String string;
		
		//System.out.println("Object value: {" + object +"}");
		
		if (object == null) {
			string = "";
		} else {
			try {
				string = String.valueOf(((CampoRptDTO)object).getId());
				//string = String.valueOf(((CampoRptDTO)object).getClave());
			}catch(ClassCastException cce){
				// TODO: handle exception
				throw new ConverterException();
			}
		}
		return string;
	}
	
	@SuppressWarnings("unchecked")
	private CampoRptDTO getObjectFromUIPickListComponent(UIComponent component, String value) {
		final DualListModel<CampoRptDTO> dualList;
		try {
			dualList = (DualListModel<CampoRptDTO>) ((PickList)component).getValue();
			CampoRptDTO campo = getObjectFromList(dualList.getSource(),Integer.valueOf(value));
			if(campo == null){
				campo = getObjectFromList(dualList.getTarget(),Integer.valueOf(value));
			}
			return campo;
		}catch(ClassCastException cce){
			throw new ConverterException();
		}catch(NumberFormatException nfe){
			throw new ConverterException();
		}
	}
	
	private CampoRptDTO getObjectFromList(List<?> list, Integer identifier) {
		for(final Object object:list) {
			final CampoRptDTO campo = (CampoRptDTO) object;
			if (campo.getId().equals(identifier)) {
				return campo;
			}
		}
		return null;
	}
}
