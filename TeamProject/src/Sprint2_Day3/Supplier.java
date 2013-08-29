package Sprint2_Day3;

import java.util.ArrayList;

public class Supplier {

	private int id;
	private String name;
	private String email;
	private String phoneNumber;
	private ArrayList<Product> products = new ArrayList<Product>();

	public Supplier(int id, String name, String email, String phoneNumber){
	this.id=id;
	this.name=name;
	this.email=email;
	this.phoneNumber =phoneNumber;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
