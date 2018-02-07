package Controlador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import Modelo.TerapistaNiño;
import Modelo.Terapista;
import Negocio.LoginDAO;
import Negocio.TerapistaDAO;

@ManagedBean
public class TerapistaCotroller {
	
	@Inject
	private TerapistaDAO terapistaDao;
	
	@Inject
	private LoginDAO loginDao;
	
	@Inject
	private Terapista ter;
	
	private String errUsuario;
	private String errCedula;
	private String claveInsertada;
	private String errClave;
	
	
	@PostConstruct
	public void init(){
		ter = new Terapista();
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
				System.out.println(errCedula);
			}
		}
		
		//Validamos que coincidan Contraseñas insertadas
		String idClave = ter.getPassword();
		if(!idClave.equals(claveInsertada)&&idClave.length()>0&&claveInsertada.length()>0){
			errClave = "Contraseñas no coinciden";
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
			init();
			return null;
		}else {
			System.out.println("Terapista NO insertado");
			return null;
		}
	}

}
