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

import com.ipartek.formacion.dbms.persistence.Inquilino;
import com.ipartek.formacion.service.interfaces.InquilinoService;

/**
 * 
 * @author Jorge
 * Clase que controla las peticiones al controlador de la clase <code>Inquilino
 *
 */

@Controller
@RequestMapping(value = "inquilinos")
public class InquilinoController {

	@Inject
	InquilinoService iS;
	ModelAndView mav = null;
	private static final Logger logger = LoggerFactory.getLogger(InquilinoController.class);
	@Resource(name="inquilinoValidator")
	private Validator validator = null;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), false, 10));
	}
	
	/**
	 * Metodo que devuelve el listado de Inquilinos. 
	 * @return mav. Objeto ModelAndView con el listado y la peticion a la URL correspondiente
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		mav = new ModelAndView("inquilinos");
		mav.addObject("inquilinos", iS.getAll());
		logger.info("Controller despues de GetAll de inquilinos: " + iS.getAll().toString());
		return mav;
	}

	/**
	 * Metodo para realizar el borrado de in inquilino de la BBDD. Recibe el codigo del Inquilino a borrrar. Devuelve la peticion a la URL tras el borrado
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "deleteInquilino/{codigo}")
	public String delete(@PathVariable("codigo") int codigo) {
		logger.info("En delete() de inquilinos con el codigo " + codigo);
		iS.delete(codigo);
		return "redirect:/inquilinos";
	}

	/**
	 * Metodo que realiza lapeticion a la URL con el formulario para crear un nuevo Inquilino
	 * @return
	 */
	@RequestMapping(value = "/addInquilino")
	public ModelAndView addInquilino() {
		mav = new ModelAndView("inquilino");
		mav.addObject("inquilino", new Inquilino());
		return mav;
	}
	
	/**
	 * Metodo que Guarda los datos de un objeto Inquilino. Si el codigo es mayor que nulo, se tratara de una edicion, en caso contrario, se creara dicho objeto.
	 * @param inquilino
	 * @param bindingresult
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveInquilino(@ModelAttribute("inquilino") @Validated Inquilino inquilino, 
			BindingResult bindingresult, Model model) {
		String destino = "";
		if (bindingresult.hasErrors()){
			logger.info("Errores al crear el Inquilino");
			destino = "inquilino";
		}else {
			if(inquilino.getCodigo() > Inquilino.CODIGO_NULO){
				logger.info("Editando fulano: " + inquilino.toString());
				iS.update(inquilino);
				destino = "redirect:/inquilinos";
			}else {
				iS.create(inquilino);
				logger.info("Nuevo fulano: " + inquilino.toString());
				destino = "redirect:/inquilinos";
			}
		}
		return destino;
	}
	
	/**
	 * Metodo para obtener un Inquilino definido por el parametro ID. Devuelve on objeto Inquilino y la redireccion a la URL correspondiente.
	 * @param id
	 * @return
	 */
	@RequestMapping( value ="/{id}")
	public ModelAndView getByID(@PathVariable("id")int id){
		mav = new ModelAndView("inquilino");
		mav.addObject("inquilino", iS.getbyId(id));
		logger.info("Datos editados del inquilino con id: " + id + " " + iS.getbyId(id));
		return mav;
	}
}
