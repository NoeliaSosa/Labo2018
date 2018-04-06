package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import modelo.Agenda;
import presentacion.vista.VentanaLocalidad;
import presentacion.vista.VentanaLocalidades;
import presentacion.vista.VentanaTipoDeContacto;
import presentacion.vista.VentanaTiposDeContactos;
import utils.FuncionesUtiles;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.LocalidadDTO;
import dto.TipoDeContactoDTO;
import exceptions.DuplicadoException;


public class ControladorABMs implements ActionListener {
	private VentanaLocalidades vistaLoc;
	private VentanaTiposDeContactos vistaTiposCont;
	private Agenda modelo;
	private Controlador controladorAgenda;
	private List<LocalidadDTO> localidades;
	private List<TipoDeContactoDTO> tiposDeContactos;
	private LocalidadDTO localidadEdit;
	private TipoDeContactoDTO tipoEdit;
	private VentanaLocalidad ventanaLocalidad;
	private VentanaTipoDeContacto ventanaTipoDeContacto;

	public ControladorABMs(VentanaLocalidades vistaLoc,
			VentanaTiposDeContactos vistaTiposCont, Agenda modelo) {
		this.vistaLoc = vistaLoc;
		this.vistaTiposCont = vistaTiposCont;
		this.modelo = modelo;
		this.vistaLoc.getBtnAgregar().addActionListener(this);
		this.vistaLoc.getBtnBorrar().addActionListener(this);
		this.vistaLoc.getBtnEditar().addActionListener(this);
		this.vistaTiposCont.getBtnAgregar().addActionListener(this);
		this.vistaTiposCont.getBtnBorrar().addActionListener(this);
		this.vistaTiposCont.getBtnEditar().addActionListener(this);


	}

	public void inicializarAbmLocalidades() {
		llenarTablaLocalidades();
		this.vistaLoc.show();
	}

	private void llenarTablaLocalidades() {
		this.vistaLoc.getModelLocalidades().setRowCount(0); // Para vaciar la
															// tabla
		this.vistaLoc.getModelLocalidades().setColumnCount(0);
		this.vistaLoc.getModelLocalidades().setColumnIdentifiers(
				this.vistaLoc.getNombreColumnas());

		this.localidades = modelo.obtenerLocalidades();
		for (int i = 0; i < this.localidades.size(); i++) {
			Object[] fila = { this.localidades.get(i).getDescripcion(),
					this.localidades.get(i).getCodigoPostal(), };
			this.vistaLoc.getModelLocalidades().addRow(fila);
		}

	}

	public void inicializarAmbTiposContactos() {
		llenarTablaTiposContactos();
		this.vistaTiposCont.show();
	}

