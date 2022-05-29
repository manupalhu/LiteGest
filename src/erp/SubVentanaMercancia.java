package erp;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import objetos.Conexion;
import objetos.NumField;

import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JPopupMenu;

public class SubVentanaMercancia extends JInternalFrame {

	private static final long serialVersionUID = 4151735017372114771L;
	private JTextField tfCoste;
	private JTextField tfPvp;
	private JTextField tfIva;
	private JTextField tfCodigoProducto;
	private NumField tfCantidad;
	private JTable tabla;
	private JTextField tfAlbaran;
	private JComboBox<?> cbProveedor;
	private Conexion cnn = new Conexion();
	private ResultSet rSet;
	private JTextField tfFecha;
	// Modelo de la tabla
	private DefaultTableModel model = (new DefaultTableModel(null,
			new String[] { "C\u00F3digo", "Descripci\u00F3n", "coste", "PVP", "IVA", "Cantidad" }));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubVentanaMercancia frame = new SubVentanaMercancia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public SubVentanaMercancia() throws SQLException {
		getContentPane().setEnabled(false);
		getContentPane().setBounds(new Rectangle(0, 0, 101, 100));
		setClosable(true);
		setBounds(145, 0, 714, 679);
		getContentPane().setLayout(null);

		this.setTitle("Entrada de Mercancia");

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 670, 204);
		getContentPane().add(panel);
		panel.setLayout(null);

		cbProveedor = new JComboBox();
		cbProveedor.setBounds(90, 9, 203, 18);
		panel.add(cbProveedor);

		JLabel lblNewLabel_1 = new JLabel("Descripci\u00F3n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(20, 129, 84, 16);
		panel.add(lblNewLabel_1);

		JTextField tfDescripcionProducto = new JTextField();
		tfDescripcionProducto.setFocusable(false);
		tfDescripcionProducto.setEditable(false);
		tfDescripcionProducto.setColumns(10);
		tfDescripcionProducto.setBounds(109, 127, 523, 20);
		panel.add(tfDescripcionProducto);

		JLabel lblNewLabel_2 = new JLabel("Coste");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(54, 166, 50, 16);
		panel.add(lblNewLabel_2);

		tfCoste = new JTextField();
		tfCoste.setFocusable(false);
		tfCoste.setEditable(false);
		tfCoste.setHorizontalAlignment(SwingConstants.RIGHT);
		tfCoste.setColumns(10);
		tfCoste.setBounds(109, 164, 67, 20);
		panel.add(tfCoste);

		tfPvp = new JTextField();
		tfPvp.setFocusable(false);
		tfPvp.setEditable(false);
		tfPvp.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPvp.setColumns(10);
		tfPvp.setBounds(225, 164, 75, 20);
		panel.add(tfPvp);

		tfIva = new JTextField();
		tfIva.setFocusable(false);
		tfIva.setEditable(false);
		tfIva.setHorizontalAlignment(SwingConstants.RIGHT);
		tfIva.setColumns(10);
		tfIva.setBounds(349, 164, 50, 20);
		panel.add(tfIva);

		JLabel lblNewLabel_3 = new JLabel("C\u00F3digo");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(32, 93, 67, 16);
		panel.add(lblNewLabel_3);

		tfCodigoProducto = new JTextField();
		tfCodigoProducto.setHorizontalAlignment(SwingConstants.RIGHT);
		tfCodigoProducto.setColumns(10);
		tfCodigoProducto.setBounds(109, 91, 104, 20);
		panel.add(tfCodigoProducto);

		JLabel lblNewLabel_3_1 = new JLabel("PVP");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3_1.setBounds(183, 166, 36, 16);
		panel.add(lblNewLabel_3_1);

		JLabel lblNewLabel_4 = new JLabel("IVA");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(308, 166, 36, 16);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Producto");
		lblNewLabel_5.setBounds(10, 68, 75, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel = new JLabel("Proveedor");
		lblNewLabel.setBounds(10, 11, 75, 14);
		panel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 179, 2);
		panel.add(separator);

		JLabel lblNewLabel_6 = new JLabel("Cantidad");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(397, 166, 67, 16);
		panel.add(lblNewLabel_6);

		tfCantidad = new NumField();
		tfCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		tfCantidad.setBounds(467, 164, 44, 20);
		panel.add(tfCantidad);
		tfCantidad.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Albar\u00E1n");
		lblNewLabel_7.setBounds(10, 40, 73, 16);
		panel.add(lblNewLabel_7);

		tfAlbaran = new JTextField();
		tfAlbaran.setBounds(90, 39, 149, 20);
		panel.add(tfAlbaran);
		tfAlbaran.setColumns(10);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(525, 165, 94, 18);
		panel.add(btnAgregar);

		JLabel lblNewLabel_8 = new JLabel("Fecha");
		lblNewLabel_8.setBounds(249, 41, 44, 16);
		panel.add(lblNewLabel_8);

		tfFecha = new JTextField();
		tfFecha.setFocusable(false);
		tfFecha.setEditable(false);
		tfFecha.setBounds(295, 38, 104, 20);
		panel.add(tfFecha);
		tfFecha.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 598, 670, 48);
		getContentPane().add(panel_1);

