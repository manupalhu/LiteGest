package erp;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import objetos.Cliente;
import objetos.Conexion;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SubVentanaCliente extends JInternalFrame {

	private static final long serialVersionUID = -7070257530877387083L;
	private JTextField tfDni;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfDireccion;
	private JTextField tfCp;
	private JTextField tfLocalidad;
	private JTextField tfPoblacion;
	private JTextField tfPais;
	private JTextField tfMail;
	private JTextField tfTelefono;
	private Conexion cnn=new Conexion();
	private ResultSet rSet;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubVentanaCliente frame = new SubVentanaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SubVentanaCliente() {
		
		setClosable(true);
		setBounds(145, 0, 820, 254);
		this.setTitle("Clientes");
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 12, 800, 44);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("DNI/CIF");
		panel_1.add(lblNewLabel);
		
		tfDni = new JTextField();
		panel_1.add(tfDni);
		tfDni.setColumns(8);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		panel_1.add(lblNewLabel_1);
		
		tfNombre = new JTextField();
		panel_1.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		panel_1.add(lblNewLabel_2);
		
		tfApellidos = new JTextField();
		panel_1.add(tfApellidos);
		tfApellidos.setColumns(25);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(0, 55, 800, 44);
		panel.add(panel_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("Direccion");
		panel_1_1.add(lblNewLabel_3);
		
		tfDireccion = new JTextField();
		panel_1_1.add(tfDireccion);
		tfDireccion.setColumns(25);
		
		JLabel lblNewLabel_4 = new JLabel("CP");
		panel_1_1.add(lblNewLabel_4);
		
		tfCp = new JTextField();
		panel_1_1.add(tfCp);
		tfCp.setColumns(6);
		
		JLabel lblNewLabel_5 = new JLabel("Localidad");
		panel_1_1.add(lblNewLabel_5);
		
		tfLocalidad = new JTextField();
		panel_1_1.add(tfLocalidad);
		tfLocalidad.setColumns(10);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(0, 97, 702, 44);
		panel.add(panel_1_2);
		
		JLabel lblNewLabel_8 = new JLabel("Poblacion");
		panel_1_2.add(lblNewLabel_8);
		
		tfPoblacion = new JTextField();
		panel_1_2.add(tfPoblacion);
		tfPoblacion.setColumns(8);
		
		JLabel lblNewLabel_9 = new JLabel("Pais");
		panel_1_2.add(lblNewLabel_9);
		
		tfPais = new JTextField();
		panel_1_2.add(tfPais);
		tfPais.setColumns(8);
		
		JLabel lblNewLabel_8_1 = new JLabel("eMail");
		panel_1_2.add(lblNewLabel_8_1);
		
		tfMail = new JTextField();
		panel_1_2.add(tfMail);
		tfMail.setColumns(15);
		
		JLabel lblNewLabel_9_1 = new JLabel("Telefono");
		panel_1_2.add(lblNewLabel_9_1);
		
		JPanel panel_1_2_1 = new JPanel();
		panel_1_2.add(panel_1_2_1);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(8);
		panel_1_2_1.add(tfTelefono);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(319, 153, 98, 26);
		panel.add(btnAlta);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.setEnabled(false);
		btnBaja.setBounds(206, 153, 98, 26);
		panel.add(btnBaja);
		
		/**
		 * Cuando se incluya un DNI que exista
		 */
		tfDni.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				 try {
					 
					 String  dni= "\""+  tfDni.getText().toString()+"\"";
					 
					rSet = cnn.SentenciaRset("select * from cliente where id_cliente ="+dni);
					
					if (!rSet.isClosed()) {
						
						while (rSet.next()) {
							
							tfNombre.setText(rSet.getString("nombre"));
							tfApellidos.setText(rSet.getString("apellidos"));
							tfDireccion.setText(rSet.getString("direccion"));
							tfCp.setText(rSet.getString("cp"));
							tfLocalidad.setText(rSet.getString("localidad"));
							tfPoblacion.setText(rSet.getString("poblacion"));
							tfPais.setText(rSet.getString("pais").toString());
							tfTelefono.setText(rSet.getString("telefono"));
							tfMail.setText(rSet.getString("correo"));
							
						}
						
						btnBaja.setEnabled(true);
						
					}else {
						
						btnBaja.setEnabled(false);
					}
					
					rSet.close();
					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
					
			}
		});

		/**
		 * Boton Alta
		 */
		btnAlta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tfNombre.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null,"Nombre obligatorio" );
					
				}else if(tfApellidos.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null,"Apellido  obligatorio" );
					
				}else if(tfDni.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null,"DNI/CIF  obligatorio" );	
					
				}else {
					
					crearCliente();
				}
				

			}
		});
		/**
		 * Boton Baja
		 */
		btnBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(btnBaja.isEnabled()) {
					
					bajaCliente();
					limpiar();
					
				}
				
			}
		});
		
		
		
	}
	
	/*
	 * 
	 * Funciones
	 * 
	 */
	
	/**
	 * Valida los datos y crea el cliente
	 */
	public void crearCliente() {
		
		tfDni.getText().toString();
		tfNombre.getText().toString();
		tfApellidos.getText().toString();
		tfDireccion.getText().toString();
		tfCp.getText().toString();
		tfLocalidad.getText();
		tfPoblacion.getText().toString();
		tfPais.getText().toString();
		tfTelefono.getText().toString();
		tfMail.getText().toString();
		
		Cliente cliente = new Cliente(tfDni.getText().toString(), tfNombre.getText().toString(),
				tfApellidos.getText().toString(), tfDireccion.getText().toString(), tfCp.getText().toString(),
				tfPoblacion.getText().toString(),tfLocalidad.getText(),  tfPais.getText().toString(),
				tfTelefono.getText().toString(), tfMail.getText().toString());
		
		cliente.alta();
		limpiar();
		
	}
	/**
	 * Recoge el cliente y llama a la función baja
	 * 
	 * @see baja()
	 */
	public void bajaCliente() {
		
		Cliente cliente = new Cliente(tfDni.getText().toString());
		cliente.baja();
		
	}
	
	/**
	 * Limpia los input
	 */
	public void limpiar() {
		
		tfDni.setText("");
		tfNombre.setText("");
		tfApellidos.setText("");
		tfDireccion.setText("");
		tfCp.setText("");
		tfLocalidad.setText("");
		tfPoblacion.setText("");
		tfPais.setText("");
		tfTelefono.setText("");
		tfMail.setText("");
		
	}
	
	
}
