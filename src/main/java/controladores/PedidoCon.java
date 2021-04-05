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
import model.Pedido;
import model.Producto;
import model.Usuario;

public class PedidoCon {
	
	static Conexion con = new Conexion();
	static MongoDatabase database = con.conectar(); 
	static Gson gson = new Gson();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//------------------------------------------------------------
		Pedido pedido = createPedido();
		Document doc = createDBObject(pedido);
		
		MongoCollection<Document> pedidoC = database.getCollection("pedido");
		pedidoC.insertOne(doc);
		System.out.println("Succesful");
		
		PedidoCon.actualizarExistencias();
		System.out.println("Succesful");
		
		PedidoCon.eliminarDatos();
		System.out.println("Succesful");
		
		//PedidoCon.articulosVendidos();

	}
	
	public static void eliminarDatos() {
		
		MongoCollection<Document> carrito = database.getCollection("carrito");
		MongoCollection<Document> orden = database.getCollection("orden");
		
		carrito.deleteOne(new BasicDBObject("_id", 1));
		orden.deleteOne(new BasicDBObject("_id", 1));
		
	}
	
	public static void actualizarExistencias() {
		
		List<String> dC = CarritoCon.getDatosCarrito();
    	
    	MongoCollection<Document> carrito = database.getCollection("carrito");
    	FindIterable<Document> conCarrito = carrito.find(new BasicDBObject("_id", 1));
		List<String> listCarrito = new ArrayList<String>();
    	conCarrito.forEach(names -> listCarrito.add(names.toJson()));

    	Carrito car = gson.fromJson(listCarrito.get(0), Carrito.class);
    	
    	MongoCollection<Document> p = database.getCollection("producto");
		
		for(int i = 0; i < dC.size(); i++) {
			
			int cantidad = car.getProductos().get(i).getCantidad();
			
			Producto c = gson.fromJson(dC.get(i), Producto.class);
			c.getExistencias();
			
			BasicDBObject match = new BasicDBObject();
			match.put("_id", c.getId());
			
			BasicDBObject update = new BasicDBObject();
			update.put("existencias", c.getExistencias()-cantidad);
			
			BasicDBObject updateObject = new BasicDBObject();
			updateObject.put("$set", update);
			
			p.updateOne(match, updateObject);
			
		}
		
	}
	
	private static Document createDBObject(Pedido pedido){
		
		
		Document document = new Document("_id", pedido.get_id()	).
		append("idUsuario", pedido.getIdUsuario()).
		append("productos", pedido.getProductos()).
		append("total", pedido.getTotal()).
		append("fecha", pedido.getFecha());
		
		return document;
	}
	
	private static Pedido createPedido() {
		
		MongoCollection<Document> usuario = database.getCollection("usuario");
		MongoCollection<Document> carrito = database.getCollection("carrito");
		
		FindIterable<Document> conUsuario = usuario.find(new BasicDBObject("_id", 1));
		
		List<String> listUsuario = new ArrayList<String>();
    	conUsuario.forEach(names -> listUsuario.add(names.toJson()));
    	
    	
    	Usuario u = gson.fromJson(listUsuario.get(0), Usuario.class);
    	
    	FindIterable<Document> conCarrito = carrito.find(new BasicDBObject("_id", 2));
    	
		List<String> listCarrito = new ArrayList<String>();
    	conCarrito.forEach(names -> listCarrito.add(names.toJson()));

    	Carrito c = gson.fromJson(listCarrito.get(0), Carrito.class);
    	
    	CarritoCon cc = new CarritoCon();
    	double total = cc.obtenerTotales();
    	
		Pedido p = new Pedido();
		
		p.set_id(2);
		p.setIdUsuario(u.get_id());
		p.setProductos(c.getProductos());
		p.setTotal(total);
		p.setFecha(LocalDate.now().toString());
		return p;
	}
	
	public static void articulosVendidos() {
		
		MongoCollection<Document> pedido = database.getCollection("pedido");
		MongoCollection<Document> producto = database.getCollection("producto");
		
		FindIterable<Document> conPedido = pedido.find();
		
		List<String> listPedido = new ArrayList<String>();
    	conPedido.forEach(names -> listPedido.add(names.toJson()));
    	
		for(int i = 0; i<listPedido.size(); i++) {
			
			Pedido c = gson.fromJson(listPedido.get(i), Pedido.class);

			for(int j = 0; j< c.getProductos().size(); j++) {
				
				int idPedido = c.getProductos().get(j).getId();

				
				FindIterable<Document> conProducto = producto.find(new BasicDBObject("_id", idPedido));

				
				List<String> listProducto = new ArrayList<String>();
		    	conProducto.forEach(names -> listProducto.add(names.toJson()));
				
				Producto p = gson.fromJson(listProducto.get(0), Producto.class);

				System.out.println("Producto "+ j);
				System.out.println("id: "+p.getId());
				System.out.println("nombre: "+ p.getNombre());
				System.out.println("descripcion: "+ p.getDescripcion());
				System.out.println("fecha: "+ p.getFecha());
				System.out.println("precio: "+ p.getPrecio());
				System.out.println("existencias: "+ p.getExistencias());
				
			}

		}

	}
}
