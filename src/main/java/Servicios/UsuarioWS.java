package Servicios;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("usuario")
public class UsuarioWS {
	
	/*@Inject
	private UsuarioDAO udao;
	
	@GET
	@Path("listar")
	@Produces("application/json")
	public List<Usuario> listarUsuario(@QueryParam("filtro") int filtro){
		return udao.getUsuario(filtro);
	}

	@GET
	@Path("list")
	@Produces("application/json")
	public List<Usuario> listarUsuarios(){
		return udao.getUsuarios();
	}
	
	@GET
	@Path("perfilEditar")
	@Produces("application/json")
	public boolean perfil(@QueryParam("correo") String correo){
		System.out.println(correo);
		if(correo.equals("Hola")){
		//	System.out.println(correo);
			return true;
		}else{
			return false;
		}
	}*/
	@GET
	@Path("perfilEditar")
	@Produces("application/json")
	public boolean perfil(@QueryParam("correo") String correo){
		System.out.println(correo);
		/*if(correo.equals("Hola")){
		//	System.out.println(correo);
			return true;
		}else{
			return false;
		}*/
		return true;
	}
}
