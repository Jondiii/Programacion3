package practica1;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelCoche extends JLabel {
	
	private String imagen = "coche.png";
	private ImageIcon icon;
	private Coche car;
	private int posX;
	private int posY;
	
//	public JLabelCoche(String img) {
//		imagen = img;
//		int ancho = 100;
//		int alto = 100;
//		
//		car = new Coche();
//		
//		icon = new ImageIcon(imagen);
//		ImageIcon icon2 = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
//		
//		setPreferredSize(new Dimension (ancho, alto));
//		setIcon(icon);
//	}
//	
	public JLabelCoche(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		car = new Coche();
		car.setPosX(posX);
		car.setPosY(posY);
		
		ImageIcon icon = new ImageIcon(getClass().getResource(imagen));
		ImageIcon icono2 = new ImageIcon(icon.getImage().getScaledInstance(100, 100, 0));
		
		setPreferredSize(new Dimension (100, 100));
		setIcon(icono2);
	}



	public Image getImagePorqueJavaNoMeLaDa() {
		return icon.getImage();
	}
	
	public Coche getCoche() {
		return car;
	}

}
