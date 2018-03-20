package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;
import dto.LocalidadDTO;

public class LocalidadDAOSQL implements LocalidadDAO {
	private static final String readall = "SELECT * FROM localidades";
	private static final String insert = "INSERT INTO localidades(Nombre) VALUES(?)";
	private static final String update = "UPDATE localidades SET Nombre=?  WHERE idLocalidad = ?";
	private static final String delete = "DELETE FROM localidades WHERE idLocalidad = ?";

	@Override
	public boolean delete(LocalidadDTO localidad_a_eliminar) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, localidad_a_eliminar.getIdLocalidad());
			if(statement.executeUpdate()>0)
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<LocalidadDTO> readAll() {
		List<LocalidadDTO> localidadesRet = new ArrayList<LocalidadDTO>();
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				localidadesRet.add(new LocalidadDTO(resultSet
						.getInt("idLocalidad"), resultSet.getString("Nombre")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return localidadesRet;
	}

	@Override
	public boolean update(LocalidadDTO localidad) {
		PreparedStatement statement = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, localidad.getDescripcion());
			int result = statement.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean insert(LocalidadDTO localidad) {
		PreparedStatement statement = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, localidad.getDescripcion());
			int result = statement.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}