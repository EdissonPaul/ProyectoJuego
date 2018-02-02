package Negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import Modelo.Administrador;


@Stateless
public class AdministradorDAO {
	
	@Inject
	private EntityManager em;
	
	/**
	 * Metodo para obtener logeo de usuario administrador
	 * @param user nombre del usuario
	 * @param password contraseña del usuario
	 * @return user admin logeado
	 */
	public List<Administrador> loginAdmin(String user,String password){
		
		String sql = "SELECT a FROM Administrador a "
				+ "WHERE usuario = ? "
				+" AND contraseña = ?";
	
	Query q = em.createQuery(sql,Administrador.class);
	q.setParameter(1, user);
	q.setParameter(2, password);
	List<Administrador> admin = q.getResultList();
	return admin;
	}

}
