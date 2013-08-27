package Sprint2_Day1;
import java.util.ArrayList; 
import java.util.Date; 
  
public class Customer { 
    //customer going into our shop and buying from us. 
  
    private int id; 
    private String name; 
    private Date dateOfBirth; 
    private String address; 
    private String phoneNumber; 
  
    public Customer(int id, String name, Date dateOfBirth, String address, String phoneNumber) { 
        this.id = id; 
        this.name = name; 
        this.dateOfBirth=dateOfBirth; 
        this.address = address; 
        this.phoneNumber = phoneNumber; 
    } 
  
    public int getId() { 
        return id; 
    } 
  
    public void setId(int id) { 
        this.id = id; 
    } 
  
    public String getName() { 
        return name; 
    } 
  
    public void setName(String name) { 
        this.name = name; 
    } 
  
    public String getAddress() { 
        return address; 
    } 
  
    public void setAddress(String address) { 
        this.address = address; 
    } 
  
    public String getPhoneNumber() { 
        return phoneNumber; 
    } 
  
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    } 
  
    public Date getDateOfBirth() { 
        return dateOfBirth; 
    } 
  
    public void setDateOfBirth(Date dateOfBirth) { 
        this.dateOfBirth = dateOfBirth; 
    } 
      
    public ArrayList<Invoice> getInvoices(ArrayList<Invoice> invoices){ 
        ArrayList<Invoice> invs = new ArrayList<Invoice>();      
        for (Invoice i : invoices){ 
            if(i.getCustomer().getId()==this.id){ 
                invs.add(i); 
            } 
        }        
        return invs; 
    } 
      
    public double getTotalMoneySpent(ArrayList<Invoice> invoices){ 
          
        double totalMoneySpent=0; 
        ArrayList<Invoice> invs = new ArrayList<Invoice>();  
        for (Invoice i : invoices){ 
            if(i.getCustomer().getId()==this.id){ 
                totalMoneySpent=totalMoneySpent+i.calculateTotalRetailValue(); 
            } 
        }    
          
        return totalMoneySpent; 
          
    } 
  
  
} 