package sprint2_Day10;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

 
public class GUI_InvoiceScreen {
	
	private String homeScreenAccess = "home", invoiceScreenAccess = "invoice", invoiceCreateScreenAccess = "invoiceCreate", invoiceViewScreenAccess = "invoiceView";

	public void invoiceScreen(){
// HEADER
        // Create screen panel that is used to replace the gui panel from MainUI.class 
        JPanel screen = new JPanel(new BorderLayout()); 
        screen.setOpaque(true);  //content panes must be opaque  
          
        // Creates three panels used for the top portion, bottom portion and button portion of the screen 
        JPanel topJP = new JPanel();     
        topJP.setBackground(NewUI.topBannerColor);
        JPanel botJP =  new JPanel(new BorderLayout());      
        JPanel buttonPanel = new JPanel(new FlowLayout());  

        // Create the title of the screen in the top panel 
        JLabel titlelbl = new JLabel("Manage Invoices", JLabel.CENTER);   
        titlelbl.setFont(NewUI.topBannerFont);   
        topJP.add(titlelbl);
// HEADER       

        
		/*
		 *	Screen specific code goes here       
		 */
        NewUI.selectedInvoiceID = 0; 
        
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
        final JTable invoicesTable = new JTable();  
        invoicesTable.setAutoCreateRowSorter(true);
        invoicesTable.setModel(dtm);
        
		// double click to get more information
        invoicesTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					NewUI.selectedInvoiceID = Integer.parseInt(invoicesTable
							.getValueAt(invoicesTable.getSelectedRow(), 0)
							.toString());

					GUI_InvoiceViewScreen gUI_InvoiceViewScreen = new GUI_InvoiceViewScreen();
					gUI_InvoiceViewScreen.invoiceViewScreen();

					CardLayout cl = (CardLayout) (NewUI.gui.getLayout());
					cl.show(NewUI.gui, "invoiceView");
				}
			}
		});
        
        //Get the selected customer ID from when the table is clicked
        invoicesTable.addMouseListener(new MouseAdapter() {
      	  public void mouseClicked(MouseEvent e) {    
      		  NewUI.selectedInvoiceID = Integer.parseInt(invoicesTable.getValueAt(invoicesTable.getSelectedRow(),0).toString());
      	  }
        });
    
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

        //Create View button and set action listener
        JButton invoiceViewButton = new JButton("View Invoice");  
        invoiceViewButton.setActionCommand(invoiceViewScreenAccess);  
        invoiceViewButton.addActionListener(new ActionListener() {
       	 public void actionPerformed(ActionEvent e){ 
       		 //Edit button checks to see if a customer is selected
       		 if (NewUI.check.isAInvoice(NewUI.selectedInvoiceID, NewUI.db.getInvoices())){
       			 NewUI.currentActiveScreen = e.getActionCommand();
       			 
       			 GUI_InvoiceViewScreen invoiceViewScreen = new GUI_InvoiceViewScreen();
       			 invoiceViewScreen.invoiceViewScreen(); 
				
       			 CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
       			 cl.show(NewUI.gui, e.getActionCommand()); 
       			 System.out.println(4);
       		 }
       	 }
       });
       buttonPanel.add(invoiceViewButton); 

        //If current logged-in user is admin, hide create invoice
        if (NewUI.currentUser.isAdmin()){
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
        }
    
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
