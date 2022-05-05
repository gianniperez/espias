package Espias;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Random;

public class Espias {
	private Conjunto<String> espias;
	private Grafo redEspias;
	private ArbolGenMinPrim redEspiasConMenorRiesgo;
	
	public Espias() {
		espias = new Conjunto<String>();
		leerNombres();
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
	
	public void agregarPosibleEncuentro(int i, int j, double peso) {
		System.out.println(i + " " + j + " " + peso);
		
		if (i == j)
			throw new IllegalArgumentException("No puede haber un encuentro entre un mismo espia");
		
		if (redEspias.existeArista(i, j))
			throw new IllegalArgumentException("Ya existe ese posible encuentro");
		
		redEspias.agregarArista(i, j, peso);
	}
	
	public HashMap<Tupla<Integer, Integer>, Double> redEspiasConMenorRiesgo() {
		redEspiasConMenorRiesgo = new ArbolGenMinPrim(redEspias);
		redEspiasConMenorRiesgo.armarArbol();
		return redEspiasConMenorRiesgo.getAristas();
	}
	
	public Grafo getRedEspias() {
		return redEspias;
	}

	public Conjunto<String> getEspias(){
		return espias;
	}
}
