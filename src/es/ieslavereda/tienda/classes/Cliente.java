package es.ieslavereda.tienda.classes;

public class Cliente {
	
	private int ID;
	private String nombre;
	private String apellidos;
	private String DNI;
	private String fechaNacimiento;
	
	public Cliente(int ID, String nombre, String apellidos, String DNI, String fechaNacimiento) {
		this.ID = ID;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.DNI = DNI;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Cliente(String nombre, String apellidos, String DNI, String fechaNacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.DNI = DNI;
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	

}
