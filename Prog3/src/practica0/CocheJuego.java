package practica0;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CocheJuego extends Coche {
	
	private JLabelCoche auto;
	
	public CocheJuego (JFrame v) {
		JPanel panelJuego = new JPanel();
		//panelJuego.setLayout(null);
		auto = new JLabelCoche((int)getPosX(), (int)getPosY()); //Toma los valores de las posiciones del objeto Coche ya creado.
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
