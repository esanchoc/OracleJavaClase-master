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

import es.ieslavereda.tienda.classes.Categories;
import es.ieslavereda.tienda.classes.Cliente;
import es.ieslavereda.tienda.classes.IVA;
import es.ieslavereda.tienda.classes.Usuario;
import es.ieslavereda.tienda.modelo.Modelo;
import es.ieslavereda.tienda.vista.JFramePrincipal;
import es.ieslavereda.tienda.vista.JIFCategories;
import es.ieslavereda.tienda.vista.JIFClientes;
import es.ieslavereda.tienda.vista.JIFFormularioCategories;
import es.ieslavereda.tienda.vista.JIFFormularioCliente;
import es.ieslavereda.tienda.vista.JIFFormularioIVA;
import es.ieslavereda.tienda.vista.JIFFormularioUsuario;
import es.ieslavereda.tienda.vista.JIFIva;
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
	JIFCategories jifcategories;
	JIFFormularioCategories jifformulariocategories;
	JIFIva jifiva;
	JIFFormularioIVA jifformularioiva;

	public Controlador(JFramePrincipal view, Modelo modelo) {
		this.view = view;
		this.modelo = modelo;
		iniciar();
	}

	public void iniciar() {

		view.setTitle("Aplicacion MVC 1ºDAW");

		// AÃ±adir las accionew a los botones del formulario padre
		view.getBtnListClients().setActionCommand("Listar clientes");
		view.getBtnUsers().setActionCommand("Abrir formulario gestion usuarios");
		view.getBtnLogin().setActionCommand("Abrir formulario login");
		view.getBtnCategories().setActionCommand("Abrir gestion categorias");
		view.getBtnIVA().setActionCommand("Abrir IVA");
		view.getBtnSalir().setActionCommand("Cerrar sesion");
		view.getBtnReport().setActionCommand("Report");

		// Ponemos a escuchar las accionew del usuario
		view.getBtnListClients().addActionListener(this);
		view.getBtnUsers().addActionListener(this);
		view.getBtnLogin().addActionListener(this);
		view.getBtnSalir().addActionListener(this);
		view.getBtnReport().addActionListener(this);
		view.getBtnCategories().addActionListener(this);
		view.getBtnIVA().addActionListener(this);

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
		} else if (comando.equals("Abrir formulario gestion usuarios")) {
			openJIFUsers();
		} else if (comando.equals("Abrir formulario login")) {
			abrirFormularioLogin();
		} else if (comando.equals("Loguear")) {
			loguearUsuario();
		} else if (comando.equals("Cerrar sesion")) {
			cerrarSesion();
		} else if (comando.equals("Abrir gestion categorias")) {
			openJIFCategories();
		} else if (comando.equals("Abrir IVA")) {
			openJIFIva();
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
		} else if (comando.equals("Add categories")) {
			openJIFFormularioCategoriesAdd();
		} else if (comando.equals("Add new categories")) {
			addNewCategories();
		} else if (comando.equals("Edit categories")) {
			openJIFFormularioCategoriesEdit();
		} else if (comando.equals("Edit new categories")) {
			editNewCategories();
		} else if (comando.equals("Delete categories")) {
			deleteCategories();
		} else if (comando.equals("Add IVA")) {
			openJIFFormularioIVAadd();
		} else if (comando.equals("Add new IVA")) {
			addNewIVA();
		} else if (comando.equals("Edit IVA")) {
			openJIFFormularioIVAEdit();
		} else if (comando.equals("Edit new IVA")) {
			editNewIVA();
		} else if (comando.equals("Delete IVA")) {
			deleteIVA();
		}

	}

	private void deleteIVA() {
		
		int[] filas = jifiva.getTableIVA().getSelectedRows();
		int option;
		int id;

		if (filas.length == 0) {

			JOptionPane.showMessageDialog(jifiva, "Debe seleccionar una o más filas para eliminar clientes",
					"Error", JOptionPane.ERROR_MESSAGE);

		} else {

			if (filas.length == 1) {

				option = JOptionPane.showConfirmDialog(jifiva,
						"Esta seguro de eliminar la categoría seleccionada?", "Info", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

			} else {

				option = JOptionPane.showConfirmDialog(jifiva,
						"Esta seguro de eliminar " + filas.length + " categorías seleccionadas?", "Info",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}

			if (option == JOptionPane.YES_OPTION) {

				for (int fila : filas) {

					id = Integer.parseInt(jifiva.getTableIVA().getValueAt(fila, 0).toString());
					modelo.eliminarIVAS(id);

				}

				actualizarTablaIVA();

			}

		}
		
	}

	private void editNewIVA() {

		int option = JOptionPane.showConfirmDialog(jifformularioiva, "Esta seguro que quiere añadir este nuevo IVA?",
				"Question", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {

			jifformularioiva.getIva()
					.setPorcentaje(Integer.parseInt(jifformularioiva.getTextFieldPorcentaje().getText()));
			jifformularioiva.getIva().setDescripcion(jifformularioiva.getTextFieldDescripcionIVA().getText());

			if (modelo.actualizarIVA(jifformularioiva.getIva())) {

				JOptionPane.showMessageDialog(jifformularioiva, "IVA actualizado correctamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				actualizarTablaIVA();
				jifformularioiva.dispose();

			} else {

				JOptionPane.showMessageDialog(jifformulariocategories, "Se ha producido un error", "Error",
						JOptionPane.ERROR_MESSAGE);

			}

		}

	}

	private void openJIFFormularioIVAEdit() {

		int option = jifiva.getTableIVA().getSelectedRow();

		if (option == -1) {
			JOptionPane.showMessageDialog(jifclientes, "Debe seleccionar primero una fila", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (!estaAbierto(jifformularioiva)) {
				jifformularioiva = new JIFFormularioIVA();
				jifformularioiva.setVisible(true);
				view.desktopPane.add(jifformularioiva);

				jifformularioiva.getBtnAction().setText("Save");

				IVA iva = modelo
						.obtenerIVAById(Integer.parseInt(jifiva.getTableIVA().getValueAt(option, 0).toString()));

				jifformularioiva.getTextFieldPorcentaje().setText(String.valueOf(iva.getPorcentaje()));
				jifformularioiva.getTextFieldDescripcionIVA().setText(iva.getDescripcion());

				jifformularioiva.getBtnAction().setActionCommand("Edit new IVA");
				jifformularioiva.getBtnAction().addActionListener(this);

			}
		}

	}

	private void addNewIVA() {

		int option = JOptionPane.showConfirmDialog(jifformularioiva, "Esta seguro que quiere añadir este nuevo IVA?",
				"Question", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {

			IVA iva = new IVA(Integer.parseInt(jifformularioiva.getTextFieldPorcentaje().getText()),
					jifformularioiva.getTextFieldDescripcionIVA().getText());

			if (modelo.addIVA(iva)) {

				JOptionPane.showMessageDialog(jifformularioiva, "IVA añadido correctamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				actualizarTablaIVA();
				jifformularioiva.dispose();

			} else {

				JOptionPane.showMessageDialog(jifformulariocategories, "Se ha producido un error", "Error",
						JOptionPane.ERROR_MESSAGE);

			}

		}

	}

	private void openJIFFormularioIVAadd() {

		if (!estaAbierto(jifformularioiva)) {
			jifformularioiva = new JIFFormularioIVA();
			jifformularioiva.setVisible(true);
			view.desktopPane.add(jifformularioiva);

			jifformularioiva.getBtnAction().setText("Add");

			jifformularioiva.getBtnAction().setActionCommand("Add new IVA");
			jifformularioiva.getBtnAction().addActionListener(this);

		}

	}

	private void openJIFIva() {

		if (!estaAbierto(jifiva)) {
			jifiva = new JIFIva();
			jifiva.setVisible(true);

			view.desktopPane.add(jifiva);

			jifiva.getBtnAddIVA().setActionCommand("Add IVA");
			jifiva.getBtnEditIVA().setActionCommand("Edit IVA");
			jifiva.getBtnDeleteIVA().setActionCommand("Delete IVA");

			jifiva.getBtnAddIVA().addActionListener(this);
			jifiva.getBtnEditIVA().addActionListener(this);
			jifiva.getBtnDeleteIVA().addActionListener(this);

			actualizarTablaIVA();
		}

	}

	private void actualizarTablaIVA() {

		DefaultTableModel dtm = new DefaultTableModel();

		dtm.addColumn("ID");
		dtm.addColumn("PORCENTAJE");
		dtm.addColumn("DESCRIPCIÓN");

		ArrayList<IVA> ivas = modelo.obtenerIVA();
		Vector<String> rowData;

		for (IVA iva : ivas) {
			rowData = new Vector<String>();

			rowData.add(String.valueOf(iva.getId()));
			rowData.add(String.valueOf(iva.getPorcentaje()) + "%");
			rowData.add(iva.getDescripcion());

			dtm.addRow(rowData);
		}

		jifiva.getTableIVA().setModel(dtm);
	}

	private void deleteCategories() {

		int[] filas = jifcategories.getTableCategories().getSelectedRows();
		int option;
		int id;

		if (filas.length == 0) {

			JOptionPane.showMessageDialog(jifcategories, "Debe seleccionar una o más filas para eliminar clientes",
					"Error", JOptionPane.ERROR_MESSAGE);

		} else {

			if (filas.length == 1) {

				option = JOptionPane.showConfirmDialog(jifclientes,
						"Esta seguro de eliminar la categoría seleccionada?", "Info", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

			} else {

				option = JOptionPane.showConfirmDialog(jifclientes,
						"Esta seguro de eliminar " + filas.length + " categorías seleccionadas?", "Info",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}

			if (option == JOptionPane.YES_OPTION) {

				for (int fila : filas) {

					id = Integer.parseInt(jifcategories.getTableCategories().getValueAt(fila, 0).toString());
					modelo.eliminarCategories(id);

				}

				actualizarTablaCategories();

			}

		}

	}

	private void editNewCategories() {

		int option = JOptionPane.showConfirmDialog(jifformulariocategories,
				"Está seguro de que quiere editar esta categoría?", "Question", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			jifformulariocategories.getCategories()
					.setDescripcion(jifformulariocategories.getTextFieldDescripcionCategories().getText());

			if (modelo.updateCategories(jifformulariocategories.getCategories())) {
				JOptionPane.showMessageDialog(jifformulariocategories, "Categoría modificada correctamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				actualizarTablaCategories();
				jifformulariocategories.dispose();
			} else {
				JOptionPane.showMessageDialog(jifformulariocategories, "Se ha producido un error", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void openJIFFormularioCategoriesEdit() {

		int option = jifcategories.getTableCategories().getSelectedRow();

		if (option == -1) {
			JOptionPane.showMessageDialog(jifclientes, "Debe seleccionar primero una fila", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} else {

			if (!estaAbierto(jifformulariocategories)) {
				jifformulariocategories = new JIFFormularioCategories();
				jifformulariocategories.setVisible(true);

				jifformulariocategories.getBtnAction().setText("Save");
				jifformulariocategories.getTextFieldIDCategories().setEnabled(false);

				jifformulariocategories.getBtnAction().setActionCommand("Edit new categories");
				jifformulariocategories.getBtnAction().addActionListener(this);

				jifformulariocategories.setCategories(modelo.obtenerCategoriesByID(
						Integer.parseInt(jifcategories.getTableCategories().getValueAt(option, 0).toString())));

				jifformulariocategories.getTextFieldIDCategories()
						.setText(String.valueOf(jifformulariocategories.getCategories().getId()));
				jifformulariocategories.getTextFieldDescripcionCategories()
						.setText(jifformulariocategories.getCategories().getDescripcion());

				view.desktopPane.add(jifformulariocategories);
			}
		}
	}

	private void addNewCategories() {
		// TODO Auto-generated method stub
		int option = JOptionPane.showConfirmDialog(jifformulariocategories,
				"Está seguro de que quiere añadir esta nueva categoría?", "Question", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {
			Categories c;
			String descripcion = jifformulariocategories.getTextFieldDescripcionCategories().getText();

			c = new Categories(descripcion);

			if (modelo.addCategories(c)) {
				JOptionPane.showMessageDialog(jifformulariocategories, "Categoría añadida correctamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				actualizarTablaCategories();
				jifformulariocategories.dispose();
			} else {
				JOptionPane.showMessageDialog(jifformulariocategories, "Se ha producido un error", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void openJIFFormularioCategoriesAdd() {
		// TODO Auto-generated method stub
		if (!estaAbierto(jifformulariocategories)) {
			jifformulariocategories = new JIFFormularioCategories();
			jifformulariocategories.setVisible(true);

			jifformulariocategories.getBtnAction().setText("Add");
			jifformulariocategories.getTextFieldIDCategories().setEnabled(false);

			jifformulariocategories.getBtnAction().setActionCommand("Add new categories");
			jifformulariocategories.getBtnAction().addActionListener(this);
			view.desktopPane.add(jifformulariocategories);
		}
	}

	private void openJIFCategories() {

		if (!estaAbierto(jifcategories)) {
			jifcategories = new JIFCategories();
			view.desktopPane.add(jifcategories);
			jifcategories.setVisible(true);

			jifcategories.getBtnAddCategories().setActionCommand("Add categories");
			jifcategories.getBtnEditCategories().setActionCommand("Edit categories");
			jifcategories.getBtnDeleteCategories().setActionCommand("Delete categories");

			jifcategories.getBtnAddCategories().addActionListener(this);
			jifcategories.getBtnEditCategories().addActionListener(this);
			jifcategories.getBtnDeleteCategories().addActionListener(this);

			actualizarTablaCategories();
		}

	}

	private void actualizarTablaCategories() {
		Vector<String> rowData;

		DefaultTableModel dtm = new DefaultTableModel();

		dtm.addColumn("ID");
		dtm.addColumn("DESCRIPCIÓN");

		ArrayList<Categories> categories = modelo.obtenerCategories();

		for (Categories c : categories) {
			rowData = new Vector<String>();

			rowData.add(String.valueOf(c.getId()));
			rowData.add(c.getDescripcion());

			dtm.addRow(rowData);

		}

		jifcategories.getTableCategories().setModel(dtm);

	}

	private void editCliente() {

		int option = JOptionPane.showConfirmDialog(jifformulariocliente,
				"Esta seguro que quiere actualizar este cliente?", "Question", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {

			jifformulariocliente.getCliente().setNombre(jifformulariocliente.getTextFieldNombre().getText());
			jifformulariocliente.getCliente().setApellidos(jifformulariocliente.getTextFieldApellidos().getText());
			jifformulariocliente.getCliente().setDNI(jifformulariocliente.getTextFieldDNI().getText());
			jifformulariocliente.getCliente()
					.setFechaNacimiento(jifformulariocliente.getTextFieldFechaNacimiento().getText());

			if (modelo.actualizarCliente(jifformulariocliente.getCliente())) {
				JOptionPane.showMessageDialog(jifformulariocliente, "El usuario ha sido actualizado correctamente",
						"Info", JOptionPane.INFORMATION_MESSAGE);
				jifformulariocliente.dispose();
				actualizarTablaClientes();
			} else {
				JOptionPane.showMessageDialog(jifformulariocliente,
						"Ha surgido un error a la hora de actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	private void openJIFFormularioClienteEdit() {

		int option = jifclientes.getTableClient().getSelectedRow();

		if (option == -1) {
			JOptionPane.showMessageDialog(jifclientes, "Debe seleccionar primero una fila", "Info",
					JOptionPane.INFORMATION_MESSAGE);
		} else {

			if (!estaAbierto(jifformulariocliente)) {

				jifformulariocliente = new JIFFormularioCliente();
				view.desktopPane.add(jifformulariocliente);
				jifformulariocliente.setVisible(true);

				jifformulariocliente.getBtnActionClients().setText("Save");
				jifformulariocliente.getBtnActionClients().addActionListener(this);
				jifformulariocliente.getBtnActionClients().setActionCommand("edit new cliente");

				int id = Integer.parseInt(jifclientes.getTableClient()
						.getValueAt(jifclientes.getTableClient().getSelectedRow(), 0).toString());

				jifformulariocliente.setCliente(modelo.obtenerClienteByID(id));

				jifformulariocliente.getTextFieldNombre().setText(jifformulariocliente.getCliente().getNombre());
				jifformulariocliente.getTextFieldApellidos().setText(jifformulariocliente.getCliente().getApellidos());
				jifformulariocliente.getTextFieldDNI().setText(jifformulariocliente.getCliente().getDNI());
				jifformulariocliente.getTextFieldFechaNacimiento()
						.setText(jifformulariocliente.getCliente().getFechaNacimiento());

			}

		}

	}

	private void deleteCliente() {

		int[] filas = jifclientes.getTableClient().getSelectedRows();
		int option;
		int id;

		if (filas.length == 0) {

			JOptionPane.showMessageDialog(jifclientes, "Debe seleccionar una o más filas para eliminar clientes",
					"Error", JOptionPane.ERROR_MESSAGE);

		} else {

			if (filas.length == 1) {

				option = JOptionPane.showConfirmDialog(jifclientes, "Esta seguro de eliminar el cliente seleccionado?",
						"Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			} else {

				option = JOptionPane.showConfirmDialog(jifclientes,
						"Esta seguro de eliminar " + filas.length + " clientes seleccionados?", "Info",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}

			if (option == JOptionPane.YES_OPTION) {

				for (int fila : filas) {

					id = Integer.parseInt(jifclientes.getTableClient().getValueAt(fila, 0).toString());
					modelo.eliminarCliente(id);

				}

				actualizarTablaClientes();

			}

		}

	}

	private void addNewCliente() {

		int option = JOptionPane.showConfirmDialog(jifformulariocliente, "Esta seguro que quiere añadir este cliente?",
				"Question", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION) {

			Cliente c;

			String nombre = jifformulariocliente.getTextFieldNombre().getText();
			String apellidos = jifformulariocliente.getTextFieldApellidos().getText();
			String DNI = jifformulariocliente.getTextFieldDNI().getText();
			String fechaNacimiento = jifformulariocliente.getTextFieldFechaNacimiento().getText();

			c = new Cliente(nombre, apellidos, DNI, fechaNacimiento);

			if (modelo.insertarCliente(c)) {
				JOptionPane.showMessageDialog(jifformulariocliente, "El usuario se ha insertado de forma correcta",
						"Info", JOptionPane.INFORMATION_MESSAGE);
				jifformulariocliente.dispose();
				actualizarTablaClientes();
			} else {
				JOptionPane.showMessageDialog(jifformulariocliente,
						"Ha surgido un problema a la hora de insertar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	private void openJIFFormularioClienteAdd() {

		if (!estaAbierto(jifformulariocliente)) {

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

		if (!jifclientes.getTextFieldSearchClient().getText().trim().isEmpty()) {
			where += (jifclientes.getComboBoxWhereClient().getSelectedItem().toString().equals("FECHA DE NACIMIENTO"))
					? "fecha_nacimiento"
					: jifclientes.getComboBoxWhereClient().getSelectedItem().toString() + " LIKE '%"
							+ jifclientes.getTextFieldSearchClient().getText() + "%' ";
		}

		order = (jifclientes.getComboBoxWhereClient().getSelectedItem().toString().equals("FECHA DE NACIMIENTO"))
				? "fecha_nacimiento"
				: jifclientes.getComboBoxWhereClient().getSelectedItem().toString();
		order += (jifclientes.getComboBoxOrdenClient().getSelectedItem().toString().equals("Ascendente")) ? " ASC"
				: " DESC";

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

		for (Cliente c : clientes) {

			rowData = new Vector<String>();

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

		String where = "";
		String order;

		if (!jifUsers.getTextFieldSearch().getText().trim().isEmpty()) {
			where += jifUsers.getComboBoxWhere().getSelectedItem().toString() + " LIKE '%"
					+ jifUsers.getTextFieldSearch().getText() + "%' ";
		}

		order = jifUsers.getComboBoxWhere().getSelectedItem().toString();
		order += (jifUsers.getComboBoxOrden().getSelectedItem().toString().equals("Ascendente")) ? " ASC" : " DESC";

		ArrayList<Usuario> usuarios = modelo.obtenerUsuarios(where, order);

		actualizarTablaUsuarios(usuarios);

	}

	private void saveEditUser() {

		int option = JOptionPane.showConfirmDialog(jifformulariousuario,
				"Esta usted seguro de actualizar este usuario?", "Question", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {

			jifformulariousuario.getUsuario().setLogin(jifformulariousuario.getTextFieldLogin().getText());
			jifformulariousuario.getUsuario()
					.setPassword(String.valueOf(jifformulariousuario.getPasswordField().getPassword()));
			jifformulariousuario.getUsuario().setMail(jifformulariousuario.getTextFieldMail().getText());
			jifformulariousuario.getUsuario()
					.setRole(jifformulariousuario.getComboBoxRole().getSelectedItem().toString());

			if (modelo.actualizarUsuario(jifformulariousuario.getUsuario())) {

				JOptionPane.showMessageDialog(jifformulariousuario, "Usuario actualizado correctamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				actualizarTablaUsuarios();
				jifformulariousuario.dispose();

			} else {

				JOptionPane.showMessageDialog(jifformulariousuario, "Error al actualizar este usuario", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
		}

	}

	private void openEditUserInternalFrame() {

		jifformulariousuario = new JIFFormularioUsuario();

		jifformulariousuario.getBtnAction().setText("Save");

		jifformulariousuario.getBtnAction().addActionListener(this);
		jifformulariousuario.getBtnAction().setActionCommand("Edit user");

		Usuario u = modelo.obtenerUsuarioByID(Integer.parseInt(
				jifUsers.getTableUsers().getValueAt(jifUsers.getTableUsers().getSelectedRow(), 0).toString()));

		jifformulariousuario.setUsuario(u);

		jifformulariousuario.getTextFieldLogin().setText(jifformulariousuario.getUsuario().getLogin());
		jifformulariousuario.getPasswordField().setText(jifformulariousuario.getUsuario().getPassword());
		jifformulariousuario.getTextFieldMail().setText(jifformulariousuario.getUsuario().getMail());
		jifformulariousuario.getComboBoxRole().setSelectedItem(jifformulariousuario.getUsuario().getRole());

		jifformulariousuario.setVisible(true);

		view.desktopPane.add(jifformulariousuario);

	}

	private void addNewUser() {

		int option = JOptionPane.showConfirmDialog(jifformulariousuario, "Esta usted seguro de añadir este usuario?",
				"Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {

			String login = jifformulariousuario.getTextFieldLogin().getText().replaceAll(" ", "");
			String password = String.valueOf(jifformulariousuario.getPasswordField().getPassword());
			String mail = jifformulariousuario.getTextFieldMail().getText();
			String role = jifformulariousuario.getComboBoxRole().getSelectedItem().toString();

			if (modelo.insertarUsuario(new Usuario(login, password, mail, role))) {

				JOptionPane.showMessageDialog(jifformulariousuario, "Usuario insertado correctamente", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				actualizarTablaUsuarios();
				jifformulariousuario.dispose();

			} else {

				JOptionPane.showMessageDialog(jifformulariousuario, "Error en la inserción de usuario", "Error",
						JOptionPane.ERROR_MESSAGE);

			}

		}
	}

	private void delUsers() {

		int[] filas = jifUsers.getTableUsers().getSelectedRows();
		int option;
		int id;

		if (filas.length == 0) {

			JOptionPane.showMessageDialog(jifUsers, "Debe seleccionar una o más filas para eliminar usuarios", "Error",
					JOptionPane.ERROR_MESSAGE);

		} else {

			if (filas.length == 1) {

				option = JOptionPane.showConfirmDialog(jifUsers, "Esta seguro de eliminar el usuario seleccionado?",
						"Info", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			} else {

				option = JOptionPane.showConfirmDialog(jifUsers,
						"Esta seguro de eliminar " + filas.length + " usuarios seleccionados?", "Info",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			}

			if (option == JOptionPane.YES_OPTION) {

				for (int fila : filas) {

					id = Integer.parseInt(jifUsers.getTableUsers().getValueAt(fila, 0).toString());
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

		for (Usuario u : usuarios) {

			rowData = new Vector<String>();

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
			view.getBtnListClients().setEnabled(false);
			view.getBtnUsers().setEnabled(false);
			view.getBtnLogin().setEnabled(true);
			view.getBtnSalir().setEnabled(false);
			view.getBtnReport().setEnabled(false);
			view.getBtnCategories().setEnabled(false);
			view.getBtnIVA().setEnabled(false);

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
				view.getBtnUsers().setEnabled(true);
				view.getBtnListClients().setEnabled(true);
				view.getBtnLogin().setEnabled(false);
				view.getBtnSalir().setEnabled(true);
				view.getBtnReport().setEnabled(true);
				view.getBtnCategories().setEnabled(true);
				view.getBtnIVA().setEnabled(true);
				jifLogin.dispose();
			} else {
				JOptionPane.showMessageDialog(jifLogin, "El usuario no es correcto", "Error",
						JOptionPane.ERROR_MESSAGE);
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