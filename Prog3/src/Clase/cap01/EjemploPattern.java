package Clase.cap01;

import java.util.regex.Pattern;

public class EjemploPattern {
	private static String[] testNombres = {
		"prueba.txt", "prueba2.jpg", "otro.dat", "pedro.doc", "andoni.txt", "dir"
	};
	public static void main(String[] args) { 
		//Los paterns nos permitn establecer "rangos" de datos aceptados. TODO TODO
		//Ciertas expresiones tienen que designarse de manera especial (mirar los doc)
		//Pareccen ser algo similares a las de la cmd.
		
		
		// Expresión regular de p .* \. .*. Lo cumplen: prueba.txt, prueba2.jpg, pedro.doc
		//p == empieza por pe
		//.* == cualquier caracter varias veces (no hay rango establecido de caracteres)
		//
		// Como el carácter \ en un string en Java es especial, hay que poner \\
		String patron1 = "p.*\\..*";   // Patrón de la ER  String "p.*\\..*" -> ER p.*\..* 
		System.out.println( patron1 );
		Pattern pat1 = Pattern.compile( patron1 );  // Se compila
		for (String s : testNombres) {
			if (pat1.matcher(s).matches())  // Se saca un "emparejador" y se comprueba si casa
				System.out.println( s + " cumple el patrón " + patron1 );
			else 
				System.out.println( "   " + s + " no cumple el patrón " + patron1 );
		}
	}
}
