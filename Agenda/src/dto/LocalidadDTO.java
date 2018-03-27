package dto;

public class LocalidadDTO {

	private int idLocalidad;
	private String descripcion;
	private String codigoPostal;
	
	public LocalidadDTO(int idLocalidad,String descripcion,String codigoPostal){
		this.idLocalidad=idLocalidad;
		this.descripcion=descripcion;
		this.codigoPostal=codigoPostal;
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
	
    public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Override
    public String toString() {
        return descripcion;
    }
    
    
}
