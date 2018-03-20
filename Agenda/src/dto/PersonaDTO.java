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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((correoElectronico == null) ? 0 : correoElectronico
						.hashCode());
		result = prime * result
				+ ((domicilio == null) ? 0 : domicilio.hashCode());
		result = prime
				* result
				+ ((fechaCumpleanios == null) ? 0 : fechaCumpleanios.hashCode());
		result = prime * result + idPersona;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + tipoContactoId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaDTO other = (PersonaDTO) obj;
		if (correoElectronico == null) {
			if (other.correoElectronico != null)
				return false;
		} else if (!correoElectronico.equals(other.correoElectronico))
			return false;
		if (domicilio == null) {
			if (other.domicilio != null)
				return false;
		} else if (!domicilio.equals(other.domicilio))
			return false;
		if (fechaCumpleanios == null) {
			if (other.fechaCumpleanios != null)
				return false;
		} else if (!fechaCumpleanios.equals(other.fechaCumpleanios))
			return false;
		if (idPersona != other.idPersona)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tipoContactoId != other.tipoContactoId)
			return false;
		return true;
	}
	
	
}
