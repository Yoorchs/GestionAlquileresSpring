package com.ipartek.formacion.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Propietario;
import com.ipartek.formacion.service.utils.Utils;


/**
*
* @author Jorge
* Clase que implementa validaciones para la clase <code>Propietario
*
*/
public class PropietarioValidator implements Validator{


		@Override
		public boolean supports(Class<?> arg0) {
			return Propietario.class.equals(arg0);
		}

		@Override
		public void validate(Object obj, Errors errors) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "500", "Tiene que introducir un DNI");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "501", "Tiene que introducir un email");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "502", "Tiene que introducir un telefono");

			Propietario prop = (Propietario) obj;
			if (prop.getCodigo() < Propietario.CODIGO_NULO) {
				errors.rejectValue("codigo", "valorNegativo", new Object[] { "'codigo'" },
						"El codigo no puede ser menor que " + Propietario.CODIGO_NULO);
			}
			if (!Utils.ValidarDNI(prop.getDni())) {
				errors.rejectValue("dni", "dni.letra", new Object[] { "'dni'" },
						"El DNI introducido es incorrecto");
			}
			
			if (!Utils.validarNIF(prop.getNss())){
				errors.rejectValue("nss", "nif.incorrecto", new Object[]{"'nss'"}, "El NIF no es correcto");
			}
		}
	}

