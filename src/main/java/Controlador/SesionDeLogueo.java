package Controlador;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;

import Modelo.Administrador;
import Modelo.Terapista;
import Negocio.AdministradorDAO;



@ManagedBean
@SessionScoped
public class SesionDeLogueo implements Serializable{
	
	//Para el TerapistaNiño 
	private Terapista user;

	public Terapista getUser() {
		return user;
	}

	public void setUser(Terapista user) {
		this.user = user;
	}
	



}
