package Espias;

public class Tupla <t1, t2> {
	private t1 valor1;
	private t2 valor2;
	
	public Tupla (t1 x, t2 y) {
		valor1 = x;
		valor2 = y;
	}
	
	public String toString() {
		return valor1.toString() + "," + valor2.toString();
	}
	
	public t1 getX() {
		return valor1;
	}
	
	public t2 getY() {
		return valor2;
	}
	
	public void setX(t1 x) {
		valor1 = x;
	}
	
	public void setY(t2 y) {
		valor2 = y;
	}
}
