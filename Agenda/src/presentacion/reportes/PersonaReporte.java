package presentacion.reportes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class PersonaReporte {

	private SimpleDateFormat mesFormat = new SimpleDateFormat("MM");
	private SimpleDateFormat diaFormat = new SimpleDateFormat("dd");
	private String nombre;
	private String telefono;
	private String localidad;
	private Date fechaCumple;
	private String correoElectronico;
	private String tipoContacto;
	private String signo;

	public PersonaReporte() {

	}

	public PersonaReporte(String nombre, String telefono, String localidad,
			Date fechaCumple, String correoElectronico, String tipoContacto) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.localidad = localidad;
		this.fechaCumple = fechaCumple;
		this.correoElectronico = correoElectronico;
		this.tipoContacto = tipoContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Date getFechaCumple() {
		return fechaCumple;
	}

	public void setFechaCumple(Date fechaCumple) {
		this.fechaCumple = fechaCumple;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public String getSigno() {
		return signo;
	}

	public Date getFechaCompare() {
		return new GregorianCalendar( 2018,(Integer.valueOf(mesFormat
						.format(fechaCumple))-1),Integer.valueOf(diaFormat
								.format(fechaCumple))).getTime();

	}

	public void setSigno(Date fecha) {
		signo = calcularSigno(Integer.valueOf(diaFormat.format(fecha)),
				Integer.valueOf(mesFormat.format(fecha)));
	}

	public String calcularSigno(int dia, int mes) {
		String signo = "";
		switch (mes) {
		case 1:
			if (dia >= 22) {
				signo = "ACUARIO";
			} else {
				signo = "CAPRICORNIO";
			}
			break;
		case 2:
			if (dia >= 22) {
				signo = "PISCIS";
			} else {
				signo = "ACUARIO";
			}
			break;
		case 3:
			if (dia >= 24) {
				signo = "ARIES";
			} else {
				signo = "PISCIS";
			}
			break;
		case 4:
			if (dia >= 21) {
				signo = "TAURO";
			} else {
				signo = "ARIES";
			}
			break;
		case 5:
			if (dia >= 21) {
				signo = "GEMINIS";
			} else {
				signo = "TAURO";
			}
			break;
		case 6:
			if (dia >= 21) {
				signo = "CANCER";
			} else {
				signo = "GEMINIS";
			}
			break;
		case 7:
			if (dia >= 23) {
				signo = "LEO";
			} else {
				signo = "CANCER";
			}
			break;
		case 8:
			if (dia >= 23) {
				signo = "VIRGO";
			} else {
				signo = "LEO";
			}
			break;
		case 9:
			if (dia >= 23) {
				signo = "LIBRA";
			} else {
				signo = "VIRGO";
			}
			break;
		case 10:
			if (dia >= 23) {
				signo = "ESCORPIO";
			} else {
				signo = "LIBRA";
			}
			break;
		case 11:
			if (dia >= 22) {
				signo = "SAGITARIO";
			} else {
				signo = "ESCORPIO";
			}
			break;
		case 12:
			if (dia >= 22) {
				signo = "CAPRICORNIO";
			} else {
				signo = "SAGITARIO";
			}
			break;
		}
		return signo;
	}

	@Override
	public String toString() {
		return "PersonaReporte [nombre=" + nombre + ", telefono=" + telefono
				+ ", localidad=" + localidad + ", fechaCumple=" + fechaCumple
				+ ", correoElectronico=" + correoElectronico
				+ ", tipoContacto=" + tipoContacto + ", signo=" + signo + "]";
	}

}
