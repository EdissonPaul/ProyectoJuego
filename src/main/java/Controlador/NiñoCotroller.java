package Controlador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import Modelo.Administrador;
import Modelo.Niño;
import Modelo.Terapista;
import Modelo.TerapistaNiño;
import Negocio.AdministradorDAO;
import Negocio.NiñoDAO;
import Negocio.TerapistaDAO;
import Negocio.TerapistaNiñoDao;

@ManagedBean
@SessionScoped
public class NiñoCotroller {
	
	private Niño nino;
	
	private TerapistaNiño ter_nino;
	
	private String errUsuario;
	private String campoNombre="";
	private String campoApellido="";
	private String campoCedula="";
	
	private String nombreNino="";
	private String apeNino="";
	
	
	private List<Terapista> listaTerapistas;
	private List<Terapista> selectedTerapistas;
	
	
	//Lista de ninos
	private List<TerapistaNiño> listNino;
	
	@Inject
	private NiñoDAO ninoDao;
	
	@Inject
	private TerapistaNiñoDao tnDao;
	
	@Inject
	private TerapistaDAO terDao;
	
	@Inject
	private SesionDeLogueo ses;
	
	@PostConstruct
	public void init(){
		nino = new Niño();
		loadNinños();
	}
	
	public void loadTerapistas() {
		listaTerapistas=terDao.getTerapistas(campoNombre,campoApellido,campoCedula);
	}
	
	public void loadNinños() {
		if(ses.getUser()!=null) {
			listNino=ninoDao.getniñoss(ses.getUser().getId(),nombreNino,apeNino);
			System.out.println("tamaño de lista niños" +listNino.size());
		}
	}

	public Niño getNino() {
		return nino;
	}

	public void setNino(Niño nino) {
		this.nino = nino;
	}

	public List<TerapistaNiño> getListNino() {
		return listNino;
	}

	public void setListNino(List<TerapistaNiño> listNino) {
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
	
	
	/**
	 * Metodo para validar los datos antes de registrar el Terapista
	 * @return
	 */
	public String validarDatosRegistro(){
		errUsuario = "";
		
		// Validamos el username
		String idUsuario = nino.getUsuario();
		Niño comprobar = ninoDao.verificarUsuarioNiño(idUsuario);
		if(comprobar!=null){
			errUsuario = "Error, usuario ya registrado.";
			System.out.println(errUsuario);
		}
		
		
		// Si no existe ningun error llamamos al metodo registrarTerapista
		if(errUsuario.equals("")){
			System.out.println("guardar.............."+selectedTerapistas.size());
			ninoDao.save(nino);
			Niño registrado=new Niño();
			registrado=ninoDao.verificarUsuarioNiño(idUsuario);
			for (int i = 0; i < selectedTerapistas.size(); i++) {
				System.out.println(selectedTerapistas.get(i).getApellido());
				ter_nino=new TerapistaNiño();
				ter_nino.setTerapista(selectedTerapistas.get(i));
				ter_nino.setNiño(registrado);
				tnDao.save(ter_nino);
			}
			init();
			return null;
		}else {
			return null;
		}
		
		
	}
	
	public void MostrarTerapistasSeleccionadas() {
		try {
			for (int i = 0; i < selectedTerapistas.size(); i++) {
				System.out.println(selectedTerapistas.get(i).getApellido() );
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		
	}
	
	public String getErrUsuario() {
		return errUsuario;
	}

	public void setErrUsuario(String errUsuario) {
		this.errUsuario = errUsuario;
	}

	public List<Terapista> getListaTerapistas() {
		return listaTerapistas;
	}

	public void setListaTerapistas(List<Terapista> listaTerapistas) {
		this.listaTerapistas = listaTerapistas;
	}

	public List<Terapista> getSelectedTerapistas() {
		return selectedTerapistas;
	}

	public void setSelectedTerapistas(List<Terapista> selectedTerapistas) {
		this.selectedTerapistas = selectedTerapistas;
	}

	public String getCampoNombre() {
		return campoNombre;
	}

	public void setCampoNombre(String campoNombre) {
		this.campoNombre = campoNombre;
	}

	public String getCampoApellido() {
		return campoApellido;
	}

	public void setCampoApellido(String campoApellido) {
		this.campoApellido = campoApellido;
	}

	public String getCampoCedula() {
		return campoCedula;
	}

	public void setCampoCedula(String campoCedula) {
		this.campoCedula = campoCedula;
	}

	public TerapistaNiño getTer_nino() {
		return ter_nino;
	}

	public void setTer_nino(TerapistaNiño ter_nino) {
		this.ter_nino = ter_nino;
	}

	public String getNombreNino() {
		return nombreNino;
	}

	public void setNombreNino(String nombreNino) {
		this.nombreNino = nombreNino;
	}

	public String getApeNino() {
		return apeNino;
	}

	public void setApeNino(String apeNino) {
		this.apeNino = apeNino;
	}

	public SesionDeLogueo getSes() {
		return ses;
	}

	public void setSes(SesionDeLogueo ses) {
		this.ses = ses;
	}

	
	
	

	
	
	
	

}
