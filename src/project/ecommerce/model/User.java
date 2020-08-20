package project.ecommerce.model;

public class User {
	private String _id;
	private String name;
	private String cpf;
	private String city;

	public User(String _id, String name, String cpf, String city) {
		super();
		this._id = _id;
		this.name = name;
		this.cpf = cpf;
		this.city = city;
	}

	public User(String name, String cpf, String city) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.city = city;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
