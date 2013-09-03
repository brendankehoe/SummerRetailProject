package sprint2_Day7;

import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Date; 
  
import javax.swing.JComboBox; 
import javax.swing.JOptionPane; 
  
public class Check { 
  
    public boolean isPositiveInt(String text){ 
          
        boolean isPositiveInt=true; 
          
        //Try to catch if text is 1) an integer and 2) positive, if error is caught return false 
        try { 
            Integer.parseInt(text); 
            if (Integer.parseInt(text)<=0){ 
                JOptionPane.showMessageDialog(null, "'"+ text + "' is not a positive integer", "Error",JOptionPane.ERROR_MESSAGE); 
                isPositiveInt=false; 
            } 
        } 
        catch (NumberFormatException e){ 
            JOptionPane.showMessageDialog(null, "'"+ text + "' is not an integer", "Error",JOptionPane.ERROR_MESSAGE); 
            isPositiveInt=false; 
        } 
          
        return isPositiveInt; 
          
    } 
      
    public boolean isPositiveNumeric(String text){ 
          
        boolean isPositiveNumeric=true; 
  
        //Try to catch if text is 1) not blank 2) an integer and 3) positive, if error is caught return false 
  
        if (text.equals("")){ 
            JOptionPane.showMessageDialog(null, "Please complete all fields", "Error",JOptionPane.ERROR_MESSAGE); 
            isPositiveNumeric=false; 
        } 
        else{ 
  
  
            try { 
                if (Double.parseDouble(text)<=0){ 
                    JOptionPane.showMessageDialog(null, "'"+ text + "' is not a positive value", "Error",JOptionPane.ERROR_MESSAGE); 
                    isPositiveNumeric=false; 
                } 
            } 
            catch (NumberFormatException e){ 
                JOptionPane.showMessageDialog(null, "'"+ text + "' is not numeric", "Error",JOptionPane.ERROR_MESSAGE); 
                isPositiveNumeric=false; 
            } 
  
        } 
  
        return isPositiveNumeric; 
          
    } 
      
    public boolean isNotBlank(String text){ 
          
        boolean isNotBlank = true; 
        if (text.equals("")){ 
            JOptionPane.showMessageDialog(null, "Please complete all fields", "Error",JOptionPane.ERROR_MESSAGE); 
            isNotBlank=false; 
        } 
        return isNotBlank; 
    } 
      
    public boolean isValidDate(String dateString) { 
          
        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy"); 
        try { 
            df.parse(dateString); 
            return true; 
        } catch (ParseException e) { 
            return false; 
        } 
    } 
      
    public boolean isBasketPopulated(ArrayList<Product> orderBasket){ 
        boolean isBasketPopulated = true;        
        if(orderBasket.size() == 0){ 
            JOptionPane.showMessageDialog(null, "Please add a product to the basket", "Error",JOptionPane.ERROR_MESSAGE); 
            isBasketPopulated = false; 
        } 
        return isBasketPopulated; 
    } 
  
    public boolean isProductSupplierMatch(JComboBox<String> combo){ 
        boolean isProductSupplierMatch = true; 
        if(combo.getSelectedIndex() < 0){ 
            JOptionPane.showMessageDialog(null, "Please check that product matches supplier", "Error",JOptionPane.ERROR_MESSAGE); 
            isProductSupplierMatch = false; 
        } 
        return isProductSupplierMatch; 
    } 
      
    public boolean isOfLegalAge(Date dob){ 
          
        boolean isOfLegalAge=true; 
        Date dateNow = new Date();; 
          
        //Need to clone original date as they are inherently linked otherwise 
        Date dOB = (Date) dob.clone(); 
          
        //Increase dOB by 18 years and check to see if it is after the current date 
        dOB.setYear(dOB.getYear()+18);         
  
        if (dOB.after(dateNow)){ 
            JOptionPane.showMessageDialog(null, "Customers must be at least 18 years old", "Error",JOptionPane.ERROR_MESSAGE); 
            isOfLegalAge=false; 
        } 
          
          
        return isOfLegalAge; 
                  
    } 
      
    public boolean isACustomer(int id, ArrayList<Customer> customers){ 
          
        boolean isACustomer = false; 
          
        for (Customer c : customers){ 
            if (c.getId()==id){ 
                isACustomer=true; 
                break; 
            } 
        } 
          
        if (!isACustomer){ 
            JOptionPane.showMessageDialog(null, "Please select a customer", "Error",JOptionPane.ERROR_MESSAGE); 
        } 
          
        return isACustomer; 
    } 
    
