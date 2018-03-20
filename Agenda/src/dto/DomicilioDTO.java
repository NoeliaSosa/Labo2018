package dto;

public class DomicilioDTO {
	private int idDomicilio;
	private String calle;
	private String altura;
	private String piso;
	private String dpto;
	private int localidad;

	public DomicilioDTO(int idDomicilio, String calle, String altura,
			String piso, String dpto, int localidad) {
		this.idDomicilio = idDomicilio;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.dpto = dpto;
		this.localidad = localidad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public int getLocalidad() {
		return localidad;
	}

	public void setLocalidad(int localidad) {
		this.localidad = localidad;
	}

	public int getIdDomicilio() {
		return idDomicilio;
	}

	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altura == null) ? 0 : altura.hashCode());
		result = prime * result + ((calle == null) ? 0 : calle.hashCode());
		result = prime * result + ((dpto == null) ? 0 : dpto.hashCode());
		result = prime * result + idDomicilio;
		result = prime * result + localidad;
		result = prime * result + ((piso == null) ? 0 : piso.hashCode());
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
		DomicilioDTO other = (DomicilioDTO) obj;
		if (altura == null) {
			if (other.altura != null)
				return false;
		} else if (!altura.equals(other.altura))
			return false;
		if (calle == null) {
			if (other.calle != null)
				return false;
		} else if (!calle.equals(other.calle))
			return false;
		if (dpto == null) {
			if (other.dpto != null)
				return false;
		} else if (!dpto.equals(other.dpto))
			return false;
		if (idDomicilio != other.idDomicilio)
			return false;
		if (localidad != other.localidad)
			return false;
		if (piso == null) {
			if (other.piso != null)
				return false;
		} else if (!piso.equals(other.piso))
			return false;
		return true;
	}
	
	

}
