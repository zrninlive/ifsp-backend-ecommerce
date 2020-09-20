package project.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
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
		doc.append("email", user.getEmail());
		doc.append("password", user.getPassword());

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

	public String findUserByEmail(String email, String encode_password) {
		MongoCollection<Document> collection = dtbase.getCollection("users");
		boolean isFound = false;
		String userJson = "";

		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("email", email));
		obj.add(new BasicDBObject("password", encode_password));

		// Form a where query
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("$and", obj);

		FindIterable<org.bson.Document> it = collection.find(whereQuery);

		for (org.bson.Document its : it) {
			userJson = its.toJson();
			isFound = true;
		}

		return isFound ? userJson : "";
	}
}
