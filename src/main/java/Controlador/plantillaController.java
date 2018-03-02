package Controlador;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import Modelo.Terapista;

@Named
@ViewScoped
public class plantillaController {

	public plantillaController() {
		// TODO Auto-generated constructor stub
	}
	public void verificarUsuario() {
		try {
			System.out.println("entra a verificar");
			FacesContext face=FacesContext.getCurrentInstance();
			Terapista t=(Terapista) face.getExternalContext().getSessionMap().get("usuario");
			if (t==null) {
				face.getExternalContext().redirect("inicio.jsf");
			} else {

			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}

}
