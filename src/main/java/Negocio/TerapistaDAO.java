package Negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
		
		String jpql = "Select p From Terapista p where cedula = :ced ";
				Query q = em.createQuery(jpql,Terapista.class);
				q.setParameter("ced", ced);
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
	
	public Terapista perfil(int id){
		Terapista t=new Terapista();
		t = em.find(Terapista.class, id);
		return t;
	}
	public List<Terapista> getTerapistas(){
		/*String jpql = "SELECT tn FROM TerapistaNiño tn left join fetch tn.terapistaNiño n "
				+ "WHERE tn.terapista.id = :id"
				+" and n.nombre like :nom "  
				+" and n.apellido like :ap ";*/
		String jpql = "SELECT tn FROM Terapista tn";
				Query q = em.createQuery(jpql,Terapista.class);
				
				List<Terapista> listado = q.getResultList();
				System.out.println(listado.size());
				return listado;
	}
	
	public void borrar(int id){
		Terapista nin = em.find(Terapista.class, id);
		em.remove(nin);
	}
	
}
