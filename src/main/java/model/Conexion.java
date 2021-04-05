package model;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Conexion {

	
	MongoClientURI uri = new MongoClientURI(
		    "mongodb+srv://Renato:1jose2renato@cluster0.2fdsm.mongodb.net/");
	CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
	MongoClient mongoClient = new MongoClient(uri);
	
	
	
	public MongoDatabase conectar() {
		
		MongoDatabase database = mongoClient.getDatabase("carrito").withCodecRegistry(pojoCodecRegistry);
		
		return database;
		
	}
	
	
}
