package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Piso;

public class PisoMapper implements RowMapper<Piso> {

	@Override
	public Piso mapRow(ResultSet rs, int rowNum) throws SQLException {
		Piso piso = new Piso();
		piso.setCodigo(rs.getInt("codigo"));
		piso.setDireccion(rs.getString("direccion"));
		piso.setPrecionoche(rs.getInt("precionoche"));
		piso.setReferenciacatastral(rs.getString("referenciacatastral"));
		piso.setSuperficie(rs.getInt("superficie"));
		piso.setAlquilado(rs.getBoolean("alquilado"));
		return piso;
	}
}
