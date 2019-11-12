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
	 */
	
	private static String nombre = "Prueba";
	
	public static void main(String[] args) {
		prueba1();
	}
	
	private static void prueba1() {
		int n = 5;
		//System.gc(); //No sé por qué lo he puesto.
		f2(0);
		
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
	
	private static int factorial(int n) {
		if (n == 0) return n;
		
		n = n * factorial(n-1);
		
		return n;
	}
	
}
