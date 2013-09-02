package Sprint2_Day6;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

 
public class GUI_InvoiceViewScreen {
	
	private String invoiceScreenAccess = "invoice", invoiceViewScreenAccess = "invoiceView";
	
	public void invoiceViewScreen(){
// HEADER
		// Create screen panel that is used to replace the gui panel from MainUI.class
		JPanel screen = new JPanel(new BorderLayout()); 
		screen.setLayout(new BorderLayout()); 
        screen.setOpaque(true);  //content panes must be opaque 
        
		// Creates three panels used for the top portion, bottom portion and button portion of the screen
        JPanel topJP = new JPanel();  
        topJP.setBackground(NewUI.topBannerColor);
        JPanel botJP =  new JPanel();  
        botJP.setLayout(new BoxLayout(botJP,BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel(new FlowLayout()); 
        
        // Create the title of the screen in the top panel
        JLabel titlelbl = new JLabel("View Invoice", JLabel.CENTER);  
        titlelbl.setFont(new Font("Arial",2 , 48));  
        topJP.add(titlelbl);
// HEADER       

        
/*
 *	Screen specific code goes here       
 */
       // Create dummy Invoice variable to hold object attributes to populate text fields
        ArrayList<Product> dummyProd = new ArrayList<Product>();
        dummyProd.add(new Product(0,"",0,0.0,0.0, new Supplier(0,"","","")));
        Invoice invoice = new Invoice(0, dummyProd, new Customer(0,"",new Date(),"",""), new Date(), true);
        
        for (Invoice i : NewUI.db.getInvoices()){
      	  if (NewUI.selectedInvoiceID == i.getId()){
      		invoice=i;
      	  }
        }
                
        JPanel invoiceViewFormPanel = new JPanel(new BorderLayout());
        JPanel invoiceViewWestPanel = new JPanel(new GridBagLayout());
        JPanel invoiceViewEastPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        
        // Labels to display invoice unique ID
        JLabel viewInvoiceIDLabel = new JLabel("ID:", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        invoiceViewWestPanel.add(viewInvoiceIDLabel,constraint);    
        
        JLabel viewInvoiceIDDisplayLabel = new JLabel(invoice.getId()+"", JLabel.TRAILING); 
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        invoiceViewWestPanel.add(viewInvoiceIDDisplayLabel,constraint);    
                
        // Label & ComboBox to display Invoice customer
        JLabel viewInvoiceCustomerLabel = new JLabel("Customer:", JLabel.TRAILING);
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        invoiceViewWestPanel.add(viewInvoiceCustomerLabel,constraint);  
        
        JLabel viewInvoiceCustomerDsiplayLabel = new JLabel(invoice.getCustomer().getName(), JLabel.TRAILING);
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        invoiceViewWestPanel.add(viewInvoiceCustomerDsiplayLabel,constraint);
        
        
        // Label & ComboBox to display Invoice date
        JLabel viewInvoiceDateLabel = new JLabel("Date created:", JLabel.TRAILING);
        constraint.gridx = 4;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        invoiceViewEastPanel.add(viewInvoiceDateLabel,constraint);  
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        JLabel viewInvoiceDateDsiplayLabel = new JLabel(sdf.format(invoice.getDate())+"", JLabel.TRAILING);
        constraint.gridx = 5;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        invoiceViewEastPanel.add(viewInvoiceDateDsiplayLabel,constraint);
        
        // Label & ComboBox to display Invoice total amount
        JLabel viewInvoiceAmountLabel = new JLabel(	"Total amount (€):", JLabel.TRAILING);
        constraint.gridx = 4;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        invoiceViewEastPanel.add(viewInvoiceAmountLabel,constraint);  
        
        JLabel viewInvoiceAmountDsiplayLabel = new JLabel(invoice.calculateTotalRetailValue()+"", JLabel.TRAILING);
        constraint.gridx = 5;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        invoiceViewEastPanel.add(viewInvoiceAmountDsiplayLabel,constraint);

        invoiceViewFormPanel.add(invoiceViewWestPanel, BorderLayout.WEST);
        invoiceViewFormPanel.add(invoiceViewEastPanel, BorderLayout.EAST);

        
        JPanel invoiceBasketPanel = new JPanel(new BorderLayout()); 
        JLabel basketLabel = new JLabel("Product Basket"); 
          
        // Populate invoiceBasket with the products from temporary invoice object
        for(Product product : invoice.getProducts()){
        	Product tempProd = new Product(product); 
            NewUI.invoiceBasket.add(tempProd); 
        }        
        
        //create JTable for bottom Panel load test data for gui design  
        DefaultTableModel dtm = new DefaultTableModel()
        {
      	  public boolean isCellEditable(int row, int column)
      	  {
      		  return false;//This causes all cells to be not editable
      	  }
        };
        dtm.addColumn("Product SKU"); 
        dtm.addColumn("Product Name"); 
        dtm.addColumn("Quantity"); 
        for (Product product : NewUI.invoiceBasket){ 
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
        invoiceBasketPanel.add(basketLabel, BorderLayout.NORTH); 
        invoiceBasketPanel.add(new JScrollPane(invoiceBasketTable), BorderLayout.CENTER); 
  
        JButton invoiceViewBackButton = new JButton("Back");  
        invoiceViewBackButton.setActionCommand(invoiceScreenAccess); 
        invoiceViewBackButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUI_InvoiceScreen invoiceScreen = new GUI_InvoiceScreen();
            	invoiceScreen.invoiceScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand());
                NewUI.currentActiveScreen=e.getActionCommand();
                
                NewUI.invoiceBasket.clear();
            } 
         });
        buttonPanel.add(invoiceViewBackButton); 

        /*
         * Delete button: When edit is pressed, code cycles through customers, finds the one with the corresponding ID 
         * to what's selected in the table (selectedCustomerID) and deletes the customer accordingly
         */
        //If current logged-in user is not admin, hide delete button
        if (NewUI.currentUser.isAdmin()){
        JButton invoiceViewDeleteButton = new JButton("Delete Invoice");  
        invoiceViewDeleteButton.setActionCommand(invoiceScreenAccess); 
        buttonPanel.add(invoiceViewDeleteButton);
        invoiceViewDeleteButton.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e){    		  
      		  for (Invoice i : NewUI.db.getInvoices()){
      	    	  if (NewUI.selectedInvoiceID==i.getId()){
      	    		  NewUI.db.getInvoices().remove(i);
      	    		  NewUI.currentActiveScreen=e.getActionCommand();
      	    		  
      	    		  NewUI.selectedInvoiceID=0;
      	    		  
      	    		  GUI_InvoiceScreen invoiceScreen = new GUI_InvoiceScreen();
      	    		  invoiceScreen.invoiceScreen();
                	  CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                	  cl.show(NewUI.gui, e.getActionCommand()); 
                	  
      	    		  break;
      	    	  }
      	      }
      	  }
        });
        }

        botJP.add(invoiceViewFormPanel,BorderLayout.CENTER); 
        botJP.add(invoiceBasketPanel,BorderLayout.SOUTH); 

        
// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        
		NewUI.invoiceViewScreen = screen;
		NewUI.gui.add(NewUI.invoiceViewScreen,invoiceViewScreenAccess);
// FOOTER 
    }

}

