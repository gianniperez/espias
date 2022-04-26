package Espias;

public class Prim {

	private Grafo g;
	private Conjunto<Integer> vertices;
	private Conjunto<Integer> aristas;
	
	public Prim (Grafo g) {
		this.g = g;
		//vertices = g; //inicia con cualquier vértice
		aristas = new Conjunto<Integer>();
	}
	
	private void arbol () {
		int i = 1;
		while (i <= g.tamano() - 1) {
			
		}
	}
}
