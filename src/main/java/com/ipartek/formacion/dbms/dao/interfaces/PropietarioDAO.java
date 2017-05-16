package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Propietario;

public interface PropietarioDAO extends DAOSetter{

	public List<Propietario> getAll();

	Propietario getbyId(int codigo);

	void delete(int codigo);

	Propietario create(Propietario propietario);

	Propietario update(Propietario propietario);

	Propietario propietarioInforme(int codigo);

}
