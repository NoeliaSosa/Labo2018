package presentacion.reportes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteAgenda {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;

	// Recibe la lista de personas para armar el reporte
	public ReporteAgenda(PersonaDatasource personas) {

		ordenarLista(personas);
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha",
				new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		parametersMap.put("Total", personas.cantPersonas());
		try {

			this.reporte = (JasperReport) JRLoader
					.loadObjectFromFile(".\\reportes\\ReporteAgenda.jasper");
			this.reporteLleno = JasperFillManager.fillReport(this.reporte,
					parametersMap, personas);
		} catch (JRException ex) {
			ex.printStackTrace();
		}
	}

	private void ordenarLista(PersonaDatasource personas) {
		String[] ordenDeSignos = { "CAPRICORNIO", "ACUARIO", "PISCIS", "ARIES",
				"TAURO", "GEMINIS", "CANCER", "LEO", "VIRGO", "LIBRA",
				"ESCORPIO", "SAGITARIO" };
		List<PersonaReporte> listaFinal = new ArrayList<PersonaReporte>();
		List<PersonaReporte> lista = personas.getListaPersonas();
		Map<String, List<PersonaReporte>> listAgrupado = lista.stream()
				.collect(Collectors.groupingBy(p -> p.getSigno()));
		for (int i = 0; i < 12; i++) {
			List<PersonaReporte> listaAux = listAgrupado.get(ordenDeSignos[i]);
			if(listaAux!=null){
				Collections.sort(listaAux,
						(PersonaReporte a, PersonaReporte b) -> a.getFechaCompare()
								.compareTo(b.getFechaCompare()));
				listaFinal.addAll(listaAux);
			}
		}
		personas.setListaPersonas(listaFinal);
	}

	public void mostrar() {
		this.reporteViewer = new JasperViewer(this.reporteLleno, false);
		this.reporteViewer.setVisible(true);
	}

}