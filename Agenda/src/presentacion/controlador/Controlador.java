package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.DomicilioDTO;
import dto.PersonaDTO;

public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private VentanaPersona ventanaPersona; 
		private Agenda agenda;
		
		public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnReporte().addActionListener(this);
			this.agenda = agenda;
			this.personas_en_tabla = null;
		}
		
		public void inicializar()
		{
			this.llenarTabla();
			this.vista.show();
		}
		
		private void llenarTabla()
		{
			this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelPersonas().setColumnCount(0);
			this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.personas_en_tabla = agenda.obtenerPersonas();
			for (int i = 0; i < this.personas_en_tabla.size(); i ++)
			{
				//esto es horrible
				Object[] fila = {this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono(),
						this.personas_en_tabla.get(i).getFechaCumpleanios(),this.personas_en_tabla.get(i).getCorreoElectronico(),
						this.personas_en_tabla.get(i).getDomicilio().getCalle(),this.personas_en_tabla.get(i).getDomicilio().getAltura(),
						this.personas_en_tabla.get(i).getDomicilio().getPiso(),this.personas_en_tabla.get(i).getDomicilio().getDpto(),
						this.personas_en_tabla.get(i).getDomicilio().getLocalidad(),obtenerTipoContacto(this.personas_en_tabla.get(i).getTipoContactoId())};
				this.vista.getModelPersonas().addRow(fila);
			}			
		}
		
		private String obtenerTipoContacto(int tipoContactoId) {
			// TODO Auto-generated method stub
			return null;
		}

		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == this.vista.getBtnAgregar())
			{
				this.ventanaPersona = new VentanaPersona(this);
			}
			else if(e.getSource() == this.vista.getBtnBorrar())
			{
				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				for (int fila:filas_seleccionadas)
				{
					this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
				}
				
				this.llenarTabla();
				
			}
			else if(e.getSource() == this.vista.getBtnReporte())
			{				
				ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
				reporte.mostrar();				
			}
			else if(e.getSource() == this.ventanaPersona.getBtnAgregarPersona())
			{
				
				DomicilioDTO domicilio = new DomicilioDTO(ventanaPersona.getCalleInput(),ventanaPersona.getAlturaInput(),ventanaPersona.getPisoInput(),
						ventanaPersona.getDptoInput(),ventanaPersona.getLocalidad());
				//resolver el date
				PersonaDTO nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre(), ventanaPersona.getTxtTelefono(),
						domicilio,new Date(ventanaPersona.getCumpleInput()),ventanaPersona.getCorreoElecInput(),ventanaPersona.getTipoContacto());
				this.agenda.agregarPersona(nuevaPersona);
				this.llenarTabla();
				this.ventanaPersona.dispose();
			}
		}

}
