package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.PropietarioDAO;
import com.ipartek.formacion.dbms.mappers.PropietarioExtractor;
import com.ipartek.formacion.dbms.mappers.PropietarioMapper;
import com.ipartek.formacion.dbms.persistence.Propietario;

@Repository("propietarioDaoImp")
public class PropietarioDAOImp implements PropietarioDAO {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;
	private Logger logger = LoggerFactory.getLogger(PropietarioDAOImp.class);
	private SimpleJdbcCall jdbcCall;
	
	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Propietario> getAll() {
		final String SQL = "CALL propietariogetall();";
		List<Propietario> propietarios = null;
		logger.info("en el DAO de propietarios");
		try {
			propietarios = (List<Propietario>) jdbctemplate.query(SQL, new PropietarioMapper());
			logger.info(propietarios.toString());
		} catch (DataAccessException e) {
			logger.trace("No se han obtenido resultados " + e.getMessage());
			propietarios = new ArrayList<Propietario>();
		}
		return propietarios;
	}
	
	@Override
	public Propietario propietarioInforme (int codigo) {
		Propietario propietario = null;
		final String SQL = "CALL propietarioinforme(?);";
		try {
			// queryForObject devuelve un unico objeto
			Map<Integer, Propietario> propietarioDetalle  = jdbctemplate.query(SQL, new PropietarioExtractor(), new Object[]{codigo});
			propietario = propietarioDetalle.get(codigo);
			logger.info("Informe de propietarioDAO sobre el propietario " +  propietario.toString());
		} catch (DataAccessException e) {
			logger.info("Error recuperando el propietario con codigo " + codigo + " " + e.getMessage());
			propietario = new Propietario();
		}
		return propietario;
	}
	
	@Override
	public void delete(int codigo) {
		final String SQL = "propietariodelete";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		try {
			SqlParameterSource in = new MapSqlParameterSource().addValue("pcodigo", codigo);
			jdbcCall.withProcedureName(SQL);
			jdbcCall.execute(in);
		} catch (DataAccessException e) {
			logger.info("Error en la operacion de borrado: " + e.getMessage());
		}
	}
	
	@Override
	public Propietario create(Propietario propietario) {
		final String SQL = "pisocreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		//Crea un mapa con los parametros del procedimiento almacenado
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", propietario.getNombre())
				.addValue("papellidos", propietario.getApellidos())
				.addValue("pdni", propietario.getDni())
				.addValue("pemail", propietario.getEmail())
				.addValue("ptelefono", propietario.getTelefono())
				.addValue("pnss", propietario.getNss());
		logger.info(propietario.toString());
		//Se ejecuta la consulta
		Map<String, Object> out = jdbcCall.execute(in);
		// En la out se han recogido los parametros de la consulta a BBDD
		propietario.setCodigo((Integer) out.get("pcodigo"));
		return propietario;
	}

	@Override
	public Propietario update(Propietario propietario) {
		final String SQL = "propietarioupdate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", propietario.getCodigo())
				.addValue("pnombre", propietario.getNombre())
				.addValue("papellidos", propietario.getApellidos())
				.addValue("pdni", propietario.getDni())
				.addValue("pemail", propietario.getEmail())
				.addValue("ptelefono", propietario.getTelefono())
				.addValue("pnss", propietario.getNss());
		jdbcCall.execute(in);
		logger.info("En el DAO con los datos de piso propietario: " + propietario.toString());
		return propietario;
	}

	@Override
	public Propietario getbyId(int codigo) {
		Propietario propietario = null;
		final String SQL = "CALL propietariogetbyid(?)";
		try {
			// queryForObject devuelve un unico objeto
			propietario = jdbctemplate.queryForObject(SQL, new PropietarioMapper(), new Object[]{codigo});
			logger.info("GetByID de PropietarioDAO sobre el propietario " +  propietario.toString());
		} catch (DataAccessException e) {
			logger.info("No se ha encontrado el propietario con codigo " + codigo);
		}
		return propietario;
	}
}
