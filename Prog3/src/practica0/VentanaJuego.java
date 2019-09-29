package practica0;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaJuego extends JFrame {
	
	private static VentanaJuego v;
	private JPanel botonera;
	private static Coche c;
	
	public static boolean fin_juego;


	/**
	 * Crea una VentanaJuego básica.
	 */
	public VentanaJuego() {
	
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		setSize(750, 750);
		setLocation(200, 100);
		
	};
	
	public void anadirBotones() {

		botonera = new JPanel();
		botonera.setLayout(new FlowLayout());
		
		JButton acc = new JButton("Acelera");
		JButton fren = new JButton("Frena");
		JButton gIzq = new JButton("Gira izq.");
		JButton gDer = new JButton("Gira der.");
		
		botonera.add(acc);
		botonera.add(fren);
		botonera.add(gIzq);
		botonera.add(gDer);
		
		v.add(botonera, BorderLayout.SOUTH);
		
		acc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.acelera(5);
				v.revalidate();

			}
		});
		
		fren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (c.getMiVelocidad() >= 5) {
					c.acelera(-5);
				}
				v.revalidate();

			}
		});
		
		gIzq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.gira(-10);
				v.revalidate();

				}
		});
		
		gDer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.gira(10);
				v.revalidate();
				
				}
		});
	}


	public static void main(String[] args) {
		v = new VentanaJuego();
		v.anadirBotones();
		c = new Coche(v);
		
		v.addWindowListener(new WindowAdapter() { //Detecta cúando la ventana está cerrada. Se usa para terminar automáticamente el hilo.
			
			@Override
			public void windowClosing(WindowEvent e) {
				fin_juego = true;
			}
			
		});
		
		MiHilo hilo = new MiHilo(c);
		hilo.start();

		v.setVisible(true);

}
	
}
/**
 * Clase interna que crea un hilo. Desde que se activa, dibuja cada cierto tiempo el coche, simulando movimiento.
 * Se detiene al cerrar la ventana.
 */
class MiHilo implements Runnable{
	
	private Coche car;
	private boolean funcionando;
	
	public MiHilo(Coche c) {
		this.car = c;
	}
	
	private void update() {
		car.mueve(0.4);
		System.out.println(car);
		
		if(VentanaJuego.fin_juego) {
			stop();
		}
		
	}
	
	@Override
	public void run() {
		
		while(funcionando) {
			update();
			
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void start() {
		funcionando = true;
		Thread juego = new Thread(this);
		juego.start();
	}
	
	public void stop() {
		funcionando = false;
	}
}