	private void llenarTablaTiposContactos() {
		this.vistaTiposCont.getModelTiposContactos().setRowCount(0); // Para
																		// vaciar
																		// la
																		// tabla
		this.vistaTiposCont.getModelTiposContactos().setColumnCount(0);
		this.vistaTiposCont.getModelTiposContactos().setColumnIdentifiers(
				this.vistaTiposCont.getNombreColumnas());

		this.tiposDeContactos = modelo.obtenerTiposDeContactos();
		for (int i = 0; i < this.tiposDeContactos.size(); i++) {
			Object[] fila = { this.tiposDeContactos.get(i).getDescripcion(), };
			this.vistaTiposCont.getModelTiposContactos().addRow(fila);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vistaLoc.getBtnAgregar()) {
			this.ventanaLocalidad = new VentanaLocalidad(this);
		} else if (e.getSource() == this.vistaLoc.getBtnBorrar()) {
			int[] filas_seleccionadas = this.vistaLoc.getTablaLocalidades()
					.getSelectedRows();
			for (int fila : filas_seleccionadas) {
				try {
					this.modelo.borrarLocalidad(this.localidades.get(fila));
				} catch (MySQLIntegrityConstraintViolationException e1) {
					this.vistaLoc
							.showError("No se puede borrar la localidad esta en uso");
				}
			}
			this.llenarTablaLocalidades();
		} else if (e.getSource() == this.vistaLoc.getBtnEditar()) {
			int[] filas_seleccionadas = this.vistaLoc.getTablaLocalidades()
					.getSelectedRows();

			for (int fila : filas_seleccionadas) {
				localidadEdit = this.localidades.get(fila);
				this.ventanaLocalidad = new VentanaLocalidad(this);
			}

		} else if (e.getSource() == this.vistaTiposCont.getBtnAgregar()) {
			this.ventanaTipoDeContacto = new VentanaTipoDeContacto(this);
		} else if (e.getSource() == this.vistaTiposCont.getBtnBorrar()) {
			int[] filas_seleccionadas = this.vistaTiposCont
					.getTablaTiposDeContactos().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				try {
					this.modelo.borrarTipoDeContacto(this.tiposDeContactos
							.get(fila));
				} catch (MySQLIntegrityConstraintViolationException e1) {
					this.vistaTiposCont
							.showError("No se uede eliminar el Tipo de Contacto esta en uso");
				}
			}
			this.llenarTablaLocalidades();
		} else if (e.getSource() == this.vistaTiposCont.getBtnEditar()) {
			int[] filas_seleccionadas = this.vistaTiposCont
					.getTablaTiposDeContactos().getSelectedRows();

			for (int fila : filas_seleccionadas) {
				tipoEdit = this.tiposDeContactos.get(fila);
				this.ventanaTipoDeContacto = new VentanaTipoDeContacto(this);
			}

		} else if (this.ventanaLocalidad != null
				&& e.getSource() == this.ventanaLocalidad
						.getBtnAgregarLocalidad()) {
			if (validaDatosLocalidad()) {
				LocalidadDTO localidadNueva = new LocalidadDTO(0,
						this.ventanaLocalidad.getInputLocalidad(),
						this.ventanaLocalidad.getInputCodigoPostal());
				try {
					if (getLocalidadEdit() == null) {
						this.modelo.agregarLocalidad(localidadNueva);
					} else {
						localidadNueva.setIdLocalidad(localidadEdit
								.getIdLocalidad());
						this.modelo.updateLocalidad(localidadNueva);
						this.controladorAgenda.inicializar();
						setLocalidadEdit(null);
					}

				} catch (DuplicadoException e1) {
					this.ventanaLocalidad.showError(e1.getError());
				}
				llenarTablaLocalidades();
				this.ventanaLocalidad.dispose();
			} else {
				this.ventanaLocalidad
						.showError("Verificar los Datos ingresados");
			}
		} else if (this.ventanaTipoDeContacto != null
				&& e.getSource() == this.ventanaTipoDeContacto
						.getBtnAgregarTipoContacto()) {
			if (validaDatosTipoContacto()) {
				TipoDeContactoDTO tipoNuevo = new TipoDeContactoDTO(0,
						this.ventanaTipoDeContacto.getTipoInput());
				try {
					if (getTipoEdit() != null) {
						tipoNuevo.setIdTipo(tipoEdit.getIdTipo());
						this.modelo.updateTipoDeContacto(tipoNuevo);
						setTipoEdit(null);
						this.controladorAgenda.inicializar();
					} else {

						this.modelo.agregarTipoDeContacto(tipoNuevo);
					}

				} catch (DuplicadoException e1) {
					this.ventanaTipoDeContacto.showError(e1.getError());
				}
				llenarTablaTiposContactos();
				this.ventanaTipoDeContacto.dispose();
			} else {
				this.ventanaTipoDeContacto
						.showError("Verificar los Datos ingresados");
			}
		}

	}

	private boolean validaDatosLocalidad() {
		return this.ventanaLocalidad.getInputLocalidad() != null
				&& !this.ventanaLocalidad.getInputLocalidad().isEmpty();		

	}

	private boolean validaDatosTipoContacto() {
		return this.ventanaTipoDeContacto.getTipoInput() != null
				&& !this.ventanaTipoDeContacto.getTipoInput().isEmpty()
				&& FuncionesUtiles.validarSoloLetras(this.ventanaTipoDeContacto
						.getTipoInput());
	}

	public LocalidadDTO getLocalidadEdit() {
		return localidadEdit;
	}

	public TipoDeContactoDTO getTipoEdit() {
		return tipoEdit;
	}

	public void setLocalidadEdit(LocalidadDTO localidadEdit) {
		this.localidadEdit = localidadEdit;
	}

	public void setTipoEdit(TipoDeContactoDTO tipoEdit) {
		this.tipoEdit = tipoEdit;
	}

	public void setControladorAgenda(Controlador controlador) {
		this.controladorAgenda=controlador;
		
	}

}
