package practica1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaJuego extends JFrame {
	
	JFrame ventana;
	JPanel pPrincipal;
	JPanel botonera;
	private static CocheJuego c;
	private Graphics2D graficos;
	private BufferedImage buffer;


	
	public VentanaJuego() {
		ventana = new JFrame();
		
		buffer = new BufferedImage(3000, 3000, BufferedImage.TYPE_INT_ARGB);
		graficos = buffer.createGraphics();
		
		ventana.setLayout(new BorderLayout());
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		pPrincipal = new JPanel();
//		pPrincipal.setLayout(null);
//		ventana.add(pPrincipal, BorderLayout.CENTER);
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
		
		ventana.add(botonera, BorderLayout.SOUTH);
		ventana.setSize(750, 750);
	
//		JLabelCoche car2 = new JLabelCoche("data/coche.png");
//		pPrincipal.add(car2);
//		graficos.drawImage(car2.getImagePorqueJavaNoMeLaDa(), 50, 50, 100, 100, null);
		ventana.setVisible(true);
		
		acc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.getAuto().getCoche().acelera(5);	
			}
		});
		
		fren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.getAuto().getCoche().acelera(-5);	
			}
		});
		
		gIzq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.getAuto().getCoche().gira(-10);
				}
		});
		
		gDer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.getAuto().getCoche().gira(10);
				}
		});
	}
	
	public static void main(String[] args) {
		JFrame v = new VentanaJuego();
		c = new CocheJuego(v);
		c.setPosX(150);
		c.setPosY(100);
		
	}
	

	 private class hiloCoche implements Runnable {

		@Override
		public void run() {
			ventana.repaint();
			try {
				wait(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

