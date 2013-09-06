package sprint2_Day10; 
  
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Calendar;
import java.util.Date; 
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
 
  
public class RetailSystem { 
      
    private ArrayList<Order> orders = new ArrayList<Order>(); 
    private ArrayList<Product> products = new ArrayList<Product>(); 
    private ArrayList <Supplier> suppliers = new ArrayList<Supplier>(); 
    private ArrayList <Invoice> invoices = new ArrayList<Invoice>(); 
    private ArrayList <Customer> customers = new ArrayList<Customer>(); 
    private DefaultTableModel stockLevel =  new DefaultTableModel();
    private ArrayList<User> users = new ArrayList<User>(); 
    private int noOfOrders=1; 
    private int noOfProducts=1; 
    private int noOfSuppliers = 1; 
    private int noOfInvoices = 1; 
    private int noOfCustomers = 1; 
    private int noofUsers = 1;
    
    private Calendar calendar = Calendar.getInstance();
      
      
    public RetailSystem(){ 
              
        //Create suppliers 
        createSupplier("Four corners","fourCorners@gmail.com","016786245"); 
        createSupplier("Premier","premier@gmail.com","016786245");  
        createSupplier("Colmans","orders@colmans.com","016786245");  
        createSupplier("C & R","candr@gmail.com","016786245");  
        createSupplier("S & S","ss@bestbeer.com","016786245");  
      
        //Create products 
        createProduct("Macks Sassy Red", 1, 3, suppliers.get(1)); 
        createProduct("Galway Hooker", 1.5, 3.5, suppliers.get(1)); 
        createProduct("Twisted Hop", 2, 4, suppliers.get(2)); 
        createProduct("Grolsch", 3, 5, suppliers.get(3)); 
        createProduct("O'Hara's IPA", 3, 5, suppliers.get(4)); 
        createProduct("Guinness", 1, 3, suppliers.get(2)); 
        createProduct("Bulmers", 2, 4, suppliers.get(2)); 
        createProduct("Python IPA", 2, 4, suppliers.get(2)); 
        
        createProduct("Meantime IPA", 1, 3, suppliers.get(1)); 
        createProduct("Thwaites Shuttle", 1.5, 3.5, suppliers.get(1)); 
        createProduct("La Trappe Blond", 2, 4, suppliers.get(2)); 
        createProduct("La Trappe Tripel", 3, 5, suppliers.get(3)); 
        createProduct("Budejovicke Pivo 1795", 3, 5, suppliers.get(4)); 
        createProduct("Staropramen", 1.5, 3, suppliers.get(2)); 
        createProduct("Baltika 3", 2, 4, suppliers.get(2)); 
        createProduct("Baltika 7", 2, 4, suppliers.get(2)); 
        
        createProduct("Baltika 9", 1, 3, suppliers.get(1)); 
        createProduct("Odells Cut Throat Porter", 1.5, 3.5, suppliers.get(1)); 
        createProduct("Brooklyn Local 1", 2, 4, suppliers.get(2)); 
        createProduct("Flying Dog Raging Bitch", 3, 5, suppliers.get(3)); 
        createProduct("Goose Island 312", 3, 5, suppliers.get(4)); 
        createProduct("James Boags", 1.5, 3, suppliers.get(2)); 
        createProduct("Maisels Weiss Kristal", 2, 4, suppliers.get(2)); 
        createProduct("Maisels Weisse Dunkel", 2, 4, suppliers.get(2));
                  
        //Creating customers 
        Date date = new Date(); 
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy"); 
        //The df.parse method needs to be surrounded by a try+catch for some reason! 
        try { 
            createCustomer("John", df.parse("05031988"),"Dublin 4", "0871234567"); 
            createCustomer("Mary", df.parse("22071984"),"Lonford", "0861234567"); 
            createCustomer("Mike", df.parse("11111973"),"Leitrim", "0851234567"); 
            createCustomer("Jean", df.parse("16021987"),"Galway", "0831234567"); 
        } catch (ParseException e) { 
        } 
  
        createRandomDatabase();
        createRandomDatabase();
        
        //create some users for testing 
        //create an Admin user 
        createNewUser("Fred Flintstone", "rocks123", true, true); 
        //create an Admin user 
        createNewUser("1", "1", true, true);
        //create an Clerk user 
        createNewUser("clerk", "1", false, true);
        //create an ordinary user 
        createNewUser("Barney Rubble", "rocks321",false,true); 
        //create an inactive ordinary user 
        createNewUser("Wilma","123",false,false); 
        
    } 


