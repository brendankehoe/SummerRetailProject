package Sprint2_Day3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class GUI_InvoiceScreen {
	
	private String homeScreenAccess = "home", invoiceScreenAccess = "invoice", invoiceCreateScreenAccess = "invoiceCreate", invoiceEditScreenAccess = "invoiceEdit";

	public void invoiceScreen(){
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
        JLabel titlelbl = new JLabel("Manage Invoices", JLabel.CENTER);  
        titlelbl.setFont(new Font("Arial",2 , 48));  
        topJP.add(titlelbl);
// HEADER       

        
		/*
		 *	Screen specific code goes here       
		 */
        //create JTable for bottom Panel load test data for gui design   
        DefaultTableModel dtm = new DefaultTableModel()
        {
      	  public boolean isCellEditable(int row, int column)
      	  {
      		  return false;//This causes all cells to be not editable
      	  }
        }; 
        dtm.addColumn("Invoice ID");  
        dtm.addColumn("Customer");  
        dtm.addColumn("Total Cost (€)");  
        for (Invoice invoice : NewUI.db.getInvoices()){  
            Vector<String> singleInvoice = new Vector<String>();  
            singleInvoice.add(Integer.toString(invoice.getId()));  
            singleInvoice.add(invoice.getCustomer().getName());  
            singleInvoice.add(Double.toString(invoice.calculateTotalWholesaleValue()));  
            dtm.addRow(singleInvoice);      
        }   
        JTable invoicesTable = new JTable();  
    
        invoicesTable.setModel(dtm);  
    
    
        JLabel invoiceslabel = new JLabel("All Invoices");   
    
        
        JButton invoiceBackButton = new JButton("Back");   
        invoiceBackButton.setActionCommand(homeScreenAccess);   
        invoiceBackButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                NewUI.currentActiveScreen = e.getActionCommand();

                GUI_HomeScreen homeScreen = new GUI_HomeScreen();
            	homeScreen.homeScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand());
            } 
        });   
        buttonPanel.add(invoiceBackButton);   
        
  /*
   * 	Complete in sprint 2        
        JButton invoiceEditButton = new JButton("Edit Invoice");   
        invoiceEditButton.setActionCommand(invoiceEditScreenAccess);   
        invoiceEditButton.addActionListener(new GUIListener());   
        tableButtonPanel.add(invoiceEditButton);  
   */       
        JButton invoiceCreateButton = new JButton("Create Invoice");   
        invoiceCreateButton.setActionCommand(invoiceCreateScreenAccess);   
        invoiceCreateButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                NewUI.currentActiveScreen = e.getActionCommand();

                GUI_InvoiceCreateScreen invoiceCreateScreen = new GUI_InvoiceCreateScreen();
                invoiceCreateScreen.invoiceCreateScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand());
            } 
        });      
        buttonPanel.add(invoiceCreateButton);   
    
        botJP.setLayout(new BorderLayout());   
        botJP.add(invoiceslabel, BorderLayout.NORTH);   
        botJP.add(new JScrollPane(invoicesTable),BorderLayout.CENTER);   
     

        
// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        
        NewUI.invoiceScreen = screen;
        NewUI.gui.add(NewUI.invoiceScreen,invoiceScreenAccess);
// FOOTER 
        
        // Clears the ArrayList of Products that can be added to an Invoice prio 
        NewUI.invoiceBasket.clear();
        
    }
	
	
}
