package Controlador;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import Modelo.TerapistaNiño;
import Modelo.Niño;
import Modelo.Terapista;
import Negocio.LoginDAO;
import Negocio.NiñoDAO;
import Negocio.TerapistaDAO;
import Negocio.TerapistaNiñoDao;

@ManagedBean
public class AdminController {
	
	@Inject
	private TerapistaDAO terapistaDao;
	
	@Inject
	private LoginDAO loginDao;
	
	@Inject
	private NiñoDAO ninoDao;
	
	@Inject
	private TerapistaNiñoDao tnDao;
	
	@Inject
	private SesionDeLogueo ses;
	
	private Terapista ter;
	
	private String errUsuario;
	private String errCedula;
	private String claveInsertada;
	private String errClave;
	private String errorMsg;
	
	private List<Terapista> listaTerapista;
	private List<Niño> listaniños;
	
	
	@PostConstruct
	public void init(){
		ter=new Terapista();
		ter = new Terapista();
		loadTerapistas();
	}
	


	/**
	 * Metodo para validar los datos antes de registrar el Terapista
	 * @return
	 */
	public String validarDatosRegistro(){
		errUsuario = "";
		errCedula ="";
		errClave = "";
		
		// Validamos el username
		String idUsuario = ter.getUsername();
		Terapista comprobar = (Terapista)loginDao.verificarUsuario(idUsuario);
		if(comprobar!=null){
			errUsuario = "Error, usuario ya registrado.";
			errorMsg="Error,usuario ya registrado";
			System.out.println(errUsuario);
		}
		
		//Validamos Cedula
		String idCedula = ter.getCedula();
		if(idCedula.length()!=10){
			errCedula = "Error, cantidad de digitos incorrectos en la cedula.";
			System.out.println(errCedula);
		}else{
			Terapista terapistaCedula = (Terapista)terapistaDao.verificarCedula(idCedula);
			if(terapistaCedula!=null){
				errCedula = "Error, cedula ya registrada.";
				errorMsg="Error, cedula ya registrada.";
				System.out.println(errCedula);
			}
			
		}
		
		//Validamos que coincidan Contraseñas insertadas
		String idClave = ter.getPassword();
		if(!idClave.equals(claveInsertada)&&idClave.length()>0&&claveInsertada.length()>0){
			errClave = "Contraseñas no coinciden";
			errorMsg="Error, cedula ya registrada.";
			System.out.println(errClave);
		}
		
		// Si no existe ningun error llamamos al metodo registrarTerapista
		if(errUsuario.equals("")&&errCedula.equals("")&&errClave.equals("")){
			System.out.println("guardar..............");
			String pagina = registrarTerapista();
			return pagina;
		}else {
			return null;
		}
		
		
	}

	/**
	 * Metodo para registrar al Terapista
	 * @return
	 */
	public String registrarTerapista(){
		boolean res;
		res = terapistaDao.insertarTerapista(ter);
		if(res){
			System.out.println("Terapista insertado");
			errorMsg="Registro exitoso,vaya a inciar Sesion para comenzar";
			init();
			return null;
		}else {
			System.out.println("Terapista NO insertado");
			return null;
		}
	}

	
	public void loadTerapistas() {
		
		listaTerapista=terapistaDao.getTerapistas();
		System.out.println("tamaño de lista niños" +listaTerapista.size());
	
	}
	
	public void deleteTerapistas(Terapista t) {
		
		List<TerapistaNiño> listTN=new ArrayList<TerapistaNiño>();
		listTN=ninoDao.getniñoss(t.getId(),"","");
		
		for (int i = 0; i < listTN.size(); i++) {
			tnDao.borrar(listTN.get(i).getId());
		}	
		
		terapistaDao.borrar(t.getId());
		init();
	}
	
	public String ViewNiños(Terapista t) {
		
		ses.setUser(t);
		return "MantenimientoNinos.jsf?faces-redirect=true";
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public Terapista getTer() {
		return ter;
	}



	public void setTer(Terapista ter) {
		this.ter = ter;
	}

	public String getErrUsuario() {
		return errUsuario;
	}

	public void setErrUsuario(String errUsuario) {
		this.errUsuario = errUsuario;
	}

	public TerapistaDAO getTerapistaDao() {
		return terapistaDao;
	}

	public void setTerapistaDao(TerapistaDAO terapistaDao) {
		this.terapistaDao = terapistaDao;
	}

	public LoginDAO getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDAO loginDao) {
		this.loginDao = loginDao;
	}

	public String getClaveInsertada() {
		return claveInsertada;
	}

	public void setClaveInsertada(String claveInsertada) {
		this.claveInsertada = claveInsertada;
	}
	

	public String getErrCedula() {
		return errCedula;
	}

	public void setErrCedula(String errCedula) {
		this.errCedula = errCedula;
	}

	public String getErrClave() {
		return errClave;
	}

	public void setErrClave(String errClave) {
		this.errClave = errClave;
	}



	public List<Terapista> getListaTerapista() {
		return listaTerapista;
	}



	public void setListaTerapista(List<Terapista> lisaTerapista) {
		this.listaTerapista = lisaTerapista;
	}



	public List<Niño> getListaniños() {
		return listaniños;
	}



	public void setListaniños(List<Niño> listaniños) {
		this.listaniños = listaniños;
	}



	public SesionDeLogueo getSes() {
		return ses;
	}



	public void setSes(SesionDeLogueo ses) {
		this.ses = ses;
	}
}
