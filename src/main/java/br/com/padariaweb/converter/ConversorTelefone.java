package br.com.padariaweb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("conversorTelefone")
public class ConversorTelefone implements Converter {

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
        // Convertendo Telefone com mascara (11) 1111-1111
        // em Telfone sem mascara (1111111111).
        String telefone = value;
        if (value != null && !value.equals("")) {
            telefone = value.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll(" ", "").replaceAll("\\(", "").replaceAll("\\)", "");
        }
        return telefone;
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
        // Converter Telefone sem mascara (1111111111)
        // em Telefone com mascara (11) 1111-1111
        String telefone = (String) value;
        if (telefone != null && telefone.length() == 10) {
            telefone = "("+telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-" + telefone.substring(6, 10);
        }else if(telefone != null && telefone.length() == 11){
        	telefone = "("+telefone.substring(0, 2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7, 11);
        }

        return telefone;
    }
}