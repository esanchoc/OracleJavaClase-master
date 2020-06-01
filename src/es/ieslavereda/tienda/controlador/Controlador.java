/**
 * 
 */
package es.ieslavereda.tienda.controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import es.ieslavereda.tienda.classes.Cliente;
import es.ieslavereda.tienda.classes.Usuario;
import es.ieslavereda.tienda.modelo.Modelo;
import es.ieslavereda.tienda.vista.JFramePrincipal;
import es.ieslavereda.tienda.vista.JIFClientes;
import es.ieslavereda.tienda.vista.JIFFormularioCliente;
import es.ieslavereda.tienda.vista.JIFFormularioUsuario;
import es.ieslavereda.tienda.vista.JIFLogin;
import es.ieslavereda.tienda.vista.JIFUsuarios;

/**
 * Creado el 27 mar. 2019
 * 
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso
 *         Saiz</a>
 *
 */
public class Controlador implements ActionListener {

	private JFramePrincipal view;
	private Modelo modelo;

	// formularios hijo
	JIFLogin jifLogin;
	JIFUsuarios jifUsers;
	JIFFormularioUsuario jifformulariousuario;
	JIFClientes jifclientes;
	JIFFormularioCliente jifformulariocliente;

	public Controlador(JFramePrincipal view, Modelo modelo) {
		this.view = view;
		this.modelo = modelo;
		iniciar();
	}

	public void iniciar() {

		view.setTitle("Aplicacion MVC 1ºDAW");

		// AÃ±adir las accionew a los botones del formulario padre
		view.btnListClients.setActionCommand("Listar clientes");
		view.btnUsers.setActionCommand("Abrir formulario gestion usuarios");
		view.btnLogin.setActionCommand("Abrir formulario login");
		view.btnSalir.setActionCommand("Cerrar sesion");
		view.btnReport.setActionCommand("Report");
		

		// Ponemos a escuchar las accionew del usuario
		view.btnListClients.addActionListener(this);
		view.btnUsers.addActionListener(this);
		view.btnLogin.addActionListener(this);
		view.btnSalir.addActionListener(this);
		view.btnReport.addActionListener(this);

	}

	public void start() {
		view.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();
		if (comando.equals("Listar clientes")) {
			openJIFClientes();
		} else if (comando.equals("Cerrar formulario listado usuarios")) {
			
		} else if (comando.equals("Abrir formulario gestion usuarios")) {
			openJIFUsers();
		} else if (comando.equals("Abrir formulario login")) {
			abrirFormularioLogin();
		} else if (comando.equals("Loguear")) {
			loguearUsuario();
		} else if (comando.equals("Cerrar sesion")) {
			cerrarSesion();
		} else if (comando.equals("")) {
			
		} else if (comando.equals("Actualizar Usuario")) {
			openEditUserInternalFrame();
		} else if (comando.equals("Report")) {
			
		} else if (comando.equals("Add user")) {
			openAddUserInternalFrame();
		} else if (comando.equals("Del users")) {
			delUsers();
		} else if (comando.equals("Add new user")) {
			addNewUser();
		} else if (comando.equals("Edit user")) {
			saveEditUser();
		} else if (comando.equals("ordenar")) {
			actualizarTablaUsuarios();
		} else if (comando.equals("Add client")) {
			openJIFFormularioClienteAdd();
		} else if (comando.equals("Del client")) {
			deleteCliente();
		} else if (comando.equals("Actualizar client")) {
			openJIFFormularioClienteEdit();
		} else if (comando.equals("ordenar clientes")) {
			actualizarTablaClientes();
		} else if (comando.equals("Add new cliente")) {
			addNewCliente();
		} else if (comando.equals("edit new cliente")) {
			editCliente();
		}

	}


