package persistencia.dao.interfaz;

import dto.PersonaDTO;

public interface PersonaDAO extends DAO<PersonaDTO>
{
	
	public boolean insert(PersonaDTO persona);
	
}
