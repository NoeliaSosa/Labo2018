package modelo;

import java.util.List;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.DomicilioDAO;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoDeContactoDAO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoDeContactoDTO;

public class Agenda {
	private PersonaDAO persona;
	private DomicilioDAO domicilio;
	private TipoDeContactoDAO tipoContacto;
	private LocalidadDAO localidad;

	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.domicilio = metodo_persistencia.createDomicilioDAO();
		this.persona = metodo_persistencia.createPersonaDAO(this.domicilio);
		this.localidad = metodo_persistencia.createLocalidadDAO();
		this.tipoContacto = metodo_persistencia.createTipoDeContactoDAO();
	}

	public void agregarPersona(PersonaDTO nuevaPersona) {
		this.persona.insert(nuevaPersona);
	}
	
	public void updatePersona(PersonaDTO nuevaPersona) {
		this.persona.update(nuevaPersona);
	}
	
	public void borrarPersona(PersonaDTO persona_a_eliminar) {
		this.persona.delete(persona_a_eliminar);
	}

	public List<PersonaDTO> obtenerPersonas() {
		return this.persona.readAll();
	}

	public List<LocalidadDTO> obtenerLocalidades() {
		return this.localidad.readAll();
	}

	public List<TipoDeContactoDTO> obtenerTiposDeContactos() {
		return this.tipoContacto.readAll();
	}

}
