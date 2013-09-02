package Sprint2_Day6;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

 
public class GUI_OrderViewScreen {
	
	private String orderScreenAccess = "order", orderViewScreenAccess = "orderView";
	
	public void orderViewScreen(){
// HEADER
		// Create screen panel that is used to replace the gui panel from MainUI.class
		JPanel screen = new JPanel(new BorderLayout()); 
		screen.setLayout(new BorderLayout()); 
        screen.setOpaque(true);  //content panes must be opaque 
        
		// Creates three panels used for the top portion, bottom portion and button portion of the screen
        JPanel topJP = new JPanel();  
        topJP.setBackground(NewUI.topBannerColor);
        JPanel botJP =  new JPanel();  
        botJP.setLayout(new BoxLayout(botJP,BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel(new FlowLayout()); 
        
        // Create the title of the screen in the top panel
        JLabel titlelbl = new JLabel("View Order", JLabel.CENTER);  
        titlelbl.setFont(new Font("Arial",2 , 48));  
        topJP.add(titlelbl);
// HEADER       

        
/*
 *	Screen specific code goes here       
 */
       // Create dummy Order variable to hold object attributes to populate text fields
        ArrayList<Product> dummyProd = new ArrayList<Product>();
        dummyProd.add(new Product(0,"",0,0.0,0.0, new Supplier(0,"","","")));
        Order order = new Order(0, dummyProd, new Date(), true, new Date());
        
        for (Order o : NewUI.db.getOrders()){
      	  if (NewUI.selectedOrderID == o.getId()){
      		  order=o;
      	  }
        }
                
        JPanel orderViewFormPanel = new JPanel(new BorderLayout());
        JPanel orderViewWestPanel = new JPanel(new GridBagLayout());
        JPanel orderViewEastPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        
        // Labels to display order unique ID
        JLabel viewOrderIDLabel = new JLabel("ID:", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewWestPanel.add(viewOrderIDLabel,constraint);    
        
        JLabel viewOrderIDDisplayLabel = new JLabel(order.getId()+"", JLabel.TRAILING); 
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewWestPanel.add(viewOrderIDDisplayLabel,constraint);    
                
        // Label & ComboBox to display Order customer
        JLabel viewOrderSupplierLabel = new JLabel("Supplier:", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewWestPanel.add(viewOrderSupplierLabel,constraint);  
        
        JLabel viewOrderSupplierDsiplayLabel = new JLabel(order.getSupplier().getName(), JLabel.TRAILING);
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewWestPanel.add(viewOrderSupplierDsiplayLabel,constraint);
       
        
        // Label & ComboBox to display Order total amount
        JLabel viewOrderAmountLabel = new JLabel("Total amount (€):", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewWestPanel.add(viewOrderAmountLabel,constraint);  
        
        JLabel viewOrderAmountDsiplayLabel = new JLabel(order.calculateTotalRetailValue()+"", JLabel.TRAILING);
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewWestPanel.add(viewOrderAmountDsiplayLabel,constraint);        
        
        // Label & ComboBox to display Order creation date
        JLabel viewOrderDateCreatedLabel = new JLabel("Creation date:", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewEastPanel.add(viewOrderDateCreatedLabel,constraint);  
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        JLabel viewOrderDateCreatedDsiplayLabel = new JLabel(sdf.format(order.getDate())+"", JLabel.TRAILING);
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewEastPanel.add(viewOrderDateCreatedDsiplayLabel,constraint);
        
        // Label & ComboBox to display Order delivery date
        JLabel viewOrderDateDeliveredLabel = new JLabel("Delivery date:", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewEastPanel.add(viewOrderDateDeliveredLabel,constraint);  
        
        JLabel viewOrderDateDeliveredDsiplayLabel = new JLabel(sdf.format(order.getDeliveryDate())+"", JLabel.TRAILING);
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewEastPanel.add(viewOrderDateDeliveredDsiplayLabel,constraint);
       
        
        // Label & ComboBox to display Order total amount
        JLabel viewOrderStatusLabel = new JLabel("Delivery Status:", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewEastPanel.add(viewOrderStatusLabel,constraint);  
        
        String deliveryStatus;
        if(order.isDelivered()){deliveryStatus="Delivered";}
        else{deliveryStatus="Not delivered";}
        JLabel viewOrderStatusDsiplayLabel = new JLabel(deliveryStatus, JLabel.TRAILING);
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        orderViewEastPanel.add(viewOrderStatusDsiplayLabel,constraint);  
        


        orderViewFormPanel.add(orderViewWestPanel, BorderLayout.WEST);
        orderViewFormPanel.add(orderViewEastPanel, BorderLayout.EAST);

  
        JPanel orderBasketPanel = new JPanel(new BorderLayout()); 
        JLabel basketLabel = new JLabel("Product Basket"); 
          
        // Populate orderBasket with the products from temporary order object
        for(Product product : order.getProducts()){
        	Product tempProd = new Product(product); 
            NewUI.orderBasket.add(tempProd); 
        }        
        
        //create JTable for bottom Panel load test data for gui design  
		DefaultTableModel dtm = new DefaultTableModel()
        {
      	  public boolean isCellEditable(int row, int column)
      	  {
      		  return false;//This causes all cells to be not editable
      	  }
        };
        dtm.addColumn("Product SKU"); 
        dtm.addColumn("Product Name"); 
        dtm.addColumn("Quantity"); 
        for (Product product : NewUI.orderBasket){ 
            Vector<String> singleProduct = new Vector<String>(); 
            singleProduct.add(Integer.toString(product.getSku())); 
            singleProduct.add(product.getName()); 
            singleProduct.add(Integer.toString(product.getQuantity())); 
            dtm.addRow(singleProduct);       
        }  
        JTable orderBasketTable = new JTable(); 
        orderBasketTable.setEnabled(false); 
        orderBasketTable.setModel(dtm);
        
        //Lay out the panel.
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        orderBasketPanel.setBorder(loweredetched);
        orderBasketPanel.add(basketLabel, BorderLayout.NORTH); 
        orderBasketPanel.add(new JScrollPane(orderBasketTable), BorderLayout.CENTER); 
  
        JButton orderViewBackButton = new JButton("Back");  
        orderViewBackButton.setActionCommand(orderScreenAccess); 
        orderViewBackButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUI_OrderScreen orderScreen = new GUI_OrderScreen();
            	orderScreen.orderScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand());
                NewUI.currentActiveScreen=e.getActionCommand();
                
                NewUI.orderBasket.clear();
            } 
         });
        buttonPanel.add(orderViewBackButton); 
        
        /*
         * Delete button: When edit is pressed, code cycles through customers, finds the one with the corresponding ID 
         * to what's selected in the table (selectedCustomerID) and deletes the customer accordingly
         */
        //If current logged-in user is not admin, hide delete button
        if (NewUI.currentUser.isAdmin()){
        JButton orderViewDeleteButton = new JButton("Delete Order");  
        orderViewDeleteButton.setActionCommand(orderScreenAccess); 
        buttonPanel.add(orderViewDeleteButton);
        orderViewDeleteButton.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e){    		  
      		  for (Order o : NewUI.db.getOrders()){
      	    	  if (NewUI.selectedOrderID==o.getId()){
      	    		  NewUI.db.getOrders().remove(o);
      	    		  NewUI.currentActiveScreen=e.getActionCommand();
      	    		  
      	    		  NewUI.selectedOrderID=0;
      	    		  
      	    		  GUI_OrderScreen orderScreen = new GUI_OrderScreen();
      	    		  orderScreen.orderScreen();
                	  CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                	  cl.show(NewUI.gui, e.getActionCommand()); 
                	  
      	    		  break;
      	    	  }
      	      }
      	  }
        });
        }
          
        botJP.add(orderViewFormPanel,BorderLayout.CENTER); 
        botJP.add(orderBasketPanel,BorderLayout.SOUTH); 


// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        
		NewUI.orderViewScreen = screen;
		NewUI.gui.add(NewUI.orderViewScreen,orderViewScreenAccess);
// FOOTER 
    }

}

