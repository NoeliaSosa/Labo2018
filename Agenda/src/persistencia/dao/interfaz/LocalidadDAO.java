package persistencia.dao.interfaz;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.LocalidadDTO;
import exceptions.DuplicadoException;

public interface LocalidadDAO extends DAO<LocalidadDTO> {

	public boolean insert(LocalidadDTO localidad)throws DuplicadoException;
	public void borrar(LocalidadDTO localidad_a_eliminar) throws MySQLIntegrityConstraintViolationException;
}
