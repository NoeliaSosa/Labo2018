package presentacion.reportes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class PersonaDatasource implements JRDataSource {
	public List<PersonaReporte> listaPersonas = new ArrayList<PersonaReporte>();
	private int indicePersonaReporteActual = -1;

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {

		Object valor = null;
		if ("nombre".equals(jrField.getName())) {
			valor = listaPersonas.get(indicePersonaReporteActual).getNombre();
		} else if ("telefono".equals(jrField.getName())) {
			valor = listaPersonas.get(indicePersonaReporteActual).getTelefono();
		} else if ("localidad".equals(jrField.getName())) {
			valor = listaPersonas.get(indicePersonaReporteActual)
					.getLocalidad();
		} else if ("fechaCumple".equals(jrField.getName())) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy");
			valor = simpleDateFormat.format(listaPersonas.get(
					indicePersonaReporteActual).getFechaCumple());

		} else if ("correoElectronico".equals(jrField.getName())) {
			valor = listaPersonas.get(indicePersonaReporteActual)
					.getCorreoElectronico();
		} else if ("tipoContacto".equals(jrField.getName())) {
			valor = listaPersonas.get(indicePersonaReporteActual)
					.getTipoContacto();
		} else if ("signo".equals(jrField.getName())) {
			valor = listaPersonas.get(indicePersonaReporteActual).getSigno();
		}

		return valor;
	}

	@Override
	public boolean next() throws JRException {
		return ++indicePersonaReporteActual < listaPersonas.size();
	}

	public void addPersonaReporte(PersonaReporte persona) {
		listaPersonas.add(persona);
	}

	public Integer cantPersonas() {
		return listaPersonas.size();
	}

	@Override
	public String toString() {
		return "PersonaDatasource [listaPersonas=" + listaPersonas + "]";
	}

	public List<PersonaReporte> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<PersonaReporte> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

}
