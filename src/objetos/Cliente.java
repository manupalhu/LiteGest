package objetos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {
	

	private String id;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String cp;
	private String poblacion;
	private String localidad;
	private String pais;
	private String telefono;
	private String correo;
	private Conexion cnn=new Conexion();
	private ResultSet rSet;
	
	public Cliente(String id, String nombre, String apellidos, String direccion, String cp, String poblacion,
			String localidad, String pais, String telefono, String correo) {
		
		this.id = "\""+id+"\"";
		this.nombre ="\""+ nombre+"\"";
		this.apellidos ="\""+ apellidos+"\"";
		this.direccion = "\""+direccion+"\"";
		this.cp ="\""+ cp+"\"";
		this.poblacion ="\""+ poblacion+"\"";
		this.localidad = "\""+localidad+"\"";
		this.pais ="\""+ pais+"\"";
		this.telefono = "\""+telefono+"\"";
		this.correo = "\""+correo+"\"";

	}
	
	public Cliente(String id) {
		
		this.id = "\""+id+"\"";
		
	}
	public Cliente() {
		
		
		}
	
	public void alta() {
		
		try {

			cnn.sentencia(
					"replace into cliente (id_cliente, nombre, apellidos, direccion, cp, poblacion, localidad, pais, telefono,correo) values("
							+ id + "," + nombre + "," + apellidos + "," + direccion + "," + cp + "," + poblacion + ","
							+ localidad + "," + pais + "," + telefono + "," + correo + ")");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public void baja() {
		
		try {
			
			cnn.sentencia("delete  from cliente where id_cliente = "+id);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public String toString() {
		
		try {
			
			rSet = cnn.SentenciaRset("select nombre, apellidos, direccion, cp, poblacion  from cliente where id_cliente ="+id);
			if (rSet.isClosed()) {
				
				return "";
				
			}else {
				
				while(rSet.next()) {
					
					nombre = rSet.getString("nombre");
					apellidos =rSet.getString("apellidos");
					direccion = rSet.getString("direccion");
					cp= rSet.getString("cp");
					poblacion=rSet.getString("poblacion");
					
				}
				rSet.close();
				return nombre+" "+apellidos+" ||  "+direccion+"  CP:"+cp+" "+poblacion;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return "";
		
		
	}

}
