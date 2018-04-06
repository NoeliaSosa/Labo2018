package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Agenda;
import persistencia.conexion.Conexion;
import presentacion.reportes.PersonaDatasource;
import presentacion.reportes.PersonaReporte;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaDatosConexion;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import utils.FuncionesUtiles;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;
import exceptions.DuplicadoException;

public class Controlador implements ActionListener {
	private Vista vista;
	private VentanaDatosConexion ventanaDatosConexion;
	private List<PersonaDTO> personas_en_tabla;
	private List<LocalidadDTO> localidades;
	private List<TipoDeContactoDTO> tiposDeContactos;
	private VentanaPersona ventanaPersona;
	private Agenda agenda;
	private PersonaDTO personaEdit;
	private ControladorABMs controladorAbm;

	public Controlador(Vista vista, Agenda agenda,
			ControladorABMs controladorAbm,
			VentanaDatosConexion ventanaDatosConexion) {
		this.vista = vista;
		this.controladorAbm = controladorAbm;
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnReporte().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
		this.vista.getBtnDatosDeConexin().addActionListener(this);
		this.vista.getBtnTiposContactos().addActionListener(this);
		this.vista.getBtnLocalidades().addActionListener(this);
		this.vista.getMntmSalir().addActionListener(this);
		this.agenda = agenda;
		this.personas_en_tabla = null;
		this.personaEdit = null;
		this.ventanaDatosConexion = ventanaDatosConexion;
		this.ventanaDatosConexion.getBtnGuardar().addActionListener(this);
	}

	public void inicializar() {
		if (validaConexion()) {
			this.actualizarListas();
			this.llenarTabla();
			this.vista.show();
		} else {
			this.ventanaDatosConexion.mostrar();
		}
	}

	private boolean validaConexion() {
		boolean ret = true;
		try {
			Conexion.getConexion();
		} catch (SQLException e) {
			ret = false;
		}
		return ret;
	}

	public void actualizarListas() {
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
					obtenerLocalidad(this.personas_en_tabla.get(i)
							.getDomicilio().getLocalidad()),
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
		} else if (e.getSource() == this.vista.getBtnDatosDeConexin()) {
			String[] datos = FuncionesUtiles.obtenerDatosDeConexion();
			this.ventanaDatosConexion.modificarDatos(datos);
		} else if (e.getSource() == this.vista.getBtnReporte()) {
			ReporteAgenda reporte = new ReporteAgenda(obtenerPersonasReporte());
			reporte.mostrar();
		} else if (e.getSource() == this.ventanaDatosConexion.getBtnGuardar()) {
			if (camposValidadosConexion() && testeoOk()) {

				FuncionesUtiles.guardarDatosDeConexion(
						this.ventanaDatosConexion.getIpInput(),
						this.ventanaDatosConexion.getPuertoInput(),
						this.ventanaDatosConexion.getUsuarioInput(),
						this.ventanaDatosConexion.getContraseniaInput());
				this.ventanaDatosConexion.dispose();
				this.inicializar();

			} else {
				this.ventanaDatosConexion
						.showError("Los datos ingresados no son correctos");
			}
		} else if (e.getSource() == this.vista.getBtnLocalidades()) {
			this.controladorAbm.inicializarAbmLocalidades();
		} else if (e.getSource() == this.vista.getBtnTiposContactos()) {
			this.controladorAbm.inicializarAmbTiposContactos();
		}
		else if(e.getSource() == this.vista.getMntmSalir()){
			int confirm = JOptionPane.showOptionDialog(null,
					"Estas seguro que quieres salir de la Agenda!?",
					"Confirmación", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (confirm == 0) {
				try {
					Conexion.getConexion().cerrarConexion();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		}
		else if (e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) {
			if (camposValidados()) {

				DomicilioDTO domicilio = crearDomicilio();
				PersonaDTO nuevaPersona = crearPersona(domicilio);

				try {
					if (personaEdit != null) {
						nuevaPersona.getDomicilio().setIdDomicilio(
								personaEdit.getDomicilio().getIdDomicilio());
						nuevaPersona.setIdPersona(personaEdit.getIdPersona());
						this.agenda.updatePersona(nuevaPersona);
						personaEdit = null;
					} else {
						try {
							this.agenda.agregarPersona(nuevaPersona);
						} catch (DuplicadoException de) {
							this.ventanaPersona.showError(de.getError());
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				this.llenarTabla();
				this.ventanaPersona.dispose();

			} else {
				this.ventanaPersona.showError();
			}

		}
	}

	private boolean testeoOk() {
		try {
			Conexion.testearConexion(this.ventanaDatosConexion.getIpInput()
					+ ":" + this.ventanaDatosConexion.getPuertoInput(),
					this.ventanaDatosConexion.getUsuarioInput(),
					this.ventanaDatosConexion.getContraseniaInput());
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	private boolean camposValidadosConexion() {

		return !this.ventanaDatosConexion.getIpInput().isEmpty()
				&& FuncionesUtiles.soloNumeros(this.ventanaDatosConexion
						.getPuertoInput())
				&& !this.ventanaDatosConexion.getUsuarioInput().isEmpty()
				&& !this.ventanaDatosConexion.getContraseniaInput().isEmpty();
	}

	private PersonaDTO crearPersona(DomicilioDTO domicilio) {
		PersonaDTO nuevaPersona = new PersonaDTO(0,
				this.ventanaPersona.getTxtNombre(),
				ventanaPersona.getTxtTelefono(), domicilio, 
						ventanaPersona.getCumpleInput(),
				ventanaPersona.getCorreoElecInput(), ventanaPersona
						.getTipoContacto().getIdTipo());
		return nuevaPersona;
	}

	private DomicilioDTO crearDomicilio() {
		DomicilioDTO domicilio = new DomicilioDTO(0,
				ventanaPersona.getCalleInput(),
				ventanaPersona.getAlturaInput(), ventanaPersona.getPisoInput(),
				ventanaPersona.getDptoInput(), ventanaPersona.getLocalidad()
						.getIdLocalidad());
		return domicilio;
	}

	private boolean camposValidados() {
		return FuncionesUtiles.validarSoloLetras(this.ventanaPersona
				.getTxtNombre())
				& FuncionesUtiles.validarTelefono(this.ventanaPersona
						.getTxtTelefono())
				& FuncionesUtiles.validarEmail(this.ventanaPersona
						.getCorreoElecInput())				
				& FuncionesUtiles.soloNumeros(this.ventanaPersona
						.getAlturaInput())
				& FuncionesUtiles.validarPiso(this.ventanaPersona
						.getPisoInput())
				& FuncionesUtiles.validarCalle(this.ventanaPersona
						.getCalleInput());
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

	private PersonaDatasource obtenerPersonasReporte() {
		PersonaDatasource data = new PersonaDatasource();
		List<PersonaDTO> personas = agenda.obtenerPersonas();
		for (PersonaDTO personaDTO : personas) {
			PersonaReporte persona = new PersonaReporte(personaDTO.getNombre(),
					personaDTO.getTelefono(), obtenerLocalidad(personaDTO
							.getDomicilio().getLocalidad()),
					personaDTO.getFechaCumpleanios(),
					personaDTO.getCorreoElectronico(),
					obtenerTipoContacto(personaDTO.getTipoContactoId()));
			persona.setSigno(personaDTO.getFechaCumpleanios());
			data.addPersonaReporte(persona);
		}
		System.out.println(data.toString());
		return data;
	}

	String obtenerStrFecha(Date fecha) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(fecha);
	}

}
