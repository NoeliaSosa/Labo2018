package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.PersonaDAO;
import dto.DomicilioDTO;
import dto.PersonaDTO;
import exceptions.DuplicadoException;

public class PersonaDAOSQL implements PersonaDAO {
	private static final String insert = "INSERT INTO personas(Nombre, Telefono,CorreoElectronico,FechaCumpleanios,tipoContactoId,domicilioId) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String update = "UPDATE personas SET Nombre=?, Telefono=?,CorreoElectronico=?,FechaCumpleanios=?,tipoContactoId=?,domicilioId=?  WHERE idPersona = ?";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String select = "SELECT * FROM personas where Nombre=? and Telefono=?";
	private DomicilioDAO domicilioSQL;

	public PersonaDAOSQL(DomicilioDAO domicilio) {
		domicilioSQL = domicilio;
	}

	public boolean insert(PersonaDTO persona) throws DuplicadoException {
		if(!validaDuplicado(persona)){
			throw new DuplicadoException("La persona y telefono ya existen");
		}
		PreparedStatement statement;
		Conexion conexion;
		try {
			conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(insert);
			agregarDomicilioPersona(persona.getDomicilio());
			// statement.setInt(1, persona.getIdPersona());
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getCorreoElectronico());
			java.sql.Date date2 = new java.sql.Date(persona
					.getFechaCumpleanios().getTime());
			statement.setDate(4, date2);
			statement.setInt(5, persona.getTipoContactoId());
			statement.setInt(6, persona.getDomicilio().getIdDomicilio());
			if (statement.executeUpdate() == 0) {// Si hubo un error borro el
													// domicilio
				domicilioSQL.delete(persona.getDomicilio());

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean validaDuplicado(PersonaDTO persona) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion;
		try {
			conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(select);
			statement.setString(1,persona.getNombre());
			statement.setString(2,persona.getTelefono());
			resultSet = statement.executeQuery();
			if(resultSet!=null && resultSet.next()){
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}

	private void agregarDomicilioPersona(DomicilioDTO domicilio)
			throws SQLException, DuplicadoException {
		if(!domicilioSQL.validaDuplicadoDomicilio(domicilio)){
			throw new DuplicadoException ("El domicilio ya existe");
		}
		Integer idDomicilio = domicilioSQL.insert(domicilio);
		domicilio.setIdDomicilio(idDomicilio);

	}

	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion;
		try {
			conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1,
					Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutó devuelvo true
				borrarDomicilioPersona(persona_a_eliminar.getDomicilio());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void borrarDomicilioPersona(DomicilioDTO domicilio) {
		domicilioSQL.delete(domicilio);

	}

	public List<PersonaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion;
		try {
			conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				java.util.Date date2 = new java.util.Date(resultSet.getDate(
						"FechaCumpleanios").getTime());
				DomicilioDTO domicilio = obtenerDomicilio(resultSet
						.getInt("domicilioId"));
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"),
						resultSet.getString("Nombre"), resultSet
								.getString("Telefono"), domicilio, date2,
						resultSet.getString("CorreoElectronico"), resultSet
								.getInt("tipoContactoId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	private DomicilioDTO obtenerDomicilio(int int1) {
		return domicilioSQL.getDomicilio(int1);
	}

	@Override
	public boolean update(PersonaDTO persona) {
		PreparedStatement statement;
		Conexion conexion;
		try {
			conexion = Conexion.getConexion();
			statement = conexion.getSQLConexion().prepareStatement(update);			
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getCorreoElectronico());
			java.sql.Date date2 = new java.sql.Date(persona
					.getFechaCumpleanios().getTime());
			statement.setDate(4, date2);
			statement.setInt(5, persona.getTipoContactoId());
			statement.setInt(6, persona.getDomicilio().getIdDomicilio());
			statement.setInt(7, persona.getIdPersona());
			if (statement.executeUpdate() > 0) {
				return  updateDomicilioPersona(persona.getDomicilio());
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean updateDomicilioPersona(DomicilioDTO domicilio) {
		return domicilioSQL.update(domicilio);

	}

	@Override
	public PersonaDTO selectPorNombre(String nombre, String telefono) {
		return null;
		
	}
}
