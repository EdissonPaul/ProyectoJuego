package Negocio;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import Modelo.Terapista;



public class LoginDAO {
	
	@Inject
	private EntityManager em;
	
	
	/**
	 * Verificamos si existe el usuario en la Base de Datos
	 * @param usuario
	 * @return
	 */
	public Terapista verificarUsuario(String usuario){
		
		try{
			String sql = "SELECT log FROM Terapista log " +
					"WHERE log.username LIKE :mi_usuario";
			Query q = em.createQuery(sql, Terapista.class);
			q.setParameter("mi_usuario", usuario);
			return (Terapista)q.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