	public void createRandomDatabase(){
    	
    	//******This method creates a random database, which is a randomised, scaled sine wave
		
		//Initiate calender (necessary for incrementing date) and random generator rg
        Date date = new Date(); 
    	Calendar c = Calendar.getInstance();
        Random rg = new Random(); //random generator
         
        //This bit sets the start date to 60 days ago
    	c.setTime(date);
    	c.add(Calendar.DATE, -365);
    	date=c.getTime();
        
        //Declaring variables for equation which determines demand factor - basically an adapted sin curve 
    	double deviation = 4; //The 'amplitude' of the sine curve - equivalent to the maximum amount that the demand varies
        double period = 200; //The 2pi 'wavelength' of the sine curve - equivalent to the frequency of change in demand
        double coefficient = 10; //This is coefficient - the height of the sine curve on the graph
        
        //Randomise the period
        double randomPeriod = period/2+rg.nextInt((int)period);
        double randomFunctionStart = rg.nextInt((int) period);
 
        for (double i=0;i<365;i++){
        	
    		ArrayList<Product> sampleProductList= new ArrayList<Product>(); 
    		double demandFactor = Math.sin((i+randomFunctionStart)/randomPeriod*2*Math.PI)*deviation+coefficient;
   	
        	//Update date by one day
        	c.setTime(date);
        	c.add(Calendar.DATE, 1);
        	date=c.getTime();// 
        	
        	//Create 5 random invoices
        	for (int j=0;j<5;j++){
            	//Reset temporary ArrayList
        		sampleProductList= new ArrayList<Product>();

        			Product prod1 = new Product(products.get(rg.nextInt(products.size()))); 
        			prod1.setQuantity(rg.nextInt((int) demandFactor)+1); 
        			sampleProductList.add(prod1);

        			Invoice invoice =  new Invoice(noOfInvoices,sampleProductList,customers.get(rg.nextInt(customers.size())),date,false);
        			invoices.add(invoice);
        			outboundDelivery(sampleProductList);
        			noOfInvoices++;

        	}
        	
        	//Update stock if less than 100
        	for (Product p : products){
        		if (p.getQuantity()<10){
        			
            		sampleProductList= new ArrayList<Product>();
        			Product prod1 = new Product(p); 		
        			prod1.setQuantity(50); 
        			sampleProductList.add(prod1);
        			
        			Order order = new Order(noOfOrders,sampleProductList,date,false,date);
        			orders.add(order);
        			inboundDelivery(order);
        			noOfOrders++;
        		}
        	}
 
        }
        
   	
    }
      
	public Date getDeliveryDate(Date creationDate){
		
		//Set the delivery date to a random date between one and three days from the creation date
		//Create random date for which delivery occurs, between one and three days
        Random rg = new Random();
        int deliveryDays = rg.nextInt(3)+1;
        
        //Forward current date by the random number of days
		calendar.setTime(creationDate);
		calendar.add(Calendar.DATE, deliveryDays);// Advances system date by one day
       
		Date deliveryDate = calendar.getTime(); //Set the delivery date to the random future date
        
        return deliveryDate;
	}
      
    public void createOrder(ArrayList<Product> products){ 
           
        
    	Date deliveryDate = getDeliveryDate(NewUI.systemDate);

        Order order =  new Order(noOfOrders, products, NewUI.systemDate, false,deliveryDate); 
        orders.add(order); 
        noOfOrders++; 
  
    } 
      
