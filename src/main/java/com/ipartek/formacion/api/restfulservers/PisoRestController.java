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

import com.ipartek.formacion.dbms.persistence.Piso;
import com.ipartek.formacion.service.interfaces.PisoService;

@RestController
@RequestMapping(value = "/api/pisos")
public class PisoRestController {

	@Inject
	PisoService pS;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Piso>> getAll(){
		List<Piso> pisos = pS.getAll();
		ResponseEntity<List<Piso>> response = null;
		if(pisos == null || pisos.isEmpty()){
			response = new ResponseEntity<List<Piso>>(HttpStatus.NO_CONTENT);
		}else{
			response = new ResponseEntity<List<Piso>>(pisos, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Piso> getbyID(@PathVariable("id") int codigo){
		Piso piso = pS.getbyId(codigo);
		ResponseEntity<Piso> response = null;
		if(piso == null){
			response = new ResponseEntity<Piso>(HttpStatus.NO_CONTENT);
		}else{
			response = new ResponseEntity<Piso>(piso, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> create(@RequestBody Piso piso, UriComponentsBuilder ucBuilder) {
		Piso pis = pS.getbyId((int) piso.getCodigo());
		ResponseEntity<Void> response = null;

		if (pis != null) {
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			try {
				Piso aux = pS.create(piso);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/pisos/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
				response = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			} catch (Exception e) {
				response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Piso> update(@PathVariable("codigo") int id, @RequestBody Piso piso) {
		Piso pis = pS.getbyId(id);
		ResponseEntity<Piso> response = null;

		if (pis == null) {
			response = new ResponseEntity<Piso>(HttpStatus.NOT_FOUND);
		} else {
			try {
				pis = pS.update(piso);
				response = new ResponseEntity<Piso>(pis, HttpStatus.ACCEPTED);

			} catch (Exception e) {
				response = new ResponseEntity<Piso>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Piso> deleteById(@PathVariable("codigo") int id) {
		Piso pis = pS.getbyId(id);
		ResponseEntity<Piso> response = null;
		if (pis == null) {
			response = new ResponseEntity<Piso>(HttpStatus.NOT_FOUND);
		} else {
			pS.delete(id);
			response = new ResponseEntity<Piso>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
