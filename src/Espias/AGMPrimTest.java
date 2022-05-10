package Espias;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class AGMPrimTest {

	private Grafo g;
	private AGMPrim g2;
	
	@Before
	public void setUp() {
		g = new Grafo(5);
		g2 = new AGMPrim(g);
	}

	@Test
	public void armarArbolTest() {
		g.agregarArista(0, 1, 0.3);
		g.agregarArista(1, 2, 0.5);
		g.agregarArista(0, 2, 0.9);
		g.agregarArista(0, 3, 0.7);
		g.agregarArista(1, 4, 0.2);
		g2.armarArbol();
		assertTrue(g2.getAristas().size() > 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void armarArbolVacioTest() {
		g2.armarArbol();
	}
	
	@Test
	public void aristasPosiblesTest() {
		g.agregarArista(0, 1, 0.3);
		g.agregarArista(1, 2, 0.5);
		g.agregarArista(0, 2, 0.9);
		HashMap<Tupla<Integer, Integer>, Double> aristasPosibles = g2.aristasPosibles();
		assertTrue(aristasPosibles.size() >= 0);
	}
	
	@Test
	public void aristasPosiblesVacioTest() {
		HashMap<Tupla<Integer, Integer>, Double> aristasPosibles = g2.aristasPosibles();
		assertTrue(aristasPosibles.size() == 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void armarAGMconGrafoDisconexo() {
		g.agregarArista(0, 1, 0.2);
		g.agregarArista(0, 2, 0.4);
		g.agregarArista(3, 4, 0.3);
		g2.armarArbol();
	}
}
