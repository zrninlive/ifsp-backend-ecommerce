package project.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import project.ecommerce.model.Order;
import project.ecommerce.model.Product;
import project.ecommerce.model.User;
import project.ecommerce.utils.MongoUtils;

public class OrderDao {
	
	private MongoDatabase dtbase;
	
	public OrderDao() {
		this.dtbase = new MongoUtils().getDatabase();
	}
	
	public void create(Order order, User user, Product product) {
		
		MongoCollection<Document> documents = dtbase.getCollection("orders");
		
		Document doc = new Document();
		doc.append("customer_id", user.get_id());
		doc.append("product_name", product.getTitle());
		doc.append("total", order.getTotal());
		
		documents.insertOne(doc);
	}
	
	public String find() {
		MongoCollection<Document> collection = dtbase.getCollection("orders");
		
		MongoCursor<Document> cursor = collection.find().iterator();
		
		List<String> results = new ArrayList<String>();
		
		while(cursor.hasNext()) {
			Document doc = cursor.next();
			results.add(doc.toJson());
		}
		return results.toString();
	}
}
