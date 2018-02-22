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
@Table(name="area")
public class Area {
	
	@Id
	@Column(name="are_id")
	@SequenceGenerator(name = "AREA_ID_GENERATOR", sequenceName="SEQ_ARE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="AREA_ID_GENERATOR")
	private int id;
	
	@Column(name = "are_nombre",length = 40)
	private String nombre;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name="area_are_id")
	private List<Actividad> actividad; 

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

	public List<Actividad> getActividad() {
		return actividad;
	}

	public void setActividad(List<Actividad> actividad) {
		this.actividad = actividad;
	}
	
}
