package practica0;

import javax.swing.JFrame;

public class Coche {
	private double miVelocidad; // Velocidad en pixels/segundo
	protected double miDireccionActual; // Dirección en la que estoy mirando en grados (de 0 a 360)
	protected double posX; // Posición en X (horizontal)
	protected double posY; // Posición en Y (vertical)
	private String piloto; // Nombre de piloto
	private CocheJuego c;
	
	
	public Coche() {
		this.miVelocidad = 0;
		this.miDireccionActual = 0;
		this.posX = 0;
		this.posY = 0;
		this.piloto = "0";
	}
	
	public Coche(JFrame v) {
		this.miVelocidad = 0;
		this.miDireccionActual = 0;
		this.posX = 0;
		this.posY = 0;
		this.piloto = "0";
		c = new CocheJuego(v);
	}
	
	//TODO Faltan los pasos 5, 6, 8 y 9
	
	/** Cambia la velocidad actual del coche
	 * @param aceleracion Incremento de la velocidad en pixels/segundo
	 */
	 public void acelera( double aceleracion ) {
		 setMiVelocidad(miVelocidad + aceleracion);
		 System.out.println("Velocidad: " + miVelocidad + " píxeles/segundo.");
	 }
	 
	 /** Cambia la dirección actual del coche
	 * @param giro Angulo de giro a sumar o restar de la dirección actual, en grados (-180 a +180)
	 */
	 public void gira( double giro ) {
		 setMiDireccionActual(miDireccionActual + giro);
		 System.out.println("Dirección: " + miDireccionActual);
	 }
	 
	 /** Cambia la posición del coche dependiendo de su velocidad y dirección
	 * @param tiempoDeMovimiento Tiempo transcurrido, en segundos
	 */
	 public void mueve( double tiempoDeMovimiento ) {
		 setPosX(miVelocidad * tiempoDeMovimiento * Math.cos(Math.toRadians(miDireccionActual)));
		 setPosY(miVelocidad * tiempoDeMovimiento * Math.sin(Math.toRadians(miDireccionActual)));
		 c.redibujar((int)posX, (int)posY);
	 } 
	
	@Override
	public String toString() {
		return "Coche [miVelocidad=" + miVelocidad + ", miDireccionActual=" + miDireccionActual + ", posX=" + posX
				+ ", posY=" + posY + ", piloto=" + piloto + "]";
	}

	public double getMiVelocidad() {
		return miVelocidad;
	}

	public void setMiVelocidad(double miVelocidad) {
		this.miVelocidad = miVelocidad;
	}

	public double getMiDireccionActual() {
		return miDireccionActual;
	}

	public void setMiDireccionActual(double miDireccionActual) {
		this.miDireccionActual = miDireccionActual;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public String getPiloto() {
		return piloto;
	}

	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}
}
