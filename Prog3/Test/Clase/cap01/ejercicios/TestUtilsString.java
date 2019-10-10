package Clase.cap01.ejercicios;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUtilsString {
	
	/**
	 * Primero se ejecutan los before, luego un test y al final los after. 
	 * Se repite hasta testear todos los test
	 * Útil cuando hay que iniciar una variable antes de los test.
	 */
	
	@Before
	public void setUp() throws Exception{
		System.out.println("before");
	}
	

	@After
	public void tearDown() throws Exception{
		System.out.println("after");
	}


	@Test
	public void quitarTabsYSaltosLinea() { //Método de prueba
		String prueba = "Hola\nEsto es un string con tres líneas\ny\tvarios\ttabuladores."; //Lo que se introduce
		String prueba2 = "Hola#Esto es un string con tres líneas#y|varios|tabuladores."; //Lo que se imprime por pantalla
		
		assertEquals(prueba2, UtilsString.quitarTabsYSaltosLinea(prueba)); //Hace un equals de ambos elementos
		
//		if (prueba2.equals(UtilsString.quitarTabsYSaltosLinea(prueba))) {//Se puede haver esta llamada porque el  método es estático.
//			//System.out.println( "OK" );
//			assertTrue(true); //Assert es un aserto que se tiene que cumplir. Si el () del assert es false, da error.
//		} else {
//			//System.out.println( "FAIL" );
//			fail("fallo por tohnto");
//		}
	}
	
	
	public void a() {//Este método no es una prueba porque no tiene @Test.
		System.out.println("a");
	}

}