    //Adding isAUser check - sharon 
    public boolean isAUser(int id, ArrayList<User> users){  
          
        boolean isAUser = false;  
            
        for (User c : users){  
            if (c.getUserId()==id){  
            	isAUser=true;  
                break;  
            }  
        }  
            
        if (!isAUser){  
            JOptionPane.showMessageDialog(null, "Please select a customer", "Error",JOptionPane.ERROR_MESSAGE);  
        }  
            
        return isAUser;  
    }
    
    public boolean confirmPasswordMatch(String password, String confirmPassword ){
    	
    	boolean isTheSame = true;
    	if(!password.equals(confirmPassword)){
    		 JOptionPane.showMessageDialog(null, "Incorrect Password / Confirm Password Combination", "Error",JOptionPane.ERROR_MESSAGE); 
    		 isTheSame=false; 
    		
    	}
		return isTheSame;
    	
    }
    
    public boolean isAProduct(int id, ArrayList<Product> products){  
        
    	boolean isAProduct = false;  
            
        for (Product p : products){  
            if (p.getSku()==id){  
                isAProduct=true;  
                break;  
            }  
        }  
            
        if (!isAProduct){  
            JOptionPane.showMessageDialog(null, "Please select a product", "Error",JOptionPane.ERROR_MESSAGE);  
        }  
            
        return isAProduct;  
    } 
    
    public boolean isASupplier(int id, ArrayList<Supplier> suppliers){  
        
    	boolean isASupplier = false;  
            
        for (Supplier s : suppliers){  
            if (s.getId()==id){  
                isASupplier=true;  
                break;  
            }  
        }  
            
        if (!isASupplier){  
            JOptionPane.showMessageDialog(null, "Please select a supplier", "Error",JOptionPane.ERROR_MESSAGE);  
        }  
            
        return isASupplier;  
    } 
    
    public boolean isSufficientStock(RetailSystem rd, ArrayList<Product> invoiceBasket, String prodName, int qty){
    	boolean isSufficientStock = true;
    	int stockqty=0;
    	for(Product product : rd.getProducts()){ 
    		if(product.getName().equals(prodName)){
    			stockqty = product.getQuantity();
            }
    	}

    	if(invoiceBasket.size()==0){
    		if(qty > stockqty){
    			isSufficientStock = false;
    			JOptionPane.showMessageDialog(null, "There is insufficient stock. "+stockqty+" "+prodName+" remaining.", "Error",JOptionPane.ERROR_MESSAGE); 
    		}
    	}
    	else{
    		for(Product product : invoiceBasket){
		        if(product.getName().equals(prodName)){ 
		            if(qty > stockqty - product.getQuantity()){
		          	  isSufficientStock = false;
		          	  JOptionPane.showMessageDialog(null, "There is insufficient stock. "+(stockqty - product.getQuantity())+" "+prodName+" remaining.", "Error",JOptionPane.ERROR_MESSAGE); 
		            }
		        }
    	    }
    	}
    	return isSufficientStock;
    }
    
    
    public boolean isInvoiceProductSelected(JComboBox<String> combo){ 
        boolean isInvoiceProductSelected = true; 
        if(combo.getSelectedIndex() < 0){ 
            JOptionPane.showMessageDialog(null, "Please check that a product is selected", "Error",JOptionPane.ERROR_MESSAGE); 
            isInvoiceProductSelected = false; 
        } 
        return isInvoiceProductSelected; 
    } 
    
    public boolean isAOrder(int id, ArrayList<Order> orders){ 
        
        boolean isAOrder = false; 
          
        for (Order o : orders){ 
            if (o.getId()==id){ 
                isAOrder=true; 
                break; 
            } 
        } 
          
        if (!isAOrder){ 
            JOptionPane.showMessageDialog(null, "Please select an order", "Error",JOptionPane.ERROR_MESSAGE); 
        } 
          
        return isAOrder; 
    }
    
    public boolean isAnInvoice(int id){ 
        
        boolean isAnInvoice = false; 
          
        for (Invoice i : NewUI.db.getInvoices()){ 
            if (i.getId()==id){ 
                isAnInvoice=true; 
                break; 
            } 
        } 
          
        if (!isAnInvoice){ 
            JOptionPane.showMessageDialog(null, "Please select an invoice", "Error",JOptionPane.ERROR_MESSAGE); 
        } 
          
        return isAnInvoice; 
    }
    
} 