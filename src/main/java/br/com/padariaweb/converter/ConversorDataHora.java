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

@FacesConverter("conversorDataHora")
public class ConversorDataHora implements Converter {

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
    		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    		date.setLenient(false);
    		Date data = null;
    		try {
    			data = date.parse(value);
    		} catch (ParseException e) {
    			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message_" + FacesMessage.SEVERITY_INFO.toString().toLowerCase().substring(0,4), "A Data informada &eacute; inv&aacute;lida! Insira uma data conforme padr&atilde;o: dd/mm/aaaa");
    			throw new ConverterException();
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
        	return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(value); 
        }

        return "";
    }
}