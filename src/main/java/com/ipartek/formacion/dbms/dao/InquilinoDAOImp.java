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
import com.ipartek.formacion.dbms.dao.interfaces.InquilinoDAO;
import com.ipartek.formacion.dbms.mappers.InquilinoMapper;
import com.ipartek.formacion.dbms.persistence.Inquilino;

@Repository("inquilinoDaoImp")
public class InquilinoDAOImp implements InquilinoDAO {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;
	private Logger logger = LoggerFactory.getLogger(InquilinoDAOImp.class);
	private SimpleJdbcCall jdbcCall;
	
	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Inquilino getbyId(int codigo) {
		Inquilino inquilino = null;
		final String SQL = "CALL inquilinogetbyid(?)";
		try {
			// queryForObject devuelve un unico objeto
			inquilino = jdbctemplate.queryForObject(SQL, new InquilinoMapper(), new Object[]{codigo});
			logger.info("GetByID de inquilinoDAO sobre el inquilino " +  inquilino.toString());
		} catch (DataAccessException e) {
			logger.info("No se ha encontrado el inquilino con codigo " + codigo);
		}
		return inquilino;
	}
	
	@Override
	public List<Inquilino> getAll() {
		List<Inquilino> inquilinos = null;
		final String SQL = "CALL inquilinogetall();";
		try {
			inquilinos = (List<Inquilino>) jdbctemplate.query(SQL, new InquilinoMapper());
			logger.info(inquilinos.toString());
		} catch (DataAccessException e) {
			logger.trace("No se han obtenido resultados " + e.getMessage());
			inquilinos = new ArrayList<Inquilino>();
		}
		return inquilinos;
	}
	
	public void delete(int codigo) {
		final String SQL = "inquilinodelete";
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
	public Inquilino create(Inquilino inquilino) {
		final String SQL = "inquilinocreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		//Crea un mapa con los parametros del procedimiento almacenado
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", inquilino.getNombre())
				.addValue("papellidos", inquilino.getApellidos())
				.addValue("ptelefono", inquilino.getTelefono())
				.addValue("pdni", inquilino.getDni())
				.addValue("pemail", inquilino.getEmail());
		logger.info(inquilino.toString());
		//Se ejecuta la consulta
		Map<String, Object> out = jdbcCall.execute(in);
		// En la out se han recogido los parametros de la consulta a BBDD
		inquilino.setCodigo((Integer) out.get("pcodigo"));
		return inquilino;
	}

	@Override
	public Inquilino update(Inquilino inquilino) {
		final String SQL = "inquilinoupdate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", inquilino.getNombre())
				.addValue("papellidos", inquilino.getApellidos())
				.addValue("ptelefono", inquilino.getTelefono())
				.addValue("pdni", inquilino.getDni())
				.addValue("pemail", inquilino.getEmail())
				.addValue("pcodigo", inquilino.getCodigo());
		//Se ejecuta la consulta
		jdbcCall.execute(in);
		logger.info("En el DAO con los datos de inquilino actualizados: " + inquilino.toString());
		return inquilino;
	}
}
