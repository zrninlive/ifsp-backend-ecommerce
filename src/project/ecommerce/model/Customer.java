package project.ecommerce.model;

public class Customer {	
	private int _id;
	private String name;
	private String email;
	private String password;
	private String cpf;
	private String phone;
	private String zipcode;
	private String street;
	private String number;
	private String city;
	private String state;
	
	public Customer(int _id, String name, String email, String password, 
			String cpf, String phone, String zipcode, String street, String number, String city, String state) {
		super();
		this._id = _id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.phone = phone;
		this.zipcode = zipcode;
		this.street = street;
		this.number = number;
		this.city = city;
		this.state = state;
	}

	public Customer(String name, String email, String password, 
			String cpf, String phone, String zipcode, String street, String number, String city, String state) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.cpf = cpf;
		this.phone = phone;
		this.zipcode = zipcode;
		this.street = street;
		this.number = number;
		this.city = city;
		this.state = state;
	}
	
	public Customer() {}
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
