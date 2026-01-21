package br.com.padariaweb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("conversorCEP")
public class ConversorCEP implements Converter {

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
        // Convertendo CEP com mascara (11.111-111)
        // em CPF sem mascara (11111111).
        String cep = value;
        if (value != null && !value.equals("")) {
            cep = value.replaceAll("\\.", "").replaceAll("\\-", "");
        }
        return cep;
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
        // Converter CEP sem mascara (11111111)
        // em CPF com mascara (11.111-111)
        String cep = (String) value;
        if (cep != null && cep.length() == 8) {
            cep = cep.substring(0, 2) + "." + cep.substring(2, 5) + "-" + cep.substring(5, 8);
        }

        return cep;
    }
}