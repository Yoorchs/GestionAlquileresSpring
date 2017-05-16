package com.ipartek.formacion.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.PisoDao;
import com.ipartek.formacion.dbms.persistence.Piso;
import com.ipartek.formacion.service.interfaces.PisoService;

@Service
public class PisoServiceImp implements PisoService {

	@Autowired
	private PisoDao pisoDao;
	private Logger logger = LoggerFactory.getLogger(PisoServiceImp.class);
	
	@Override
	public Piso getbyId(int codigo) {
		return pisoDao.getbyId(codigo);
	}

	@Override
	public List<Piso> getAll() {
		List<Piso>pisos =  pisoDao.getAll();
		logger.info("Nº de pisos " + pisos.size());
		return pisos;
	}

	@Override
	public Piso create(Piso piso) {
		return pisoDao.create(piso);
	}

	@Override
	public Piso update(Piso piso) {
		return pisoDao.update(piso);
	}

	@Override
	public void delete(int idpiso) {
		pisoDao.delete(idpiso);
		
	}
}
