package Clase.cap04;

public class ExploracionRecursiva {
	
	/**
	 * Cada vez que se hace una llamada a un método se crea un espacio en memoria para las variables locales de ese método.
	 * Una vez se hace return del método, estas variables se borran y el espacio se libera. 
	 * 
	 * Estos espacios funcionan como una pila. Last input first output. Primero se llama al main, se le asigna su espacio,
	 * luego al método f(), y se le asigna su espacio. El método f() llama a g(), al que se le asigna su espacio, y una vez
	 * se haga return de g(), se borra su espacio asignado, que es el último que se ha añadido a la pila. Cada vez que se hace
	 * return de un método se borra su espacio asignado, y como las variables locales del método no son accesibles desde fuera
	 * se borran también. 
	 * 
	 * Fibonacci de 5: 4 + 3 -> 3 + 2, 2 + 1 -> 2 + 1, 1 + 0, 1 + 0, 1 -> 1 + 0, 1, 1, 0, 1, 0, 1
	 */
	
	private static String nombre = "Prueba";
	
	public static void main(String[] args) {
		hanoi(3);
		busquedaBinaria();
	}
	
	private static void prueba1() {
		//System.gc(); //No sé por qué lo he puesto.

		long n = factorial(5);
		
		System.out.println(n);
	}
	
	private static void f(int n) {
		System.out.println(n);
		f( n+1 ); //Esto da un stack overflow. Se están creando variables n constantemente que se acumulan en la pila,
	}			  //hasta que la memoria de pila se llena. Hace falta un caso base.

	
	private static void f2(int n) {
		if(n<1000) { //Este es el caso recursivo, el else que no está sería el caso base, es decir, el que lo detiene.
			System.out.println(n);
			f2(n+1);
		}
	}
	
	private static void fIterativo(int n) { //Al final es esto lo que está haciendo f2. El problema es que f2 gasta muchísima memoria.
		while(n<1000) {						//La recursividad además es más lenta que los bucles, por lo que hay coste de eficiencia.
			System.out.println(n);			//
			n = n + 1;
		}
	}
	
	private static void f3(int n) {
		if(n<1000) { //En este programa se llama contínuamente a f3, por lo que el código, hasta el final no llega al print
			f3(n+1); //por lo que se visualizan los números del 0 al 999 pero en orden descendente (primero 999, luego 998...)
			System.out.println(n);
		}
	}
	
	private static void f4(int n) {//Esto muestra más claramente lo que pone en f3. Como hemos dicho antes, para esto se consume
		if(n<1000) { 			   //mucha memoria, pues se crean mil variables n.
			System.out.println( "antes " + n);
			f4(n+1); 
			System.out.println("después" + n);
		}
	}
	
	private static long factorial(long n) {
		if (n == 0) return 1;
		else {
			n = n * factorial(n-1);
		}
		return n;
	}
	
	private static void factorial2(int nInicial, long valorInicial, int nFinal) {
		if(nInicial == nFinal) {
			System.out.println(valorInicial);
		} else {
			factorial2(nInicial+1, valorInicial*(nInicial+1), nFinal);
		}
	}
	
	/**
	 * Calcula el producto de m y n utilizando solo sumas
	 * m * n = m + m*(n-1)
	 */
	private static int prodConSuma(int m, int n) {
		if(n == 0) return 0;
		else {
			return m + prodConSuma(m, n-1);
		}
	}
	
	private static void aLaLuna() {
		calcDobleces(0.0001, 384400000.0, 0);
	}
	
	private static void calcDobleces(double grosor, double distancia, int numDobleces) {
		if(grosor >= distancia) {
			System.out.println(numDobleces);
		} else {
			calcDobleces(grosor*2, distancia, numDobleces+1);
		}
	}
	
	//Hacer: f(n) = f(n-1)+f(n-2); buscar caso base
	private static int fibonacci( int n) {
		if(n==1) {
			return 1;
		} else if (n==2) {
			return 1;
		} else {
			return fibonacci(n-1) + fibonacci(n-2);
		}
	}
	
	private static void hanoi(int num) {
		hanoiRec(num, 'A', 'B', 'C');
	}
	
	private static void hanoiRec(int tam, char origen, char destino, char auxiliar) {
		if (tam == 1) { //Caso Base
			System.out.println("Muevo el disco 1 de " + origen + " a " + destino) ;
		} else {
		hanoiRec(tam - 1, 'A', auxiliar, destino);
		System.out.println("Muevo el disco " + tam + " de " + origen + " a " + destino) ;
		hanoiRec(tam-1, auxiliar, destino, origen);
		}
	}
	
	private static void busquedaBinaria() {
		int[] v = {1, 2, 10, 11, 15, 21, 43, 57, 83, 84, 85, 86, 87, 89, 110};
		System.out.println(busq(v, 110, 0, v.length - 1));
	}
	
	
	//Busca el valorBuscado en el vector v entre las posiciones ini y fin, ambas inclusive.
	//Devuelve la posición del vector si se encuentra el valor, devuelve -1 si no.
	//Calculamos la mitad 
	//Comparamos el valor buscado con el que hay en la mitad
	//a) son iguales - CB
	//b) El de la mitad es menor: buscar recursivamente en la mitad superior
	//c) El de la mitad es mayor: buscar recursivamente en la mitad inferior
	private static int busq(int[] v, int valorBuscado, int primeraPos, int ultPos) {
		if (primeraPos>ultPos) return -1;
		int mitad = (primeraPos + ultPos)/2; //int devuelve un entero
		if (v[mitad] == valorBuscado) {
			return mitad;
		} else if (v[mitad]<valorBuscado) {
			return busq(v, valorBuscado, mitad+1, ultPos);
		} else {
			return busq(v, valorBuscado, primeraPos, mitad-1);
		}
		
	}
}
