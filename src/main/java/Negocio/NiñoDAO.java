package Negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import Modelo.Niño;


@Stateless
public class NiñoDAO {

	@Inject
	EntityManager em;
	
	/**
	 * Metodo para insertar datos del objeto Niño
	 * @param nin
	 */
	public void insertar(Niño nin){
		em.persist(nin);
	}
	
	/**
	 * Metodo para actualizar datos del objeto Niño
	 * @param nin
	 */
	public void actualizar(Niño nin){
		em.merge(nin);
	}
	
	/**
	 * Metodo para borrar un registro del objeto Niño
	 * @param id
	 */
	public void borrar(int id){
		Niño nin = em.find(Niño.class, id);
		em.remove(nin);
	}
	
	/**
	 * Metodo para guardar un nuevo registro del objeto Niño o actualizar un registro
	 * @param nin
	 */
	public void save(Niño nin){
		if(em.find(Niño.class, nin.getId())==null)
			insertar(nin);
		else
			actualizar(nin);
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
	public Niño getNiño(int id){
		return em.find(Niño.class,id);
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
