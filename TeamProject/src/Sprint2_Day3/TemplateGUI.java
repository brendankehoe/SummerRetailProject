package Sprint2_Day3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class TemplateGUI {

	public void TemplateGUIFunctionality(String screenName){
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
 *	Screen specific code goes here       
 */

        
// FOOTER 
        // Adds the top, bottom and button panels to the screen panel
        screen.add(topJP,BorderLayout.NORTH);  
        screen.add(botJP,BorderLayout.CENTER);  
        screen.add(buttonPanel,BorderLayout.SOUTH);
        
        // Assigns the MainUI gui panel the contents of the screen panel
        
//        NewUI.orderScreen = screen;
//        NewUI.gui.add(NewUI.orderScreen,orderScreenAccess);
// FOOTER 
    }

}
