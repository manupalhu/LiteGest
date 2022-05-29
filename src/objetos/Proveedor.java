package objetos;

public class Proveedor {

	private String id;
	private String nombre;
	private String direccion;
	private String cp;
	private String poblacion;
	private String localidad;
	private String pais;
	private String telefono;
	private String correo;
	private String comilla = "\"";

	public Proveedor() {

	}

	public Proveedor(String id, String nombre, String direccion, String cp, String poblacion, String localidad,
			String pais, String telefono, String correo) {

		this.id = comilla + id + comilla;
		this.nombre = comilla + nombre + comilla;
		this.direccion = comilla + direccion + comilla;
		this.cp = comilla + cp + comilla;
		this.poblacion = comilla + poblacion + comilla;
		this.localidad = comilla + localidad + comilla;
		this.pais = comilla + pais + comilla;
		this.telefono = comilla + telefono + comilla;
		this.correo = comilla + correo + comilla;

	}

	@Override
	public String toString() {
		return "replace into proveedor (id_proveedor , nombre , direccion , cp , poblacion , localidad , pais , telefono , correo)  values ("
				+ id + ", " + nombre + "," + direccion + "," + cp + ", " + poblacion + "," + localidad + ", " + pais
				+ "," + telefono + "," + correo + ") ";
	}

}
