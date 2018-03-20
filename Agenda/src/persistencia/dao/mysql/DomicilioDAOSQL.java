package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;
import dto.DomicilioDTO;

public class DomicilioDAOSQL implements DomicilioDAO{
	
	private static final String insert = "INSERT INTO domicilios(Calle,Altura,piso,departamento,localidadId) VALUES(?, ?, ?, ?,?)";
	private static final String update = "UPDATE domicilios SET Calle=?,Altura=?,piso=?,departamento=?,localidadId=?  WHERE idDireccion = ?";
	private static final String delete = "DELETE FROM domicilios WHERE idDireccion = ?";
	private static final String select = "SELECT * FROM domicilios WHERE idDireccion = ?";
	private static final String readall = "SELECT * FROM domicilios";
	@Override
	public Integer insert(DomicilioDTO domicilio) throws SQLException {
		PreparedStatement statement = null;
		Conexion conexion = Conexion.getConexion();
		String idDomicilio = null; 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert,PreparedStatement.RETURN_GENERATED_KEYS);
			//statement.setInt(1, domicilio.getIdDomicilio());
			statement.setString(1, domicilio.getCalle());
			statement.setString(2, domicilio.getAltura());
			statement.setString(3, domicilio.getPiso());
			statement.setString(4,  domicilio.getDpto());
			statement.setInt(5, domicilio.getLocalidad());
			int result = statement.executeUpdate();
			if(result>0){				
				ResultSet rs = statement.getGeneratedKeys();
				if (rs != null && rs.next()) {
					idDomicilio = rs.getString(1);
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		 
		return Integer.valueOf(idDomicilio);
	}

	@Override
	public boolean delete(DomicilioDTO domicilio_a_eliminar) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, domicilio_a_eliminar.getIdDomicilio());
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
	public List<DomicilioDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<DomicilioDTO> domicilios = new ArrayList<DomicilioDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				domicilios.add(new DomicilioDTO(resultSet.getInt("idDomicilio"), resultSet.getString("Calle"), resultSet.getString("Altura"),
						resultSet.getString("piso"),resultSet.getString("dpto"),resultSet.getInt("tipoContactoId")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return domicilios;
	}

	@Override
	public DomicilioDTO getDomicilio(int id) {
		DomicilioDTO domicilioRet = null;
		ResultSet resultSet;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(select);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			if(resultSet!= null&&resultSet.next()){
				domicilioRet = new DomicilioDTO(resultSet.getInt("idDireccion"), resultSet.getString("Calle"), resultSet.getString("Altura"),
						resultSet.getString("piso"),resultSet.getString("departamento"),resultSet.getInt("localidadId"));
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return domicilioRet;
	}

	@Override
	public boolean update(DomicilioDTO domicilio) {
		PreparedStatement statement = null;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, domicilio.getCalle());
			statement.setString(2, domicilio.getAltura());
			statement.setString(3, domicilio.getPiso());
			statement.setString(4,  domicilio.getDpto());
			statement.setInt(5, domicilio.getLocalidad());
			statement.setInt(6, domicilio.getIdDomicilio());
			int result = statement.executeUpdate();
			if(result>0){				
				return true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		 
		return false;
	}

}
