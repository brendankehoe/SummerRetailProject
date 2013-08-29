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
import javax.swing.JCheckBox;
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
	            
	        TitledBorder nameBorder = BorderFactory.createTitledBorder("User Details");  
	        createuserForm.setBorder(nameBorder); // needs to be removed 
	            
	        //create JPanel row1 and add components  
	        //row1 to hold user name label and textfield value  
	        JPanel row1 = new JPanel(); 
//	       
	          
	          
//	      create JPanel row2 and add components  
//	        row2 to hold ....... 
	        JPanel row2 = new JPanel(); 
	       
	        
       
	          
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
	        JLabel userCreatePassordLabel = new JLabel("Password ");   
	        row4.add(userCreatePassordLabel); 
	        final JTextField userCreatePassword = new JTextField(10);  
	        row4.add(userCreatePassword); 
	        JLabel userCreateConfirmPasswordlabel = new JLabel("Confirm Password");   
	          
	        final JTextField userCreateConfirmPassword = new JTextField(10);  
	        
	        final JCheckBox IsAdmincheckBox = new JCheckBox();
	        IsAdmincheckBox.setText("Administrator");
	        IsAdmincheckBox.setSelected(false);
	        final JCheckBox IsActivecheckBox = new JCheckBox();
	        IsActivecheckBox.setText("Active Account");
	        IsActivecheckBox.setSelected(false);
	        

	        
	        
	          
	       JPanel row5 = new JPanel(); 
	       row5.setLayout(new FlowLayout(FlowLayout.CENTER)); 
	       row5.add(userCreateConfirmPasswordlabel); 
	       row5.add(userCreateConfirmPassword); 
	       
	       JPanel row6 = new JPanel();
	       row6.setLayout(new FlowLayout(FlowLayout.CENTER));
	       row6.add(IsActivecheckBox);
	       row6.add(IsAdmincheckBox);
	   
	        createuserForm.add(row1); 
	        createuserForm.add(row2); 
	        createuserForm.add(row3); 
	        createuserForm.add(row4); 
	        createuserForm.add(row5); 
	        createuserForm.add(row6);
	          
	        botJP.add(createuserForm); 
	        //check password matches confirm password
	              // && userCreatePassword.getText().equals(userCreateConfirmPassword.getText())
	  
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
	                if (NewUI.check.isNotBlank(userCreateNameText.getText()) && NewUI.check.isNotBlank(userCreateNameText.getText())   
	                        && NewUI.check.isNotBlank(userCreatePassword.getText()) && NewUI.check.confirmPasswordMatch(userCreatePassword.getText(), userCreateConfirmPassword.getText())){   
	                    NewUI.db.createNewUser(userCreateNameText.getText(), userCreatePassword.getText(),IsAdmincheckBox.isSelected() , IsActivecheckBox.isSelected());    
	                         
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
