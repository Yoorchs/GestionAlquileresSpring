package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Piso;

public interface PisoService {
	
	public Piso getbyId(int codigo);
	public List<Piso> getAll();
	public Piso create(Piso piso);
	public Piso update(Piso piso);
	public void delete(int idpiso);
}
