package Clase.cap01.ejercicios;

/** Ejercicio 1.8.a  planteado (sin codificar ni probar)
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class Fraccion {
	
	/** Método de prueba de la clase fracción
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		Fraccion f = new Fraccion( 2, 6 ); // 2/6 = 1/3
		System.out.println( f );
		Fraccion f2 = new Fraccion( 1, -5 ); // 1/-5 = -1/5
		System.out.println( f2 );
		System.out.println( suma(f,f2) ); // 1/3 + (-1/5) = 2/15
		System.out.println( resta(f,f2) ); // 1/3 - (-1/5) = 8/15
		System.out.println( multiplica(f,f2) ); // 1/3 * (-1/5) = -1/15
		System.out.println( divide(f,f2) ); // 1/3 / (-1/5) = -5/3
	}
	
	private int num; // Numerador
	private int den; // Denominador
	
	/** Crea y simplifica (si procede) una fracción
	 * @param num	Numerador
	 * @param den	Denominador
	 */
	public Fraccion( int num, int den ) {
		this.num = num;
		this.den = den;
		this.num /= mcd(num, den);
		this.den /= mcd(num, den);
		
		//Excepción al construir fraccion con den = 0. Igual con la división de fracciones.
	}
	
	/** Devuelve el numerador de una fracción
	 * @return	Valor del numerador (si hay signo lo tiene el numerador)
	 */
	public int getNum() { return num; }
	
	/** Devuelve el denominador de una fracción
	 * @return	Valor del denominador (positivo)
	 */
	public int getDen() { return den; }
	
	/** Suma dos fracciones
	 * @param f1	Fracción 1
	 * @param f2	Fracción 2
	 * @return	Fracción resultado de la suma de las fracciones 1 y 2
	 */
	public static Fraccion suma( Fraccion f1, Fraccion f2 ) {
		if (f1.getDen() == f2.getDen()) {
			int sumaNum = f1.getNum() + f2.getNum();
			if (sumaNum / f1.getDen() != 0) {
				return new Fraccion(sumaNum, f1.getDen());
			} else {
				
			}
		}
		return null;
	}
	
	/** Resta dos fracciones
	 * @param f1	Fracción 1
	 * @param f2	Fracción 2
	 * @return	Fracción resultado de la resta f1 - f2
	 */
	public static Fraccion resta( Fraccion f1, Fraccion f2 ) {
		// TODO
		return null;
	}
	
	/** Multiplica dos fracciones
	 * @param f1	Fracción 1
	 * @param f2	Fracción 2
	 * @return	Fracción resultado de la multiplicación f1 * f2
	 */
	public static Fraccion multiplica( Fraccion f1, Fraccion f2 ) {
		int multNum = f1.getNum() * f2.getNum();
		int multDen = f1.getDen() * f2.getNum();
		return new Fraccion(multNum, multDen);
	}
	
	/** Divide dos fracciones
	 * @param f1	Fracción 1
	 * @param f2	Fracción 2
	 * @return	Fracción resultado de la resta f1 - f2
	 */
	public static Fraccion divide( Fraccion f1, Fraccion f2 ) {
		int divNum = f1.getNum() * f2.getDen();
		int divDen = f1.getNum() * f2.getNum();
		return new Fraccion(divNum, divDen);
	}
	
	@Override
	public String toString() {
		// TODO
		return num + "/" + den;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Fraccion) {
			Fraccion fracTemp = (Fraccion)obj;
			return num == fracTemp.getNum() && den == fracTemp.getDen();
		} else {
			return false;
		}
	}
	
	// Utilidad: Devuelve el máximo común divisor de 2 números positivos
	private static int mcd( int num1, int num2 ) {
		int divisor = num1<num2 ? num1 : num2;  // El número mayor que hay que probar para calcular el MCD es el más pequeño. De ahí hacia abajo
		int mcd = 1;
		while (divisor > 1) {
			if (num1%divisor == 0 && num2%divisor==0) {  // Es divisor común: se añade y se reducen los números
				mcd *= divisor;
				num1 /= divisor;
				num2 /= divisor;
			}
			divisor--;
		}
		return mcd;
	}
	
}