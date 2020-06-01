/**
 * 
 */
package es.ieslavereda.tienda.modelo;

import java.awt.Image;
import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import es.ieslavereda.tienda.classes.Cliente;
import es.ieslavereda.tienda.classes.Usuario;

/**
 * Creado el 27 mar. 2019
 * 
 * @author <a href="mailto:joaalsai@ieslavereda.es">Joaquin Vicente Alonso
 *         Saiz</a>
 *
 */
public class Modelo extends Database {

	public Modelo() {
		super();
	}

	public boolean autentificar(String login, String password) {
		boolean exito = false;
		int cantidad = contar(login, password);

		if (cantidad == 1)
			exito = true;

		return exito;
	}
	
	public boolean validar(String login, String password) throws SQLException {

		boolean validado = false;

		String sql = "SELECT COUNT(*) AS CANTIDAD FROM Usuario WHERE login=? AND password=? ";

		try (Connection con = conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

			int pos = 0, cantidad;

			ps.setString(++pos, login);
			ps.setString(++pos, password);

//			ps.setString(1, login);
//			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			rs.next();

			cantidad = rs.getInt("CANTIDAD");

			if (cantidad == 1)
				validado = true;

		}

		return validado;
	}


	protected int contar(String login, String password) {

		int cantidad;
		String sql = "SELECT COUNT(*) AS CANTIDAD FROM Usuario WHERE login='" + login + "' AND password='" + password
				+ "'";

		try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {

			rs.next();

			cantidad = rs.getInt("CANTIDAD");

			return cantidad;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean insertarUsuario(Usuario usuario) {
		boolean insertado = false;

		String sql = "INSERT INTO Usuario (login ,mail ,role ,password) VALUES (?,?,?,?)";

		try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(sql);) {

			
			int pos = 0;
			
			pst.setString(++pos, usuario.getLogin());
			pst.setString(++pos, usuario.getMail());
			pst.setString(++pos, usuario.getRole());
			pst.setString(++pos, usuario.getPassword());
			
			pst.execute();
			insertado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertado;
	}
	
	public Usuario obtenerUsuarioByID(int id) {
		
		Usuario usuario = null;
		
		String sql = "SELECT * FROM Usuario WHERE ID="+id;
		
		try(Connection con = conectar();
				Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(sql)){
			
			
			while(rs.next()) {
				
				usuario = new Usuario(rs.getInt("id"),rs.getString("login"),rs.getString("password"),rs.getString("mail"),rs.getString("role"));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	public boolean insertarCliente(Cliente cliente) {
		boolean insertado = false;
		
		String sql = "INSERT INTO CLIENTE (nombre,apellidos,dni,fecha_nacimiento) VALUES (?,?,?,TO_DATE(?,'DD/MM/YYYY'))";
//		String sql = "INSERT INTO CLIENTE (nombre,apellidos,dni,fecha_nacimiento) VALUES ('"+cliente.getNombre()+"','"+cliente.getApellidos()+"','"+cliente.getDNI()+"',to_date('"+cliente.getFechaNacimiento()+"','DD/MM/YYYY')";
		
		try(Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(sql);)
//				Statement st = con.createStatement();)
		{
			
			int pos = 0;
			
			pst.setString(++pos, cliente.getNombre());
			pst.setString(++pos, cliente.getApellidos());
			pst.setString(++pos, cliente.getDNI());
			pst.setString(++pos, cliente.getFechaNacimiento());
			
			pst.execute();
//			st.execute(sql);
			
			insertado = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return insertado;
	}
	
	public boolean actualizarCliente(Cliente cliente) {
		boolean actualizado = false;
		
		String sql = "UPDATE Cliente SET nombre=? ,apellidos=? ,dni=? ,fecha_nacimiento=to_date(?,'DD/MM/YYYY') WHERE id=?";
		
		try(Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(sql)){
			
			int pos = 0;
			
			pst.setString(++pos, cliente.getNombre());
			pst.setString(++pos, cliente.getApellidos());
			pst.setString(++pos, cliente.getDNI());
			pst.setString(++pos, cliente.getFechaNacimiento());
			pst.setInt(++pos, cliente.getID());
			
			pst.execute();
			
			actualizado = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return actualizado;
	}
	
	public boolean eliminarCliente(int id) {
		boolean eliminado = false;
		
		String sql = "DELETE FROM CLIENTE WHERE ID=?";
		
		try(Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(sql)){
			
			int pos = 0;
			
			pst.setInt(++pos, id);
			
			pst.execute();
			
			eliminado = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return eliminado;
	}
	
	public ArrayList<Cliente> obtenerClientes(){
		return obtenerClientes("","");
	}
	
	public ArrayList<Cliente> obtenerClientes(String where){
		return obtenerClientes(where,"");
	}
	
	public ArrayList<Cliente> obtenerClientes(String where, String order){
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		int id;
		String nombre;
		String apellidos;
		String DNI;
		String fechaNacimiento;
		String sql = "SELECT id,nombre,apellidos,DNI,to_char(fecha_nacimiento,'DD/MM/YY') as fecha_nacimiento FROM CLIENTE";
		Cliente cliente = null;
		
		if(!where.equals("")) {
			sql+=" WHERE " + where;
		}
		
		if(!order.equals("")) {
			sql+=" ORDER BY " + order;
		}
		
		try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {

			while (rs.next()) {

				id = rs.getInt("id");
				nombre = rs.getString("nombre");
				apellidos = rs.getString("apellidos");
				DNI = rs.getString("DNI");
				fechaNacimiento = rs.getString("fecha_nacimiento");

				cliente = new Cliente(id, nombre, apellidos, DNI, fechaNacimiento);

				clientes.add(cliente);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	public ArrayList<Usuario> obtenerUsuarios(){
		
		return obtenerUsuarios("","");
	}
	
	public ArrayList<Usuario> obtenerUsuarios(String where){
		
		return obtenerUsuarios(where,"");
	}

	public ArrayList<Usuario> obtenerUsuarios(String where, String order) {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		int id;
		String login;
		String mail;
		String role;
		String password;
		Usuario usuario;
		String sql = "SELECT * FROM Usuario";
		
		if(!where.equals("")) {
			sql+=" WHERE " + where;
		}
		
		if(!order.equals("")) {
			sql+=" ORDER BY " + order;
		}

		try (Connection con = conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql);) {

			while (rs.next()) {

				id = rs.getInt("id");
				login = rs.getString("login");
				mail = rs.getString("mail");
				role = rs.getString("role");
				password = rs.getString("password");

				usuario = new Usuario(id, login, password, mail, role);

				usuarios.add(usuario);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	public boolean actualizarUsuario(Usuario usuario) {
		boolean actualizado = false;

		String sql = "UPDATE Usuario SET login=? ,mail=? ,role=? ,password=? WHERE id=?";
		
		
		try (Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(sql);) {
			
			int pos = 0,cantidad;
			
			pst.setString(++pos, usuario.getLogin());
			pst.setString(++pos, usuario.getMail());
			pst.setString(++pos, usuario.getRole());
			pst.setString(++pos, usuario.getPassword());
			pst.setInt(++pos, usuario.getId());
			
			pst.execute();
			
			actualizado = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return actualizado;
	}

	public boolean eliminarUsuario(int id) {

		boolean eliminado = false;

		String sql = "DELETE FROM Usuario WHERE id=?";

		try (Connection con = conectar(); PreparedStatement st = con.prepareStatement(sql);) {
			
			int pos = 0;
			
			st.setInt(++pos, id);

			st.execute();

			eliminado = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eliminado;

	}

	public Cliente obtenerClienteByID(int id) {
		
		Cliente c = null;
		
		String sql = "SELECT id, nombre, apellidos, dni, to_char(fecha_nacimiento,'DD/MM/YY') as fecha_nacimiento FROM CLIENTE WHERE ID="+id;
		
		try(Connection con = conectar();
				Statement pst = con.createStatement();
						ResultSet rs = pst.executeQuery(sql);){
			
			rs.next();
			
			c = new Cliente(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("dni"),rs.getString("fecha_nacimiento"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}

}
