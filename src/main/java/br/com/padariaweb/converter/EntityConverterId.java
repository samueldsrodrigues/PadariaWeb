package br.com.padariaweb.converter;

import java.lang.reflect.Field;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.Id;

/**
 * Conversor de entidades para as telas do JSF.
 * 
 */
@FacesConverter("entityConverterId")
public class EntityConverterId implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null) {
			return component.getAttributes().get(value);
		}
		return null;
	}

	public String getAsString(FacesContext ctx, UIComponent component, Object obj) {
		if (obj != null && !"".equals(obj)) {
			try {
				String id = this.getId(getClazz(ctx, component), obj);
				if (id == null) {
					id = "";
				}
				id = id.trim();
				component.getAttributes().put(id, obj);
				return id;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private Class<?> getClazz(FacesContext facesContext, UIComponent component) {
		return component.getValueExpression("value").getType(facesContext.getELContext());
	}

	public String getId(Class<?> clazz, Object obj) throws Exception {
		for (Field field : clazz.getDeclaredFields()) {
			if ((field.getAnnotation(Id.class)) != null) {
				Field privateField = clazz.getDeclaredField(field.getName());
				privateField.setAccessible(true);
				if (privateField.get(clazz.cast(obj)) != null) {
					return field.getType()
							.cast(privateField.get(clazz.cast(obj))).toString();
				} else {
					return null;
				}
			}
		}
		Class<?> classe = obj.getClass();
		for (Field field : classe.getDeclaredFields()) {
			if ((field.getAnnotation(Id.class)) != null) {
				Field privateField = classe.getDeclaredField(field.getName());
				privateField.setAccessible(true);
				if (privateField.get(classe.cast(obj)) != null) {
					return field.getType().cast(privateField.get(classe.cast(obj))).toString();
				} 
				return null;
			}
		}
		return null;
	}
}
