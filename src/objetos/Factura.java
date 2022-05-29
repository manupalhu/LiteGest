package objetos;

import java.sql.SQLException;

public class Factura {
	
	String IdCliente;
	String idFactura ;
	String fecha ;
	String efectivo ;
	String tarjeta ;
	private Conexion cnn = new Conexion();
	
	
	public Factura() {
	
	}

	public Factura(String idCliente, String idFactura, String fecha, String efectivo, String tarjeta) {
		IdCliente = idCliente;
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.efectivo = efectivo;
		this.tarjeta = tarjeta;
	}
	public void crearFactura() {
		
		try {
			
			cnn.sentencia(" insert into factura (id_factura,id_cliente,fecha,efectivo,tarjeta) values (" + idFactura
					+ "," + IdCliente + "," + fecha + "," + efectivo + "," + tarjeta + ")");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	public void crearDetalles(	String idProducto,String pvp,String cantidad,String comentario,String iva,String descuento) {
		
		try {
			cnn.sentencia(	"insert into detalle (id_factura, id_producto, pvp, cantidad, comentario, iva, descuento) values ("
							+ idFactura + "," + idProducto + "," + pvp + "," + cantidad + "," + comentario + ","
							+ iva + ", " + descuento + ")");
			
			cnn.sentencia("update producto set  stock  = stock -" + cantidad
					+ " where id_producto=" + idProducto);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
