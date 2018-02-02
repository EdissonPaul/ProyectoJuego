package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="puntajes")
public class Puntajes {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "pun_nombre",length = 40)
	private String nombre;
	
	@Column(name = "pun_valor")
	private int valor;
}
