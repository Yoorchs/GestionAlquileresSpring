package com.ipartek.formacion.dbms.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipartek.formacion.dbms.dao.interfaces.PisoDao;
import com.ipartek.formacion.dbms.persistence.Piso;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:application-context-test.xml")
public class PisoDaoTest {

	@Autowired
	PisoDao pD;
	Piso piso1;
	Piso piso2;
	private Logger logger = LoggerFactory.getLogger(PisoDaoTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		piso1 = new Piso();
    	piso1.setAlquilado(false);
    	piso1.setDireccion("La direccion que sea");
    	piso1.setPrecionoche(800);
    	piso1.setSuperficie(56);
    	piso1.setReferenciacatastral("13077A018000390000FP");
    	
    	piso2= new Piso();
    	piso2.setCodigo(2);
	}

	@After
	public void tearDown() throws Exception {
		piso1 = null;
		piso2 = null;
	}

	@Test
	public void testClase(){
		logger.info(this.pD.getClass().toString());
		assertEquals("class com.ipartek.formacion.dbms.dao.PisoDAOImp", this.pD.getClass().toString());
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void testUpdate() {
		//Creamos el piso1 en BBDD
		Piso pso = pD.create(piso1);
		logger.info("A ver si entra en esta mierda de test ");
		//Comprobamos que el piso no sea nulo
		assertNotNull("El piso es nulo", pso == null);
		//Modifico el objeto
		pso.setDireccion("999");
		//Actualizo en BBDD con update
		pD.update(pso);
		//Comprobamos que el dato se ha actualizado
		logger.info("Intentando probar el update desde test: " + pD.getbyId(pso.getCodigo()).toString());
		assertEquals("No se actualiza la direccion", pso.getDireccion(), (pD.getbyId(pso.getCodigo())).getDireccion());
		pD.delete(pso.getCodigo());
	}
	
	@Test
	public void testGetAll() {
		List<Piso> pisos = pD.getAll();
		assertEquals("No se han obtenido los datos esperados", 5, pisos.size());
	}

	@Test(timeout = 500)
	public void testGetbyId() {
		Piso piso = pD.getbyId(piso2.getCodigo()); 
		assertEquals("Los datos no son identicos", piso,piso2);
		assertNull("El objeto no es null", pD.getbyId(5000000));
	}

	@Test
	public void testDelete() {
		Long codigo = piso1.getCodigo();
		pD.delete(codigo);
		assertNull("El valor devuelto ha de ser null", pD.getbyId(codigo));
	}

	@Test(expected = DuplicateKeyException.class)
	public void testCreate() {
		Piso pso = pD.create(piso1);
		assertNotNull("El piso es nulo", pso == null);
		assertTrue("El codigo debe ser mayor que 0", pso.getCodigo() > 0);
		assertFalse("El codigo no debe ser menor que 0", pso.getCodigo() < 0);
		assertEquals("La referencia no es identica", pso.getReferenciacatastral(), piso1.getReferenciacatastral());
		// Todos los atributos
		assertEquals("Los datos no son identicos", pso, pD.getbyId((int) pso.getCodigo()));
		pD.delete((int) pso.getCodigo());
	}
}
