package examen1819resuelto.sesionesCentros;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import examen1819resuelto.sesionesCentros.Datos.Sesion;

/** Clase de ventana para muestra de datos de centros escolares y feedback de mentoras
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
@SuppressWarnings("serial")
public class VentanaDatos extends JFrame {
	
	private JTable tDatos;  // JTable de datos de la ventana
	// T5
	private JLabel lMensaje;  // Label de mensaje
	
	/** Crea una nueva ventana
	 */
	public VentanaDatos() {
		// Configuración general
		setTitle( "Ventana de datos" );
		setSize( 800, 600 ); // Tamaño por defecto
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		// Creación de componentes y contenedores
		JPanel pBotonera = new JPanel();
		tDatos = new JTable();
		JButton bCargaFeedback = new JButton( "Carga feedback" );
		JButton bGuardaBD = new JButton( "Guardar en BD" );
		JButton bBuscaMentora = new JButton( "Buscar mentora" );
		JButton bT2 = new JButton( "T2" );
		JButton bT3 = new JButton( "T3" );
		// T5
		lMensaje = new JLabel( " " );
		// Asignación de componentes
		pBotonera.add( bCargaFeedback );
		pBotonera.add( bGuardaBD );
		pBotonera.add( bBuscaMentora );
		pBotonera.add( bT2 );
		pBotonera.add( bT3 );
		getContentPane().add( new JScrollPane( tDatos ), BorderLayout.CENTER );
		getContentPane().add( pBotonera, BorderLayout.SOUTH );
		getContentPane().add( lMensaje, BorderLayout.NORTH );
		// Eventos
		bCargaFeedback.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickCargaFeedback();
			}
		});
		bGuardaBD.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickGuardaBD();
			}
		});
		bBuscaMentora.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickBuscarMentora();
			}
		});
		bT2.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickT2();
			}
		});
		bT3.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickT3();
			}
		});
		// Cierre
		setLocationRelativeTo( null );  // Centra la ventana en el escritorio
		// T5
		tDatos.setDefaultRenderer( Object.class, new DefaultTableCellRenderer() {
			private JProgressBar pb = new JProgressBar( 0, 500 );
			private JLabel lVacia = new JLabel( "" );
			private JLabel lError = new JLabel( "ERROR" );
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				if (column==2 || column==3) {
					try {
						if (value.toString().isEmpty()) return lVacia;
						double val = Double.parseDouble( value.toString() ); 
						pb.setValue( (int) val*100 );
						return pb;
					} catch (Exception e) {}
					return new JLabel( "Error" );
				} else {
					Component comp = super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
					if (value instanceof String) {
						String string = (String) value;
						comp.setBackground( Color.white );
						comp.setForeground( Color.black );
						((JLabel)comp).setHorizontalAlignment( JLabel.LEFT );
						if ("0".equals(string)) {
							comp.setForeground( Color.LIGHT_GRAY ); 
							((JLabel)comp).setHorizontalAlignment( JLabel.CENTER );
						}
						if (column==1 || column>=4 && column<=9) {
							((JLabel)comp).setHorizontalAlignment( JLabel.CENTER );
						}
					}
					return comp;
				}
			}
		} );
		tDatos.addMouseMotionListener( new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int fila = tDatos.rowAtPoint( e.getPoint() );
				int columna = tDatos.columnAtPoint( e.getPoint() );
				if (columna==2 || columna==3) {
					if (tDatos.getModel().getValueAt( fila, columna ).toString().isEmpty()) {
						lMensaje.setText( " " );
					} else {
						lMensaje.setText( "Valor de satisfacción: " + tDatos.getModel().getValueAt( fila, columna )  );
					}
				} else {
					lMensaje.setText( " " );
				}
			}
		});
		tDatos.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()>=2) { // Doble click
					int fila = tDatos.rowAtPoint( e.getPoint() );
					int columna = tDatos.columnAtPoint( e.getPoint() );
					if (columna==0) {
						String centro = tDatos.getModel().getValueAt( fila, columna ).toString();
						CentroEd centroEd = Datos.centros.get( centro );
						JOptionPane.showMessageDialog( VentanaDatos.this, "Centro " + centro + "\nAbreviaturas: " + centroEd.getlAbrevs(), "Información de centro", JOptionPane.INFORMATION_MESSAGE );
						centroEd.getlAbrevs();
					} else {
						lMensaje.setText( " " );
					}
				}
			}
		});
	}
	
	/** Asigna una tabla de datos a la JTable principal de la ventana
	 * @param tabla	Tabla de datos a visualizar
	 */
	public void setTabla( Tabla tabla ) {
		tDatos.setModel( tabla.getTableModel() );
	}
	
	// ========================================
	// Eventos
	
	// Click botón de guardado en Base de Datos
	private void clickGuardaBD() {
		Connection conn = BD.initBD( "centrosEd.bd" );
		Statement stat = BD.usarCrearTablasBD( conn );
		if (stat==null) return; // Error
		for (CentroEd centro : Datos.centros.values()) {
			if (centro.getContSesiones().get()>0) { // Solo se actualizan los centros con sesiones
				// Tabla centros
				boolean existe = BD.centroSelect( stat, centro.getCodigo() );
				if (existe) {
					BD.centroUpdate( stat, centro );
				} else {
					BD.centroInsert( stat, centro );
				}
				// Tabla sesiones
				for (int numSes=0; numSes<6; numSes++) {
					int numEsts = BD.sesionSelect( stat, centro.getCodigo(), numSes+1 );
					if (numEsts==-1) {
						BD.sesionInsert( stat, centro.getCodigo(), numSes+1, centro.getEstudPorSesion()[numSes] );
					} else {
						BD.sesionUpdate( stat, centro.getCodigo(), numSes+1, centro.getEstudPorSesion()[numSes] );
					}
				}
			}
		}
		BD.cerrarBD( conn,  stat );
	}
	
	// T2
	private void clickT2() {
		System.out.println( "t2" );
		HashMap<String,TreeSet<Sesion>> mentorasEstuds = new HashMap<String,TreeSet<Datos.Sesion>>();
		try {
			Tabla mentoras = Tabla.processCSV( VentanaDatos.class.getResource( "Mentoring2018.csv" ).toURI().toURL() );
			int columnaFecha = mentoras.getColumnWithHeader( "Fecha", false );
			int columnaEmail = mentoras.getColumnWithHeader( "email", false );
			int columnaNumEsts = mentoras.getColumnWithHeader( "Nº de chicas/os", false );
			for (int fila=0; fila<mentoras.size(); fila++) {
				try {
					long fecha = Datos.sdf.parse( mentoras.get( fila, columnaFecha ) ).getTime();
					String email = mentoras.get( fila,  columnaEmail );
					int numEstuds = Integer.parseInt( mentoras.get( fila, columnaNumEsts ) );
					TreeSet<Datos.Sesion> estSesiones = mentorasEstuds.get( email );
					if (estSesiones==null) {
						estSesiones = new TreeSet<Datos.Sesion>();
						mentorasEstuds.put( email, estSesiones );
					}
					estSesiones.add( new Datos.Sesion( numEstuds, fecha ) );
				} catch (Exception e) {  // Error en fecha
					System.err.println( "Error de fecha: " + mentoras.get( fila, columnaFecha ) );
				}
			}
			System.out.println( "Avisos a mentoras:" );
			for (String mentora : mentorasEstuds.keySet()) {
				TreeSet<Datos.Sesion> ts = mentorasEstuds.get( mentora );
				int minimo = Integer.MAX_VALUE;
				int maximo = Integer.MIN_VALUE;
				boolean espaciadas = false;
				long fechaAnt = Long.MAX_VALUE;  // Para empezar a comparar las fechas
				for (Datos.Sesion sesion : ts) {
					if (sesion.getEstudiantes()>maximo) maximo = sesion.getEstudiantes();  // Actualiza máximo
					if (sesion.getEstudiantes()<minimo) minimo = sesion.getEstudiantes();  // Actualiza mínimo
					if (sesion.getFecha()-fechaAnt > 21*24*3600000L) espaciadas = true;
					fechaAnt = sesion.getFecha();
				}
				if (espaciadas || maximo-minimo > 2) {  // Generar aviso en consola
					System.out.println( mentora + " --> " + ts );
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	// T3
	private void clickT3() {
		Connection conn = BD.initBD( "centrosEd.bd" );
		Statement stat = BD.usarCrearTablasBD( conn );
		try {
			Statement stat2 = conn.createStatement();  // Para las consultas anidadas
			String sentSQL = "select * from centroEd order by satEst;";
			ResultSet rs = stat.executeQuery( sentSQL );
			int numCentros = 0;
			while (rs.next() && numCentros<10) {
				numCentros++;
				String codCentro = rs.getString( "cod" );
				double satEst = rs.getDouble( "satEst" );
				String sql2 = "select * from centrosesion where cod ='" + codCentro + "' order by numSes;";
				ResultSet rs2 = stat2.executeQuery( sql2 );
				String sesiones = "";
				while (rs2.next()) {
					int numSes = rs2.getInt( "numSes" );
					int numEst = rs2.getInt( "numEst" );
					if (numEst!=0) {
						sesiones += ("  Sesión " + numSes + ": " + numEst + " estudiantes.\n" );
					}
				}
				rs2.close();
				System.out.println( codCentro + " - " + satEst + "\n" + sesiones );
			}
			stat2.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BD.cerrarBD( conn,  stat );
	}
	
	
	// Click botón de búsqueda de mentora
	private void clickBuscarMentora() {
		String aBuscar = JOptionPane.showInputDialog( this, "Introduce email", "Búsqueda de mentora", JOptionPane.QUESTION_MESSAGE );
		if (aBuscar==null || aBuscar.isEmpty()) return;
		ArrayList<MentoraCentro> listaMentorasCentros = new ArrayList<>();
		try {
			Tabla mentoras = Tabla.processCSV( VentanaDatos.class.getResource( "Mentoring2018.csv" ).toURI().toURL() );
			int columnaCentro = mentoras.getColumnWithHeader( "COD", true );
			int columnaEmail = mentoras.getColumnWithHeader( "email", false );
			for (int fila=0; fila<mentoras.size(); fila++) {
				try {
					String codCentro = mentoras.get( fila, columnaCentro );
					String email = mentoras.get( fila,  columnaEmail );
					if (!email.isEmpty() && !codCentro.isEmpty()) {
						MentoraCentro mc = new MentoraCentro( email, codCentro );
						int yaEsta = listaMentorasCentros.indexOf( mc );
						if (yaEsta==-1)
							listaMentorasCentros.add( mc );  // Nueva mentora-centro: meterla en la lista
						else
							listaMentorasCentros.get( yaEsta ).getContSesiones().inc();  // Ya estaba: incrementar contador de sesiones
					}
				} catch (Exception e) {}
			}
			listaMentorasCentros.sort( new Comparator<MentoraCentro>() {  // Ordena la lista por emails y luego por centros
				@Override
				public int compare(MentoraCentro o1, MentoraCentro o2) {
					return (o1.email+o1.codCentro).compareTo( o2.email+o2.codCentro );
				}
			});
			for (MentoraCentro mc : listaMentorasCentros) System.out.println( mc );  // Visualiza las mentoras en consola (a efectos de entender la estructura en el examen)
			contLlamadas = 0;
			int posi = buscarMentoraRec( listaMentorasCentros, aBuscar, 0, listaMentorasCentros.size()-1 );
			String mens = "";
			while (posi>=0 && posi<listaMentorasCentros.size() && listaMentorasCentros.get(posi).getEmail().equals( aBuscar )) {
				mens = mens + listaMentorasCentros.get(posi).getCodCentro() + " - " + listaMentorasCentros.get(posi).getContSesiones() + "\n";
				posi++;
			}
			if (mens.isEmpty()) mens = "No encontrada";
			JOptionPane.showMessageDialog( this, mens, "Sesiones de mentora " + aBuscar, JOptionPane.INFORMATION_MESSAGE );
			System.out.println( "Número de llamadas recursivas: " + contLlamadas + " (tamaño de la lista = " + listaMentorasCentros.size() + ")" );
		} catch (Exception e) { e.printStackTrace(); }
	}
	
		private static int contLlamadas = 0;
		private int buscarMentoraRec( ArrayList<MentoraCentro> listaMC, String emailABuscar, int desde, int hasta ) {
			contLlamadas++;
			if (desde>=hasta) {  // Caso base: encontrado elemento o no
				if (desde==hasta && listaMC.get(desde).getEmail().equals(emailABuscar)) return desde;  // Búsqueda exitosa
				return -1;  // Búsqueda no exitosa
			} else {
				int medio = (desde + hasta) / 2;
				MentoraCentro mc = listaMC.get( medio );
				if (mc.getEmail().compareTo(emailABuscar)>=0) {  // Email del medio >= email a buscar
					return buscarMentoraRec( listaMC, emailABuscar, desde, medio );
				} else {  // Email del medio < email a buscar
					return buscarMentoraRec( listaMC, emailABuscar, medio+1, hasta );
				}
			}
		}
	
		/** Clase interna de gestión de mentora-centro con contador de sesiones de esa mentora en ese centro */
		public static class MentoraCentro {
			public String email;
			public String codCentro;
			public Contador contSesiones;
			/** Crea un nuevo objeto mentora-centro con contador de sesiones a uno
			 * @param email	Email de la mentora
			 * @param codCentro	Código del centro
			 */
			public MentoraCentro(String email, String codCentro) {
				super();
				this.email = email;
				this.codCentro = codCentro;
				this.contSesiones = new Contador(1);
			}
			public Contador getContSesiones() { return contSesiones; }
			public String getEmail() { return email; }
			public String getCodCentro() { return codCentro; }
			@Override
			public boolean equals(Object obj) {
				if (!(obj instanceof MentoraCentro)) return false;
				MentoraCentro mc = (MentoraCentro) obj;
				return mc.codCentro.equals(codCentro) && mc.email.equals(email);
			}
			@Override
			public String toString() {
				return "{" + email + "-" + codCentro + " : " + contSesiones + "}";
			}
		}

	// Click botón de carga de feedback
	private void clickCargaFeedback() {
		// Cálculo de datos en función del seguimiento del mentoring
		seguimientoSesiones();
	}
	
	private static void seguimientoSesiones() {
		try {
			Tabla mentoras = Tabla.processCSV( VentanaDatos.class.getResource( "Mentoring2018.csv" ).toURI().toURL() );
			int columnaSesion = mentoras.getColumnWithHeader( "Número de sesión", false );
			int columnaCentro = mentoras.getColumnWithHeader( "COD", true );
			int columnaNumEsts = mentoras.getColumnWithHeader( "Nº de chicas/os", false );
			int columnaSatMent = mentoras.getColumnWithHeader( "Tu nivel de satisf", false );
			int columnaSatEst = mentoras.getColumnWithHeader( "satisfacción de los chicas/os", false );
			for (int fila=0; fila<mentoras.size(); fila++) {
				try {
					int numSesion = Integer.parseInt( mentoras.get( fila, columnaSesion ) );
					String codCentro = mentoras.get( fila, columnaCentro );
					int numEstuds = Integer.parseInt( mentoras.get( fila, columnaNumEsts ) );
					int satMentora = Integer.parseInt( mentoras.get( fila, columnaSatMent ) );
					int satEstuds = Integer.parseInt( mentoras.get( fila, columnaSatEst ) );
					CentroEd centro = Datos.centros.get( codCentro );
					if (centro!=null) {
						centro.getContSesiones().inc();    // Incrementa el contador de sesiones
						centro.addValMentor( satMentora ); // Añade satisfacción de mentora
						centro.addValEstud( satEstuds );   // Añade satisfacción de estudiantes
						centro.getEstudPorSesion()[ numSesion-1 ] += numEstuds;  // Añade número de estudiantes en la sesión correspondiente
					} else {
						// System.err.println( "Código de centro incorrecto en línea de seguimiento: " + mentoras.getFila( fila ) );
						procesaErrorLinea( (l) -> System.out.println( "Código de centro incorrecto en línea de seguimiento: " + l ), 
							mentoras.getFila(fila) );
					}
				} catch (Exception e) {
					// System.err.println( "Error en línea de seguimiento: " + mentoras.getFila( fila ) );
					procesaErrorLinea( (l) -> logger.log( Level.WARNING, "Error en línea de seguimiento: " + l ), 
						mentoras.getFila(fila) );
				}
			}
			Tabla c = Tabla.createTablaFromColl( Datos.centros.values() );
			Datos.v.setTabla( c );
		} catch (Exception e) { e.printStackTrace(); }
	}
	
		private static Logger logger = Logger.getLogger( "Seguimiento sesiones" );
		static {
			try {
				logger.addHandler( new FileHandler( "errores-sesiones.xml", true ) );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private static void procesaErrorLinea( Consumer<ArrayList<String>> proceso, ArrayList<String> linea ) {
			proceso.accept( linea );
		}
	
}
