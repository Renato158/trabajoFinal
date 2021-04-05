package model;

public class ProductoCarrito {
	
	private int idProducto;
	private int cantidad;
	
	public ProductoCarrito(int id, int cantidad) {
		this.idProducto = id;
		this.cantidad = cantidad;
	}

	public int getId() {
		return idProducto;
	}

	public void setId(int id) {
		this.idProducto = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
    @Override
    public String toString() {
        return "Productos{" + "idProducto=" + idProducto + ", cantidad=" + cantidad + '}';
    }
	
	

}
