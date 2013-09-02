package Sprint2_Day6;

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
 
public class GUI_ProductScreen {
	
    private String productScreenAccess = "product",  
            homeScreenAccess = "home",  
            productCreateScreenAccess = "productCreate",  
            productEditScreenAccess = "productEdit"; 
	
    public void productScreen(){ 
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
        JLabel titlelbl = new JLabel("Manage Products", JLabel.CENTER);   
        titlelbl.setFont(NewUI.topBannerFont);   
        topJP.add(titlelbl); 
        // HEADER        
  
                  
        /* 
         *  Screen specific code goes here        
         */
             
                NewUI.selectedProductID = 0;
                
                //create JTable - DefaultTableModel easy way to populate JTable
                DefaultTableModel productsTableModel = new DefaultTableModel()
                {
              	  public boolean isCellEditable(int row, int column)
              	  {
              		  return false;//This causes all cells to be not editable
              	  }
                };
                productsTableModel.addColumn("Product SKU");    
                productsTableModel.addColumn("Product Name");    
                productsTableModel.addColumn("In Stock");    
                productsTableModel.addColumn("Supplier");    
                      
                //Populating the products table - using vectors because they are compatible with tables   
                for (Product p : NewUI.db.getProducts()){    
                    Vector<String> singleOrder = new Vector<String>();    
                    singleOrder.add(Integer.toString(p.getSku()));   
                    singleOrder.add(p.getName());    
                    singleOrder.add(Integer.toString(p.getQuantity()));    
                    singleOrder.add(p.getSupplier().getName());    
                    productsTableModel.addRow(singleOrder);        
                }     
                final JTable productsTable = new JTable();  
                productsTable.setModel(productsTableModel);         
                      
                
                //Get the selected product ID from when the table is clicked
                productsTable.addMouseListener(new MouseAdapter() {
              	  public void mouseClicked(MouseEvent e) {    
              		  NewUI.selectedProductID = Integer.parseInt(productsTable.getValueAt(productsTable.getSelectedRow(),0).toString());
              	  }
                });
                //Get the selected products ID from when the keyboard is clicked
                productsTable.addKeyListener(new KeyListener(){
              	  @Override
              	  public void keyPressed(KeyEvent e){ 

              	  }
              	  public void keyReleased(KeyEvent e) {	
              		  NewUI.selectedProductID = Integer.parseInt(productsTable.getValueAt(productsTable.getSelectedRow(),0).toString());
              	  }
              	  public void keyTyped(KeyEvent e) {

              	  }
                });
                
                //Make Create button and set action listener   
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
                      
                     
                //Make Edit button and set action listener   
                JButton productEditButton = new JButton("View Product");     
                productEditButton.setActionCommand(productEditScreenAccess); 
                productEditButton.addActionListener(new ActionListener(){  
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	if (NewUI.check.isAProduct(NewUI.selectedProductID, NewUI.db.getProducts())){
                  			NewUI.currentActiveScreen = e.getActionCommand();
                  			NewUI.productsLine = productsTable.getSelectedRow(); 
                  			  
                  			GUI_ProductViewScreen productViewScreen = new GUI_ProductViewScreen();
            				productViewScreen.productViewScreen(); 
            				
            				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
            				cl.show(NewUI.gui, e.getActionCommand()); 
                  		}
                    }  
                });             
                buttonPanel.add(productEditButton);     
               
                //Make Create button and set action listener   
                //If current logged-in user is not admin, hide add product
                if (NewUI.currentUser.isAdmin()){
                JButton productCreateButton = new JButton("Add New Product"); 
                productCreateButton.setActionCommand(productCreateScreenAccess); 
                productCreateButton.addActionListener(new ActionListener(){  
                    @Override
                    public void actionPerformed(ActionEvent e) { 
                        GUI_ProductCreateScreen productCreateScreen = new GUI_ProductCreateScreen(); 
                        productCreateScreen.productCreateScreen("Add Product"); 
                          
                        CardLayout cl = (CardLayout)(NewUI.gui.getLayout()); 
                        cl.show(NewUI.gui, e.getActionCommand()); 
                    }  
                });         
                buttonPanel.add(productCreateButton); 
                }
                
                //Set botJP content   
                botJP.add(new JLabel("All Products"), BorderLayout.NORTH);     
                botJP.add(new JScrollPane(productsTable),BorderLayout.CENTER); 
                  
                Vector<Vector<String>> wholeTable = productsTableModel.getDataVector();  
                Vector<String> tableLine = wholeTable.get(0);  
  
                  
        // FOOTER  
                // Adds the top, bottom and button panels to the screen panel 
                screen.add(topJP,BorderLayout.NORTH);   
                screen.add(botJP,BorderLayout.CENTER);   
                screen.add(buttonPanel,BorderLayout.SOUTH); 
                  
                // Assigns the MainUI gui panel the contents of the screen panel 
                NewUI.productScreen = screen; 
                NewUI.gui.add(NewUI.productScreen,productScreenAccess); 
        // FOOTER  
            } 
}
