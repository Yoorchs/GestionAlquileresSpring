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

import com.ipartek.formacion.dbms.persistence.Piso;
import com.ipartek.formacion.service.interfaces.PisoService;

/**
 * 
 * @author Jorge
 * Clase que controla las peticiones al controlador de la clase <code>Piso
 *
 */

@Controller
@RequestMapping(value = "pisos")
public class PisoController {

	@Inject
	PisoService pS;
	ModelAndView mav = null;
	private static final Logger logger = LoggerFactory.getLogger(PisoController.class);
	@Resource(name = "pisoValidator")
	private Validator validator = null;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), false, 10));
	}

	/**
	 * Metodo que devuelve el listado de Pisos. 
	 * @return mav. Objeto ModelAndView con el listado y la peticion a la URL correspondiente
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		mav = new ModelAndView("pisos");
		logger.info("En getAll() de pisos");
		mav.addObject("listadopisos", pS.getAll());
		return mav;
	}
	/**
	 * Metodo para realizar el borrado de in Piso de la BBDD. Recibe el codigo del Inquilino a borrrar. Devuelve la peticion a la URL tras el borrado
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "deletePiso/{codigo}")
	public String delete(@PathVariable("codigo") int codigo) {
		logger.info("En delete() de pisos con el codigo " + codigo);
		pS.delete(codigo);
		return "redirect:/pisos";
	}

	/**
	 * Metodo que realiza lapeticion a la URL con el formulario para crear un nuevo Piso
	 * @return
	 */
	@RequestMapping(value = "/addPiso")
	public ModelAndView addPiso() {
		mav = new ModelAndView("piso");
		mav.addObject("piso", new Piso());
		return mav;
	}

	/**
	 * Metodo que Guarda los datos de un objeto Piso. Si el codigo es mayor que nulo, se tratara de una edicion, en caso contrario, se creara dicho objeto.
	 * @param piso
	 * @param bindingresult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePiso(@ModelAttribute("piso") @Validated Piso piso, 
			BindingResult bindingresult, Model model) {
		String destino = "";
		logger.info("Vamos a salvar los valores del piso");
		if (bindingresult.hasErrors()) {
			logger.info("Errores al crear el piso");
			destino = "piso";
		} else {
			if (piso.getCodigo() > Piso.CODIGO_NULO) {
				logger.info("Editando pisaco: " + piso.toString());
				pS.update(piso);
				destino = "redirect:/pisos";
			} else {
				pS.create(piso);
				logger.info("Nuevo piso: " + piso.toString());
				destino = "redirect:/pisos";
			}
		}
		return destino;
	}

	/**
	 * Metodo para obtener un Piso definido por el parametro ID. Devuelve on objeto Piso y la redireccion a la URL correspondiente.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}")
	public ModelAndView getByID(@PathVariable("id") int id) {
		mav = new ModelAndView("piso");
		mav.addObject("piso", pS.getbyId(id));
		logger.info("Datos editados del piso con id: " + id + " " + pS.getbyId(id));
		return mav;
	}
}
