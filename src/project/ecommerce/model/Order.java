package project.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

	List<Product> products = new ArrayList<Product>();
	Customer customer;
	double total;

	public Order() {
	}

	public Order(List<Product> products, Customer customer, double total) {
		super();
		this.products = products;
		this.customer = customer;
		this.total = total;
	}

	public void setProducts(List<Product> products) {
		this.products = (List<Product>) products;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
