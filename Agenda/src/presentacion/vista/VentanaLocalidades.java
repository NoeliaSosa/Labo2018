package presentacion.vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaLocalidades {

	private JFrame frame;
	private JTable tablaLocalidades;
	private DefaultTableModel modelLocalidades;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private String[] nombreColumnas = {"Localidad", "Codigo Postal"};
	
	public VentanaLocalidades() {
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

		modelLocalidades = new DefaultTableModel(null, nombreColumnas);
		tablaLocalidades = new JTable(modelLocalidades);
		spLocalidades.setViewportView(tablaLocalidades);
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

	public JTable getTablaLocalidades() {
		return tablaLocalidades;
	}

	public DefaultTableModel getModelLocalidades() {
		return modelLocalidades;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	public void showError(String txt) {
		JOptionPane.showMessageDialog(null, txt);
	}
	
}
