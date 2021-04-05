package controladores;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.time.LocalDate;

import org.bson.Document;

import model.Conexion;
import model.Producto;
/**
 * Hello world!
 *
 */
public class ProductoCon 
{

    public static void main( String[] args )
    {
    	
		Producto producto = createProducto();
		Document doc = createDBObject(producto);
    	
    	Conexion con = new Conexion();
    	MongoDatabase database = con.conectar(); 

		MongoCollection<Document> productoC = database.getCollection("producto");

		productoC.insertOne(doc);
		System.out.println("Sussesful");

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

		private static Producto createProducto() {
			Producto p = new Producto();
			p.setId(3);
			p.setNombre("Camiseta rosa");
			p.setDescripcion("Camisera rosa estanpada modelo A");
			p.setFecha(LocalDate.now().toString());
			p.setPrecio(38000);
			p.setExistencias(4);
			return p;
		}
		
		

}
