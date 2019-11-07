package Clase.EjercicioCompleto;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class VentanaPrincipal extends JFrame {
	
	public VentanaPrincipal ventana;
	public String urlBD =  "miiiamazon.db";
	private JTextArea texto = new JTextArea();
	private ArrayList<Producto> listaProds;
	
	public VentanaPrincipal() {
		ventana = this;
		setLayout(new BorderLayout());
		setSize(500, 500);
		setLocation(150, 150);
		
		JPanel pEntero = new JPanel();
		pEntero.setLayout(new BorderLayout());
		
		JPanel botonera = new JPanel();
		
		JButton bComprar = new JButton("Comprar");
		JButton bCancelarCompra = new JButton("Cancelar compra");
		JButton bVer = new JButton("Ver productos");
		
		botonera.add(bVer);
		botonera.add(bComprar);
		botonera.add(bCancelarCompra);
		
		pEntero.add(botonera);
		
		pEntero.add(texto, BorderLayout.CENTER);
		//Para el JTextArea es mejor añadir un borderlayout, porque sino,
		//por defecto hay flow layout y en ese caso java usa el espacio
		//mínimo que necesita para el componente, por lo que no llega a verse.
		//Si se crea un border layout, se le asigna todo el espacio del borde.
		
		pEntero.add(botonera, BorderLayout.SOUTH);
		
		add(pEntero);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				BaseDatos.abrirConexion(urlBD);
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				BaseDatos.cerrarConexion();
			}
			
		});
		
		bVer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clickVerProductos();
			}
		});
		
		bComprar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clickComprar();
			}
		});
		
	}
	
	public static void main(String[] args) {
		VentanaPrincipal window = new VentanaPrincipal();
		
	}
	
	public void clickVerProductos() {
		listaProds = BaseDatos.getProductos();
		for (Producto producto : listaProds) {
			texto.append(producto.toString() + "\n");
		}
		ventana.revalidate();
	}
	
	public void clickComprar() {
		Producto[] productos = new Producto[2];
		int i = 0;
		for (Producto producto : listaProds) {
			productos[i] = producto;
		}
		JOptionPane.showInputDialog(ventana, null, "Compra", JOptionPane.QUESTION_MESSAGE, null, productos, null);
	}
}
