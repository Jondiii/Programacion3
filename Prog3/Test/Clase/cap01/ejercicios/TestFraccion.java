package Clase.cap01.ejercicios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/** Ejercicio 1.8.a  planteado (sin codificar ni probar)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class TestFraccion {
	
	// Variables utilizadas para las pruebas
	private Fraccion f;
	private Fraccion f2;
	
	/**
	 * Se pueden iniciar así variables que queremos que se usen siempre en las pruebas.
	 */
	@Before
	public void init() {
		f = new Fraccion( 2, 6 ); // 2/6 = 1/3
		f2 = new Fraccion( 1, -5 ); // 1/-5 = -1/5
	}

	
	/** Método de prueba de la clase fracción
	 * @param args	No utilizado
	 */
	//@Test //No se puede hacer test de métodos estáticos. Tampoco pueden tener argumentos.  TODO TODO
	public void main() {
		Fraccion f = new Fraccion( 2, 6 ); // 2/6 = 1/3
		System.out.println( f );
		Fraccion f2 = new Fraccion( 1, -5 ); // 1/-5 = -1/5
		System.out.println( f2 );
		System.out.println( Fraccion.suma(f,f2) ); // 1/3 + (-1/5) = 2/15
		System.out.println(  Fraccion.resta(f,f2) ); // 1/3 - (-1/5) = 8/15
		System.out.println(  Fraccion.multiplica(f,f2) ); // 1/3 * (-1/5) = -1/15
		System.out.println(  Fraccion.divide(f,f2) ); // 1/3 / (-1/5) = -5/3
	}
		
		@Test
		public void testSimplificacion() {
			assertEquals( 1.0/3, 1.0*f.getNum()/f.getDen(), 0.00000000001 );  // Prueba el valor numérico
			assertEquals( new Fraccion(1,3), f );  // Prueba la comparación completa de fracciones
		}
		
		@Test
		public void testSigno() {
			assertEquals( -1, f2.getNum() );
			assertEquals( 5, f2.getDen() );
		}
		
		@Test
		public void testSuma() {
			assertEquals( new Fraccion(2,15), Fraccion.suma(f,f2) ); // 1/3 + (-1/5) = 2/15
		}
		
		@Test
		public void testResta() {
			assertEquals( new Fraccion(8,15), Fraccion.resta(f,f2) ); // 1/3 - (-1/5) = 8/15
		}
		
		@Test
		public void testMultiplicacion() {
			assertEquals( new Fraccion(-1,15), Fraccion.multiplica(f,f2) ); // 1/3 * (-1/5) = -1/15
		}
		
		@Test
		public void testDivision() {
			assertEquals( new Fraccion(-5,3), Fraccion.divide(f,f2) ); // 1/3 / (-1/5) = -5/3
		}	
		
		@Test
		public void testFraccionCero() {
			Fraccion f3 = new Fraccion( 0, 6 );
			assertEquals( new Fraccion( 0, 1 ), f3 ); // 0/6 = 0/1  (cualquier 0 da igual el denominador)
			assertEquals( new Fraccion( 1, 3 ), Fraccion.suma(f,f3) ); // 1/3 + 0/1 = 1/3
			assertEquals( new Fraccion( 1, 3 ), Fraccion.resta(f,f3) ); // 1/3 - 0/1 = 1/3
			assertEquals( new Fraccion( 0, 1 ), Fraccion.multiplica(f,f3) ); // 1/3 * 0/1 = 0/1
			try {
				Fraccion.divide(f,f3); // Error! división por 0
				fail();
			} catch (ArithmeticException e) {
				// Test correcto
			}
			try {
				Fraccion f4 = new Fraccion( 6, 0 ); // Error! Fracción irracional
				fail();
			} catch (ArithmeticException e) {
				// Test correcto
			}
			try {
				Fraccion f5 = new Fraccion( 0, 0 ); // Error! Fracción más irracional
				fail();
			} catch (ArithmeticException e) {
				// Test correcto
			}
		}

		// Ejemplo de cómo se pueden probar cosas programáticamente, a veces es útil
		// Prueba que todas las operaciones de posibles fracciones n/m para valores entre 1 y 10 
		// son correctas numéricamente y están bien simplificadas
		@Test
		public void testRepetitivo() {
			int numTests = 0;  // Solo por curiosidad para ver los tests que se hacen
			for (int num1=1; num1<=10; num1++) {
				for (int den1=1; den1<=10; den1++) {
					for (int num2=1; num2<=10; num2++) {
						for (int den2=1; den2<=10; den2++) {
							// todas las combinaciones num1/den1 con num2/den2
							Fraccion fr1 = new Fraccion( num1, den1 );
							Fraccion fr2 = new Fraccion( num2, den2 );
							Fraccion fSum = Fraccion.suma( fr1, fr2 );
							Fraccion fRes = Fraccion.resta( fr1, fr2 );
							Fraccion fMul = Fraccion.multiplica( fr1, fr2 );
							Fraccion fDiv = Fraccion.divide( fr1, fr2 );
							// Fracciones bien simplificadas (MCD = 1)
							assertEquals( 1, Fraccion.mcd( fSum.getNum(), fSum.getDen()) );
							assertEquals( 1, Fraccion.mcd( fRes.getNum(), fRes.getDen()) );
							assertEquals( 1, Fraccion.mcd( fMul.getNum(), fMul.getDen()) );
							assertEquals( 1, Fraccion.mcd( fDiv.getNum(), fDiv.getDen()) );
							// Valor numérico correcto
							assertEquals( fr1.getVal()+fr2.getVal(), fSum.getVal(), 0.0000001 );
							assertEquals( fr1.getVal()-fr2.getVal(), fRes.getVal(), 0.0000001 );
							assertEquals( fr1.getVal()*fr2.getVal(), fMul.getVal(), 0.0000001 );
							assertEquals( fr1.getVal()/fr2.getVal(), fDiv.getVal(), 0.0000001 );
							numTests++;
						}
					}
				}
			}
			System.out.println( "Test realizados en testRepetitivo, todos correctos: " + numTests );
		}
		

	/**
	 * Todo esto no hace falta, el JUnit llamará a los métodos de la clase principal. TODO TODO
	 */
