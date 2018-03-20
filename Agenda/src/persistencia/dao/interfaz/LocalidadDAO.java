package persistencia.dao.interfaz;

import dto.LocalidadDTO;

public interface LocalidadDAO extends DAO<LocalidadDTO> {

	public boolean insert(LocalidadDTO localidad);
}
