package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import persistencia.conexion.Conexion;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Vista {
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnLocalidades;
	private JButton btnTiposContactos;
	private JButton btnBorrar;
	private JButton btnReporte;
	JButton btnDatosDeConexin ;
	private JButton btnEditar;
	private DefaultTableModel modelPersonas;
	private String[] nombreColumnas = { "Nombre y Apellido", "Tel\u00E9fono",
			"Fecha de Cumplea\u00F1os", "Correo Electr\u00d3nico", "Calle",
			"Altura", "Piso", "Dpto.", "Localidad", "Tipo Contacto", };
	private JMenu mnNewMenu_1;
	private JSeparator separator;
	private JMenu mnLocalidades;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JMenuItem mntmSalir;

	public Vista() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1002, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		modelPersonas = new DefaultTableModel(null, nombreColumnas);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 976, 407);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(0, 26, 966, 368);
		panel.add(spPersonas);
		tablaPersonas = new JTable(modelPersonas);
		spPersonas.setViewportView(tablaPersonas);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 266, 21);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		separator = new JSeparator();
		mnNewMenu.add(separator);
		
		mnNewMenu_1 = new JMenu("Opciones Conexion");
		mnNewMenu.add(mnNewMenu_1);
		
		btnDatosDeConexin = new JButton("Datos de Conexi\u00F3n");
		mnNewMenu_1.add(btnDatosDeConexin);
		
		separator_4 = new JSeparator();
		mnNewMenu.add(separator_4);
		
		mntmSalir = new JMenuItem("Salir");
		mnNewMenu.add(mntmSalir);
		
		JMenu mnPersonas = new JMenu("Personas");
		menuBar.add(mnPersonas);
		
				btnAgregar = new JButton("Agregar");
				btnAgregar.setSize(30, 15);
				mnPersonas.add(btnAgregar);
				
				JSeparator separator_1 = new JSeparator();
				mnPersonas.add(separator_1);
				
						btnEditar = new JButton("Editar");
						btnEditar.setSize(30, 15);
						mnPersonas.add(btnEditar);
						
						JSeparator separator_2 = new JSeparator();
						mnPersonas.add(separator_2);
						
								btnBorrar = new JButton("Borrar");
								btnBorrar.setSize(30, 15);
								mnPersonas.add(btnBorrar);
								
								separator_3 = new JSeparator();
								mnPersonas.add(separator_3);
								//panel.add(btnLocalidades);

								btnTiposContactos = new JButton("Tipos De Contactos");
								mnPersonas.add(btnTiposContactos);
								btnTiposContactos.setBounds(10, 405, 137, 23);
								
								mnLocalidades = new JMenu("Localidades");
								menuBar.add(mnLocalidades);
								
										btnLocalidades = new JButton("Localidades");
										mnLocalidades.add(btnLocalidades);
										btnLocalidades.setBounds(157, 405, 89, 23);
								
								JMenu mnReporte = new JMenu("Reporte");
								menuBar.add(mnReporte);
								
										btnReporte = new JButton("Generar Reporte");
										mnReporte.add(btnReporte);
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
					try {
						Conexion.getConexion().cerrarConexion();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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

	public JButton getBtnTiposContactos() {
		return btnTiposContactos;
	}

	public JButton getBtnLocalidades() {
		return btnLocalidades;
	}

	public JButton getBtnDatosDeConexin() {
		return btnDatosDeConexin;
	}
	
	public JMenuItem getMntmSalir() {
		return mntmSalir;
	}
}
