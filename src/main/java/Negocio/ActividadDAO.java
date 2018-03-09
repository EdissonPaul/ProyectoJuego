package Negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import Modelo.Actividad;

@Stateless
public class ActividadDAO {
	
	@Inject
	private EntityManager em;
	
	public Actividad getActividad(int codigo) {
		return em.find(Actividad.class, codigo);
	}

}
