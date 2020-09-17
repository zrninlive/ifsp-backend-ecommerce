package project.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

	Product products = new Product();
	User customer;
	double total;
	
	public Order(List<Product> products, User customer, double total) {
		super();
		this.products = (Product) products;
		this.customer = customer;
		this.total = total;
	}

	public List<Product> getProducts() { return (List<Product>) products;	}
	public void setProducts(List<Product> products) {	this.products = (Product) products;	}

	public User getCustomer() {	return customer; }
	public void setCustomer(User customer) {this.customer = customer;}

	public double getTotal() {	return total;	}
	public void setTotal(double total) { this.total = total;	}
	
}
