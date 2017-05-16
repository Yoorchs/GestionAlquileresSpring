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

import com.ipartek.formacion.dbms.dao.interfaces.PisoDao;
import com.ipartek.formacion.dbms.mappers.PisoMapper;
import com.ipartek.formacion.dbms.persistence.Piso;

@Repository("pisoDaoImp")
public class PisoDAOImp implements PisoDao {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;
	private Logger logger = LoggerFactory.getLogger(PisoDAOImp.class);
	private SimpleJdbcCall jdbcCall;
	
	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Piso> getAll() {
		final String SQL = "CALL pisogetall();";
		List<Piso> pisos = null;
		logger.info("en el DAO de pisos");
		try {
			pisos = (List<Piso>) jdbctemplate.query(SQL, new PisoMapper());
			logger.info(pisos.toString());
		} catch (DataAccessException e) {
			logger.trace("No se han obtenido resultados " + e.getMessage());
			pisos = new ArrayList<Piso>();
		}
		return pisos;
	}
	
	@Override
	public Piso getbyId(long codigo) {
		Piso piso = null;
		final String SQL = "CALL pisogetbyid(?)";
		try {
			// queryForObject devuelve un unico objeto
			piso = jdbctemplate.queryForObject(SQL, new PisoMapper(), new Object[]{codigo});
			logger.info("GetByID de pisoDAO sobre el piso " +  piso.toString());
		} catch (DataAccessException e) {
			logger.info("No se ha encontrado el piso con codigo " + codigo);
		}
		return piso;
	}

	@Override
	public void delete(long codigo) {
		final String SQL = "pisodelete";
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
	public Piso create(Piso piso) {
		final String SQL = "pisocreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		//Crea un mapa con los parametros del procedimiento almacenado
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pdireccion", piso.getDireccion())
				.addValue("pprecionoche", piso.getPrecionoche())
				.addValue("preferenciacatastral", piso.getReferenciacatastral())
				.addValue("psuperficie", piso.getSuperficie());
		logger.info(piso.toString());
		//Se ejecuta la consulta
		Map<String, Object> out = jdbcCall.execute(in);
		// En la out se han recogido los parametros de la consulta a BBDD
		piso.setCodigo((Integer) out.get("pcodigo"));
		return piso;
	}

	@Override
	public Piso update(Piso piso) {
		final String SQL = "pisoupdate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		logger.info("En el DAO actualizando piso: " + piso.toString());
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", piso.getCodigo())
				.addValue("pdireccion", piso.getDireccion())
				.addValue("pprecionoche", piso.getPrecionoche())
				.addValue("preferenciacatastral", piso.getReferenciacatastral())
				.addValue("psuperficie", piso.getSuperficie());
		jdbcCall.execute(in);
		logger.info("En el DAO con los datos de piso actualizados: " + piso.toString());
		return piso;
	}
}