//	private int num; // Numerador
//	private int den; // Denominador
//	
//	/** Crea y simplifica (si procede) una fracción
//	 * @param num	Numerador
//	 * @param den	Denominador
//	 */
//	public TestFraccion( int num, int den ) {
//		this.num = num;
//		this.den = den;
//	}
//	
//	/** Devuelve el numerador de una fracción
//	 * @return	Valor del numerador (si hay signo lo tiene el numerador)
//	 */
//	public int getNum() { return num; }
//	
//	/** Devuelve el denominador de una fracción
//	 * @return	Valor del denominador (positivo)
//	 */
//	public int getDen() { return den; }
//	
//	/** Suma dos fracciones
//	 * @param f1	Fracción 1
//	 * @param f2	Fracción 2
//	 * @return	Fracción resultado de la suma de las fracciones 1 y 2
//	 */
//	public static Fraccion suma( Fraccion f1, Fraccion f2 ) {
//		// TODO
//		return null;
//	}
//	
//	/** Resta dos fracciones
//	 * @param f1	Fracción 1
//	 * @param f2	Fracción 2
//	 * @return	Fracción resultado de la resta f1 - f2
//	 */
//	public static Fraccion resta( Fraccion f1, Fraccion f2 ) {
//		// TODO
//		return null;
//	}
//	
//	/** Multiplica dos fracciones
//	 * @param f1	Fracción 1
//	 * @param f2	Fracción 2
//	 * @return	Fracción resultado de la resta f1 - f2
//	 */
//	public static Fraccion multiplica( Fraccion f1, Fraccion f2 ) {
//		// TODO
//		return null;
//	}
//	
//	/** Divide dos fracciones
//	 * @param f1	Fracción 1
//	 * @param f2	Fracción 2
//	 * @return	Fracción resultado de la resta f1 - f2
//	 */
//	public static Fraccion divide( Fraccion f1, Fraccion f2 ) {
//		// TODO
//		return null;
//	}
//	
//	@Override
//	public String toString() {
//		// TODO
//		return null;
//	}
//	
//	// Utilidad: Devuelve el máximo común divisor de 2 números positivos
//	private static int mcd( int num1, int num2 ) {
//		int divisor = num1<num2 ? num1 : num2;  // El número mayor que hay que probar para calcular el MCD es el más pequeño. De ahí hacia abajo
//		int mcd = 1;
//		while (divisor > 1) {
//			if (num1%divisor == 0 && num2%divisor==0) {  // Es divisor común: se añade y se reducen los números
//				mcd *= divisor;
//				num1 /= divisor;
//				num2 /= divisor;
//			}
//			divisor--;
//		}
//		return mcd;
//	}
//	
}