package Espias;

public class Prim {

	private Grafo g;
	private Conjunto<Integer> vertices;
	private Conjunto<Integer> aristas;
	
	public Prim (Grafo g) {
		this.g = g;
		vertices = new Conjunto<Integer>(); //hace falta?
		aristas = new Conjunto<Integer>(); //hace falta?
		vertices.agregarElemento(g.dameVertice());
	}
	
	private void arbol () {
		int i = 1;
		while (i <= g.tamano() - 1) {
			
		}
	}
}
