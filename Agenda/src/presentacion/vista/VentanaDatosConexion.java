package presentacion.vista;

import java.awt.Rectangle;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import persistencia.conexion.Conexion;

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

	public VentanaDatosConexion() {
		setBounds(new Rectangle(200, 200, 200, 200));
		setTitle("Datos de Conexi\u00F3n");
		getContentPane().setLayout(null);	
		setBounds(100, 100, 555, 360);
		JLabel lblIp = new JLabel("ip");
		lblIp.setBounds(78, 40, 62, 14);
		getContentPane().add(lblIp);

		ipInput = new JTextField();
		ipInput.setBounds(190, 37, 152, 23);
		getContentPane().add(ipInput);
		ipInput.setColumns(10);

		JLabel lblPuerto = new JLabel("puerto");
		lblPuerto.setBounds(78, 91, 46, 14);
		getContentPane().add(lblPuerto);

		puertoInput = new JTextField();
		puertoInput.setBounds(190, 88, 86, 23);
		getContentPane().add(puertoInput);
		puertoInput.setColumns(10);

		JLabel lblUsuario = new JLabel("usuario");
		lblUsuario.setBounds(78, 139, 46, 14);
		getContentPane().add(lblUsuario);

		usuarioInput = new JTextField();
		usuarioInput.setBounds(190, 139, 152, 23);
		getContentPane().add(usuarioInput);
		usuarioInput.setColumns(10);

		JLabel lblContrasenia = new JLabel("Contrase\u00F1a");
		lblContrasenia.setBounds(78, 191, 102, 14);
		getContentPane().add(lblContrasenia);

		contraseniaInput = new JTextField();
		contraseniaInput.setBounds(190, 188, 152, 23);
		getContentPane().add(contraseniaInput);
		contraseniaInput.setColumns(10);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(384, 240, 89, 23);
		getContentPane().add(btnGuardar);
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
