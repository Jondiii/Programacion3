package Clase.cap01.ejercicios;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

import javax.swing.*;

/** Ejercicio de pattern: ¿cómo comprobar que una fecha tiene el formato correcto dd/mm/aaaa?
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EjercicioPattern {
	
	/** Comprueba si una fecha es correcta o no según el formato dd/mm/aaaa 
	 * @param fecha	Fecha en un string sin formato comprobado
	 * @return	true si la fecha cumple el formato dd/mm/aaaa, false en caso contrario
	 */
	public static boolean comprobarFecha( String fecha ) {
		Pattern pat1 = Pattern.compile("[0-9][0-9]/[0-9][0-9]/[0-2][0-9][0-9][0-9]");
		//Pattern pat1 = Pattern.compile("\\d\\d/\\d\\d/\\d\\d\\d\\d"); //Opción 2
		if(pat1.matcher(fecha).matches()) {
			return true;
		}
		return false;
	}

	private static JTextField tfEntrada = new JTextField( 10 );
	public static void main(String[] args) {
		// Ventana de ejemplo para el ejercicio
		final JFrame f = new JFrame( "Ventana rápida para ejercicio pattern" );  // Ventana a visualizar
		f.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		f.setSize( 300, 200 );
		f.setLocationRelativeTo( null );
		JPanel pSuperior = new JPanel();
		JPanel pInferior = new JPanel();
		JButton bProbar = new JButton( "Probar" );
		pSuperior.add( new JLabel("Introduce fecha (dd/mm/aaaa):") ); 
		pSuperior.add( tfEntrada );
		pInferior.add( bProbar );
		f.add( pSuperior, BorderLayout.CENTER );
		f.add( pInferior, BorderLayout.SOUTH );
		tfEntrada.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				testFecha();
			}
		});
		bProbar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				testFecha();
			}
		});
		f.setVisible( true );
	}
	
	private static void testFecha() {
		if (!comprobarFecha( tfEntrada.getText() )) 
			JOptionPane.showMessageDialog( null, "Formato incorrecto dd/mm/aaaa", "Error", JOptionPane.ERROR_MESSAGE );
		else
			JOptionPane.showMessageDialog( null, "¡Fecha " + tfEntrada.getText() + " correcta!", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE );
	}

}
