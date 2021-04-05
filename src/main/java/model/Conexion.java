package model;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Conexion {

	
	MongoClientURI uri = new MongoClientURI(
		    "mongodb+srv://Renato:1jose2renato@cluster0.2fdsm.mongodb.net/");
	MongoClient mongoClient = new MongoClient(uri);
	
	
	
	public MongoDatabase conectar() {
		
		MongoDatabase database = mongoClient.getDatabase("carrito");
		
		return database;
		
	}
	
	
}
