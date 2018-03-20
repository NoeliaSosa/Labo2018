package persistencia.dao.interfaz;

import java.util.List;


public interface DAO<T> {

	public  boolean delete(T persona_a_eliminar);
	
	public List<T> readAll();
	
	public boolean update(T  persona);
}
