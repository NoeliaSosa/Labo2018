package persistencia.dao.interfaz;


public interface DAOAbstractFactory {
	
	public DomicilioDAO createDomicilioDAO();
	public LocalidadDAO createLocalidadDAO();
	public TipoDeContactoDAO createTipoDeContactoDAO();
	public PersonaDAO createPersonaDAO(DomicilioDAO domicilio);

}
