package project.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
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
	
	
	
	public String findOrdersByCpf(String cpf) {
		MongoCollection<Document> collection = dtbase.getCollection("orders");
		
		BasicDBObject whereQuery = new BasicDBObject();
	    whereQuery.put("customer.cpf", cpf);
		
		MongoCursor<Document> cursor = collection.find(whereQuery).iterator();
		
		List<String> results = new ArrayList<String>();
		
		while(cursor.hasNext()) {
			Document doc = cursor.next();
			results.add(doc.toJson());
		}
		return results.toString();
	}
}
