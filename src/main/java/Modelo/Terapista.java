package Modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "ter_cedula"))
public class Terapista implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "TERAPISTA_ID_GENERATOR",sequenceName = "SEQ_TER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="TERAPISTA_ID_GENERATOR")
	@Column(name="ter_id")
	private int id;
	
	@Column(name = "ter_nombre",length = 40)
	@NotNull
	private String nombre;
	
	@Column(name = "ter_apellido",length = 40)
	@NotNull
	private String apellido;
	
	@Column(name = "ter_cedula",length = 10)
	@NotNull
	private String cedula;
	
	@Column(name="log_username",length=15)
	@NotNull
	private String username;
	
	@Size(min=4, max=128)
	@Column(name="log_password")
	@NotNull
	private String password;
	
	@OneToMany(mappedBy="terapista")
	private List<TerapistaNiño> listaTerapistasNiño;
	
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public List<TerapistaNiño> getListaTerapistasNiño() {
		return listaTerapistasNiño;
	}

	public void setListaTerapistasNiño(List<TerapistaNiño> listaTerapistasNiño) {
		this.listaTerapistasNiño = listaTerapistasNiño;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
