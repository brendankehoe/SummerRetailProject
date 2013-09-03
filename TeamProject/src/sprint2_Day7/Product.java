package sprint2_Day7;
public class Product {
	
	private int sku = 0;
	private String name;
	private Supplier supplier;
	private int quantity;
	private double wholesalePrice;
	private double retailPrice;
		
	public Product(int sku, String name, int quantity, double wholesalePrice, double retailPrice, Supplier supplier){
		this.sku=sku;
		this.name = name;
		this.supplier = supplier;
		this.quantity = quantity;
		this.wholesalePrice=wholesalePrice;
		this.retailPrice=retailPrice;	
	}
	

	//For creating duplicates
	public Product(Product product){
		this (product.getSku(), product.getName(), product.getQuantity(), product.getWholesalePrice(), product.getRetailPrice(), product.getSupplier());
	}

	public int getSku() {
		return sku;
	}


	public void setSku(int sku) {
		this.sku = sku;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getWholesalePrice() {
		return wholesalePrice;
	}


	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}


	public double getRetailPrice() {
		return retailPrice;
	}


	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
    
	public void increaseQuantity(int increase){
    	this.quantity += increase;
    }
	
}
