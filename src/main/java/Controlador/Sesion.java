package Controlador;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;

import Modelo.Administrador;
import Modelo.Terapista;
import Negocio.AdministradorDAO;
import Negocio.TerapistaDAO;



@ManagedBean
@SessionScoped
public class Sesion implements Serializable{
	
	//Para el TerapistaNiño 
	private String user;
	private String password;
	
	private Terapista t;

	@Inject
	private SesionDeLogueo ses;
	
	@Inject
	private AdministradorDAO adminDao;
	
	@Inject
	private TerapistaDAO terDao;
	
	//Lista de usuarios administradores
	private List<Administrador> listAdmin;
	

	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Terapista getT() {
		int id = t.getId();
		t = terDao.perfil(id);
		return t;
	}

	public void setT(Terapista t) {
		this.t = t;
	}

	
	/**
	 * Metodo de logeo del usuario administrador
	 * @return si se cumple la condicion returna pagina del administrados caso contrario retorna usuario o contraseña incorrecto
	 */
	public String loginTerapista(){
		System.out.println("login....... Terapista");
		
		 t= terDao.buscarTerapista(user, password);
		 System.out.println(t);
		if(t!=null){	
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", t);
			//HttpSession session =Util.getSession();
	        //session.setAttribute("username",user);
			ses.setUser(t);
			return "admin.jsf?faces-redirect=true";
		}else{
			return null;
		}
	}
	
	/**
	 * Metodo de logeo del usuario administrador
	 * @return si se cumple la condicion returna pagina del administrados caso contrario retorna usuario o contraseña incorrecto
	 */
	public String loginAdmin(){
		System.out.println("login......................");
		
		listAdmin = adminDao.loginAdmin(user, password);
		if(!listAdmin.isEmpty()){	
			return "administrador";
		}else{
			return null;
		}
	}
	
	/**
	 * Metodo para cerrar secion
	 * @return
	 */
	public String cerrarSecion(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso:", "Sesión finalizada satisfactoriamente!!"));
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login.jsf?faces-redirect=true";
	}

	public SesionDeLogueo getSes() {
		return ses;
	}

	public void setSes(SesionDeLogueo ses) {
		this.ses = ses;
	}

}
