package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administrador")
public class Administrador {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "adm_nombre",length = 20)
	private String nombre;
	
	@Column(name = "adm_apellido",length = 20)
	private String apellido;
	
	@Column(name = "adm_contraseña",length = 20)
	private String contraseña;
	
	@Column(name = "adm_usuario",length = 20)
	private String usuario;

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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
