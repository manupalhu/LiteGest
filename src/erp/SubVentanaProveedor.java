package erp;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import objetos.Conexion;
import objetos.Proveedor;

public class SubVentanaProveedor extends JInternalFrame {

	private static final long serialVersionUID = 1356262832619087321L;
	private JTextField tfCif;
	private JTextField tfNombre;
	private JTextField tfDireccion;
	private JTextField tfCp;
	private JTextField tfPoblacion;
	private JTextField tfLocalidad;
	private JTextField tfPais;
	private JTextField tfTelefono;
	private JTextField tfMail;
	private Conexion cnn = new Conexion();
	private ResultSet rSet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubVentanaProveedor frame = new SubVentanaProveedor();
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
	public SubVentanaProveedor() {
		getContentPane().setFont(new Font("TPV", Font.PLAIN, 17));
		setClosable(true);
		setBounds(145, 0, 721, 254);
		getContentPane().setLayout(null);
		this.setTitle("Proveedor");

		JPanel panel = new JPanel();
		panel.setBounds(10, 22, 689, 179);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("CIF");
		lblNewLabel.setBounds(0, 10, 58, 17);
		panel.add(lblNewLabel);

		tfCif = new JTextField();
		tfCif.setBounds(68, 8, 96, 20);
		panel.add(tfCif);
		tfCif.setColumns(10);

		JButton btnbuscar = new JButton("Buscar");
		btnbuscar.setBounds(174, 7, 89, 23);
		panel.add(btnbuscar);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(273, 156, 89, 23);
		panel.add(btnGuardar);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(0, 40, 58, 19);
		panel.add(lblNewLabel_1);

		tfNombre = new JTextField();
		tfNombre.setBounds(68, 39, 195, 20);
		panel.add(tfNombre);
		tfNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Direcci\u00F3n");
		lblNewLabel_2.setBounds(273, 42, 67, 14);
		panel.add(lblNewLabel_2);

		tfDireccion = new JTextField();
		tfDireccion.setBounds(339, 41, 286, 20);
		panel.add(tfDireccion);
		tfDireccion.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("CP");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(331, 73, 36, 14);
		panel.add(lblNewLabel_3);

		tfCp = new JTextField();
		tfCp.setBounds(361, 70, 96, 20);
		panel.add(tfCp);
		tfCp.setColumns(10);

		tfPoblacion = new JTextField();
		tfPoblacion.setBounds(68, 70, 96, 20);
		panel.add(tfPoblacion);
		tfPoblacion.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Poblaci\u00F3n");
		lblNewLabel_4.setBounds(0, 72, 58, 17);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Localidad");
		lblNewLabel_5.setBounds(164, 71, 58, 16);
		panel.add(lblNewLabel_5);

		tfLocalidad = new JTextField();
		tfLocalidad.setBounds(225, 70, 96, 20);
		panel.add(tfLocalidad);
		tfLocalidad.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Pa\u00EDs");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(467, 73, 36, 14);
		panel.add(lblNewLabel_6);

		tfPais = new JTextField();
		tfPais.setBounds(513, 70, 112, 20);
		panel.add(tfPais);
		tfPais.setColumns(10);

		tfTelefono = new JTextField();
		tfTelefono.setBounds(68, 101, 96, 20);
		panel.add(tfTelefono);
		tfTelefono.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Tel\u00E9fono");
		lblNewLabel_7.setBounds(0, 101, 58, 17);
		panel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("e-mail");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(164, 102, 58, 16);
		panel.add(lblNewLabel_8);

		tfMail = new JTextField();
		tfMail.setBounds(225, 101, 137, 20);
		panel.add(tfMail);
		tfMail.setColumns(10);

		// Implementado botón buscar
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String cif = tfCif.getText().toString();

				try {

					rSet = cnn.SentenciaRset("select * from proveedor where id_proveedor = \"" + cif + "\"  ");

					if (rSet.isClosed()) {

						JOptionPane.showMessageDialog(null, "No se ha encontrado ningún proveedor", "ERROR",
								JOptionPane.ERROR_MESSAGE);

						tfNombre.setText("");
						tfDireccion.setText("");
						tfCp.setText("");
						tfPoblacion.setText("");
						tfLocalidad.setText("");
						tfPais.setText("");
						tfTelefono.setText("");
						tfMail.setText("");

						rSet.close();

					} else {

						while (rSet.next()) {

							tfNombre.setText(rSet.getString("nombre"));
							tfDireccion.setText(rSet.getString("direccion"));
							tfCp.setText(rSet.getString("cp"));
							tfPoblacion.setText(rSet.getString("poblacion"));
							tfLocalidad.setText(rSet.getString("localidad"));
							tfPais.setText(rSet.getString("pais"));
							tfTelefono.setText(rSet.getString("telefono"));
							tfMail.setText(rSet.getString("correo"));

							
						}
						rSet.close();
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex, "ERROR busqueda", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		// implementado botón guardar

		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


					try {
						Proveedor proveedor = new Proveedor(tfCif.getText().toString(), tfNombre.getText().toString(),
								tfDireccion.getText().toString(), tfCp.getText().toString(),
								tfPoblacion.getText().toString(), tfLocalidad.getText().toString(),
								tfPais.getText().toString(), tfTelefono.getText().toString(),
								tfMail.getText().toString());

						cnn.sentencia(proveedor.toString());

						JOptionPane.showMessageDialog(null, "Realizado con éxito");

						tfNombre.setText("");
						tfDireccion.setText("");
						tfCp.setText("");
						tfPoblacion.setText("");
						tfLocalidad.setText("");
						tfPais.setText("");
						tfTelefono.setText("");
						tfMail.setText("");

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
					}

			}
		});

	}
}
