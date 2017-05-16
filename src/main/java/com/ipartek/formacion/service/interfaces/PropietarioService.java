package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Propietario;

public interface PropietarioService {

	public Propietario create(Propietario propietario);

	public List<Propietario> getAll();
	
	public Propietario update (Propietario propietario);
	
	public Propietario getbyId (int codigo);
	
	public void delete (int codigo);

	public Propietario getInforme(int idPropietario);
	
}
