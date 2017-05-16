package com.ipartek.formacion.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Piso;


/**
*
* @author Jorge
* Clase que implementa validaciones para la clase <code>Piso
*
*/
public class PisoValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Piso.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion", "504", "Tiene que introducir una direccion");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precionoche", "505", "Tiene que introducir precio de alquiler");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "referenciacatastral", "506", "Tiene que introducir una referencia catastral");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "superficie", "507", "Tiene que introducir la superficie");

		Piso p = (Piso) obj;
		if (p.getCodigo() < Piso.CODIGO_NULO) {
			errors.rejectValue("codigo", "valorNegativo", new Object[] { "'codigo'" },
					"El codigo no puede ser menor que " + Piso.CODIGO_NULO);
		}
		
		if (p.getSuperficie()<=0){
			errors.rejectValue("superficie", "valorCero", new Object[] { "'superficie'" },
					"La superficie debe ser un valor mayor que 0");
		}
		
		if (p.getPrecionoche()<=0){
			errors.rejectValue("precionoche", "valorCero", new Object[] { "'precionoche'" },
					"El precio debe ser un valor mayor que 0");
		}
	}

}
