package Espias;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

public class Espias {
	private Conjunto<String> espias;
	private Grafo redEspias;
	private ArbolGenMinPrim redEspiasConMenorRiesgo;
	
	public Espias() {
		espias = new Conjunto<String>();
		leerNombres();
		redEspias = new Grafo(espias.cantElementos());
		//armarRedEspias();
		//redEspiasConMenorRiesgo = new ArbolGenMinPrim(redEspias);
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
	
	public void armarRedEspias() { //aleatoriamente
		Random r = new Random();
		int cantMaxAristas = (redEspias.tamano() * (redEspias.tamano() - 1)) / 2;
		for (int n = 0; n < cantMaxAristas; n++) {
			int i = r.nextInt(espias.cantElementos());
			int j = r.nextInt(espias.cantElementos());
			double p = r.nextDouble();
			if (i != j) agregarPosibleEncuentro(i, j, p);
		}
	}
	
	public void agregarPosibleEncuentro(int i, int j, double peso) {
		System.out.println(i + " " + j + " " + peso);
		redEspias.agregarArista(i, j, peso);
	}

	public ArbolGenMinPrim getRedEspiasConMenorRiesgo() {
		return redEspiasConMenorRiesgo;
	}

	public Conjunto<String> getEspias(){
		return espias;
	}
}
