package Sprint2_Day3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GUI_CustomerScreen {
	
	private String homeScreenAccess = "home", customerScreenAccess = "customer", customerCreateScreenAccess = "customerCreate", customerViewScreenAccess = "customerView";
	
	public void customerScreen(){
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
        JLabel titlelbl = new JLabel("Manage Customers", JLabel.CENTER);  
        titlelbl.setFont(new Font("Arial",2 , 48));  
        topJP.add(titlelbl);
// HEADER       


        /*
         * Populating the customers table - Loop through customers ArrayList and all each ID and name to vector
         * Using vectors because they are compatible with tables whereas ArrayLists are not
         */
        
        // Reset the value of the variable that stores the selected line in the JTable
        NewUI.selectedCustomerID = 0;
        
        
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
        for (Customer c : NewUI.db.getCustomers()){ 
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
      		  NewUI.selectedCustomerID = Integer.parseInt(customerTable.getValueAt(customerTable.getSelectedRow(),0).toString());
      	  }
        });
        //Get the selected customer ID from when the keyboard is clicked
        customerTable.addKeyListener(new KeyListener(){
      	  @Override
      	  public void keyPressed(KeyEvent e){ 

      	  }
      	  public void keyReleased(KeyEvent e) {	
      		  NewUI.selectedCustomerID = Integer.parseInt(customerTable.getValueAt(customerTable.getSelectedRow(),0).toString());
      	  }
      	  public void keyTyped(KeyEvent e) {

      	  }
        });

        
        //Create Back button and set action listener
        JButton backButton = new JButton("Back");  
        backButton.setActionCommand(homeScreenAccess);  
        backButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUI_HomeScreen homeScreen = new GUI_HomeScreen();
            	homeScreen.homeScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand()); 
            } 
         });        
        buttonPanel.add(backButton); 
        
        //Create Edit button and set action listener
        JButton viewButton = new JButton("View Customer");  
        viewButton.setActionCommand(customerViewScreenAccess);  
        viewButton.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e){ 
      	      //Edit button checks to see if a customer is selected
      		  if (NewUI.check.isACustomer(NewUI.selectedCustomerID, NewUI.db.getCustomers())){
      			NewUI.currentActiveScreen = e.getActionCommand();
      			  
      			GUI_CustomerViewScreen customerViewScreen = new GUI_CustomerViewScreen();
				customerViewScreen.customerViewScreen(); 
				
				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 
//      			  refresh(); 
      		  }
      	  }
        });

        buttonPanel.add(viewButton); 

        //Create New Customer button and set action listener
        JButton addButton = new JButton("Add New Customer");  
        addButton.setActionCommand(customerCreateScreenAccess);  
        addButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUI_CustomerCreateScreen customerCreateScreen = new GUI_CustomerCreateScreen();
            	customerCreateScreen.customerCreateScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
            	cl.show(NewUI.gui, e.getActionCommand());
               
            	NewUI.currentActiveScreen = e.getActionCommand();
            } 
         }); ;  
        buttonPanel.add(addButton);  

        //Set inner panel content
        topJP.add(titlelbl);
        botJP.add(new JLabel("All Customers"), BorderLayout.NORTH);  
        botJP.add(new JScrollPane(customerTable),BorderLayout.CENTER);

        
// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        
        NewUI.customerScreen = screen;
        NewUI.gui.add(NewUI.customerScreen,customerScreenAccess);
// FOOTER 
    }
	
	

}
