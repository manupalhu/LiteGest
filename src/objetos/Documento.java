package objetos;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Documento {

	private String idFactura;
	private String idCliente;
	private String fecha;
	private String efectivo;
	private String tarjeta;

	private String idProducto;
	private String descripcionProducto;
	private String cantidad;
	private String pvp;
	private String iva;
	private String descuento;
	private String comentario;

	String idEmpresa;
	private String nombreEmpres;
	private String direccionEmpresa;
	private String cpEmpresa;
	private String paisEmpresa;
	private String tlfEmpresa;
	private String correoEmpresa;

	private String nombreCliente;
	private String direccionCliente;
	private String cpCliente;
	private String paisCliente;

	private Conexion cnn = new Conexion();
	private ResultSet rSet;
	private ResultSet rSet2;

	public Documento() {

	}

	public Documento(String idFactura) throws IOException {

		this.idFactura = idFactura;

		

	}
	public void imprimir() throws IOException {
		try {

			File file = new File("documento.txt");
			file.createNewFile();

			rSet = cnn.SentenciaRset("select * from factura where id_factura = " + "\"" + idFactura + "\"");
			while (rSet.next()) {

				idCliente = rSet.getString("id_cliente");
				fecha = rSet.getString("fecha");
				efectivo = rSet.getString("efectivo");
				tarjeta = rSet.getString("tarjeta");

			}
			rSet.close();

			rSet = cnn.SentenciaRset("select * from empresa");
			while (rSet.next()) {

				idEmpresa = rSet.getString("id_empresa");
				nombreEmpres = rSet.getString("nombre");
				direccionEmpresa = rSet.getString("direccion");
				cpEmpresa = rSet.getString("cp") + "-" + rSet.getString("localidad") + "(" + rSet.getString("poblacion")
						+ ")";
				paisEmpresa = rSet.getString("pais");
				tlfEmpresa = rSet.getString("telefono");
				correoEmpresa = rSet.getString("mail");

			}
			rSet.close();

			rSet = cnn.SentenciaRset("select * from cliente where id_cliente = " + "\"" + idCliente + "\"");

			while (rSet.next()) {

				nombreCliente = rSet.getString("nombre") + " " + rSet.getString("apellidos");
				direccionCliente = rSet.getString("direccion");
				cpCliente = rSet.getString("cp") + "-" + rSet.getString("localidad") + "(" + rSet.getString("poblacion")
						+ ")";
				paisCliente = rSet.getString("pais");

			}
			rSet.close();

			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fileWriter);

			// Datos empresa
			bWriter.write(nombreEmpres);
			bWriter.newLine();
			bWriter.write("CIF: " + idEmpresa);
			bWriter.newLine();
			bWriter.write(direccionEmpresa);
			bWriter.newLine();
			bWriter.write(cpEmpresa);
			bWriter.newLine();
			bWriter.write(paisEmpresa);
			bWriter.newLine();
			bWriter.write(tlfEmpresa + "-" + correoEmpresa);
			bWriter.newLine();
			bWriter.write("---------------------------------");
			bWriter.newLine();

			if (idCliente.equals("0")) {
				// Cliente TPV
				bWriter.write("Cliente TPV");
				bWriter.newLine();
				bWriter.write("------------------------------------");
				bWriter.newLine();

			} else {
				// Cliente
				bWriter.write("CLIENTE:");
				bWriter.newLine();
				bWriter.write(nombreCliente);
				bWriter.newLine();
				bWriter.write("CIF/DNI: " + idCliente);
				bWriter.newLine();
				bWriter.write(direccionCliente);
				bWriter.newLine();
				bWriter.write(cpCliente);
				bWriter.newLine();
				bWriter.write(paisCliente);
				bWriter.newLine();
				bWriter.write("------------------------------------");
				bWriter.newLine();

			}

			bWriter.write("Documento:" + idFactura);
			bWriter.newLine();
			bWriter.write("Fecha:" + fecha);
			bWriter.newLine();
			bWriter.write("------------------------------------");
			bWriter.newLine();
			bWriter.write("------------------------------------");
			bWriter.newLine();
			
			
			rSet = cnn.SentenciaRset("select * from detalle where id_factura = " + "\"" + idFactura + "\"");
			while (rSet.next()) {

				idProducto = rSet.getString("id_producto");
				cantidad = rSet.getString("cantidad");
				pvp = rSet.getString("pvp");
				iva = rSet.getString("iva");
				descuento = rSet.getString("descuento");
				comentario = rSet.getString("comentario");

				rSet2=cnn.SentenciaRset("select nombre from producto where id_producto = "+"\""+idProducto+"\"");
				while(rSet2.next()) {
					
					descripcionProducto = rSet2.getString("nombre");
				}
				
				
				bWriter.write("cod:" + idProducto);
				bWriter.newLine();
				bWriter.write(descripcionProducto);
				bWriter.newLine();
				bWriter.write("ctd:"+cantidad+" PVP:"+pvp+"€ dto:"+descuento+"% IVA:"+iva+"%");
				bWriter.newLine();
				bWriter.write(comentario);
				bWriter.newLine();
				bWriter.write("-----------------");
				bWriter.newLine();
				
			}
			bWriter.write("------------------------------------");
			bWriter.newLine();
			
			float total = Float.parseFloat(efectivo)+Float.parseFloat(tarjeta);

			bWriter.write("TOTAL:"+total+"€");
			bWriter.newLine();
			bWriter.write("PAGADO EF:"+efectivo+"€");
			bWriter.newLine();
			bWriter.write("PAGADO TJ:"+tarjeta+"€");
			bWriter.newLine();

			bWriter.close();
			fileWriter.close();
			
	
			File fileToPrint = new File("documento.txt");
			Desktop.getDesktop().print(fileToPrint);

		

		} catch (SQLException e) {

			e.printStackTrace();
		} 
	}

}
