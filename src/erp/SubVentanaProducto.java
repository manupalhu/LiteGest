package erp;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import objetos.Conexion;
import objetos.NumField;
import objetos.Producto;

class SubVentanaProducto extends JInternalFrame {
	

	private static final long serialVersionUID = 780417001290109450L;
	private JTextField tfCodigo;
	private JTextField tfDescripcion;
	private NumField tfCoste;
	private NumField tfPvp;
	private NumField tfIva;
	private JButton btnBaja;
	private JButton btnAlta;
	private Conexion cnn = new Conexion();
	private ResultSet rSet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubVentanaProducto frame = new SubVentanaProducto();
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
	public SubVentanaProducto() {

		getContentPane().setFont(new Font("Producto", Font.PLAIN, 17));
		setClosable(true);
		setBounds(145, 0, 677, 254);
		getContentPane().setLayout(null);
		this.setTitle("Productos");

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 643, 197);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("C\u00F3digo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(17, 12, 67, 16);
		panel.add(lblNewLabel);

		tfCodigo = new JTextField();
		tfCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		tfCodigo.setBounds(90, 10, 104, 20);
		panel.add(tfCodigo);
		tfCodigo.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(206, 10, 89, 20);
		panel.add(btnBuscar);

		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(0, 48, 84, 16);
		panel.add(lblNewLabel_1);

		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(90, 46, 523, 20);
		panel.add(tfDescripcion);
		tfDescripcion.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Coste");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(34, 85, 50, 16);
		panel.add(lblNewLabel_2);

		tfCoste = new NumField();
		tfCoste.setHorizontalAlignment(SwingConstants.RIGHT);
		tfCoste.setBounds(90, 83, 67, 20);
		panel.add(tfCoste);
		tfCoste.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("PVP");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(178, 85, 36, 16);
		panel.add(lblNewLabel_3);

		tfPvp = new NumField();
		tfPvp.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPvp.setBounds(220, 83, 75, 20);
		panel.add(tfPvp);
		tfPvp.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("IVA");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(307, 85, 36, 16);
		panel.add(lblNewLabel_4);

		tfIva = new NumField();
		tfIva.setBounds(349, 83, 50, 20);
		panel.add(tfIva);
		tfIva.setColumns(10);

		btnAlta = new JButton("Alta");
		btnAlta.setEnabled(true);
		btnAlta.setBounds(331, 157, 98, 26);
		panel.add(btnAlta);

		btnBaja = new JButton("Baja");
		btnBaja.setEnabled(true);
		btnBaja.setBounds(197, 157, 98, 26);
		panel.add(btnBaja);

		// implementar botones

		/*
		 * Implementado boton buscar
		 */
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String codigo = tfCodigo.getText().toString();

				try {

					rSet = cnn.SentenciaRset("select * from producto where id_producto = \"" + codigo + "\"  ");

					if (rSet.isClosed()) {

						tfCoste.setText("");
						tfDescripcion.setText("");
						tfIva.setText("");
						tfPvp.setText("");
						

						JOptionPane.showMessageDialog(null, "No se ha encontrado el producto");
						
						

					} else {

						while (rSet.next()) {

							tfCoste.setText(rSet.getString("coste"));
							tfDescripcion.setText(rSet.getString("nombre"));
							tfIva.setText(rSet.getString("iva"));
							tfPvp.setText(rSet.getString("pvp"));
							

						}

					}
					rSet.close();

				} catch (SQLException ex) {

					JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		/*
		 * Implementado boton Alta
		 */

		btnAlta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (comprobarCampos()) {

					Producto producto = new Producto(tfCodigo.getText().toString(), tfDescripcion.getText().toString(),
							Double.parseDouble(tfCoste.getText().toString()),
							Double.parseDouble(tfPvp.getText().toString()),
							Double.parseDouble(tfIva.getText().toString()));
					
					
					try {
						
						cnn.sentencia(producto.Alta());
						
						tfCoste.setText("");
						tfDescripcion.setText("");
						tfIva.setText("");
						tfPvp.setText("");
						
						JOptionPane.showMessageDialog(null, "Ejecutado con exito");
	
					} catch (Exception ex) {
						
						JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
					}

				}else {
					
					JOptionPane.showMessageDialog(null, "Compruebe los campos");
					
				}

			}
		});
		
		
		/*
		 *Implementado boton Baja 
		 */
		
		btnBaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (comprobarCampos()) {
					
					Producto producto = new Producto(tfCodigo.getText().toString());
					try {
						
						cnn.sentencia(producto.Baja());
						
						tfCoste.setText("");
						tfDescripcion.setText("");
						tfIva.setText("");
						tfPvp.setText("");
						
						JOptionPane.showMessageDialog(null, "Ejecutado con �xito");
						
						
					} catch (Exception ex) {
						
						JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
						
					}
					

				}else {
					
					
					JOptionPane.showMessageDialog(null, "Compruebe los campos");
					
				}
				
			}
		});

	}

	private boolean comprobarCampos() {

		if (tfCodigo.getText().isEmpty() || tfDescripcion.getText().isEmpty() || tfPvp.getText().isEmpty()
				|| tfCoste.getText().isEmpty() || tfIva.getText().isEmpty()) {

			return false;

		} else {

			return true;
		}

	}

}
