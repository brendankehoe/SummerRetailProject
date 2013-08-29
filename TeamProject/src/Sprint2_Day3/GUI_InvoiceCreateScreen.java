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


public class GUI_InvoiceCreateScreen {
	
	private String invoiceScreenAccess = "invoice", invoiceCreateScreenAccess = "invoiceCreate";
	private JTextField createInvoiceQuantityField;
	private JComboBox<String> createInvoiceCustomerCombo, createInvoiceProductCombo;
	private String invoiceCustomer;
	
	public void invoiceCreateScreen(){
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
        JLabel titlelbl = new JLabel("Create Invoice", JLabel.CENTER);  
        titlelbl.setFont(new Font("Arial",2 , 48));  
        topJP.add(titlelbl);
// HEADER       

        
		/*
		 *	Screen specific code goes here       
		 */
        
        // Panel to contain invoice input fields
        JPanel invoiceCreateFormPanel = new JPanel(new SpringLayout()); 
        // Labels to display invoice unique ID
        JLabel createInvoiceIDLabel = new JLabel("ID:", JLabel.TRAILING); 
        JLabel createInvoiceIDDisplayLabel = new JLabel(NewUI.db.getNoOfInvoices()+"", JLabel.TRAILING); 
        createInvoiceIDLabel.setLabelFor(createInvoiceIDDisplayLabel); 
        // Label & ComboBox to display invoice customer
        JLabel createInvoiceCustomerLabel = new JLabel("Customer:", JLabel.TRAILING); 
        NewUI.listOfCustomersVector = new Vector<String>();

        if(NewUI.invoiceBasket.size()>0){ 
        	NewUI.listOfCustomersVector.add(invoiceCustomer); 
        } 
        else{ 
            for(Customer customer : NewUI.db.getCustomers()){ 
            	NewUI.listOfCustomersVector.add(customer.getName()); 
            } 
        }
        createInvoiceCustomerCombo = new JComboBox<String>(NewUI.listOfCustomersVector);
        createInvoiceCustomerCombo.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) { 
                JComboBox cb = (JComboBox) e.getSource(); 
                NewUI.createInvoiceSupplierIndex = cb.getSelectedIndex(); 

                invoiceCustomer = NewUI.listOfCustomersVector.get(NewUI.createInvoiceSupplierIndex);
                NewUI.listOfInvoiceProductsVector.clear(); 
                for(Product product : NewUI.db.getProducts()){ 
                	NewUI.listOfInvoiceProductsVector.add(product.getName()); 
                 } 
            } 
        }); 
        createInvoiceCustomerCombo.setPreferredSize(new Dimension(40,40)); 
        createInvoiceCustomerLabel.setLabelFor(createInvoiceCustomerCombo);

        // Label & ComboBox to display invoice product
        JLabel createInvoiceProductLabel = new JLabel("Product: ", JLabel.TRAILING); 
        NewUI.listOfInvoiceProductsVector = new Vector<String>(); 
        createInvoiceProductCombo = new JComboBox<String>(NewUI.listOfInvoiceProductsVector); 
        createInvoiceProductLabel.setLabelFor(createInvoiceProductCombo); 

        JLabel createInvoiceQuantityLabel = new JLabel("Quantity:", JLabel.TRAILING); 
        createInvoiceQuantityField = new JTextField(5); 


        invoiceCreateFormPanel.add(createInvoiceIDLabel); 
        invoiceCreateFormPanel.add(createInvoiceIDDisplayLabel); 
        invoiceCreateFormPanel.add(createInvoiceCustomerLabel); 
        invoiceCreateFormPanel.add(createInvoiceCustomerCombo); 
        invoiceCreateFormPanel.add(createInvoiceProductLabel); 
        invoiceCreateFormPanel.add(createInvoiceProductCombo); 
        invoiceCreateFormPanel.add(createInvoiceQuantityLabel); 
        invoiceCreateFormPanel.add(createInvoiceQuantityField); 

        JPanel invoiceBsketPanel = new JPanel(new BorderLayout()); 
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
        for (Product product : NewUI.invoiceBasket){ 
            Vector<String> singleProduct = new Vector<String>(); 
            singleProduct.add(Integer.toString(product.getSku())); 
            singleProduct.add(product.getName()); 
            singleProduct.add(Integer.toString(product.getQuantity())); 
            dtm.addRow(singleProduct);       
        }  
        JTable invoiceBasketTable = new JTable(); 
        invoiceBasketTable.setEnabled(false); 
        invoiceBasketTable.setModel(dtm); 

        //Lay out the panel. 
        SpringUtilities.makeCompactGrid(invoiceCreateFormPanel, 
                4, 2,   //rows, columns 
                6, 6,   //initX, initY 
                6, 6);  //xPad, yPad 
        invoiceBsketPanel.add(basketLabel, BorderLayout.NORTH); 
        invoiceBsketPanel.add(new JScrollPane(invoiceBasketTable), BorderLayout.CENTER); 

        JButton invoiceCreateBackButton = new JButton("Cancel"); 
        invoiceCreateBackButton.setToolTipText("Cancels new invoice and returns to Invoice screen"); 
        invoiceCreateBackButton.setActionCommand(invoiceScreenAccess); 
        invoiceCreateBackButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUI_InvoiceScreen invoiceScreen = new GUI_InvoiceScreen();
            	invoiceScreen.invoiceScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand()); 
                NewUI.currentActiveScreen = e.getActionCommand();
                
                NewUI.invoiceBasket.clear();
            } 
        }); 
        JButton invoiceCreateAddButton = new JButton("Add Product"); 
        
        invoiceCreateAddButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent arg0) { 
          	  if (NewUI.check.isPositiveNumeric(createInvoiceQuantityField.getText()) && NewUI.check.isInvoiceProductSelected(createInvoiceProductCombo) &&
          			NewUI.check.isSufficientStock(NewUI.db, NewUI.invoiceBasket, NewUI.listOfInvoiceProductsVector.get(createInvoiceProductCombo.getSelectedIndex()), Integer.parseInt(createInvoiceQuantityField.getText()))){ 
              	  	addToBasket(NewUI.invoiceBasket, NewUI.listOfInvoiceProductsVector.get(createInvoiceProductCombo.getSelectedIndex()), Integer.parseInt(createInvoiceQuantityField.getText())); 
              	  	orderCreateScreenRefresh();
                    NewUI.createInvoiceSupplierIndex = createInvoiceCustomerCombo.getSelectedIndex(); 
                    invoiceCustomer = NewUI.listOfCustomersVector.get(NewUI.createInvoiceSupplierIndex);
                    NewUI.listOfInvoiceProductsVector.clear(); 
                    for(Product product : NewUI.db.getProducts()){ 
                    	NewUI.listOfInvoiceProductsVector.add(product.getName()); 
                     } 
                } 
            } 
        }); 
        JButton invoiceCreateSubmitButton = new JButton("Submit Invoice"); 
        invoiceCreateSubmitButton.setActionCommand(invoiceScreenAccess); 
        invoiceCreateSubmitButton.addActionListener(new ActionListener() {  
            @Override
            public void actionPerformed(ActionEvent e) { 
                if(NewUI.check.isBasketPopulated(NewUI.invoiceBasket)){ 
                    invoiceSubmit(NewUI.invoiceBasket);
                    
                    NewUI.invoiceScreen.removeAll();
	            	GUI_InvoiceScreen invoiceScreen = new GUI_InvoiceScreen();
	            	invoiceScreen.invoiceScreen();
	            	NewUI.invoiceBasket.clear();
                      
                    CardLayout cl = (CardLayout)(NewUI.gui.getLayout());  
                    cl.show(NewUI.gui, e.getActionCommand()); 
                    NewUI.currentActiveScreen=e.getActionCommand(); 
                } 
            } 
        }); 

        buttonPanel.add(invoiceCreateBackButton); 
        buttonPanel.add(invoiceCreateAddButton); 
        buttonPanel.add(invoiceCreateSubmitButton); 
          
        botJP.setLayout(new BorderLayout());  
        botJP.add(invoiceCreateFormPanel,BorderLayout.CENTER); 
        botJP.add(invoiceBsketPanel,BorderLayout.SOUTH); 
        
// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        
        NewUI.invoiceCreateScreen = screen;
        NewUI.gui.add(NewUI.invoiceCreateScreen,invoiceCreateScreenAccess);
// FOOTER 
    }


//  Adds Product/Quantity pair to the basket 
    public void addToBasket(ArrayList<Product> basket, String prodName, int qty){ 
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
	
	
//  Creates an Invoice of products in the basket 
    public void invoiceSubmit(ArrayList<Product> prodArray){ 
        ArrayList<Product> basketContents = new ArrayList<Product>( (ArrayList<Product>) prodArray.clone()); 
        for(Customer customer : NewUI.db.getCustomers()){
        	if(customer.getName().equals(invoiceCustomer)){
        		NewUI.db.createInvoice(basketContents, customer); 
        	}
        }
        // For auto-delivery of order upon submission 
        NewUI.db.outboundDelivery(basketContents); 
    } 
	
    public void orderCreateScreenRefresh(){ 
        NewUI.invoiceCreateScreen.removeAll(); 
        invoiceCreateScreen(); 
        
        CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
        cl.show(NewUI.gui, NewUI.currentActiveScreen); 
    } 
    
}
