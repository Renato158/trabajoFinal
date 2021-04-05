package controladores;

import java.time.LocalDate;

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
import model.Domicilio;
import model.Orden;
import model.Producto;
import model.Usuario;


public class OrdenCon {
	
	static Conexion con = new Conexion();
	static MongoDatabase database = con.conectar();
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
    	//-------------------------------------------------------------
		Orden producto = createOrden();
		Document doc = createDBObject(producto);
		
		MongoCollection<Document> ordenC = database.getCollection("orden");
		
		if(OrdenCon.verificarExistencias() == "") {
			ordenC.insertOne(doc);
			System.out.println("Sussesful");
		}else {
			System.out.println(OrdenCon.verificarExistencias());
			System.out.print("Actualice su carrito");
		}

	}
	
	private static Document createDBObject(Orden orden){
		
		
		Document document = new Document("_id", orden.getId()).
		append("idUsuario", orden.getIdUsuario()).
		append("idDomicilio", orden.getIdDomicilio()).
		append("idCarrito", orden.getIdCarrito()).
		append("precio", orden.getMetodoPago()).
		append("fecha", orden.getFecha()).
		append("total", orden.getTotal());
		
		return document;
	}
	
	private static Orden createOrden() {
		
		MongoCollection<Document> usuario = database.getCollection("usuario");
		MongoCollection<Document> domicilio = database.getCollection("domicilio");
		MongoCollection<Document> carrito = database.getCollection("domicilio");
		
		FindIterable<Document> conUsuario = usuario.find(new BasicDBObject("_id", 1));
		
		List<String> listUsuario = new ArrayList<String>();
    	conUsuario.forEach(names -> listUsuario.add(names.toJson()));
    	
    	Gson gson = new Gson();
    	Usuario u = gson.fromJson(listUsuario.get(0), Usuario.class);
		
    	FindIterable<Document> conDomicilio = domicilio.find(new BasicDBObject("_id", 1));
    	
		List<String> listDomicilio = new ArrayList<String>();
    	conDomicilio.forEach(names -> listDomicilio.add(names.toJson()));

    	Domicilio d = gson.fromJson(listDomicilio.get(0), Domicilio.class);
    	
    	FindIterable<Document> conCarrito = carrito.find(new BasicDBObject("_id", 1));
    	
		List<String> listCarrito = new ArrayList<String>();
    	conCarrito.forEach(names -> listCarrito.add(names.toJson()));

    	Carrito c = gson.fromJson(listCarrito.get(0), Carrito.class);
    	
    	CarritoCon cc = new CarritoCon();
    	double total = cc.obtenerTotales();
    	

		Orden o = new Orden();
		o.setId(1);
		o.setIdUsuario(u.get_id());
		o.setIdDomicilio(d.getId());
		o.setIdCarrito(c.getId());
		o.setMetodoPago("Tarjeta de credito");
		o.setFecha(LocalDate.now());
		o.setTotal(total);
		return o;
	}
	
	public static String verificarExistencias() {
		
		Gson gson = new Gson();

    	List<String> dC = CarritoCon.getDatosCarrito();
		
    	for (int i = 0; i < dC.size(); i++) {
    		
    		Producto g = gson.fromJson(dC.get(i), Producto.class);
    		if(g.getExistencias() == 0) {
    			return "no hay existencias del producto: "+ g.getNombre();
    		}
    		
    	}
    	
    	return "";
		
	}
	

}
