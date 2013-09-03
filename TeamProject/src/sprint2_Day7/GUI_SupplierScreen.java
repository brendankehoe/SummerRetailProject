package sprint2_Day7;

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
import java.util.Vector; 
  

import javax.swing.BorderFactory; 
import javax.swing.JButton; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import javax.swing.table.DefaultTableModel; 
  
  
  
public class GUI_SupplierScreen { 
  
	public String   homeScreenAccess = "home", 
			supplierScreenAccess = "supplier" , 
			supplierCreateScreenAccess = "supplierCreate", supplierViewScreenAccess = "supplierView"; 
	Check check =  new Check();

    public void supplierScreen(){ 
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
                JLabel titlelbl = new JLabel("Manage Suppliers", JLabel.CENTER);   
                titlelbl.setFont(NewUI.topBannerFont);   
                topJP.add(titlelbl); 
        // HEADER        
  
                  
        /* 
         *  Screen specific code goes here        
         */
         
	            //create Table for bottom panel. using DefaultTableModel as an easy way to make a table  
                DefaultTableModel supplierTableModel = new DefaultTableModel(){
                	  public boolean isCellEditable(int row, int column)
                  	  {
                  		  return false;//This causes all cells to be not editable
                  	  }
                };   
                supplierTableModel.addColumn("ID");   
                supplierTableModel.addColumn("Name");
                supplierTableModel.addColumn("Email");
	          
	            /*  
	             * Populating the suppliers table - Loop through suppliers ArrayList and all each ID and name to vector  
	             * Using vectors because they are compatible with tables whereas ArrayLists are not  
	             */
	            for (Supplier s : NewUI.db.getSuppliers()){   
	                Vector<String> singleSupplier = new Vector<String>();   
	                singleSupplier.add(Integer.toString(s.getId()));    
	                singleSupplier.add(s.getName());   
	                singleSupplier.add(s.getEmail()); 
	                supplierTableModel.addRow(singleSupplier);       
	            }    
	            final JTable supplierTable = new JTable();    
	            supplierTable.setModel(supplierTableModel);
	            
	            //Reset the selected supplier
	            NewUI.selectedSupplierID=-1;
	            
	            //Get the selected customer ID from when the table is clicked
	            supplierTable.addMouseListener(new MouseAdapter() {
	          	  public void mouseClicked(MouseEvent e) {    
	          		  NewUI.selectedSupplierID = Integer.parseInt(supplierTable.getValueAt(supplierTable.getSelectedRow(),0).toString());
	          	  }
	            });
	            //Get the selected customer ID from when the keyboard is clicked
	            supplierTable.addKeyListener(new KeyListener(){
	          	  @Override
	          	  public void keyPressed(KeyEvent e){ 

	          	  }
	          	  public void keyReleased(KeyEvent e) {	
	          		  NewUI.selectedSupplierID = Integer.parseInt(supplierTable.getValueAt(supplierTable.getSelectedRow(),0).toString());
	          	  }
	          	  public void keyTyped(KeyEvent e) {

	          	  }
	            });
	            
        		// double click to get more information
	            supplierTable.addMouseListener(new MouseAdapter() {
        			public void mouseClicked(MouseEvent e) {
        				if (e.getClickCount() == 2) {
        					NewUI.selectedSupplierID = Integer.parseInt( supplierTable
        							.getValueAt( supplierTable.getSelectedRow(), 0 )
        							.toString() );

        					GUI_SupplierViewScreen gui_SupplierViewScreen = new GUI_SupplierViewScreen();
        					gui_SupplierViewScreen.supplierViewScreen();

        					CardLayout cl = (CardLayout) (NewUI.gui.getLayout());
        					cl.show(NewUI.gui, "supplierView");
        				}
        			}
        		});
	          
	            //Create back button and set action listener  
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
	            
	            //Create View Supplier button and set action listener   
	            JButton viewButton = new JButton("View Supplier");    
	            viewButton.setActionCommand(supplierViewScreenAccess);    
	            viewButton.addActionListener(new ActionListener(){  
	                @Override
	                public void actionPerformed(ActionEvent e) {

	                	if (check.isASupplier(NewUI.selectedSupplierID,
	                			NewUI.db.getSuppliers())){

		                    GUI_SupplierViewScreen supplierViewScreen = new GUI_SupplierViewScreen(); 
		                    supplierViewScreen.supplierViewScreen(); 
		                      
		                    CardLayout cl = (CardLayout)(NewUI.gui.getLayout()); 
		                    cl.show(NewUI.gui, e.getActionCommand()); 
	                	}

	                }  
	            });            
	            buttonPanel.add(viewButton); 
	            
	            if (NewUI.currentUser.isAdmin()){
		            //Create New Supplier button and set action listener   
		            JButton createButton = new JButton("Add New Supplier");    
		            createButton.setActionCommand(supplierCreateScreenAccess);    
		            createButton.addActionListener(new ActionListener(){  
		                @Override
		                public void actionPerformed(ActionEvent e) { 
		                    GUI_SupplierCreateScreen supplierCreateScreen = new GUI_SupplierCreateScreen(); 
		                    supplierCreateScreen.supplierCreateScreen(); 
		                      
		                    CardLayout cl = (CardLayout)(NewUI.gui.getLayout()); 
		                    cl.show(NewUI.gui, e.getActionCommand()); 
		                }  
		            });            
		            buttonPanel.add(createButton); 
	            }
	                
    
	          
	            //Set inner panel content  
	            topJP.add(titlelbl);  
	            botJP.add(new JLabel("All Suppliers"), BorderLayout.NORTH);    
	            botJP.add(new JScrollPane(supplierTable),BorderLayout.CENTER);    
                 
        // FOOTER  
                // Adds the top, bottom and button panels to the screen panel 
                screen.add(topJP,BorderLayout.NORTH);   
                screen.add(botJP,BorderLayout.CENTER);   
                screen.add(buttonPanel,BorderLayout.SOUTH); 
                  
                // Assigns the MainUI gui panel the contents of the screen panel 
                
                NewUI.supplierScreen = screen; 
                NewUI.gui.add(NewUI.supplierScreen,supplierScreenAccess); 
  
                  
        // FOOTER  
            } 
  
  
} 