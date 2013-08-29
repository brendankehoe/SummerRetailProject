package Sprint2_Day3;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NewUI {
	
/*
 * 	
 */
    // Sets window to size of screen    
    Toolkit tk = Toolkit.getDefaultToolkit();      
    private final int WINDOW_WIDTH = 800;//((int) tk.getScreenSize().getWidth());  
    private final int WINDOW_HEIGHT = 800;//((int) tk.getScreenSize().getHeight());
		  
    // Sets the repository of all stored objects    
    public static RetailSystem db = new RetailSystem();    
    public static Check check = new Check();
    
    // JPanel variables for screens to be used   
    public static JPanel contentPane, gui, homeScreen, productScreen, orderScreen, supplierScreen, customerScreen, invoiceScreen, 
    productCreateScreen, productEditScreen,   
    orderCreateScreen, orderEditScreen,  
    supplierCreateScreen, 
    customerCreateScreen, customerViewScreen, 
    invoiceCreateScreen, stockControlScreen, userScreen, userCreateScreen;
    public static String currentActiveScreen;
    
    
    // Vector string variables for objects in db repository  
    public static Vector<String> listOfOrdersVector, listOfOrderProductsVector, listOfInvoiceProductsVector, listOfSuppliersVector, listOfInvoicesVector, listOfCustomersVector;  
    // String ArrayList to store the Products in basket to be added to an Order/Invoices 
    public static ArrayList<Product> orderBasket = new ArrayList<Product>(), invoiceBasket = new ArrayList<Product>();
    // Integer variables used for combo box position indexes   
    public static int createOrderSupplierIndex, createInvoiceCustomerIndex, createInvoiceSupplierIndex, selectedCustomerID, selectedProductID, 
    selectedSupplierID, selecteduserID;  

    
    //Need
    public static JComboBox<String> createOrderSupplierCombo;
	public static int productsLine;
/*
 * 	
 */

    
    public NewUI(){
    	
    	JFrame mainFrame = new JFrame();
    	mainFrame.setTitle("NewUI - Retail Management System");   
    	mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);   
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
//    	mainFrame.setResizable(false); 
    
        // Create the menu bar   
        mainFrame.setJMenuBar(new MenuBarUI().getMenubar());   
    	
        // Create a panel for the JFrame content pane
        JPanel contentPane = new JPanel();
        
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


	public static void main(String[] args){
    	new NewUI();
    }

}
