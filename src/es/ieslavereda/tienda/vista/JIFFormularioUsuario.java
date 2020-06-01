package es.ieslavereda.tienda.vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import es.ieslavereda.tienda.classes.Usuario;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class JIFFormularioUsuario extends JInternalFrame {
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JTextField textFieldMail;
	private JButton btnAction;
	private JComboBox comboBoxRole;
	private Usuario u;
	/**
	 * Create the frame.
	 */
	public JIFFormularioUsuario() {
		setTitle("Usuario");
		setClosable(true);
		setBounds(100, 100, 270, 246);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAction = new JButton("Action");
		panel_1.add(btnAction);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_1.add(btnNewButton);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(91dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Login");
		panel.add(lblNewLabel, "2, 4, right, default");
		
		textFieldLogin = new JTextField();
		panel.add(textFieldLogin, "4, 4, fill, default");
		textFieldLogin.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		panel.add(lblNewLabel_1, "2, 6, right, default");
		
		passwordField = new JPasswordField();
		panel.add(passwordField, "4, 6, fill, default");
		
		JLabel lblNewLabel_2 = new JLabel("E-mail");
		panel.add(lblNewLabel_2, "2, 8, right, default");
		
		textFieldMail = new JTextField();
		panel.add(textFieldMail, "4, 8, fill, default");
		textFieldMail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Role");
		panel.add(lblNewLabel_3, "2, 10, right, default");
		
		comboBoxRole = new JComboBox();
		comboBoxRole.setModel(new DefaultComboBoxModel(new String[] {"Admin", "User"}));
		panel.add(comboBoxRole, "4, 10, fill, default");
		getContentPane().setLayout(groupLayout);

	}
	public JTextField getTextFieldLogin() {
		return textFieldLogin;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public JTextField getTextFieldMail() {
		return textFieldMail;
	}
	public JButton getBtnAction() {
		return btnAction;
	}
	public JComboBox getComboBoxRole() {
		return comboBoxRole;
	}
	public Usuario getUsuario() {
		return u;
	}
	public void setUsuario(Usuario u) {
		this.u = u;
	}
	
	
}
