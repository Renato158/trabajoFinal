package model;

public class ProductoCarrito {
	
	private int _id;
	private int cantidad;
	
	public ProductoCarrito(int id, int cantidad) {
		this._id = id;
		this.cantidad = cantidad;
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
    @Override
    public String toString() {
        return "productos{" + "idProducto=" + _id + ", cantidad=" + cantidad + '}';
    }
	
	

}
