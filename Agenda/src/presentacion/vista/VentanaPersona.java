package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

import javax.swing.JComboBox;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SuppressWarnings("rawtypes")
public class VentanaPersona extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private Controlador controlador;
	private JTextField correoElecInput;
	private JTextField cumpleInput;
	private JTextField calleInput;
	private JTextField alturaInput;
	private JTextField pisoInput;
	private JTextField dptoInput;
	private JComboBox localidadBox;
	private JComboBox tipoContactoBox;

	public VentanaPersona(Controlador controlador) {
		super();
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 587, 358);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.addActionListener(this.controlador);
		btnAgregarPersona.setBounds(469, 309, 89, 23);
		panel.add(btnAgregarPersona);

		JLabel lblCorreoElectrnico = new JLabel("Correo Electr\u00F3nico");
		lblCorreoElectrnico.setBounds(307, 52, 99, 14);
		panel.add(lblCorreoElectrnico);

		correoElecInput = new JTextField();
		correoElecInput.setColumns(10);
		correoElecInput.setBounds(413, 49, 164, 20);
		panel.add(correoElecInput);

		JLabel lblFechaCumpleaos = new JLabel("Fecha Cumplea\u00F1os");
		lblFechaCumpleaos.setBounds(307, 11, 99, 14);
		panel.add(lblFechaCumpleaos);

		cumpleInput = new JTextField();
		cumpleInput.setColumns(10);
		cumpleInput.setBounds(413, 8, 164, 20);
		panel.add(cumpleInput);

		JLabel lblTipoContacto = new JLabel("Tipo Contacto");
		lblTipoContacto.setBounds(10, 96, 113, 14);
		panel.add(lblTipoContacto);

		tipoContactoBox = new JComboBox();
		tipoContactoBox.setBounds(133, 93, 164, 20);
		panel.add(tipoContactoBox);

		JPanel panel_domicilio = new JPanel();
		panel_domicilio.setBackground(Color.LIGHT_GRAY);
		panel_domicilio.setBounds(10, 143, 567, 155);
		panel.add(panel_domicilio);
		panel_domicilio.setLayout(null);

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 11, 150, 14);
		panel_domicilio.add(lblDomicilio);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 36, 27, 14);
		panel_domicilio.add(lblCalle);

		calleInput = new JTextField();
		calleInput.setBounds(43, 33, 181, 20);
		panel_domicilio.add(calleInput);
		calleInput.setColumns(10);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(234, 36, 38, 14);
		panel_domicilio.add(lblAltura);

		alturaInput = new JTextField();
		alturaInput.setBounds(277, 33, 59, 20);
		panel_domicilio.add(alturaInput);
		alturaInput.setColumns(10);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(346, 36, 27, 14);
		panel_domicilio.add(lblPiso);

		pisoInput = new JTextField();
		pisoInput.setBounds(373, 33, 59, 20);
		panel_domicilio.add(pisoInput);
		pisoInput.setColumns(10);

		JLabel lblDpto = new JLabel("Dpto.");
		lblDpto.setBounds(442, 36, 38, 14);
		panel_domicilio.add(lblDpto);

		dptoInput = new JTextField();
		dptoInput.setBounds(481, 33, 59, 20);
		panel_domicilio.add(dptoInput);
		dptoInput.setColumns(10);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 72, 59, 14);
		panel_domicilio.add(lblLocalidad);

		localidadBox = new JComboBox();
		localidadBox.setBounds(74, 69, 188, 20);
		panel_domicilio.add(localidadBox);
		llenarCombos();
		verificarEdicion();
		this.setVisible(true);
	}

	private void verificarEdicion() {
		PersonaDTO personaEdit = this.controlador.getPersonaEdit();
		if (personaEdit != null) {
			txtNombre.setText(personaEdit.getNombre());
			txtTelefono.setText(personaEdit.getTelefono());
			correoElecInput.setText(personaEdit.getCorreoElectronico());
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateStr = formatter.format(personaEdit.getFechaCumpleanios());
			cumpleInput.setText(dateStr);
			calleInput.setText(personaEdit.getDomicilio().getCalle());
			alturaInput.setText(personaEdit.getDomicilio().getAltura());
			pisoInput.setText(personaEdit.getDomicilio().getPiso());
			dptoInput.setText(personaEdit.getDomicilio().getDpto());
			localidadBox.setSelectedItem(obtenerLocalidad(personaEdit
					.getDomicilio().getLocalidad()));
			tipoContactoBox.setSelectedItem(obtenerTipoContacto(personaEdit
					.getTipoContactoId()));

		}

	}

	private TipoDeContactoDTO obtenerTipoContacto(int tipoContactoId) {
		for (TipoDeContactoDTO tipoDeContactoDTO : this.controlador
				.getTiposDeContactos()) {
			if (tipoDeContactoDTO.getIdTipo() == tipoContactoId)
				return tipoDeContactoDTO;
		}
		return null;
	}

	private LocalidadDTO obtenerLocalidad(int localidadId) {
		for (LocalidadDTO localidadDto : this.controlador.getLocalidades()) {
			if (localidadDto.getIdLocalidad() == localidadId)
				return localidadDto;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void llenarCombos() {
		for (LocalidadDTO iterable_element : this.controlador.getLocalidades()) {
			localidadBox.addItem(iterable_element);
		}
		for (TipoDeContactoDTO iterable_element : this.controlador
				.getTiposDeContactos()) {
			tipoContactoBox.addItem(iterable_element);
		}

	}
	
	public void showError() {
		JOptionPane.showMessageDialog(null, "Existen errores en la carga de datos por favor VERIFICAR");
	}

	public String getTxtNombre() {
		return txtNombre.getText();
	}

	public String getTxtTelefono() {
		return txtTelefono.getText();
	}

	public String getCorreoElecInput() {
		return correoElecInput.getText();
	}

	public String getCumpleInput() {
		return cumpleInput.getText();
	}

	public String getCalleInput() {
		return calleInput.getText();
	}

	public String getAlturaInput() {
		return alturaInput.getText();
	}

	public String getPisoInput() {
		return pisoInput.getText();
	}

	public String getDptoInput() {
		return dptoInput.getText();
	}

	public LocalidadDTO getLocalidad() {
		return (LocalidadDTO) localidadBox.getSelectedItem();
	}

	public TipoDeContactoDTO getTipoContacto() {
		return (TipoDeContactoDTO) tipoContactoBox.getSelectedItem();
	}
	
	public JButton getBtnAgregarPersona() {
		return btnAgregarPersona;
	}
}
