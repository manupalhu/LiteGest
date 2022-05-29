package objetos;

public class Producto {

	private String id;
	private String nombre;
	private double coste;
	private double pvp;
	private double iva;
	private String comilla = "\"";

	public Producto() {

	}

	public Producto(String id) {

		this.id = comilla + id + comilla;

	}

	public Producto(String id, String nombre, double coste, double pvp, double iva) {

		this.id = comilla + id + comilla;
		this.nombre = comilla + nombre + comilla;
		this.coste = coste;
		this.pvp = pvp;
		this.iva = iva;

	}

	public String Alta() {

		return "insert into producto (id_producto, nombre, coste,pvp,iva) values(" + id + "," + nombre + ", "
				+ coste + ", " + pvp + "," + iva +") on conflict (id_producto) do update set nombre ="+nombre+", coste= "+coste+", pvp = "+pvp+", iva = "+iva;

	}

	public String Baja() {

		return " delete  from producto where id_producto = " + id;

	}

}
