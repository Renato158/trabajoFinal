package controladores;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import model.Carrito;
import model.Conexion;
import model.Producto;

public class CarritoCon {
	
	static Conexion con = new Conexion();
	static MongoDatabase database = con.conectar();
	
	static MongoCollection<Document> carrito = database.getCollection("carrito");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		
	}
		
    public static List<String> getDatosCarrito() {
    	
		BasicDBObject filtro = new BasicDBObject();
    	filtro.put("_id", 2);
    	
    	FindIterable<Document> cur = carrito.find(filtro);
    	
    	List<String> list = new ArrayList<String>();
    	

    	cur.forEach(names -> list.add(names.toJson()));
    	
    	String f = list.get(0).toString();
    	
    	Gson gson = new Gson();
    	
    	Carrito caaa = gson.fromJson(f, Carrito.class);
		
    	int[] idProductos = new int[caaa.getProductos().size()];
    	
    	for(int i = 0; i < caaa.getProductos().size() ; i++) {
    		
    		idProductos[i] = caaa.getProductos().get(i).getId();
    	}
		
    	MongoCollection<Document> producto = database.getCollection("producto");
    	List<String> datosCarrito = new ArrayList<String>();
		
    	for(int i = 0; i < caaa.getProductos().size() ; i++) {
    	    
        	FindIterable<Document> res = producto.find(new BasicDBObject("_id", idProductos[i]));
        	List<String> ducto = new ArrayList<String>();
        	res.forEach(names -> ducto.add(names.toJson()));

        	datosCarrito.addAll(ducto);
    	}
 
		return datosCarrito;
	}

	
	public Double obtenerTotales() {
    	
    	Gson gson = new Gson();
    	
    	Double total = 0.0;
    	List<String> datosCarrito = CarritoCon.getDatosCarrito();
    	
    	MongoCollection<Document> carrito = database.getCollection("carrito");
    	FindIterable<Document> conCarrito = carrito.find(new BasicDBObject("_id", 2));
    	
		List<String> listCarrito = new ArrayList<String>();
    	conCarrito.forEach(names -> listCarrito.add(names.toJson()));

    	Carrito c = gson.fromJson(listCarrito.get(0), Carrito.class);
    	
    	for(int i = 0; i < datosCarrito.size(); i++) {
    		
        	Producto p = gson.fromJson(datosCarrito.get(i), Producto.class);
        	
        	double precio = p.getPrecio();
        	
        	int cantidad = c.getProductos().get(i).getCantidad();
        	
        	total += precio*cantidad;
        	
    	}
    	
		return total;
	}


}
