package Servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import Modelo.Actividad;
import Modelo.Niño;
import Modelo.Puntajes;
import Modelo.SesionJuego;
import Negocio.ActividadDAO;
import Negocio.NiñoDAO;
import Negocio.SesionJuegoDao;

@Path("usuario")
public class UsuarioWS {
	@Inject
	private NiñoDAO ndao;
	 
	@Inject
	private ActividadDAO actDao; 
	
	@Inject
	private SesionJuegoDao sesDao;
	
	
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
	
	@GET
	@Path("jugador")
	@Produces("application/json")
	public int  buscar(@QueryParam("user") String user){
		System.out.println(user);
		Niño n=new Niño();
		n=ndao.verificarUsuarioNiño(user);
		if(n!=null){
		//	System.out.println(correo);
			return n.getId();
		}
		return -1;
	}
	
	@GET
	@Path("jugadores")
	@Produces("application/json")
	public List<Niño>  buscar(){
		List<Niño> l=new ArrayList<Niño>();
		l=ndao.getNinos();
		if(l.size()!=0){
		//	System.out.println(correo);
			return l;
		}
		return null;
	}
	
	@GET
	@Path("save")
	@Produces("application/json")
	public int Guardar(@QueryParam("dato") String dato){
		SesionJuego sesj=new SesionJuego();
		Niño n=new Niño();
		Actividad ac=new Actividad();
		List<Puntajes> puntuaciones =new ArrayList<Puntajes>();
		
		try {
		//1*1*tiempo,2
		
			String[] datos=dato.split("-");
			System.out.println(datos[0]+"  "+datos[1]+" "+datos[2]);
			n=ndao.getNiño(Integer.parseInt(datos[0]));
			ac=actDao.getActividad(Integer.parseInt(datos[1]));
			if(n!=null){
			//	System.out.println(correo);
				sesj.setNino(n);
				sesj.setActividad(ac);
				String [] puntajes=datos[2].split(";");
				System.out.println(puntajes[0]);
				for (int i = 0; i < puntajes.length; i++) {
					String [] valor=puntajes[i].split(",");
					Puntajes pt=new Puntajes();
					pt.setNombre(valor[0]);
					pt.setValor(Integer.parseInt(valor[1]));
					puntuaciones.add(pt);
				}
				sesj.setPuntajes(puntuaciones);
				
				
			}
			sesj.setFecha(obtenerFechaHora());
			sesDao.saveSesion(sesj);
			return 1;		
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			return -1;
		}
	}
	
	
	public String obtenerFechaHora() {
		Calendar fecha = new GregorianCalendar();
        
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        
       String fech=""+dia+"/"+mes+1+"/"+ano+"    "+hora+":"+minuto+":"+segundo;
		return fech;
	}
	
}
