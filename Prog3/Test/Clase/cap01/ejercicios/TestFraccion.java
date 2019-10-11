package Clase.cap01.ejercicios;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/** Ejercicio 1.8.a  planteado (sin codificar ni probar)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class TestFraccion {
	
	Fraccion f;
	Fraccion f2;
	
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
	
	public void testSimplificacion() {
		System.out.println(f);
		System.out.println(1.0/3.0);
		assertEquals(1.0/3, 1.0*f.getNum()/f.getDen(), 0.000000000001);
		assertEquals(new Fraccion(1, 3), f);
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