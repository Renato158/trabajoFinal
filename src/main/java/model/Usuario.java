package model;

public class Usuario {
	
	private int _id;
	private String email;
	private String contraseņa;
	private String nombre;
	private String fecha;
	
	public Usuario(int _id, String email, String password, String nombre, String fecha) {
		this._id = _id;
		this.email = email;
		this.contraseņa = password;
		this.nombre = nombre;
		this.fecha = fecha;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return contraseņa;
	}

	public void setPassword(String password) {
		this.contraseņa = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Usuario [_id=" + _id + ", email=" + email + ", contraseņa=" + contraseņa + ", nombre=" + nombre + ", fecha="
				+ fecha + "]";
	}
	
}
