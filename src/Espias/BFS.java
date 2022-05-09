package Espias;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BFS {
	
	private static boolean [] marcados;
	private static ArrayList<Integer> l;
	
	public static boolean esConexo(Grafo g) {
		if(g == null)
			throw new IllegalArgumentException("Grafo null");
		if(g.tamano() == 0 )
			return true;
		return alcanzables(g, 0).size() == g.tamano();
	}

	public static Set<Integer> alcanzables(Grafo g, int origen) {
		Set<Integer> ret = new HashSet<Integer>();
		inicializar(g, origen);
		
		while(l.size() > 0) {
			int i = l.get(0);
			marcados[i] = true;
			ret.add(i);
			
			agregarVecinosPendientes (g, i);
			l.remove(0);
		}
		return ret;
	}

	private static void agregarVecinosPendientes(Grafo g, int i) {
		for (int vertice : g.vecinos(i)) {
			if(marcados[vertice] == false && l.contains(vertice) == false) {
				l.add(vertice);
			}
		}
	}

	private static void inicializar(Grafo g, int origen) {
		l = new ArrayList<Integer>();
		l.add(origen);
		marcados = new boolean[g.tamano()];
	}
}
