package br.com.sonikro.b2w_images.database;

import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class DatastoreManager {
	
	private static Datastore datastore;
	
	public static Datastore getMongoDatastore()
	{
		if(datastore == null)
		{
			//Prepares MongoDB Connection
			Morphia morphia = new Morphia();
			morphia.mapPackage("br.com.sonikro.b2w_images");
			MongoCredential mongoCredential = MongoCredential.createCredential("root", "admin", "root".toCharArray());
			ServerAddress address = new ServerAddress("localhost",27017);
					
			MongoClient mongoClient = new MongoClient(address, Arrays.asList(mongoCredential));
					
			datastore = morphia.createDatastore(mongoClient, "b2w_example");
			
		}
		
		return datastore;
	}
}
