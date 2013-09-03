package sprint2_Day7;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class NewUI {
	
/*
 * 	
 */
    // Sets window to size of screen    
    Toolkit tk = Toolkit.getDefaultToolkit();      
    public static int WINDOW_WIDTH = 900;//((int) tk.getScreenSize().getWidth());  
    public static int WINDOW_HEIGHT = 700;//((int) tk.getScreenSize().getHeight());
		  
    // Sets the repository of all stored objects    
    public static RetailSystem db = new RetailSystem();    
    public static Check check = new Check();
    
    // JPanel variables for screens to be used   
    public static JPanel contentPane, gui, homeScreen, productScreen, orderScreen, supplierScreen, customerScreen, invoiceScreen, 
    productCreateScreen, productEditScreen,   
    orderCreateScreen, orderViewScreen,  
    supplierCreateScreen, supplierViewScreen, 
    customerCreateScreen, customerViewScreen, 
    invoiceCreateScreen, invoiceViewScreen,
    stockControlScreen,
    userScreen, userCreateScreen;
    public static String currentActiveScreen;
    public static Date systemDate = new Date();
    
    
    // Vector string variables for objects in db repository  
    public static Vector<String> listOfOrdersVector, listOfOrderProductsVector, listOfInvoiceProductsVector, listOfSuppliersVector, listOfInvoicesVector, listOfCustomersVector;  
    // String ArrayList to store the Products in basket to be added to an Order/Invoices 
    public static ArrayList<Product> orderBasket = new ArrayList<Product>(), invoiceBasket = new ArrayList<Product>();
    // Integer variables used for combo box position indexes   
    public static int createOrderSupplierIndex, createInvoiceCustomerIndex, createInvoiceSupplierIndex, selectedCustomerID, selectedProductID, 
    selectedSupplierID, selectedUserID, selectedOrderID, selectedInvoiceID;  
    public static User currentUser;
    public static Color topBannerColor = new Color(175,210,232);
    public static Font topBannerFont= new Font("Calibri",0,42);
    
    //Need
    public static JComboBox<String> createOrderSupplierCombo;
	public static int productsLine, usersLine;
/*
 * 	
 */

    
    public NewUI(User user){

    	//Set the style of the the GUI to 'Nimbus'
    	try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      	
    	JFrame mainFrame = new JFrame();
    	//Set the user from Login 
        currentUser = user; 
    	//Set the title of the main frame
    	if (currentUser.isAdmin()){
    		mainFrame.setTitle("Retail Management System - Logged in as " +currentUser.getUserName()+" (administrator)");
    	}
    	else {
        	mainFrame.setTitle("Retail Management System - Logged in as "+ currentUser.getUserName()); 
    	}
  
    	mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);   
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
//    	mainFrame.setResizable(false); 
    
        // Create the menu bar   
        mainFrame.setJMenuBar(new MenuBarUI().getMenubar());   
    	
        // Create a panel for the JFrame content pane
        JPanel contentPane = new JPanel(new BorderLayout());
        
        // Adds panel, gui, to the content pane that will have each screen displayed on it
        gui = new JPanel(new CardLayout());

        // Intialise home screen
        GUI_HomeScreen homeScreen = new GUI_HomeScreen();
        homeScreen.homeScreen();
        
        // Add the gui panel to the content pane
        contentPane.add(gui);
        mainFrame.setContentPane(contentPane);
        
        mainFrame.setLocationRelativeTo(null); //Centres the window on screen 
        mainFrame.setVisible(true);
        

    }


//	public static void main(String[] args){
//    	new NewUI(new User());
//    }

}