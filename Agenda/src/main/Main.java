package main;

import java.io.File;

import javax.swing.UIManager;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.controlador.ControladorABMs;
import presentacion.vista.VentanaDatosConexion;
import presentacion.vista.VentanaLocalidades;
import presentacion.vista.VentanaTiposDeContactos;
import presentacion.vista.Vista;

import com.alee.laf.WebLookAndFeel;


public class Main 
{

	public static void main(String[] args) 
	{
		try {
	        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel ( new WebLookAndFeel() );
	    } catch(Exception e) {
	        System.out.println("Error setting native LAF: " + e);
	    }
		File archivo  = new File("."+File.separator+"datos"+File.separator+"conexion.txt");
		Vista vista = new Vista();
		VentanaLocalidades ventanaLocalidades = new VentanaLocalidades();
		VentanaTiposDeContactos ventanaTiposContactos = new VentanaTiposDeContactos();
		VentanaDatosConexion ventanaConexion = new VentanaDatosConexion();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		ControladorABMs controladorABMs = new ControladorABMs(ventanaLocalidades, ventanaTiposContactos, modelo);
		Controlador controlador = new Controlador(vista, modelo,controladorABMs,ventanaConexion);
		controladorABMs.setControladorAgenda(controlador);
		controlador.inicializar();
	}
}
