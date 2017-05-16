package com.ipartek.formacion.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.InquilinoDAO;
import com.ipartek.formacion.dbms.persistence.Inquilino;
import com.ipartek.formacion.service.interfaces.InquilinoService;

@Service
public class InquilinoServiceImp implements InquilinoService {

	@Inject
	private InquilinoDAO inquilinoDao;
	
	@Override
	public Inquilino create(Inquilino inquilino) {
		return inquilinoDao.create(inquilino);
	}

	@Override
	public List<Inquilino> getAll() {
		return inquilinoDao.getAll();
	}

	@Override
	public Inquilino update(Inquilino inquilino) {
		return inquilinoDao.update(inquilino);
	}

	@Override
	public Inquilino getbyId(int codigo) {
		return inquilinoDao.getbyId(codigo);
	}

	@Override
	public void delete(int codigo) {
		inquilinoDao.delete(codigo);
	}

	@Override
	public Inquilino getbyDNI(String dni) {
		// TODO Auto-generated method stub
		return null;
	}
}
