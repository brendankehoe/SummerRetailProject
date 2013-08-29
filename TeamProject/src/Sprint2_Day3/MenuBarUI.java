package Sprint2_Day3;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;


public class MenuBarUI {
	
	private JMenuBar menubar;
	private String homeScreenAccess = "home", userScreenAccess = "user",userCreateScreenAccess = "userCreate";
	
	public MenuBarUI(){
		menubar = new JMenuBar();   
		
        //Create Menus  
		JMenu filemenu = new JMenu("File");  
		JMenu productsmenu = new JMenu("Products");   
//		productsmenu.add(new JSeparator());   
		JMenu ordersmenu = new JMenu("Orders");   
//		ordersmenu.add(new JSeparator());   
		JMenu custumersmenu = new JMenu("Customers");   
//		custumersmenu.add(new JSeparator());   
		JMenu suppliersmenu = new JMenu("Suppliers");   
//        suppliersmenu.add(new JSeparator());   
        JMenu invoicesmenu = new JMenu("Invoices");   
        JMenu stockControlmenu = new JMenu("Stock Control");  
        JMenu usersMenu = new JMenu("Users") ;
            
        //create Menu Items and add Listeners for File Menu  
        JMenuItem homeView = new JMenuItem("Home");
//        homeView.setActionCommand(homeScreenAccess);  
//        homeView.addActionListener(new GUIListener());
            
        JMenuItem eExit = new JMenuItem("Exit");  
        eExit.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent event) {  
                System.exit(0);  
            }  
        });  
            
        //create Menu Items and add Listeners for Products Menu  
        JMenuItem productsView = new JMenuItem("Manage Products");  
//        productsView.setActionCommand(productScreenAccess);   
//        productsView.addActionListener(new GUIListener());   
//          productsView.addActionListener(new ActionListener() {  
//              public void actionPerformed(ActionEvent event) {  
//                ProductMenu.productScreen(); 
//              }  
//          });
        //create Menu Items and add Listeners for Orders Menu  
        JMenuItem ordersView = new JMenuItem("Manage Orders");  
//        ordersView.setActionCommand(orderScreenAccess);  
//        ordersView.addActionListener(new GUIListener());  
        //create Menu Items and add Listeners for Customers Menu  
        JMenuItem customerView = new JMenuItem("Manage Cusomters");   
//        customerView.setActionCommand(customerScreenAccess);  
//        customerView.addActionListener(new GUIListener());  
            
        //create Menu Items and add Listeners for Suppliers Menu  
        JMenuItem supplierView = new JMenuItem("Manage Suppliers");   
//        supplierView.setActionCommand(supplierScreenAccess);  
//        supplierView.addActionListener(new GUIListener());  
            
        //create Menu Items and add Listeners for Invoices Menu  
        JMenuItem invoicesView = new JMenuItem("Manage Invoices");  
//        invoicesView.setActionCommand(invoiceScreenAccess);  
//        invoicesView.addActionListener(new GUIListener());  
            
        //create Menu Items and add Listeners for Stock Control Menu  
        JMenuItem reportsView = new JMenuItem("View Reports");  
   
        JMenuItem addRandomDatabase = new JMenuItem("Add Random Database");  
        addRandomDatabase.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent event) {  
              NewUI.db.createRandomDatabase(); 
            }  
        });
        
        //create menu items for users menu
        JMenuItem userView = new JMenuItem("Manage Users");
        userView.setActionCommand(userScreenAccess);
        userView.addActionListener(new ActionListener(){ 
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	GUI_UserScreen userScreen = new GUI_UserScreen();
		            	userScreen.userScreen(); 
		            	
		            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
		                cl.show(NewUI.gui, e.getActionCommand()); 
		                
		                NewUI.currentActiveScreen = e.getActionCommand();
		            } 
		        }); 
        
        JMenuItem userCreate = new JMenuItem("Create User");
        userCreate.setActionCommand(userCreateScreenAccess);
        userCreate.addActionListener(new ActionListener(){ 
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.out.println(2);
        		GUI_UserCreateScreen userCreateScreen = new GUI_UserCreateScreen();
        		userCreateScreen.userCreateScreen("User Create Screen"); 

        		CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
        		cl.show(NewUI.gui, e.getActionCommand()); 

        		NewUI.currentActiveScreen = e.getActionCommand();
        	} 
        });
            
        //Add menu Items to Menus  
        filemenu.add(homeView);  
        filemenu.add(new JSeparator());  
        filemenu.add(eExit);  
        productsmenu.add(productsView);  
        ordersmenu.add(ordersView);   
        custumersmenu.add(customerView);   
        suppliersmenu.add(supplierView);   
        invoicesmenu.add(invoicesView);   
        stockControlmenu.add(reportsView); 
        stockControlmenu.add(addRandomDatabase); 
        usersMenu.add(userView);
        usersMenu.add(userCreate);
    
        menubar.add(filemenu);  
        menubar.add(productsmenu);   
        menubar.add(ordersmenu);   
        menubar.add(custumersmenu);   
        menubar.add(suppliersmenu);   
        menubar.add(invoicesmenu);   
        menubar.add(stockControlmenu);  
        //check if current user is an admin if so add usersmenu to menubar.
        //if()
        menubar.add(usersMenu);
	}

	public JMenuBar getMenubar() {
		return menubar;
	}

	public void setMenubar(JMenuBar menubar) {
		this.menubar = menubar;
	}

	
	
}
