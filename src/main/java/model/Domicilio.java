package model;

public class Domicilio {
	
	private int _id;
	private int idUsuario;
	private String direccion;
	private String departamento;
	private String municipio;
	private String codigoPostal;
	
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCiudad() {
		return municipio;
	}
	public void setCiudad(String ciudad) {
		this.municipio = ciudad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	@Override
	public String toString() {
		return "Domicilio [_id=" + _id + ", idUsuario=" + idUsuario + ", direccion=" + direccion + ", departamento="
				+ departamento + ", ciudad=" + municipio + ", codigoPostal=" + codigoPostal + "]";
	}
	
}
