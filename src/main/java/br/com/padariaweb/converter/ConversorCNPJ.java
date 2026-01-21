package br.com.padariaweb.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("conversorCNPJ")
public class ConversorCNPJ implements Converter {

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
        // Convertendo CPF com mascara (111.111.111-11)
        // em CPF sem mascara (11111111111).
        String cnpj = value;
        if (value != null && !value.equals("")) {
            cnpj = value.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("\\/", "");
        }
        return cnpj;
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
        // Converter CPF sem m�scara (11111111111)
        // em CPF com mascara (111.111.111-11)
        String cnpj = (String) value;
        if (cnpj != null && cnpj.length() == 11) {
            cnpj = cnpj.substring(0, 3) + "." + cnpj.substring(3, 6) + "." + cnpj.substring(6, 9) + "-" + cnpj.substring(9, 11);
        }

        return cnpj;
    }
}