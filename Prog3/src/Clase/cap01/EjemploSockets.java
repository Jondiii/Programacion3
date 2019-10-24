package Clase.cap01;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** Ejemplo de utilización de sockets para comunicar un programa "servidor"
 * con un "cliente" en el mismo equipo. El cliente puede enviar textos
 * al servidor, que envía un mensaje de confirmación con cada texto.
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EjemploSockets {

	private static String HOST = "localhost";  // IP de conexión para la comunicación
	private static int PUERTO = 4000;          // Puerto de conexión
	
	private static VentanaServidor vs;			//El server es quién se "abre" a otras conexiones. Puede establecer conexión con varios clientes a la vez.
	private static VentanaCliente vc;
	public static void main(String[] args) {
		vs = new VentanaServidor();
		vs.setVisible( true );
		(new Thread() {
			@Override
			public void run() {
				vs.lanzaServidor();
			}
		}).start();
		vc = new VentanaCliente();
		vc.setVisible( true );
		(new Thread() {
			@Override
			public void run() {
				vc.lanzaCliente();
			}
		}).start();
	}

	@SuppressWarnings("serial")
	public static class VentanaCliente extends JFrame { //EJERCICIO: Modificar programa. Si el cliente dice "hora" el server devuelve la hora.
		JLabel lEstado = new JLabel( " " );
		JTextField tfMensaje = new JTextField( "Introduce tu mensaje y pulsa <Enter>" );
        PrintWriter flujoOut;
        boolean finComunicacion = false;
		public VentanaCliente() {
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 400, 300 );
			setLocation( 0, 0 );
			setTitle( "Ventana cliente - 'fin' acaba" );
			getContentPane().add( tfMensaje, BorderLayout.NORTH );
			getContentPane().add( lEstado, BorderLayout.SOUTH );
			tfMensaje.addFocusListener( new FocusAdapter() { // Selecciona todo el texto del cuadro en cuanto se le da el foco del teclado
				@Override
				public void focusGained(FocusEvent e) {
					tfMensaje.selectAll();
				}
			});
			tfMensaje.addActionListener( new ActionListener() { // Evento de <enter> de textfield
				@Override
				public void actionPerformed(ActionEvent e) { //Aquí se manda algo al server.
					flujoOut.println( tfMensaje.getText() );
					if (tfMensaje.getText().equals("fin"))
						finComunicacion = true;
					tfMensaje.setText( "" );
				}
			});
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					flujoOut.println( "fin" );
					finComunicacion = true;
				}
			});
		}
	    public void lanzaCliente() {
	        try (Socket socket = new Socket( HOST, PUERTO )) { //En el cliente se usa un socket, en el server un server socket. Le pones la IP del servidor
	            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            flujoOut = new PrintWriter(socket.getOutputStream(), true);
	            do {
	            	String feedback = echoes.readLine();  // Devuelve mensaje de servidor o null cuando se cierra la comunicación
	            	if (feedback!=null) {
	            		lEstado.setText( feedback );
	            	} else {  // Comunicación cortada por el servidor o por error en comunicación
	            		finComunicacion = true;
	            	}
	            } while(!finComunicacion);
	        } catch (IOException e) {
            	lEstado.setText( "Error en cliente: " + e.getMessage());
	        }
	        lEstado.setText( "Fin de proceso de cliente." );
	    }
	}
	    
	@SuppressWarnings("serial")
	public static class VentanaServidor extends JFrame { //Muchas veces los servers no tienen interfaz gráfico. Suelen tener un log.
		JLabel lEstado = new JLabel( " " );				//Primero se lanza el server.
		JTextArea taMensajes = new JTextArea( 10, 1 );
        boolean finComunicacion = false;
        Socket socket;
		public VentanaServidor() {
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 400, 300 );
			setLocation( 400, 0 );
			setTitle( "Ventana servidor" );
			getContentPane().add( new JScrollPane(taMensajes), BorderLayout.CENTER );
			getContentPane().add( lEstado, BorderLayout.SOUTH );
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					try {
						socket.close();
					} catch (IOException e1) {
			    		lEstado.setText("Error en servidor: " + e1.getMessage());
					}
					finComunicacion = true;
				}
			});
		}
	    public void lanzaServidor() {
	    	try(ServerSocket serverSocket = new ServerSocket( PUERTO )) {	//El puerto es el canal por el que te comunicas. Son 16 bits (creo)
	    		socket = serverSocket.accept();
	    		lEstado.setText( "Cliente conectado" );
	    		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Canal de comunicación de entrada. Para imagen se pondría object input stream
	    		PrintWriter output = new PrintWriter(socket.getOutputStream(), true);						//Canal de comunicación de salida.
	    		while(!finComunicacion) {
	    			String textoRecibido = input.readLine(); //Está aquí esperando a que el cliente le diga algo
	    			if(textoRecibido.equals("fin")) {		//Para límite de tiempo poner un timeout. Estos se definen sobre el socket
	    				break;
	    			}
	    			lEstado.setText( "Recibido de cliente: [" + textoRecibido + "]" );
	    			taMensajes.append( textoRecibido + "\n" );
	    			taMensajes.setSelectionStart( taMensajes.getText().length() );
	    			output.println("Recibido: [" + textoRecibido + "]" );
	    		}
	    		lEstado.setText( "El cliente se ha desconectado." );
	    		socket.close();
	    	} catch(IOException e) {
	    		lEstado.setText("Error en servidor: " + e.getMessage());
	    	}
	    }
	}

}
