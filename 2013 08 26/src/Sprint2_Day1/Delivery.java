package Sprint2_Day1;

import java.util.ArrayList;

public class Delivery {
	
	ArrayList<Product> products;
	int orderID;
	int deliveryID = 1;
	
	public Delivery(ArrayList<Product> productsParameter){
		products = productsParameter;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
}
