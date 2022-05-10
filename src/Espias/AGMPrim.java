package Espias;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AGMPrim {

	private Grafo g;
	private Conjunto<Integer> vertices;
	private HashMap<Tupla<Integer, Integer>,Double> aristas;
	
	public AGMPrim (Grafo g) {
		this.g = g;
		this.vertices = new Conjunto<Integer>();
		this.aristas = new HashMap<Tupla<Integer, Integer>,Double>();
		
		int verticeInicial = g.dameVertice();
		vertices.agregarElemento(verticeInicial); //agrego un vertice random a V(arbol g min)
	}

	public void armarArbol() {

		if(!BFS.esConexo(g))
			throw new IllegalArgumentException("No se puede armar un Árbol generador mínimo de un grafo disconexo");

		int i = 1;
		Tupla<Integer,Integer> aristaMenorPeso = null;
		
		while (i <= g.tamano() - 1) {
			HashMap<Tupla<Integer,Integer>,Double> aristasPosibles = aristasPosibles(); //trae las aristas que van de un vertice agregado a uno no agregado
			double menorPeso = 1;
			
			for(Tupla<Integer, Integer> a : aristasPosibles.keySet()) {
				 if(aristasPosibles.get(a) < menorPeso) {
					 menorPeso = aristasPosibles.get(a);
					 aristaMenorPeso = a; //me quedo con la arista de menor peso
				 }
			}
			
			if (aristaMenorPeso != null) {
				aristas.put(aristaMenorPeso, g.damePesoDeArista(aristaMenorPeso.getX(), aristaMenorPeso.getY())); //agrego esa arista a V(arbol g min)
				vertices.agregarElemento(vertices.pertenece(aristaMenorPeso.getX()) ?  aristaMenorPeso.getY() : aristaMenorPeso.getX()); //agrego el vertice de la arista que no habia sido agregado aun
			}
			i++;
		}
	}
	
	public HashMap<Tupla<Integer,Integer>,Double> aristasPosibles() {
		HashMap<Tupla<Integer, Integer>, Double> aristasPosibles = new HashMap<Tupla<Integer, Integer>, Double>();
		for(int i = 0; i < g.tamano(); i++) { // recorro los vertices del grafo original
			if(vertices.pertenece(i)) {	//si el vertice ya fue agregado a V(arbol g min)
				Set<Integer> vecinos = g.vecinos(i); //obtengo todos sus vecinos
				Iterator<Integer> iter = vecinos.iterator();
				while(iter.hasNext()) { //recorro los vecinos de ese vertice para ver las aristas que forman
					int v = iter.next();
					if(!aristasPosibles.containsKey(new Tupla<Integer, Integer>(i,v)) && !aristasPosibles.containsKey(new Tupla<Integer, Integer>(v,i))) //si no es una arista que ya fue agregada a E(arbol g min)
						if(!vertices.pertenece(v)) //veo si el vertice aun no fue agregado a V(arb g min) xq sino formaria ciclo
							aristasPosibles.put(new Tupla<Integer,Integer>(i,v), g.damePesoDeArista(i, v));
				}
			}
		}
		return aristasPosibles;
	}

	public HashMap<Tupla<Integer, Integer>, Double> getAristas() {
		return aristas;
	}

	public Conjunto<Integer> getVertices() {
		return vertices;
	}
	
}
