package erp;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

import objetos.Cliente;
import objetos.Conexion;
import objetos.Documento;
import objetos.Factura;
import objetos.NumField;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.beans.PropertyChangeEvent;

public class SubVentanaTPV extends JInternalFrame {
	
	private static final long serialVersionUID = 3079960855634405624L;
	private JTextField tfCantidad;
	private NumField tfPrecio;
	private NumField tfDescuento;
	private JTextField tfPendiente;
	private JTextField tfTotal;
	private JTextField tfPagado;
	private NumField tfPagoEfectivo;
	private NumField tfPagoTarjeta;
	private JTextField tfDni;
	private JTextField tfCodigo;
	private JTextField tfDescripcion;
	private JTable table;
	private JLabel lblNombre;
	// Modelo de la tabla
	private DefaultTableModel model = (new DefaultTableModel(null,
			new String[] { "Codigo", "Descripcion", "PVP", "Dto", "IVA", "Cnt", "Total", "Comentario" }));
	private JTextField tfDocumento;
	private JTextField tfFecha;
	private JCheckBox checkFactura;
	private ResultSet rSet;
	private Conexion cnn = new Conexion();
	private String descripcion;
	private String cantidad;
	private String precio;
	private String descuento;
	private String iva;
	private String codigo;
	private float total = 0.00f;
	private float pendiente = 0.00f;
	private float pagado = 0.00f;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubVentanaTPV frame = new SubVentanaTPV();
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
	public SubVentanaTPV() throws SQLException {

		this.setTitle("TPV");

		getContentPane().setFont(new Font("TPV", Font.PLAIN, 17));
		setClosable(true);
		setBounds(145, 0, 816, 686);
		getContentPane().setLayout(null);

		lblNombre = new JLabel("");
		lblNombre.setFocusCycleRoot(true);
		lblNombre.setBounds(241, 83, 534, 20);
		getContentPane().add(lblNombre);

		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 774, 160);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Documento N\u00BA: ");
		lblNewLabel.setBounds(37, 12, 114, 33);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblNewLabel_1_1 = new JLabel("Fecha");
		lblNewLabel_1_1.setBounds(253, 12, 47, 33);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(442, 16, 98, 26);
		panel.add(btnNuevo);

		checkFactura = new JCheckBox("Factura");
		checkFactura.setBounds(33, 69, 78, 23);
		panel.add(checkFactura);
		checkFactura.setHorizontalAlignment(SwingConstants.LEFT);

		tfDni = new JTextField();
		tfDni.setBounds(119, 70, 86, 20);
		panel.add(tfDni);
		tfDni.setEnabled(false);
		tfDni.setColumns(10);

		tfCodigo = new JTextField();
		tfCodigo.setBounds(37, 116, 131, 20);
		panel.add(tfCodigo);
		tfCodigo.setColumns(10);

		tfDescripcion = new JTextField();
		tfDescripcion.setBounds(180, 116, 299, 20);
		panel.add(tfDescripcion);
		tfDescripcion.setColumns(10);

		tfCantidad = new JTextField();
		tfCantidad.setBounds(485, 116, 55, 20);
		panel.add(tfCantidad);
		tfCantidad.setColumns(10);

		tfPrecio = new NumField();
		tfPrecio.setBounds(548, 116, 79, 20);
		panel.add(tfPrecio);
		tfPrecio.setColumns(10);

		tfDescuento = new NumField();
		tfDescuento.setBounds(639, 116, 55, 20);
		panel.add(tfDescuento);
		tfDescuento.setColumns(10);

		JButton btnNewButton_5 = new JButton("+");
		btnNewButton_5.setBounds(706, 113, 41, 26);
		panel.add(btnNewButton_5);

