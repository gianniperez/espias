package Espias;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class BFSTest {

	private Grafo inicializarGrafo() {
		Grafo g = new Grafo(5);
		g.agregarArista(0, 1, 0.2);
		g.agregarArista(0, 2, 0.3);
		g.agregarArista(2, 3, 0.4);
		return g;
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullTest() {
		BFS.esConexo(null);
	}

	@Test
	public void alcanzablesTest() {
		Grafo g = inicializarGrafo();
		Set<Integer> alcanzables = BFS.alcanzables(g, 0);
		int [] esperados = {0,1,2,3};
		
		Assert.iguales(esperados, alcanzables);
	}
	
	@Test 
	public void conexoTest() {
		Grafo g = inicializarGrafo();
		g.agregarArista(3, 4, 0.3);
		assertTrue(BFS.esConexo(g));
	}
	
	public void noConexoTest() {
		Grafo g = inicializarGrafo();
		assertFalse(BFS.esConexo(g));
	}
	
	public void vacioTest() {
		Grafo g = new Grafo(0);
		assertTrue(BFS.esConexo(g));
	}
}
