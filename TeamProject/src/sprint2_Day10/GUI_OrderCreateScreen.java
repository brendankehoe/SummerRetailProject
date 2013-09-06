package sprint2_Day10;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

 
public class GUI_OrderCreateScreen {
	
	private String orderScreenAccess = "order", orderCreateScreenAccess = "orderCreate";
	
	public void orderCreateScreen(){
// HEADER
        // Create screen panel that is used to replace the gui panel from MainUI.class 
        JPanel screen = new JPanel(new BorderLayout()); 
        screen.setOpaque(true);  //content panes must be opaque  
          
        // Creates three panels used for the top portion, bottom portion and button portion of the screen 
        JPanel topJP = new JPanel();     
        topJP.setBackground(NewUI.topBannerColor);
        JPanel botJP =  new JPanel(); 
        botJP.setLayout(new BoxLayout(botJP,BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel(new FlowLayout());  

        // Create the title of the screen in the top panel 
        JLabel titlelbl = new JLabel("Create Order", JLabel.CENTER);   
        titlelbl.setFont(NewUI.topBannerFont);   
        topJP.add(titlelbl);
// HEADER       

        
/*
 *	Screen specific code goes here       
 */
        JPanel orderCreateFormPanel = new JPanel(new SpringLayout());
        // Labels to display order unique ID
        JLabel createOrderIDLabel = new JLabel("ID:", JLabel.TRAILING); 
        JLabel createOrderIDDisplayLabel = new JLabel(NewUI.db.getNoOfOrders()+"", JLabel.LEADING); 
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
                JComboBox<String> createOrderProductCombo = new JComboBox<String>(NewUI.listOfOrderProductsVector); 
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
  
        JPanel orderBasketPanel = new JPanel(new BorderLayout()); 
        JLabel basketLabel = new JLabel("Product Basket"); 
          
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
        
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        orderBasketPanel.setBorder(loweredetched);
        orderBasketPanel.add(basketLabel, BorderLayout.NORTH); 
        orderBasketPanel.add(new JScrollPane(orderBasketTable), BorderLayout.CENTER); 
  
  
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
        orderCreateAddButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent arg0) { 

                if (NewUI.check.isPositiveNumeric(createOrderQuantityField.getText()) && NewUI.check.isProductSupplierMatch(createOrderProductCombo)){ 
                	addToBasket(NewUI.orderBasket, NewUI.listOfOrderProductsVector.get(createOrderProductCombo.getSelectedIndex()), Integer.parseInt(createOrderQuantityField.getText())); 
                	orderCreateScreenRefresh(); 
                    NewUI.createOrderSupplierIndex = NewUI.createOrderSupplierCombo.getSelectedIndex(); 
                    NewUI.listOfOrderProductsVector.clear(); 
                    for(Product product : NewUI.db.getProducts()){ 
                        if(NewUI.listOfSuppliersVector.get(NewUI.createOrderSupplierIndex).equals(product.getSupplier().getName())){ 
                        	NewUI.listOfOrderProductsVector.add(product.getName()); 
                        } 
                    } 
                } 
            } 
        }); 
        JButton orderCreateSubmitButton = new JButton("Submit Order"); 
        orderCreateSubmitButton.setActionCommand(orderScreenAccess); 
        orderCreateSubmitButton.addActionListener(new ActionListener() {  
            @Override
            public void actionPerformed(ActionEvent e) { 
                if(NewUI.check.isBasketPopulated(NewUI.orderBasket)){ 
                    orderSubmit(NewUI.orderBasket); 
                    
                    NewUI.orderScreen.removeAll();
	            	GUI_OrderScreen orderScreen = new GUI_OrderScreen();
	            	orderScreen.orderScreen();
	            	NewUI.orderBasket.clear();
                    
                    CardLayout cl = (CardLayout)(NewUI.gui.getLayout());  
                    cl.show(NewUI.gui, e.getActionCommand()); 
                    NewUI.currentActiveScreen=e.getActionCommand(); 
                } 
            } 
        }); 
        buttonPanel.add(orderCreateBackButton); 
        buttonPanel.add(orderCreateAddButton); 
        buttonPanel.add(orderCreateSubmitButton); 
          
        botJP.add(orderCreateFormPanel); 
        botJP.add(orderBasketPanel); 

        
// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        
		NewUI.orderCreateScreen = screen;
		NewUI.gui.add(NewUI.orderCreateScreen,orderCreateScreenAccess);
// FOOTER 
    }

	public static void addToBasket(ArrayList<Product> basket, String prodName, int qty){ 
        for(Product product : NewUI.db.getProducts()){ 
            if(product.getName().equals(prodName)){ 
                Product tempProd = new Product(product); 
                  
                if(basket.size()==0){ 
                    tempProd.setQuantity(qty); 
                    basket.add(tempProd); 
                } 
                else{ 
                    int isProductPresent=0; 
                    for(Product basketProduct : basket){ 
                        if(tempProd.getSku() == basketProduct.getSku()){ 
                            isProductPresent = 1; 
                            break; 
                        } 
                        else{ 
                            isProductPresent = 2; 
                        } 
                    } 
                    switch (isProductPresent) { 
                        case 1: for(Product basketProduct : basket){ 
                                    if(tempProd.getSku() == basketProduct.getSku()){ 
                                        basketProduct.increaseQuantity(qty); 
                                    } 
                                } 
                                break; 
  
                        case 2: tempProd.setQuantity(qty); 
                        		basket.add(tempProd); 
                                break; 
                    } 
                } 
            } 
        } 
    }
	
	public void orderSubmit(ArrayList<Product> prodArray){ 
        ArrayList<Product> basketContents = new ArrayList<Product>( (ArrayList<Product>) prodArray.clone()); 
        NewUI.db.createOrder(basketContents); 
        // For auto-delivery of order upon submission 
    }
	
    public void orderCreateScreenRefresh(){ 
        NewUI.orderCreateScreen.removeAll(); 
        orderCreateScreen(); 
        
        CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
        cl.show(NewUI.gui, NewUI.currentActiveScreen); 
    } 
}
