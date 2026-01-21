package br.com.padariaweb.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.padariaweb.util.Util;

@FacesConverter("conversorData")
public class ConversorData implements Converter {

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     * @throws ConverterException
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
       
    	if(Util.isValido(value)){
    		SimpleDateFormat dateCompleta = new SimpleDateFormat("dd/MM/yyyy");
    		SimpleDateFormat dateMes = new SimpleDateFormat("MM/yyyy");
    		dateCompleta.setLenient(false);
    		dateMes.setLenient(false);
    		Date data = null;
    		try {
    			data = dateCompleta.parse(value);
    		} catch (ParseException e) {
    			try {
					data = dateMes.parse(value);
				} catch (ParseException e1) {
					FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message_" + FacesMessage.SEVERITY_INFO.toString().toLowerCase().substring(0,4), "Falha na conversão da Data");
					throw new ConverterException();
				}
    			
    		}
    		return data;
    	}
    	return null;
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return
     * @throws ConverterException
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        if(Util.isValido(value)){
        	
        	return new SimpleDateFormat("dd/MM/yyyy").format(((Date)value)); 
        }

        return "";
    }
}