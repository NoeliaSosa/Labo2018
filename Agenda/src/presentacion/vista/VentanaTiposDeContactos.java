package presentacion.vista;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JSeparator;

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
		frame.setBounds(100, 100, 464, 296);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(0, 0, 434, 228);

		modelTiposContactos = new DefaultTableModel(null, nombreColumnas);
		tablaTiposDeContactos = new JTable(modelTiposContactos);
		spLocalidades.setViewportView(tablaTiposDeContactos);
		panel.add(spLocalidades);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu Opciones");
		menuBar.add(mnNewMenu);
		
		btnAgregar = new JButton("Agregar");
		mnNewMenu.add(btnAgregar);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		btnEditar = new JButton("Editar");
		mnNewMenu.add(btnEditar);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		
		btnBorrar = new JButton("Borrar");
		mnNewMenu.add(btnBorrar);
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
