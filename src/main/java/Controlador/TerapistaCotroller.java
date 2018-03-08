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
	private String errorMsg;
	
	
	@PostConstruct
	public void init(){
		ter = new Terapista();
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
			if(!validarCedula(idCedula)) {
				errorMsg="Error, incorrecta.";
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
	
	public boolean validarCedula(String cedula) {
		    int suma=0;
		    if(cedula.length()==9){
		      System.out.println("Ingrese su cedula de 10 digitos");
		      return false;
		    }else{
		      int a[]=new int [cedula.length()/2];
		      int b[]=new int [(cedula.length()/2)];
		      int c=0;
		      int d=1;
		      for (int i = 0; i < cedula.length()/2; i++) {
		        a[i]=Integer.parseInt(String.valueOf(cedula.charAt(c)));
		        c=c+2;
		        if (i < (cedula.length()/2)-1) {
		          b[i]=Integer.parseInt(String.valueOf(cedula.charAt(d)));
		          d=d+2;
		        }
		      }
		    
		      for (int i = 0; i < a.length; i++) {
		        a[i]=a[i]*2;
		        if (a[i] >9){
		          a[i]=a[i]-9;
		        }
		        suma=suma+a[i]+b[i];
		      } 
		      int aux=suma/10;
		      int dec=(aux+1)*10;
		      if ((dec - suma) == Integer.parseInt(String.valueOf(cedula.charAt(cedula.length()-1))))
		        return true;
		      else
		        if(suma%10==0 && cedula.charAt(cedula.length()-1)=='0'){
		          return true;
		        }else{
		          return false;
		        }
		     
		  
	}
		    

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
}
