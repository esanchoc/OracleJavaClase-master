package es.ieslavereda.tienda.classes;

public class IVA {
	
	private int id;
	private int porcentaje;
	private String descripcion;
	
	public IVA(int id, int porcentaje, String descripcion) {
		this.id = id;
		this.porcentaje = porcentaje;
		this.descripcion = descripcion;
	}
	
	public IVA(int porcentaje, String descripcion) {
		this.porcentaje = porcentaje;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
}
