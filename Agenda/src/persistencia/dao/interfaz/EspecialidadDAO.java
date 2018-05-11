package persistencia.dao.interfaz;


import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.EspecialidadDTO;
import exceptions.DuplicadoException;

public interface EspecialidadDAO extends DAO<EspecialidadDTO>{

	public boolean insert(EspecialidadDTO localidad)throws DuplicadoException;
	public void borrar(EspecialidadDTO localidad_a_eliminar) throws MySQLIntegrityConstraintViolationException;
}
