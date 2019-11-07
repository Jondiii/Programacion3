package Clase.EjercicioCompleto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BaseDatos {
	
	private static Statement st;
	
	public static void abrirConexion(String nombreBD) {
		try {
			 Class.forName("org.sqlite.JDBC");
			 Connection con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			 st = con.createStatement();
			 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void cerrarConexion() {
		try {
			st.close();  
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static ArrayList<Producto> getProductos(){
		ArrayList<Producto> listaP = new ArrayList<>();
		try {
			String sentSQL = "select * from productos";
			ResultSet rs = st.executeQuery( sentSQL );
			while(rs.next()) {
				Producto p = new Producto();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setPrecio(rs.getInt("precio"));
				listaP.add(p);
				System.out.println(p);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error garrafal D:\n" + e);
		}
		return listaP;
		
	}
}
