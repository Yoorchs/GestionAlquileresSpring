package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.Piso;
import com.ipartek.formacion.dbms.persistence.Propietario;

public class PropietarioExtractor implements ResultSetExtractor<Map <Integer, Propietario>> {

	@Override
	public Map<Integer, Propietario> extractData (ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer, Propietario> propietarios = new HashMap<Integer, Propietario>();
		Logger logger = LoggerFactory.getLogger(PropietarioExtractor.class);
		
		while (rs.next()) {	
			// Recogemos el codigo del propietario
			int codigo = rs.getInt("codigo");
			logger.info("Recuperando del Extractor el propietario con codigo: " + codigo);
			// Recogemos el propietario del mapa
			Propietario propietario = propietarios.get(codigo);
			// Si el propietario no esta ya recogido en el mapa
			if (propietario == null){ 
				propietario = new Propietario();
				propietario.setApellidos(rs.getString("apellidos"));
				propietario.setCodigo(rs.getInt("codigo"));
				propietario.setNombre(rs.getString("nombre"));
				propietario.setEmail(rs.getString("email"));
				propietario.setTelefono(rs.getString("telefono"));
				propietario.setDni(rs.getString("dni"));
				propietario.setNss(rs.getString("nss"));
				propietario.setPisos(new ArrayList<Piso>());
				logger.info("Extracto de Propietario: " + propietario.toString());
				propietarios.put(propietario.getCodigo(), propietario);
				
			}
			
			Integer cPiso = rs.getInt("propietario_codigo");
			if (cPiso != null) {
				// Aqui se carga la lista de los pisos	
				Piso piso = new Piso();
				piso.setCodigo(rs.getLong("codigo"));
				piso.setDireccion(rs.getString("direccion"));
				piso.setSuperficie(rs.getInt("superficie"));
				piso.setPrecionoche(rs.getInt("precionoche"));
				piso.setAlquilado(rs.getBoolean("alquilado"));
				piso.setReferenciacatastral(rs.getString("referenciacatastral"));
				propietario.getPisos().add(piso);
				logger.info("Propietarios: " + propietarios.toString());
			}
		}
		return propietarios;
	}
}