		JButton btnGuardar = new JButton("Guardar");
		panel_1.add(btnGuardar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 227, 672, 359);
		getContentPane().add(scrollPane);

		/*
		 * Configuracion de la tabla
		 */

		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		tabla.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabla.setModel(model);
		tabla.getColumnModel().getColumn(0).setMaxWidth(90);
		tabla.getColumnModel().getColumn(1).setMaxWidth(330);
		tabla.getColumnModel().getColumn(2).setMaxWidth(55);
		tabla.getColumnModel().getColumn(3).setMaxWidth(55);
		tabla.getColumnModel().getColumn(4).setMaxWidth(50);
		tabla.getColumnModel().getColumn(5).setMaxWidth(60);

		/*
		 * Menu contextual para borrar linea
		 */

		JPopupMenu popupMenu = new JPopupMenu();
		JButton btnEliminarLinea = new JButton("Eliminar linea");
		popupMenu.add(btnEliminarLinea);
		tabla.setComponentPopupMenu(popupMenu);

		/*
		 * Recoge y pone la fecha de hoy.
		 */

		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		tfFecha.setText(formateador.format(ahora));

		/*
		 * Carga la lista de proveedores en el comboBox.
		 */

		cargarProveedores();

		// Vuelve a cargar al ahcer click por si hemos incluido algun proveedor.
		cbProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					cargarProveedores();

				} catch (SQLException ex) {

					JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		/*
		 * Leer codigo cuando el textfield pierda el foco
		 */

		tfCodigoProducto.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {

				try {

					rSet = cnn.SentenciaRset(
							"select * from producto where id_producto =\" " + tfCodigoProducto.getText() + "\"");

					if (rSet.isClosed()) {

						tfDescripcionProducto.setText("");
						tfCoste.setText("");
						tfPvp.setText("");
						tfIva.setText("");
						tfCantidad.setText("");

					} else {

						while (rSet.next()) {

							tfDescripcionProducto.setText(rSet.getString("nombre"));
							tfCoste.setText(rSet.getString("coste"));
							tfPvp.setText(rSet.getString("pvp"));
							tfIva.setText(rSet.getString("iva"));
							tfCantidad.setText("1");

						}
						rSet.close();
					}
					

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {

				tfCodigoProducto.selectAll();

			}
		});

		/*
		 * Implementado boton agregar
		 */

		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (tfDescripcionProducto.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Se necesita un c�digo de producto v�lido");

				} else {

					model.addRow(new Object[] { tfCodigoProducto.getText(), tfDescripcionProducto.getText(),
							tfCoste.getText(), tfPvp.getText(), tfIva.getText(), tfCantidad.getText() });

				}

			}
		});

		/*
		 * Implementado boton eliminarLinea
		 */
		btnEliminarLinea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					model.removeRow(tabla.getSelectedRow());

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Ninguna linea selecionada");
				}

				popupMenu.setVisible(false);
			}
		});

		/*
		 * Implementado boton guardar
		 */

		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (cbProveedor.getSelectedIndex() != 0 && !tfAlbaran.getText().isEmpty() && tabla.getRowCount() != 0) {
					
					
					String proveedorString;
					String albaranString = tfAlbaran.getText().toString();
					String pvpString = tfPvp.getText().toString();
					String fechaString = tfFecha.getText().toString();
					int filas = tabla.getRowCount();
					float total = 0.00F;

					try {
						
						
						// entramos cada cantidad de producto en el almacen
						for (int i = 0; i < filas; i++) {

							
							String codString = model.getValueAt(i, 0).toString();
							String cantidadString = model.getValueAt(i, 5).toString();
							cnn.sentencia("update producto set  stock  = stock +" + cantidadString
									+ " where id_producto=" + codString);

						}
						
						// creamos los apuntes en entrada_mercancia

						rSet = cnn.SentenciaRset("select id_proveedor from proveedor where nombre ="
								+ cadenaSql(cbProveedor.getSelectedItem().toString()));
						proveedorString = rSet.getString("id_proveedor");
						rSet.close();

						
						for (int i = 0; i < filas; i++) {

							String codString = model.getValueAt(i, 0).toString();
							String cantidadString = model.getValueAt(i, 5).toString();

							cnn.sentencia(
									"insert into entrada_mercancia (id_producto, id_proveedor, cantidad, albaran, fecha, pvp) values ("
											+ cadenaSql(codString) + "," + cadenaSql(proveedorString) + ","
											+ cantidadString + "," + cadenaSql(albaranString) + ","
											+ cadenaSql(fechaString) + "," + pvpString + ")");

						}
						
						for (int i = 0; i < filas; i++) {

							float coste = Float.parseFloat(model.getValueAt(i, 2).toString());
							int cantidad = Integer.parseInt(model.getValueAt(i, 5).toString());

							total = total + (coste * cantidad);

						}
						BigDecimal bdTotal = new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);

						cnn.sentencia("insert into finanzas (tipo, origen, fecha, montante) values(" + "\"Gasto\"" + ","
								+ "\"Albaran proveedor: " + albaranString + "\"" + "," + "\"" + fechaString + "\""
								+ ",-" + bdTotal.toString() + ")");

					} catch (Exception ex) {

						JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
					}

					// Si todo es correcto cerramos creando otra ventana nueva
					try {

						SubVentanaMercancia svMercancia;
						svMercancia = new SubVentanaMercancia();
						VentanaPrincipal.desktopPane.add(svMercancia);
						svMercancia.show();

						dispose();

						// Si falla la nueva ventana se cierra igualmente para evitar duplicar el
						// albaran
					} catch (SQLException e1) {

						dispose();

					}

				} else {

					JOptionPane.showMessageDialog(null, "Compruebe los campos");
				}
			}
		});

	}

	/*
	 * 
	 * Funciones
	 * 
	 */

	/*
	 * Funcion para cargar los proveedores en el comboBox
	 */

	public void cargarProveedores() throws SQLException {

		try {

			rSet = cnn.SentenciaRset("select nombre from proveedor");

			ArrayList<String> proveedorList = new ArrayList<String>();
			proveedorList.add("");

			while (rSet.next()) {

				proveedorList.add(rSet.getString("nombre"));

			}

			cbProveedor.setModel(new DefaultComboBoxModel(proveedorList.toArray()));

			rSet.close();
			
		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);

		}

	}

	private String cadenaSql(String entrada) {

		return "\"" + entrada + "\"";

	}

	/*
	 * Popup Menu
	 */
}
