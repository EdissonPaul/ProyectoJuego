package Negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import Modelo.Niño;
import Modelo.SesionJuego;
import Modelo.Terapista;
import Modelo.TerapistaNiño;


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
		Niño n=em.find(Niño.class,id);
		n.getSesionJuego().size();
		return n;
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
	public Niño verificarNiño(int id,String usuario){
		
		try{
			
			Query q = em.createQuery("SELECT n FROM Niño n WHERE n.id <> :mi_id and n.usuario LIKE :mi_usuario",Niño.class);
			q.setParameter("mi_usuario", usuario);
			q.setParameter("mi_id", id);
			Niño n= (Niño) q.getSingleResult();
			return n;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<SesionJuego> obtenerSesiones(int id,String fecha_in,String fecha_fin){
		
		String sql;
		if(fecha_in.length()==0) {
			sql = "SELECT * FROM sesionJuego n "
					+ "WHERE  n.niño_nin_id="+id;
		}else {
		
		sql = "SELECT * FROM sesionJuego n "
				+ "WHERE  n.ses_fecha BETWEEN to_Date('"+fecha_in+"','DD/MM/YYYY') AND to_Date('"+fecha_fin+"','DD/MM/YYYY') "
						+ " and n.niño_nin_id="+id;
		}
		
		
		System.out.println("query cuentas "+ sql);
		Query q =em.createNativeQuery(sql,SesionJuego.class );
		List<SesionJuego> lista = q.getResultList();
		System.out.println(" Tamaño  sesiones "+lista.size());
		return lista;
	}
	
	
	public List<TerapistaNiño> getniñoss(int id, String nom,String ape){
		
		String jpql = "SELECT tn FROM TerapistaNiño tn join fetch tn.niño n "
				+ "WHERE tn.terapista.id = :id"
				+" and n.nombre like :nom "  
				+" and n.apellido like :ap ";
				Query q = em.createQuery(jpql,TerapistaNiño.class);
				q.setParameter("id", id);
				q.setParameter("nom", "%"+nom+"%");
				q.setParameter("ap", "%"+ape+"%");
				List<TerapistaNiño> listado = q.getResultList();
				System.out.println(listado.size());
				return listado;
		
	}
	
	public List<TerapistaNiño> getTerapistaNinos(int id){
		
		String jpql = "SELECT n FROM TerapistaNiño n "
				+ "WHERE n.niño.id = :id";
				Query q = em.createQuery(jpql,TerapistaNiño.class);
				q.setParameter("id", id);
				List<TerapistaNiño> listado = q.getResultList();
				System.out.println(listado.size());
				return listado;
		
	}
	
	
}
