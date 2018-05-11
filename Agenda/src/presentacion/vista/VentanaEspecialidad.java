package presentacion.vista;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import presentacion.controlador.Controlador;

import javax.swing.JButton;

public class VentanaEspecialidad extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtEspecialidad;
	private Controlador controlador;
	private JButton btnAgregar;
	private JButton btnCancelar;
	

	public VentanaEspecialidad(Controlador controlador) {
		setAlwaysOnTop(true);
		
		this.controlador = controlador;
		
		getContentPane().setLayout(null);
		
		JLabel lblEspecialidad = new JLabel("Especialidad: ");
		lblEspecialidad.setBounds(47, 23, 80, 14);
		getContentPane().add(lblEspecialidad);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setBounds(137, 20, 141, 20);
		getContentPane().add(txtEspecialidad);
		txtEspecialidad.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(68, 81, 89, 23);
		getContentPane().add(btnAgregar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(189, 81, 89, 23);
		getContentPane().add(btnCancelar);
		
		this.setVisible(true);
		
	}
	
	
	public JTextField getTxtEspecialidad() {
		return txtEspecialidad;
	}

	public void setTxtEspecialidad(JTextField txtEspecialidad) {
		this.txtEspecialidad = txtEspecialidad;
	}
	
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	
}
