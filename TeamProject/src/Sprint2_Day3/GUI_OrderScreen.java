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



public class GUI_OrderScreen {
	
	private String homeScreenAccess = "home", orderScreenAccess = "order", orderCreateScreenAccess = "orderCreate", orderEditScreenAccess = "orderEdit";
	
	public void orderScreen(){
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
        JLabel titlelbl = new JLabel("Manage Orders", JLabel.CENTER);  
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
         dtm.addColumn("Order ID");  
         dtm.addColumn("Supplier");  
         dtm.addColumn("Total Cost (€)");  
         for (Order order : NewUI.db.getOrders()){  
             Vector<String> singleOrder = new Vector<String>();  
             singleOrder.add(Integer.toString(order.getId()));  
             singleOrder.add(order.getSupplier().getName());  
             singleOrder.add(Double.toString(order.calculateTotalWholesaleValue())); 
             dtm.addRow(singleOrder);      
         }   
         JTable ordersTable = new JTable();  
  
         ordersTable.setModel(dtm);  
     
     
         JLabel orderslabel = new JLabel("All Orders");   
     

         JButton orderBackButton = new JButton("Back"); 
         orderBackButton.setActionCommand(homeScreenAccess);   
         orderBackButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUI_HomeScreen homeScreen = new GUI_HomeScreen();
            	homeScreen.homeScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand()); 
            } 
         });      
         buttonPanel.add(orderBackButton);   
         
/*       Complete in Sprint 2
 *   
         JButton orderEditButton = new JButton("Edit Order");   
         orderEditButton.setActionCommand(orderEditScreenAccess);   
         orderEditButton.addActionListener(new GUIListener());   
         buttonPanel.add(orderEditButton); 
*/ 
           
         JButton orderCreateButton = new JButton("Create Order");
         orderCreateButton.setActionCommand(orderCreateScreenAccess);   
         orderCreateButton.addActionListener(new ActionListener(){ 
             @Override
             public void actionPerformed(ActionEvent e) {
             	GUI_OrderCreateScreen orderCreateScreen = new GUI_OrderCreateScreen();
             	orderCreateScreen.orderCreateScreen(); 
             	
             	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand());
                
                NewUI.currentActiveScreen = e.getActionCommand();
             } 
          });   
         buttonPanel.add(orderCreateButton);
         
         botJP.setLayout(new BorderLayout());   
         botJP.add(orderslabel, BorderLayout.NORTH);   
         botJP.add(new JScrollPane(ordersTable),BorderLayout.CENTER); 

        
// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        
        NewUI.orderScreen = screen;
        NewUI.gui.add(NewUI.orderScreen,orderScreenAccess);
// FOOTER 
    }

}
