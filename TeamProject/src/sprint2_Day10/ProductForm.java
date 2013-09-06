package sprint2_Day10;
import java.util.ArrayList;
import java.util.Date;


public class ProductForm {
	
	private int id;
	private ArrayList<Product> products = new ArrayList<Product>();
	private boolean isDelivered;
	private Date date;
	
	public ProductForm(int id, ArrayList<Product> products, Date date, boolean isDelivered) {
		this.id = id;
		this.products = products;
		this.date=date;
		this.isDelivered=isDelivered;
		
	}
	
	public double calculateTotalWholesaleValue(){
		
		double value = 0;	
		for (Product p: products){
			value+=p.getWholesalePrice()*p.getQuantity();
		}	
		return value;

	}
	
	public double calculateWholesaleValue(int sku){
		
		double value = 0;	
		for (Product product: products){
			if ( sku == product.getSku() ){
				value+=product.getWholesalePrice()*product.getQuantity();				
			}
		}	
		return value;

	}

	public double calculateTotalRetailValue(){
		
		double value = 0;	
		for (Product p: products){
			value=value+p.getRetailPrice()*p.getQuantity();
		}
		return value;

	}
	
	public double calculateRetailValue(int sku){
		
		double value = 0;	
		for (Product product: products){
			if ( sku == product.getSku() ){
			value=value+product.getWholesalePrice()*product.getQuantity();
			}
		}	
		return value;

	}
	
	public int calculateTotalNumberItems(){
		
		int value = 0;
		for (Product p: products){
			value=value+p.getQuantity();
		}
		return value;

	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

}
