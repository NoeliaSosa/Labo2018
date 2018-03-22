package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personas_en_tabla;
	private List<LocalidadDTO> localidades;
	private List<TipoDeContactoDTO> tiposDeContactos;
	private VentanaPersona ventanaPersona;
	private Agenda agenda;
	private PersonaDTO personaEdit;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnReporte().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
		this.agenda = agenda;
		this.personas_en_tabla = null;
		this.personaEdit = null;
	}

	public void inicializar() {
		this.llenarCombos();
		this.llenarTabla();
		this.vista.show();
	}

	private void llenarCombos() {
		localidades = this.agenda.obtenerLocalidades();
		tiposDeContactos = this.agenda.obtenerTiposDeContactos();
	}

	private void llenarTabla() {
		this.vista.getModelPersonas().setRowCount(0); // Para vaciar la tabla
		this.vista.getModelPersonas().setColumnCount(0);
		this.vista.getModelPersonas().setColumnIdentifiers(
				this.vista.getNombreColumnas());

		this.personas_en_tabla = agenda.obtenerPersonas();
		for (int i = 0; i < this.personas_en_tabla.size(); i++) {
			// esto es horrible
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateStr = formatter.format(this.personas_en_tabla.get(i)
					.getFechaCumpleanios());

			Object[] fila = {
					this.personas_en_tabla.get(i).getNombre(),
					this.personas_en_tabla.get(i).getTelefono(),
					dateStr,
					this.personas_en_tabla.get(i).getCorreoElectronico(),
					this.personas_en_tabla.get(i).getDomicilio().getCalle(),
					this.personas_en_tabla.get(i).getDomicilio().getAltura(),
					this.personas_en_tabla.get(i).getDomicilio().getPiso(),
					this.personas_en_tabla.get(i).getDomicilio().getDpto(),
					obtenerLocalidad(this.personas_en_tabla.get(i).getDomicilio().getLocalidad()),
					obtenerTipoContacto(this.personas_en_tabla.get(i)
							.getTipoContactoId()) };
			this.vista.getModelPersonas().addRow(fila);
		}

	}

	private String obtenerTipoContacto(int tipoContactoId) {
		for (TipoDeContactoDTO tipoDeContactoDTO : tiposDeContactos) {
			if (tipoDeContactoDTO.getIdTipo() == tipoContactoId)
				return tipoDeContactoDTO.getDescripcion();
		}
		return "";
	}
	
	private String obtenerLocalidad(int localidadId) {
		for (LocalidadDTO tipoDeContactoDTO : localidades) {
			if (tipoDeContactoDTO.getIdLocalidad() == localidadId)
				return tipoDeContactoDTO.getDescripcion();
		}
		return "";
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBtnAgregar()) {
			this.ventanaPersona = new VentanaPersona(this);
		} else if (e.getSource() == this.vista.getBtnBorrar()) {
			int[] filas_seleccionadas = this.vista.getTablaPersonas()
					.getSelectedRows();
			for (int fila : filas_seleccionadas) {
				this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
			}
			this.llenarTabla();
		} else if (e.getSource() == this.vista.getBtnEditar()) {
			int[] filas_seleccionadas = this.vista.getTablaPersonas()
					.getSelectedRows();
			
			for (int fila : filas_seleccionadas) {
				personaEdit = this.personas_en_tabla.get(fila);
				this.ventanaPersona = new VentanaPersona(this);
			}
			
		} else if (e.getSource() == this.vista.getBtnReporte()) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();
		} else if (e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) {
			if(camposValidados()) {
				
				DomicilioDTO domicilio = crearDomicilio();
				PersonaDTO nuevaPersona = crearPersona(domicilio);

				if (personaEdit != null) {
					nuevaPersona.getDomicilio().setIdDomicilio(
							personaEdit.getDomicilio().getIdDomicilio());
					nuevaPersona.setIdPersona(personaEdit.getIdPersona());
					this.agenda.updatePersona(nuevaPersona);
					personaEdit = null;
				} else {
					this.agenda.agregarPersona(nuevaPersona);
				}
				this.llenarTabla();
				this.ventanaPersona.dispose();
			}else {
				this.ventanaPersona.showError();
			}
		}
	}

	private PersonaDTO crearPersona(DomicilioDTO domicilio) {
		@SuppressWarnings("deprecation")
		PersonaDTO nuevaPersona = new PersonaDTO(0,
				this.ventanaPersona.getTxtNombre(),
				ventanaPersona.getTxtTelefono(), domicilio, 
				new Date(ventanaPersona.getCumpleInput()),
				ventanaPersona.getCorreoElecInput(), ventanaPersona
						.getTipoContacto().getIdTipo());
		return nuevaPersona;
	}

	private DomicilioDTO crearDomicilio() {
		DomicilioDTO domicilio = new DomicilioDTO(0,
			ventanaPersona.getCalleInput(),
			ventanaPersona.getAlturaInput(),
			ventanaPersona.getPisoInput(),
			ventanaPersona.getDptoInput(), ventanaPersona
					.getLocalidad().getIdLocalidad());
		return domicilio;
	}

	private boolean camposValidados() {
		return validarNombre() & validarTelefono() & validarEmail() & validarFecha() & validarAltura() & validarPiso() & validarCalle();
	}

	private boolean validador(String regex,String matcher) {
		Pattern pat = Pattern.compile(regex);
	    Matcher mat = pat.matcher(matcher);
		return mat.matches();
	}
	
	private boolean validarFecha() {
		return validador("^(0?[1-9]|[12][0-9]|3[01])[\\/](0?[1-9]|1[012])[/\\\\/](19|20)\\d{2}$",this.ventanaPersona.getCumpleInput());
	}

	private boolean validarEmail() {
		String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
		return validador(EMAIL_VERIFICATION,this.ventanaPersona.getCorreoElecInput());
	}

	private boolean validarTelefono() {
		return validador("[0-9]+",this.ventanaPersona.getTxtTelefono());
	}

	private boolean validarNombre() {
		return validador("[A-Za-z]+",this.ventanaPersona.getTxtNombre());
	}
	
	private boolean validarCalle() {
		return validador("^[A-z]+\\s[A-z]+|^[A-z]+|^[A-z]+\\s",this.ventanaPersona.getCalleInput());
	}
	
	private boolean validarPiso() {
		return validador("[0-9]+|\\s",this.ventanaPersona.getPisoInput());
	}
	
	private boolean validarAltura() {
		return validador("[0-9]+",this.ventanaPersona.getAlturaInput());
	}

	public List<LocalidadDTO> getLocalidades() {
		return localidades;
	}

	public List<TipoDeContactoDTO> getTiposDeContactos() {
		return tiposDeContactos;
	}

	public PersonaDTO getPersonaEdit() {
		return personaEdit;
	}

}
