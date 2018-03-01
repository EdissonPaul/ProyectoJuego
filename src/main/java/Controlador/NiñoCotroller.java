package Controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import Modelo.Administrador;
import Modelo.Niño;
import Modelo.SesionJuego;
import Modelo.Terapista;
import Modelo.TerapistaNiño;
import Negocio.AdministradorDAO;
import Negocio.NiñoDAO;
import Negocio.TerapistaDAO;
import Negocio.TerapistaNiñoDao;

@ManagedBean
@ViewScoped
public class NiñoCotroller {
	
	private Niño nino;
	
	private SesionJuego selectedSesion;
	
	private TerapistaNiño ter_nino;
	
	private String errUsuario;
	private String campoNombre="";
	private String campoApellido="";
	private String campoCedula="";
	
	private String campoActividad="";
	
	private String nombreNino="";
	private String apeNino="";
	
	
	private List<Terapista> listaTerapistas;
	private List<Terapista> selectedTerapistas;
	
	private List<SesionJuego> sesionesNino;
	
	private List<SesionJuego> filtersSesions;
	
	
	SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

	private Date campof_in;//fecha inicial
	private Date campof_fin;//fecha corte
	
	//Lista de ninos
	private List<TerapistaNiño> listNino;
	
	private int id;
	
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
		sesionesNino=new ArrayList<SesionJuego>();
		this.campof_fin=new Date();
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
	
	public void loadDetalleNiño(int id) {
		System.out.println("Niño a buscar " +id);
		nino=ninoDao.getNiño(id);
		//nino=ninoDao.obtnerNino(id);
		System.out.println("Niño obtenido detalle " +nino);
		loadSesiones(id);
	}
	public void loadSesiones(int id) {
		
		String f="";
		String ff=formateador.format(campof_fin);
		if(campof_in!=null)
			f=""+formateador.format(campof_in);
		sesionesNino=ninoDao.obtenerSesiones(id, f, ff);
	
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
	
	public String editarNino() {
		String idUsuario = nino.getUsuario();
		Niño comprobar = ninoDao.verificarNiño(nino.getId(),idUsuario);
		if(comprobar!=null){
			FacesMessage msg = new FacesMessage("Error", "usuario ya existe");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}else{
			ninoDao.save(nino);
			init();
			return null;
		}
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
			FacesMessage msg = new FacesMessage("Error", "usuario ya existe");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadDetalleNiño(id);
	}

	public SesionJuego getSelectedSesion() {
		return selectedSesion;
	}

	public void setSelectedSesion(SesionJuego selectedSesion) {
		this.selectedSesion = selectedSesion;
	}

	public Date getCampof_fin() {
		return campof_fin;
	}

	public void setCampof_fin(Date campof_fin) {
		this.campof_fin = campof_fin;
	}

	public List<SesionJuego> getSesionesNino() {
		return sesionesNino;
	}

	public void setSesionesNino(List<SesionJuego> sesionesNino) {
		this.sesionesNino = sesionesNino;
	}

	public String getCampoActividad() {
		return campoActividad;
	}

	public void setCampoActividad(String campoActividad) {
		this.campoActividad = campoActividad;
	}

	public Date getCampof_in() {
		return campof_in;
	}

	public void setCampof_in(Date campof_in) {
		this.campof_in = campof_in;
	}

	public List<SesionJuego> getFiltersSesions() {
		return filtersSesions;
	}

	public void setFiltersSesions(List<SesionJuego> filtersSesions) {
		this.filtersSesions = filtersSesions;
	}

	
	
	

	
	
	
	

}
