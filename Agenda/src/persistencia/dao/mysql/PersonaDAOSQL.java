package persistencia.dao.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.DomicilioDTO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas(Nombre, Telefono,CorreoElectronico,FechaCumpleanios,tipoContactoId,domicilioId) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
		
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			int idDomicilio = agregarDomicilioPersona(persona.getDomicilio());
			//statement.setInt(1, persona.getIdPersona());
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getCorreoElectronico());
			statement.setDate(4, (Date) persona.getFechaCumpleanios());
			statement.setInt(5, persona.getTipoContactoId());
			statement.setInt(6, idDomicilio);
			if(statement.executeUpdate() > 0) //Si se ejecutÛ devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	


	private int agregarDomicilioPersona(DomicilioDTO domicilio) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			borrarDomicilioPersona(persona_a_eliminar.getDomicilio());
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecut√≥ devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	private void borrarDomicilioPersona(DomicilioDTO domicilio) {
		// TODO Auto-generated method stub
		
	}

	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				DomicilioDTO domicilio = obtenerDomicilio(resultSet.getInt("domicilioId"));
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"), resultSet.getString("Telefono"),domicilio
						,resultSet.getDate("FechaCumpleanios"),resultSet.getString("CorreoElectronico"),resultSet.getInt("tipoContactoId")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}


	private DomicilioDTO obtenerDomicilio(int int1) {
		// TODO Auto-generated method stub
		return null;
	}
}
