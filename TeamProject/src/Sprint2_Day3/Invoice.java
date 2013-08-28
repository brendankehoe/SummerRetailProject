package Sprint2_Day3;
import java.util.ArrayList;
import java.util.Date;


public class Invoice extends ProductForm{
	
	private Customer customer;
	
	public Invoice(int id, ArrayList<Product> products, Customer customer, Date date, boolean isDelivered){
		super(id, products, date, isDelivered);
		this.customer = customer;
		
	}
	
	public void printDetails(){
		
		System.out.println();
		System.out.println("***Invoice details***");
		for (Product p: getProducts()){
			System.out.println("Name of product: " + p.getName() + ", product SKU: " + p.getSku() + ", product quantity: " + p.getQuantity());
			
		}
			System.out.println();
			System.out.println("Customer ID: " + customer.getId());			
			System.out.println("Customer name: " + customer.getName());
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
