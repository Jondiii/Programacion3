package examen1819resuelto.editorSprites;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// T1

public class TestAnimacion {

	private static final long PAUSA = 200; 
	
	private VentanaEdicionSprites v;
	@Before
	public void setUp() throws Exception {
		v = new VentanaEdicionSprites();
	}

	@After
	public void tearDown() throws Exception {
		try { Thread.sleep(PAUSA); } catch (Exception e) {}
		v.dispose();
	}

	@Test
	public void testCambioSecuencia() {
		File f1 = new File( "src/examen/ord201901/editorSprites/img/Attack__000.png" );
		v.getController().anyadirSpriteASecuencia( f1 );
		v.setVisible( true );
		try { Thread.sleep(PAUSA); } catch (Exception e) {}
		v.tfVelocidad.setText( "100" );
		v.tfAngulo.setText("25");
		v.bSecuenciaAnim.doClick();
		try { Thread.sleep(PAUSA); } catch (Exception e) {}
		// Chequear que hay movimiento
		boolean estaParado = false;
		int x = v.getController().lAnim.getX(); int y = v.getController().lAnim.getY();
		long tiempo = System.currentTimeMillis();
		while (!estaParado) {
			try { Thread.sleep( 100 ); } catch (Exception e) {}
			int movX = v.getController().lAnim.getX() - x; int movY = v.getController().lAnim.getY() - y;
			x = v.getController().lAnim.getX(); y = v.getController().lAnim.getY();
			assertTrue(movX >= 0); // Se ha movido hacia la derecha
			if (movX==0 && movY==0) estaParado = true;
			// System.out.println( v.getController().lAnim.getX() + " , " + v.getController().lAnim.getY() );
		}
		// System.out.println( v.getController().lAnim.getX() + " , " + v.getController().lAnim.getY() );
		// System.out.println( v.pArena.getWidth() + " -- " + v.pArena.getHeight() );
		assertTrue( x >= v.pArena.getWidth() || y >= v.pArena.getHeight());  // Se ha parado porque se ha salido del panel
		assertTrue( System.currentTimeMillis() - tiempo < 10000 ); // No ha tardado más de 10 segundos
	}
	
	
}
