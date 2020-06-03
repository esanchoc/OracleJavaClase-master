package es.ieslavereda.tienda.classes;

public class IVA {
	
	private int id;
	private float porcentaje;
	private String descripcion;
	
	public IVA(int id, float porcentaje, String descripcion) {
		this.id = id;
		this.porcentaje = porcentaje;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(float porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
}
