package com.ipartek.formacion.api.restfulservers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipartek.formacion.dbms.persistence.Propietario;
import com.ipartek.formacion.service.interfaces.PropietarioService;

@RestController
@RequestMapping(value = "/api/propietarios")
public class PropietarioRestController {

	@Inject
	PropietarioService pS;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Propietario>> getAll(){
		List<Propietario> propietarios = pS.getAll();
		ResponseEntity<List<Propietario>> response = null;
		if(propietarios == null || propietarios.isEmpty()){
			response = new ResponseEntity<List<Propietario>>(HttpStatus.NO_CONTENT);
		}else{
			response = new ResponseEntity<List<Propietario>>(propietarios, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Propietario> getbyID(@PathVariable("id") int codigo){
		Propietario propietario = pS.getbyId(codigo);
		ResponseEntity<Propietario> response = null;
		if(propietario == null){
			response = new ResponseEntity<Propietario>(HttpStatus.NO_CONTENT);
		}else{
			response = new ResponseEntity<Propietario>(propietario, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> create(@RequestBody Propietario propietario, UriComponentsBuilder ucBuilder) {
		Propietario prop = pS.getbyId((int) propietario.getCodigo());
		ResponseEntity<Void> response = null;

		if (prop != null) {
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			try {
				Propietario aux = pS.create(propietario);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/propietarios/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
				response = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			} catch (Exception e) {
				response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Propietario> update(@PathVariable("codigo") int id, @RequestBody Propietario propietario) {
		Propietario prop = pS.getbyId(id);
		ResponseEntity<Propietario> response = null;

		if (prop == null) {
			response = new ResponseEntity<Propietario>(HttpStatus.NOT_FOUND);
		} else {
			try {
				prop = pS.update(propietario);
				response = new ResponseEntity<Propietario>(prop, HttpStatus.ACCEPTED);

			} catch (Exception e) {
				response = new ResponseEntity<Propietario>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Propietario> deleteById(@PathVariable("codigo") int id) {
		Propietario prop = pS.getbyId(id);
		ResponseEntity<Propietario> response = null;
		if (prop == null) {
			response = new ResponseEntity<Propietario>(HttpStatus.NOT_FOUND);
		} else {
			pS.delete(id);
			response = new ResponseEntity<Propietario>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
