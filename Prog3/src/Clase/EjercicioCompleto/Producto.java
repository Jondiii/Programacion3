package Clase.EjercicioCompleto;

import java.util.ArrayList;

public class Producto {

	public int id;
	private String nombre;
	private int precio;
	
	public ArrayList<Compra> listaCompras;

	public Producto(int id, String nombre, int precio) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Producto() {}


	
	@Override
	public String toString() {
		return id + " " +  nombre + " " + precio;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public ArrayList<Compra> getListaCompras() {
		return listaCompras;
	}
	public void setListaCompras(ArrayList<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}
	
	
}
