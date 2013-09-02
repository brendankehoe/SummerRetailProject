package Sprint2_Day3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
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
        topJP.setBorder(BorderFactory.createLineBorder(Color.RED));  
        JPanel botJP =  new JPanel(new BorderLayout());  
        botJP.setBorder(BorderFactory.createLineBorder(Color.blue));  
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
        Order order = new Order(0, dummyProd, new Date(), true);
        
        for (Order o : NewUI.db.getOrders()){
      	  if (NewUI.selectedOrderID == o.getId()){
      		  order=o;
      	  }
        }
        
        
        JPanel orderCreateFormPanel = new JPanel(new SpringLayout());
        // Labels to display order unique ID
        JLabel createOrderIDLabel = new JLabel("ID:", JLabel.TRAILING); 
        JLabel createOrderIDDisplayLabel = new JLabel(NewUI.db.getNoOfOrders()+"", JLabel.TRAILING); 
        createOrderIDLabel.setLabelFor(createOrderIDDisplayLabel); 
        // Label & ComboBox to display order supplier
        JLabel createOrderSupplierLabel = new JLabel("Supplier:", JLabel.TRAILING); 
        NewUI.listOfSuppliersVector = new Vector<String>(); 
        if(NewUI.orderBasket.size()>0){ 
        	NewUI.listOfSuppliersVector.add(NewUI.orderBasket.get(0).getSupplier().getName()); 
        } 
        else{ 
            for(Supplier supplier : NewUI.db.getSuppliers()){ 
            	NewUI.listOfSuppliersVector.add(supplier.getName()); 
            } 
        } 
        NewUI.createOrderSupplierCombo = new JComboBox<String>(NewUI.listOfSuppliersVector); 
        NewUI.createOrderSupplierCombo.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) { 
                JComboBox cb = (JComboBox) e.getSource(); 
                NewUI.createOrderSupplierIndex = cb.getSelectedIndex(); 
                  
                NewUI.listOfOrderProductsVector.clear(); 
                for(Product product : NewUI.db.getProducts()){ 
                    if(NewUI.listOfSuppliersVector.get(NewUI.createOrderSupplierIndex).equals(product.getSupplier().getName())){ 
                    	NewUI.listOfOrderProductsVector.add(product.getName()); 
                    } 
                } 
            } 
        }); 
        NewUI.createOrderSupplierCombo.setPreferredSize(new Dimension(40,40)); 
        createOrderSupplierLabel.setLabelFor(NewUI.createOrderSupplierCombo); 
        // Label & ComboBox to display order products
        JLabel createOrderProductLabel = new JLabel("Product:", JLabel.TRAILING); 
        NewUI.listOfOrderProductsVector = new Vector<String>(); 
        final JComboBox<String> createOrderProductCombo = new JComboBox<String>(NewUI.listOfOrderProductsVector); 
        createOrderProductLabel.setLabelFor(createOrderProductCombo); 
  
        JLabel createOrderQuantityLabel = new JLabel("Quantity:", JLabel.TRAILING); 
        final JTextField createOrderQuantityField = new JTextField(5); 
  
  
        orderCreateFormPanel.add(createOrderIDLabel); 
        orderCreateFormPanel.add(createOrderIDDisplayLabel); 
        orderCreateFormPanel.add(createOrderSupplierLabel); 
        orderCreateFormPanel.add(NewUI.createOrderSupplierCombo); 
        orderCreateFormPanel.add(createOrderProductLabel); 
        orderCreateFormPanel.add(createOrderProductCombo); 
        orderCreateFormPanel.add(createOrderQuantityLabel); 
        orderCreateFormPanel.add(createOrderQuantityField); 
  
        JPanel orderBsketPanel = new JPanel(new BorderLayout()); 
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
        SpringUtilities.makeCompactGrid(orderCreateFormPanel, 
                4, 2,   //rows, columns 
                6, 6,   //initX, initY 
                6, 6);  //xPad, yPad 
        orderBsketPanel.add(basketLabel, BorderLayout.NORTH); 
        orderBsketPanel.add(new JScrollPane(orderBasketTable), BorderLayout.CENTER); 
  
  
        JButton orderCreateBackButton = new JButton("Cancel"); 
        orderCreateBackButton.setToolTipText("Cancels new order and returns to Order screen"); 
        orderCreateBackButton.setActionCommand(orderScreenAccess); 
        orderCreateBackButton.addActionListener(new ActionListener(){ 
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
        JButton orderCreateAddButton = new JButton("Add Product"); 
//        orderCreateAddButton.addActionListener(new ActionListener() { 
//            @Override
//            public void actionPerformed(ActionEvent arg0) { 
//
//                if (NewUI.check.isPositiveNumeric(createOrderQuantityField.getText()) && NewUI.check.isProductSupplierMatch(createOrderProductCombo)){ 
//                	addToBasket(NewUI.orderBasket, NewUI.listOfOrderProductsVector.get(createOrderProductCombo.getSelectedIndex()), Integer.parseInt(createOrderQuantityField.getText())); 
//                	orderCreateScreenRefresh(); 
//                    NewUI.createOrderSupplierIndex = NewUI.createOrderSupplierCombo.getSelectedIndex(); 
//                    NewUI.listOfOrderProductsVector.clear(); 
//                    for(Product product : NewUI.db.getProducts()){ 
//                        if(NewUI.listOfSuppliersVector.get(NewUI.createOrderSupplierIndex).equals(product.getSupplier().getName())){ 
//                        	NewUI.listOfOrderProductsVector.add(product.getName()); 
//                        } 
//                    } 
//                } 
//            } 
//        }); 
        JButton orderCreateSubmitButton = new JButton("Submit Order"); 
        orderCreateSubmitButton.setActionCommand(orderScreenAccess); 
//        orderCreateSubmitButton.addActionListener(new ActionListener() {  
//            @Override
//            public void actionPerformed(ActionEvent e) { 
//                if(NewUI.check.isBasketPopulated(NewUI.orderBasket)){ 
//                    orderSubmit(NewUI.orderBasket); 
//                    
//                    NewUI.orderScreen.removeAll();
//	            	GUI_OrderScreen orderScreen = new GUI_OrderScreen();
//	            	orderScreen.orderScreen();
//	            	NewUI.orderBasket.clear();
//                    
//                    CardLayout cl = (CardLayout)(NewUI.gui.getLayout());  
//                    cl.show(NewUI.gui, e.getActionCommand()); 
//                    NewUI.currentActiveScreen=e.getActionCommand(); 
//                } 
//            } 
//        }); 
        buttonPanel.add(orderCreateBackButton); 
        buttonPanel.add(orderCreateAddButton); 
        buttonPanel.add(orderCreateSubmitButton); 
          
        botJP.add(orderCreateFormPanel,BorderLayout.CENTER); 
        botJP.add(orderBsketPanel,BorderLayout.SOUTH); 

        
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

	public void orderCreateScreenRefresh(){ 
        NewUI.orderViewScreen.removeAll(); 
        orderViewScreen(); 
        
        CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
        cl.show(NewUI.gui, NewUI.currentActiveScreen); 
    } 
}

