package Clase.cap00.ejercicios;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Ejercicio de hilos con ventanas. Programa esta clase para que se cree una ventana
 * que pida un dato de texto al usuario y un botón de confirmar para que se confirme.
 * Haz que al pulsar el botón de confirmación se llame al procesoConfirmar()
 * que simula un proceso de almacenamiento externo que tarda unos segundos.
 * Observa que hasta que ocurre esta confirmación la ventana no responde.
 * 1. Arréglalo para que la ventana no se quede "frita" hasta que se acabe de confirmar.
 * 2. Haz que el botón de "confirmar" no se pueda pulsar dos veces mientras el proceso
 * de confirmación se esté realizando
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class VentanaConfirmacionLenta {

		private static Random r = new Random();
	// Este método simula un proceso que tarda un tiempo en hacerse (entre 5 y 10 segundos)
	private static void procesoConfirmar() {
		try {
			Thread.sleep( 5000 + 1000*r.nextInt(6) );
		} catch (InterruptedException e) {}
	}
	
	/**
	 * Main para crear la ventana, text field, botón y los paneles necesarios.
	 */
	public static void main(String[] args) {
		JFrame v = new JFrame();
		v.setSize(500, 500);
		v.setDefaultCloseOperation(v.DISPOSE_ON_CLOSE);

		JPanel pPrincipal = new JPanel();
		JTextField texto = new JTextField(20);
		pPrincipal.add(texto);
		JButton bConf = new JButton("Confirmar");
		JPanel pBoton = new JPanel();
		pBoton.add(bConf);
		
		v.add(pPrincipal, BorderLayout.CENTER);
		v.add(pBoton, BorderLayout.SOUTH);
		v.setVisible(true);
		
		bConf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Al llegar a aquí, se crea un hilo nuevo que se encarga de gestionar el procesoConfirmar, y deja que 
				//la ventana haga otras cosas de mientras
				Thread hilo = new Thread() {
					public void run() {
						System.out.println("empiezo");
						 procesoConfirmar();				
						 System.out.println("acabo");
					}
				};
				
				//El start habre un nuevo hilo (a parte del que ya había antes, es decir, el main) y ejecuta de manera independiente
				//el hilo.
				hilo.start();
				
			}
		});
	}

}
