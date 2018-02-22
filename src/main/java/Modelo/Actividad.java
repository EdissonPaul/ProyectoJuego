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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="actividad")
public class Actividad {

	@Id
	@Column(name="act_id")
	@SequenceGenerator(name = "ACTIVIDAD_ID_GENERATOR", sequenceName="SEQ_ACT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ACTIVIDAD_ID_GENERATOR")
	private int id;
	
	@Column(name = "act_nombre",length = 40)
	private String nombre;
	
	@Column(name = "act_idepunt")
	private int idepunt;
	
	@OneToMany(mappedBy="actividad")
	//@JoinColumn(name="actividad_act_id")
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

	public int getIdepunt() {
		return idepunt;
	}

	public void setIdepunt(int idepunt) {
		this.idepunt = idepunt;
	}

	public List<SesionJuego> getSesionJuego() {
		return sesionJuego;
	}

	public void setSesionJuego(List<SesionJuego> sesionJuego) {
		this.sesionJuego = sesionJuego;
	}
	
	
}
