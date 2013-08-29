package Sprint2_Day3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class GUI_StockControlScreen {
	
	private String homeScreenAccess = "home", stockControlScreenAccess="stockControl";

	private JPanel botJP =  new JPanel();
	private JComboBox<String> productSelectionComboBox = new JComboBox<String>();
	private String selctedProductText = "All";
	private JComboBox<String> orderInvoiceSelectionComboBox = new JComboBox<String>();
	private String orderInvoiceSelection = "Sales";
	private JComboBox<String> stockRangeComboBox = new JComboBox<String>();
	private int chartDays=92;
	private DefaultTableModel dataTableModel  = new DefaultTableModel();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	JCheckBox predictionCheckBox = new JCheckBox("Include Predictions");
	int predictionWeeks = 6;
	
	public void stockControlScreen(){
		
		setupComboBoxes();
		
		//If screen is being entered for the first time, plot default graph
		if (dataTableModel.getColumnCount()==0){
			makeTableModel();
			JTable dataTable = new JTable();
			dataTable.setModel(dataTableModel);
			
			botJP.removeAll();	
			makeChart();
			botJP.add(new JScrollPane(dataTable));
		}

		// Create screen panel
		JPanel screen = new JPanel(); 
		BoxLayout bl = new BoxLayout(screen,BoxLayout.Y_AXIS);
		screen.setLayout(bl);
		screen.setOpaque(true);  //content panes must be opaque 

		// Creates three panels used for the top portion, bottom portion and button portion of the screen
		JPanel topJP = new JPanel();  
		topJP.setBorder(BorderFactory.createLineBorder(Color.RED));  

		botJP.setBorder(BorderFactory.createLineBorder(Color.blue));  
		botJP.setLayout(new BoxLayout(botJP,BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel(new FlowLayout()); 

		// Create the title of the screen in the top panel
		JLabel titlelbl = new JLabel("Stock Control", JLabel.CENTER);  
		titlelbl.setFont(new Font("Arial",2 , 48));  
		topJP.add(titlelbl);


		/*
		 *	Screen specific code goes here       
		 */

		
		JPanel botJPComboPanel = new JPanel(new FlowLayout());
		botJPComboPanel.add(orderInvoiceSelectionComboBox); 
		botJPComboPanel.add(productSelectionComboBox);  	
		botJPComboPanel.add(stockRangeComboBox); 

		//Create the view graph button
		JButton viewChartButton = new JButton("View Graph");
		viewChartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

				makeTableModel();
				JTable dataTable = new JTable();
				dataTable.setModel(dataTableModel);
				
				botJP.removeAll();	
				makeChart();
				botJP.add(new JScrollPane(dataTable));

				refresh();
			}  
		});
		botJPComboPanel.add(viewChartButton);
		
		botJPComboPanel.add(predictionCheckBox);
		botJP.add(botJPComboPanel);
		

		

		//Create Back button and set action listener
		JButton backButton = new JButton("Back"); 
		backButton.setActionCommand(homeScreenAccess);  
		backButton.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				GUI_HomeScreen homeScreen = new GUI_HomeScreen();
				homeScreen.homeScreen(); 

				CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
				cl.show(NewUI.gui, e.getActionCommand()); 

				NewUI.currentActiveScreen = e.getActionCommand(); 
			} 
		});      
		buttonPanel.add(backButton); 

		// Adds the top, bottom and button panels to the screen panel
		
		botJP.setPreferredSize(new Dimension(700,570));
		screen.add(topJP);
		screen.add(botJP);
		screen.add(buttonPanel);

		// Assigns the MainUI gui panel the contents of the screen panel	        
		NewUI.stockControlScreen = screen;
		NewUI.gui.add(NewUI.stockControlScreen,stockControlScreenAccess);


	}
	
	public void refresh(){
		stockControlScreen();
    	
    	CardLayout cl = (CardLayout)(NewUI.gui.getLayout());       
        cl.show(NewUI.gui, stockControlScreenAccess); 
        
        NewUI.currentActiveScreen = stockControlScreenAccess;
	}
	
	public void setupComboBoxes(){

		/*
		 ***************Combo box for selecting type of data to display (order/invoice/stock)************
		 */
		orderInvoiceSelectionComboBox = new JComboBox<String>();
		orderInvoiceSelectionComboBox.addItem("Sales");
		orderInvoiceSelectionComboBox.addItem("Purchases");
		orderInvoiceSelectionComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {   			
				orderInvoiceSelection=orderInvoiceSelectionComboBox.getSelectedItem().toString();
			}  
		});
		if (orderInvoiceSelection.equals("Sales")){
			orderInvoiceSelectionComboBox.setSelectedIndex(0);          		
		}
		else if (orderInvoiceSelection.equals("Purchases")){
			orderInvoiceSelectionComboBox.setSelectedIndex(1);          		
		}

		/*
		 ***************Combo box for selecting product************
		 */
		productSelectionComboBox = new JComboBox<String>();
		productSelectionComboBox.addItem("All");
		for (Product p : NewUI.db.getProducts()){
			productSelectionComboBox.addItem(p.getSku() + " - " + p.getName());
		}
		productSelectionComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {   			
				selctedProductText=productSelectionComboBox.getSelectedItem().toString();
			}
		});
		productSelectionComboBox.setSelectedItem(selctedProductText);

		/*
		 ***************Combo box for selecting time range************
		 */
		stockRangeComboBox=new JComboBox<String>();
		stockRangeComboBox.addItem("One month");
		stockRangeComboBox.addItem("Three months");
		stockRangeComboBox.addItem("One year");
		stockRangeComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (stockRangeComboBox.getSelectedItem().toString().equals("One month")){
					chartDays=31;          		
				}
				else if (stockRangeComboBox.getSelectedItem().toString().equals("Three months")){
					chartDays=92; 
				}
				else if (stockRangeComboBox.getSelectedItem().toString().equals("One year")){
					chartDays=365; 
				}
			}  
		});
		//Must set combobox selected item correctly at every refresh
		if (chartDays==31){
			stockRangeComboBox.setSelectedIndex(0);          		
		}
		else if (chartDays==92){
			stockRangeComboBox.setSelectedIndex(1);          		
		}
		else if (chartDays==365){
			stockRangeComboBox.setSelectedIndex(2);          		
		}   


	}

	public void makeChart(){
		 
		String chartTitle="";
		
		//Build a string buffer - detects if the selection contains numbers
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<selctedProductText.length(); i++)
		{
			if(Character.isDigit(selctedProductText.charAt(i))){
				sb.append(selctedProductText.charAt(i));
			}
			else{
				break;
			}
		}
		if (orderInvoiceSelection.equals("Sales")) {
			if (sb.length()!=0){ //then a product must be selected
				//Find the product corresponding to the selected ID in the item
				Product product = NewUI.db.getProducts().get(Integer.parseInt(sb.toString())-1);
				chartTitle=product.getName()+ " - Weekly Sales";
			}
			else if (productSelectionComboBox.getSelectedIndex()==0){
				chartTitle="Total Weekly Sales";
			}
		}
		else if (orderInvoiceSelection.equals("Purchases")) {
			if (sb.length() != 0) { // then a product must be selected
				// Find the product corresponding to the selected ID in the item
				Product product = NewUI.db.getProducts().get(
						Integer.parseInt(sb.toString()) - 1);
				chartTitle = product.getName() + " - Weekly Purchases";
			} else if (productSelectionComboBox.getSelectedIndex() == 0) {
				chartTitle = "Total Weekly Purchases";
			}
		}

		
		JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle, "Time", "€", null,true,false,false);
		XYPlot xyplot = (XYPlot)chart.getPlot();
		XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.white);
		
		plot.setDataset(0,dataFromTable());
		XYLineAndShapeRenderer renderer0 = new XYLineAndShapeRenderer(); 
		plot.setRenderer(0,renderer0); 
		plot.getRendererForDataset(plot.getDataset(0)).setSeriesItemLabelsVisible(0, false); 
		plot.getRendererForDataset(plot.getDataset(0)).setBaseItemLabelsVisible(false); 
		plot.getRendererForDataset(plot.getDataset(0)).setBaseItemLabelsVisible(false); 
		plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaint(0, Color.blue); 


		//If prediction combo box is selected, then add prediction values to chart
		if (predictionCheckBox.isSelected()){			
			plot.setDataset(1, predictionDataFromTable());
			XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
			plot.setRenderer(1, renderer1); 
			plot.getRendererForDataset(plot.getDataset(1)).setSeriesPaint(0, Color.red);
		}

		DateAxis dateaxis = (DateAxis) plot.getDomainAxis();
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(700, 470));
		botJP.add(chartPanel);

	}

	public void makeTableModel(){

		//Build a string buffer - detects if the selection contains numbers
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<selctedProductText.length(); i++)
		{
			if(Character.isDigit(selctedProductText.charAt(i))){
				sb.append(selctedProductText.charAt(i));
			}
			else{
				break;
			}
		}

		//Initiate calender (necessary for incrementing date)
		Date date = new Date(); 
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.DATE, -chartDays); //Set the date to 60 days ago
		date=c1.getTime();

		//Initiate 2nd date - one to compare the loop date to 
		Calendar c2 = Calendar.getInstance();
		double totalValue=0; //cumulative sales for certain date

		//Set up table model with columns
		dataTableModel = new DefaultTableModel() { 
			public boolean isCellEditable(int row, int column) { 
				return false;// This causes all cells to be not editable 
			} 
		};  	
		dataTableModel.addColumn("Date");
		dataTableModel.addColumn("Weekly Sales (€)");
		Vector<String> singleRow = new Vector<String>();

		if (orderInvoiceSelection.equals("Sales")){
			for (int j=0;j<chartDays-1;j=j+7){           	

				//Update date by seven days
				c1.setTime(date);
				c1.add(Calendar.DATE, 7);
				date=c1.getTime();
				
				totalValue=0; //reset totalSales

				//Loop through the invoices. Add the total values of the invoices with the same date as the date in loop
				for (Invoice i : NewUI.db.getInvoices()){

					c2.setTime(i.getDate());
					if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
							&& c1.get(Calendar.WEEK_OF_YEAR) == c2
									.get(Calendar.WEEK_OF_YEAR)) {
						// If sb has a length (is a number) then the selection must be a product
						if (sb.length() != 0) {
							// Find the product corresponding to the selected ID in the item
							Product product = NewUI.db.getProducts().get(
									Integer.parseInt(sb.toString()) - 1);
							// Loop through products. Only add the total sales
							// if the product is the same as the selected product
							totalValue = totalValue
									+ i.calculateRetailValue(product.getSku());
						}

						// Else it must be another, non product, selection
						else if (productSelectionComboBox.getSelectedIndex() == 0) {
							totalValue = totalValue
									+ i.calculateTotalRetailValue();
						}
					}
				}
				singleRow.add(sdf.format(date));
				singleRow.add(Double.toString(totalValue));
				dataTableModel.addRow(singleRow);

				singleRow = new Vector<String>();
			}
			



		}
		else if (orderInvoiceSelection.equals("Purchases")) {
			for (int j = 0; j < chartDays; j = j + 7) {

				// Update date by seven days
				c1.setTime(date);
				c1.add(Calendar.DATE, 7);
				date = c1.getTime();

				totalValue = 0; // reset totalSales

				// Loop through the invoices. Add the total values of the
				// invoices with the same date as the date in loop
				for (Order order : NewUI.db.getOrders()) {
					c2.setTime(order.getDate());
					if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
							&& c1.get(Calendar.WEEK_OF_YEAR) == c2
									.get(Calendar.WEEK_OF_YEAR)) {

						// If sb has a length (is a number) then the selection must be a product
						if (sb.length() != 0) {
							// Find the product corresponding to the selected ID in the item
							Product product = NewUI.db.getProducts().get(
									Integer.parseInt(sb.toString()) - 1);
							// Loop through products. Only add the total sales
							// if the product is the same as the selected product
							totalValue = totalValue
									+ order.calculateWholesaleValue( product.getSku() );							
						}
						// Else it must be another, non product, selection
						else if (productSelectionComboBox.getSelectedIndex() == 0) {
							totalValue = totalValue
									+ order.calculateTotalWholesaleValue();
						}
					}
				}
				singleRow.add(sdf.format(date));
				singleRow.add(Double.toString(totalValue));
				dataTableModel.addRow(singleRow);
				singleRow = new Vector<String>();
			}

		}
		
		//If prediction combo box is selected, then add prediction values to defaultTable
		if (predictionCheckBox.isSelected()){
			
			addPredictionData();
			
		}

	}

	public void addPredictionData(){
		
		//Preliminary prediction - Gets average of last three weeks
		double averageValue = (Double.parseDouble(dataTableModel.getValueAt(dataTableModel.getRowCount()-1, 1).toString())
				+Double.parseDouble(dataTableModel.getValueAt(dataTableModel.getRowCount()-2, 1).toString())+
				Double.parseDouble(dataTableModel.getValueAt(dataTableModel.getRowCount()-3, 1).toString()))/3;

		//Get last date in Table
		String dateString = dataTableModel.getValueAt(dataTableModel.getRowCount()-1, 0).toString();
		Date date = new Date();
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {}
		
		for (int i = 0; i<predictionWeeks ; i++){
			
			//Update date by seven days
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date);
			c1.add(Calendar.DATE, 7);
			date=c1.getTime();
			
			
			double totalValue=0; //reset totalSales
			//Set the prediction values
			totalValue=averageValue;
			Vector<String> singleRow = new Vector<String>();
			singleRow.add(sdf.format(date));
			singleRow.add(Double.toString(totalValue));
			dataTableModel.addRow(singleRow);
			singleRow = new Vector<String>();
			
		}
	}
	
	public XYDataset dataFromTable(){

		TimeSeries series1 = new TimeSeries(orderInvoiceSelection+ " Data");
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		
		int rowsToGraph = dataTableModel.getRowCount();
		if (predictionCheckBox.isSelected()){
			rowsToGraph = rowsToGraph-predictionWeeks;
		}

		try {
			for (int i=0; i<rowsToGraph;i++){
				RegularTimePeriod xValue;

				xValue = new Day(sdf.parse(dataTableModel.getValueAt(i, 0).toString()));

				double yValue = Double.parseDouble(dataTableModel.getValueAt(i, 1).toString());

				series1.add(xValue ,yValue);
			}
		} catch (ParseException e) {
		}
		dataset.addSeries(series1);


		return dataset;
	} 
		
	public XYDataset predictionDataFromTable(){

		TimeSeries series1 = new TimeSeries("Prediction Data");
		TimeSeriesCollection dataset = new TimeSeriesCollection();

		try {
			for (int i=dataTableModel.getRowCount()-1-predictionWeeks; i<dataTableModel.getRowCount()-1;i++){
				RegularTimePeriod xValue;

				xValue = new Day(sdf.parse(dataTableModel.getValueAt(i, 0).toString()));

				double yValue = Double.parseDouble(dataTableModel.getValueAt(i, 1).toString());

				series1.add(xValue ,yValue);
			}
		} catch (ParseException e) {
		}

		dataset.addSeries(series1);
		return dataset;
	} 
	
	
//	public void exporter(File file) throws IOException {
//		
//		file= new File("H:\");"
//	    
//	    FileWriter out = new FileWriter(file);  
//	    BufferedWriter bw = new BufferedWriter(out);
//	    
//	    for (int i = 0; i<dataTableModel.getColumnCount();i++){
//	    	bw.write(dataTableModel.getColumnName(i)+"\t");
//	    }
//	    bw.write("\n");
//	    
//	    for (int j = 0; j<dataTableModel.getRowCount();j++){
//	    	for (int i = 0; i<dataTableModel.getRowCount();i++){
//		    	bw.write(dataTableModel.getValueAt(i, j)+"\t");
//	    	}
//		    bw.write("\n");
//	    }
//	    bw.write("\n");
//	    bw.close();
//	    
//	    
//	    
//	}
	
}
