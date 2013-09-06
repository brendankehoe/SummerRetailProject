package sprint2_Day10;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI_SupplierViewScreen {
	
	private String supplierScreenAccess = "supplier", supplierViewScreenAccess = "supplierView";
	

	public void supplierViewScreen(){
// HEADER
        // Create screen panel that is used to replace the gui panel from MainUI.class 
        JPanel screen = new JPanel(new BorderLayout()); 
        screen.setOpaque(true);  //content panes must be opaque  
          
        // Creates three panels used for the top portion, bottom portion and button portion of the screen 
        JPanel topJP = new JPanel();     
        topJP.setBackground(NewUI.topBannerColor);
        JPanel botJP =  new JPanel();  
        botJP.setLayout(new BoxLayout(botJP,BoxLayout.Y_AXIS));
        JPanel buttonPanel = new JPanel(new FlowLayout());  

        // Create the title of the screen in the top panel 
        JLabel titlelbl = new JLabel("View Supplier", JLabel.CENTER);   
        titlelbl.setFont(NewUI.topBannerFont);   
        topJP.add(titlelbl); 
// HEADER       

        
/*
 *	Screen specific code goes here       
 */
      //Declare panels and layouts


        JPanel middleDetailsJP = new JPanel(new GridBagLayout());
        JPanel middleProductsJP =  new JPanel(new BorderLayout());
      

        //************Supplier Details Panel*************************
        //Need to initiate supplier as it is not defined until inside a loop
        Supplier supplier = new Supplier();
        
        
        GridBagConstraints constraint = new GridBagConstraints();
        
        //Retrieving the correct supplier from the selectedSupplierID from the table. 
        for (Supplier c : NewUI.db.getSuppliers()){
      	  if (NewUI.selectedSupplierID == c.getId()){
      		  supplier=c;
      	  }
        }
        

        //Putting supplier details into the text fields
        JLabel nameLabel = new JLabel("Name: ", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        middleDetailsJP.add(nameLabel,constraint);
        
        final JTextField nameText = new JTextField();
        nameText.setText(supplier.getName());
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        middleDetailsJP.add(nameText,constraint);
        
        JLabel emailLabel = new JLabel("Email: ", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        middleDetailsJP.add(emailLabel,constraint);
        
        final JTextField emailText = new JTextField();
        emailText.setText(supplier.getEmail());
        emailText.setMinimumSize(new Dimension(30,30));
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        middleDetailsJP.add(emailText,constraint);
        
        JLabel phoneLabel = new JLabel("Phone number: ", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        middleDetailsJP.add(phoneLabel,constraint);
        
        final JTextField phoneText = new JTextField();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        phoneText.setText(supplier.getPhoneNumber());
        middleDetailsJP.add(phoneText,constraint);

        if (!NewUI.currentUser.isAdmin()){
        	nameText.setEditable(false);
        	emailText.setEditable(false);
        	phoneText.setEditable(false);
        }
        /*
         * Edit button: When edit is pressed, code cycles through suppliers, finds the one with the corresponding ID 
         * to what's selected in the table (selectedSupplierID) and edits the details according to the text fields
         */
        if (NewUI.currentUser.isAdmin()){
        	JButton editButton = new JButton("Edit");
        	constraint.fill = GridBagConstraints.HORIZONTAL;
        	constraint.weightx = 0.5;
        	constraint.gridx = 2;
        	constraint.gridy = 2;
        	constraint.insets = new Insets(10, 5, 10, 10);
        	constraint.anchor = GridBagConstraints.WEST;
        	middleDetailsJP.add(editButton,constraint);
        	editButton.setActionCommand(supplierScreenAccess);  
        	editButton.addActionListener(new ActionListener() { 
        		public void actionPerformed(ActionEvent e){
        			for (Supplier s : NewUI.db.getSuppliers()){
        				if (NewUI.selectedSupplierID == s.getId()){
        					if (NewUI.check.isNotBlank(nameText.getText()) && NewUI.check.isNotBlank(emailText.getText())
        							&& NewUI.check.isNotBlank(phoneText.getText())){
        						s.setName(nameText.getText());
        						s.setEmail(emailText.getText());
        						s.setPhoneNumber(phoneText.getText());
        						NewUI.currentActiveScreen=e.getActionCommand();

        						GUI_SupplierScreen supplierScreen = new GUI_SupplierScreen();
        						supplierScreen.supplierScreen(); 
        						NewUI.selectedSupplierID=0;
        						supplierViewScreenRefresh();
        						break;
        					}
        				}
        			}
        		}
        	});
        }
        botJP.add(middleDetailsJP);
        
        //************Products Panel*************************

        DefaultTableModel model = new DefaultTableModel()
        {
      	  public boolean isCellEditable(int row, int column)
      	  {
      		  return false;//This causes all cells to be not editable
      	  }
        };
        model.addColumn("ID"); 
        model.addColumn("Name");
        model.addColumn("Wholesale Price");
        model.addColumn("In Stock");

        
        //Populate rows with product data    
        for (Product p : supplier.getProducts()){ 
      	  Vector<String> singleProduct = new Vector<String>(); 
      	singleProduct.add(Integer.toString(p.getSku()));  
      	singleProduct.add(p.getName());  
      	singleProduct.add(Double.toString(p.getWholesalePrice()));
      	singleProduct.add(Integer.toString(p.getQuantity())); 
      	  model.addRow(singleProduct);     
        }      
        final JTable productTable = new JTable();
        productTable.setAutoCreateRowSorter(true);
        productTable.setModel(model);
        middleProductsJP.setLayout(new BorderLayout());
        
		// double click to get more information
        productTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					NewUI.selectedProductID = Integer.parseInt(productTable
							.getValueAt(productTable.getSelectedRow(), 0)
							.toString());

					GUI_ProductViewScreen gUI_ProductViewScreen = new GUI_ProductViewScreen();
					gUI_ProductViewScreen.productViewScreen();

					CardLayout cl = (CardLayout) (NewUI.gui.getLayout());
					cl.show(NewUI.gui, "productEdit");
				}
			}
		});
        
        //Populate invoices panel with labels and table
        middleProductsJP.add(new JLabel("Products:"), BorderLayout.NORTH);
        middleProductsJP.add(new JScrollPane(productTable), BorderLayout.CENTER);
        botJP.add(middleProductsJP);
        
        
        //************Bottom Panel*************************
        
        //Create back button and set action listener
        JButton backButton = new JButton("Back");  
        backButton.setActionCommand(supplierScreenAccess);  
        backButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                NewUI.currentActiveScreen = e.getActionCommand();

                GUI_SupplierScreen supplierScreen = new GUI_SupplierScreen();
            	supplierScreen.supplierScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand()); 
            } 
        }); 
        buttonPanel.add(backButton);

        /*
         * Delete button: When edit is pressed, code cycles through suppliers, finds the one with the corresponding ID 
         * to what's selected in the table (selectedSupplierID) and deletes the supplier accordingly
         */
        if (NewUI.currentUser.isAdmin()){
        	JButton deleteButton = new JButton("Delete Supplier");  
        	deleteButton.setActionCommand(supplierScreenAccess); 
        	buttonPanel.add(deleteButton);
        	deleteButton.addActionListener(new ActionListener() { 
        		public void actionPerformed(ActionEvent e){    		  
        			for (Supplier s : NewUI.db.getSuppliers()){
        				if (NewUI.selectedSupplierID==s.getId()){
        					NewUI.db.getSuppliers().remove(s);
        					NewUI.currentActiveScreen=e.getActionCommand();

        					for(Product p : NewUI.db.getProducts()){
        						if(NewUI.selectedSupplierID == p.getSupplier().getId()){
        							String oldName = p.getSupplier().getName();
        							p.getSupplier().setName(oldName+" <Deleted>");
        							break;
        						}
        					}      	                         
        					NewUI.selectedSupplierID=0;

        					GUI_SupplierScreen supplierScreen = new GUI_SupplierScreen();
        					supplierScreen.supplierScreen();
        					CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
        					cl.show(NewUI.gui, e.getActionCommand()); 

        					break;
        				}
        			}
        		}
        	});
        }


        
// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        NewUI.supplierViewScreen = screen;
        NewUI.gui.add(NewUI.supplierViewScreen,supplierViewScreenAccess);
// FOOTER 
    }
	
	
    public void supplierViewScreenRefresh(){ 
        NewUI.supplierViewScreen.removeAll(); 
        supplierViewScreen();
        
        CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
        cl.show(NewUI.gui, NewUI.currentActiveScreen); 
    } 


}
