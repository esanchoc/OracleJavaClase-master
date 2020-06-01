package es.ieslavereda.tienda.vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import es.ieslavereda.tienda.classes.Cliente;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFFormularioCliente extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldDNI;
	private JTextField textFieldFechaNacimiento;
	private JButton btnActionClients;
	private Cliente cliente;
	/**
	 * Create the frame.
	 */
	public JIFFormularioCliente() {
		setTitle("Cliente");
		setClosable(true);
		setBounds(100, 100, 599, 395);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_5 = new JLabel("FOTO");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(110, Short.MAX_VALUE)
					.addComponent(lblNewLabel_5)
					.addGap(104))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(113)
					.addComponent(lblNewLabel_5)
					.addContainerGap(128, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnActionClients = new JButton("Action");
		panel_2.add(btnActionClients);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(btnCancel);
		panel_1.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		panel_1.add(lblNewLabel_1, "cell 0 2,alignx trailing");
		
		textFieldNombre = new JTextField();
		panel_1.add(textFieldNombre, "cell 1 2,growx");
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		panel_1.add(lblNewLabel_2, "cell 0 3,alignx trailing");
		
		textFieldApellidos = new JTextField();
		panel_1.add(textFieldApellidos, "cell 1 3,growx");
		textFieldApellidos.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("DNI");
		panel_1.add(lblNewLabel_3, "cell 0 4,alignx trailing");
		
		textFieldDNI = new JTextField();
		panel_1.add(textFieldDNI, "cell 1 4,growx");
		textFieldDNI.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha de nacimiento");
		panel_1.add(lblNewLabel_4, "cell 0 5,alignx trailing");
		
		textFieldFechaNacimiento = new JTextField();
		panel_1.add(textFieldFechaNacimiento, "cell 1 5,growx");
		textFieldFechaNacimiento.setColumns(10);
		getContentPane().setLayout(groupLayout);

	}

	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}
	public JTextField getTextFieldApellidos() {
		return textFieldApellidos;
	}
	public JTextField getTextFieldDNI() {
		return textFieldDNI;
	}
	public JTextField getTextFieldFechaNacimiento() {
		return textFieldFechaNacimiento;
	}
	public JButton getBtnActionClients() {
		return btnActionClients;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
