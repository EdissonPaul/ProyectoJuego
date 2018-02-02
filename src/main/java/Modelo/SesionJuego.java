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
@Table(name="sesionJuego")
public class SesionJuego {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "ses_fecha",length = 40)
	private String fecha;
	
	@OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinColumn(name="sesion_ses_id")
	private List<Puntajes> puntajes;
}
