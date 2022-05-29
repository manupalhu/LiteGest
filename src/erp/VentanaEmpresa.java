package erp;

import java.awt.EventQueue; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import org.sqlite.Function.Window;

import objetos.Conexion;
import objetos.NumField;

import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


public class VentanaEmpresa {

	private JFrame frame;
	private JTextField tf_nombre;
	private JTextField tf_cif;
	private JTextField tf_direccion;
	private JTextField tf_poblacion;
	private JTextField tf_localidad;
	private NumField tf_cp;
	private NumField tf_telecono;
	private JTextField tf_mail;
	private JTextField tf_pais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEmpresa window = new VentanaEmpresa();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaEmpresa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
			
		frame = new JFrame();
		frame.setBounds(500, 200, 395, 437);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(28, 30, 323, 343);
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		tf_nombre = new JTextField();
		GridBagConstraints gbc_tf_nombre = new GridBagConstraints();
		gbc_tf_nombre.gridwidth = 5;
		gbc_tf_nombre.insets = new Insets(0, 0, 5, 0);
		gbc_tf_nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_nombre.gridx = 2;
		gbc_tf_nombre.gridy = 0;
		panel_1.add(tf_nombre, gbc_tf_nombre);
		tf_nombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CIF/NIF");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		tf_cif = new JTextField();
		GridBagConstraints gbc_tf_cif = new GridBagConstraints();
		gbc_tf_cif.gridwidth = 5;
		gbc_tf_cif.insets = new Insets(0, 0, 5, 0);
		gbc_tf_cif.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_cif.gridx = 2;
		gbc_tf_cif.gridy = 1;
		panel_1.add(tf_cif, gbc_tf_cif);
		tf_cif.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Direccion");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		tf_direccion = new JTextField();
		GridBagConstraints gbc_tf_direccion = new GridBagConstraints();
		gbc_tf_direccion.gridwidth = 5;
		gbc_tf_direccion.insets = new Insets(0, 0, 5, 0);
		gbc_tf_direccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_direccion.gridx = 2;
		gbc_tf_direccion.gridy = 2;
		panel_1.add(tf_direccion, gbc_tf_direccion);
		tf_direccion.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Poblaci\u00F3n");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 3;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		tf_poblacion = new JTextField();
		GridBagConstraints gbc_tf_poblacion = new GridBagConstraints();
		gbc_tf_poblacion.insets = new Insets(0, 0, 5, 0);
		gbc_tf_poblacion.gridwidth = 5;
		gbc_tf_poblacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_poblacion.gridx = 2;
		gbc_tf_poblacion.gridy = 3;
		panel_1.add(tf_poblacion, gbc_tf_poblacion);
		tf_poblacion.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Localidad");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 4;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		tf_localidad = new JTextField();
		GridBagConstraints gbc_tf_localidad = new GridBagConstraints();
		gbc_tf_localidad.gridwidth = 5;
		gbc_tf_localidad.insets = new Insets(0, 0, 5, 0);
		gbc_tf_localidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_localidad.gridx = 2;
		gbc_tf_localidad.gridy = 4;
		panel_1.add(tf_localidad, gbc_tf_localidad);
		tf_localidad.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Codido Postal");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 5;
		panel_1.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		tf_cp = new NumField();
		GridBagConstraints gbc_tf_cp = new GridBagConstraints();
		gbc_tf_cp.gridwidth = 5;
		gbc_tf_cp.insets = new Insets(0, 0, 5, 0);
		gbc_tf_cp.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_cp.gridx = 2;
		gbc_tf_cp.gridy = 5;
		panel_1.add(tf_cp, gbc_tf_cp);
		tf_cp.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Pais");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 1;
		gbc_lblNewLabel_11.gridy = 6;
		panel_1.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		tf_pais = new JTextField();
		GridBagConstraints gbc_tf_pais = new GridBagConstraints();
		gbc_tf_pais.gridwidth = 5;
		gbc_tf_pais.insets = new Insets(0, 0, 5, 0);
		gbc_tf_pais.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_pais.gridx = 2;
		gbc_tf_pais.gridy = 6;
		panel_1.add(tf_pais, gbc_tf_pais);
		tf_pais.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Tel\u00E9fono");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 7;
		panel_1.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		tf_telecono = new NumField();
		GridBagConstraints gbc_tf_telecono = new GridBagConstraints();
		gbc_tf_telecono.insets = new Insets(0, 0, 5, 0);
		gbc_tf_telecono.gridwidth = 5;
		gbc_tf_telecono.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_telecono.gridx = 2;
		gbc_tf_telecono.gridy = 7;
		panel_1.add(tf_telecono, gbc_tf_telecono);
		tf_telecono.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Mail");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 8;
		panel_1.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		tf_mail = new JTextField();
		GridBagConstraints gbc_tf_mail = new GridBagConstraints();
		gbc_tf_mail.insets = new Insets(0, 0, 5, 0);
		gbc_tf_mail.gridwidth = 5;
		gbc_tf_mail.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_mail.gridx = 2;
		gbc_tf_mail.gridy = 8;
		panel_1.add(tf_mail, gbc_tf_mail);
		tf_mail.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 10;
		panel_1.add(btnCancelar, gbc_btnCancelar);
		
		JButton btnCrear = new JButton("Crear");
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.gridwidth = 3;
		gbc_btnCrear.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrear.gridx = 3;
		gbc_btnCrear.gridy = 10;
		panel_1.add(btnCrear, gbc_btnCrear);
		
		//Implementado bot�n cancelar
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frame.dispose();
			}
			
		});
		
		//Implementado bot�n Crear
		
		btnCrear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Comprobaci�n de que todos los campos est�n rellenos
				if(tf_nombre.getText().isEmpty() || tf_cif.getText().isEmpty() || tf_direccion.getText().isEmpty() || tf_poblacion.getText().isEmpty() 
						||  tf_localidad.getText().isEmpty() || tf_cp.getText().isEmpty() || tf_telecono.getText().isEmpty() 
						|| tf_mail.getText().isEmpty()  ){
					
					//Mensaje error si alguno est� vaciuo
					JOptionPane.showMessageDialog(null, "Deben rellenarse todos los campos");
					
				}else{
					
					// si todos los campos están rellenos, se pocede al insert
					
					String tablaEmpresaInsert = "delete  from empresa; insert into empresa values ('"+ tf_cif.getText() + "','" + tf_nombre.getText() + "','" + tf_direccion.getText()
					+ "','" + tf_poblacion.getText() + "','"+ tf_localidad.getText() + "'," + tf_cp.getText() + ",'" + tf_pais.getText() 
					+ "','" + tf_mail.getText() + "'," + tf_telecono.getText()+")";    
			        
					
					Conexion cn = new Conexion();
					
					try {
						
						cn.sentencia(tablaEmpresaInsert);
						JOptionPane.showMessageDialog(null, "Ejecutado con �xito");
						VentanaPrincipal vp = new VentanaPrincipal();
			        	vp.setVisible(true);
			        	
			        	
			        	//Se oculta el frame
			        	frame.setVisible(false);
			        	
			      
					} catch (SQLException ex) {
						
						JOptionPane.showMessageDialog(null, ex ,"ERROR",JOptionPane.ERROR_MESSAGE);
					}
					
					
					
				};
				
				
			}
		});
		
		

	}
	
	
}
