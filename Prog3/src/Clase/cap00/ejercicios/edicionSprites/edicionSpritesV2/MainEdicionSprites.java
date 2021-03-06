package Clase.cap00.ejercicios.edicionSprites.edicionSpritesV2;

import java.util.Properties;
import java.util.ResourceBundle.Control;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.UIManager;  // Para usar look and feels distintos al estándar

/** Clase principal de edición de sprites<br/>
 * Enlace a un zip con gráficos para sprites de ejemplo:
 * <a href="https://drive.google.com/file/d/1UhqJT1zh_aYzcCgKa_6eRUdQvnqP8k0v/view?usp=sharing">link a fichero comprimido</a>
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class MainEdicionSprites {

	static Properties prf = new Properties();

	/** Método principal, crea una ventana de edición y la lanza 
	 * @param args
	 */
	public static void main(String[] args) {
		try { // Cambiamos el look and feel (se tiene que hacer antes de crear la GUI
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) { } // Si Nimbus no está disponible, se usa el l&f por defecto
		VentanaEdicionSprites v = new VentanaEdicionSprites();
		
		
//		try { //Lo pongo con comentario porque no quiero que se esté creando el ficherito todo el rato. PASO 3
//			ControladorVentanaSprites.log = Logger.getLogger("log_Ventana_Edicion");
//			ControladorVentanaSprites.log.addHandler(new FileHandler("edicionSprites.xml", true)); //True el append, es decir, si está ya creado, añade la info al final de lo que hay.
//		} catch (Exception e) {
//		}
		
		try {//PASO 1
			prf.loadFromXML(new java.io.FileInputStream( "propertiesPractica1.xml" ));
		} catch (Exception e) {
		}
		
		// TODO Sentencias de prueba
		// Estas tres líneas inicializan la secuencia con tres gráficos de ejemplos (sustituir los paths por los gráficos que se deseen)
		// (Para hacer pruebas en cualquier ventana a veces es conveniente inicializar componentes a mano
		// y así se pueden probar cosas sin tener que hacer todos los pasos. Luego se quitan cuando las 
		// pruebas se han acabado)
		v.getController().anyadirSpriteASecuencia( new java.io.File( "D:\\t\\spritesheets\\ninja\\png\\Attack__000.png" ) );
		v.getController().anyadirSpriteASecuencia( new java.io.File( "D:\\t\\spritesheets\\ninja\\png\\Attack__001.png" ) );
		v.getController().anyadirSpriteASecuencia( new java.io.File( "D:\\t\\spritesheets\\ninja\\png\\Attack__002.png" ) );
		v.getController().anyadirSpriteASecuencia( new java.io.File( "D:\\t\\spritesheets\\ninja\\png\\Attack__003.png" ) );
		v.getController().anyadirSpriteASecuencia( new java.io.File( "D:\\t\\spritesheets\\ninja\\png\\Attack__004.png" ) );
		
		
		v.setVisible( true );
	}

}
