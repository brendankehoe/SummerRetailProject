package Sprint2_Day3;

import java.awt.BorderLayout;   
import java.awt.CardLayout;   
import java.awt.Color;   
import java.awt.Dimension;   
import java.awt.FlowLayout;  
import java.awt.Font;   
import java.awt.GridLayout;   
import java.awt.Toolkit;   
import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;   
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList;   
import java.util.Calendar;
import java.util.Random;
//import java.util.Calendar; 
import java.util.Date; 
import java.util.Vector;  




import javax.swing.BorderFactory;   
import javax.swing.Box;
import javax.swing.BoxLayout; 
import javax.swing.JButton;   
import javax.swing.JComboBox;   
import javax.swing.JFrame;   
import javax.swing.JLabel;   
import javax.swing.JMenu;   
import javax.swing.JMenuBar;   
import javax.swing.JMenuItem;   
import javax.swing.JPanel;   
import javax.swing.JScrollPane;   
import javax.swing.JSeparator;   
import javax.swing.JTable;   
import javax.swing.JTextField;   
import javax.swing.SpringLayout;   
import javax.swing.border.TitledBorder; 
import javax.swing.table.AbstractTableModel; 
import javax.swing.table.DefaultTableModel;  
import javax.swing.table.TableModel; 

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class MainUI extends JFrame {   

	// Sets the repository of all stored objects    
	private RetailSystem db = new RetailSystem();    
	private Check check = new Check();   

	// Sets window to size of screen    
	Toolkit tk = Toolkit.getDefaultToolkit();      
	private final int WINDOW_WIDTH = ((int) tk.getScreenSize().getWidth());    
	private final int WINDOW_HEIGHT = ((int) tk.getScreenSize().getHeight());   
	// JMenu variable for menu bar on screen   
	private JMenu filemenu, productsmenu, ordersmenu, custumersmenu, suppliersmenu, invoicesmenu, stockControlmenu;   
	// JMenuItem variable for menu bar on screen   
	private JMenuItem homeView, eExit, productsView, ordersView, customerView, supplierView, invoicesView, reportsView, addRandomDatabase;   
	// Stings identify the JPanels screens in the CardLayout   
	private final String homeScreenAccess = "home", productScreenAccess = "product", orderScreenAccess = "order", supplierScreenAccess = "supplierEdit", customerScreenAccess = "customer", invoiceScreenAccess = "invoice", 
			productCreateScreenAccess = "productCreate", productEditScreenAccess = "productEdit",   
			orderCreateScreenAccess = "orderCreate", orderEditScreenAccess = "orderEdit",   
			supplierCreateScreenAccess = "supplierCreate", 
			customerCreateScreenAccess = "customerCreate", customerViewScreenAccess = "customerView", 
			invoiceCreateScreenAccess = "invoiceCreate", invoiceEditScreenAccess = "invoiceEdit", stockControlScreenAccess = "stockControl";            
	private String currentActiveScreen;
	//
	private String invoiceCustomer;
	// JPanel variables for screens to be used   
	private JPanel contentPane, gui, homeScreen, productScreen, orderScreen, supplierScreen, customerScreen, invoiceScreen, 
	productCreateScreen, productEditScreen,   
	orderCreateScreen, orderEditScreen,  
	supplierCreateScreen, 
	customerCreateScreen, customerViewScreen, 
	invoiceCreateScreen, stockControlScreen;
	// JTextField variables to be used on screens   
	private JTextField createOrderQuantityField, createInvoiceQuantityField;  
	// JComboBox variables to be used on screens  
	private JComboBox<String> createOrderSupplierCombo, createOrderProductCombo, createInvoiceCustomerCombo, createInvoiceProductCombo;
	// JButton variables to be used on screen 
	JButton orderCreateSubmitButton, invoiceCreateSubmitButton; 
	// Vector string variables for objects in db repository  
	Vector<String> listOfOrdersVector, listOfOrderProductsVector, listOfInvoiceProductsVector, listOfSuppliersVector, listOfInvoicesVector, listOfCustomersVector;  
	// Integer variables used for combo box position indexes   
	int createOrderSupplierIndex, createInvoiceCustomerIndex, createInvoiceSupplierIndex, selectedCustomerID;  
	//     
	JMenuBar menubar; 
	// String ArrayList to store the Products in basket to be added to an Order/Invoices 
	private ArrayList<Product> orderBasket = new ArrayList<Product>(), invoiceBasket = new ArrayList<Product>();
	private int productsLine;
	private DefaultTableModel productsTableModel; 
	JTable productsTable;




	// Constructor to configure JFrame and set parameters   
	public MainUI(){   

		setTitle("Retail Management System");   
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);   
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		setResizable(false); 

		//create the menu bar   
		menuBar();   
		setJMenuBar(menubar);   

		// Creates each screen to be added to GUI   
		homeScreen();   
		productScreen();   
		productCreateScreen();   
		productEditScreen();   
		orderScreen();   
		orderCreateScreen();   
		orderEditScreen();   
		supplierScreen(); 
		supplierCreateScreen(); 
		customerScreen(); 
		customerCreateScreen(); 
		customerViewScreen(); 
		invoiceScreen();
		invoiceCreateScreen();
		stockControlScreen();

		// Adds each screen to the GUI   
		gui = new JPanel(new CardLayout());  
		gui.add(homeScreen,homeScreenAccess);   
		gui.add(productScreen,productScreenAccess);   
		gui.add(productCreateScreen,productCreateScreenAccess);    
		gui.add(productEditScreen,productEditScreenAccess);   
		gui.add(orderScreen,orderScreenAccess);   
		gui.add(orderCreateScreen,orderCreateScreenAccess);   
		gui.add(orderEditScreen,orderEditScreenAccess);   
		gui.add(supplierScreen,supplierScreenAccess);   
		gui.add(supplierCreateScreen,supplierCreateScreenAccess);    
		gui.add(customerScreen,customerScreenAccess);  
		gui.add(customerCreateScreen,customerCreateScreenAccess);  
		gui.add(customerViewScreen,customerViewScreenAccess);  
		gui.add(invoiceScreen,invoiceScreenAccess);
		gui.add(invoiceCreateScreen,invoiceCreateScreenAccess);
		gui.add(stockControlScreen,stockControlScreenAccess);


		contentPane = new JPanel();   
		contentPane.add(gui);   
		setContentPane(contentPane); 
		pack();   
		setLocationRelativeTo(null); //Centre the window on screen 
		setVisible(true);   

	}   

	public void menuBar(){   
		menubar = new JMenuBar();   
		//Create Menus  
		filemenu = new JMenu("File");  
		productsmenu = new JMenu("Products");   
		productsmenu.add(new JSeparator());   
		ordersmenu = new JMenu("Orders");   
		ordersmenu.add(new JSeparator());   
		custumersmenu = new JMenu("Customers");   
		custumersmenu.add(new JSeparator());   
		suppliersmenu = new JMenu("Suppliers");   
		suppliersmenu.add(new JSeparator());   
		invoicesmenu = new JMenu("Invoices");   
		stockControlmenu = new JMenu("Stock Control");  

		//create Menu Items and add Listeners for File Menu  
		homeView = new JMenuItem("Home");  
		homeView.setActionCommand(homeScreenAccess);  
		homeView.addActionListener(new GUIListener());

		eExit = new JMenuItem("Exit");  
		eExit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent event) {  
				System.exit(0);  
			}  
		});  

		//create Menu Items and add Listeners for Products Menu  
		productsView = new JMenuItem("Manage Products");  
		productsView.setActionCommand(productScreenAccess);   
		productsView.addActionListener(new GUIListener());   

		//create Menu Items and add Listeners for Orders Menu  
		ordersView = new JMenuItem("Manage Orders");  
		ordersView.setActionCommand(orderScreenAccess);  
		ordersView.addActionListener(new GUIListener());  
		//create Menu Items and add Listeners for Customers Menu  
		customerView = new JMenuItem("Manage Cusomters");   
		customerView.setActionCommand(customerScreenAccess);  
		customerView.addActionListener(new GUIListener());  

		//create Menu Items and add Listeners for Suppliers Menu  
		supplierView = new JMenuItem("Manage Suppliers");   
		supplierView.setActionCommand(supplierScreenAccess);  
		supplierView.addActionListener(new GUIListener());  

		//create Menu Items and add Listeners for Invoices Menu  
		invoicesView = new JMenuItem("Manage Invoices");  
		invoicesView.setActionCommand(invoiceScreenAccess);  
		invoicesView.addActionListener(new GUIListener());  

		//create Menu Items and add Listeners for Stock Control Menu  
		reportsView = new JMenuItem("View Reports");  

		addRandomDatabase = new JMenuItem("Add Random Database");  
		addRandomDatabase.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent event) {  
				db.createRandomDatabase(); 
				refresh();
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

		menubar.add(filemenu);  
		menubar.add(productsmenu);   
		menubar.add(ordersmenu);   
		menubar.add(custumersmenu);   
		menubar.add(suppliersmenu);   
		menubar.add(invoicesmenu);   
		menubar.add(stockControlmenu);   
	}    

	/**************************************************************************************    
	 * 	Retail Solution Screens
	 **************************************************************************************/

	// Home Screen - Product, Order    
	public void homeScreen(){   
		homeScreen = new JPanel();    
		homeScreen.setLayout(new BorderLayout());    

		JPanel topJP = new JPanel();    
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));    
		JPanel botJP =  new JPanel();    
		botJP.setLayout(new GridLayout (2,3,2,2));    

		homeScreen.setOpaque(true);    
		JLabel titlelbl = new JLabel("Retail Management System Home", JLabel.CENTER);    
		titlelbl.setFont(new Font("Arial",2 , 48));    
		topJP.add(titlelbl);    

		//create Panel for Products add button and listener for products    
		JPanel pruductsPanel = new JPanel();    
		pruductsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));     
		JButton productScreenButton = new JButton("Manage Products");    
		pruductsPanel.add(productScreenButton);    
		productScreenButton.setActionCommand(productScreenAccess);    
		productScreenButton.addActionListener(new GUIListener());    
		pruductsPanel.add(productScreenButton);    

		//create Panel for Orders    
		JPanel ordersPanel = new JPanel();    
		ordersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));    
		JButton orderScreenButton = new JButton("Manage Orders");    
		orderScreenButton.setActionCommand(orderScreenAccess);    
		orderScreenButton.addActionListener(new GUIListener());    
		ordersPanel.add(orderScreenButton);    

		//create Panel for Customers    
		JPanel customersPanel = new JPanel();    
		customersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));    
		JButton customerScreenButton = new JButton("Manage Customers");    
		customerScreenButton.setActionCommand(customerScreenAccess);    
		customerScreenButton.addActionListener(new GUIListener());    
		customersPanel.add(customerScreenButton);    

		//create Panel for Suppliers    
		JPanel suppliersPanel = new JPanel();    
		suppliersPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));    
		JButton supplierScreenButton = new JButton("Manage Suppliers");    
		supplierScreenButton.setActionCommand(supplierScreenAccess);    
		supplierScreenButton.addActionListener(new GUIListener());    
		suppliersPanel.add(supplierScreenButton);    

		//create Panel for Invoices    
		JPanel invoicesPanel = new JPanel();    
		invoicesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));    
		JButton invoiceScreenButton = new JButton("Manage Invoices");    
		invoiceScreenButton.setActionCommand(invoiceScreenAccess);    
		invoiceScreenButton.addActionListener(new GUIListener());    
		invoicesPanel.add(invoiceScreenButton);    

		//create Panel for Stock Control   
		JPanel stockControlPanel = new JPanel();    
		stockControlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));    
		JButton stockControlScreenButton = new JButton("Stock Contol");    
		stockControlScreenButton.setActionCommand(stockControlScreenAccess);    
		stockControlScreenButton.addActionListener(new GUIListener());    
		stockControlPanel.add(stockControlScreenButton);    

		//add panel to the main panel         
		botJP.add(pruductsPanel);    
		botJP.add(ordersPanel);    
		botJP.add(customersPanel);    
		botJP.add(suppliersPanel);    
		botJP.add(invoicesPanel);   
		botJP.add(stockControlPanel);    

		homeScreen.add(topJP,BorderLayout.NORTH);    
		homeScreen.add(botJP,BorderLayout.CENTER);    
	}

	JPanel stockControlBotJP = new JPanel(new BorderLayout());
	JComboBox<String> productSelectionComboBox = new JComboBox<String>();
	String selctedProductText = "All";
	JComboBox<String> orderInvoiceSelectionComboBox = new JComboBox<String>();
	String orderInvoiceSelection = "Sales";
	JComboBox<String> stockRangeComboBox = new JComboBox<String>();
	int chartDays=92;
	DefaultTableModel dataTableModel  = new DefaultTableModel();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void stockControlScreen(){

		stockControlScreen = new JPanel();
		BorderLayout myLayout = new BorderLayout();
		stockControlScreen.setLayout(new BorderLayout()); 

		JPanel topJP = new JPanel();
		JPanel buttonJP = new JPanel();

		setupComboBoxes();

		topJP.add(orderInvoiceSelectionComboBox); 
		topJP.add(productSelectionComboBox);  	
		topJP.add(stockRangeComboBox);  

		//Create the view graph button
		JButton viewChartButton = new JButton("View Graph");
		viewChartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				makeTableModel();
				JTable dataTable = new JTable();
				dataTable.setModel(dataTableModel);

				stockControlBotJP.removeAll();
				stockControlBotJP.add(new JScrollPane(dataTable), BorderLayout.CENTER);

				makeChart();
			}  
		});
		topJP.add(viewChartButton);

		//Make back button and set action listener  
		JButton backButton = new JButton("Back");    
		backButton.setActionCommand(homeScreenAccess);    
		backButton.addActionListener(new GUIListener());    
		buttonJP.add(backButton); 

		stockControlScreen.add(topJP,BorderLayout.NORTH);
		stockControlScreen.add(stockControlBotJP,BorderLayout.CENTER);
		stockControlScreen.add(buttonJP,BorderLayout.SOUTH);
		


	}

	public void setupComboBoxes(){

		/*
		 ***************Combo box for selecting type of data to display (order/invoice/stock)************
		 */
		orderInvoiceSelectionComboBox = new JComboBox<String>();
		orderInvoiceSelectionComboBox.addItem("Sales");
		orderInvoiceSelectionComboBox.addItem("Orders");
		orderInvoiceSelectionComboBox.addItem("Stock Level");
		orderInvoiceSelectionComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {   			
				orderInvoiceSelection=orderInvoiceSelectionComboBox.getSelectedItem().toString();
			}  
		});
		if (orderInvoiceSelection.equals("Sales")){
			orderInvoiceSelectionComboBox.setSelectedIndex(0);          		
		}
		else if (orderInvoiceSelection.equals("Orders")){
			orderInvoiceSelectionComboBox.setSelectedIndex(1);          		
		}
		else if (orderInvoiceSelection.equals("Stock Level")){
			orderInvoiceSelectionComboBox.setSelectedIndex(2);          		
		}

		/*
		 ***************Combo box for selecting product************
		 */
		productSelectionComboBox = new JComboBox<String>();
		productSelectionComboBox.addItem("All");
		for (Product p : db.getProducts()){
			productSelectionComboBox.addItem(p.getSku() + " - " + p.getName());
		}
		productSelectionComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {   			
				selctedProductText=productSelectionComboBox.getSelectedItem().toString();
			}
		});
		productSelectionComboBox.setSelectedItem(selctedProductText);

		/*
		 ***************Combo box for selecting time range************
		 */
		stockRangeComboBox=new JComboBox<String>();
		stockRangeComboBox.addItem("One month");
		stockRangeComboBox.addItem("Three months");
		stockRangeComboBox.addItem("One year");
		stockRangeComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (stockRangeComboBox.getSelectedItem().toString().equals("One month")){
					chartDays=31;          		
				}
				else if (stockRangeComboBox.getSelectedItem().toString().equals("Three months")){
					chartDays=92; 
				}
				else if (stockRangeComboBox.getSelectedItem().toString().equals("One year")){
					chartDays=365; 
				}
			}  
		});
		//Must set combobox selected item correctly at every refresh
		if (chartDays==31){
			stockRangeComboBox.setSelectedIndex(0);          		
		}
		else if (chartDays==92){
			stockRangeComboBox.setSelectedIndex(1);          		
		}
		else if (chartDays==365){
			stockRangeComboBox.setSelectedIndex(2);          		
		}   


	}

	public void makeChart(){
		 
		String chartTitle="";
		
		//Build a string buffer - detects if the selection contains numbers
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<selctedProductText.length(); i++)
		{
			if(Character.isDigit(selctedProductText.charAt(i)))
				sb.append(selctedProductText.charAt(i));
		}
		
		if (sb.length()!=0){ //then a product must be selected
			//Find the product corresponding to the selected ID in the item
			Product product = db.getProducts().get(Integer.parseInt(sb.toString()));
			chartTitle=product.getName()+ " - Weekly Sales";
		}
		else if (productSelectionComboBox.getSelectedIndex()==0){
			chartTitle="Total Weekly Sales";
		}
		
		JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle, "Time", "€", dataFromTable(),false,false,false);
		XYPlot xyplot = (XYPlot)chart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(700, 300));
		stockControlBotJP.add(chartPanel,BorderLayout.NORTH);
		refresh();
	}

	public void makeTableModel(){

		//Build a string buffer - detects if the selection contains numbers
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<selctedProductText.length(); i++)
		{
			if(Character.isDigit(selctedProductText.charAt(i)))
				sb.append(selctedProductText.charAt(i));
		}

		//Initiate calender (necessary for incrementing date)
		Date date = new Date(); 
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.DATE, -chartDays); //Set the date to 60 days ago
		date=c1.getTime();

		//Initiate 2nd date - one to compare the loop date to 
		Calendar c2 = Calendar.getInstance();
		double totalSales=0; //cumulative sales for certain date

		//Set up table model with columns
		dataTableModel = new DefaultTableModel() { 
			public boolean isCellEditable(int row, int column) { 
				return false;// This causes all cells to be not editable 
			} 
		};  	
		dataTableModel.addColumn("Date");
		dataTableModel.addColumn("Weekly Sales (€)");
		Vector<String> singleDate = new Vector<String>();

		if (orderInvoiceSelection.equals("Sales")){
			for (int j=0;j<chartDays;j=j+7){           	

				//Update date by seven days
				c1.setTime(date);
				c1.add(Calendar.DATE, 7);
				date=c1.getTime();

				totalSales=0; //reset totalSales

				//Loop through the invoices. Add the total values of the invoices with the same date as the date in loop
				for (Invoice i : db.getInvoices()){
					c2.setTime(i.getDate());
					if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
							c1.get(Calendar.WEEK_OF_YEAR) == c2.get(Calendar.WEEK_OF_YEAR)){

						//If sb has a length (is a number) then the selection must be a product
						if (sb.length()!=0){
							//Find the product corresponding to the selected ID in the item
							Product product = db.getProducts().get(Integer.parseInt(sb.toString()));
							//Loop through products. Only add the total sales if the product is the same as the selected product
							for (Product p : i.getProducts()){    					
								if (p.getSku()==product.getSku()){
									totalSales=totalSales+i.calculateTotalRetailValue();
								}
							}
						}

						//Else it must be another, non product, selection 
						else if (productSelectionComboBox.getSelectedIndex()==0){
							totalSales=totalSales+i.calculateTotalRetailValue();
						}					
					}
				}
				singleDate.add(sdf.format(date));
				singleDate.add(Double.toString(totalSales));
				dataTableModel.addRow(singleDate);
				singleDate = new Vector<String>();
			}


		}
		else if (orderInvoiceSelection.equals("Orders")){
			//Code
		}
		else if (orderInvoiceSelection.equals("Stock Level")){
			//Code
		}
	}

	

	public XYDataset dataFromTable(){

		TimeSeries series1 = new TimeSeries("Sales");
		TimeSeriesCollection dataset = new TimeSeriesCollection();

		try {
			for (int i=0; i<dataTableModel.getRowCount()-1;i++){
				RegularTimePeriod xValue;

				xValue = new Day(sdf.parse(dataTableModel.getValueAt(i, 0).toString()));

				double yValue = Double.parseDouble(dataTableModel.getValueAt(i, 1).toString());

				series1.add(xValue ,yValue);
				System.out.println(yValue);
			}
		} catch (ParseException e) {
		}
		dataset.addSeries(series1);

		return dataset;
	} 

	// Product Screen - Create Product, Edit Product, Back    
	public void productScreen(){    

		//Creating panels and layouts  
		productScreen = new JPanel();    
		productScreen.setLayout(new BorderLayout());    
		JPanel topJP = new JPanel();    
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));    
		JPanel botJP =  new JPanel();    
		botJP.setBorder(BorderFactory.createLineBorder(Color.blue));   
		botJP.setLayout(new BorderLayout());    
		JPanel buttonPanel = new JPanel(); 

		// create JTable 
		productsTableModel = new DefaultTableModel() { 
			public boolean isCellEditable(int row, int column) { 
				return false;// This causes all cells to be not editable 
			} 
		}; 

		//create JTable - DefaultTableModel easy way to populate JTable    
		productsTableModel.addColumn("Product SKU");   
		productsTableModel.addColumn("Product Name");   
		productsTableModel.addColumn("In Stock");   
		productsTableModel.addColumn("Supplier");   

		//Populating the products table - using vectors because they are compatible with tables  
		for (Product p : db.getProducts()){   
			Vector<String> singleOrder = new Vector<String>();   
			singleOrder.add(Integer.toString(p.getSku()));  
			singleOrder.add(p.getName());   
			singleOrder.add(Integer.toString(p.getQuantity()));   
			singleOrder.add(p.getSupplier().getName());   
			productsTableModel.addRow(singleOrder);       
		}    
		JTable productsTable = new JTable(); 
		productsTable.setModel(productsTableModel);        

		//Make Create button and set action listener  
		JButton backButton = new JButton("Back");    
		backButton.setActionCommand(homeScreenAccess);    
		backButton.addActionListener(new GUIListener());    
		buttonPanel.add(backButton);    


		//Make Edit button and set action listener  
		JButton productEditButton = new JButton("Edit Product");    
		productEditButton.setActionCommand(productEditScreenAccess);    
		productEditButton.addActionListener(new GUIListener());    
		buttonPanel.add(productEditButton);    

		//Make back button and set action listener  
		JButton productCreateButton = new JButton("Add New Product");    
		productCreateButton.setActionCommand(productCreateScreenAccess);    
		productCreateButton.addActionListener(new GUIListener());    
		buttonPanel.add(productCreateButton)  ; 

		//Set botJP content  
		botJP.add(new JLabel("All Products"), BorderLayout.NORTH);    
		botJP.add(new JScrollPane(productsTable),BorderLayout.CENTER);    

		//Set top banner  
		productScreen.setOpaque(true);    
		JLabel titlelbl = new JLabel("Manage Products", JLabel.CENTER);    
		titlelbl.setFont(new Font("Arial",2 , 48));    
		topJP.add(titlelbl);    

		//Set main productScreen panel content  
		productScreen.add(topJP,BorderLayout.NORTH);    
		productScreen.add(botJP,BorderLayout.CENTER);   
		productScreen.add(buttonPanel,BorderLayout.SOUTH);

		Vector<Vector<String>> wholeTable = productsTableModel.getDataVector(); 
		Vector<String> tableLine = wholeTable.get(0); 

	}    


	// Create Product Screen - Cancel  
	public void productCreateScreen(){  

		productCreateScreen = new JPanel();  
		productCreateScreen.setLayout(new BorderLayout());  

		JPanel topJP = new JPanel();   
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));   
		JPanel botJP =  new JPanel();   

		JPanel createProductForm = new JPanel();  
		createProductForm.setLayout(new GridLayout(10,1,3,3)); 

		TitledBorder nameBorder = BorderFactory.createTitledBorder("Product Details"); 
		createProductForm.setBorder(nameBorder); 

		//create JPanel row1 and add components 
		//row1 to hold sku label and textfield value 
		JPanel row1 = new JPanel();
		JLabel createProductIDLabel = new JLabel("SKU:", JLabel.TRAILING); 
		row1.add(createProductIDLabel);
		JLabel createOrderIDDisplayLabel = new JLabel(db.getNoOfProducts()+"", JLabel.TRAILING); 
		createProductIDLabel.setLabelFor(createOrderIDDisplayLabel); 
		row1.add(createOrderIDDisplayLabel);


		//create JPanel row2 and add components 
		//row2 to hold supplier label and combobox  
		JPanel row2 = new JPanel();
		JLabel productCreateSupplierLabel = new JLabel("Supplier: "); 
		row1.add(productCreateSupplierLabel);
		//Populate supplier combo box and add to row1
		final JComboBox productCreateSupplierCombo = new JComboBox(); 
		for (Supplier s : db.getSuppliers()){ 
			productCreateSupplierCombo.addItem(s.getName()); 
		} 

		row2.add(productCreateSupplierLabel);
		row2.add(productCreateSupplierCombo);

		//create JPanel row3 and add components 
		//row3 to hold productName label and productTextField
		JPanel row3 = new JPanel();
		JLabel productCreateNameLabel = new JLabel("Product Name: "); 
		//    row3.add(productCreateNameLabel);
		final JTextField productCreateNameText = new JTextField(10); 
		//    row3.add(productCreateNameText);

		//create JPanel row4 and add components 
		//row4 to hold wholesalePrice label and wholeSaleTextField & retailprice label and textFields
		JPanel row4 = new JPanel();
		row4.setLayout(new FlowLayout(FlowLayout.CENTER));
		row2.add(productCreateNameLabel);
		row2.add(productCreateNameText);
		JLabel productCreateWholesaleLabel = new JLabel("Wholesale Price:  €");  
		row4.add(productCreateWholesaleLabel);
		final JTextField productCreateWholesaleText = new JTextField(5); 
		row4.add(productCreateWholesaleText);
		JLabel productCreateRetailLabel = new JLabel("       Retail Price:  €");  

		final JTextField productCreateRetailText = new JTextField(5); 


		JPanel row5 = new JPanel();
		row5.setLayout(new FlowLayout(FlowLayout.CENTER));
		row5.add(productCreateRetailLabel);
		row5.add(productCreateRetailText);

		createProductForm.add(row1);
		createProductForm.add(row2);
		createProductForm.add(row3);
		createProductForm.add(row4);
		createProductForm.add(row5);

		JPanel tableButtonPanel = new JPanel();  
		//Back button  
		JButton backButton = new JButton("Cancel");  
		backButton.setToolTipText("Cancels new product and returns to Product screen"); 
		backButton.setActionCommand(productScreenAccess);  
		backButton.addActionListener(new GUIListener());  
		tableButtonPanel.add(backButton);  

		//Add button       
		final JButton productCreateAddButton = new JButton("Add");  
		productCreateAddButton.setActionCommand(productScreenAccess);  
		productCreateAddButton.addActionListener(new ActionListener() { //Create product from fields  
			public void actionPerformed(ActionEvent e){  
				if (check.isNotBlank(productCreateNameText.getText()) && check.isPositiveNumeric(productCreateWholesaleText.getText())   
						&& check.isPositiveNumeric(productCreateRetailText.getText())){  
					db.createProduct(productCreateNameText.getText(), Double.parseDouble(productCreateWholesaleText.getText()),   
							Double.parseDouble(productCreateRetailText.getText()),db.getSuppliers().get(productCreateSupplierCombo.getSelectedIndex()));  

					CardLayout cl = (CardLayout)(gui.getLayout());   
					cl.show(gui, e.getActionCommand());  
					currentActiveScreen=e.getActionCommand();  
					refresh();  
				}  

			}  
		});     
		tableButtonPanel.add(productCreateAddButton);  

		productCreateScreen.setOpaque(true);   
		JLabel titlelbl = new JLabel("Add Product", JLabel.CENTER);   
		titlelbl.setFont(new Font("Arial",2 , 48));   
		topJP.add(titlelbl);   
		botJP.setLayout(new BorderLayout());   
		botJP.add(createProductForm,BorderLayout.CENTER);   

		//        SpringUtilities.makeCompactGrid(createProductForm, 4, 2, 5, 5, 5, 5);  
		productCreateScreen.add(topJP,BorderLayout.NORTH);   
		productCreateScreen.add(botJP,BorderLayout.CENTER);   
		productCreateScreen.add(tableButtonPanel,BorderLayout.SOUTH);   

	}  



	// Edit Product Screen - Cancel           
	// Edit Product Screen - Back 
	public void productEditScreen() { 
		productEditScreen = new JPanel(); 
		productEditScreen.setLayout(new BorderLayout()); 

		final Product product; 
		int supplierCount = 0; 
		int sku = 0; 
		String wholesalePrice, retailPrice, quantity; 

		// System.out.println(productsLine); 
		Vector<Vector<String>> wholeTable = productsTableModel.getDataVector(); 
		if (productsLine != -1) { 
			Vector<String> tableLine = wholeTable.get(productsLine); 
			sku = Integer.parseInt(tableLine.get(0)); 
		} 
		product = db.findProduct(sku); 

		// for(Product product : db.getProducts()){ 
		// if(false){ 
		// 
		// } 
		// } 

		// System.out.println( tableLine.get(0) ); 

		JPanel topJP = new JPanel(); 
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED)); 
		JPanel botJP = new JPanel(); 
		botJP.setBorder(BorderFactory.createLineBorder(Color.blue)); 

		JPanel editProductForm = new JPanel(); 
		editProductForm.setLayout(new SpringLayout()); 

		JLabel SupplierLabel = new JLabel("Supplier: "); 
		editProductForm.add(SupplierLabel); 

		final JComboBox supplierCombo = new JComboBox(); 
		// Populate supplier combo box 
		for (Supplier s : db.getSuppliers()) { 
			supplierCombo.addItem(s.getName()); 
			if (s.getID() == product.getSupplier().getID()) { 
				supplierCombo.setSelectedIndex(supplierCount); 
			} 
			supplierCount++; 
		} 


		//      System.out.println(supplier.getName()); 

		// for (Supplier s : db.getSuppliers()) { 
		// 
		// 
		// } 

		editProductForm.add(supplierCombo); 

		JLabel nameLabel = new JLabel("Name: "); 
		editProductForm.add(nameLabel); 

		final JTextField nameText = new JTextField(product.getName()); 
		editProductForm.add(nameText); 

		JLabel wholesaleLabel = new JLabel("Wholesale Price:  €"); 
		editProductForm.add(wholesaleLabel); 

		wholesalePrice = Double.toString(product.getWholesalePrice()); 
		final JTextField wholesaleText = new JTextField(wholesalePrice); 
		editProductForm.add(wholesaleText); 

		JLabel retailLabel = new JLabel("Retail Price:  €"); 
		editProductForm.add(retailLabel); 

		retailPrice = Double.toString(product.getRetailPrice()); 
		final JTextField retailText = new JTextField(retailPrice); 
		editProductForm.add(retailText); 

		JLabel quantityLabel = new JLabel("Stock quantity"); 
		editProductForm.add(quantityLabel); 

		quantity = Integer.toString(product.getQuantity()); 
		final JTextField quantityText = new JTextField(quantity); 
		editProductForm.add(quantityText); 

		JPanel tableButtonPanel = new JPanel(); 
		JButton productEditBackButton = new JButton("Cancel");  
		productEditBackButton.setToolTipText("Cancels edit product and returns to Product screen");  
		productEditBackButton.setActionCommand(productScreenAccess); 
		productEditBackButton.addActionListener(new GUIListener()); 
		tableButtonPanel.add(productEditBackButton); 
		// productEditBackButton.setSize(width, height) 

		// orderScreen(); 

		// Save button 
		final JButton productCreateAddButton = new JButton("Save"); 
		productCreateAddButton.setActionCommand(productScreenAccess); 
		productCreateAddButton.addActionListener(new ActionListener() { 


			// Create product from fields 
			public void actionPerformed(ActionEvent e) { 
				if (check.isNotBlank(nameText.getText()) 
						&& check.isPositiveNumeric(wholesaleText.getText()) 
						&& check.isPositiveNumeric(retailText.getText())) { 



					Supplier supplier = db.getSuppliers().get(supplierCombo.getSelectedIndex()); 

					product.setName(nameText.getText()); 
					product.setSupplier(supplier); 
					product.setWholesalePrice(Double.parseDouble(wholesaleText 
							.getText())); 
					product.setRetailPrice(Double.parseDouble(retailText 
							.getText())); 
					product.setQuantity( Integer.parseInt( quantityText.getText() ) ); 

					// db.createProduct(nameText.getText(), 
					// Double.parseDouble(wholesaleText 
					// .getText()), 
					// Double.parseDouble(retailText 
					// .getText()), 
					// db.getSuppliers().get( 
					// supplierCombo 
					// .getSelectedIndex())); 

					currentActiveScreen = e.getActionCommand(); 
					refresh(); 
				} 

			} 
		}); 
		tableButtonPanel.add(productCreateAddButton); 

		productEditScreen.setOpaque(true); 
		JLabel titlelbl = new JLabel("Edit Product", JLabel.CENTER); 
		titlelbl.setFont(new Font("Arial", 2, 48)); 
		topJP.add(titlelbl); 
		botJP.setLayout(new BorderLayout()); 
		botJP.add(editProductForm, BorderLayout.CENTER); 

		SpringUtilities.makeCompactGrid(editProductForm, 5, 2, 5, 5, 5, 5); 
		productEditScreen.add(topJP, BorderLayout.NORTH); 
		productEditScreen.add(botJP, BorderLayout.CENTER); 
		productEditScreen.add(tableButtonPanel, BorderLayout.SOUTH); 

	} 



	// Order Screen - Create Order, Edit Order, Back   
	public void orderScreen(){   
		orderScreen = new JPanel();   
		orderScreen.setLayout(new BorderLayout());   
		JPanel topJP = new JPanel();   
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));   
		JPanel botJP =  new JPanel();   
		botJP.setBorder(BorderFactory.createLineBorder(Color.blue));   

		//create JTable for bottom Panel load test data for gui design   
		DefaultTableModel dtm = new DefaultTableModel();  
		dtm.addColumn("Order ID");  
		dtm.addColumn("Supplier");  
		dtm.addColumn("Total Cost (€)");  
		for (Order order : db.getOrders()){  
			Vector<String> singleOrder = new Vector<String>();  
			singleOrder.add(Integer.toString(order.getId()));  
			singleOrder.add(order.getSupplier().getName());  
			singleOrder.add(Double.toString(order.calculateTotalWholesaleValue())); 
			dtm.addRow(singleOrder);      
		}   
		JTable ordersTable = new JTable();  

		ordersTable.setModel(dtm);  


		JLabel orderslabel = new JLabel("All Orders");   

		JPanel tableButtonPanel = new JPanel();   

		JButton orderBackButton = new JButton("Back");   
		orderBackButton.setActionCommand(homeScreenAccess);   
		orderBackButton.addActionListener(new GUIListener());  
		tableButtonPanel.add(orderBackButton);   

		/*       Complete in Sprint 2
		 *   
         JButton orderEditButton = new JButton("Edit Order");   
         orderEditButton.setActionCommand(orderEditScreenAccess);   
         orderEditButton.addActionListener(new GUIListener());   
         tableButtonPanel.add(orderEditButton); 
		 */ 

		JButton orderCreateButton = new JButton("Create Order");   
		orderCreateButton.setActionCommand(orderCreateScreenAccess);   
		orderCreateButton.addActionListener(new GUIListener());   
		tableButtonPanel.add(orderCreateButton);   

		botJP.setLayout(new BorderLayout());   
		botJP.add(orderslabel, BorderLayout.NORTH);   
		botJP.add(new JScrollPane(ordersTable),BorderLayout.CENTER);   

		orderScreen.setOpaque(true);   
		JLabel titlelbl = new JLabel("Manage Orders", JLabel.CENTER);   
		titlelbl.setFont(new Font("Arial",2 , 48));   
		topJP.add(titlelbl);   

		orderScreen.add(topJP,BorderLayout.NORTH);   
		orderScreen.add(botJP,BorderLayout.CENTER);   
		orderScreen.add(tableButtonPanel,BorderLayout.SOUTH);   

	}   



	// Create Order Screen - Cancel  
	public void orderCreateScreen(){ 
		orderCreateScreen = new JPanel(new BorderLayout()); 

		JPanel topJP = new JPanel();  
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));  
		JPanel botJP =  new JPanel();  
		botJP.setBorder(BorderFactory.createLineBorder(Color.blue));  

		JPanel orderCreateFormPanel = new JPanel(new SpringLayout());
		// Labels to display order unique ID
		JLabel createOrderIDLabel = new JLabel("ID:", JLabel.TRAILING); 
		JLabel createOrderIDDisplayLabel = new JLabel(db.getNoOfOrders()+"", JLabel.TRAILING); 
		createOrderIDLabel.setLabelFor(createOrderIDDisplayLabel); 
		// Label & ComboBox to display order supplier
		JLabel createOrderSupplierLabel = new JLabel("Supplier:", JLabel.TRAILING); 
		listOfSuppliersVector = new Vector<String>(); 
		if(orderBasket.size()>0){ 
			listOfSuppliersVector.add(orderBasket.get(0).getSupplier().getName()); 
		} 
		else{ 
			for(Supplier supplier : db.getSuppliers()){ 
				listOfSuppliersVector.add(supplier.getName()); 
			} 
		} 
		createOrderSupplierCombo = new JComboBox<String>(listOfSuppliersVector); 
		createOrderSupplierCombo.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				JComboBox cb = (JComboBox) e.getSource(); 
				createOrderSupplierIndex = cb.getSelectedIndex(); 

				listOfOrderProductsVector.clear(); 
				for(Product product : db.getProducts()){ 
					if(listOfSuppliersVector.get(createOrderSupplierIndex).equals(product.getSupplier().getName())){ 
						listOfOrderProductsVector.add(product.getName()); 
					} 
				} 
			} 
		}); 
		createOrderSupplierCombo.setPreferredSize(new Dimension(40,40)); 
		createOrderSupplierLabel.setLabelFor(createOrderSupplierCombo); 
		// Label & ComboBox to display order products
		JLabel createOrderProductLabel = new JLabel("Product(s):", JLabel.TRAILING); 
		listOfOrderProductsVector = new Vector<String>(); 
		createOrderProductCombo = new JComboBox<String>(listOfOrderProductsVector); 
		createOrderProductLabel.setLabelFor(createOrderProductCombo); 

		JLabel createOrderQuantityLabel = new JLabel("Quantity:", JLabel.TRAILING); 
		createOrderQuantityField = new JTextField(5); 


		orderCreateFormPanel.add(createOrderIDLabel); 
		orderCreateFormPanel.add(createOrderIDDisplayLabel); 
		orderCreateFormPanel.add(createOrderSupplierLabel); 
		orderCreateFormPanel.add(createOrderSupplierCombo); 
		orderCreateFormPanel.add(createOrderProductLabel); 
		orderCreateFormPanel.add(createOrderProductCombo); 
		orderCreateFormPanel.add(createOrderQuantityLabel); 
		orderCreateFormPanel.add(createOrderQuantityField); 

		JPanel orderBsketPanel = new JPanel(new BorderLayout()); 
		JLabel basketLabel = new JLabel("Product Basket"); 

		//create JTable for bottom Panel load test data for gui design  
		DefaultTableModel dtm = new DefaultTableModel(); 
		dtm.addColumn("Product SKU"); 
		dtm.addColumn("Product Name"); 
		dtm.addColumn("Quantity"); 
		for (Product product : orderBasket){ 
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
		orderCreateBackButton.addActionListener(new GUIListener()); 
		JButton orderCreateAddButton = new JButton("Add Product"); 
		orderCreateAddButton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent arg0) { 
				if (check.isPositiveNumeric(createOrderQuantityField.getText()) && check.isProductSupplierMatch(createOrderProductCombo)){ 
					addToBasket(orderBasket, listOfOrderProductsVector.get(createOrderProductCombo.getSelectedIndex()), Integer.parseInt(createOrderQuantityField.getText())); 
					orderBasketRefresh(); 
					createOrderSupplierIndex = createOrderSupplierCombo.getSelectedIndex(); 
					listOfOrderProductsVector.clear(); 
					for(Product product : db.getProducts()){ 
						if(listOfSuppliersVector.get(createOrderSupplierIndex).equals(product.getSupplier().getName())){ 
							listOfOrderProductsVector.add(product.getName()); 
						} 
					} 
				} 
			} 
		}); 
		orderCreateSubmitButton = new JButton("Submit Order"); 
		orderCreateSubmitButton.setActionCommand(orderScreenAccess); 
		orderCreateSubmitButton.addActionListener(new ActionListener() {  
			@Override
			public void actionPerformed(ActionEvent e) { 
				if(check.isBasketPopulated(orderBasket)){ 
					orderSubmit(orderBasket); 

					CardLayout cl = (CardLayout)(gui.getLayout());  
					cl.show(gui, e.getActionCommand()); 
					currentActiveScreen=e.getActionCommand(); 
					refresh(); 
				} 
			} 
		}); 
		JPanel orderCreateButtonPanel = new JPanel(new FlowLayout()); 
		orderCreateButtonPanel.add(orderCreateBackButton); 
		orderCreateButtonPanel.add(orderCreateAddButton); 
		orderCreateButtonPanel.add(orderCreateSubmitButton); 

		JLabel titlelbl = new JLabel("Create Order", JLabel.CENTER);  
		titlelbl.setFont(new Font("Arial",2 , 48));  
		topJP.add(titlelbl);  
		botJP.setLayout(new BorderLayout());  
		botJP.add(orderCreateFormPanel,BorderLayout.CENTER); 
		botJP.add(orderBsketPanel,BorderLayout.SOUTH);  

		orderCreateScreen.setOpaque(true);  //content panes must be opaque 
		orderCreateScreen.add(topJP,BorderLayout.NORTH);  
		orderCreateScreen.add(botJP,BorderLayout.CENTER);  
		orderCreateScreen.add(orderCreateButtonPanel,BorderLayout.SOUTH);  

	} 



	// Edit Order Screen - Cancel   
	public void orderEditScreen(){   
		orderEditScreen = new JPanel();   
		JButton orderEditBackButton = new JButton("Cancel"); 
		orderEditBackButton.setToolTipText("Cancels editing order and returns to Order screen"); 
		orderEditBackButton.setActionCommand(orderScreenAccess);   
		orderEditBackButton.addActionListener(new GUIListener());   
		orderEditScreen.add(orderEditBackButton);   
	}   


	// Supplier Screen - Create Supplier, Back 
	public void supplierScreen(){ 

		//Declare panels and layouts 
		supplierScreen = new JPanel();   
		supplierScreen.setLayout(new BorderLayout());       
		JPanel topJP = new JPanel();   
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));        
		JPanel botJP =  new JPanel();   
		botJP.setBorder(BorderFactory.createLineBorder(Color.blue));  
		botJP.setLayout(new BorderLayout());  
		JPanel tableButtonPanel = new JPanel();  

		//Creating top banner 
		supplierScreen.setOpaque(true);   
		JLabel titlelbl = new JLabel("Manage Suppliers", JLabel.CENTER);   
		titlelbl.setFont(new Font("Arial",2 , 48));   


		//create Table for bottom panel. using DefaultTableModel as an easy way to make a table 
		DefaultTableModel dtm = new DefaultTableModel();  
		dtm.addColumn("Supplier ID");  
		dtm.addColumn("Supplier Name"); 

		/* 
		 * Populating the suppliers table - Loop through suppliers ArrayList and all each ID and name to vector 
		 * Using vectors because they are compatible with tables whereas ArrayLists are not 
		 */
		for (Supplier s : db.getSuppliers()){  
			Vector<String> singleSupplier = new Vector<String>();  
			singleSupplier.add(Integer.toString(s.getID()));   
			singleSupplier.add(s.getName());  
			dtm.addRow(singleSupplier);      
		}   
		JTable supplierTable = new JTable();  
		supplierTable.setEnabled(false); 
		supplierTable.setModel(dtm);  

		//Create back button and set action listener 
		JButton backButton = new JButton("Back");
		backButton.setActionCommand(homeScreenAccess);   
		backButton.addActionListener(new GUIListener());   
		tableButtonPanel.add(backButton);  

		//Create New Supplier button and set action listener  
		JButton createButton = new JButton("Add New Supplier");   
		createButton.setActionCommand(supplierCreateScreenAccess);   
		createButton.addActionListener(new GUIListener());   
		tableButtonPanel.add(createButton);    

		//Set inner panel content 
		topJP.add(titlelbl); 
		botJP.add(new JLabel("All Suppliers"), BorderLayout.NORTH);   
		botJP.add(new JScrollPane(supplierTable),BorderLayout.CENTER);   

		//Set outer panel content 
		supplierScreen.add(topJP,BorderLayout.NORTH);   
		supplierScreen.add(botJP,BorderLayout.CENTER);  
		supplierScreen.add(tableButtonPanel,BorderLayout.SOUTH);   
	} 


	// Create Supplier Screen - Cancel 
	public void supplierCreateScreen(){  

		//Declare panels and layouts 
		supplierCreateScreen = new JPanel();  
		supplierCreateScreen.setLayout(new BorderLayout());        
		JPanel topJP = new JPanel();   
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));   
		JPanel botJP =  new JPanel();   
		JPanel buttonPanel = new JPanel();  
		// create Supplier form Panel and add title border
		JPanel createSupplierForm = new JPanel();  
		createSupplierForm.setLayout(new GridLayout(10,1,3,3)); 
		TitledBorder nameBorder = BorderFactory.createTitledBorder("Supplier Details"); 
		createSupplierForm.setBorder(nameBorder); 
		//Create row1 Panel and add components
		JPanel row1 = new JPanel();

		//Creating label 
		JLabel supplierCreateNameLabel = new JLabel("Name: ");  
		row1.add(supplierCreateNameLabel);    

		//Creating editable text field 
		final JTextField supplierCreateNameText = new JTextField(10);  
		row1.add(supplierCreateNameText);

		createSupplierForm.add(row1);  

		//Create back button and set action listener  
		JButton backButton = new JButton("Cancel");  
		backButton.setToolTipText("Cancels new supplier and returns to Supplier screen"); 
		backButton.setActionCommand(supplierScreenAccess);  
		backButton.addActionListener(new GUIListener());  
		buttonPanel.add(backButton); 


		//Create 'Add' button     
		final JButton addButton = new JButton("Add");  

		//Set action command so that program switches to supplierScreenAccess panel after successful add 
		addButton.setActionCommand(supplierScreenAccess);  

		//Add Supplier - when button is pressed, check if the text field is not blank, then add supplier 
		addButton.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e){  
				if (check.isNotBlank(supplierCreateNameText.getText())){  

					//Create supplier from 'name' text field 
					db.createSupplier(supplierCreateNameText.getText());  

					//Retrieving the current active window so that the program can refresh() and then switch to the previous screen 
					CardLayout cl = (CardLayout)(gui.getLayout());   
					cl.show(gui, e.getActionCommand());  
					currentActiveScreen=e.getActionCommand(); 

					//This updates the rest of the program with the new supplier 
					refresh();  
				}  

			}  
		});    

		buttonPanel.add(addButton);  

		//Creating top banner 
		supplierCreateScreen.setOpaque(true);   
		JLabel titlelbl = new JLabel("Add Supplier", JLabel.CENTER);   
		titlelbl.setFont(new Font("Arial",2 , 48));   

		//Set inner panel content 
		topJP.add(titlelbl);   
		botJP.setLayout(new BorderLayout());   
		botJP.add(createSupplierForm,BorderLayout.CENTER);   


		//Set outer panel content 
		supplierCreateScreen.add(topJP,BorderLayout.NORTH);   
		supplierCreateScreen.add(botJP,BorderLayout.CENTER);   
		supplierCreateScreen.add(buttonPanel,BorderLayout.SOUTH);   

	}  


	//	Customer screen - Create Customer, View Customer, Back 
	public void customerScreen(){

		//Declare panels and layouts
		customerScreen = new JPanel();  
		customerScreen.setLayout(new BorderLayout());      
		JPanel topJP = new JPanel();  
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));       
		JPanel botJP =  new JPanel();  
		botJP.setBorder(BorderFactory.createLineBorder(Color.blue)); 
		botJP.setLayout(new BorderLayout());  
		JPanel buttonPanel = new JPanel();  

		//Creating top banner
		customerScreen.setOpaque(true);  
		JLabel titlelbl = new JLabel("Manage Customers", JLabel.CENTER);  
		titlelbl.setFont(new Font("Arial",2 , 48));  

		/*
		 * Populating the customers table - Loop through customers ArrayList and all each ID and name to vector
		 * Using vectors because they are compatible with tables whereas ArrayLists are not
		 */

		//create Table for customers panel. using DefaultTableModel as an easy way to make a table
		final JTable customerTable = new JTable();
		DefaultTableModel model = new DefaultTableModel()
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}
		};
		model.addColumn("ID"); 
		model.addColumn("Name");
		model.addColumn("Date of Birth");
		model.addColumn("Phone number");

		//Populate rows with customer data
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (Customer c : db.getCustomers()){ 
			Vector<String> singleCustomer = new Vector<String>(); 
			singleCustomer.add(Integer.toString(c.getId()));  
			singleCustomer.add(c.getName()); 
			singleCustomer.add(df.format(c.getDateOfBirth()).toString());
			singleCustomer.add(c.getPhoneNumber()); 

			model.addRow(singleCustomer);     
		}      
		customerTable.setModel(model); 


		//Get the selected customer ID from when the table is clicked
		customerTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {    	      
				selectedCustomerID = Integer.parseInt(customerTable.getValueAt(customerTable.getSelectedRow(),0).toString());
			}
		});
		//Get the selected customer ID from when the keyboard is clicked
		customerTable.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent e){ 

			}
			public void keyReleased(KeyEvent e) {	
				selectedCustomerID = Integer.parseInt(customerTable.getValueAt(customerTable.getSelectedRow(),0).toString());
			}
			public void keyTyped(KeyEvent e) {

			}
		});


		//Create Back button and set action listener
		JButton backButton = new JButton("Back");  
		backButton.setActionCommand(homeScreenAccess);  
		backButton.addActionListener(new GUIListener());  
		buttonPanel.add(backButton); 

		//Create Edit button and set action listener
		JButton viewButton = new JButton("View Customer");  
		viewButton.setActionCommand(customerViewScreenAccess);  

		viewButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e){ 
				//Edit button checks to see if a customer is selected
				if (check.isACustomer(selectedCustomerID, db.getCustomers())){
					currentActiveScreen = e.getActionCommand(); 
					refresh(); 
				}
			}
		});

		buttonPanel.add(viewButton); 

		//Create New Customer button and set action listener
		JButton addButton = new JButton("Add New Customer");  
		addButton.setActionCommand(customerCreateScreenAccess);  
		addButton.addActionListener(new GUIListener());  
		buttonPanel.add(addButton);  

		//Set inner panel content
		topJP.add(titlelbl);
		botJP.add(new JLabel("All Customers"), BorderLayout.NORTH);  
		botJP.add(new JScrollPane(customerTable),BorderLayout.CENTER);  

		//Set outer panel content
		customerScreen.add(topJP,BorderLayout.NORTH);  
		customerScreen.add(botJP,BorderLayout.CENTER); 
		customerScreen.add(buttonPanel,BorderLayout.SOUTH);  

	}


	//	Create Customer Screen - Cancel 
	public void customerCreateScreen(){ 

		//Initiate panels and layouts 
		customerCreateScreen = new JPanel();  
		customerCreateScreen.setLayout(new BorderLayout());         
		JPanel topJP = new JPanel();   
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));   
		JPanel midJP =  new JPanel();   

		JPanel createCustomerForm = new JPanel();  
		createCustomerForm.setLayout(new SpringLayout());  

		JPanel botPanel = new JPanel();  
		botPanel.setLayout(new FlowLayout()); 

		createCustomerForm.setLayout(new GridLayout(10,1,3,3)); 
		TitledBorder nameBorder = BorderFactory.createTitledBorder("Customer Details"); 
		createCustomerForm.setBorder(nameBorder); 
		//Create row1 Panel and add components
		JPanel row1 = new JPanel();


		//Create name label and text field   
		JLabel customerCreateNameLabel = new JLabel("Name: ");  
		createCustomerForm.add(customerCreateNameLabel);        
		final JTextField customerCreateNameText = new JTextField(10);  
		createCustomerForm.add(customerCreateNameText);  

		//Create phone number label and text field   
		JLabel customerCreatePhoneLabel = new JLabel("Phone Number: ");  
		createCustomerForm.add(customerCreatePhoneLabel);    
		final JTextField customerCreatePhoneText = new JTextField(10);  
		createCustomerForm.add(customerCreatePhoneText);  

		//Create address label and text field   
		JLabel customerCreateRetailLabel = new JLabel("Address: ");  
		createCustomerForm.add(customerCreateRetailLabel);    
		final JTextField customerCreateAddressText = new JTextField(10);  
		createCustomerForm.add(customerCreateAddressText);  

		//Date of birth label 
		JLabel customerCreateDOBLabel = new JLabel("Date of Birth: ");  
		createCustomerForm.add(customerCreateDOBLabel);  

		//******************Date of birth panel******************* 
		/* 
		 * Create the Date of Birth Panel. This will contain the date labels  
		 * and combo boxes and be inserted into the Spring Grid Layout 
		 */ 
		JPanel dOBPanel = new JPanel();  

		//Day label 
		JLabel dOBDayLabel = new JLabel("Day:"); 
		dOBPanel.add(dOBDayLabel); 
		//Populate the array of days (1-31) 
		String[] days = new String[31]; 
		for (int i = 1; i<32;i++){ 
			//If the number is less than 10, convert to a double digit (necessary for date formatting) 
			days[i-1]=(i < 10 ? "0" : "")+ Integer.toString(i);  

		} 
		final JComboBox<String> daysComboBox = new JComboBox<String>(days); 
		//Add String [] of days to the combo box 
		dOBPanel.add(daysComboBox); 

		//Month label 
		JLabel dOBMonthLabel = new JLabel("Month:"); 
		dOBPanel.add(dOBMonthLabel);      
		//Populate the array of months 
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}; 
		final JComboBox<String> monthsComboBox = new JComboBox<String>(months); 
		//Add String [] of months to the combo box 
		dOBPanel.add(monthsComboBox); 

		//Year label 
		JLabel dOBYearLabel = new JLabel("Year:"); 
		dOBPanel.add(dOBYearLabel);   
		//Populate the array of years (100 previous years) 
		String[] years = new String[100]; 
		int startYear=2013; 
		for (int i = 0; i < years.length; i++){ 
			years[i] = Integer.toString(startYear - i); 
		} 
		final JComboBox<String> yearsComboBox = new JComboBox<String>(years); 
		//Add String [] of years to the combo box 
		dOBPanel.add(yearsComboBox); 

		createCustomerForm.add(dOBPanel); 

		//Back button  
		JButton customerCreateBackButton = new JButton("Cancel");  
		customerCreateBackButton.setToolTipText("Cancels new customer and returns to Customer screen"); 
		customerCreateBackButton.setActionCommand(customerScreenAccess);  
		customerCreateBackButton.addActionListener(new GUIListener());  
		botPanel.add(customerCreateBackButton); 

		//Add button       
		final JButton customerCreateAddButton = new JButton("Add");  
		customerCreateAddButton.setActionCommand(customerScreenAccess);  

		/* 
		 * Add customer: if all text fields contain text, create customer from text fields 
		 */
		customerCreateAddButton.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){  
				if (check.isNotBlank(customerCreateNameText.getText()) && check.isNotBlank(customerCreatePhoneText.getText()) 
						&& check.isNotBlank(customerCreateAddressText.getText())){ 

					//Initiate date format so that date of birth can be passed into createCustomer method 
					SimpleDateFormat ft = new SimpleDateFormat("ddMMyyyy"); 
					Date dOB = new Date(); 

					//Gets the correct month as a string in two digit form (01-12)  
					String month = (monthsComboBox.getSelectedIndex()+1 < 10 ? "0" : "") + Integer.toString(monthsComboBox.getSelectedIndex()+1); 

					//the .parse function will not work unless surrounded by a try+catch for some reason! 
					try { 

						//Making the date string of format ddMMyyyy 
						dOB = ft.parse(daysComboBox.getSelectedItem().toString() +month+yearsComboBox.getSelectedItem().toString()); 
					} catch (ParseException e1) { 
					} 
					//Create customer from fields 
					if (check.isOfLegalAge(dOB)){ 
						db.createCustomer(customerCreateNameText.getText(), dOB, customerCreateAddressText.getText(), customerCreatePhoneText.getText()); 

						currentActiveScreen=e.getActionCommand();  
						refresh();    
					} 

				} 
			} 
		}); 

		botPanel.add(customerCreateAddButton);  

		//Set top banner 
		customerCreateScreen.setOpaque(true);   
		JLabel titlelbl = new JLabel("Add Customer", JLabel.CENTER);   
		titlelbl.setFont(new Font("Arial",2 , 48));   

		//Add content to outer panels 
		topJP.add(titlelbl);   
		midJP.setLayout(new BorderLayout());   
		midJP.add(createCustomerForm,BorderLayout.CENTER);   




		//Add content to inner panel 
		customerCreateScreen.add(topJP,BorderLayout.NORTH);   
		customerCreateScreen.add(midJP,BorderLayout.CENTER);   
		customerCreateScreen.add(botPanel,BorderLayout.SOUTH);   
	} 

	// 	View Customer Screen - Edit, Delete 

	public void customerViewScreen(){

		//Declare panels and layouts
		customerViewScreen = new JPanel();
		customerViewScreen.setLayout(new BoxLayout(customerViewScreen,BoxLayout.Y_AXIS))  ;     

		JPanel topJP = new JPanel();
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED)); 
		JPanel middleDetailsJP = new JPanel();
		middleDetailsJP.setLayout(new FlowLayout(FlowLayout.LEADING,5,15));
		JPanel middleInvoicesJP =  new JPanel(new BorderLayout());
		middleDetailsJP.setBorder(BorderFactory.createLineBorder(Color.blue)); 

		JPanel bottomJP=new JPanel(new FlowLayout());

		//************Top Panel*************************
		//Creating top banner
		customerScreen.setOpaque(true);  
		JLabel titlelbl = new JLabel(" View Customer", JLabel.CENTER);  
		titlelbl.setFont(new Font("Arial",2 , 48));  
		topJP.add(titlelbl);


		//************Customer Details Panel*************************
		//Need to initiate customer as it is not defined until inside a loop
		Customer customer = new Customer(0,"",new Date(),"","");

		//Retrieving the correct customer from the selectedCustomerID from the table. 
		for (Customer c : db.getCustomers()){
			if (selectedCustomerID == c.getId()){
				customer=c;
			}
		}

		//Putting customer details into the text fields
		JLabel nameLabel = new JLabel("Name:");
		middleDetailsJP.add(nameLabel);

		final JTextField nameText = new JTextField();
		nameText.setText(customer.getName());
		middleDetailsJP.add(nameText);

		JLabel addressLabel = new JLabel("Address:");
		middleDetailsJP.add(addressLabel);

		final JTextField addressText = new JTextField();
		addressText.setText(customer.getAddress());
		addressText.setMinimumSize(new Dimension(30,30));
		middleDetailsJP.add(addressText);

		JLabel phoneLabel = new JLabel("Phone number:");
		middleDetailsJP.add(phoneLabel);

		final JTextField phoneText = new JTextField();
		phoneText.setText(customer.getPhoneNumber());
		middleDetailsJP.add(phoneText);

		JLabel dODLabel = new JLabel("Date of Birth:");  
		middleDetailsJP.add(dODLabel);

		JTextField dOBText = new JTextField();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dOBText.setText(sdf.format(customer.getDateOfBirth()));
		dOBText.setEditable(false);
		middleDetailsJP.add(dOBText);

		/*
		 * Edit button: When edit is pressed, code cycles through customers, finds the one with the corresponding ID 
		 * to what's selected in the table (selectedCustomerID) and edits the details according to the text fields
		 */
		JButton editButton = new JButton("Edit");
		middleDetailsJP.add(editButton);
		editButton.setActionCommand(customerScreenAccess);  
		editButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e){
				for (Customer c : db.getCustomers()){
					if (selectedCustomerID == c.getId()){
						if (check.isNotBlank(nameText.getText()) && check.isNotBlank(addressText.getText())
								&& check.isNotBlank(phoneText.getText())){
							c.setName(nameText.getText());
							c.setAddress(addressText.getText());
							c.setPhoneNumber(phoneText.getText());
							currentActiveScreen=e.getActionCommand();
							refresh();
							selectedCustomerID=0;
							break;
						}
					}
				}
			}
		});


		//************Invoices Panel*************************

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID"); 
		model.addColumn("Total number of items");
		model.addColumn("Invoice Cost");


		//Populate rows with invoice data    
		for (Invoice i : customer.getInvoices(db.getInvoices())){ 
			Vector<String> singleInvoice = new Vector<String>(); 
			singleInvoice.add(Integer.toString(i.getId()));  
			singleInvoice.add(String.valueOf(i.calculateTotalNumberItems())); 
			singleInvoice.add(String.valueOf(i.calculateTotalRetailValue())); 
			model.addRow(singleInvoice);     
		}      
		final JTable invoiceTable = new JTable();
		invoiceTable.setModel(model);
		middleInvoicesJP.setLayout(new BorderLayout());

		//Populate invoices panel with labels and table
		middleInvoicesJP.add(new JLabel("Invoices"), BorderLayout.NORTH);
		middleInvoicesJP.add(new JScrollPane(invoiceTable), BorderLayout.CENTER);
		middleInvoicesJP.add(new JLabel("Invoices"), BorderLayout.NORTH);


		middleInvoicesJP.add(new JLabel("Total money spent: " + customer.getTotalMoneySpent(db.getInvoices())), BorderLayout.SOUTH);

		//************Bottom Panel*************************

		//Create back button and set action listener
		JButton backButton = new JButton("Back");  
		backButton.setActionCommand(customerScreenAccess);  
		backButton.addActionListener(new GUIListener());  
		bottomJP.add(backButton);

		/*
		 * Delete button: When edit is pressed, code cycles through customers, finds the one with the corresponding ID 
		 * to what's selected in the table (selectedCustomerID) and deletes the customer accordingly
		 */
		JButton deleteButton = new JButton("Delete Customer");  
		deleteButton.setActionCommand(customerScreenAccess); 
		bottomJP.add(deleteButton);
		deleteButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e){    		  
				for (Customer c : db.getCustomers()){
					if (selectedCustomerID==c.getId()){
						db.getCustomers().remove(c);
						currentActiveScreen=e.getActionCommand();
						refresh();
						selectedCustomerID=0;
						break;
					}
				}

			}
		});

		customerViewScreen.add(topJP);
		customerViewScreen.add(middleDetailsJP);
		customerViewScreen.add(middleInvoicesJP);
		customerViewScreen.add(bottomJP);

		//   customerViewScreen.add(botJP,0);

	} 


	// 	Invoice Screen - Create Invoice, Edit Invoice, Back   
	public void invoiceScreen(){   
		invoiceScreen = new JPanel();   
		invoiceScreen.setLayout(new BorderLayout());   
		JPanel topJP = new JPanel();   
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));   
		JPanel botJP =  new JPanel();   
		botJP.setBorder(BorderFactory.createLineBorder(Color.blue));   

		//create JTable for bottom Panel load test data for gui design   
		DefaultTableModel dtm = new DefaultTableModel();  
		dtm.addColumn("Invoice ID");  
		dtm.addColumn("Date");  
		dtm.addColumn("Total Cost (€)");  
		//Making a new simple date format to print dates in table
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Invoice invoice : db.getInvoices()){  
			Vector<String> singleInvoice = new Vector<String>();  
			singleInvoice.add(Integer.toString(invoice.getId())); 

			singleInvoice.add(sdf.format(invoice.getDate())); 


			singleInvoice.add(Double.toString(invoice.calculateTotalRetailValue()));  
			dtm.addRow(singleInvoice);      
		}   
		JTable invoicesTable = new JTable();  

		invoicesTable.setModel(dtm);  


		JLabel invoiceslabel = new JLabel("All Invoices");   

		JPanel tableButtonPanel = new JPanel();   

		JButton invoiceBackButton = new JButton("Back");   
		invoiceBackButton.setActionCommand(homeScreenAccess);   
		invoiceBackButton.addActionListener(new GUIListener());  
		tableButtonPanel.add(invoiceBackButton);   

		/*
		 * 	Complete in sprint 2        
      JButton invoiceEditButton = new JButton("Edit Invoice");   
      invoiceEditButton.setActionCommand(invoiceEditScreenAccess);   
      invoiceEditButton.addActionListener(new GUIListener());   
      tableButtonPanel.add(invoiceEditButton);  
		 */       
		JButton invoiceCreateButton = new JButton("Create Invoice");   
		invoiceCreateButton.setActionCommand(invoiceCreateScreenAccess);   
		invoiceCreateButton.addActionListener(new GUIListener());   
		tableButtonPanel.add(invoiceCreateButton);   

		botJP.setLayout(new BorderLayout());   
		botJP.add(invoiceslabel, BorderLayout.NORTH);   
		botJP.add(new JScrollPane(invoicesTable),BorderLayout.CENTER);   

		invoiceScreen.setOpaque(true);   
		JLabel titlelbl = new JLabel("Manage Invoices", JLabel.CENTER);   
		titlelbl.setFont(new Font("Arial",2 , 48));   
		topJP.add(titlelbl);   

		invoiceScreen.add(topJP,BorderLayout.NORTH);   
		invoiceScreen.add(botJP,BorderLayout.CENTER);   
		invoiceScreen.add(tableButtonPanel,BorderLayout.SOUTH);   

		//      // Clears the ArrayList of Products that can be added to an Invoice prio 
		//      invoiceBasket.clear(); 
	}  



	//	Create Invoice Screen - Cancel  
	public void invoiceCreateScreen(){ 
		invoiceCreateScreen = new JPanel(new BorderLayout()); 

		JPanel topJP = new JPanel();  
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));  
		JPanel botJP =  new JPanel();  
		botJP.setBorder(BorderFactory.createLineBorder(Color.blue));  

		// Panel to contain invoice input fields
		JPanel invoiceCreateFormPanel = new JPanel(new SpringLayout()); 
		// Labels to display invoice unique ID
		JLabel createInvoiceIDLabel = new JLabel("ID:", JLabel.TRAILING); 
		JLabel createInvoiceIDDisplayLabel = new JLabel(db.getNoOfInvoices()+"", JLabel.TRAILING); 
		createInvoiceIDLabel.setLabelFor(createInvoiceIDDisplayLabel); 
		// Label & ComboBox to display invoice customer
		JLabel createInvoiceCustomerLabel = new JLabel("Customer:", JLabel.TRAILING); 
		listOfCustomersVector = new Vector<String>(); 
		if(invoiceBasket.size()>0){ 
			listOfCustomersVector.add(invoiceCustomer); 
		} 
		else{ 
			for(Customer customer : db.getCustomers()){ 
				listOfCustomersVector.add(customer.getName()); 
			} 
		}
		createInvoiceCustomerCombo = new JComboBox<String>(listOfCustomersVector);
		createInvoiceCustomerCombo.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				JComboBox cb = (JComboBox) e.getSource(); 
				createInvoiceSupplierIndex = cb.getSelectedIndex(); 

				invoiceCustomer = listOfCustomersVector.get(createInvoiceSupplierIndex);
				listOfInvoiceProductsVector.clear(); 
				for(Product product : db.getProducts()){ 
					listOfInvoiceProductsVector.add(product.getName()); 
				} 
			} 
		}); 
		createInvoiceCustomerCombo.setPreferredSize(new Dimension(40,40)); 
		createInvoiceCustomerLabel.setLabelFor(createInvoiceCustomerCombo);

		// Label & ComboBox to display invoice product
		JLabel createInvoiceProductLabel = new JLabel("Product(s):", JLabel.TRAILING); 
		listOfInvoiceProductsVector = new Vector<String>(); 
		createInvoiceProductCombo = new JComboBox<String>(listOfInvoiceProductsVector); 
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
		DefaultTableModel dtm = new DefaultTableModel(); 
		dtm.addColumn("Product SKU"); 
		dtm.addColumn("Product Name"); 
		dtm.addColumn("Quantity"); 
		for (Product product : invoiceBasket){ 
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
		invoiceCreateBackButton.addActionListener(new GUIListener()); 
		JButton invoiceCreateAddButton = new JButton("Add Product"); 

		invoiceCreateAddButton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent arg0) { 
				if (check.isPositiveNumeric(createInvoiceQuantityField.getText()) && check.isInvoiceProductSelected(createInvoiceProductCombo) &&
						check.isSufficientStock(db, invoiceBasket, listOfInvoiceProductsVector.get(createInvoiceProductCombo.getSelectedIndex()), Integer.parseInt(createInvoiceQuantityField.getText()))){ 
					addToBasket(invoiceBasket, listOfInvoiceProductsVector.get(createInvoiceProductCombo.getSelectedIndex()), Integer.parseInt(createInvoiceQuantityField.getText())); 
					invoiceBasketRefresh();
					createInvoiceSupplierIndex = createInvoiceCustomerCombo.getSelectedIndex(); 
					invoiceCustomer = listOfCustomersVector.get(createInvoiceSupplierIndex);
					listOfInvoiceProductsVector.clear(); 
					for(Product product : db.getProducts()){ 
						listOfInvoiceProductsVector.add(product.getName()); 
					} 
				} 
			} 
		}); 
		invoiceCreateSubmitButton = new JButton("Submit Invoice"); 
		invoiceCreateSubmitButton.setActionCommand(invoiceScreenAccess); 
		invoiceCreateSubmitButton.addActionListener(new ActionListener() {  
			@Override
			public void actionPerformed(ActionEvent e) { 
				if(check.isBasketPopulated(invoiceBasket)){ 
					invoiceSubmit(invoiceBasket); 

					CardLayout cl = (CardLayout)(gui.getLayout());  
					cl.show(gui, e.getActionCommand()); 
					currentActiveScreen=e.getActionCommand(); 
					refresh(); 
				} 
			} 
		}); 
		JPanel invoiceCreateButtonPanel = new JPanel(new FlowLayout()); 
		invoiceCreateButtonPanel.add(invoiceCreateBackButton); 
		invoiceCreateButtonPanel.add(invoiceCreateAddButton); 
		invoiceCreateButtonPanel.add(invoiceCreateSubmitButton); 

		JLabel titlelbl = new JLabel("Create Invoice", JLabel.CENTER);  
		titlelbl.setFont(new Font("Arial",2 , 48));  
		topJP.add(titlelbl);  
		botJP.setLayout(new BorderLayout());  
		botJP.add(invoiceCreateFormPanel,BorderLayout.CENTER); 
		botJP.add(invoiceBsketPanel,BorderLayout.SOUTH);  

		invoiceCreateScreen.setOpaque(true);  //content panes must be opaque 
		invoiceCreateScreen.add(topJP,BorderLayout.NORTH);  
		invoiceCreateScreen.add(botJP,BorderLayout.CENTER);  
		invoiceCreateScreen.add(invoiceCreateButtonPanel,BorderLayout.SOUTH);  

	}


	/**************************************************************************************    
	 * 	Action Listeners
	 **************************************************************************************/

	//  ActionListener for GUI navigation buttons   
	private class GUIListener implements ActionListener{   
		@Override
		public void actionPerformed(ActionEvent e) {   
			currentActiveScreen = e.getActionCommand();  
			refresh();  
		}   
	}   



	//  ActionListener for OrderSupplier buttons   
	private class OrderSupplierListener implements ActionListener{  
		@Override
		public void actionPerformed(ActionEvent e) {  
			JComboBox cb = (JComboBox) e.getSource();  
			createOrderSupplierIndex = cb.getSelectedIndex();  
			System.out.println(createOrderSupplierIndex);  

			listOfOrderProductsVector.clear();  
			for(Product product : db.getProducts()){  
				if(db.getSuppliers().get(createOrderSupplierIndex) == product.getSupplier()){  
					listOfOrderProductsVector.add(product.getName());  
					System.out.println(product.getName());  
				}  
			}  
		}  
	}  

	/**************************************************************************************    
	 * 	Additional methods
	 **************************************************************************************/

	//  Adds Product/Quantity pair to the basket 
	public void addToBasket(ArrayList<Product> basket, String prodName, int qty){ 
		for(Product product : db.getProducts()){ 
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


	//  Creates an Order of products in the basket 
	public void orderSubmit(ArrayList<Product> prodArray){ 
		ArrayList<Product> basketContents = new ArrayList<Product>( (ArrayList<Product>) prodArray.clone()); 
		db.createOrder(basketContents); 
		// For auto-delivery of order upon submission 
		db.inboundDelivery(basketContents); 
	}


	//  Creates an Invoice of products in the basket 
	public void invoiceSubmit(ArrayList<Product> prodArray){ 
		ArrayList<Product> basketContents = new ArrayList<Product>( (ArrayList<Product>) prodArray.clone()); 
		for(Customer customer : db.getCustomers()){
			if(customer.getName().equals(invoiceCustomer)){
				db.createInvoice(basketContents, customer); 
			}
		}
		// For auto-delivery of order upon submission 
		db.outboundDelivery(basketContents); 
	} 


	//  Refreshes data displayed on all screens 
	public void refresh(){  
		// Clears the ArrayList of Products that can be added to an Order prio 
		orderBasket.clear();
		invoiceBasket.clear();

		homeScreen.removeAll();  
		productScreen.removeAll();  
		orderScreen.removeAll();  
		productCreateScreen.removeAll();  
		productEditScreen.removeAll();  
		orderCreateScreen.removeAll();  
		orderEditScreen.removeAll();  
		supplierScreen.removeAll(); 
		supplierCreateScreen.removeAll(); 
		customerScreen.removeAll(); 
		customerCreateScreen.removeAll(); 
		customerViewScreen.removeAll(); 
		invoiceScreen.removeAll();
		invoiceCreateScreen.removeAll();
		stockControlScreen.removeAll();

		homeScreen();  
		productScreen();  
		orderScreen();  
		productCreateScreen();  
		productEditScreen();  
		orderCreateScreen();  
		orderEditScreen();  
		supplierScreen(); 
		supplierCreateScreen(); 
		customerScreen(); 
		customerCreateScreen(); 
		customerViewScreen(); 
		invoiceScreen();
		invoiceCreateScreen();
		stockControlScreen();

		gui.removeAll();  
		gui.add(homeScreen,homeScreenAccess);  
		gui.add(productScreen,productScreenAccess);  
		gui.add(orderScreen,orderScreenAccess);  
		gui.add(productCreateScreen,productCreateScreenAccess);   
		gui.add(productEditScreen,productEditScreenAccess);  
		gui.add(orderCreateScreen,orderCreateScreenAccess);  
		gui.add(orderEditScreen,orderEditScreenAccess); 
		gui.add(supplierScreen,supplierScreenAccess); 
		gui.add(supplierCreateScreen,supplierCreateScreenAccess); 
		gui.add(customerScreen,customerScreenAccess);  
		gui.add(customerCreateScreen,customerCreateScreenAccess);  
		gui.add(customerViewScreen,customerViewScreenAccess);  
		gui.add(invoiceScreen,invoiceScreenAccess);
		gui.add(invoiceCreateScreen,invoiceCreateScreenAccess);
		gui.add(stockControlScreen,stockControlScreenAccess);

		CardLayout cl = (CardLayout)(gui.getLayout());        
		cl.show(gui, currentActiveScreen);  

	} 

	private class EditProductListener implements ActionListener { 
		@Override
		public void actionPerformed(ActionEvent e) { 
			currentActiveScreen = e.getActionCommand(); 
			productsLine = productsTable.getSelectedRow(); 
			refresh(); 
			// System.out.println(a); 
		} 
	} 

	//  Refreshes data displayed on create order screens 
	public void orderBasketRefresh(){ 
		orderCreateScreen.removeAll(); 
		orderCreateScreen(); 
		gui.remove(5); 
		gui.add(orderCreateScreen,orderCreateScreenAccess,5); 

		CardLayout cl = (CardLayout)(gui.getLayout());       
		cl.show(gui, currentActiveScreen); 
	}    

	//  Refreshes data displayed on create order screens 
	public void invoiceBasketRefresh(){ 
		invoiceCreateScreen.removeAll(); 
		invoiceCreateScreen(); 
		gui.remove(5); 
		gui.add(invoiceCreateScreen,invoiceCreateScreenAccess,5); 

		CardLayout cl = (CardLayout)(gui.getLayout());       
		cl.show(gui, currentActiveScreen); 
	}  


	/**************************************************************************************    
	 * 	Main Method
	 **************************************************************************************/

	// Runs program main   
	public static void main(String args[]){   
		new MainUI();  
	}   
}