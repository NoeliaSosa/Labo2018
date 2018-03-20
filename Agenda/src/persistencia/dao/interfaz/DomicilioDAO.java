package persistencia.dao.interfaz;

import java.sql.SQLException;

import dto.DomicilioDTO;


public interface DomicilioDAO extends DAO<DomicilioDTO> {

		public Integer insert(DomicilioDTO domicilio) throws SQLException;
		public DomicilioDTO getDomicilio(int id);
	
}
