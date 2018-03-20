package dto;

import java.util.Date;

public class PersonaDTO {
	private int idPersona;
	private String nombre;
	private String telefono;
	private DomicilioDTO domicilio;
	private Date fechaCumpleanios;
	private String correoElectronico;
	private int tipoContactoId;

	public PersonaDTO(int idPersona, String nombre, String telefono,
			DomicilioDTO domicilio, Date fechaCumpleanios,
			String correoElectronico, int tipoContactoId) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.setDomicilio(domicilio);
		this.tipoContactoId = tipoContactoId;
		this.fechaCumpleanios = fechaCumpleanios;
		this.correoElectronico = correoElectronico;
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public DomicilioDTO getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(DomicilioDTO domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaCumpleanios() {
		return this.fechaCumpleanios;
	}

	public void setFechaCumpleanios(Date fechaCumpleanios) {
		this.fechaCumpleanios = fechaCumpleanios;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public int getTipoContactoId() {
		return this.tipoContactoId;
	}

	public void setTipoContactoId(int tipoContactoId) {
		this.tipoContactoId = tipoContactoId;
	}

}
