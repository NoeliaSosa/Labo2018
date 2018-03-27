package persistencia.dao.interfaz;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.TipoDeContactoDTO;
import exceptions.DuplicadoException;

public interface TipoDeContactoDAO extends DAO<TipoDeContactoDTO>{
	
	public boolean insert(TipoDeContactoDTO tipoContacto)throws DuplicadoException;
	public void borrar(TipoDeContactoDTO tipoContacto) throws MySQLIntegrityConstraintViolationException;
}
