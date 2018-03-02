package Servicios;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import Utilidades.LevenshteinDistance;

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
	@Path("validar")
	@Produces("application/json")
	public boolean verificar(@QueryParam("str1") String text1,@QueryParam("str2") String text2){
		try {
			String resp=validarPalabras(text1.toUpperCase(), text2.toUpperCase());
			if (resp.equals("correcto")){
				System.out.println("correcto.........");
				return true;
			}
			System.out.println("incorrecto.........");
			return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return false;
	}
	
	@GET
	@Path("save")
	@Produces("application/json")
	public int Guardar(@QueryParam("dato") String dato){
		System.out.println(dato+" a guardar");
		SesionJuego sesj=new SesionJuego();
		Niño n=new Niño();
		Actividad ac=new Actividad();
		List<Puntajes> puntuaciones =new ArrayList<Puntajes>();
		
		try {
		//1*1*tiempo,2
		
			String[] datos=dato.split(":");
			System.out.println(datos[0]);
			System.out.println(datos[1]);
			System.out.println(datos[2]);
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
					if(!valor[2].equals("null")){
						System.out.println("valor fallos....."+valor[3]);
						pt.setValorFallos(Integer.parseInt(valor[2]));
					}else {
						pt.setValorFallos(-1);
					}
					if(!valor[3].equals("null")){
						System.out.println("valor esperado....."+valor[3]);
						pt.setValorEsperado(Integer.parseInt(valor[3]));
					}else{
						pt.setValorEsperado(-1);
					}
					/*if(Integer.parseInt(valor[2])==900){
						valor[2] ="0";
						System.out.println("valor sale...."+valor[2]);
						pt.setValorFallos(Integer.parseInt(valor[2]));
					}else {
						pt.setValorFallos(Integer.parseInt(valor[2]));
						System.out.println("valor entra...."+valor[2]);
					}
					if(Integer.parseInt(valor[3])==900){
						valor[3] ="0";
						System.out.println("valor sale...."+valor[3]);
						pt.setValorEsperado(Integer.parseInt(valor[3]));
					}else {
						pt.setValorEsperado(Integer.parseInt(valor[3]));
						System.out.println("valor entra...."+valor[3]);
					}*/				
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
	
	
	
	public Date obtenerFechaHora() {
		Calendar fecha = new GregorianCalendar();
        
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        
       String fech=""+dia+"/"+mes+1+"/"+ano+"    "+hora+":"+minuto+":"+segundo;
       Date dat=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Date d;
		try {
			d = formato.parse(formato.format(dat));
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
	}
	
	public String validarPalabras(String str1,String str2) {
		
	      LevenshteinDistance ld = new LevenshteinDistance();
	      ld.setWords(str1, str2);

	      // Mostrar resultados
	      System.out.println("Palabra1: " + str1);
	      System.out.println("Palabra2: " + str2);
	      System.out.println("\nDistancia de Levenshtein:\n" + ld.getDistancia());
	      System.out.println("Afinidad:\n" + ld.getAfinidad() * 100 + " %");
	      String str3="";
	      String str4="";
	      String str5="";
	      if((ld.getAfinidad() * 100)>50) {
	    	  return "correcto";
	      }else if((ld.getAfinidad() * 100)==50) {
	    	  if(str1.length()>str2.length()) {
	    		  str3=str1.substring(0,str1.length()/2);
	    		  str4=str2;
	    		  str5=str1.substring(str1.length()/2);
	    	  }else {
	    		  str3=str2.substring(0,str2.length()/2);
	    		  str4=str1;
	    		  str5=str1.substring(str2.length()/2);
	    	  }
	    	  System.out.println(str3);
	    		if (str3.equals(str4)) {
	    		    return "correcto";
				} else if (str5.equals(str4)) {
	    		    return "correcto";
				} else {
					return "incorrecto";

				}
	      }else {
	    	  return "incorrecto";
	      }

	}
	
}
