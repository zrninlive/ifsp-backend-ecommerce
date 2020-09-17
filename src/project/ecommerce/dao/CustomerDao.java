package project.ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import project.ecommerce.model.Customer;
import project.ecommerce.utils.MongoUtils;

public class CustomerDao {

	private MongoDatabase dtbase;

	public CustomerDao() {
		this.dtbase = new MongoUtils().getDatabase();
	}

	public void insertCustomer(Customer customer) {

		MongoCollection<Document> documents = dtbase.getCollection("customers");

		Document doc = new Document();
		doc.append("name", customer.getName());
		doc.append("email", customer.getEmail());
		doc.append("password", customer.getPassword());
		doc.append("cpf", customer.getCpf());
		doc.append("phone", customer.getPhone());
		doc.append("zipcode", customer.getZipcode());
		doc.append("street", customer.getStreet());
		doc.append("number", customer.getNumber());
		doc.append("city", customer.getCity()); 
		doc.append("state", customer.getState());

		documents.insertOne(doc);
	}

	public String findCustomerByEmail(String email, String encode_password) {
		MongoCollection<Document> collection = dtbase.getCollection("customers");
		boolean isFound = false;
		String CustomerJson = "";
		
	    List obj = new ArrayList();
        obj.add(new BasicDBObject("email", email));
        obj.add(new BasicDBObject("password", encode_password));
 
        // Form a where query
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("$and", obj);
	    
	    FindIterable<org.bson.Document> it = collection.find(whereQuery);
	     
	       for (org.bson.Document its : it) {
	           CustomerJson = its.toJson();	    	   
	           isFound = true;
	       }        
	       
	       return isFound ? CustomerJson : "";
	}

	public void updateCustomer(Customer customer) {	}
}


