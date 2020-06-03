package es.ieslavereda.tienda.vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import es.ieslavereda.tienda.classes.Categories;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFFormularioCategories extends JInternalFrame {
	
	Categories categories;
	private JTextField textFieldIDCategories;
	private JTextField textFieldDescripcionCategories;
	private JButton btnAction;
	/**
	 * Create the frame.
	 */
	public JIFFormularioCategories() {
		setTitle("Category");
		setClosable(true);
		setBounds(100, 100, 450, 180);
		
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		btnAction = new JButton("Action");
		panel_1.add(btnAction);
		panel_1.add(btnCancel);
		panel.setLayout(new MigLayout("", "[][][][][grow]", "[]"));
		
		JLabel lblNewLabel = new JLabel("ID");
		panel.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		textFieldIDCategories = new JTextField();
		panel.add(textFieldIDCategories, "cell 1 0,growx");
		textFieldIDCategories.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n");
		panel.add(lblNewLabel_1, "cell 3 0,alignx trailing");
		
		textFieldDescripcionCategories = new JTextField();
		panel.add(textFieldDescripcionCategories, "cell 4 0,growx");
		textFieldDescripcionCategories.setColumns(10);
		getContentPane().setLayout(groupLayout);

	}
	public Categories getCategories() {
		return categories;
	}
	public void setCategories(Categories categories) {
		this.categories = categories;
	}
	public JTextField getTextFieldIDCategories() {
		return textFieldIDCategories;
	}
	public JTextField getTextFieldDescripcionCategories() {
		return textFieldDescripcionCategories;
	}
	public JButton getBtnAction() {
		return btnAction;
	}
}
