package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import presentacion.controlador.ControladorABMs;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class VentanaLocalidad extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5017018168323418356L;
	private ControladorABMs controlador;
	private JTextField inputLocalidad;
	private JTextField inputCodigoPostal;
	private JButton btnAgregarLocalidad;
	private JLabel lblIngreseLocalidadY;
	private JSeparator separator;
	
	public VentanaLocalidad(ControladorABMs controlador){
		setResizable(false);
		setAlwaysOnTop(true);
		this.controlador = controlador;
		
		setBounds(100, 100, 398, 190);
		getContentPane().setLayout(null);
		
		inputLocalidad = new JTextField();
		inputLocalidad.setBounds(171, 42, 201, 20);
		getContentPane().add(inputLocalidad);
		inputLocalidad.setColumns(10);
		
		JLabel lblLocalidad = new JLabel("Localidad ");
		lblLocalidad.setBounds(27, 45, 97, 14);
		getContentPane().add(lblLocalidad);
		
		inputCodigoPostal = new JTextField();
		inputCodigoPostal.setBounds(171, 76, 200, 20);
		getContentPane().add(inputCodigoPostal);
		inputCodigoPostal.setColumns(10);
		
		JLabel lblCdigoPostal = new JLabel("C\u00F3digo Postal ");
		lblCdigoPostal.setBounds(27, 79, 107, 14);
		getContentPane().add(lblCdigoPostal);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(285, 122, 89, 23);
		btnAgregarLocalidad.addActionListener(controlador);
		getContentPane().add(btnAgregarLocalidad);
		
		lblIngreseLocalidadY = new JLabel("Ingrese Localidad y Codigo Postal");
		lblIngreseLocalidadY.setBounds(27, 11, 187, 14);
		getContentPane().add(lblIngreseLocalidadY);
		
		separator = new JSeparator();
		separator.setBounds(27, 31, 345, 2);
		getContentPane().add(separator);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		validarEdicion();
		
		this.setVisible(true);
	}

	private void validarEdicion() {
		if(controlador.getLocalidadEdit()!=null){
			inputCodigoPostal.setText(controlador.getLocalidadEdit().getCodigoPostal());
			inputLocalidad.setText(controlador.getLocalidadEdit().getDescripcion());
			btnAgregarLocalidad.setName("Guardar");
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					int confirm = JOptionPane.showOptionDialog(null,
							"Estas seguro que quieres descartar la edición!?",
							"Confirmación", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (confirm == 0) {
						controlador.setLocalidadEdit(null);
					}
				}
			});
		}else{
			btnAgregarLocalidad.setName("Agregar");
		}
		
	}

	public String getInputLocalidad() {
		return inputLocalidad.getText();
	}

	public String getInputCodigoPostal() {
		return inputCodigoPostal.getText();
	}

	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}
	
	public void showError(String txt) {
		JOptionPane.showMessageDialog(null, txt);
	}
	
}
