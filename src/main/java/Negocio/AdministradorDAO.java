package Negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import Modelo.Administrador;
import Modelo.Terapista;



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
	public Administrador loginAdmin(String user,String password){
		try{
			String sql = "SELECT a FROM Administrador a "
					+ "WHERE usuario = ? "
					+" AND contraseña = ?";
		
		Query q = em.createQuery(sql,Administrador.class);
		q.setParameter(1, user);
		q.setParameter(2, password);
		Administrador admin = (Administrador)q.getSingleResult();
		if (admin!=null)
		return admin;
		
		return null;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	


}