		JLabel lblNewLabel_11 = new JLabel("DNI");
		lblNewLabel_11.setBounds(120, 57, 86, 14);
		panel.add(lblNewLabel_11);
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_2 = new JLabel("Codigo");
		lblNewLabel_2.setBounds(38, 100, 131, 16);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_3 = new JLabel("Descripcion");
		lblNewLabel_3.setBounds(181, 100, 299, 16);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_4 = new JLabel("Cnt");
		lblNewLabel_4.setBounds(486, 100, 55, 16);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_5 = new JLabel("Precio");
		lblNewLabel_5.setBounds(548, 100, 79, 16);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_6 = new JLabel("Dscto");
		lblNewLabel_6.setBounds(639, 100, 55, 16);
		panel.add(lblNewLabel_6);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);

		tfDocumento = new JTextField();
		tfDocumento.setEditable(false);
		tfDocumento.setBounds(148, 19, 98, 20);
		panel.add(tfDocumento);
		tfDocumento.setColumns(10);

		tfFecha = new JTextField();
		tfFecha.setEditable(false);
		tfFecha.setBounds(301, 19, 100, 20);
		panel.add(tfFecha);
		tfFecha.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(691, 70, 0, 0);
		panel.add(textField);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(42, 481, 719, 160);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_9 = new JLabel("Pago efectivo");
		lblNewLabel_9.setBounds(238, 45, 93, 16);
		panel_1.add(lblNewLabel_9);

		tfPagoEfectivo = new NumField();
		tfPagoEfectivo.setText("0.00");
		tfPagoEfectivo.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPagoEfectivo.setBounds(331, 43, 93, 20);
		panel_1.add(tfPagoEfectivo);
		tfPagoEfectivo.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Pago tarjeta");
		lblNewLabel_10.setBounds(238, 80, 93, 16);
		panel_1.add(lblNewLabel_10);

		tfPagoTarjeta = new NumField();
		tfPagoTarjeta.setText("0.00");
		tfPagoTarjeta.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPagoTarjeta.setBounds(331, 78, 93, 20);
		panel_1.add(tfPagoTarjeta);
		tfPagoTarjeta.setColumns(10);

		JButton btnPagar = new JButton("Pagar");
		btnPagar.setBounds(331, 122, 93, 26);
		panel_1.add(btnPagar);

		JLabel lblNewLabel_7 = new JLabel("Total");
		lblNewLabel_7.setBounds(503, 43, 93, 26);
		panel_1.add(lblNewLabel_7);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setFont(new Font("Dialog", Font.BOLD, 17));

		JLabel lblNewLabel_8 = new JLabel("Pagado");
		lblNewLabel_8.setBounds(503, 81, 93, 26);
		panel_1.add(lblNewLabel_8);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setFont(new Font("Dialog", Font.BOLD, 17));

		JLabel lblNewLabel_8_1 = new JLabel("Pendiente");
		lblNewLabel_8_1.setBounds(503, 118, 93, 26);
		panel_1.add(lblNewLabel_8_1);
		lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8_1.setFont(new Font("Dialog", Font.BOLD, 17));

		tfTotal = new JTextField();
		tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotal.setEditable(false);
		tfTotal.setText("0.00");
		tfTotal.setBounds(605, 45, 114, 26);
		panel_1.add(tfTotal);
		tfTotal.setColumns(10);

		tfPagado = new JTextField();
		tfPagado.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPagado.setEditable(false);
		tfPagado.setText("0.00");
		tfPagado.setBounds(605, 82, 114, 25);
		panel_1.add(tfPagado);
		tfPagado.setColumns(10);

		tfPendiente = new JTextField();
		tfPendiente.setText("0.00");
		tfPendiente.setHorizontalAlignment(SwingConstants.RIGHT);
		tfPendiente.setEditable(false);
		tfPendiente.setBounds(605, 119, 114, 25);
		panel_1.add(tfPendiente);
		tfPendiente.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 173, 774, 296);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(model);
		table.setPreferredScrollableViewportSize(getSize());
		table.getColumnModel().getColumn(0).setMaxWidth(90);
		table.getColumnModel().getColumn(1).setMaxWidth(220);
		table.getColumnModel().getColumn(2).setMaxWidth(65);
		table.getColumnModel().getColumn(3).setMaxWidth(50);
		table.getColumnModel().getColumn(4).setMaxWidth(50);
		table.getColumnModel().getColumn(5).setMaxWidth(50);
		table.getColumnModel().getColumn(6).setMaxWidth(65);
		table.getColumnModel().getColumn(7).setMaxWidth(170);

		JPopupMenu popupMenu = new JPopupMenu();
		JButton btnEliminarLinea = new JButton("Eliminar linea");
		popupMenu.add(btnEliminarLinea);
		table.setComponentPopupMenu(popupMenu);

		/**
		 * inicio
		 */

		nuevo();

		/*
		 * implementado boton nuevo
		 */

		btnNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					nuevo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1, "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		/**
		 * implementado check factura
		 */

		checkFactura.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (checkFactura.isSelected()) {

					tfDni.setEnabled(true);

				} else {

					tfDni.setEnabled(false);
				}
			}
		});
		/*
		 * Campo DNI
		 */

		tfDni.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			
				Cliente cliente = new Cliente(tfDni.getText().toString());
				String datosClietnte= cliente.toString();
				if (datosClietnte.equals("")) {
					
					JOptionPane.showMessageDialog(null, "No existe cliente con ese DNI/CIF");
					tfDni.setText("");
					tfDni.setEnabled(false);
					checkFactura.setSelected(false);
					
				}else {
					
					lblNombre.setText(datosClietnte);
				}
				
			
			}
		});
		
		/*
		 * Cuando pierde el foco codigo
		 */
		tfCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if (!tfCodigo.getText().toString().equals(""))
				try {
					codigo = tfCodigo.getText();
					rSet = cnn.SentenciaRset("select * from producto where id_producto =\"" + codigo + "\"");

					if (rSet.isClosed()) {

						JOptionPane.showMessageDialog(null, "El producto no existe");

					} else {

						descripcion = rSet.getString("nombre");
						cantidad = "1";
						precio = rSet.getString("pvp");
						descuento = "0";
						iva = rSet.getString("iva");

						while (rSet.next()) {

							tfDescripcion.setText(descripcion);
							tfCantidad.setText(cantidad);
							tfPrecio.setText(precio);
							tfDescuento.setText(descuento);

						}

					}

				} catch (SQLException e1) {

					JOptionPane.showMessageDialog(null, e1, "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		/*
		 * Implementado boton +
		 */

		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					cantidad = tfCantidad.getText();
					descuento = tfDescuento.getText();
					precio = tfPrecio.getText();
					descripcion = tfDescripcion.getText();

					float precioFinal = Float.parseFloat(precio)
							- (Float.parseFloat(descuento) * Float.parseFloat(precio) / 100);
					float total = (precioFinal + (precioFinal * Float.parseFloat(iva) / 100))
							* Integer.parseInt(cantidad);

					BigDecimal totalBigDecimal = new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);

					model.addRow(new Object[] { codigo, descripcion, precio, descuento, iva, cantidad, totalBigDecimal,
							"" });

					total();
					
					//limpiar 
					tfCodigo.setText("");
					tfCantidad.setText("");
					tfDescuento.setText("");
					tfPrecio.setText("");
					tfDescripcion.setText("");

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2, "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		/*
		 * Actualiza la fila si se cambia la cantidad en tabla
		 */

		table.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {

				if (table.getRowCount() > 0) {

					int filaSelecionada = table.getSelectedRow();
					int cantidad = Integer.parseInt(table.getValueAt(filaSelecionada, 5).toString());
					float sinIva = Float.parseFloat(table.getValueAt(filaSelecionada, 2).toString());
					float ivaFila = Float.parseFloat(table.getValueAt(filaSelecionada, 4).toString());

					float totalConIVA = cantidad * (sinIva + (sinIva * ivaFila / 100));

					BigDecimal bdTotal = new BigDecimal(totalConIVA).setScale(2, RoundingMode.HALF_EVEN);
					table.setValueAt(bdTotal, filaSelecionada, 6);

					total();

				}

			}
		});
		/*
		 * Implementado popup borrar linea
		 */
		btnEliminarLinea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					model.removeRow(table.getSelectedRow());
					total();

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "Ninguna linea selecionada");
				}

				popupMenu.setVisible(false);
			}
		});

		/*
		 * Actualiza los totales cuando se cambia el valor efectivo y tarjeta
		 */

		tfPagoEfectivo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				totalizar();
			}
		});
		tfPagoTarjeta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				totalizar();
			}
		});

		/*
		 * Implementado boton pagar
		 */

		btnPagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (tfTotal.getText().toString().equals("0.00")) {

					JOptionPane.showMessageDialog(null, "No se puede crear venta vacia");

				} else if (tfPendiente.getText().toString().equals("0.00")) {

					pagar();
				} else {

					JOptionPane.showMessageDialog(null, "No se ha satisfecho la cantidad");
				}

			}
		});
	}

	/*
	 * 
	 * Funciones
	 * 
	 */

	private void nuevo() throws SQLException {

		rSet = cnn.SentenciaRset("select id_factura from factura order by id_factura desc");
		rSet.next();
		int documento = rSet.getInt("id_factura") + 1;
		tfDocumento.setText(Integer.toString(documento));
		rSet.close();

		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		tfFecha.setText(formateador.format(ahora));

		checkFactura.setSelected(false);

		tfDni.setText("");
		tfCodigo.setText("");
		tfDescripcion.setText("");
		tfCantidad.setText("");
		tfPrecio.setText("");
		tfDescuento.setText("");
		tfPagoEfectivo.setText("0.00");
		tfPagoTarjeta.setText("0.00");
		tfPagado.setText("0.00");
		tfPendiente.setText("0.00");
		tfTotal.setText("0.00");
		lblNombre.setText("");
		tfDni.setEnabled(false);
		
		while (model.getRowCount() > 0) {

			model.removeRow(0);

		}

		

	}

	private void total() {

		int nFilas = model.getRowCount();
		total = 0.00F;

		for (int i = 0; i < nFilas; i++) {

			total = total + Float.parseFloat(model.getValueAt(i, 6).toString());
			BigDecimal bdTotal = new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
			tfTotal.setText(bdTotal.toString());
		}

		pendiente = total - pagado;
		BigDecimal bdPendiente = new BigDecimal(pendiente).setScale(2, RoundingMode.HALF_EVEN);
		tfPendiente.setText(bdPendiente.toString());

		if (nFilas == 0) {

			tfTotal.setText("0.00");
		}

	}

	private void pagar() {

		String idCliente;
		if (checkFactura.isSelected()) {

			idCliente = "\"" + tfDni.getText().toString() + "\"";

		} else {

			idCliente = "\"0\"";// cliente tpv

		}
		String idFactura = tfDocumento.getText().toString();
		String fecha = "\"" + tfFecha.getText().toString() + "\"";
		String efectivo = tfPagoEfectivo.getText().toString();
		String tarjeta = tfPagoTarjeta.getText().toString();

		try {

			Factura factura = new Factura(idCliente, idFactura, fecha, efectivo, tarjeta);

			factura.crearFactura();
			
			// comprueba que no exista la factura
			rSet = cnn.SentenciaRset("select * from detalle where id_factura =" + idFactura);

			if (rSet.isClosed()) {

				for (int i = 0; i < table.getRowCount(); i++) {

					String idProducto = "\"" + model.getValueAt(i, 0).toString() + "\"";
					String pvp = model.getValueAt(i, 2).toString();
					String cantidad = model.getValueAt(i, 5).toString();
					String comentario = "\"" + model.getValueAt(i, 7).toString() + "\"";
					String iva = model.getValueAt(i, 4).toString();
					String descuento = model.getValueAt(i, 3).toString();

					
					factura.crearDetalles(idProducto, pvp, cantidad, comentario, iva, descuento);

				}

				rSet.close();

				//apunte en finanzas
				cnn.sentencia("insert into finanzas (tipo, origen, fecha, montante) values(" + "\"Ingreso\"" + ","
						+ "\"factura:" + idFactura.toString() + "\"" + "," + fecha + "," + efectivo + "+" + tarjeta
						+ ")");

			}

			//impresion
			
			int opcion = JOptionPane.showOptionDialog(null, "Desea imprimir el documento", "Advertencia",
					JOptionPane.OK_CANCEL_OPTION, 1, null, null, null);
			
			if (opcion==0) {
				
				try {
					
					Documento doc = new Documento(tfDocumento.getText().toString());
					doc.imprimir();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			nuevo();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

	}

	private void totalizar() {

		float efectivo = Float.parseFloat(tfPagoEfectivo.getText().toString());
		float tarjeta = Float.parseFloat(tfPagoTarjeta.getText().toString());
		float total = Float.parseFloat(tfTotal.getText().toString());
		float pagado = 0.00F;
		float pendiente = Float.parseFloat(tfPendiente.getText().toString());

		BigDecimal bdPagado;
		BigDecimal bdPendiente;

		if (efectivo > total) {

			float devolucion = pendiente - efectivo;
			BigDecimal bdDevolucion = new BigDecimal(devolucion).setScale(2, RoundingMode.HALF_EVEN);
			JOptionPane.showMessageDialog(null, "devolucion: " + bdDevolucion);

			efectivo = pendiente;
			BigDecimal bdEfectivo = new BigDecimal(efectivo).setScale(2, RoundingMode.HALF_EVEN);

			tfPagoEfectivo.setText(bdEfectivo.toString());

		}

		if (tarjeta > pendiente) {

			JOptionPane.showMessageDialog(null, "El pago con tarjeta no puede superar la cantidad pendiente");
			tarjeta = 0.00F;
			tfPagoTarjeta.setText("0.00");

		}

		pagado = efectivo + tarjeta;
		bdPagado = new BigDecimal(pagado).setScale(2, RoundingMode.HALF_EVEN);
		tfPagado.setText(bdPagado.toString());

		bdPendiente = new BigDecimal(total - pagado).setScale(2, RoundingMode.HALF_EVEN);
		tfPendiente.setText(bdPendiente.toString());
	}
}
