package project.ecommerce.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoUtils {
	private MongoClient connection = null;
	private final String BANCO = "db_ecommerce_nosql";
	private final String HOST = "localhost";
	private final int PORT = 27016;

	public MongoDatabase getDatabase() {
		connection = new MongoClient(HOST, PORT);

		return connection.getDatabase(BANCO);
	}
}
