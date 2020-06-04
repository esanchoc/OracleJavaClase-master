/**
 * 
 */
package es.ieslavereda.tienda.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * Creado el 27 mar. 2019
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso Saiz</a>
 *
 */
public class JFramePrincipal extends JFrame {

	private JPanel contentPane;
	private JButton btnListClients;
	public JDesktopPane desktopPane;
	private JButton btnUsers;
	private JButton btnLogin;
	private JButton btnSalir;
	private JButton btnReport;
	private JButton btnCategories;
	private JButton btnIVA;

	/**
	 * Create the frame.
	 */
	public JFramePrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/logo_compacto.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1006, 652);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		btnLogin = new JButton("Login");
		getBtnLogin().setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/login.png")));
		toolBar.add(getBtnLogin());
		
		btnUsers = new JButton("Users");
		getBtnUsers().setEnabled(false);
		getBtnUsers().setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/alta_usuario.png")));
		toolBar.add(getBtnUsers());
		
		btnListClients = new JButton("Clients");
		getBtnListClients().setEnabled(false);
		getBtnListClients().setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/usuarios.png")));
		toolBar.add(getBtnListClients());
		
		btnCategories = new JButton("Categories");
		btnCategories.setEnabled(false);
		btnCategories.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/categories.png")));
		toolBar.add(btnCategories);
		
		btnIVA = new JButton("IVA");
		btnIVA.setEnabled(false);
		btnIVA.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/porcentaje25x25.png")));
		toolBar.add(btnIVA);
		
		btnReport = new JButton("Report");
		getBtnReport().setEnabled(false);
		getBtnReport().setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/Printer.png")));
		toolBar.add(getBtnReport());
		
		btnSalir = new JButton("Salir");
		getBtnSalir().setEnabled(false);
		getBtnSalir().setIcon(new ImageIcon(JFramePrincipal.class.getResource("/es/ieslavereda/tienda/images/salir.png")));
		toolBar.add(getBtnSalir());
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	public JButton getBtnListClients() {
		return btnListClients;
	}

	public JButton getBtnUsers() {
		return btnUsers;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JButton getBtnReport() {
		return btnReport;
	}

	public JButton getBtnCategories() {
		return btnCategories;
	}

	public JButton getBtnIVA() {
		return btnIVA;
	}
}
