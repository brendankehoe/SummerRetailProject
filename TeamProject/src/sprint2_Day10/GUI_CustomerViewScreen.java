package sprint2_Day10;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUI_CustomerViewScreen {

	private String customerScreenAccess = "customer",
			customerViewScreenAccess = "customerView";

	public void customerViewScreen() {
		// HEADER
		// Create screen panel that is used to replace the gui panel from
		// MainUI.class
		JPanel screen = new JPanel(new BorderLayout());
		screen.setOpaque(true); // content panes must be opaque

		// Creates three panels used for the top portion, bottom portion and
		// button portion of the screen
		JPanel topJP = new JPanel();
		topJP.setBackground(NewUI.topBannerColor);
		JPanel botJP = new JPanel();
		botJP.setLayout(new BoxLayout(botJP, BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel(new FlowLayout());

		// Create the title of the screen in the top panel
		JLabel titlelbl = new JLabel("View Customer", JLabel.CENTER);
		titlelbl.setFont(NewUI.topBannerFont);
		topJP.add(titlelbl);
		// HEADER

		/*
		 * Screen specific code goes here
		 */
		// Declare panels and layouts
		// screen = new JPanel();
		// screen.setLayout(new BoxLayout(customerViewScreen,BoxLayout.Y_AXIS))
		// ;

		JPanel middleDetailsJP = new JPanel(new GridBagLayout());
		JPanel middleInvoicesJP = new JPanel(new BorderLayout());

		// ************Customer Details Panel*************************
		// Need to initiate customer as it is not defined until inside a loop
		Customer customer = new Customer(0, "", new Date(), "", "");

		GridBagConstraints constraint = new GridBagConstraints();

		// Retrieving the correct customer from the selectedCustomerID from the
		// table.
		for (Customer c : NewUI.db.getCustomers()) {
			if (NewUI.selectedCustomerID == c.getId()) {
				customer = c;
			}
		}

		// Putting customer details into the text fields
		JLabel nameLabel = new JLabel("Name:", JLabel.TRAILING);
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.insets = new Insets(10, 5, 10, 10);
		constraint.anchor = GridBagConstraints.EAST;
		middleDetailsJP.add(nameLabel, constraint);

		final JTextField nameText = new JTextField();
		nameText.setText(customer.getName());
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 1;
		constraint.gridy = 0;
		constraint.insets = new Insets(10, 5, 10, 10);
		constraint.anchor = GridBagConstraints.WEST;
		middleDetailsJP.add(nameText, constraint);

		JLabel addressLabel = new JLabel("Address:", JLabel.TRAILING);
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.insets = new Insets(10, 5, 10, 10);
		constraint.anchor = GridBagConstraints.EAST;
		middleDetailsJP.add(addressLabel, constraint);

		final JTextField addressText = new JTextField();
		addressText.setText(customer.getAddress());
		addressText.setMinimumSize(new Dimension(30, 30));
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 1;
		constraint.gridy = 1;
		constraint.insets = new Insets(10, 5, 10, 10);
		constraint.anchor = GridBagConstraints.WEST;
		middleDetailsJP.add(addressText, constraint);

		JLabel phoneLabel = new JLabel("Phone number:", JLabel.TRAILING);
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 0;
		constraint.gridy = 2;
		constraint.insets = new Insets(10, 5, 10, 10);
		constraint.anchor = GridBagConstraints.EAST;
		middleDetailsJP.add(phoneLabel, constraint);

		final JTextField phoneText = new JTextField();
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 1;
		constraint.gridy = 2;
		constraint.insets = new Insets(10, 5, 10, 10);
		constraint.anchor = GridBagConstraints.WEST;
		phoneText.setText(customer.getPhoneNumber());
		middleDetailsJP.add(phoneText, constraint);

		JLabel dODLabel = new JLabel("Date of Birth:", JLabel.TRAILING);
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 0;
		constraint.gridy = 3;
		constraint.insets = new Insets(10, 5, 10, 10);
		constraint.anchor = GridBagConstraints.EAST;
		middleDetailsJP.add(dODLabel, constraint);

		JTextField dOBText = new JTextField();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dOBText.setText(sdf.format(customer.getDateOfBirth()));
		dOBText.setEditable(false);
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.weightx = 0.5;
		constraint.gridx = 1;
		constraint.gridy = 3;
		constraint.insets = new Insets(10, 5, 10, 10);
		constraint.anchor = GridBagConstraints.WEST;
		middleDetailsJP.add(dOBText, constraint);

		// If current logged-in user is not admin, disable text fields
		if (!NewUI.currentUser.isAdmin()) {
			nameText.setEditable(false);
			addressText.setEditable(false);
			phoneText.setEditable(false);
		}

		/*
		 * Edit button: When edit is pressed, code cycles through customers,
		 * finds the one with the corresponding ID to what's selected in the
		 * table (selectedCustomerID) and edits the details according to the
		 * text fields
		 */
		// If current logged-in user is not admin, hide edit button
		if (NewUI.currentUser.isAdmin()) {
			JButton editButton = new JButton("Edit");
			constraint.fill = GridBagConstraints.HORIZONTAL;
			constraint.weightx = 0.5;
			constraint.gridx = 2;
			constraint.gridy = 3;
			constraint.insets = new Insets(10, 5, 10, 10);
			constraint.anchor = GridBagConstraints.WEST;
			middleDetailsJP.add(editButton, constraint);
			editButton.setActionCommand(customerScreenAccess);
			editButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (Customer c : NewUI.db.getCustomers()) {
						if (NewUI.selectedCustomerID == c.getId()) {
							if (NewUI.check.isNotBlank(nameText.getText())
									&& NewUI.check.isNotBlank(addressText
											.getText())
									&& NewUI.check.isNotBlank(phoneText
											.getText())) {
								c.setName(nameText.getText());
								c.setAddress(addressText.getText());
								c.setPhoneNumber(phoneText.getText());
								NewUI.currentActiveScreen = e
										.getActionCommand();

								GUI_CustomerScreen customerScreen = new GUI_CustomerScreen();
								customerScreen.customerScreen();
								NewUI.selectedCustomerID = 0;
								customerViewScreenRefresh();
								break;
							}
						}
					}
				}
			});
		}
		botJP.add(middleDetailsJP);

		// ************Invoices Panel*************************

		DefaultTableModel model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;// This causes all cells to be not editable
			}
		};
		model.addColumn("Invoice ID");
		model.addColumn("Total number of items");
		model.addColumn("Invoice Cost");

		// Populate rows with invoice data
		for (Invoice i : customer.getInvoices(NewUI.db.getInvoices())) {
			Vector<String> singleInvoice = new Vector<String>();
			singleInvoice.add(Integer.toString(i.getId()));
			singleInvoice.add(String.valueOf(i.calculateTotalNumberItems()));
			singleInvoice.add(String.valueOf(i.calculateTotalRetailValue()));
			model.addRow(singleInvoice);
		}
		final JTable invoiceTable = new JTable();
		invoiceTable.setAutoCreateRowSorter(true);
		invoiceTable.setModel(model);

		// double click to get more information
		invoiceTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					NewUI.selectedInvoiceID = Integer.parseInt(invoiceTable
							.getValueAt(invoiceTable.getSelectedRow(), 0)
							.toString());

					GUI_InvoiceViewScreen gui_InvoiceViewScreen = new GUI_InvoiceViewScreen();
					gui_InvoiceViewScreen.invoiceViewScreen();

					CardLayout cl = (CardLayout) (NewUI.gui.getLayout());
					cl.show(NewUI.gui, "invoiceView");
				}
			}
		});

		middleInvoicesJP.setLayout(new BorderLayout());

		// Populate invoices panel with labels and table
		middleInvoicesJP.add(new JLabel("Invoices:"), BorderLayout.NORTH);
		middleInvoicesJP
				.add(new JScrollPane(invoiceTable), BorderLayout.CENTER);

		middleInvoicesJP.add(
				new JLabel("Total money spent: "
						+ customer.getTotalMoneySpent(NewUI.db.getInvoices())),
				BorderLayout.SOUTH);

		botJP.add(middleInvoicesJP);

		// ************Bottom Panel*************************

		// Create back button and set action listener
		JButton backButton = new JButton("Back");
		backButton.setActionCommand(customerScreenAccess);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewUI.currentActiveScreen = e.getActionCommand();

				GUI_CustomerScreen customerScreen = new GUI_CustomerScreen();
				customerScreen.customerScreen();

				CardLayout cl = (CardLayout) (NewUI.gui.getLayout());
				cl.show(NewUI.gui, e.getActionCommand());
			}
		});
		buttonPanel.add(backButton);

		/*
		 * Delete button: When edit is pressed, code cycles through customers,
		 * finds the one with the corresponding ID to what's selected in the
		 * table (selectedCustomerID) and deletes the customer accordingly
		 */
		// If current logged-in user is not admin, hide delete button
		if (NewUI.currentUser.isAdmin()) {
			JButton deleteButton = new JButton("Delete Customer");
			deleteButton.setActionCommand(customerScreenAccess);
			buttonPanel.add(deleteButton);
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (Customer c : NewUI.db.getCustomers()) {
						if (NewUI.selectedCustomerID == c.getId()) {
							NewUI.db.getCustomers().remove(c);
							NewUI.currentActiveScreen = e.getActionCommand();

							for (Invoice invoice : NewUI.db.getInvoices()) {
								if (NewUI.selectedCustomerID == invoice
										.getCustomer().getId()) {
									String oldName = invoice.getCustomer()
											.getName();
									invoice.getCustomer().setName(
											oldName + " <Deleted>");
									break;
								}
							}
							NewUI.selectedCustomerID = 0;

							GUI_CustomerScreen customerScreen = new GUI_CustomerScreen();
							customerScreen.customerScreen();
							CardLayout cl = (CardLayout) (NewUI.gui.getLayout());
							cl.show(NewUI.gui, e.getActionCommand());

							break;
						}
					}
				}
			});
		}

		// FOOTER
		// Adds the top, bottom and button panels to the screen panel
		screen.add(topJP, BorderLayout.NORTH);
		screen.add(botJP, BorderLayout.CENTER);
		screen.add(buttonPanel, BorderLayout.SOUTH);

		// Assigns the MainUI gui panel the contents of the screen panel
		NewUI.customerViewScreen = screen;
		NewUI.gui.add(NewUI.customerViewScreen, customerViewScreenAccess);
		// FOOTER
	}

	public void customerViewScreenRefresh() {
		NewUI.customerViewScreen.removeAll();
		customerViewScreen();

		CardLayout cl = (CardLayout) (NewUI.gui.getLayout());
		cl.show(NewUI.gui, NewUI.currentActiveScreen);
	}

}
