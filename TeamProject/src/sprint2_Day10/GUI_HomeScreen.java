package sprint2_Day10;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
		        screen.setPreferredSize(new Dimension(300,300));
		        screen.setMaximumSize(new Dimension(300,300));
		        
				// Creates three panels used for the top portion, bottom portion and button portion of the screen
		        JPanel topJP = new JPanel();  
		        JPanel botJP =  new JPanel(new GridLayout (2,3,2,2));  

		        // Create the title of the screen in the top panel
		        JLabel titlelbl = new JLabel("Retail Management System Home", JLabel.CENTER);  
		        titlelbl.setFont(new Font("Calibri", 0, 42)); 
		        topJP.setBackground(NewUI.topBannerColor);
		        topJP.add(titlelbl);
		// HEADER       

		        
		/*
		 *	Screen specific code goes here       
		 */
		        //Setting default font for home screen icons and retrieving icons
		        Font homePageFont=new Font("Forte",0,36);
		        ImageIcon productImage = new ImageIcon("resources/rsz_product.jpg");
		        ImageIcon stockControlImage = new ImageIcon("resources/rsz_stockcontrol.jpg");
		        ImageIcon invoiceImage = new ImageIcon("resources/rsz_invoice.jpg");
		        ImageIcon supplierImage = new ImageIcon("resources/rsz_supplier.jpg");
		        ImageIcon orderImage = new ImageIcon("resources/rsz_order.jpg");
		        ImageIcon customerImage = new ImageIcon("resources/rsz_customer.jpg");
		        
		        JButton productButton = new JButton();
		        productButton.setLayout(new BoxLayout(productButton,BoxLayout.X_AXIS));
		        productButton.setBounds(0,2,productImage.getIconWidth(),productImage.getIconHeight());
		        productButton.setIcon(productImage);
		        		        
		        //Set action listener for button         
		        productButton.setActionCommand(productScreenAccess);
		        productButton.addActionListener(new ActionListener(){ 
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	GUI_ProductScreen productScreen = new GUI_ProductScreen();
		            	productScreen.productScreen(); 
		            	
		            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		                cl.show(NewUI.gui, e.getActionCommand());
		                
		                NewUI.currentActiveScreen = e.getActionCommand();
		            } 
		        }); 
		        //HTML makes the text wrap
		        JLabel productLabel = new JLabel("<html>Manage Products</html>");        
		        productLabel.setFont(homePageFont);

		        productButton.add(Box.createRigidArea(new Dimension(65,0)));
		        productButton.add(productLabel);
		      
		        //Create the button for Customer. This is set to take up its entire grid area
		        JButton customerButton = new JButton();
		        customerButton.setLayout(new BoxLayout(customerButton,BoxLayout.X_AXIS));
		        customerButton.setBounds(0,2,customerImage.getIconWidth(),customerImage.getIconHeight());
		        customerButton.setIcon(customerImage); //Set image

		        //Set action listener for button         
		        customerButton.setActionCommand(customerScreenAccess);
		        customerButton.addActionListener(new ActionListener(){ 
		        	@Override
		        	public void actionPerformed(ActionEvent e) {
		        		GUI_CustomerScreen customerScreen = new GUI_CustomerScreen();
		        		customerScreen.customerScreen(); 

		        		CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		        		cl.show(NewUI.gui, e.getActionCommand());

		        		NewUI.currentActiveScreen = e.getActionCommand();
		        	} 
		        }); 
		        //HTML makes the text wrap
		        JLabel customerLabel = new JLabel("<html>Manage Customers</html>");        
		        customerLabel.setFont(homePageFont);

		        customerButton.add(Box.createRigidArea(new Dimension(65,0)));
		        customerButton.add(customerLabel);    
		      
		        
		        //Create the button for Supplier. This is set to take up its entire grid area
		        JButton supplierButton = new JButton();
		        supplierButton.setLayout(new BoxLayout(supplierButton,BoxLayout.X_AXIS));
		        supplierButton.setBounds(0,2,supplierImage.getIconWidth(),supplierImage.getIconHeight());
		        supplierButton.setIcon(supplierImage); //Set image
		        		        
		        //Set action listener for button         
		        supplierButton.setActionCommand(supplierScreenAccess);
		        supplierButton.addActionListener(new ActionListener(){ 
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	GUI_SupplierScreen supplierScreen = new GUI_SupplierScreen();
		            	supplierScreen.supplierScreen(); 
		            	
		            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		                cl.show(NewUI.gui, e.getActionCommand());
		                
		                NewUI.currentActiveScreen = e.getActionCommand();
		            } 
		        }); 
		        //HTML makes the text wrap
		        JLabel supplierLabel = new JLabel("<html>Manage Suppliers</html>");        
		        supplierLabel.setFont(homePageFont);

		        supplierButton.add(Box.createRigidArea(new Dimension(65,0)));
		        supplierButton.add(supplierLabel); 
		        
		        //Create the button for Order. This is set to take up its entire grid area
		        JButton orderButton = new JButton();
		        orderButton.setLayout(new BoxLayout(orderButton,BoxLayout.X_AXIS));
		        orderButton.setBounds(0,2,orderImage.getIconWidth(),orderImage.getIconHeight());
		        orderButton.setIcon(orderImage); //Set image

		        //Set action listener for button         
		        orderButton.setActionCommand(orderScreenAccess);
		        orderButton.addActionListener(new ActionListener(){ 
		        	@Override
		        	public void actionPerformed(ActionEvent e) {
		        		GUI_OrderScreen orderScreen = new GUI_OrderScreen();
		        		orderScreen.orderScreen(); 

		        		CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		        		cl.show(NewUI.gui, e.getActionCommand());

		        		NewUI.currentActiveScreen = e.getActionCommand();
		        	} 
		        }); 
		        //HTML makes the text wrap
		        JLabel orderLabel = new JLabel("<html>Manage Orders</html>");        
		        orderLabel.setFont(homePageFont);

		        orderButton.add(Box.createRigidArea(new Dimension(65,0)));
		        orderButton.add(orderLabel);   
		      
		        //Create the button for Invoice. This is set to take up its entire grid area
		        JButton invoiceButton = new JButton();
		        invoiceButton.setLayout(new BoxLayout(invoiceButton,BoxLayout.X_AXIS));
		        invoiceButton.setBounds(0,2,invoiceImage.getIconWidth(),invoiceImage.getIconHeight());
		        invoiceButton.setIcon(invoiceImage); //Set image

		        //Set action listener for button         
		        invoiceButton.setActionCommand(invoiceScreenAccess);
		        invoiceButton.addActionListener(new ActionListener(){ 
		        	@Override
		        	public void actionPerformed(ActionEvent e) {
		        		GUI_InvoiceScreen invoiceScreen = new GUI_InvoiceScreen();
		        		invoiceScreen.invoiceScreen(); 

		        		CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		        		cl.show(NewUI.gui, e.getActionCommand());

		        		NewUI.currentActiveScreen = e.getActionCommand();
		        	} 
		        }); 
		        //HTML makes the text wrap
		        JLabel invoiceLabel = new JLabel("<html>Manage Invoices</html>");        
		        invoiceLabel.setFont(homePageFont);

		        invoiceButton.add(Box.createRigidArea(new Dimension(65,0)));
		        invoiceButton.add(invoiceLabel); 
		      
		        //create Panel for Stock Control   
		        
		        JButton stockControlButton = new JButton();
		        stockControlButton.setBounds(0,2,stockControlImage.getIconWidth(),stockControlImage.getIconHeight());
		        stockControlButton.setIcon(stockControlImage);
		           
		        stockControlButton.setActionCommand(stockControlScreenAccess);
		        stockControlButton.addActionListener(new ActionListener(){ 
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	GUI_StockControlScreen stockControlScreen = new GUI_StockControlScreen();
		            	stockControlScreen.stockControlScreen(); 
		            	
		            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		                cl.show(NewUI.gui, e.getActionCommand()); 
		                
		                NewUI.currentActiveScreen = e.getActionCommand();
		            } 
		        });    
		        JLabel stockControlLabel = new JLabel("Stock Control");
		        stockControlLabel.setFont(homePageFont);
		        stockControlLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		        stockControlButton.add(stockControlLabel);    
		      
		        //add panel to the main panel         
		        botJP.add(productButton);    
		        botJP.add(supplierButton); 
		        botJP.add(customerButton);    
   	        
		        botJP.add(orderButton);
		        botJP.add(invoiceButton);   
		        if (NewUI.currentUser.isAdmin()){
			        botJP.add(stockControlButton); 
		        }		        

		        
		// FOOTER 
		        // Adds the top, bottom and button panels to the screen panel
		        screen.add(topJP,BorderLayout.NORTH);  
		        screen.add(botJP,BorderLayout.CENTER);  
		        
		        // Assigns the MainUI gui panel the contents of the screen panel
		        NewUI.homeScreen = screen;
		        NewUI.gui.add(NewUI.homeScreen,homeScreenAccess);
		        NewUI.currentActiveScreen = homeScreenAccess;
		// FOOTER 

	}
	
	

private class ImagePanel extends JPanel {

  private Image img;

  public ImagePanel(String img) {
    this(new ImageIcon(img).getImage());
  }

  public ImagePanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }

  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
    
  }

}
}
