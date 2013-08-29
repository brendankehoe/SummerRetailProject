package Sprint2_Day3;
/** 
* Login / Access Control Class for Retail Management System
* 
* @date 26/08/2013
* @version 1.0
* @param JFrame loginframe
* @param JTextField userTex
* @param JPasswordField passwordText
* @param String loginUserName
* @param String loginUserPassword
* @param RetailSystem rs
* @return currentUser.isAdmin
*/ 


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.util.ArrayList; 
  
import javax.swing.BorderFactory;
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.JPasswordField; 
import javax.swing.JTextField; 



  
  
  
  
public  class LoginModule extends JFrame { 

	private JFrame loginframe = new JFrame("Retail Management System Login"); 
//
	public static JPanel p = new JPanel();
//
    private JTextField userText = new JTextField(20); 
    private JPasswordField passwordText = new JPasswordField(20); 
    private JButton loginButton = new JButton("Login"); 
    private String loginUserName; 
    @SuppressWarnings("deprecation") 
    private String loginUserPassword; 
    public static User currentUser;
    
    
      
    public LoginModule(){ 
    	
        loginframe.setSize(400, 150); 
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        logInScreen();
        placeComponents(loginframe); 
        loginframe.setVisible(true); 
        loginframe.setLocationRelativeTo(null); //Centres the window on screen 


    } 
      
    
    private void logInScreen(){
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
    	        JLabel titlelbl = new JLabel("Retail Management System Log-in", JLabel.CENTER);  
    	        titlelbl.setFont(new Font("Arial",2 , 48));  
    	        topJP.add(titlelbl);
    	// HEADER       

    	        
    	/*
    	 *	Screen specific code goes here       
    	 */

    	        
    	// FOOTER 
    	        // Adds the top, bottom and button panels to the screen panel
    	        screen.add(topJP,BorderLayout.NORTH);  
    	        screen.add(botJP,BorderLayout.CENTER);  
    	        screen.add(buttonPanel,BorderLayout.SOUTH);
    	        
    	        // Assigns the MainUI gui panel the contents of the screen panel

    	// FOOTER 
    }
    
//    public void printUsers(){ 
//          
//        for(User user: NewUI.db.getUsers()){ 
//            System.out.println(user.getUserName() + " "  + user.getPassword()); 
//        } 
//    } 
      
      
    public boolean authenticateUser(){ 
        loginUserName = userText.getText(); 
        loginUserPassword = passwordText.getText(); 
        boolean isAdmin = false;
        currentUser = new User();
        for(User user :  NewUI.db.getUsers()){
        	if(user.getUserName().equals(loginUserName)){
        		currentUser = user;
        	}
        }
        
        ArrayList<String> existingUserNames = new ArrayList<String>();
        
        for(User user: NewUI.db.getUsers()  ){ 
        	existingUserNames.add(user.getUserName());
        }
        
        
        if(loginUserName.equals("")){ 
           
            JOptionPane.showMessageDialog(loginButton, "Please enter a User Name"); 
        } 
        else if(!existingUserNames.contains(loginUserName)){
        	JOptionPane.showMessageDialog(loginButton, "User Name entered is not a valid user name");
        }
        else if (loginUserPassword.equals("")){ 
            JOptionPane.showMessageDialog(loginButton, "Please enter a Password"); 
        }
        else if (loginUserName.equals(currentUser.getUserName()) && !loginUserPassword.equals(currentUser.getPassword())){ 
            JOptionPane.showMessageDialog(loginButton,"Incorrect Password", 
            "Error",JOptionPane.ERROR_MESSAGE); 
        }
        else if (loginUserName.equals(currentUser.getUserName())&& currentUser.isActive() == false ) {   
            JOptionPane.showMessageDialog(loginButton, "Account is Not Active"); 
        } 
        else if (loginUserName.equals(currentUser.getUserName()) && loginUserPassword.equals(currentUser.getPassword()) && currentUser.isActive() == true ) {   
            isAdmin = currentUser.isAdmin();   
           int selection = JOptionPane.showConfirmDialog(null, "Welcome " + userText.getText() + " Click OK To Continue", loginUserName, JOptionPane.OK_CANCEL_OPTION
                    , JOptionPane.INFORMATION_MESSAGE); 
           if (selection == JOptionPane.OK_OPTION)
           {
               // Code to use when OK is PRESSED.
                   	
               loginframe.dispose(); 
               NewUI newui = new NewUI();
           }
           else if (selection == JOptionPane.CANCEL_OPTION)
           {
               // Code to use when CANCEL is PRESSED.
        	   loginframe.dispose();
           }

        }
        
        return currentUser.isAdmin();
      
    } 
  
    private void placeComponents(JFrame frame) { 
        frame.setLayout(null); 
  
        JLabel userLabel = new JLabel("User Name"); 
        userLabel.setBounds(10, 10, 80, 25); 
        frame.add(userLabel); 
  
          
        userText.setBounds(100, 10, 160, 25); 
        frame.add(userText); 
  
        JLabel passwordLabel = new JLabel("Password"); 
        passwordLabel.setBounds(10, 40, 80, 25); 
        frame.add(passwordLabel); 
  
           
        passwordText.setBounds(100, 40, 160, 25); 
        frame.add(passwordText); 
  
          
        loginButton.setBounds(10, 80, 80, 25); 
        frame.add(loginButton); 
  
          
  
        ActionListener loginButtonListener = new ActionListener() { 
              
  
            @Override
            public void actionPerformed(ActionEvent e) { 
                JButton source = (JButton) e.getSource(); 
  
                authenticateUser();  
                  
                  } 
        }; 
          
        loginButton.addActionListener(loginButtonListener); 
          
    } 
      
    public static void main(String[] args) { 
        LoginModule loginModule = new LoginModule(); 

    } 
      
  
      
    } 