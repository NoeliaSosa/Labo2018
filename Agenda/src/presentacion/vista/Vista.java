package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import persistencia.conexion.Conexion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista {
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private JButton btnEditar;
	private DefaultTableModel modelPersonas;
	private String[] nombreColumnas = { "Nombre y apellido", "Tel\u00E9fono",
			"Fecha de Cumplea\u00F1os", "Correo Electr\u00D3nico", "Calle",
			"Altura", "Piso", "Dpto.", "Localidad", "Tipo Contacto", };

	public Vista() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 847, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		modelPersonas = new DefaultTableModel(null, nombreColumnas);
		
				JPanel panel = new JPanel();
				panel.setBounds(0, 0, 831, 433);
				frame.getContentPane().add(panel);
				panel.setLayout(null);
				
						JScrollPane spPersonas = new JScrollPane();
						spPersonas.setBounds(0, 11, 831, 383);
						panel.add(spPersonas);
						tablaPersonas = new JTable(modelPersonas);
						spPersonas.setViewportView(tablaPersonas);
						
								btnAgregar = new JButton("Agregar");
								btnAgregar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
									}
								});
								btnAgregar.setBounds(446, 405, 89, 23);
								panel.add(btnAgregar);
								
										btnEditar = new JButton("Editar");
										btnEditar.setBounds(541, 405, 89, 23);
										panel.add(btnEditar);
										
												btnBorrar = new JButton("Borrar");
												btnBorrar.setBounds(637, 405, 89, 23);
												panel.add(btnBorrar);
												
														btnReporte = new JButton("Reporte");
														btnReporte.setBounds(732, 405, 89, 23);
														panel.add(btnReporte);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null,
						"Estas seguro que quieres salir de la Agenda!?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnReporte() {
		return btnReporte;
	}

	public DefaultTableModel getModelPersonas() {
		return modelPersonas;
	}

	public JTable getTablaPersonas() {
		return tablaPersonas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
}
