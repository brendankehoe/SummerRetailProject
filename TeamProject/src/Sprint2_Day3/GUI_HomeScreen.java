package Sprint2_Day3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI_HomeScreen {
 	
	private String homeScreenAccess = "home", productScreenAccess = "product", orderScreenAccess = "order", supplierScreenAccess = "supplier", customerScreenAccess = "customer", invoiceScreenAccess = "invoice",
			stockControlScreenAccess =  "stockControl";
	
	public void homeScreen(){
		// HEADER
				// Create screen panel that is used to replace the gui panel from MainUI.class
				JPanel screen = new JPanel(new BorderLayout()); 
				screen.setLayout(new BorderLayout()); 
		        screen.setOpaque(true);  //content panes must be opaque 
		        
				// Creates three panels used for the top portion, bottom portion and button portion of the screen
		        JPanel topJP = new JPanel();  
		        topJP.setBorder(BorderFactory.createLineBorder(Color.RED));  
		        JPanel botJP =  new JPanel(new GridLayout (2,3,2,2));  
		        JPanel buttonPanel = new JPanel(new FlowLayout()); 

		        // Create the title of the screen in the top panel
		        JLabel titlelbl = new JLabel("Retail Management System Home", JLabel.CENTER);  
		        titlelbl.setFont(new Font("Arial",2 , 48));  
		        topJP.add(titlelbl);
		// HEADER       

		        
		/*
		 *	Screen specific code goes here       
		 */
		        //create Panel for Products add button and listener for products    
		        JPanel pruductsPanel = new JPanel();    
		        pruductsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));     
		        JButton productScreenButton = new JButton("Manage Products");
		        productScreenButton.setActionCommand(productScreenAccess);
		        productScreenButton.addActionListener(new ActionListener(){ 
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	GUI_ProductScreen productScreen = new GUI_ProductScreen();
		            	productScreen.productScreen(); 
		            	
		            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		                cl.show(NewUI.gui, e.getActionCommand());
		                
		                NewUI.currentActiveScreen = e.getActionCommand();
		            } 
		        });    
		        pruductsPanel.add(productScreenButton);    
		      
		        //create Panel for Customers    
		        JPanel customersPanel = new JPanel();    
		        customersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));    
		        JButton customerScreenButton = new JButton("Manage Customers");
		        customerScreenButton.setActionCommand(customerScreenAccess);
		        customerScreenButton.addActionListener(new ActionListener(){ 
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	GUI_CustomerScreen customerScreen = new GUI_CustomerScreen();
		            	customerScreen.customerScreen(); 
		            	
		            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		                cl.show(NewUI.gui, e.getActionCommand()); 
		                
		                NewUI.currentActiveScreen = e.getActionCommand();
		            } 
		        });
		        customersPanel.add(customerScreenButton);    
		      
		        //create Panel for Suppliers    
                //working of this one now 
                JPanel suppliersPanel = new JPanel();     
                suppliersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));     
                JButton supplierScreenButton = new JButton("Manage Suppliers");  
                supplierScreenButton.setActionCommand(supplierScreenAccess); 
                supplierScreenButton.addActionListener(new ActionListener(){  
                    @Override
                    public void actionPerformed(ActionEvent e) { 
                        GUI_SupplierScreen supplierScreen = new GUI_SupplierScreen(); 
                        supplierScreen.supplierScreen();  //supplier screen(add argument);  
                          
                        CardLayout cl = (CardLayout)(NewUI.gui.getLayout());        
                        cl.show(NewUI.gui, e.getActionCommand());  
                    }  
                });         
                suppliersPanel.add(supplierScreenButton);     
		        
                //create Panel for Orders    
		        JPanel ordersPanel = new JPanel();    
		        ordersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));    
		        JButton orderScreenButton = new JButton("Manage Orders"); 
		        orderScreenButton.setActionCommand(orderScreenAccess);
		        orderScreenButton.addActionListener(new ActionListener(){ 
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	GUI_OrderScreen orderScreen = new GUI_OrderScreen();
		            	orderScreen.orderScreen(); 
		            	
		            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		                cl.show(NewUI.gui, e.getActionCommand()); 
		                
		                NewUI.currentActiveScreen = e.getActionCommand();
		            } 
		        });    
		        ordersPanel.add(orderScreenButton);    
		      
		        //create Panel for Invoices    
		        JPanel invoicesPanel = new JPanel();    
		        invoicesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));    
		        JButton invoiceScreenButton = new JButton("Manage Invoices");    
		        invoiceScreenButton.setActionCommand(invoiceScreenAccess);
		        invoiceScreenButton.addActionListener(new ActionListener(){ 
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	GUI_InvoiceScreen invoiceScreen = new GUI_InvoiceScreen();
		            	invoiceScreen.invoiceScreen(); 
		            	
		            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		                cl.show(NewUI.gui, e.getActionCommand()); 
		                
		                NewUI.currentActiveScreen = e.getActionCommand();
		            } 
		        }); 
		        invoicesPanel.add(invoiceScreenButton);    
		      
		        //create Panel for Stock Control   
		        JPanel stockControlPanel = new JPanel();    
		        stockControlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));    
		        JButton stockControlScreenButton = new JButton("Stock Contol");    
		        stockControlScreenButton.setActionCommand(stockControlScreenAccess);
		        stockControlScreenButton.addActionListener(new ActionListener(){ 
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	GUI_StockControlScreen stockControlScreen = new GUI_StockControlScreen();
		            	stockControlScreen.stockControlScreen(); 
		            	
		            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		                cl.show(NewUI.gui, e.getActionCommand()); 
		                
		                NewUI.currentActiveScreen = e.getActionCommand();
		            } 
		        });    
		       stockControlPanel.add(stockControlScreenButton);      
		      
		        //add panel to the main panel         
		        botJP.add(pruductsPanel);    
		        botJP.add(suppliersPanel); 
		        botJP.add(customersPanel);    
   	        
		        botJP.add(stockControlPanel); 
		        botJP.add(ordersPanel);
		        botJP.add(invoicesPanel);   

		        

		        
		// FOOTER 
		        // Adds the top, bottom and button panels to the screen panel
		        screen.add(topJP,BorderLayout.NORTH);  
		        screen.add(botJP,BorderLayout.CENTER);  
		        screen.add(buttonPanel,BorderLayout.SOUTH);
		        
		        // Assigns the MainUI gui panel the contents of the screen panel
		        NewUI.homeScreen = screen;
		        NewUI.gui.add(NewUI.homeScreen,homeScreenAccess);
		        NewUI.currentActiveScreen = homeScreenAccess;
		// FOOTER 

	}

}
