package Sprint2_Day3;
 
import java.awt.BorderLayout; 
import java.awt.CardLayout; 
import java.awt.Color; 
import java.awt.Dimension;
import java.awt.FlowLayout; 
import java.awt.Font; 
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout; 
import java.awt.Insets;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
  
import javax.swing.BorderFactory; 
import javax.swing.BoxLayout;
import javax.swing.JButton; 
import javax.swing.JComboBox; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JTextField; 
import javax.swing.border.TitledBorder; 
  
public class GUI_ProductCreateScreen { 
    private String productScreenAccess = "product",  productCreateScreenAccess = "productCreate"; 
  
    public void productCreateScreen(String screenName){ 
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
          
        JPanel createProductForm = new JPanel();
        createProductForm.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

            
        //create JPanel row1 and add components  
        //row1 to hold sku label and textfield value  
        JLabel createProductIDLabel = new JLabel("SKU:", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createProductForm.add(createProductIDLabel,constraint); 
        
        JLabel createOrderIDDisplayLabel = new JLabel(NewUI.db.getNoOfProducts()+"", JLabel.TRAILING);  
        createProductIDLabel.setLabelFor(createOrderIDDisplayLabel);  
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 0;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createProductForm.add(createOrderIDDisplayLabel,constraint); 
        //see what happens
        createProductForm.setPreferredSize(new Dimension(botJP.getWidth(),1));        
        createProductForm.setMaximumSize(new Dimension(botJP.getWidth(),1));
      //create JPanel row2 and add components  
        //row2 to hold supplier label and combobox   
        JLabel productCreateSupplierLabel = new JLabel("Supplier:", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createProductForm.add(productCreateSupplierLabel,constraint); 
        
        //Populate supplier combo box and add to row1 
        final JComboBox productCreateSupplierCombo = new JComboBox();  
        for (Supplier s : NewUI.db.getSuppliers()){  
            productCreateSupplierCombo.addItem(s.getName());  
        }  
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST; 
        createProductForm.add(productCreateSupplierCombo,constraint); 
          
        //create JPanel row3 and add components  
        //row3 to hold productName label and productTextField 
        JLabel productCreateNameLabel = new JLabel("Product Name:", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createProductForm.add(productCreateNameLabel,constraint); 
        
        final JTextField productCreateNameText = new JTextField(10);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 2;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createProductForm.add(productCreateNameText,constraint); 
         
        //create JPanel row4 and add components  
        //row4 to hold wholesalePrice label and wholeSaleTextField & retailprice label and textFields 

        
        JLabel productCreateWholesaleLabel = new JLabel("Wholesale Price:  €", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 3;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createProductForm.add(productCreateWholesaleLabel,constraint); 
        
        final JTextField productCreateWholesaleText = new JTextField(5);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 3;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createProductForm.add(productCreateWholesaleText,constraint); 
        
        JLabel productCreateRetailLabel = new JLabel("Retail Price:  €", JLabel.TRAILING);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 0;
        constraint.gridy = 4;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.EAST;
        createProductForm.add(productCreateRetailLabel,constraint);
          
        final JTextField productCreateRetailText = new JTextField(5);
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 0.5;
        constraint.gridx = 1;
        constraint.gridy = 4;
        constraint.insets = new Insets(10, 5, 10, 10);
        constraint.anchor = GridBagConstraints.WEST;
        createProductForm.add(productCreateRetailText,constraint); 
   
        
        //Add the create products form to the bottom JPanel
        botJP.add(new JLabel("Product Details"), BorderLayout.NORTH);
        
        JPanel boxPanel = new JPanel();
        BoxLayout bl = new BoxLayout(boxPanel,BoxLayout.Y_AXIS);
        boxPanel.add(createProductForm);
        botJP.add(boxPanel, BorderLayout.CENTER); 
              
  
        //Back button   
        JButton backButton = new JButton("Cancel");   
        backButton.setToolTipText("Cancels new product and returns to Product screen");  
        backButton.setActionCommand(productScreenAccess);   
        backButton.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e) { 
                GUI_ProductScreen productScreen = new GUI_ProductScreen(); 
                productScreen.productScreen();  
                  
                CardLayout cl = (CardLayout)(NewUI.gui.getLayout());        
                cl.show(NewUI.gui, productScreenAccess);  
            }  
        });  
          
        buttonPanel.add(backButton);   
      
        //Add button        
        final JButton productCreateAddButton = new JButton("Add");   
        productCreateAddButton.setActionCommand(productScreenAccess);   
        productCreateAddButton.addActionListener(new ActionListener() { //Create product from fields   
            public void actionPerformed(ActionEvent e){   
            	if (NewUI.check.isNotBlank(productCreateNameText.getText()) && NewUI.check.isPositiveNumeric(productCreateWholesaleText.getText())    
                 && NewUI.check.isPositiveNumeric(productCreateRetailText.getText())){
            		
            		NewUI.db.createProduct(productCreateNameText.getText(), Double.parseDouble(productCreateWholesaleText.getText()),    
                                   Double.parseDouble(productCreateRetailText.getText()),NewUI.db.getSuppliers().get(productCreateSupplierCombo.getSelectedIndex()));   
                           
                    NewUI.productScreen.removeAll();
       	            GUI_ProductScreen productScreen = new GUI_ProductScreen();
       	            productScreen.productScreen();
                           
                    CardLayout cl = (CardLayout)(NewUI.gui.getLayout());    
                    cl.show(NewUI.gui, e.getActionCommand());   
                    NewUI.currentActiveScreen=e.getActionCommand();   
                 }   
            }   
        });      
        buttonPanel.add(productCreateAddButton); 
          
// FOOTER  
        // Adds the top, bottom and button panels to the screen panel 
        screen.add(topJP,BorderLayout.NORTH);   
        screen.add(botJP,BorderLayout.CENTER);   
        screen.add(buttonPanel,BorderLayout.SOUTH); 
          
        // Assigns the MainUI gui panel the contents of the screen panel 
        NewUI.productCreateScreen = screen; 
        NewUI.gui.add(NewUI.productCreateScreen,productCreateScreenAccess); 
// FOOTER  
    } 
} 
