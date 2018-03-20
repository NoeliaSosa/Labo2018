package persistencia.dao.interfaz;

import java.util.List;

import dto.DomicilioDTO;


public interface DomicilioDAO {

		public Integer insert(DomicilioDTO domicilio);

		public  boolean delete(DomicilioDTO persona_a_eliminar);
		
		public List<DomicilioDTO> readAll();
	
}
