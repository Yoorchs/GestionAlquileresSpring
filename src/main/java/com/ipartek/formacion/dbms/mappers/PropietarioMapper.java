package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Propietario;

public class PropietarioMapper implements RowMapper<Propietario> {

	@Override
	public Propietario mapRow(ResultSet rs, int rownum) throws SQLException {
		Propietario propietario = new Propietario();
		propietario.setCodigo(rs.getInt("codigo"));
		propietario.setNombre(rs.getString("nombre"));
		propietario.setApellidos(rs.getString("apellidos"));
		propietario.setDni(rs.getString("dni"));
		propietario.setTelefono(rs.getString("telefono"));
		propietario.setEmail(rs.getString("email"));
		propietario.setNss(rs.getString("nss"));
		return propietario;
	}

}
