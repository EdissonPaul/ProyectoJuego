package Negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import Modelo.Niño;
import Modelo.Terapista;
import Modelo.TerapistaNiño;


@Stateless
public class TerapistaNiñoDao {

	@Inject
	EntityManager em;
	
	/**
	 * Metodo para insertar datos del objeto Niño
	 * @param nin
	 */
	public void insertar(TerapistaNiño nin){
		em.persist(nin);
	}
	
	/**
	 * Metodo para actualizar datos del objeto Niño
	 * @param nin
	 */
	public void actualizar(TerapistaNiño nin){
		em.merge(nin);
	}
	
	/**
	 * Metodo para borrar un registro del objeto Niño
	 * @param id
	 */
	public void borrar(int id){
		TerapistaNiño nin = em.find(TerapistaNiño.class, id);
		em.remove(nin);
	}
	
	/**
	 * Metodo para guardar un nuevo registro del objeto Niño o actualizar un registro
	 * @param nin
	 */
	public void save(TerapistaNiño nin){
		if(em.find(TerapistaNiño.class, nin.getId())==null)
			insertar(nin);
		else
			actualizar(nin);
	}
	
	
	public TerapistaNiño encontrarXTerapista(int ter ){
		
		
		try{
			
			Query q = em.createQuery("SELECT n FROM TerapistaNiño n  join fetch n.terapista t WHERE t.id = :ter",TerapistaNiño.class);
			q.setParameter("ter", ter);
			TerapistaNiño tp= (TerapistaNiño) q.getSingleResult();
			if (tp!= null)
				return tp;
			
		}
		catch (Exception e){}
		
		return null;
	}
	
	
	/**
	 * Meodo para obtener una lista de los niños
	 * @return
	 */
	public List<Niño> getNinos(){
		String sql = "SELECT n FROM Niño n";
		Query q =em.createQuery(sql,Niño.class );
		List<Niño> nino = q.getResultList();
		return nino;
	}
	
	/**
	 * Metodo para obtener los datos de un niño en especifico segun su id
	 * @param id
	 * @return
	 */
	public TerapistaNiño getNiño(int id){
		return em.find(TerapistaNiño.class,id);
	}
	
	/**
	 * Verificamos si existe el usuario en la Base de Datos
	 * @param usuario
	 * @return
	 */
	public Niño verificarUsuarioNiño(String usuario){
		
		try{
			
			Query q = em.createQuery("SELECT n FROM Niño n WHERE n.usuario LIKE :mi_usuario",Niño.class);
			q.setParameter("mi_usuario", usuario);
			Niño n= (Niño) q.getSingleResult();
			return n;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
