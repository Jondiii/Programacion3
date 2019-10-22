package Clase.cap01.ejercicios;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;

/** Ejemplo/ejercicio para mostrar la sintaxis funcional de Java 8
 * Ejercicio: haz que el botón procese la lista de strings que mete el usuario en el cuadro de texto
 * y que muestre los enteros uno a uno (2 segundos cada uno) en el label de mensaje
 * UTILIZANDO EN LO POSIBLE JAVA FUNCIONAL
 */
public class JavaFuncional {

	private static JLabel lSalida = new JLabel( " " );
	private static JTextField tfEntrada = new JTextField( 20 );
	private static JButton bProcesar;
	
	/** Crea ventana de ejemplo con un cuadro de texto y un botón
	 * USAR JAVA FUNCIONAL (NO ACTION LISTENER) TODO TODO
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		// Creación y configuración ventana
		JFrame f = new JFrame( "Ejemplo de lambda en Java 8" );
		f.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// Componentes
		JPanel pEntrada = new JPanel();
		bProcesar = new JButton( "Procesar" );
		pEntrada.add( new JLabel( "lista de enteros entre comas:" ) );
		pEntrada.add( tfEntrada );
		pEntrada.add( bProcesar );
		f.add( pEntrada, BorderLayout.NORTH );
		f.add( lSalida, BorderLayout.SOUTH );
		// Visualizar
		f.pack();
		f.setLocationRelativeTo( null );
		f.setVisible( true );
		
		bProcesar.addActionListener(
				(e) -> {
					procesoBoton();
				});
		
//		bProcesar.addActionListener( //TODO TODO Lo mío
//				(e) -> {ArrayList<Integer> a = listaDeInts(listaDeStrings(tfEntrada.getText()));
//				System.out.println(a);
//					for (int num : a) {
//						lSalida.setText(Integer.toString(num));
//						lSalida.repaint();
//						try {
//							Thread.sleep(760);
//						} catch (Exception e2) {}
//					}
//				});
		
//		A todo esto se le llama Sentencia Lambda.
//		ActionListener obj = new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				//El arg0 registra el objeto de donde viene el suceso. Al
//				//hacer click, se ejcuta el código sobre el arg0, que es de donde viene el suceso.
//				}	
//			};
//		
//		bProcesar.addActionListener(obj);
//
//		Programación funcional: poder usar código igual que datos, es decir, poder
//		guardar código en variables, pasar código como parámetro, devler código en un método...
//		
//		Ahora se puede poner:
//
//		bProcesar.addActionListener( (ActionEvent j) -> {Aquí va código});
//		() => Lo que recibe el código
//		
//		¿Qué ocurre aquí?
//		
//		addActionListener neceita un ActionLitener. Y este solo tiene un método.
//		Como solo hay un método, java interpreta el código como lo que tendría el método.
//		
//		La signatura del código (parámetros y el return) tiene que ser igual que el método actionPerformed.
//		
//		Entonces lo que va entre {} es el código que le corresponde al método actionPerformed.
//		
//		// Como este interfaz tiene más de un método, no
		// vale, porque no se sabría de qué método
		// es el código que se pasa.
		f.addWindowListener( new WindowListener() {	
			@Override
			public void windowOpened(WindowEvent arg0) {}			
			@Override
			public void windowIconified(WindowEvent arg0) {}			
			@Override
			public void windowDeiconified(WindowEvent arg0) {}			
			@Override
			public void windowDeactivated(WindowEvent arg0) {}			
			@Override
			public void windowClosing(WindowEvent arg0) {}
			@Override
			public void windowClosed(WindowEvent arg0) {}
			@Override
			public void windowActivated(WindowEvent arg0) {}
		});
//		
//		Esto daría error: f.affWindowsListener( (e) -> {} );
//		
//		
//		
//		
//		
	}
	
	private static void procesoBoton() {
		ArrayList<String> lStrings = listaDeStrings(tfEntrada.getText());
		ArrayList<Integer> lEnteros = listaDeInts(lStrings);
		if(lEnteros == null || lEnteros.isEmpty()) return;
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//			}
//		});
		
		Thread t = new Thread(
			() -> {//No ponemos nada en el () porque no recibe nada el método run.
			//Lo que queramos que haga el hilo.
				bProcesar.setEnabled(false);
				for (int i : lEnteros) {
					lSalida.setText(i + "");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {}
					}
					lSalida.setText("");
					bProcesar.setEnabled(true);
			}
		);
		
		t.start();
	}
	
	/** Devuelve un arraylist de strings partiendo de un string con comas
	 * @param lista	Lista de substrings separados por comas
	 * @return	Devuelve una lista de strings separando los substrings que estén con comas (quitando los espacios)
	 */
	private static ArrayList<String> listaDeStrings( String lista ) {
		ArrayList<String> ret = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer( lista, "," );
		while (st.hasMoreTokens()) {
			ret.add( st.nextToken().trim() );  // Mete el siguiente substring quitando espacios
		}
		return ret;
	}
	
	/** Devuelve una lista de enteros partiendo de una lista de strings
	 * @param lista	Lista de strings que representan a enteros
	 * @return	Lista de los enteros en la lista de strings (si algún string no es un entero válido, se ignora)
	 */
	private static ArrayList<Integer> listaDeInts( ArrayList<String> lista ) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (String string : lista) {
			try {
				ret.add( Integer.parseInt( string ) );
			} catch (NumberFormatException e) {
				// Se ignora el string que no es un entero válido
			}
		}
		return ret;
	}

}
