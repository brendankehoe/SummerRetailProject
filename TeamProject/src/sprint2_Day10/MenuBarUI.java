package sprint2_Day10;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;


public class MenuBarUI {

	private JMenuBar menubar;
	private String homeScreenAccess = "home", userScreenAccess = "user",userCreateScreenAccess = "userCreate", productScreenAccess ="product" 
			,productCreateScreenAccess = "productCreate",orderScreenAccess = "order",orderCreateScreenAccess = "orderCreate", 
			customerScreenAccess = "customer", customerCreateScreenAccess = "customerCreate",
			supplierScreenAccess = "supplier", supplierCreateScreenAccess = "supplierCreate", invoiceScreenAccess = "invoice",
			invoiceCreateScreenAccess = "invoiceCreate", stockControlScreenAccess="stockControl";

	public MenuBarUI(){
		menubar = new JMenuBar();   

		//Create Menus  
		JMenu fileMenu = new JMenu("File");  
		JMenu productsMenu = new JMenu("Products");   
		JMenu ordersMenu = new JMenu("Orders");   
		JMenu customersMenu = new JMenu("Customers");     
		JMenu suppliersMenu = new JMenu("Suppliers");    
		JMenu invoicesMenu = new JMenu("Invoices");   
		JMenu stockControlMenu = new JMenu("Stock Control");  
		JMenu usersMenu = new JMenu("Users") ;

		//Create Menu Items and add Listeners for File Menu  
		//Create Home Menu and add actionListener
		JMenuItem homeView = new JMenuItem("Home");
		homeView.setActionCommand(homeScreenAccess);
		homeView.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_HomeScreen homeScreen = new GUI_HomeScreen();
				homeScreen.homeScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		}); 
		
		//Create Exit Menu and add actionLsitner
		JMenuItem eExit = new JMenuItem("Exit");  
		eExit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent event) {  
				System.exit(0);  
			}  
		});  

