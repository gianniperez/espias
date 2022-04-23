package Espias;

import java.io.BufferedReader;
import java.io.FileReader;

public class Espias {
	private Conjunto<String> espias;
	private Grafo redEspias;
	
	public Espias() {
		espias = new Conjunto<String>();
		redEspias = new Grafo(espias.cantElementos());
	}
	
	public void leerNombres() {
		try {
			FileReader archivo = new FileReader("nombresEspias.txt");
			BufferedReader lectura = new BufferedReader(archivo);
			String nombre;
			while ((nombre = lectura.readLine()) != null) {
				espias.agregarElemento(nombre);
				}
			lectura.close();
		} catch(Exception ex) {}
	}
}
