package Sprint2_Day3; 
  
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Calendar;
import java.util.Date; 
import java.util.Random;
  
  
  
public class RetailSystem { 
      
    private ArrayList<Order> orders = new ArrayList<Order>(); 
    private ArrayList<Product> products = new ArrayList<Product>(); 
    private ArrayList <Supplier> suppliers = new ArrayList<Supplier>(); 
    private ArrayList <Invoice> invoices = new ArrayList<Invoice>(); 
    private ArrayList <Customer> customers = new ArrayList<Customer>(); 
    private int noOfOrders=1; 
    private int noOfProducts=1; 
    private int noOfSuppliers = 1; 
    private int noOfInvoices = 1; 
    private int noOfCustomers = 1; 
      
      
    public RetailSystem(){ 
              
        //Create suppliers 
        createSupplier("Four corners"); 
        createSupplier("Premier"); 
        createSupplier("Colmans"); 
        createSupplier("C & R"); 
        createSupplier("S & S"); 
      
        //Create products 
        createProduct("Macks Sassy Red", 1, 3, suppliers.get(1)); 
        createProduct("Galway Hooker", 1.5, 3.5, suppliers.get(1)); 
        createProduct("Twisted Hop", 2, 4, suppliers.get(2)); 
        createProduct("Grolsch", 3, 5, suppliers.get(3)); 
        createProduct("O'Hara's IPA", 3, 5, suppliers.get(4)); 
        createProduct("Guinness", 6, 9, suppliers.get(2)); 
        createProduct("Bulmers", 2, 4, suppliers.get(2)); 
                  
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
        
    } 
    

    public void createRandomDatabase(){
    	
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
        			prod1.setQuantity(rg.nextInt((int) demandFactor)); 
        			sampleProductList.add(prod1);

        			Invoice invoice =  new Invoice(noOfInvoices,sampleProductList,customers.get(rg.nextInt(customers.size())),date,false);
        			invoices.add(invoice);
        			outboundDelivery(sampleProductList);
        			noOfInvoices++;

        	}
        	
        	//Update stock if less than 100
        	for (Product p : products){
        		if (p.getQuantity()<100){
        			
            		sampleProductList= new ArrayList<Product>();
        			Product prod1 = new Product(p); 		
        			prod1.setQuantity(100); 
        			sampleProductList.add(prod1);
        			
        			Order order = new Order(noOfOrders,sampleProductList,date,false);
        			orders.add(order);
        			inboundDelivery(sampleProductList);
        			noOfOrders++;
        		}
        	}
        }
    	
    }
      
 
      
    public void createOrder(ArrayList<Product> products){ 
              
        //set date 
        Date date = new Date(); 
          
        Order order =  new Order(noOfOrders, products, date, false); 
        orders.add(order); 
        noOfOrders++; 
  
    } 
      
  
    //update stock levels (add to stock levels) 
    public void inboundDelivery(ArrayList<Product> orderProducts){ 
        int newQuantity; 
        int orderID = noOfOrders -1; 
          
        for(Order order : orders){ 
            if( order.getId() == orderID ){ 
                order.setDelivered(true); 
                break; 
            } 
        } 
          
        for(Product orderProduct : orderProducts){ 
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
          
        //set date 
        Date date = new Date(); 
          
        Invoice invoice =  new Invoice(noOfInvoices, products, customer, date, false); 
        invoices.add(invoice); 
        noOfInvoices++; 
  
    } 
      
    public void createProduct(String name, double wholesalePrice, double retailPrice, Supplier supplier){ 
          
        Product product = new Product(noOfProducts, name, 0, wholesalePrice, retailPrice, supplier);         
        products.add(product); 
        noOfProducts++; 
      
    } 
      
    public void createSupplier(String name){ 
          
        Supplier supplier = new Supplier(noOfSuppliers, name); 
        suppliers.add(supplier); 
        noOfSuppliers++; 
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
  
} 