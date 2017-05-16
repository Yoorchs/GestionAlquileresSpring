package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Piso;

public interface PisoDao extends DAOSetter{

	public List<Piso> getAll();
	Piso getbyId(long codigo);
	public void delete(long codigo);
	public Piso create(Piso piso);
	public Piso update(Piso piso);
}
