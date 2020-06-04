package es.ieslavereda.tienda.vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFIva extends JInternalFrame {
	private JTable tableIVA;
	private JButton btnCancel;
	private JButton btnDeleteIVA;
	private JButton btnEditIVA;
	private JButton btnAddIVA;
	/**
	 * Create the frame.
	 */
	public JIFIva() {
		setTitle("IVA");
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
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnAddIVA = new JButton("Add");
		panel_1.add(btnAddIVA);
		
		btnEditIVA = new JButton("Edit");
		panel_1.add(btnEditIVA);
		
		btnDeleteIVA = new JButton("Delete");
		panel_1.add(btnDeleteIVA);
		panel_1.add(btnCancel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tableIVA = new JTable();
		scrollPane.setViewportView(tableIVA);
		getContentPane().setLayout(groupLayout);

	}
	public JTable getTableIVA() {
		return tableIVA;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	public JButton getBtnDeleteIVA() {
		return btnDeleteIVA;
	}
	public JButton getBtnEditIVA() {
		return btnEditIVA;
	}
	public JButton getBtnAddIVA() {
		return btnAddIVA;
	}
}
