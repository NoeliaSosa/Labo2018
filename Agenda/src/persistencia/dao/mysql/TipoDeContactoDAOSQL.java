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

	@Override
	public boolean delete(TipoDeContactoDTO persona_a_eliminar) {
		// TODO Auto-generated method stub
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
	public boolean update(TipoDeContactoDTO persona) {
		// TODO Auto-generated method stub
		return false;
	}

}
