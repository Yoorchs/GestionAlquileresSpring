package com.ipartek.formacion.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Inquilino;
import com.ipartek.formacion.service.utils.Utils;

/**
 *
 * @author Jorge
 * Clase que implementa validaciones para la clase <code>Iinquilino
 *
 */

public class InquilinoValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Inquilino.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "500", "Tiene que introducir un DNI");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "501", "Tiene que introducir un email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "502", "Tiene que introducir un telefono");

		Inquilino inq = (Inquilino) obj;
		if (inq.getCodigo() < Inquilino.CODIGO_NULO) {
			errors.rejectValue("codigo", "valorNegativo", new Object[] { "'codigo'" },
					"El codigo no puede ser menor que " + Inquilino.CODIGO_NULO);
		}
		if (!Utils.ValidarDNI(inq.getDni())) {
			errors.rejectValue("dni", "dni.character", new Object[] { "'dni'" },
					"La letra del DNI introducida es incorrecta");
		}
	}
}
