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
	

	public JLabelCoche(int posX, int posY) {

		ImageIcon icon = new ImageIcon(getClass().getResource(imagen));
		ImageIcon icono2 = new ImageIcon(icon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT));
		setPreferredSize(new Dimension (100, 100));
		setLocation((int) posX, (int)posY);
		setIcon(icono2);
		
	}

}
