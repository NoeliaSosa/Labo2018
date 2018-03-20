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

	@Override
	public boolean delete(LocalidadDTO persona_a_eliminar) {
		// TODO Auto-generated method stub
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
	public boolean update(LocalidadDTO persona) {
		// TODO Auto-generated method stub
		return false;
	}
}