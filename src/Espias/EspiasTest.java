package Espias;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EspiasTest {

	private Espias espias;
	
	@Before
	public void setUp() {
		espias = new Espias();
		espias.agregarPosibleEncuentro(0, 2, 0.8);
		espias.agregarPosibleEncuentro(1, 3, 0.5);
	}
	
	@Test
	public void agregarPosibleEncuentroTest() {
		espias.agregarPosibleEncuentro(1, 2, 0.3);
		assertTrue(espias.getRedEspias().existeArista(1, 2));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarPosibleEncuentroYaExistenteTest() {
		espias.agregarPosibleEncuentro(0, 2, 0.3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarPosibleEncuentroMismoEspiaTest() {
		espias.agregarPosibleEncuentro(2, 2, 0.3);
	}
}
