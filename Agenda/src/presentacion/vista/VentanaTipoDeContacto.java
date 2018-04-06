package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import presentacion.controlador.ControladorABMs;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class VentanaTipoDeContacto extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6490078839313479637L;
	private ControladorABMs controlador;
	private JTextField tipoInput;
	private JButton btnAgregarTipoContacto;
	private JLabel lblIngreseNuevoTipo;
	private JSeparator separator;
	
	public VentanaTipoDeContacto (ControladorABMs controlador){
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 414, 131);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.controlador = controlador;
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de Contacto");
		lblNewLabel.setBounds(21, 37, 112, 14);
		getContentPane().add(lblNewLabel);
		
		tipoInput = new JTextField();
		tipoInput.setBounds(166, 34, 206, 20);
		getContentPane().add(tipoInput);
		tipoInput.setColumns(10);
		
		btnAgregarTipoContacto = new JButton("Agregar");
		btnAgregarTipoContacto.setBounds(281, 60, 89, 23);
		btnAgregarTipoContacto.addActionListener(controlador);
		getContentPane().add(btnAgregarTipoContacto);
		
		lblIngreseNuevoTipo = new JLabel("Ingrese Nuevo Tipo de Contacto");
		lblIngreseNuevoTipo.setBounds(21, 9, 233, 14);
		getContentPane().add(lblIngreseNuevoTipo);
		
		separator = new JSeparator();
		separator.setBounds(21, 24, 349, 2);
		getContentPane().add(separator);
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
							"Estas seguro que quieres descartar la edición!?",
							"Confirmación", JOptionPane.YES_NO_OPTION,
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
