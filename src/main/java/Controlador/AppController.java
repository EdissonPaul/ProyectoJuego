package Controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@RequestScoped
public class AppController implements Serializable{
	// BEAN de App para manejo de Faces Context	
	private static final long serialVersionUID = 1L;
	
	// Bean properties
	private String path;
	
	//Inyeccion con el EJB
	@Inject
	private FacesContext fc;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public FacesContext getFc() {
		return fc;
	}

	public void setFc(FacesContext fc) {
		this.fc = fc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@PostConstruct
	public void init(){
		ExternalContext ec = fc.getCurrentInstance().getExternalContext();
		this.path = ec.getApplicationContextPath();
	}

}
