package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import presentacion.controlador.ControladorABMs;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaTipoDeContacto extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6490078839313479637L;
	private ControladorABMs controlador;
	private JTextField tipoInput;
	private JButton btnAgregarTipoContacto;
	
	public VentanaTipoDeContacto (ControladorABMs controlador){
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.controlador = controlador;
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de Contacto");
		lblNewLabel.setBounds(20, 22, 112, 14);
		getContentPane().add(lblNewLabel);
		
		tipoInput = new JTextField();
		tipoInput.setBounds(198, 19, 173, 20);
		getContentPane().add(tipoInput);
		tipoInput.setColumns(10);
		
		btnAgregarTipoContacto = new JButton("Agregar");
		btnAgregarTipoContacto.setBounds(335, 227, 89, 23);
		btnAgregarTipoContacto.addActionListener(controlador);
		getContentPane().add(btnAgregarTipoContacto);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		validarEdicion();
	
		this.setVisible(true);
	}

	private void validarEdicion() {
		if(controlador.getTipoEdit()!=null){
			tipoInput.setText(controlador.getTipoEdit().getDescripcion());
			btnAgregarTipoContacto.setName("Guardar");
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					int confirm = JOptionPane.showOptionDialog(null,
							"Estas seguro que quieres descartar la edici�n!?",
							"Confirmaci�n", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (confirm == 0) {
						controlador.setTipoEdit(null);
					}
				}
			});
		}else{
			btnAgregarTipoContacto.setName("Agregar");
		}
		
	}

	public String getTipoInput() {
		return tipoInput.getText();
	}

	public JButton getBtnAgregarTipoContacto() {
		return btnAgregarTipoContacto;
	}
	
	public void showError(String txt) {
		JOptionPane.showMessageDialog(null, txt);
	}
}