package br.com.padariaweb.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("conversorMoeda")
public class ConversorMoeda implements Converter {

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
//        String telefone = value;
//        if (value != null && !value.equals("")) {
//            telefone = value.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "");
//        }
        return value;
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
        @SuppressWarnings("unused")
		BigDecimal moeda = (BigDecimal) value;
        String retorno = "";
//        if (moeda != null) {
//            moeda = "("+moeda.substring(0, 2) + ") " + moeda.substring(2, 6) + "-" + moeda.substring(6, 10);
//        }

        return retorno;
    }
}