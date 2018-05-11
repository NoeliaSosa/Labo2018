package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.EspecialidadDTO;
import exceptions.DuplicadoException;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.EspecialidadDAO;


public class EspecialidadDAOSQL implements EspecialidadDAO{
	private static final String insert = "INSERT INTO especialidad(Nombre) VALUES(?)";
	private static final String select = "SELECT id FROM especialidad WHERE Nombre = ?";

	@Override
	public boolean delete(EspecialidadDTO persona_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EspecialidadDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(EspecialidadDTO persona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(EspecialidadDTO localidad) throws DuplicadoException {
		PreparedStatement statement = null;
		Conexion conexion;
		try {
			conexion = Conexion.getConexion();
			if (!validaDuplicado(localidad)) {
				throw new DuplicadoException("La Especialidad ya existe");
			}
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, localidad.getNombreEspecialidad());
			int result = statement.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (DuplicadoException e) {
			throw new DuplicadoException("La Especialidad ya existe");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public void borrar(EspecialidadDTO localidad_a_eliminar) throws MySQLIntegrityConstraintViolationException {
		
		
	}
	
	private boolean validaDuplicado(EspecialidadDTO localidad) {
		PreparedStatement statement;
		ResultSet resultSet = null;
		Conexion conexion;
		try {
			conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(select);
			statement.setString(1, localidad.getNombreEspecialidad());
			resultSet = statement.executeQuery();

			while (resultSet != null && resultSet.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	

}
