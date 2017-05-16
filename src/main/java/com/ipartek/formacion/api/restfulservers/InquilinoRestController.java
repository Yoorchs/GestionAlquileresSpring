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

import com.ipartek.formacion.dbms.persistence.Inquilino;
import com.ipartek.formacion.service.interfaces.InquilinoService;

@RestController
@RequestMapping(value = "/api/inquilinos")
public class InquilinoRestController {

	@Inject
	InquilinoService iS;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Inquilino>> getAll(){
		List<Inquilino> inquilinos = iS.getAll();
		ResponseEntity<List<Inquilino>> response = null;
		if(inquilinos == null || inquilinos.isEmpty()){
			response = new ResponseEntity<List<Inquilino>>(HttpStatus.NO_CONTENT);
		}else{
			response = new ResponseEntity<List<Inquilino>>(inquilinos, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Inquilino> getbyID(@PathVariable("id") int codigo){
		Inquilino inquilino = iS.getbyId(codigo);
		ResponseEntity<Inquilino> response = null;
		if(inquilino == null){
			response = new ResponseEntity<Inquilino>(HttpStatus.NO_CONTENT);
		}else{
			response = new ResponseEntity<Inquilino>(inquilino, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> create(@RequestBody Inquilino inquilino, UriComponentsBuilder ucBuilder) {
		Inquilino inq = iS.getbyId((int) inquilino.getCodigo());
		ResponseEntity<Void> response = null;

		if (inq != null) {
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			try {
				Inquilino aux = iS.create(inquilino);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/inquilinos/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
				response = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			} catch (Exception e) {
				response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Inquilino> update(@PathVariable("codigo") int id, @RequestBody Inquilino inquilino) {
		Inquilino inq = iS.getbyId(id);
		ResponseEntity<Inquilino> response = null;

		if (inq == null) {
			response = new ResponseEntity<Inquilino>(HttpStatus.NOT_FOUND);
		} else {
			try {
				inq = iS.update(inquilino);
				response = new ResponseEntity<Inquilino>(inq, HttpStatus.ACCEPTED);

			} catch (Exception e) {
				response = new ResponseEntity<Inquilino>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Inquilino> deleteById(@PathVariable("codigo") int id) {
		Inquilino inq = iS.getbyId(id);
		ResponseEntity<Inquilino> response = null;
		if (inq == null) {
			response = new ResponseEntity<Inquilino>(HttpStatus.NOT_FOUND);
		} else {
			iS.delete(id);
			response = new ResponseEntity<Inquilino>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
