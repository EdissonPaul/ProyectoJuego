package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class TerapistaNiño implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="teu_id")
	@SequenceGenerator(name = "TERUSR_ID_GENERATOR", sequenceName="SEQ_TEU_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TERUSR_ID_GENERATOR")
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="nin_id")
	@JsonIgnore
	private Niño niño;
	
	@ManyToOne
	@JoinColumn(name="ter_id")
	@JsonIgnore
	private Terapista terapista;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	


	public Terapista getTerapista() {
		return terapista;
	}

	public void setTerapista(Terapista terapista) {
		this.terapista = terapista;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Niño getNiño() {
		return niño;
	}

	public void setNiño(Niño niño) {
		this.niño = niño;
	}
	
	

	
}
