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
	
	// Más pruebas variadas de quitarTabsYSaltosLinea
	@Test
	public void testQuitarTabsYSaltosLinea2() {
		assertEquals( "", UtilsString.quitarTabsYSaltosLinea("") ); // String vacío
		assertEquals( "|", UtilsString.quitarTabsYSaltosLinea("\t") ); // Strings solo con \t y \n
		assertEquals( "#", UtilsString.quitarTabsYSaltosLinea("\n") );
		assertEquals( "#|||#", UtilsString.quitarTabsYSaltosLinea("\n\t\t\t\n") );
		assertEquals( "sin nada", UtilsString.quitarTabsYSaltosLinea("sin nada") ); // String sin \t o \n
	}
	
	
	// Prueba de null de quitarTabsYSaltosLinea
	@Test
	public void testQuitarTabsYSaltosLineaNull() {
		assertNull( UtilsString.quitarTabsYSaltosLinea(null) );
	}

	// Pruebas básicas de wrapString
	@Test
	public void testWrapString() {
		assertEquals( "And...", UtilsString.wrapString( "Andoni", 3) );
		assertEquals( "Andoni", UtilsString.wrapString( "Andoni", 6) );
		assertEquals( "Andoni", UtilsString.wrapString( "Andoni", 8) );
		assertEquals( "", UtilsString.wrapString( "", 8) );
		assertEquals( "", UtilsString.wrapString( "", 0) );
		assertEquals( "...", UtilsString.wrapString( "Andoni", 0) );
	}
	
	// Pruebas de wrapString con valores extremos
	@Test
	public void testWrapString2() {
		assertEquals( null, UtilsString.wrapString( null, 3) );
		assertEquals( null, UtilsString.wrapString( null, -1) );
	}
		
	// Pruebas de excepción de wrapString (método 1)
	@Test
	public void testWrapStringExc() {
		try {
			UtilsString.wrapString( "Andoni", -5 );
			fail();
		} catch (IndexOutOfBoundsException e) {
			// Ok
		}
	}

	// Pruebas de excepción de wrapString (método 2)
	// Otra manera de probar excepción para wrapString (alternativa - hace lo mismo que la anterior, de otra manera)
	@Test(expected=IndexOutOfBoundsException.class)
	public void testWrapStringExc2() {
		UtilsString.wrapString( "Andoni", -5 );
		}

	}

