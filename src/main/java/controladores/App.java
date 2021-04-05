package controladores;


import com.mongodb.MongoClient;


import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.time.LocalDate;

import org.bson.Document;

import model.Conexion;
import model.Orden;
import model.Producto;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
    	
		//Producto producto = createProducto();
		//Document doc = createDBObject(producto);
    	
    	Conexion con = new Conexion();
    	MongoDatabase database = con.conectar(); 
    	MongoCollection<Document> ordenC = database.getCollection("orden");
    	
		

		MongoCollection<Document> productoC = database.getCollection("producto");
		
		BasicDBObject filtro = new BasicDBObject();
		filtro.put("_id", 1);
		FindIterable<Document> cur = productoC.find(filtro);
		cur.forEach(names -> System.out.println(names.toJson()));

		//create user
		//productoC.insertOne(doc);
		System.out.println("Sussesful");
		
		CarritoCon caaa = new CarritoCon();
		caaa.getDatosCarrito();
		
		System.out.println("Sussesful");
		
			
		}
		
		private static Document createDBObject(Producto producto){
			
									
			Document document = new Document("_id", producto.getId()).
			append("nombre", producto.getNombre()).
			append("descripcion", producto.getDescripcion()).
			append("fecha", producto.getFecha()).
			append("precio", producto.getPrecio()).
			append("existencias", producto.getExistencias());
			
			return document;
		}
		/*
		
		private static Producto createProducto() {
			Producto p = new Producto();
			p.setId(3);
			p.setNombre("Camiseta rosa");
			p.setDescripcion("Camisera rosa estanpada modelo A");
			p.setFecha(LocalDate.now());
			p.setPrecio(38000);
			p.setExistencias(4);
			return p;
		}
		
		/*
		private static Orden createOrden() {
			Orden o = new Orden();
			o.setId(1);
			o.setIdUsuario();
			o.setIdDomicilio();
			o.setIdCarrito();
			o.setMetodoPago("Tarjeta de credito");
			o.setFecha(LocalDate.now());
			o.setTotal();
			return o;
		}
		*/
		
		

}
