package practica1;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelCoche extends JLabel {
	
	private String imagen;
	private ImageIcon icon;
	private Coche car;
	
	public JLabelCoche(String img) {
		imagen = img;
		int ancho = 100;
		int alto = 100;
		
		car = new Coche();
		
		icon = new ImageIcon(imagen);
//		ImageIcon icon2 = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
//		
//		setPreferredSize(new Dimension (ancho, alto));
		setIcon(icon);
	}



	public Image getImagePorqueJavaNoMeLaDa() {
		return icon.getImage();
	}
	
	public Coche getCoche() {
		return car;
	}

}