	private void editCliente() {
		
		int option = JOptionPane.showConfirmDialog(jifformulariocliente, "Esta seguro que quiere actualizar este cliente?", "Question", JOptionPane.YES_NO_OPTION);
		
		if(option == JOptionPane.YES_OPTION) {
			
			jifformulariocliente.getCliente().setNombre(jifformulariocliente.getTextFieldNombre().getText());
			jifformulariocliente.getCliente().setApellidos(jifformulariocliente.getTextFieldApellidos().getText());
			jifformulariocliente.getCliente().setDNI(jifformulariocliente.getTextFieldDNI().getText());
			jifformulariocliente.getCliente().setFechaNacimiento(jifformulariocliente.getTextFieldFechaNacimiento().getText());
			
			if(modelo.actualizarCliente(jifformulariocliente.getCliente())) {
				JOptionPane.showMessageDialog(jifformulariocliente, "El usuario ha sido actualizado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
				jifformulariocliente.dispose();
				actualizarTablaClientes();
			} else {
				JOptionPane.showMessageDialog(jifformulariocliente, "Ha surgido un error a la hora de actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		
	}

	private void openJIFFormularioClienteEdit() {
		
		int option = jifclientes.getTableClient().getSelectedRow();
		
		if(option == -1) {
			JOptionPane.showMessageDialog(jifclientes, "Debe seleccionar primero una fila", "Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
		
			if(!estaAbierto(jifformulariocliente)) {
				
				jifformulariocliente = new JIFFormularioCliente();
				view.desktopPane.add(jifformulariocliente);
				jifformulariocliente.setVisible(true);
				
				jifformulariocliente.getBtnActionClients().setText("Save");
				jifformulariocliente.getBtnActionClients().addActionListener(this);
				jifformulariocliente.getBtnActionClients().setActionCommand("edit new cliente");
				
				int id = Integer.parseInt(jifclientes.getTableClient().getValueAt(jifclientes.getTableClient().getSelectedRow(), 0).toString());
				
				jifformulariocliente.setCliente(modelo.obtenerClienteByID(id));
				
				jifformulariocliente.getTextFieldNombre().setText(jifformulariocliente.getCliente().getNombre());
				jifformulariocliente.getTextFieldApellidos().setText(jifformulariocliente.getCliente().getApellidos());
				jifformulariocliente.getTextFieldDNI().setText(jifformulariocliente.getCliente().getDNI());
				jifformulariocliente.getTextFieldFechaNacimiento().setText(jifformulariocliente.getCliente().getFechaNacimiento());
				
				
			}
		
		}
		
	}

	private void deleteCliente() {
		
		int [] filas = jifclientes.getTableClient().getSelectedRows();
		int option;
		int id;
		
		if(filas.length == 0) {
			
			JOptionPane.showMessageDialog(jifclientes, "Debe seleccionar una o más filas para eliminar clientes", "Error", JOptionPane.ERROR_MESSAGE);
			
		} else {
			
			if (filas.length == 1) {
				
				option = JOptionPane.showConfirmDialog(jifclientes,"Esta seguro de eliminar el cliente seleccionado?", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
			} else {
				
				option = JOptionPane.showConfirmDialog(jifclientes,"Esta seguro de eliminar "+ filas.length +" clientes seleccionados?", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}
			
			if(option == JOptionPane.YES_OPTION) {
				
				for (int fila : filas) {
					
					id = Integer.parseInt(jifclientes.getTableClient().getValueAt(fila,0).toString());
					modelo.eliminarCliente(id);
					
				}
				
				actualizarTablaClientes();
				
			}
			
		}
		
	}

	private void addNewCliente() {
		
		int option = JOptionPane.showConfirmDialog(jifformulariocliente, "Esta seguro que quiere añadir este cliente?", "Question", JOptionPane.YES_NO_OPTION);
		
		if (option == JOptionPane.YES_OPTION) {
			
			Cliente c;
			
			String nombre = jifformulariocliente.getTextFieldNombre().getText();
			String apellidos = jifformulariocliente.getTextFieldApellidos().getText();
			String DNI = jifformulariocliente.getTextFieldDNI().getText();
			String fechaNacimiento = jifformulariocliente.getTextFieldFechaNacimiento().getText();
			
			c = new Cliente(nombre,apellidos,DNI,fechaNacimiento);
			
			if(modelo.insertarCliente(c)) {
				JOptionPane.showMessageDialog(jifformulariocliente, "El usuario se ha insertado de forma correcta", "Info", JOptionPane.INFORMATION_MESSAGE);
				jifformulariocliente.dispose();
				actualizarTablaClientes();
			} else {
				JOptionPane.showMessageDialog(jifformulariocliente, "Ha surgido un problema a la hora de insertar el usuario","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		
	}

	private void openJIFFormularioClienteAdd() {
		
		if(!estaAbierto(jifformulariocliente)) {
			
			jifformulariocliente = new JIFFormularioCliente();
			view.desktopPane.add(jifformulariocliente);
			jifformulariocliente.setVisible(true);
			
			jifformulariocliente.getBtnActionClients().setText("Add");
			jifformulariocliente.getBtnActionClients().addActionListener(this);
			jifformulariocliente.getBtnActionClients().setActionCommand("Add new cliente");
		}
		
	}

	private void openJIFClientes() {
		
		if (!estaAbierto(jifclientes)) {
			
			jifclientes = new JIFClientes();
			
			view.desktopPane.add(jifclientes);
			jifclientes.setVisible(true);
			
			jifclientes.getBtnAddClient().addActionListener(this);
			jifclientes.getBtnDelClient().addActionListener(this);
			jifclientes.getBtnEditClient().addActionListener(this);
			jifclientes.getComboBoxWhereClient().addActionListener(this);
			jifclientes.getComboBoxOrdenClient().addActionListener(this);
			jifclientes.getBtnSearchClient().addActionListener(this);
			
			jifclientes.getBtnAddClient().setActionCommand("Add client");
			jifclientes.getBtnDelClient().setActionCommand("Del client");
			jifclientes.getBtnEditClient().setActionCommand("Actualizar client");
			jifclientes.getComboBoxWhereClient().setActionCommand("ordenar clientes");
			jifclientes.getComboBoxOrdenClient().setActionCommand("ordenar clientes");
			jifclientes.getBtnSearchClient().setActionCommand("ordenar clientes");
			
			// Cargamos usuarios en la tabla
			
			actualizarTablaClientes();
		}
		
	}

	private void actualizarTablaClientes() {
		
		String where = "";
		String order;
		
		if(!jifclientes.getTextFieldSearchClient().getText().trim().isEmpty()) {
			where += (jifclientes.getComboBoxWhereClient().getSelectedItem().toString().equals("FECHA DE NACIMIENTO"))? "fecha_nacimiento":jifclientes.getComboBoxWhereClient().getSelectedItem().toString() + " LIKE '%" + jifclientes.getTextFieldSearchClient().getText() + "%' ";
		}
		
		order = (jifclientes.getComboBoxWhereClient().getSelectedItem().toString().equals("FECHA DE NACIMIENTO"))? "fecha_nacimiento":jifclientes.getComboBoxWhereClient().getSelectedItem().toString();
		order += (jifclientes.getComboBoxOrdenClient().getSelectedItem().toString().equals("Ascendente"))?" ASC":" DESC";
		
		ArrayList<Cliente> clientes = modelo.obtenerClientes(where, order);
		
		actualizarTablaClientes(clientes);
	}

	private void actualizarTablaClientes(ArrayList<Cliente> clientes) {
		
		DefaultTableModel dtm = new DefaultTableModel();
		Vector<String> rowData;
		
		dtm.addColumn("ID");
		dtm.addColumn("NOMBRE");
		dtm.addColumn("APELLIDOS");
		dtm.addColumn("DNI");
		dtm.addColumn("FECHA DE NACIMIENTO");
		
		for(Cliente c : clientes) {
			
			rowData= new Vector<String>();
			
			rowData.add(String.valueOf(c.getID()));
			rowData.add(c.getNombre());
			rowData.add(c.getApellidos());
			rowData.add(c.getDNI());
			rowData.add(c.getFechaNacimiento());
			
			dtm.addRow(rowData);
			
		}
		
		jifclientes.getTableClient().setModel(dtm);
		
	}

	private void actualizarTablaUsuarios() {
		
		String where="";
		String order;
		
		if(!jifUsers.getTextFieldSearch().getText().trim().isEmpty()) {
			where += jifUsers.getComboBoxWhere().getSelectedItem().toString() + " LIKE '%" + jifUsers.getTextFieldSearch().getText() + "%' ";
		}
		
		order = jifUsers.getComboBoxWhere().getSelectedItem().toString();
		order += (jifUsers.getComboBoxOrden().getSelectedItem().toString().equals("Ascendente"))?" ASC":" DESC";
		
		ArrayList<Usuario> usuarios = modelo.obtenerUsuarios(where, order);
		
		actualizarTablaUsuarios(usuarios);
		
	}

	private void saveEditUser() {
		
		int option = JOptionPane.showConfirmDialog(jifformulariousuario, "Esta usted seguro de actualizar este usuario?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(option == JOptionPane.YES_OPTION) {
			
			jifformulariousuario.getUsuario().setLogin(jifformulariousuario.getTextFieldLogin().getText());
			jifformulariousuario.getUsuario().setPassword(String.valueOf(jifformulariousuario.getPasswordField().getPassword()));
			jifformulariousuario.getUsuario().setMail(jifformulariousuario.getTextFieldMail().getText());
			jifformulariousuario.getUsuario().setRole(jifformulariousuario.getComboBoxRole().getSelectedItem().toString());
			
			if(modelo.actualizarUsuario(jifformulariousuario.getUsuario())) {
				
				JOptionPane.showMessageDialog(jifformulariousuario, "Usuario actualizado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
				actualizarTablaUsuarios();
				jifformulariousuario.dispose();
				
			} else {
				
				JOptionPane.showMessageDialog(jifformulariousuario, "Error al actualizar este usuario", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		
		
	}

	private void openEditUserInternalFrame() {
		
		jifformulariousuario = new JIFFormularioUsuario();
		
		jifformulariousuario.getBtnAction().setText("Save");
		
		jifformulariousuario.getBtnAction().addActionListener(this);
		jifformulariousuario.getBtnAction().setActionCommand("Edit user");
		
		Usuario u = modelo.obtenerUsuarioByID(Integer.parseInt(jifUsers.getTableUsers().getValueAt(jifUsers.getTableUsers().getSelectedRow(), 0).toString()));
		
		jifformulariousuario.setUsuario(u);
		
		jifformulariousuario.getTextFieldLogin().setText(jifformulariousuario.getUsuario().getLogin());
		jifformulariousuario.getPasswordField().setText(jifformulariousuario.getUsuario().getPassword());
		jifformulariousuario.getTextFieldMail().setText(jifformulariousuario.getUsuario().getMail());
		jifformulariousuario.getComboBoxRole().setSelectedItem(jifformulariousuario.getUsuario().getRole());
		
		jifformulariousuario.setVisible(true);
		
		view.desktopPane.add(jifformulariousuario);
		
	}

	private void addNewUser() {
		
		int option = JOptionPane.showConfirmDialog(jifformulariousuario, "Esta usted seguro de añadir este usuario?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if (option == JOptionPane.YES_OPTION) {
			
			String login = jifformulariousuario.getTextFieldLogin().getText().replaceAll(" ", "");
			String password = String.valueOf(jifformulariousuario.getPasswordField().getPassword());
			String mail = jifformulariousuario.getTextFieldMail().getText();
			String role = jifformulariousuario.getComboBoxRole().getSelectedItem().toString();
			
			if(modelo.insertarUsuario(new Usuario(login,password,mail,role))) {
				
				JOptionPane.showMessageDialog(jifformulariousuario, "Usuario insertado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
				actualizarTablaUsuarios();
				jifformulariousuario.dispose();
				
			} else {
				
				JOptionPane.showMessageDialog(jifformulariousuario, "Error en la inserción de usuario", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
	}

	private void delUsers() {
		
		int [] filas = jifUsers.getTableUsers().getSelectedRows();
		int option;
		int id;
		
		if(filas.length == 0) {
			
			JOptionPane.showMessageDialog(jifUsers, "Debe seleccionar una o más filas para eliminar usuarios", "Error", JOptionPane.ERROR_MESSAGE);
			
		} else {
			
			if (filas.length == 1) {
				
				option = JOptionPane.showConfirmDialog(jifUsers,"Esta seguro de eliminar el usuario seleccionado?", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
			} else {
				
				option = JOptionPane.showConfirmDialog(jifUsers,"Esta seguro de eliminar "+ filas.length +" usuarios seleccionados?", "Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}
			
			if(option == JOptionPane.YES_OPTION) {
				
				for (int fila : filas) {
					
					id = Integer.parseInt(jifUsers.getTableUsers().getValueAt(fila,0).toString());
					modelo.eliminarUsuario(id);
					
				}
				
				actualizarTablaUsuarios();
				
			}
			
		}
		
	}

	private void openAddUserInternalFrame() {
		
		jifformulariousuario = new JIFFormularioUsuario();
		
		jifformulariousuario.getBtnAction().setText("Add");
		
		jifformulariousuario.getBtnAction().addActionListener(this);
		jifformulariousuario.getBtnAction().setActionCommand("Add new user");
		
		jifformulariousuario.setVisible(true);
		
		view.desktopPane.add(jifformulariousuario);
	}

	private void openJIFUsers() {
		
		if (!estaAbierto(jifUsers)) {
			
			jifUsers = new JIFUsuarios();
			
			view.desktopPane.add(jifUsers);
			jifUsers.setVisible(true);
			
			jifUsers.getBtnAddUser().addActionListener(this);
			jifUsers.getBtnDelUser().addActionListener(this);
			jifUsers.getBtnEditUser().addActionListener(this);
			jifUsers.getComboBoxWhere().addActionListener(this);
			jifUsers.getComboBoxOrden().addActionListener(this);
			jifUsers.getBtnSearch().addActionListener(this);
			
			jifUsers.getBtnAddUser().setActionCommand("Add user");
			jifUsers.getBtnDelUser().setActionCommand("Del users");
			jifUsers.getBtnEditUser().setActionCommand("Actualizar Usuario");
			jifUsers.getComboBoxWhere().setActionCommand("ordenar");
			jifUsers.getComboBoxOrden().setActionCommand("ordenar");
			jifUsers.getBtnSearch().setActionCommand("ordenar");
			
			// Cargamos usuarios en la tabla
			
			actualizarTablaUsuarios();
		}
		
	}
	
	private void actualizarTablaUsuarios(ArrayList<Usuario> usuarios) {
		
		Vector<String> rowData;
		
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("ID");
		dtm.addColumn("Login");
		dtm.addColumn("E-mail");
		dtm.addColumn("Role");
		
		for(Usuario u : usuarios) {
			
			rowData= new Vector<String>();
			
			rowData.add(String.valueOf(u.getId()));
			rowData.add(u.getLogin());
			rowData.add(u.getMail());
			rowData.add(u.getRole());
			
			dtm.addRow(rowData);
			
		}
		
		jifUsers.getTableUsers().setModel(dtm);		
		
	}

	private boolean estaAbierto(JInternalFrame jif) {
		boolean abierto = false;
		JInternalFrame[] internalFrames = view.desktopPane.getAllFrames();

		for (JInternalFrame internalFrame : internalFrames)
			if (jif == internalFrame)
				abierto = true;

		return abierto;
	}

	private void cerrarSesion() {

		int option = JOptionPane.showConfirmDialog(view, "Esta seguro de cerrar sesion?", "Cerrar sesion",
				JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			view.btnListClients.setEnabled(false);
			view.btnUsers.setEnabled(false);
			view.btnLogin.setEnabled(true);
			view.btnSalir.setEnabled(false);
			view.btnReport.setEnabled(false);

			JInternalFrame[] internalFrames = view.desktopPane.getAllFrames();

			for (JInternalFrame internalFrame : internalFrames)
				internalFrame.dispose();
		}

	}

	private void loguearUsuario() {
		String login = jifLogin.txtFieldLogin.getText();
		String password = String.valueOf(jifLogin.passwordField.getPassword());

		try {
			if (modelo.validar(login, password)) {
				view.btnUsers.setEnabled(true);
				view.btnListClients.setEnabled(true);
				view.btnLogin.setEnabled(false);
				view.btnSalir.setEnabled(true);
				view.btnReport.setEnabled(true);
				jifLogin.dispose();
			} else {
				JOptionPane.showMessageDialog(jifLogin, "El usuario no es correcto", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private void abrirFormularioLogin() {
		if (!estaAbierto(jifLogin)) {
			jifLogin = new JIFLogin();
			view.desktopPane.add(jifLogin);
			jifLogin.setVisible(true);

			// Establecemos las acciones para el boton del formulario
			jifLogin.btnLogin.setActionCommand("Loguear");
			jifLogin.btnLogin.addActionListener(this);

		}
	}

	
}
