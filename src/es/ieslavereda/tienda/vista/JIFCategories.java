package es.ieslavereda.tienda.vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import es.ieslavereda.tienda.classes.Categories;
import es.ieslavereda.tienda.classes.IVA;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class JIFCategories extends JInternalFrame {
	
	private Categories categoria;
	private JButton btnDeleteCategories;
	private JButton btnEditCategories;
	private JButton btnAddCategories;
	private JTable tableCategories;
	
	public JIFCategories() {
		setTitle("Categories");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnAddCategories = new JButton("Add");
		panel_1.add(btnAddCategories);
		
		btnEditCategories = new JButton("Edit");
		panel_1.add(btnEditCategories);
		
		btnDeleteCategories = new JButton("Delete");
		panel_1.add(btnDeleteCategories);
		panel_1.add(btnCancel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tableCategories = new JTable();
		scrollPane.setViewportView(tableCategories);
		getContentPane().setLayout(groupLayout);

	}

	public Categories getCategoria() {
		return categoria;
	}

	public void setCategoria(Categories categoria) {
		this.categoria = categoria;
	}

	public JButton getBtnDeleteCategories() {
		return btnDeleteCategories;
	}

	public JButton getBtnEditCategories() {
		return btnEditCategories;
	}

	public JButton getBtnAddCategories() {
		return btnAddCategories;
	}

	public JTable getTableCategories() {
		return tableCategories;
	}
}
