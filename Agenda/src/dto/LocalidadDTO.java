package dto;

public class LocalidadDTO {

	private int idLocalidad;
	private String descripcion;
	
	public LocalidadDTO(int idLocalidad,String descripcion){
		this.idLocalidad=idLocalidad;
		this.descripcion=descripcion;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
    @Override
    public String toString() {
        return descripcion;
    }

}
