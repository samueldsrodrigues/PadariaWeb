package br.com.padariaweb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("conversorToUpperCase")
public class ConversorToUpperCase implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return (arg2.equals(null)) ? null : arg2.toUpperCase();
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return (arg2.equals(null)) ? "" : arg2.toString().toUpperCase();
	}

}
