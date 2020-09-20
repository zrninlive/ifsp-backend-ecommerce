package project.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import project.ecommerce.model.Customer;
import project.ecommerce.model.Order;
import project.ecommerce.model.Product;
import project.ecommerce.model.User;
import project.ecommerce.utils.MongoUtils;

public class OrderDao {

	private MongoDatabase dtbase;

	public OrderDao() {
		this.dtbase = new MongoUtils().getDatabase();
	}

	public String findOrdersByCpf(String cpf) {
		MongoCollection<Document> collection = dtbase.getCollection("orders");

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("customer.cpf", cpf);

		MongoCursor<Document> cursor = collection.find(whereQuery).iterator();

		List<String> results = new ArrayList<String>();

		while (cursor.hasNext()) {
			Document doc = cursor.next();
			results.add(doc.toJson());
		}
		return results.toString();
	}

	public void insertOrder(List<Document> products, Document customer, double total) {

		MongoCollection<Document> documents = dtbase.getCollection("orders");

		Document document = new Document();

		document.append("products", products);
		document.append("customer", customer);
		document.append("total", total);

		System.out.println(document);

		documents.insertOne(document);

	}
}
