package Negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import Modelo.Actividad;
import Modelo.Niño;
import Modelo.SesionJuego;


@Stateless
public class SesionJuegoDao {
	
	@Inject
	private EntityManager em;
	
	public void saveSesion(SesionJuego ses) {
		if(em.find(Actividad.class, ses.getId())!=null) {
			em.persist(ses);
		}else {
			em.merge(ses);
		}
		
		
	}
	public void borrar(int id){
		SesionJuego nin = em.find(SesionJuego.class, id);
		em.remove(nin);
	}

}
