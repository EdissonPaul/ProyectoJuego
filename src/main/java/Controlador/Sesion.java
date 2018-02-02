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
import Negocio.AdministradorDAO;



@ManagedBean
@SessionScoped
public class Sesion implements Serializable{
	
	//Para el Login 
	private String user;
	private String password;
	

	
	@Inject
	private AdministradorDAO adminDao;
	
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
	
	/**
	 * Metodo de logeo del usuario administrador
	 * @return si se cumple la condicion returna pagina del administrados caso contrario retorna usuario o contraseña incorrecto
	 */
	public String loginAdmin(){
		System.out.println("login......................");
		
		/*listAdmin = adminDao.loginAdmin(user, password);
		if(!listAdmin.isEmpty()){	
			return "administrador";
		}else{
			return null;
		}*/
		return null;
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

}