    public void recordDeliveries(){
    	   	
    	String deliveryText = "";
    	boolean hasDeliveryOccured = false;
    	
    	Calendar c1 = Calendar.getInstance(); 
    	Calendar c2 = Calendar.getInstance();
    	c1.setTime(NewUI.systemDate);

    	
        //loop though orders and check for new ones    	
    	for (Order order : orders){
    		c1.setTime(order.getDeliveryDate());
    		
    		//Check if the delivery date is the system date
    		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) 
                    && c1.get(Calendar.DAY_OF_YEAR) == c2 
                    .get(Calendar.DAY_OF_YEAR)){
    			
        		inboundDelivery(order);
        		System.out.println(order.getDeliveryDate().toString());
        		if (hasDeliveryOccured){
        			deliveryText=deliveryText+ "\n \n Order ID: " + order.getId()+"\n Supplier: "+ order.getSupplier().getName();       			
        		}
        		else {
               		deliveryText="Deliveries arrived: \n Order ID: " + order.getId()+"\n Supplier: "+ order.getSupplier().getName();
               		hasDeliveryOccured=true;
        		}

    		}

    	}

    	if (hasDeliveryOccured){
        	JOptionPane.showMessageDialog(null,deliveryText);  
    	}
    			


    }
    
    
    //update stock levels (add to stock levels) 
    public void inboundDelivery(Order order){ 
        
    	int newQuantity; 

        order.setDelivered(true); 

        for(Product orderProduct : order.getProducts()){ 
            for(Product product : products){ 
                if(orderProduct.getSku() == product.getSku()){ 
                    newQuantity = product.getQuantity() + orderProduct.getQuantity(); 
                    product.setQuantity(newQuantity); 
                    break; 
                } 
            } 
        } 
    } 
      
    //update stock levels (subtract from stock levels) 
    public void outboundDelivery(ArrayList<Product> invoiceProducts){ 
          
        int newQuantity; 
        int invoiceID = noOfInvoices -1; 
          
        for(Invoice invoice : invoices){ 
            if( invoice.getId() == invoiceID ){ 
                invoice.setDelivered(true); 
                break; 
            } 
        } 
                  
        for(Product invoiceProduct : invoiceProducts){ 
            for(Product product : products){ 
                if(invoiceProduct.getSku() == product.getSku()){ 
                    newQuantity = product.getQuantity() - invoiceProduct.getQuantity(); 
                    product.setQuantity(newQuantity); 
                    break; 
                } 
            } 
        } 
    } 
      
    public void createInvoice(ArrayList<Product> products, Customer customer){ 
          
        Invoice invoice =  new Invoice(noOfInvoices, products, customer, NewUI.systemDate, false); 
        invoices.add(invoice); 
        noOfInvoices++; 
  
    } 
      
    public void createProduct(String name, double wholesalePrice, double retailPrice, Supplier supplier){ 
          
        Product product = new Product(noOfProducts, name, 0, wholesalePrice, retailPrice, supplier);         
        products.add(product); 
        noOfProducts++; 
        
        updateSupplierProducts(supplier);
      
    } 
      
    public void createSupplier(String name, String email, String phoneNumber){ 

    	Supplier supplier = new Supplier(noOfSuppliers, name, email, phoneNumber);
    	updateSupplierProducts(supplier);
    	suppliers.add(supplier); 
    	noOfSuppliers++; 
    } 
    
    public void updateSupplierProducts(Supplier supplier){
    	
    	supplier.getProducts().clear();
    	ArrayList<Product> tempProducts =  new ArrayList<Product>();
    	
    	for (Product p : products){
    		if (p.getSupplier().getId()==supplier.getId()){
    			tempProducts.add(p);
    		}
    	}
		supplier.setProducts(tempProducts);
    }
      
    public void createCustomer(String name, Date dateOfBirth, String address, String phoneNumber){ 
              
        Customer customer = new Customer(noOfCustomers, name, dateOfBirth, address, phoneNumber); 
        customers.add(customer); 
        noOfCustomers++; 
          
    } 
    
    public Product findProduct(int sku) { 
        for (Product product : products) { 
            if ( product.getSku() == sku ) { 
                return product; 
            } 
        } 
        return products.get(0); 
    } 
    
    public void createNewUser(String userName,String password,boolean isAdmin,boolean isActive){ 
        User user = new User(noofUsers,userName, password, isAdmin, isActive); 
        users.add(user); 
        noofUsers++;
          
    } 
    
    public ArrayList<User> getUsers() {
		return users;
	}



	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
      
    public ArrayList<Order> getOrders() { 
        return orders; 
    } 
  
    public void setOrders(ArrayList<Order> orders) { 
        this.orders = orders; 
    } 
  
    public ArrayList<Product> getProducts() { 
        return products; 
    } 
  
    public void setProducts(ArrayList<Product> products) { 
        this.products = products; 
    } 
  
    public ArrayList<Supplier> getSuppliers() { 
        return suppliers; 
    } 
  
    public void setSuppliers(ArrayList<Supplier> suppliers) { 
        this.suppliers = suppliers; 
    } 
  
    public ArrayList<Invoice> getInvoices() { 
        return invoices; 
    } 
  
    public void setInvoices(ArrayList<Invoice> invoices) { 
        this.invoices = invoices; 
    } 
  
    public ArrayList<Customer> getCustomers() { 
        return customers; 
    } 
  
    public void setCustomers(ArrayList<Customer> customers) { 
        this.customers = customers; 
    } 
  
    public int getNoOfOrders() { 
        return noOfOrders; 
    } 
  
    public void setNoOfOrders(int noOfOrders) { 
        this.noOfOrders = noOfOrders; 
    } 
  
    public int getNoOfProducts() { 
        return noOfProducts; 
    } 
  
    public void setNoOfProducts(int noOfProducts) { 
        this.noOfProducts = noOfProducts; 
    } 
  
    public int getNoOfSuppliers() { 
        return noOfSuppliers; 
    } 
  
    public void setNoOfSuppliers(int noOfSuppliers) { 
        this.noOfSuppliers = noOfSuppliers; 
    } 
  
    public int getNoOfInvoices() { 
        return noOfInvoices; 
    } 
  
    public void setNoOfInvoices(int noOfInvoices) { 
        this.noOfInvoices = noOfInvoices; 
    } 
  
    public int getNoOfCustomers() { 
        return noOfCustomers; 
    } 
  
    public void setNoOfCustomers(int noOfCustomers) { 
        this.noOfCustomers = noOfCustomers; 
    }   

    public DefaultTableModel getStockLevel() {
		return stockLevel;
	}


	public void setStockLevel(DefaultTableModel stockLevel) {
		this.stockLevel = stockLevel;
	}
  
} 