//		//Create Log out and add actionLsitner
//		JMenuItem logOut = new JMenuItem("Log Out"); 
//		logOut.setActionCommand(homeScreenInactiveAccess);
//		logOut.addActionListener(new ActionListener() {  
//			public void actionPerformed(ActionEvent e) {  
//				
//				
//				GUI_HomeScreen_Inactive homeScreenInactive = new GUI_HomeScreen_Inactive();
//				homeScreenInactive.homeScreenInactive(); 
//				
//				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
//				cl.show(NewUI.gui, e.getActionCommand()); 
//
//				NewUI.currentActiveScreen = e.getActionCommand();
//				SwitchUserModule loginModule = new SwitchUserModule();
//								
//				NewUI.mainFrame.setTitle("Retail Management System - Logged out");
//
//			}  
//		}); 
		
		//Create Log out and add actionLsitner
		JMenuItem logOut = new JMenuItem("Log Out"); 
		logOut.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) {  
				
				LoginModule login = new LoginModule();
				NewUI.mainFrame.setVisible(false);
				NewUI.mainFrame.dispose();

			}  
		}); 
		
		//Create Menu Items and add Listeners for Products Menu  
		//To manage products
		JMenuItem productsView = new JMenuItem("Manage Products"); 
		productsView.setActionCommand(productScreenAccess);
		productsView.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_ProductScreen productScreen = new GUI_ProductScreen();
				productScreen.productScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		}); 

		//to add a new product
		JMenuItem createProduct = new JMenuItem("Add New Product"); 
		createProduct.setActionCommand(productCreateScreenAccess);
		createProduct.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_ProductCreateScreen productCreateScreen = new GUI_ProductCreateScreen();
				productCreateScreen.productCreateScreen("Add New Product"); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		}); 


		//Create Menu Items and add Listeners for Orders Menu  
		JMenuItem ordersView = new JMenuItem("Manage Orders");  
		ordersView.setActionCommand(orderScreenAccess);
		ordersView.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_OrderScreen orderScreen = new GUI_OrderScreen();
				orderScreen.orderScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		}); 
		//to add a new order
		JMenuItem createOrder = new JMenuItem("Add New Order"); 
		createOrder.setActionCommand(orderCreateScreenAccess);
		createOrder.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_OrderCreateScreen orderCreateScreen = new GUI_OrderCreateScreen();
				orderCreateScreen.orderCreateScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		}); 
		
        //Create a forward date menu item and add action listener
        JMenuItem advanceDate = new JMenuItem("Advance Date"); 
        final Calendar calendar = Calendar.getInstance();
        advanceDate.addActionListener(new ActionListener() {  
        	public void actionPerformed(ActionEvent event) {  
                //Update date by one day
        		calendar.setTime(NewUI.systemDate);
        		calendar.add(Calendar.DATE, 1);
                NewUI.systemDate=calendar.getTime();// Advances system date by one day
                
                NewUI.db.recordDeliveries();
                
                //Set curretn screen to the order screen
				GUI_OrderScreen orderScreen = new GUI_OrderScreen();
				orderScreen.orderScreen();
				
                CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, orderScreenAccess); 
                
        	}  
        });

		//create Menu Items and add Listeners for Customers Menu  
		JMenuItem customerView = new JMenuItem("Manage Cusomters");   
		customerView.setActionCommand(customerScreenAccess);
		customerView.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_CustomerScreen customerScreen = new GUI_CustomerScreen();
				customerScreen.customerScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		}); 

		//to add a new customer
		JMenuItem createCustomer = new JMenuItem("Add New Customer"); 
		createCustomer.setActionCommand(customerCreateScreenAccess);
		createCustomer.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_CustomerCreateScreen customerCreateScreen = new GUI_CustomerCreateScreen();
				customerCreateScreen.customerCreateScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		}); 

		//create Menu Items and add Listeners for Suppliers Menu  
		JMenuItem supplierView = new JMenuItem("Manage Suppliers");   
		supplierView.setActionCommand(supplierScreenAccess);
		supplierView.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_SupplierScreen supplierScreen = new GUI_SupplierScreen();
				supplierScreen.supplierScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		});   

		//to add a new supplier
		JMenuItem createSupplier= new JMenuItem("Add New Supplier"); 
		createSupplier.setActionCommand(supplierCreateScreenAccess);
		createSupplier.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_SupplierCreateScreen supplierCreateScreen = new GUI_SupplierCreateScreen();
				supplierCreateScreen.supplierCreateScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		}); 

		//create Menu Items and add Listeners for Invoices Menu  
		JMenuItem invoicesView = new JMenuItem("Manage Invoices");  
		invoicesView.setActionCommand(invoiceScreenAccess);
		invoicesView.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_InvoiceScreen invoiceScreen = new GUI_InvoiceScreen();
				invoiceScreen.invoiceScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		});  
		//to create a new invoice
		JMenuItem createsInvoice= new JMenuItem("Create New Invoice"); 
		createsInvoice.setActionCommand(invoiceCreateScreenAccess);
		createsInvoice.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_InvoiceCreateScreen invoiceCreateScreen = new GUI_InvoiceCreateScreen();
				invoiceCreateScreen.invoiceCreateScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		}); 

		//create Menu Items and add Listeners for Stock Control Menu 

		JMenuItem reportsView = new JMenuItem("View Reports");  
		reportsView.setActionCommand(stockControlScreenAccess);
		reportsView.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI_StockControlScreen stockControlScreen= new GUI_StockControlScreen();
				stockControlScreen.stockControlScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		});  


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

		JMenuItem userCreate = new JMenuItem("Create New User");
		userCreate.setActionCommand(userCreateScreenAccess);
		userCreate.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) {

				GUI_UserCreateScreen userCreateScreen = new GUI_UserCreateScreen();
				userCreateScreen.userCreateScreen("User Create Screen"); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand();
			} 
		});


		//Add menu Items to Menus  
		fileMenu.add(homeView);    
		fileMenu.add(new JSeparator());  
		fileMenu.add(logOut);
		fileMenu.add(new JSeparator());
		fileMenu.add(eExit);  
		productsMenu.add(productsView);
		productsMenu.add(new JSeparator());
		productsMenu.add(createProduct);
		ordersMenu.add(ordersView); 
		ordersMenu.add(advanceDate); 

		//If is admin add create order 
		if(LoginModule.currentUser.isAdmin()==true){
			ordersMenu.add(new JSeparator());
			ordersMenu.add(createOrder);
			ordersMenu.add(new JSeparator());
			ordersMenu.add(advanceDate); 
		} 

		customersMenu.add(customerView);
		customersMenu.add(new JSeparator());
		customersMenu.add(createCustomer);
		suppliersMenu.add(supplierView);  
		suppliersMenu.add(new JSeparator());
		suppliersMenu.add(createSupplier);
		invoicesMenu.add(invoicesView);  
		//If is admin add create invoice 
		if(LoginModule.currentUser.isAdmin()==true){
			invoicesMenu.add(new JSeparator());
			invoicesMenu.add(createsInvoice);
		}

		stockControlMenu.add(reportsView); 
		stockControlMenu.add(new JSeparator());
		stockControlMenu.add(addRandomDatabase); 
		usersMenu.add(userView);
		usersMenu.add(new JSeparator());
		usersMenu.add(userCreate);

		menubar.add(fileMenu);  
		menubar.add(productsMenu);   
		menubar.add(ordersMenu);   
		menubar.add(customersMenu);   
		menubar.add(suppliersMenu);   
		menubar.add(invoicesMenu);   

		//Check if current user is an administrator, if so add Users Menu & Decide what functions need to be here for Admin Only
		//Just giving Admin access to Users and Stock Control for now
		if(LoginModule.currentUser.isAdmin()==true){

			menubar.add(stockControlMenu); 
			menubar.add(usersMenu);
		}

	}

	public JMenuBar getMenubar() {
		return menubar;
	}

	public void setMenubar(JMenuBar menubar) {
		this.menubar = menubar;
	}



}
