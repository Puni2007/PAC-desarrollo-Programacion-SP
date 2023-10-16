package server;

public class Tarea {
	
	//Atributos
	private String descripcion;
	private String estado;
	
	//Constructores
	public Tarea() {
		
	}
	
	public Tarea(String descripcion, String estado) {
		
		this.descripcion=descripcion;
		this.estado=estado;
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Tarea: " + descripcion + ", " 
				+"con estado: " + estado;
	}
	
	

}
