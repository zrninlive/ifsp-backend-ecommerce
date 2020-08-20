package project.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import project.ecommerce.model.User;
import project.ecommerce.utils.MongoUtils;

public class UserDao {

	private MongoDatabase dtbase;

	public UserDao() {
		this.dtbase = new MongoUtils().getDatabase();
	}

	public void insertUser(User user) {

		MongoCollection<Document> documents = dtbase.getCollection("users");

		Document doc = new Document();
		doc.append("name", user.getName());
		doc.append("cpf", user.getCpf());
		doc.append("city", user.getCity());

		documents.insertOne(doc);
	}

	public String findUsers() {
		MongoCollection<Document> collection = dtbase.getCollection("users");

		MongoCursor<Document> cursor = collection.find().iterator();

		List<String> results = new ArrayList<String>();

		while (cursor.hasNext()) {
			Document doc = cursor.next();
			results.add(doc.toJson());
		}
		return results.toString();
	}
}
