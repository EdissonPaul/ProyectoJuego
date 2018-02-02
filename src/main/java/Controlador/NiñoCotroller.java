package Controlador;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import Modelo.Administrador;
import Modelo.Niño;
import Negocio.AdministradorDAO;
import Negocio.NiñoDAO;

@ManagedBean
@SessionScoped
public class NiñoCotroller {
	
	private Niño nino;
	
	//Lista de ninos
	private List<Niño> listNino;
	
	@Inject
	private NiñoDAO ninoDao;
	
	@PostConstruct
	public void init(){
		nino = new Niño();
		listNino = ninoDao.getNinos();
	}

	public Niño getNino() {
		return nino;
	}

	public void setNino(Niño nino) {
		this.nino = nino;
	}

	public List<Niño> getListNino() {
		return listNino;
	}

	public void setListNino(List<Niño> listNino) {
		this.listNino = listNino;
	}
	
	/**
	 * Metodo para guardar un nuevo registro de un Niño
	 * @return
	 */
	public String guardarNiño(){
		ninoDao.save(nino);
		init();
		return null;
	}
	
	/**
	 * Meto para elminar un registro del objeto Niño
	 * @param id para identificar que registro eliminar
	 * @return
	 */
	public String eliminarNiño(int id){
		ninoDao.borrar(id);
		init();
		return null;
	}
	
	/**
	 * Metodo para actualizar los datos de un niño
	 * @param id identifica que registro de niño quiere actualizar 
	 * @return
	 */
	public String actualizarNiño(int id){
		 Niño nino = ninoDao.getNiño(id);
		 this.nino = nino;
		return null;
	}

}
