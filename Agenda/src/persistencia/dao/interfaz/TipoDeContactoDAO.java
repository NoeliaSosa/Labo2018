package persistencia.dao.interfaz;

import dto.TipoDeContactoDTO;

public interface TipoDeContactoDAO extends DAO<TipoDeContactoDTO>{
	
	public boolean insert(TipoDeContactoDTO tipoContacto);
}
