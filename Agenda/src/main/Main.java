package main;

import javax.swing.UIManager;

import com.alee.laf.WebLookAndFeel;

import modelo.Agenda;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;


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
		Vista vista = new Vista();
		Agenda modelo = new Agenda(new DAOSQLFactory());
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
	}
}
