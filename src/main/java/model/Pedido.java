package model;

import java.util.ArrayList;

public class Pedido {
	
	private int _id;
	private int idUsuario;
	private ArrayList<ProductoCarrito> productos;
	private double total;
	private String fecha;
	
	
	

	public Pedido(int _id, int idUsuario, double total, String fecha) {
		this._id = _id;
		this.idUsuario = idUsuario;
		this.productos = new ArrayList();
		this.total = total;
		this.fecha = fecha;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public ArrayList<ProductoCarrito> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<ProductoCarrito> productos) {
		this.productos = productos;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Pedido [_id=" + _id + ", idUsuario=" + idUsuario + ", productos=" + productos + ", total=" + total
				+ ", fecha=" + fecha + "]";
	}
	
}
