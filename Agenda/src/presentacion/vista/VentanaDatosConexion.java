package presentacion.vista;

import java.awt.Rectangle;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import persistencia.conexion.Conexion;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

public class VentanaDatosConexion extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1125311358271779129L;
	private JTextField ipInput;
	private JTextField puertoInput;
	private JTextField usuarioInput;
	private JTextField contraseniaInput;
	private JButton btnGuardar;
	private JPasswordField passwordField;

	public VentanaDatosConexion() {
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(new Rectangle(200, 200, 200, 200));
		setTitle("Datos de Conexi\u00F3n");
		getContentPane().setLayout(null);	
		setBounds(100, 100, 384, 316);
		JLabel lblIp = new JLabel("Ip");
		lblIp.setBounds(45, 51, 62, 14);
		getContentPane().add(lblIp);

		ipInput = new JTextField();
		ipInput.setBounds(157, 48, 152, 23);
		getContentPane().add(ipInput);
		ipInput.setColumns(10);

		JLabel lblPuerto = new JLabel("Puerto\r\n");
		lblPuerto.setBounds(45, 85, 46, 14);
		getContentPane().add(lblPuerto);

		puertoInput = new JTextField();
		puertoInput.setBounds(157, 82, 152, 23);
		getContentPane().add(puertoInput);
		puertoInput.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario\r\n");
		lblUsuario.setBounds(45, 133, 46, 14);
		getContentPane().add(lblUsuario);

		usuarioInput = new JTextField();
		usuarioInput.setBounds(157, 133, 152, 23);
		getContentPane().add(usuarioInput);
		usuarioInput.setColumns(10);

		JLabel lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setBounds(45, 170, 102, 14);
		getContentPane().add(lblContrasenia);

		contraseniaInput = new JPasswordField();
		contraseniaInput.setBounds(157, 167, 152, 23);
		getContentPane().add(contraseniaInput);
		contraseniaInput.setColumns(10);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(136, 243, 89, 23);
		getContentPane().add(btnGuardar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 120, 319, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(24, 38, 319, 2);
		getContentPane().add(separator_1);
		
		JLabel lblIngreseDatosDe = new JLabel("Ingrese Datos de Conexion");
		lblIngreseDatosDe.setBounds(24, 13, 165, 27);
		getContentPane().add(lblIngreseDatosDe);
		
		//passwordField = new JPasswordField();
		//passwordField.setBounds(157, 201, 152, 23);
		//getContentPane().add(passwordField);
	}

	public void modificarDatos(String[] datos){
		String server = datos[0];
		String[] ipPuerto= server.split(":");
		ipInput.setText(ipPuerto[0]);
		puertoInput.setText(ipPuerto[1]);
		usuarioInput.setText(datos[1]);
		contraseniaInput.setText(datos[2]);
		setVisible(true);
	}
	public void mostrar() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int confirm = JOptionPane.showOptionDialog(null,
				"Debe ingresar los datos de conexión", "Confirmación",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);
		if (confirm != 0) {
			try {
				Conexion.getConexion().cerrarConexion();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.exit(0);
			
		}
		setVisible(true);
	}

	public void showError(String txt) {
		JOptionPane.showMessageDialog(null, txt);
	}

	public String getIpInput() {
		return ipInput.getText();
	}

	public String getPuertoInput() {
		return puertoInput.getText();
	}

	public String getUsuarioInput() {
		return usuarioInput.getText();
	}

	public String getContraseniaInput() {
		return contraseniaInput.getText();
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}
}
