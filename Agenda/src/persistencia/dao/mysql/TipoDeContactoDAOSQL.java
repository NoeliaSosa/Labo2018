package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoDeContactoDAO;
import dto.TipoDeContactoDTO;

public class TipoDeContactoDAOSQL implements TipoDeContactoDAO {
	private static final String readall = "SELECT * FROM tipos_de_contactos";
	private static final String insert = "INSERT INTO tipos_de_contactos(Descripcion) VALUES(?)";
	private static final String update = "UPDATE tipos_de_contactos SET Descripcion=?  WHERE idTipo = ?";
	private static final String delete = "DELETE FROM tipos_de_contactos WHERE idTipo = ?";

	@Override
	public boolean delete(TipoDeContactoDTO tipoContacto_a_eliminar) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, tipoContacto_a_eliminar.getIdTipo());
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<TipoDeContactoDTO> readAll() {
		List<TipoDeContactoDTO> localidadesRet = new ArrayList<TipoDeContactoDTO>();
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				localidadesRet.add(new TipoDeContactoDTO(resultSet
						.getInt("idTipo"), resultSet.getString("Descripcion")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return localidadesRet;
	}

	@Override
	public boolean update(TipoDeContactoDTO tipoContacto) {
		PreparedStatement statement = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, tipoContacto.getDescripcion());
			statement.setInt(6, tipoContacto.getIdTipo());
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
	public boolean insert(TipoDeContactoDTO tipoContacto) {
		PreparedStatement statement = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, tipoContacto.getDescripcion());
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
