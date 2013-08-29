package Sprint2_Day3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class GUI_UserCreateScreen {
	  private String userScreenAccess = "user",  userCreateScreenAccess = "userCreate"; 
	  
	    public void userCreateScreen(String screenName){ 
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
	        JLabel titlelbl = new JLabel(screenName, JLabel.CENTER);   
	        titlelbl.setFont(new Font("Arial",2 , 48));   
	        topJP.add(titlelbl); 
	// HEADER        
	  
	          
	/* 
	 *  Screen specific code goes here        
	 */
	          
	        JPanel createuserForm = new JPanel();   
	        createuserForm.setLayout(new GridLayout(10,1,3,3));  
	            
	        TitledBorder nameBorder = BorderFactory.createTitledBorder("user Details");  
	        createuserForm.setBorder(nameBorder); // needs to be removed 
	            
	        //create JPanel row1 and add components  
	        //row1 to hold user name label and textfield value  
	        JPanel row1 = new JPanel(); 
	        JLabel createuserNameLabel = new JLabel("User Name", JLabel.TRAILING);  
	        row1.add(createuserNameLabel); 
	        JLabel creatuserPassword = new JLabel("Password", JLabel.TRAILING);  
	        row1.add(creatuserPassword); 
	          
	          
//	      create JPanel row2 and add components  
//	        row2 to hold ....... 
	        JPanel row2 = new JPanel(); 
	        JLabel userCreateSupplierLabel = new JLabel("Supplier: ");  
	        row1.add(userCreateSupplierLabel); 
       
	          
//	        row2.add(userCreateSupplierLabel); 
//	        row2.add(userCreateSupplierCombo); 
	          
	        //create JPanel row3 and add components  
	        //row3 to hold userName label and userTextField 
	        JPanel row3 = new JPanel(); 
	        JLabel userCreateNameLabel = new JLabel("user Name: ");  
	    //    row3.add(userCreateNameLabel); 
	        final JTextField userCreateNameText = new JTextField(10);  
	    //    row3.add(userCreateNameText); 
	         
	        //create JPanel row4 and add components  
	        //row4 to hold wholesalePrice label and wholeSaleTextField & retailprice label and textFields 
	        JPanel row4 = new JPanel(); 
	        row4.setLayout(new FlowLayout(FlowLayout.CENTER)); 
	        row2.add(userCreateNameLabel); 
	        row2.add(userCreateNameText); 
	        JLabel userCreateWholesaleLabel = new JLabel("Wholesale Price:  €");   
	        row4.add(userCreateWholesaleLabel); 
	        final JTextField userCreateWholesaleText = new JTextField(5);  
	        row4.add(userCreateWholesaleText); 
	        JLabel userCreateRetailLabel = new JLabel("       Retail Price:  €");   
	          
	        final JTextField userCreateRetailText = new JTextField(5);  
	         
	          
	       JPanel row5 = new JPanel(); 
	       row5.setLayout(new FlowLayout(FlowLayout.CENTER)); 
	       row5.add(userCreateRetailLabel); 
	       row5.add(userCreateRetailText); 
	   
	        createuserForm.add(row1); 
	        createuserForm.add(row2); 
	        createuserForm.add(row3); 
	        createuserForm.add(row4); 
	        createuserForm.add(row5); 
	          
	        botJP.add(createuserForm); 
	              
	  
	        //Back button   
	        JButton backButton = new JButton("Cancel");   
	        backButton.setToolTipText("Cancels new user and returns to user screen");  
	        backButton.setActionCommand(userScreenAccess);   
	        backButton.addActionListener(new ActionListener(){  
	            @Override
	            public void actionPerformed(ActionEvent e) { 
	            	GUI_UserCreateScreen userCreateScreen = new GUI_UserCreateScreen(); 
	            	userCreateScreen.userCreateScreen(null);  
	                  
	                CardLayout cl = (CardLayout)(NewUI.gui.getLayout());        
	                cl.show(NewUI.gui, userScreenAccess);  
	            }  
	        });  
	          
	        buttonPanel.add(backButton);   
	      
	        //Add button        
	        final JButton userCreateAddButton = new JButton("Add");   
	        userCreateAddButton.setActionCommand(userScreenAccess);   
	        userCreateAddButton.addActionListener(new ActionListener() { //Create user from fields   
	            public void actionPerformed(ActionEvent e){   
	                if (NewUI.check.isNotBlank(userCreateNameText.getText()) && NewUI.check.isPositiveNumeric(userCreateWholesaleText.getText())    
	                        && NewUI.check.isPositiveNumeric(userCreateRetailText.getText())){   
	                    NewUI.db.createNewUser(userCreateNameText.getText(), "test", false, false);    
	                           // Double.parseDouble(userCreateRetailText.getText()),NewUI.db.getSuppliers().get(userCreateSupplierCombo.getSelectedIndex()));   
	                          
	                    CardLayout cl = (CardLayout)(NewUI.gui.getLayout());    
	                    cl.show(NewUI.gui, e.getActionCommand());   
	                    NewUI.currentActiveScreen=e.getActionCommand();   
//	              refresh();   
	                }   
	      
	            }   
	        });      
	        buttonPanel.add(userCreateAddButton); 
	          
	// FOOTER  
	        // Adds the top, bottom and button panels to the screen panel 
	        screen.add(topJP,BorderLayout.NORTH);   
	        screen.add(botJP,BorderLayout.CENTER);   
	        screen.add(buttonPanel,BorderLayout.SOUTH); 
	          
	        // Assigns the MainUI gui panel the contents of the screen panel 
	        NewUI.userCreateScreen = screen; 
	        NewUI.gui.add(NewUI.userCreateScreen,userCreateScreenAccess); 

	// FOOTER  
	    } 
	

}
