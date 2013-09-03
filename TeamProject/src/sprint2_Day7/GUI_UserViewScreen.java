package sprint2_Day7;

import java.awt.BorderLayout; 
import java.awt.CardLayout;
import java.awt.Color; 
import java.awt.FlowLayout; 
import java.awt.Font; 
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.util.Date;
import java.util.Vector; 
  
import javax.swing.BorderFactory; 
import javax.swing.BoxLayout;
import javax.swing.JButton; 
import javax.swing.JCheckBox;
import javax.swing.JComboBox; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JTextField; 
import javax.swing.table.DefaultTableModel; 

public class GUI_UserViewScreen {
private String userScreenAccess = "user",userViewScreenAccess = "userView" ; 
    
    public void userViewScreen(){ 
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
        JLabel titlelbl = new JLabel("View User" , JLabel.CENTER);   
        titlelbl.setFont(new Font("Arial",2 , 48));   
        topJP.add(titlelbl); 
// HEADER        
  
          
/* 
 *  Screen specific code goes here        
 */
      //Declare panels and layouts
//      screen = new JPanel();
//      screen.setLayout(new BoxLayout(customerViewScreen,BoxLayout.Y_AXIS))  ;     


      //JPanel middleDetailsJP = new JPanel(new GridBagLayout());
     
        
        //************user Details Panel*************************
        //Need to initiate user as it is not defined until inside a loop
        User user = new User();
        
        //Retrieving the correct user from the selectedUSerID from the table. 
        for (User p : NewUI.db.getUsers()){
      	  if (NewUI.selectedUserID == p.getUserId()){
      		  user=p;
      	  }
        }
        
        //Display and populate user details from selected userID
        JPanel viewUserForm = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
    
        JLabel userNameLabel = new JLabel("User Name",JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        viewUserForm.add(userNameLabel,constraint);
    

        final JTextField userNameText = new JTextField();
        userNameText.setText(user.getUserName());
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        viewUserForm.add(userNameText,constraint);
      
      //Creating label for password
        JLabel userPassordLabel = new JLabel("Password ", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        viewUserForm.add(userPassordLabel,constraint);
    
      
        //Creating password text field to enter password
        final JTextField userPassword = new JTextField(10);
        userPassword.setText(user.getPassword());
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        viewUserForm.add(userPassword,constraint);
     
      
        //Creating check box for Administrator
        final JCheckBox IsAdmincheckBox = new JCheckBox();
        IsAdmincheckBox.setText("Administrator");
        if(user.isAdmin()){
        	IsAdmincheckBox.setSelected(true);
        }
        else{
        	IsAdmincheckBox.setSelected(false);
        } 
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        viewUserForm.add(IsAdmincheckBox,constraint);  
     
     
        //Creating check box for Activate/ Deactivate User
        final JCheckBox IsActivecheckBox = new JCheckBox();
        IsActivecheckBox.setText("Active Account");
        if(user.isActive()){
        	IsActivecheckBox.setSelected(true);
        }
        else{
        	IsActivecheckBox.setSelected(false);
        } 
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        viewUserForm.add(IsActivecheckBox,constraint);  
    
      
        
        /*
         * Edit button: When edit is pressed, code cycles through users, finds the one with the corresponding ID 
         * to what's selected in the table (selectedUserID) and edits the details according to the text fields
         */
     
        JButton userViewEditButton = new JButton("Edit User");
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 2;
        constraint.gridy = 3;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        viewUserForm.add(userViewEditButton,constraint);
        
        userViewEditButton.setActionCommand(userScreenAccess);  
        userViewEditButton.addActionListener(new ActionListener() {  
            // Create user from fields
            public void actionPerformed(ActionEvent e) {
            	for (User p : NewUI.db.getUsers()){
            		if (NewUI.selectedUserID == p.getUserId()){
            			if (NewUI.check.isNotBlank(userNameText.getText()) && NewUI.check.isNotBlank(userPassword.getText())){
            				p.setUserName(userNameText.getText());
        					  p.setPassword(userPassword.getText());
        					  p.setAdmin(IsAdmincheckBox.isSelected());
        					  p.setActive(IsActivecheckBox.isSelected());
        					  NewUI.currentActiveScreen=e.getActionCommand();
        					  
        					  GUI_UserScreen userScreen = new GUI_UserScreen();
        					  userScreen.userScreen(); 
        					  NewUI.selectedCustomerID=0;
        					  userViewScreen();
							 break;
            			}
            		}
            	}
            } 
        }); 
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 2;
        constraint.gridy = 4;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        viewUserForm.add(userViewEditButton,constraint);
        

        JButton userEditBackButton = new JButton("Back");   
        userEditBackButton.setToolTipText("Cancels edit user and returns to user screen");   
        userEditBackButton.setActionCommand(userScreenAccess);  
        userEditBackButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                NewUI.currentActiveScreen = e.getActionCommand();

                GUI_UserScreen userScreen = new GUI_UserScreen();
            	userScreen.userScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand()); 
            } 
        });    
        buttonPanel.add(userEditBackButton);  
        // userEditBackButton.setSize(width, height)  
    
    
        // Delete button
        JButton userViewDeleteButton = new JButton("Delete User");
        userViewDeleteButton.setActionCommand(userScreenAccess);
        userViewDeleteButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
            	for (User p : NewUI.db.getUsers()){
					if (NewUI.selectedUserID==p.getUserId()){
						NewUI.db.getUsers().remove(p);
						NewUI.currentActiveScreen=e.getActionCommand();
					                       
						NewUI.selectedUserID=0;
					 
						GUI_UserScreen userScreen = new GUI_UserScreen();
						userScreen.userScreen();
						CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
						cl.show(NewUI.gui, e.getActionCommand()); 
					 
						break;	
					}
            	}
            } 
        });
        buttonPanel.add(userViewDeleteButton);  

        
        JPanel boxPanel = new JPanel();
        BoxLayout bl = new BoxLayout(boxPanel,BoxLayout.Y_AXIS);
        boxPanel.add(viewUserForm);
        botJP.add(boxPanel, BorderLayout.CENTER); 
         
    
          
          
          
// FOOTER  
        // Adds the top, bottom and button panels to the screen panel 
        screen.add(topJP,BorderLayout.NORTH);   
        screen.add(botJP,BorderLayout.CENTER);   
        screen.add(buttonPanel,BorderLayout.SOUTH); 
          
        // Assigns the MainUI gui panel the contents of the screen panel 
        NewUI.userCreateScreen = screen; 
        NewUI.gui.add(NewUI.userCreateScreen,userViewScreenAccess); 
  
// FOOTER  
    } 

	

}
