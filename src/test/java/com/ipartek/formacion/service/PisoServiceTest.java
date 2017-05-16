package com.ipartek.formacion.service;

import static org.junit.Assert.*;

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

import com.ipartek.formacion.dbms.persistence.Piso;
import com.ipartek.formacion.service.interfaces.PisoService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:application-context-test.xml")
public class PisoServiceTest {

	@Autowired
	PisoService ps;
	Piso piso;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		piso = new Piso();
    	piso.setAlquilado(false);
    	piso.setDireccion("Kalea 234");
    	piso.setPrecionoche(800);
    	piso.setSuperficie(56);
    	piso.setReferenciacatastral("13077A018000390000FP");
	}

	@After
	public void tearDown() throws Exception {
		piso = null;
	}

	@Test
	public void testClase(){
		assertEquals("class com.ipartek.formacion.service.PisoServiceImp", this.ps.getClass().toString());
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void testCreate() {
		Piso pso = ps.create(piso);
		assertNotNull("El piso es nulo", pso == null);
		assertTrue("El codigo debe ser mayor que 0", pso.getCodigo() > 0);
		assertFalse("El codigo no debe ser menor que 0", pso.getCodigo() < 0);
		assertEquals("La referencia no es identica", pso.getReferenciacatastral(), piso.getReferenciacatastral());
		// Todos los atributos
		assertEquals("Los datos no son identicos", pso, ps.getbyId((int) pso.getCodigo()));
		ps.delete((int) pso.getCodigo());
	}

}
