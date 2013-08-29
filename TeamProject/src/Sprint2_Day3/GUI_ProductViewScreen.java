package Sprint2_Day3;

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
        screen.setLayout(new BorderLayout());  
        screen.setOpaque(true);  //content panes must be opaque  
          
        // Creates three panels used for the top portion, bottom portion and button portion of the screen 
        JPanel topJP = new JPanel();   
        topJP.setBorder(BorderFactory.createLineBorder(Color.RED));   
        JPanel botJP =  new JPanel(new BorderLayout());   
        botJP.setBorder(BorderFactory.createLineBorder(Color.blue));   
        JPanel buttonPanel = new JPanel(new FlowLayout());  
  
        // Create the title of the screen in the top panel 
        JLabel titlelbl = new JLabel("View Product" , JLabel.CENTER);   
        titlelbl.setFont(new Font("Arial",2 , 48));   
        topJP.add(titlelbl); 
// HEADER        
  
          
/* 
 *  Screen specific code goes here        
 */
        
        //************Product Details Panel*************************
        //Need to initiate product as it is not defined until inside a loop
        Product product = new Product(0,"",0,0,0, new Supplier(0,"","",""));
        
        //Retrieving the correct customer from the selectedCustomerID from the table. 
        for (Product p : NewUI.db.getProducts()){
      	  if (NewUI.selectedProductID == p.getSku()){
      		  product=p;
      	  }
        }
        
        
        
        
        
        
//        int supplierCount = 0;  
//    
//        // System.out.println(productsLine); 
////        Vector<Vector<String>> wholeTable =  NewUI.productsTableModel.getDataVector();  
//        
//      
//        if (NewUI.productsLine != -1) {  
//            Vector<String> tableLine = wholeTable.get(NewUI.productsLine);  
//            sku = Integer.parseInt(tableLine.get(0));  
//        }  
//        product = NewUI.db.findProduct(sku);  
    
        // System.out.println( tableLine.get(0) );  
        JPanel editProductForm = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
    
        JLabel SupplierLabel = new JLabel("Supplier: ");
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(SupplierLabel,constraint);  
    

        final JComboBox supplierCombo = new JComboBox();  
        // Populate supplier combo box  
        for (Supplier s : NewUI.db.getSuppliers()) {  
            supplierCombo.addItem(s.getName());  
            if (s.getId() == product.getSupplier().getId()) {  
                supplierCombo.setSelectedItem(s.getName());//.setSelectedIndex(supplierCount);  
            }  
//            supplierCount++;  
        }  
    
            
//      System.out.println(supplier.getName());  
    
        // for (Supplier s : db.getSuppliers()) {  
        //  
        //  
        // }  
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        editProductForm.add(supplierCombo,constraint);  
    
        JLabel nameLabel = new JLabel("Name:");
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(nameLabel,constraint);  
    
        final JTextField nameText = new JTextField();
        nameText.setText(product.getName());
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        editProductForm.add(nameText,constraint);  
    
        JLabel wholesaleLabel = new JLabel("Wholesale Price:  €");
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(wholesaleLabel,constraint);  
    
        final JTextField wholesaleText = new JTextField();  
        wholesaleText.setText(Double.toString(product.getWholesalePrice()));
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        editProductForm.add(wholesaleText,constraint);  
    
        JLabel retailLabel = new JLabel("Retail Price:  €");
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(retailLabel,constraint);  
    
        final JTextField retailText = new JTextField();
        retailText.setText(Double.toString(product.getRetailPrice()));
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 3;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        editProductForm.add(retailText,constraint);  
    
        JLabel quantityLabel = new JLabel("Stock quantity");
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 4;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(quantityLabel,constraint);  
    
        final JTextField quantityText = new JTextField();
        quantityText.setText(Double.toString(product.getQuantity()));
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 4;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        editProductForm.add(quantityText,constraint);  
    
        
        // Save button  
        final JButton productViewEditButton = new JButton("Edit");  
        productViewEditButton.setActionCommand(productScreenAccess);  
        productViewEditButton.addActionListener(new ActionListener() {  
            // Create product from fields
            public void actionPerformed(ActionEvent e) {
            	for (Product p : NewUI.db.getProducts()){
            		System.out.println(1);
    				  if (NewUI.selectedProductID == p.getSku()){
    					  System.out.println(2);
    					  if (true
//    						&&	  NewUI.check.isNotBlank(nameText.getText())
//    						   && NewUI.check.isPositiveNumeric(wholesaleText.getText())    
//    		                   && NewUI.check.isPositiveNumeric(retailText.getText())
    		                        ){
    						  System.out.println(3);
    						  Supplier supplier = NewUI.db.getSuppliers().get(supplierCombo.getSelectedIndex());
    						  p.setSupplier(supplier);
    						  p.setName(nameText.getText());
    						  p.setWholesalePrice(Double.parseDouble(wholesaleText.getText()));
        					  p.setRetailPrice(Double.parseDouble(retailText.getText()));
        					  p.setQuantity(Integer.parseInt(quantityText.getText()));
   
        					  NewUI.currentActiveScreen=e.getActionCommand();
        					  GUI_ProductScreen productScreen = new GUI_ProductScreen();
        					  productScreen.productScreen(); 
        					  NewUI.selectedCustomerID=0;
        				      CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
        				      cl.show(NewUI.gui, NewUI.currentActiveScreen); 
        					  break;
    					  }
    	    	  
            	
            	
            	
//                if (NewUI.check.isNotBlank(nameText.getText())  
//                        && NewUI.check.isPositiveNumeric(wholesaleText.getText())  
//                        && NewUI.check.isPositiveNumeric(retailText.getText())) {  
//                	
//                    Supplier supplier = NewUI.db.getSuppliers().get(supplierCombo.getSelectedIndex());  
//                    product.setName(nameText.getText());  
//                    product.setSupplier(supplier);  
//                    product.setWholesalePrice(Double.parseDouble(wholesaleText  
//                            .getText()));  
//                    product.setRetailPrice(Double.parseDouble(retailText  
//                            .getText()));  
//                    product.setQuantity( Integer.parseInt( quantityText.getText() ) );  
//
//                    NewUI.db.createProduct(nameText.getText(),  
//                    Double.parseDouble(wholesaleText.getText()),  
//                    Double.parseDouble(retailText.getText()),  
//                    NewUI.db.getSuppliers().get(supplierCombo.getSelectedIndex()));  
//    
//                    NewUI.currentActiveScreen = e.getActionCommand();  
////                  refresh();  
//                }  
            } }} 
        }); 
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 2;
        constraint.gridy = 4;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        editProductForm.add(productViewEditButton,constraint);
        

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
        JButton productViewDeleteButton = new JButton("Delete");
        productViewDeleteButton.setActionCommand(productScreenAccess);
        productViewDeleteButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
            	for (Product p : NewUI.db.getProducts()){
					if (NewUI.selectedProductID==p.getSku()){
						NewUI.db.getProducts().remove(p);
						NewUI.currentActiveScreen=e.getActionCommand();
					                       
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
