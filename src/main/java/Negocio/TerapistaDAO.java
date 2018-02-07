package Negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import Modelo.Administrador;
import Modelo.Terapista;


@Stateless
public class TerapistaDAO {

	@Inject
	EntityManager em;
	
	/**
	 * Metodo para insertar datos del objeto Terapista
	 * @param ter
	 */
	public boolean insertarTerapista(Terapista ter){
		try{
			em.persist(ter);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Metodo para actualizar datos del objeto Terapista
	 * @param ter
	 */
	public void actualizarTerapista(Terapista ter){
		em.merge(ter);
	}
	
	/**
	 * Metodo para guardar un nuevo registro del objeto Niño o actualizar un registro
	 * @param ter
	 */
	public void save(Terapista ter){
		if(em.find(Terapista.class, ter.getId())==null)
			insertarTerapista(ter);
		else
			actualizarTerapista(ter);
	}
	
	/**
	 * Metodo para verificar si existe la cedula en la B.D
	 * @param cedula
	 * @return
	 */
	public Terapista verificarCedula(String cedula){
		try{
			String sql = "SELECT ter FROM Terapista ter " +
					"WHERE ter.cedula LIKE :mi_cedula";
			Query q = em.createQuery(sql, Terapista.class);
			q.setParameter("mi_cedula", cedula);
			return (Terapista) q.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	public List<Terapista> getTerapistas( String nom,String ape,String ced){
		
		String jpql = "Select p From Terapista p where cedula like :ced " + 
				" and nombre like :nom " + 
				" and apellido like :ap ";
				Query q = em.createQuery(jpql,Terapista.class);
				q.setParameter("ced", ced+"%");
				q.setParameter("nom", nom+"%");
				q.setParameter("ap", ape+"%");
				List<Terapista> listado = q.getResultList();
				System.out.println(listado.size());
				return listado;
		
	}
	
	public Terapista buscarTerapista(String user,String password){
		try {
			Query q = em.createQuery("SELECT a FROM Terapista a WHERE username = :user AND password = :password",Terapista.class);
			q.setParameter("user", user);
			q.setParameter("password", password);
			Terapista admin = (Terapista) q.getSingleResult();
			
			
			if(admin!=null)
				return admin;
			
			return null;
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
		return null;
	
	}
	
}
