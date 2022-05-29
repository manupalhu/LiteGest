package erp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class Main {
	
	public static void main(String[] args) {
		

		try {
			
            Connection connection = DriverManager.getConnection("jdbc:sqlite:uno.db");    
	        Statement statement = connection.createStatement();
	        ResultSet rSet = statement.executeQuery("select * from empresa");
	        String id_empresa = null;
	        
	        while (rSet.next()) {
	        	id_empresa  = rSet.getString("id_empresa");
	        }
	       
	        if (id_empresa != null) {
	        	
	        	/**
	        	 * Si existe empresa, lanza la ventana principal
	        	 */
	        	
	        	VentanaPrincipal vp = new VentanaPrincipal();
	        	vp.setVisible(true);
				
	        	
	        }else {
	        	
	        	/**
	        	 * Si no existe empresa lanza  la ventana empresa
	        	 */
	        	
	        	JOptionPane.showMessageDialog(null, "Primero debe introducir datos de la empresa");
	        	
	        	VentanaEmpresa.main(null);
	        	
	        }
			
		}catch (Exception ex) {
			
			ex.printStackTrace();
		}

	}

}
