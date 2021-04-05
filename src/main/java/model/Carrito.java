package model;

import javax.faces.bean.ManagedBean;


import java.util.ArrayList;

import model.ProductoCarrito;

@ManagedBean
public class Carrito {
	
	private int _id;
	
	private ArrayList<ProductoCarrito> productos;
	
	public Carrito(int id) {
		this._id = id;
		this.productos = new ArrayList<ProductoCarrito>();
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public ArrayList<ProductoCarrito> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<ProductoCarrito> productos) {
		this.productos = productos;
	}
	
    @Override
    public String toString() {
        return "Ca{" + "id=" + _id + ", productos=" + productos + '}';
    }
	
	

}