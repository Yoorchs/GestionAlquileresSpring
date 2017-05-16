package com.ipartek.formacion.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Propietario;
import com.ipartek.formacion.service.interfaces.PropietarioService;

/**
 * 
 * @author Jorge
 * Clase que controla las peticiones al controlador de la clase <code>Propietario
 *
 */

@Controller
@RequestMapping(value = "propietarios")
public class PropietarioController {

	@Inject
	PropietarioService prS;
	ModelAndView mav = null;
	private static final Logger logger = LoggerFactory.getLogger(PisoController.class);
	@Resource(name = "propietarioValidator")
	private Validator validator = null;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), false, 10));
	}

	/**
	 * Metodo que devuelve el listado de Propietarios. 
	 * @return mav. Objeto ModelAndView con el listado y la peticion a la URL correspondiente
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		mav = new ModelAndView("propietarios");
		logger.info("En getAll() de propietarios");
		mav.addObject("propietarios", prS.getAll());
		return mav;
	}

	/**
	 * Metodo para obtener un Propietario definido por el parametro ID. Devuelve on objeto Propietario y la redireccion a la URL correspondiente.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getByID(@PathVariable int id) {
		mav = new ModelAndView("propietario");
		logger.info("En getByID() de propietarios");
		mav.addObject("propietario", prS.getInforme(id));
		return mav;
	}

	/**
	 * Metodo que Guarda los datos de un objeto Propietario. Si el codigo es mayor que nulo, se tratara de una edicion, en caso contrario, se creara dicho objeto.
	 * @param propietario
	 * @param bindingresult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePropietario(@ModelAttribute("propietario") @Validated Propietario propietario,
			BindingResult bindingresult, Model model) {
		String destino = "";
		if (bindingresult.hasErrors()) {
			logger.info("Errores al crear el propietario");
			destino = "propietario";
		} else {
			if (propietario.getCodigo() > Propietario.CODIGO_NULO) {
				logger.info("Editando propietario: " + propietario.toString());
				prS.update(propietario);
				destino = "redirect:/propietarios";
			} else {
				prS.create(propietario);
				logger.info("Nuevo propietario: " + propietario.toString());
				destino = "redirect:/propietarios";
			}
		}
		return destino;
	}

	/**
	 * Metodo que realiza lapeticion a la URL con el formulario para crear un nuevo Propietario
	 * @return
	 */
	@RequestMapping(value = "/addPropietario")
	public ModelAndView addPropietario() {
		mav = new ModelAndView("propietario");
		mav.addObject("propietario", new Propietario());
		return mav;
	}

	/**
	 * Metodo para realizar el borrado de in Propietario de la BBDD. Recibe el codigo del Inquilino a borrrar. Devuelve la peticion a la URL tras el borrado
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "deletePropietario/{codigo}")
	public String delete(@PathVariable("codigo") int codigo) {
		logger.info("En delete() de propietarios con el codigo " + codigo);
		prS.delete(codigo);
		return "redirect:/propietarios";
	}
	
	/**
	 * Metodo que realiza la peticion a la URL donde se muestran los detalles de un objeto Propietario y el listado de Pisos de los que dispone.
	 * @param idPropietario
	 * @return
	 */
	@RequestMapping(value = "/informePropietario/{id}", method = RequestMethod.GET)
	public ModelAndView informePropietario(@PathVariable("id") int idPropietario) {
		mav = new ModelAndView("propietario");
		Propietario propietario = prS.getInforme(idPropietario);
		mav.addObject("informePropietario", propietario);
		logger.info("Detalles del propietario: " + propietario.toString());
		return mav;
	}
}