package Sprint2_Day6;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
	        createuserForm.setLayout(new GridBagLayout());
	        GridBagConstraints constraint = new GridBagConstraints(); 
	            
	        TitledBorder nameBorder = BorderFactory.createTitledBorder("User Details");  
	        createuserForm.setBorder(nameBorder); // needs to be removed 
	        
	        
	      //Creating label for User Name
	        JLabel userCreateNameLabel = new JLabel("User Name ",JLabel.TRAILING); 
	        constraint.fill = GridBagConstraints.HORIZONTAL;
	        constraint.weightx = 0.5;
	        constraint.gridx = 0;
	        constraint.gridy = 0;
	        constraint.insets = new Insets(10, 5, 10, 10);
	        constraint.anchor = GridBagConstraints.EAST;
	        createuserForm.add(userCreateNameLabel,constraint);     
	      
	        //Creating editable text field  to Enter User Name
	        final JTextField userCreateNameText = new JTextField(10);  
	        constraint.fill = GridBagConstraints.HORIZONTAL;
	        constraint.weightx = 0.5;
	        constraint.gridx = 1;
	        constraint.gridy = 0;
	        constraint.insets = new Insets(10, 5, 10, 10);
	        constraint.anchor = GridBagConstraints.WEST;
	        createuserForm.add(userCreateNameText,constraint); 
	       
	        //Creating label for password
	        JLabel userCreatePassordLabel = new JLabel("Password ", JLabel.TRAILING);
	        constraint.fill = GridBagConstraints.HORIZONTAL;
	        constraint.weightx = 0.5;
	        constraint.gridx = 0;
	        constraint.gridy = 1;
	        constraint.insets = new Insets(10, 5, 10, 10);
	        constraint.anchor = GridBagConstraints.EAST;
	        createuserForm.add(userCreatePassordLabel,constraint);     
	      
	        //Creating password text field to enter password
	        final JPasswordField userCreatePassword = new JPasswordField(10);
	        constraint.fill = GridBagConstraints.HORIZONTAL;
	        constraint.weightx = 0.5;
	        constraint.gridx = 1;
	        constraint.gridy = 1;
	        constraint.insets = new Insets(10, 5, 10, 10);
	        constraint.anchor = GridBagConstraints.WEST;
	        createuserForm.add(userCreatePassword,constraint); 
	         
	        //Creating label  password confirmation
	        JLabel userCreateConfirmPasswordlabel = new JLabel("Confirm Password ",JLabel.TRAILING);
	        constraint.fill = GridBagConstraints.HORIZONTAL;
	        constraint.weightx = 0.5;
	        constraint.gridx = 0;
	        constraint.gridy = 2;
	        constraint.insets = new Insets(10, 5, 10, 10);
	        constraint.anchor = GridBagConstraints.EAST;
	        createuserForm.add(userCreateConfirmPasswordlabel,constraint);     
	      
	        //Creating password  text field  to enter confirm password
	        final JPasswordField userCreateConfirmPassword = new JPasswordField(10);
	        constraint.fill = GridBagConstraints.HORIZONTAL;
	        constraint.weightx = 0.5;
	        constraint.gridx = 1;
	        constraint.gridy = 2;
	        constraint.insets = new Insets(10, 5, 10, 10);
	        constraint.anchor = GridBagConstraints.WEST;
	        createuserForm.add(userCreateConfirmPassword,constraint); 
	        
	          
	        //Creating Check box for Administrator privileges
	        final JCheckBox IsAdmincheckBox = new JCheckBox();
	        IsAdmincheckBox.setText("Administrator");
	        IsAdmincheckBox.setSelected(false);
	        constraint.gridx = 0;
	        constraint.gridy = 3;
	        constraint.insets = new Insets(10, 5, 10, 10);
	        constraint.anchor = GridBagConstraints.CENTER;
	        createuserForm.add(IsAdmincheckBox,constraint); 
	       
	        //Creating Check box for Active User marker
	        final JCheckBox IsActivecheckBox = new JCheckBox();
	        IsActivecheckBox.setText("Active Account");
	        IsActivecheckBox.setSelected(false);
	        constraint.gridx = 0;
	        constraint.gridy = 4;
	        constraint.insets = new Insets(10, 5, 10, 10);
	        constraint.anchor = GridBagConstraints.CENTER;
	        createuserForm.add(IsActivecheckBox,constraint); 
	        

	        
	        
	       
	   
	        
	          
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
