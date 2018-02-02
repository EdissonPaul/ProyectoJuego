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
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@Column(name = "nin_sexo",length = 40)
	private String sexo;
	
	@Column(name = "nin_institucion",length = 40)
	private String institucion;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name="niño_nin_id")
	private List<SesionJuego> sesionJuego;

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
	
	
}
