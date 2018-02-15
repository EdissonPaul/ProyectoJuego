package Modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="niño")
public class Niño {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "nin_nombre",length = 40)
	private String nombre;
	
	@Column(name = "nin_apellido",length = 40)
	private String apellido;
	
	@Column(name = "nin_usuario",length = 40)
	private String usuario;
	
	@Column(name = "nin_sexo",length = 40)
	private String sexo;
	
	@Column(name = "nin_institucion",length = 40)
	private String institucion;
	
	@OneToMany(mappedBy="nino")
	private List<SesionJuego> sesionJuego;
	
	@OneToMany(mappedBy="niño")
	private List<TerapistaNiño> listaTerapistaNiño;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public List<SesionJuego> getSesionJuego() {
		return sesionJuego;
	}

	public void setSesionJuego(List<SesionJuego> sesionJuego) {
		this.sesionJuego = sesionJuego;
	}

	

	public List<TerapistaNiño> getListaTerapistaNiño() {
		return listaTerapistaNiño;
	}

	public void setListaTerapistaNiño(List<TerapistaNiño> listaTerapistaNiño) {
		this.listaTerapistaNiño = listaTerapistaNiño;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
}
