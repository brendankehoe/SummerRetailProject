package sprint2_Day10;
import java.util.ArrayList;
import java.util.Date;

public class Order extends ProductForm{

	private Supplier supplier;
	private Date deliveryDate;
	
	public Order(int id, ArrayList<Product> products, Date date, boolean isDelivered, Date deliveryDate){
		super(id, products, date, isDelivered);
		this.supplier = products.get(0).getSupplier();	
		this.deliveryDate=deliveryDate;
	}
	
	public void printDetails(){
		
		System.out.println();
		System.out.println("***Order details***");
		
		for (Product p: getProducts()){
			System.out.println("Name of product: " + p.getName() + ", product SKU: " + p.getSku() + ", product quantity: " + p.getQuantity());
			
		}
			System.out.println("Supplier: " + supplier.getId());			
	
	}
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSuppliers(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	
}
