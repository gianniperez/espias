package Espias;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ArbolGenMinPrimTest {

	private Grafo g;
	private ArbolGenMinPrim g2;
	
	@Before
	public void setUp() {
		g = new Grafo(3);
		g2 = new ArbolGenMinPrim(g);
	}

	@Test
	public void aristasPosiblesTest() {
		g.agregarArista(0, 1, 0.3);
		g.agregarArista(1, 2, 0.5);
		g.agregarArista(0, 2, 0.9);
		HashMap<Tupla<Integer, Integer>, Double> aristasPosibles = g2.aristasPosibles();
		assertTrue(aristasPosibles.size() > 0);
	}
	
	@Test
	public void aristasPosiblesVacioTest() {
		HashMap<Tupla<Integer, Integer>, Double> aristasPosibles = g2.aristasPosibles();
		assertTrue(aristasPosibles.size() == 0);
	}
	
}
