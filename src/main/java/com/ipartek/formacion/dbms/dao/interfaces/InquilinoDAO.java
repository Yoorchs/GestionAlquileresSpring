package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Inquilino;

public interface InquilinoDAO extends DAOSetter{

	public List<Inquilino> getAll();
	public void delete(int codigo);
	public Inquilino create(Inquilino inquilino);
	public Inquilino update(Inquilino inquilino);
	Inquilino getbyId(int codigo);
}
