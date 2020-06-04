package es.ieslavereda.tienda.vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import es.ieslavereda.tienda.classes.IVA;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFFormularioIVA extends JInternalFrame {
	private JTextField textFieldPorcentaje;
	private JTextField textFieldDescripcionIVA;
	private JButton btnAction;
	private IVA iva;
	/**
	 * Create the frame.
	 */
	public JIFFormularioIVA() {
		setTitle("IVA");
		setClosable(true);
		setBounds(100, 100, 450, 166);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(140, Short.MAX_VALUE))
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
		panel.setLayout(new MigLayout("", "[][][][][][]", "[]"));
		
		JLabel lbl = new JLabel("Porcentaje");
		panel.add(lbl, "cell 1 0,alignx trailing");
		
		textFieldPorcentaje = new JTextField();
		panel.add(textFieldPorcentaje, "cell 2 0,growx");
		textFieldPorcentaje.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Descripci\u00F3n");
		panel.add(lblNewLabel, "cell 4 0,alignx trailing");
		
		textFieldDescripcionIVA = new JTextField();
		panel.add(textFieldDescripcionIVA, "cell 5 0,growx");
		textFieldDescripcionIVA.setColumns(10);
		getContentPane().setLayout(groupLayout);

	}
	public JTextField getTextFieldPorcentaje() {
		return textFieldPorcentaje;
	}
	public JTextField getTextFieldDescripcionIVA() {
		return textFieldDescripcionIVA;
	}
	public JButton getBtnAction() {
		return btnAction;
	}
	public IVA getIva() {
		return iva;
	}
	public void setIva(IVA iva) {
		this.iva = iva;
	}
	
}
