package sprint2_Day7;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class GUI_CustomerCreateScreen {
	
	private String customerScreenAccess = "customer", customerCreateScreenAccess = "customerCreate";

	public void customerCreateScreen(){
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
        JLabel titlelbl = new JLabel("Add Customer", JLabel.CENTER);   
        titlelbl.setFont(NewUI.topBannerFont);   
        topJP.add(titlelbl);
// HEADER       

        
		/*
		 *	Screen specific code goes here       
		 */
        JPanel createCustomerForm = new JPanel();  
        createCustomerForm.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        
        //Create name label and text field   
        JLabel customerCreateNameLabel = new JLabel("Name:", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createCustomerForm.add(customerCreateNameLabel,constraint);        
        
        final JTextField customerCreateNameText = new JTextField(10);  
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createCustomerForm.add(customerCreateNameText,constraint);  
    
        //Create phone number label and text field   
        JLabel customerCreatePhoneLabel = new JLabel("Phone Number:", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createCustomerForm.add(customerCreatePhoneLabel,constraint);
        
        final JTextField customerCreatePhoneText = new JTextField(10); 
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createCustomerForm.add(customerCreatePhoneText,constraint);  
    
        //Create address label and text field   
        JLabel customerCreateRetailLabel = new JLabel("Address:", JLabel.TRAILING);  
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createCustomerForm.add(customerCreateRetailLabel,constraint);    
        
        final JTextField customerCreateAddressText = new JTextField(10); 
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createCustomerForm.add(customerCreateAddressText,constraint);  
          
        //Date of birth label 
        JLabel customerCreateDOBLabel = new JLabel("Date of Birth:", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createCustomerForm.add(customerCreateDOBLabel,constraint);  
          
        //******************Date of birth panel******************* 
        /* 
         * Create the Date of Birth Panel. This will contain the date labels  
         * and combo boxes and be inserted into the Spring Grid Layout 
         */ 
        JPanel dOBPanel = new JPanel();  
          
        //Day label 
        JLabel dOBDayLabel = new JLabel("Day:"); 
        dOBPanel.add(dOBDayLabel); 
        //Populate the array of days (1-31) 
        String[] days = new String[31]; 
        for (int i = 1; i<32;i++){ 
        //If the number is less than 10, convert to a double digit (necessary for date formatting) 
        days[i-1]=(i < 10 ? "0" : "")+ Integer.toString(i);  
         
        } 
        final JComboBox<String> daysComboBox = new JComboBox<String>(days); 
        //Add String [] of days to the combo box 
        dOBPanel.add(daysComboBox); 
          
        //Month label 
        JLabel dOBMonthLabel = new JLabel("Month:"); 
        dOBPanel.add(dOBMonthLabel);      
        //Populate the array of months 
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}; 
        final JComboBox<String> monthsComboBox = new JComboBox<String>(months); 
        //Add String [] of months to the combo box 
        dOBPanel.add(monthsComboBox); 
    
        //Year label 
        JLabel dOBYearLabel = new JLabel("Year:"); 
        dOBPanel.add(dOBYearLabel);   
        //Populate the array of years (100 previous years) 
        String[] years = new String[100]; 
        int startYear=2013; 
        for (int i = 0; i < years.length; i++){ 
            years[i] = Integer.toString(startYear - i); 
        } 
        final JComboBox<String> yearsComboBox = new JComboBox<String>(years); 
        //Add String [] of years to the combo box 
        dOBPanel.add(yearsComboBox); 
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 3;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createCustomerForm.add(dOBPanel,constraint);
        
        //Add the create customers form to the bottom JPanel
        botJP.add(new JLabel("Customer Details"), BorderLayout.NORTH);
        
        JPanel boxPanel = new JPanel();
        boxPanel.add(createCustomerForm);
        botJP.add(boxPanel, BorderLayout.CENTER);
          
        //Back button  
        JButton customerCreateBackButton = new JButton("Cancel");  
        customerCreateBackButton.setToolTipText("Cancels new customer and returns to Customer screen"); 
        customerCreateBackButton.setActionCommand(customerScreenAccess);  
        customerCreateBackButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
                NewUI.currentActiveScreen = e.getActionCommand();

                GUI_CustomerScreen customerScreen = new GUI_CustomerScreen();
            	customerScreen.customerScreen(); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                cl.show(NewUI.gui, e.getActionCommand()); 
            } 
        }); 
        buttonPanel.add(customerCreateBackButton); 
          
        //Add button       
        final JButton customerCreateAddButton = new JButton("Add");  
        customerCreateAddButton.setActionCommand(customerScreenAccess);  
          
        /* 
         * Add customer: if all text fields contain text, create customer from text fields 
         */
        if (NewUI.currentUser.isAdmin()){
        customerCreateAddButton.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){  
                if (NewUI.check.isNotBlank(customerCreateNameText.getText()) && NewUI.check.isNotBlank(customerCreatePhoneText.getText()) 
                        && NewUI.check.isNotBlank(customerCreateAddressText.getText())){ 
                      
                    //Initiate date format so that date of birth can be passed into createCustomer method 
                    SimpleDateFormat ft = new SimpleDateFormat("ddMMyyyy"); 
                    Date dOB = new Date(); 
                      
                    //Gets the correct month as a string in two digit form (01-12)  
                    String month = (monthsComboBox.getSelectedIndex()+1 < 10 ? "0" : "") + Integer.toString(monthsComboBox.getSelectedIndex()+1); 
                      
                    //the .parse function will not work unless surrounded by a try+catch for some reason! 
                    try { 
                          
                        //Making the date string of format ddMMyyyy 
                        dOB = ft.parse(daysComboBox.getSelectedItem().toString() +month+yearsComboBox.getSelectedItem().toString()); 
                    } catch (ParseException e1) { 
                    } 
                    //Create customer from fields 
                    if (NewUI.check.isOfLegalAge(dOB)){ 
                    	NewUI.db.createCustomer(customerCreateNameText.getText(), dOB, customerCreateAddressText.getText(), customerCreatePhoneText.getText()); 
    
                    	  NewUI.currentActiveScreen=e.getActionCommand();  
      					  
      					  GUI_CustomerScreen customerScreen = new GUI_CustomerScreen();
      					  customerScreen.customerScreen();
                    	  CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
                    	  cl.show(NewUI.gui, e.getActionCommand()); 
                    } 
    
                } 
            } 
        });  	
    }
     
        buttonPanel.add(customerCreateAddButton);  
            

        
// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        
        NewUI.customerCreateScreen = screen;
        NewUI.gui.add(NewUI.customerCreateScreen,customerCreateScreenAccess);
// FOOTER 
    }


}
