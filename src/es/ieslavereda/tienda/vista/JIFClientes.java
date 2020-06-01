package es.ieslavereda.tienda.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import es.ieslavereda.tienda.classes.Cliente;
import net.miginfocom.swing.MigLayout;

public class JIFClientes extends JInternalFrame {
	
	private JTable tableClient;
	private JButton btnAddClient;
	private JButton btnDelClient;
	private JButton btnEditClient;
	private JComboBox comboBoxWhereClient;
	private JComboBox comboBoxOrdenClient;
	private JButton btnSearchClient;
	private JTextField textFieldSearchClient;

	/**
	 * Create the frame.
	 */
	public JIFClientes() {
		setClosable(true);
		setTitle("Clientes");
		setBounds(100, 100, 688, 423);
		
		JPanel panelTabla = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panelBotones = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelBotones, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
						.addComponent(panelTabla, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelTabla, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panelBotones, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(new MigLayout("", "[][grow][][][92.00][][][96.00][][]", "[]"));
		
		JLabel lblNewLabel_2 = new JLabel("Search");
		panel_1.add(lblNewLabel_2, "cell 0 0,alignx trailing");
		
		textFieldSearchClient = new JTextField();
		panel_1.add(textFieldSearchClient, "cell 1 0,growx");
		textFieldSearchClient.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Campo");
		panel_1.add(lblNewLabel, "cell 3 0,alignx trailing");
		
		comboBoxWhereClient = new JComboBox();
		comboBoxWhereClient.setModel(new DefaultComboBoxModel(new String[] {"ID", "NOMBRE", "APELLIDOS", "DNI","FECHA DE NACIMIENTO"}));
		panel_1.add(comboBoxWhereClient, "cell 4 0,growx");
		
		JLabel lblNewLabel_1 = new JLabel("Orden");
		panel_1.add(lblNewLabel_1, "cell 6 0,alignx trailing");
		
		comboBoxOrdenClient = new JComboBox();
		comboBoxOrdenClient.setModel(new DefaultComboBoxModel(new String[] {"Ascendente", "Descendente"}));
		panel_1.add(comboBoxOrdenClient, "cell 7 0,growx");
		
		btnSearchClient = new JButton("Search");
		panel_1.add(btnSearchClient, "cell 9 0");
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnCancelClient = new JButton("Cancel");
		btnCancelClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnAddClient = new JButton("Add");
		panelBotones.add(btnAddClient);
		
		btnDelClient = new JButton("Delete");
		panelBotones.add(btnDelClient);
		
		btnEditClient = new JButton("Edit");
		panelBotones.add(btnEditClient);
		panelBotones.add(btnCancelClient);
		panelTabla.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);
		
		tableClient = new JTable();
		scrollPane.setViewportView(tableClient);
		getContentPane().setLayout(groupLayout);

	}

	public JTable getTableClient() {
		return tableClient;
	}

	public JButton getBtnAddClient() {
		return btnAddClient;
	}

	public JButton getBtnDelClient() {
		return btnDelClient;
	}

	public JButton getBtnEditClient() {
		return btnEditClient;
	}

	public JComboBox getComboBoxWhereClient() {
		return comboBoxWhereClient;
	}

	public JComboBox getComboBoxOrdenClient() {
		return comboBoxOrdenClient;
	}

	public JButton getBtnSearchClient() {
		return btnSearchClient;
	}

	public JTextField getTextFieldSearchClient() {
		return textFieldSearchClient;
	}
	
}
