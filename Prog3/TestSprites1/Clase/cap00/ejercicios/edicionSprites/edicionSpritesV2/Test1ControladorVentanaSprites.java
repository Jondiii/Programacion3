package Clase.cap00.ejercicios.edicionSprites.edicionSpritesV2;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class Test1ControladorVentanaSprites {
	
	private VentanaEdicionSprites miVentana;
	
	@Before
	void init() {
		miVentana = new VentanaEdicionSprites();
	}

	@Test
	void cargaFicherosGraficosOrdenados(File dir) {
		miVentana.mSprites.clear();
		if (dir==null || !dir.exists()) return;
		File[] fics = dir.listFiles(); // Ficheros de la carpeta
		Arrays.sort( fics, new Comparator<File>() {  // Ordena los ficheros por nombre
			@Override
			public int compare(File o1, File o2) {
				return o1.getName().compareTo( o2.getName() );
			}
		});
		for (File f2 : fics) { // Recorre los ficheros y añade los pngs
			if (f2.getName().toLowerCase().endsWith("png"))  // Añadir más extensiones si procede
				miVentana.mSprites.addElement( f2 );
		}
	}

}
