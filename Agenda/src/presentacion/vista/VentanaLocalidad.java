package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import presentacion.controlador.ControladorABMs;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaLocalidad extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5017018168323418356L;
	private ControladorABMs controlador;
	private JTextField inputLocalidad;
	private JTextField inputCodigoPostal;
	private JButton btnAgregarLocalidad;
	
	public VentanaLocalidad(ControladorABMs controlador){
		this.controlador = controlador;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		inputLocalidad = new JTextField();
		inputLocalidad.setBounds(170, 11, 201, 20);
		getContentPane().add(inputLocalidad);
		inputLocalidad.setColumns(10);
		
		JLabel lblLocalidad = new JLabel("Localidad : ");
		lblLocalidad.setBounds(40, 14, 97, 14);
		getContentPane().add(lblLocalidad);
		
		inputCodigoPostal = new JTextField();
		inputCodigoPostal.setBounds(284, 45, 86, 20);
		getContentPane().add(inputCodigoPostal);
		inputCodigoPostal.setColumns(10);
		
		JLabel lblCdigoPostal = new JLabel("C\u00F3digo Postal : ");
		lblCdigoPostal.setBounds(40, 48, 107, 14);
		getContentPane().add(lblCdigoPostal);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(335, 227, 89, 23);
		btnAgregarLocalidad.addActionListener(controlador);
		getContentPane().add(btnAgregarLocalidad);
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
