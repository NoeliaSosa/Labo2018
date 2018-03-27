package persistencia.dao.interfaz;

import dto.PersonaDTO;
import exceptions.DuplicadoException;

public interface PersonaDAO extends DAO<PersonaDTO>
{
	
	public boolean insert(PersonaDTO persona)throws DuplicadoException;
	
	public PersonaDTO selectPorNombre(String nombre,String telefono);
}
