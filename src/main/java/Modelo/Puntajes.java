package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="puntajes")
public class Puntajes {

	@Id
	@Column(name="pun_id")
	@SequenceGenerator(name = "PUNTAJES_ID_GENERATOR", sequenceName="SEQ_PUN_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PUNTAJES_ID_GENERATOR")
	private int id;
	
	@Column(name = "pun_nombre",length = 40)
	private String nombre;
	
	@Column(name = "pun_valor")
	private int valor;

	@Column(name = "pun_fallo")
	private int valorFallos;
	
	@Column(name = "pun_esperado")
	private int valorEsperado;
	
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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getValorFallos() {
		return valorFallos;
	}

	public void setValorFallos(int valorFallos) {
		this.valorFallos = valorFallos;
	}

	public int getValorEsperado() {
		return valorEsperado;
	}

	public void setValorEsperado(int valorEsperado) {
		this.valorEsperado = valorEsperado;
	}
	
	
}
