package Espias;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GrafoTest {
	
	private Grafo g;

	@Before
	public void setUp() {
		g = new Grafo(10);
	}
	
	@Test
	public void damePesoDeAristaTest() {
		g.agregarArista(1, 2, 0.5);
		double p = g.damePesoDeArista(1, 2);
		assertEquals(p, 0.5, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void damePesoDeAristaInexsistenteTest() {
		g.damePesoDeArista(1, 2);
	}
	
	@Test
	public void dameVerticeTest() {
		int a = g.dameVertice();
		assertTrue(a <= g.tamano());
	}
	
	@Test
	public void estaVacioTest() {
		Grafo g2 = new Grafo(0);
		Boolean b = g2.estaVacio();
		assertTrue(b);
	}
	
	@Test
	public void noEstaVacioTest() {
		Boolean b = g.estaVacio();
		assertFalse(b);
	}

}
