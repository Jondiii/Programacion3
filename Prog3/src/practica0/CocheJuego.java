package practica0;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CocheJuego extends Coche {
	
	private JLabelCoche auto;
	
	public CocheJuego (JFrame v) {
		auto = new JLabelCoche((int)getPosX(), (int)getPosY());
		JPanel panelJuego = new JPanel();
		panelJuego.add(auto);
		v.add(panelJuego, BorderLayout.CENTER);
		v.revalidate();
	}
	
	public void redibujar(int posX, int posY) {
		auto.setLocation(posX, posY);
	}
	
	public JLabelCoche getAuto() {
		return auto;
	}
}
