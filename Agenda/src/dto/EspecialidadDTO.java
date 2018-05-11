package dto;

public class EspecialidadDTO {

	private Integer id;
	private String nombreEspecialidad;
	
	public EspecialidadDTO(String nomEsp) {
		this.nombreEspecialidad = nomEsp;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombreEspecialidad() {
		return nombreEspecialidad;
	}
	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}
}
