package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Inquilino;

public interface InquilinoService {

	public Inquilino create(Inquilino inquilino);

	public List<Inquilino> getAll();
	
	public Inquilino update (Inquilino inquilino);
	
	public Inquilino getbyId (int codigo);
	
	public void delete (int codigo);
	
	public Inquilino getbyDNI (String dni);
}
