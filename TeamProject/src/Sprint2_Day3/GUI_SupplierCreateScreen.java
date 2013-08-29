package Sprint2_Day3;

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
import java.util.Vector; 
  
import javax.swing.BorderFactory; 
import javax.swing.JButton; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import javax.swing.JTextField; 
import javax.swing.border.TitledBorder; 
import javax.swing.table.DefaultTableModel; 
  
  
public class GUI_SupplierCreateScreen { 
    private String   supplierScreenAccess = "supplier", supplierCreateScreenAccess = "supplierCreate"; 
      
  
  
    public void supplierCreateScreen(){ 
// HEADER  
        // Create screen panel that is used to replace the gui panel from MainUI.class 
        JPanel screen = new JPanel(new BorderLayout());  
        screen.setLayout(new BorderLayout());  
        screen.setOpaque(true);  //content panes must be opaque  
          
        // Creates three panels used for the top portion, bottom portion and button portion of the screen 
        JPanel topJP = new JPanel(new BorderLayout());   
        topJP.setBorder(BorderFactory.createLineBorder(Color.RED));   
        JPanel botJP =  new JPanel(new BorderLayout());   
        botJP.setBorder(BorderFactory.createLineBorder(Color.blue));   
        JPanel buttonPanel = new JPanel(new FlowLayout());  
  
        // Create the title of the screen in the top panel 
        JLabel titlelbl = new JLabel("Add Supplier", JLabel.CENTER);   
        titlelbl.setFont(new Font("Arial",2 , 48));   
        topJP.add(titlelbl); 
// HEADER        
  
          
/* 
 *  Screen specific code goes here        
 */
        JPanel createSupplierForm = new JPanel();   
        createSupplierForm.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        //Creating label  
        JLabel supplierCreateNameLabel = new JLabel("Name: ",JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createSupplierForm.add(supplierCreateNameLabel,constraint);     
      
        //Creating editable text field  
        final JTextField supplierCreateNameText = new JTextField(10);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createSupplierForm.add(supplierCreateNameText,constraint); 
        
        //Creating label  
        JLabel supplierCreateEmailLabel = new JLabel("Email: ",JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createSupplierForm.add(supplierCreateEmailLabel,constraint);     
      
        //Creating editable text field  
        final JTextField supplierCreateEmailText = new JTextField(10);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createSupplierForm.add(supplierCreateEmailText,constraint); 
        
        //Creating label  
        JLabel supplierCreatePhoneLabel = new JLabel("Phone Number: ",JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createSupplierForm.add(supplierCreatePhoneLabel,constraint);     
      
        //Creating editable text field  
        final JTextField supplierCreatePhoneText = new JTextField(10);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createSupplierForm.add(supplierCreatePhoneText,constraint); 

            
        //Create back button and set action listener   
        JButton backButton = new JButton("Cancel");   
        backButton.setToolTipText("Cancels new supplier and returns to Supplier screen");  
        backButton.setActionCommand(supplierScreenAccess);   
        backButton.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e) { 
            	GUI_SupplierScreen supplierScreen = new GUI_SupplierScreen(); 
            	supplierScreen.supplierScreen(); 
                  
                CardLayout cl = (CardLayout)(NewUI.gui.getLayout());        
                cl.show(NewUI.gui, e.getActionCommand());  
            }  
        });           
        buttonPanel.add(backButton);  
      
      
        //Create 'Add' button      
        final JButton addButton = new JButton("Add");   
            
        //Set action command so that program switches to supplierScreenAccess panel after successful add  
        addButton.setActionCommand(supplierScreenAccess);   
            
        //Add Supplier - when button is pressed, check if the text field is not blank, then add supplier  
        addButton.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent e){   
                if (NewUI.check.isNotBlank(supplierCreateNameText.getText())
                		&& NewUI.check.isNotBlank(supplierCreateEmailText.getText())
                		&& NewUI.check.isNotBlank(supplierCreatePhoneText.getText())){   
      
//                    Create supplier from text fields  
                    NewUI.db.createSupplier(supplierCreateNameText.getText(),supplierCreateEmailText.getText(),
                    		supplierCreatePhoneText.getText());   
      
                    //Retrieving the current active window so that the program can refresh() and then switch to the previous screen  
                    NewUI.currentActiveScreen=e.getActionCommand();  
					  
					GUI_SupplierScreen supplierScreen = new GUI_SupplierScreen();
					supplierScreen.supplierScreen();
              	  	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
              	  	cl.show(NewUI.gui, e.getActionCommand()); 
   
                }   
      
            }   
        });     
                  
        buttonPanel.add(addButton);   
              
        //Add the create supplier form to the bottom JPanel
        botJP.add(new JLabel("Supplier Details"), BorderLayout.NORTH);
        botJP.add(createSupplierForm,BorderLayout.CENTER);  
       
      
           
        
// FOOTER  
        // Adds the top, bottom and button panels to the screen panel 
        screen.add(topJP,BorderLayout.NORTH); 
        screen.add(botJP,BorderLayout.CENTER);   
        screen.add(buttonPanel,BorderLayout.SOUTH); 
          
        // Assigns the MainUI gui panel the contents of the screen panel 
        
        NewUI.supplierCreateScreen = screen; 
        NewUI.gui.add(NewUI.supplierCreateScreen,supplierCreateScreenAccess); 
  
          
// FOOTER  
    } 
  
} 