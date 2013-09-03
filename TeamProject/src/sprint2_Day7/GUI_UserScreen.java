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

public class GUI_UserScreen {
	
private String userScreenAccess = "user",homeScreenAccess = "home", userCreateScreenAccess = "userCreate",userViewScreenAccess = "userView";
	
	public void userScreen(){
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
        JLabel titlelbl = new JLabel("Manage Users", JLabel.CENTER);  
        titlelbl.setFont(new Font("Arial",2 , 48));  
        topJP.add(titlelbl);
// HEADER       


        /*
         * Populating the users table - Loop through users ArrayList and all each ID and name to vector
         * Using vectors because they are compatible with tables whereas ArrayLists are not
         */
        // Reset the value of the variable that stores the selected line in the JTable
        NewUI.selectedUserID = 0;

        
        //create Table for users panel. using DefaultTableModel as an easy way to make a table
        final JTable usersTable = new JTable();
        DefaultTableModel model = new DefaultTableModel()
        {
      	  public boolean isCellEditable(int row, int column)
      	  {
      		  return false;//This causes all cells to be not editable
      	  }
        };
        model.addColumn("ID"); 
        model.addColumn("Name");
        model.addColumn("Administrator");
        model.addColumn("Active");
        
        //Populate rows with user data
       
        for (User c : NewUI.db.getUsers()){ 
      	  Vector<String> singleuser = new Vector<String>(); 
      	  singleuser.add(Integer.toString(c.getUserId()));  
      	  singleuser.add(c.getUserName()); 
      	  singleuser.add(Boolean.toString(c.isAdmin()));
      	  singleuser.add(Boolean.toString(c.isActive())); 

      	  model.addRow(singleuser);     
        }      
        usersTable.setModel(model); 
        

        //Get the selected user ID from when the table is clicked
        usersTable.addMouseListener(new MouseAdapter() {
      	  public void mouseClicked(MouseEvent e) {  
      		  NewUI.selectedUserID = Integer.parseInt(usersTable.getValueAt(usersTable.getSelectedRow(),0).toString());

      	  }
        });
        //Get the selected user ID from when the keyboard is clicked
        usersTable.addKeyListener(new KeyListener(){
      	  @Override
      	  public void keyPressed(KeyEvent e){ 

      	  }
      	  public void keyReleased(KeyEvent e) {	
      		  NewUI.selectedUserID = Integer.parseInt(usersTable.getValueAt(usersTable.getSelectedRow(),0).toString());
      	  }
      	  public void keyTyped(KeyEvent e) {

      	  }
        });

        
        //Create Back button and set action listener
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
        
        //Create View button and set action listener
        JButton viewButton = new JButton("View User");  
        viewButton.setActionCommand(userViewScreenAccess);  
        viewButton.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e){ 
        		//Edit button checks to see if a user is selected

        		if (NewUI.check.isAUser(NewUI.selectedUserID, NewUI.db.getUsers())){

        			NewUI.currentActiveScreen = e.getActionCommand();

      			GUI_UserViewScreen userViewScreen = new GUI_UserViewScreen();

				userViewScreen.userViewScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

      		  }
      	  }
        });

        buttonPanel.add(viewButton); 

        //Create New user button and set action listener
        JButton addButton = new JButton("Add New user");  
        addButton.setActionCommand(userCreateScreenAccess);  
        addButton.addActionListener(new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent e) {
            	GUI_UserCreateScreen userCreateScreen = new GUI_UserCreateScreen();
            	userCreateScreen.userCreateScreen("Create New User"); 
            	
            	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
            	cl.show(NewUI.gui, e.getActionCommand());
               
            	NewUI.currentActiveScreen = e.getActionCommand();
            } 
         }); ;  
        buttonPanel.add(addButton);  

        //Set inner panel content
        topJP.add(titlelbl);
        botJP.add(new JLabel("All users"), BorderLayout.NORTH);  
        botJP.add(new JScrollPane(usersTable),BorderLayout.CENTER);

        
// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        
        NewUI.userScreen = screen;
        NewUI.gui.add(NewUI.userScreen,userScreenAccess);
// FOOTER 

}
}
