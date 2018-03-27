package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;
import dto.LocalidadDTO;
import exceptions.DuplicadoException;

public class LocalidadDAOSQL implements LocalidadDAO {
	private static final String readall = "SELECT * FROM localidades";
	private static final String insert = "INSERT INTO localidades(Nombre,CodigoPostal) VALUES(?,?)";
	private static final String update = "UPDATE localidades SET Nombre=?,CodigoPostal=?  WHERE idLocalidad = ?";
	private static final String delete = "DELETE FROM localidades WHERE idLocalidad = ?";
	private static final String select = "SELECT idLocalidad FROM localidades WHERE Nombre = ? and CodigoPostal = ? ";

	@Override
	public void borrar(LocalidadDTO localidad_a_eliminar)
			throws MySQLIntegrityConstraintViolationException {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, localidad_a_eliminar.getIdLocalidad());
			statement.executeUpdate();

		} catch (MySQLIntegrityConstraintViolationException m) {
			throw new MySQLIntegrityConstraintViolationException();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
						.getInt("idLocalidad"), resultSet.getString("Nombre"),
						resultSet.getString("CodigoPostal")));
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
			statement.setString(2, localidad.getCodigoPostal());
			statement.setInt(3, localidad.getIdLocalidad());
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
	public boolean insert(LocalidadDTO localidad) throws DuplicadoException {

		PreparedStatement statement = null;
		Conexion conexion = Conexion.getConexion();
		try {
			if (!validaDuplicado(localidad)) {
				throw new DuplicadoException("La localidad ya existe");
			}
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, localidad.getDescripcion());
			statement.setString(2, localidad.getCodigoPostal());
			int result = statement.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (DuplicadoException e) {
			throw new DuplicadoException("La localidad ya existe");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private boolean validaDuplicado(LocalidadDTO localidad) {
		PreparedStatement statement;
		ResultSet resultSet = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(select);
			statement.setString(1, localidad.getDescripcion());
			statement.setString(2, localidad.getCodigoPostal());
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

	@Override
	public boolean delete(LocalidadDTO persona_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}
}