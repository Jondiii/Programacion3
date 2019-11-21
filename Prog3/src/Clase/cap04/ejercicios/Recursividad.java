package Clase.cap04.ejercicios;

public class Recursividad { //Práctica 4 de prog 3.

	
	public static void main(String[] args) {
		String g = "Hola";
		System.out.println(invertirFrase(g));
	}
	
	private static String invertirFrase(String f) {
		if (f.length() == 1) return f;
		else {
			String n = String.valueOf(f.charAt(0));
			String frase = invertirFrase(f.substring(1));
			return frase.concat(n);
		}
	}
	
	
}
