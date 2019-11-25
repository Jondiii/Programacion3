package Clase.cap04.ejercicios;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.sun.jna.platform.win32.WinDef.CHAR;

public class Recursividad { //Práctica 4 de prog 3.

	
	public static void main(String[] args) {
		String g = " Hola";
		char d = 'a';
		System.out.println("Ejercicio 1:" + invertirFrase(g));
		
		System.out.println(g.length());
		System.out.println(String.valueOf(g.charAt(0)) + "<- eso era");
		
		String r = "ho la \n  ^-^ yo soy Jon";
		System.out.println("Ejercicio 2:" + invertirPalabras(r));
		
		try {
			Scanner scanner = new Scanner( new File( "data/ficheroPrueba.txt" ));
			
		} catch (Exception e) {
			System.out.println("No se ha podido abrir el fichero, por lo que no se podrá hacer la prueba del ejercicio 4.");
		}
		
	}
	
	private static String invertirFrase(String f) {
		if (f.length() == 1) return f;
		else {
			String n = String.valueOf(f.charAt(0));
			String frase = invertirFrase(f.substring(1));
			return frase.concat(n);
		}
	}
	
	private static String invertirPalabras(String f) {
		if (f.length() <= 1) return f;
		String n = "";
		String frase;
		if (f.charAt(0) < 33) { //En ASCII, los caracteres menores de 33 son caracteres cuyo "print" no se muestra en la pantalla, como saltos de línea, espacios, etc.
			n = String.valueOf(f.charAt(0));
			frase = invertirPalabras(f.substring(1));
			return frase.concat(n);
			
		} else {
			for (char d : f.toCharArray()) {
				n = n + String.valueOf(d);
				if (d < 33) break;
			}
			frase = invertirPalabras(f.substring(n.length()));
			return frase.concat(n);
		}
		
	}
	
//	private static String longAHexa(String l) { //???
//		//long numero = Long.parseLong(l, 16);
//		long numero = Long.parseLong(l);
//		if (numero < 16) return String.valueOf(numero);
//		String valor = "";
//		
//		long resto = l%16;
//		numero = Long.toHexString(resto);
//		
//		longAHexa(l-16);
//	}
	
//	private static ArrayList<String> sacaPalabras(Scanner fichero) {
//		ArrayList<String> lista = new ArrayList<>();
//		if (fichero.hasNext()) {
//			ArrayList<String> palabras = sacaPalabras(fichero); //Aquí tengo que hacer que se quite la palabra que ya se ha leído.
//		} else {
//			return lista;
//		}
//	}
}
