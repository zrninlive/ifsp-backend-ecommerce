package project.ecommerce.model;

public class Product {
	private int id;
	private int category_id;
	private String title;
	private int quantity;
	private String description;
	private double price;
	private String image;
	
	public Product(int category_id, String title,int quantity, String description, double price, String image) {
		super();
		this.category_id = category_id;
		this.title = title;
		this.quantity = quantity;
		this.description = description;
		this.price = price;
		this.image = image;
	}
	public Product(int id, int category_id, String title,int quantity, String description, double price, String image) {
		super();
		this.id = id;
		this.category_id = category_id;
		this.title = title;
		this.quantity = quantity;
		this.description = description;
		this.price = price;
		this.image = image;
	}
	public Product() {}

	public int getId() {	return id;	}
	public void setId(int id) {	this.id = id;	}

	public int getCategory_id() { return category_id;	}
	public void setCategory_id(int category_id) {	this.category_id = category_id;	}

	public String getTitle() {	return title;	}
	public void setTitle(String title) { this.title = title;	}
	
	public int getQuantity() {	return quantity;	}
	public void setQuantity(int quantity) {	this.quantity = quantity;	}

	public String getDescription() {	return description;	}
	public void setDescription(String description) {	this.description = description;	}

	public double getPrice() {	return price;	}
	public void setPrice(double price) {	this.price = price;	}

	public String getImage() {	return image;	}
	public void setImage(String image) { this.image = image; }	
	
}
