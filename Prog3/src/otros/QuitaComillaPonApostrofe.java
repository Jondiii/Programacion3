package otros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Contexto: tenemos que hacer un proyecto para Bases de Datos I, el cual empezamos a hacer conjuntamente en Drive.
 * Al pulsar la tecla ' Drive mete los caracteres ‘ y ’ en su lugar, y estos SQL no los reconoce. Me daba pereza
 * cambiar todas y cada una de las comillas manualmente, así que he hecho esto.
 * @author Jon
 *
 */
public class QuitaComillaPonApostrofe {

	
	public static void main(String[] args) {
		String stringFinal = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jdiaz\\Desktop\\proyecto.txt"));
			
			String linea;
			
			 try {
					while ((linea = br.readLine()) != null) {
						linea = linea.replace("�f", "'");
						linea = linea.replace("�e", "'");
						stringFinal = stringFinal + linea + "\n";	
						}
					
					System.out.println(stringFinal);
				} catch (IOException e) {
					e.printStackTrace();
				} 
				  try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				  File file = new File("C:\\Users\\jdiaz\\Desktop\\proyectoBien.txt");
			        FileWriter fr = null;
			        BufferedWriter bw = null;
			        try{
			            fr = new FileWriter(file);
			            bw = new BufferedWriter(fr);
			            bw.write(stringFinal);
			         
			        } catch (IOException e) {
			            e.printStackTrace();
			        }finally{
			            try {
			                bw.close();
			                fr.close();
			            } catch (IOException e) {
			                e.printStackTrace();
			            }
			        }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
}
