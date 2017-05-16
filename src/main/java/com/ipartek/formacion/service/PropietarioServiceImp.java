package com.ipartek.formacion.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.PropietarioDAO;
import com.ipartek.formacion.dbms.persistence.Propietario;
import com.ipartek.formacion.service.interfaces.PropietarioService;

@Service
public class PropietarioServiceImp implements PropietarioService {

	@Inject
	private PropietarioDAO propietarioDao;

	@Override
	public List<Propietario> getAll() {
		List<Propietario> propietarios = propietarioDao.getAll();
		return propietarios;
	}

	@Override
	public Propietario create(Propietario propietario) {
		return propietarioDao.create(propietario);
	}

	@Override
	public Propietario update(Propietario propietario) {
		return propietarioDao.update(propietario);
	}

	@Override
	public Propietario getbyId(int codigo) {
		return propietarioDao.getbyId(codigo);
	}

	@Override
	public void delete(int codigo) {
		propietarioDao.delete(codigo);
		
	}

	@Override
	public Propietario getInforme(int idPropietario) {
		return propietarioDao.propietarioInforme(idPropietario);
	}
	
}
