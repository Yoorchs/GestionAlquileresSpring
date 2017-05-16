package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Inquilino;

public class InquilinoMapper implements RowMapper<Inquilino>{

	@Override
	public Inquilino mapRow(ResultSet rs, int rowNum) throws SQLException {
		Inquilino inquilino = new Inquilino();
		inquilino.setCodigo(rs.getInt("codigo"));
		inquilino.setNombre(rs.getString("nombre"));
		inquilino.setApellidos(rs.getString("apellidos"));
		inquilino.setDni(rs.getString("dni"));
		inquilino.setTelefono(rs.getString("telefono"));
		inquilino.setEmail(rs.getString("email"));
		return inquilino;
	}
}
