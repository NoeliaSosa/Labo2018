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

public class VentanaLocalidades {

	private JFrame frame;
	private JTable tablaLocalidades;
	private DefaultTableModel modelLocalidades;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private String[] nombreColumnas = {"Localidad", "Codigo Postal"};
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JSeparator separator;
	private JSeparator separator_1;
	
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
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Menu Opciones");
		menuBar.add(mnNewMenu);
		
		btnAgregar = new JButton("Agregar");
		mnNewMenu.add(btnAgregar);
		
		separator = new JSeparator();
		mnNewMenu.add(separator);
		
		btnEditar = new JButton("Editar");
		mnNewMenu.add(btnEditar);
		
		separator_1 = new JSeparator();
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
