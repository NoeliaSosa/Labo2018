package presentacion.vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaTiposDeContactos {

	private JFrame frame;
	private JTable tablaTiposDeContactos;
	private DefaultTableModel modelTiposContactos;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private String[] nombreColumnas = {"Tipo de Contacto"};
	
	public VentanaTiposDeContactos() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(0, 0, 434, 210);

		modelTiposContactos = new DefaultTableModel(null, nombreColumnas);
		tablaTiposDeContactos = new JTable(modelTiposContactos);
		spLocalidades.setViewportView(tablaTiposDeContactos);
		panel.add(spLocalidades);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(345, 238, 89, 23);
		panel.add(btnBorrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(249, 238, 89, 23);
		panel.add(btnEditar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(150, 238, 89, 23);
		panel.add(btnAgregar);
	}
	
	public void show() {
		this.frame.setVisible(true);
	}
	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JTable getTablaTiposDeContactos() {
		return tablaTiposDeContactos;
	}

	public DefaultTableModel getModelTiposContactos() {
		return modelTiposContactos;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	public void showError(String txt) {
		JOptionPane.showMessageDialog(null, txt);
	}
}
