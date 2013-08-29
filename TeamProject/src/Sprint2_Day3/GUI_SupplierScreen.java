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
  
  
  
public class GUI_SupplierScreen { 
  
    public String   homeScreenAccess = "home", 
                    supplierScreenAccess = "supplier" , 
                    supplierCreateScreenAccess = "supplierCreate"; 
              
      
      
    public void supplierScreen(){ 
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
                JLabel titlelbl = new JLabel("Manage Supplier", JLabel.CENTER);   
                titlelbl.setFont(new Font("Arial",2 , 48));   
                topJP.add(titlelbl); 
        // HEADER        
  
                  
        /* 
         *  Screen specific code goes here        
         */
        


	          
	            //create Table for bottom panel. using DefaultTableModel as an easy way to make a table  
	            DefaultTableModel dtm = new DefaultTableModel();   
	            dtm.addColumn("Supplier ID");   
	            dtm.addColumn("Supplier Name");  
	          
	            /*  
	             * Populating the suppliers table - Loop through suppliers ArrayList and all each ID and name to vector  
	             * Using vectors because they are compatible with tables whereas ArrayLists are not  
	             */
	            for (Supplier s : NewUI.db.getSuppliers()){   
	                Vector<String> singleSupplier = new Vector<String>();   
	               // singleSupplier.add(Integer.toString(s.getId()));    
	                singleSupplier.add(s.getName());   
	                dtm.addRow(singleSupplier);       
	            }    
	            JTable supplierTable = new JTable();   
	            supplierTable.setEnabled(false);  
	            supplierTable.setModel(dtm);   
	          
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