/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.EspecialidadDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoDeContactoDAO;

public class DAOSQLFactory implements DAOAbstractFactory {
	/*
	 * (non-Javadoc)
	 * 
	 * @see persistencia.dao.interfaz.DAOAbstractFactory#createPersonaDAO()
	 */
	@Override
	public PersonaDAO createPersonaDAO(DomicilioDAO domicilio) {
		return new PersonaDAOSQL(domicilio);
	}

	@Override
	public DomicilioDAO createDomicilioDAO() {

		return new DomicilioDAOSQL();
	}

	@Override
	public LocalidadDAO createLocalidadDAO() {
		
		return new LocalidadDAOSQL();
	}

	@Override
	public TipoDeContactoDAO createTipoDeContactoDAO() {
		
		return new TipoDeContactoDAOSQL();
	}

	@Override
	public EspecialidadDAO createEspecialidadDAO() {
		// TODO Auto-generated method stub
		return new EspecialidadDAOSQL();
		}

}
