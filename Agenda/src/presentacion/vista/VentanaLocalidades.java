package presentacion.vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaLocalidades {

	private JFrame frame;
	private JTable tablaLocalidades;
	private DefaultTableModel modelLocalidades;
	
	public VentanaLocalidades() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(0, 0, 831, 394);
		panel.add(spLocalidades);
		tablaLocalidades = new JTable(modelLocalidades);
		spLocalidades.setViewportView(tablaLocalidades);
	}

}
