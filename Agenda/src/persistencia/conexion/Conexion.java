package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.FuncionesUtiles;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;

	private Conexion(String url, String user, String pass) throws SQLException {
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://" + url
					+ "/agenda", user, pass);
			System.out.println("Conexion exitosa");
		} catch (Exception e) {
			System.out.println("Conexion fallida");
			throw new SQLException();
		}
	}

	public static Conexion getConexion() throws SQLException {
		if (instancia == null) {
			String[] datos = FuncionesUtiles.obtenerDatosDeConexion();
			instancia = new Conexion(datos[0], datos[1], datos[2]);
		}
		return instancia;
	}

	public static Conexion testearConexion(String url, String user, String pass)
			throws SQLException {

		instancia = new Conexion(url, user, pass);
		return instancia;
	}

	public Connection getSQLConexion() {
		return this.connection;
	}

	public void cerrarConexion() {
		try {
			this.connection.close();
			System.out.println("Conexion cerrada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		instancia = null;
	}
}
