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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sesionJuego")
public class SesionJuego {

	@Id
	@Column(name="ses_id")
	@SequenceGenerator(name = "SESION_ID_GENERATOR", sequenceName="SEQ_SES_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SESION_ID_GENERATOR")
	private int id;
	
	@Column(name = "ses_fecha",length = 40)
	private String fecha;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name="sesion_ses_id")
	private List<Puntajes> puntajes;
	
	
	@ManyToOne
	@JoinColumn(name="niño_nin_id")
	@JsonIgnore
	private Niño nino;
	
	@ManyToOne
	@JoinColumn(name="actividad_act_id")
	@JsonIgnore
	private Actividad actividad;


	public Actividad getActividad() {
		return actividad;
	}


	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public List<Puntajes> getPuntajes() {
		return puntajes;
	}


	public void setPuntajes(List<Puntajes> puntajes) {
		this.puntajes = puntajes;
	}


	public Niño getNino() {
		return nino;
	}


	public void setNino(Niño nino) {
		this.nino = nino;
	}
	
	
	
}
