package project.ecommerce.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;

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
		String customerJson = "";

		List obj = new ArrayList();
		obj.add(new BasicDBObject("email", email));
		obj.add(new BasicDBObject("password", encode_password));

		// Form a where query
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("$and", obj);

		FindIterable<org.bson.Document> it = collection.find(whereQuery);

		for (org.bson.Document its : it) {
			customerJson = its.toJson();
			isFound = true;
		}

		return isFound ? customerJson : "";
	}

	public String findCustomer(String cpf) throws Exception {
		MongoCollection<Document> collection = dtbase.getCollection("customers");
		boolean isFound = false;
		String customerJson = "";

		// Form a where query
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("cpf", cpf);

		FindIterable<org.bson.Document> it = collection.find(whereQuery);

		for (org.bson.Document its : it) {
			customerJson = its.toJson();
			isFound = true;
		}
		if (!isFound) {
			throw new Exception("CUSTOMER NOT FOUND");
		}

		return isFound ? customerJson : "";
	}

	public void updateCustomer(Customer customer) {

		MongoCollection<Document> collection = dtbase.getCollection("customers");

		Bson filter = new BasicDBObject("email", customer.getEmail());

		Document found = (Document) collection.find(filter).first();

		if (found != null) {

			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("name", customer.getName());
			newDocument.put("email", customer.getEmail());
			newDocument.put("password", customer.getPassword());
			newDocument.put("cpf", customer.getCpf());
			newDocument.put("phone", customer.getPhone());
			newDocument.put("zipcode", customer.getZipcode());
			newDocument.put("street", customer.getStreet());
			newDocument.put("number", customer.getNumber());
			newDocument.put("city", customer.getCity());
			newDocument.put("state", customer.getState());

			collection.updateOne(filter, new Document("$set", newDocument));
		}
	}
}
