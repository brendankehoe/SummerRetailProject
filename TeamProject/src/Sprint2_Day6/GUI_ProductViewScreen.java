package Sprint2_Day6;

import java.awt.BorderLayout; 
import java.awt.CardLayout;
import java.awt.Color; 
import java.awt.FlowLayout; 
import java.awt.Font; 
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.util.Date;
import java.util.Vector; 
  





import javax.swing.BorderFactory; 
import javax.swing.BoxLayout;
import javax.swing.JButton; 
import javax.swing.JComboBox; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JTextField; 
import javax.swing.table.DefaultTableModel; 

public class GUI_ProductViewScreen { 
	  
    private String productEditScreenAccess = "productEdit", productScreenAccess = "product"; 
    
    public void productViewScreen(){ 
// HEADER 
        // Create screen panel that is used to replace the gui panel from MainUI.class 
        JPanel screen = new JPanel(new BorderLayout()); 
        screen.setOpaque(true);  //content panes must be opaque  
          
        // Creates three panels used for the top portion, bottom portion and button portion of the screen 
        JPanel topJP = new JPanel();     
        topJP.setBackground(NewUI.topBannerColor);
        JPanel botJP =  new JPanel(new BorderLayout());      
        JPanel buttonPanel = new JPanel(new FlowLayout());  

        // Create the title of the screen in the top panel 
        JLabel titlelbl = new JLabel("View Product", JLabel.CENTER);   
        titlelbl.setFont(NewUI.topBannerFont);   
        topJP.add(titlelbl); 
// HEADER        
  
          
/* 
 *  Screen specific code goes here        
 */
        
        //************Product Details Panel*************************
        //Need to initiate product as it is not defined until inside a loop
        Product product = new Product(0,"",0,0.0,0.0, new Supplier(0,"","",""));
        
        //Retrieving the correct product from the selectedProductID from the table. 
        for (Product p : NewUI.db.getProducts()){
      	  if (NewUI.selectedProductID == p.getSku()){
      		  product=p;
      	  }
        }
        
 
        JPanel editProductForm = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
    
        JLabel SupplierLabel = new JLabel("Supplier: ", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(SupplierLabel,constraint);  
    

        final JComboBox<String> supplierCombo = new JComboBox<String>();  
        // Populate supplier combo box  
        for (Supplier s : NewUI.db.getSuppliers()) {  
            supplierCombo.addItem(s.getName());  
            if (s.getId() == product.getSupplier().getId()) {  
                supplierCombo.setSelectedItem(s.getName());//.setSelectedIndex(supplierCount);  
            }  
        }  

        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.anchor = GridBagConstraints.WEST;
       
        //If current logged-in user is not admin, hide combo box, replace with textfield
        if (NewUI.currentUser.isAdmin()){
            editProductForm.add(supplierCombo,constraint); 
        }
        else {
        	JTextField supplierText = new JTextField();
            for (Supplier s : NewUI.db.getSuppliers()) {   
                if (s.getId() == product.getSupplier().getId()) {  
                	supplierText.setText(s.getName());//.setSelectedIndex(supplierCount);  
                    break;
                }  
            } 
            supplierText.setEditable(false);
        	editProductForm.add(supplierText,constraint); 
        }
  
    
        JLabel nameLabel = new JLabel("Name: ", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(nameLabel,constraint);  
    
        final JTextField nameText = new JTextField();
        nameText.setText(product.getName());
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.anchor = GridBagConstraints.WEST;
        editProductForm.add(nameText,constraint);  
    
        JLabel wholesaleLabel = new JLabel("Wholesale Price:  €", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(wholesaleLabel,constraint);  
    
        final JTextField wholesaleText = new JTextField();  
        wholesaleText.setText(Double.toString(product.getWholesalePrice()));
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.anchor = GridBagConstraints.WEST;
        editProductForm.add(wholesaleText,constraint);  
    
        JLabel retailLabel = new JLabel("Retail Price:  €", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(retailLabel,constraint);  
    
        final JTextField retailText = new JTextField();
        retailText.setText(Double.toString(product.getRetailPrice()));
        constraint.gridx = 1;
        constraint.gridy = 3;
        constraint.anchor = GridBagConstraints.WEST;
        editProductForm.add(retailText,constraint);  
    
        JLabel quantityLabel = new JLabel("Stock quantity", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 4;
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(quantityLabel,constraint);  
    
        final JTextField quantityText = new JTextField();
        quantityText.setText(Integer.toString(product.getQuantity()));
        quantityText.setEditable(false);
        constraint.gridx = 1;
        constraint.gridy = 4;
        constraint.anchor = GridBagConstraints.WEST;
        editProductForm.add(quantityText,constraint);  
    
        
        //If current logged-in user is not admin, set fields non-editable
        if (!NewUI.currentUser.isAdmin()){
        	nameText.setEditable(false);
        	wholesaleText.setEditable(false);
        	retailText.setEditable(false);
        }
        
        // Edit button  
        //If current logged-in user is not admin, hide edit product
        if (NewUI.currentUser.isAdmin()){
        	JButton productViewEditButton = new JButton("Edit");  
        	productViewEditButton.setActionCommand(productScreenAccess);  
        	productViewEditButton.addActionListener(new ActionListener() {  
        		// Create product from fields
        		public void actionPerformed(ActionEvent e) {
        			for (Product p : NewUI.db.getProducts()){
        				if (NewUI.selectedProductID == p.getSku()){
        					if (NewUI.check.isNotBlank(nameText.getText()) && NewUI.check.isPositiveNumeric(wholesaleText.getText())    
        							&& NewUI.check.isPositiveNumeric(retailText.getText())){
        						System.out.println(3);
        						Supplier supplier = NewUI.db.getSuppliers().get(supplierCombo.getSelectedIndex());
        						p.setSupplier(supplier);
        						p.setName(nameText.getText());
        						p.setWholesalePrice(Double.parseDouble(wholesaleText.getText()));
        						p.setRetailPrice(Double.parseDouble(retailText.getText()));
        						p.setQuantity(Integer.parseInt(quantityText.getText()));
        						NewUI.db.updateSupplierProducts(p.getSupplier());
        						
        						NewUI.currentActiveScreen=e.getActionCommand();
        						GUI_ProductScreen productScreen = new GUI_ProductScreen();
        						productScreen.productScreen(); 
        						NewUI.selectedCustomerID=0;
        						CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
        						cl.show(NewUI.gui, NewUI.currentActiveScreen); 
        						break;
        					}
        				}
        			}
        		} 
        	}); 

        constraint.gridx = 2;
        constraint.gridy = 4;
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(productViewEditButton,constraint);
        }
        

        JButton productEditBackButton = new JButton("Back");   
        productEditBackButton.setToolTipText("Cancels edit product and returns to Product screen");   
        productEditBackButton.setActionCommand(productScreenAccess);  
        productEditBackButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                NewUI.currentActiveScreen = e.getActionCommand();

            	GUI_ProductScreen productScreen = new GUI_ProductScreen();
            	productScreen.productScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand()); 
            } 
        });    
        buttonPanel.add(productEditBackButton);  
        // productEditBackButton.setSize(width, height)  


        // Delete button
        //If current logged-in user is not admin, hide delete product
        if (NewUI.currentUser.isAdmin()){
        	JButton productViewDeleteButton = new JButton("Delete");
        	productViewDeleteButton.setActionCommand(productScreenAccess);
        	productViewDeleteButton.addActionListener(new ActionListener(){ 
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			for (Product p : NewUI.db.getProducts()){
        				if (NewUI.selectedProductID==p.getSku()){
        					NewUI.db.getProducts().remove(p);
        					NewUI.currentActiveScreen=e.getActionCommand();
        					 NewUI.db.updateSupplierProducts(p.getSupplier());  

        					NewUI.selectedProductID=0;

        					GUI_ProductScreen productScreen = new GUI_ProductScreen();
        					productScreen.productScreen();
        					CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
        					cl.show(NewUI.gui, e.getActionCommand()); 

        					break;	
        				}
        			}
        		} 
        	});
        	buttonPanel.add(productViewDeleteButton); 
        }

        
        JPanel boxPanel = new JPanel();
        BoxLayout bl = new BoxLayout(boxPanel,BoxLayout.Y_AXIS);
        boxPanel.add(editProductForm);
        botJP.add(boxPanel, BorderLayout.CENTER); 
         
    
          
          
          
// FOOTER  
        // Adds the top, bottom and button panels to the screen panel 
        screen.add(topJP,BorderLayout.NORTH);   
        screen.add(botJP,BorderLayout.CENTER);   
        screen.add(buttonPanel,BorderLayout.SOUTH); 
          
        // Assigns the MainUI gui panel the contents of the screen panel 
        NewUI.productCreateScreen = screen; 
        NewUI.gui.add(NewUI.productCreateScreen,productEditScreenAccess); 
  
// FOOTER  
    } 

	

}
