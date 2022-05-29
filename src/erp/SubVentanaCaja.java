package erp;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import objetos.Conexion;
import objetos.Documento;
import objetos.NumField;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SubVentanaCaja extends JInternalFrame {

	static final long serialVersionUID = -7446336002728814259L;
	private JTextField tfFecha;
	private JTextField tfEfectivo;
	private JTextField tfTarjeta;
	private NumField tfRecEfectivo;
	private NumField tfRecTarjeta;
	private NumField tfFondo;
	private JTable tabla;
	private DefaultTableModel model = (new DefaultTableModel(null,
			new String[] { "Documento", "Cliente", "Efectivo", "Tarjeta" }));
	private Conexion cnn = new Conexion();
	private Conexion cnn2 = new Conexion();
	private ResultSet rSet;
	private ResultSet rSet2;
	private JTextField tfDiferenciaEf;
	private JTextField tfDiferenciaTj;
	private JButton btnCerrarCaja;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubVentanaCaja frame = new SubVentanaCaja();
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
	 
	public SubVentanaCaja() {
		setClosable(true);
		setBounds(145, 0, 602, 663);
		this.setTitle("Caja");

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 12, 568, 61);
		panel.add(panel_1);

		JLabel lblNewLabel = new JLabel("Fecha");
		panel_1.add(lblNewLabel);

		tfFecha = new JTextField();
		panel_1.add(tfFecha);
		tfFecha.setColumns(7);

		JButton btnCargar = new JButton("Cargar");
		panel_1.add(btnCargar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 85, 568, 400);
		panel.add(scrollPane);

		JPanel panel_2 = new JPanel();
		panel_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		panel_2.setBounds(12, 497, 568, 121);
		panel.add(panel_2);
		panel_2.setLayout(null);

		tfEfectivo = new JTextField();
		tfEfectivo.setEditable(false);
		tfEfectivo.setText("0.00");
		tfEfectivo.setHorizontalAlignment(SwingConstants.RIGHT);
		tfEfectivo.setBounds(85, 12, 89, 20);
		panel_2.add(tfEfectivo);
		tfEfectivo.setColumns(7);

		tfTarjeta = new JTextField();
		tfTarjeta.setText("0.00");
		tfTarjeta.setEditable(false);
		tfTarjeta.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTarjeta.setBounds(85, 44, 89, 20);
		panel_2.add(tfTarjeta);
		tfTarjeta.setColumns(7);

		tfRecEfectivo = new NumField();
		tfRecEfectivo.setText("0.00");
		tfRecEfectivo.setHorizontalAlignment(SwingConstants.RIGHT);
		tfRecEfectivo.setBounds(281, 12, 89, 20);
		panel_2.add(tfRecEfectivo);
		tfRecEfectivo.setColumns(7);

		tfRecTarjeta = new NumField();
		tfRecTarjeta.setText("0.00");
		tfRecTarjeta.setHorizontalAlignment(SwingConstants.RIGHT);
		tfRecTarjeta.setBounds(281, 44, 89, 20);
		panel_2.add(tfRecTarjeta);
		tfRecTarjeta.setColumns(7);

		tfFondo = new NumField();
		tfFondo.setText("0.00");
		tfFondo.setHorizontalAlignment(SwingConstants.RIGHT);
		tfFondo.setBounds(85, 76, 89, 20);
		panel_2.add(tfFondo);
		tfFondo.setColumns(7);

		btnCerrarCaja = new JButton("Cerrar Caja");
		btnCerrarCaja.setEnabled(false);
		btnCerrarCaja.setBounds(196, 76, 174, 20);
		panel_2.add(btnCerrarCaja);

		JLabel lblNewLabel_1 = new JLabel("Efectivo ");
		lblNewLabel_1.setBounds(12, 14, 55, 16);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tarjeta");
		lblNewLabel_2.setBounds(12, 46, 55, 16);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Fondo Caja");
		lblNewLabel_3.setBounds(12, 78, 75, 16);
		panel_2.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Recuento Ef");
		lblNewLabel_4.setBounds(196, 14, 82, 16);
		panel_2.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Recuento TJ");
		lblNewLabel_5.setBounds(196, 46, 82, 16);
		panel_2.add(lblNewLabel_5);

		JLabel lblNewLabel_4_1 = new JLabel("Diferencia EF");
		lblNewLabel_4_1.setBounds(381, 14, 82, 16);
		panel_2.add(lblNewLabel_4_1);

		tfDiferenciaEf = new JTextField();
		tfDiferenciaEf.setEditable(false);
		tfDiferenciaEf.setText("0.00");
		tfDiferenciaEf.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDiferenciaEf.setColumns(7);
		tfDiferenciaEf.setBounds(466, 12, 89, 20);
		panel_2.add(tfDiferenciaEf);

		JLabel lblNewLabel_5_1 = new JLabel("Diferencia TJ");
		lblNewLabel_5_1.setBounds(381, 46, 82, 16);
		panel_2.add(lblNewLabel_5_1);

		tfDiferenciaTj = new JTextField();
		tfDiferenciaTj.setEditable(false);
		tfDiferenciaTj.setText("0.00");
		tfDiferenciaTj.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDiferenciaTj.setColumns(7);
		tfDiferenciaTj.setBounds(466, 44, 89, 20);
		panel_2.add(tfDiferenciaTj);

		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		tabla.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabla.setModel(model);
		tabla.getColumnModel().getColumn(0).setMaxWidth(110);
		tabla.getColumnModel().getColumn(1).setMaxWidth(240);
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(3).setMaxWidth(100);

		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		tfFecha.setText(formateador.format(ahora));

		/*
		 * Boton cargar
		 */
		btnCargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cargar();

			}
		});
		/**
		 * Cuando se actualiza los campos
		 */
		tfRecEfectivo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				calcular();

			}
		});
		tfRecTarjeta.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				calcular();

			}
		});
		tfFondo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				calcular();

			}
		});

		btnCerrarCaja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (btnCerrarCaja.isEnabled())
					cerrarCaja();

			}
		});
		/**
		 * Menu reimpresion
		 */
		
		JPopupMenu popupMenu = new JPopupMenu();
		JButton btnReimpresion = new JButton("Imprimir documento");
		popupMenu.add(btnReimpresion);
		tabla.setComponentPopupMenu(popupMenu);
		
		btnReimpresion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int eleccion=tabla.getSelectedRow();
				String codigo=model.getValueAt(eleccion, 0).toString();
				
				
				try {
					
					Documento doc = new Documento(codigo);
					doc.imprimir();
					
				} catch (IOException e1) {
					
					JOptionPane.showMessageDialog(null, "No ha elegido ningun documento");
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
	 * Carga las ventas de la fecha indicada
	 */
	private void cargar() {

		limpiar();

		float totalEfectivo = 0.00F;
		float totalTarjeta = 0.00F;

		String fecha = "\"" + tfFecha.getText().toString() + "\"";

		try {

			rSet = cnn.SentenciaRset("select * from factura where fecha =" + fecha);

			if (rSet.isClosed()) {

				JOptionPane.showMessageDialog(null, "No hay ventas en esa fecha");
				//si no hay caja deshabilita el boton de cierre de caja
				btnCerrarCaja.setEnabled(false);
				
			} else {

				while (rSet.next()) {

					String idFactura = rSet.getString("id_factura");
					String idCliente = "\"" + rSet.getString("id_cliente") + "\"";
					String efectivo = rSet.getString("efectivo");
					String tarjeta = rSet.getString("tarjeta");
					String cliente = "";

					totalEfectivo += Float.parseFloat(efectivo);
					totalTarjeta += Float.parseFloat(tarjeta);

					rSet2 = cnn2.SentenciaRset("select nombre,apellidos from cliente where id_cliente = " + idCliente);

					while (rSet2.next()) {

						cliente = rSet2.getString("nombre") + " " + rSet2.getString("apellidos");
					}

					rSet2.close();
					model.addRow(new Object[] { idFactura, cliente, efectivo, tarjeta });

				}

				rSet.close();

				BigDecimal bdEfectivo = new BigDecimal(totalEfectivo);
				BigDecimal bdTarjeta = new BigDecimal(totalTarjeta);

				tfEfectivo.setText(bdEfectivo.setScale(2, RoundingMode.HALF_EVEN).toString());
				tfTarjeta.setText(bdTarjeta.setScale(2, RoundingMode.HALF_EVEN).toString());

				//comprobamos si la caja está cerrada.
				comprobarCaja();
				
			

			}

			calcular();

			

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Limpia la tabla
	 */
	public void limpiar() {

		while (model.getRowCount() > 0) {

			model.removeRow(0);

		}

	}

	private void calcular() {

		Float recEfectivo = Float.parseFloat(tfRecEfectivo.getText().toString());
		Float recTarjeta = Float.parseFloat(tfRecTarjeta.getText().toString());
		Float fondoCaja = Float.parseFloat(tfFondo.getText().toString());
		Float efectivo = Float.parseFloat(tfEfectivo.getText().toString());
		Float tarjeta = Float.parseFloat(tfTarjeta.getText().toString());

		BigDecimal bdDiferenciaEf = new BigDecimal( recEfectivo-fondoCaja-efectivo );
		BigDecimal bdDifernciaTj = new BigDecimal( recTarjeta-tarjeta);

		tfDiferenciaEf.setText(bdDiferenciaEf.setScale(2, RoundingMode.HALF_EVEN).toString());
		tfDiferenciaTj.setText(bdDifernciaTj.setScale(2, RoundingMode.HALF_EVEN).toString());

	}

	/**
	 * Cerrar la caja y hacer los apuntes necesarios.
	 */
	private void cerrarCaja() {

		int opcion = JOptionPane.showOptionDialog(null, "Esta accion no se puede deshacer, ¿continuar?", "Advertencia",
				JOptionPane.OK_CANCEL_OPTION, 1, null, null, null);
		System.out.println(opcion);

		if (opcion == 0) {

			String fecha = "\"" + tfFecha.getText().toString() + "\"";
			String fondo = tfFondo.getText().toString();
			String recuentoEf = tfRecEfectivo.getText().toString();
			String recuentoTj = tfRecTarjeta.getText().toString();
			float difereciaEf = Float.parseFloat(tfDiferenciaEf.getText().toString());
			float diferenciaTj = Float.parseFloat(tfDiferenciaTj.getText().toString());
			float diferenciaTotal = difereciaEf + diferenciaTj;

			try {

				cnn.sentencia("insert into caja (fecha, fondo,recuento_efectivo,recuento_tarjeta) values (" + fecha
						+ "," + fondo + "," + recuentoEf + "," + recuentoTj + ")");

				if (diferenciaTotal < 0) {

					cnn.sentencia(
							"insert into finanzas (tipo, origen, fecha,montante) values (\"Gasto\", \"Diferencia Caja\","
									+ fecha + "," + diferenciaTotal + ")");

				} else if (diferenciaTotal > 0) {

					cnn.sentencia(
							"insert into finanzas (tipo, origen, fecha,montante) values (\"Ingreso\", \"Diferencia Caja\","
									+ fecha + "," + diferenciaTotal + ")");

				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
			cargar();

		}

	}
	
	/**
	 * Comprueba si la caja ha sido cerrada
	 */
	
	private void comprobarCaja() throws SQLException {
		
		rSet=cnn.SentenciaRset("select * from caja where fecha = \""+tfFecha.getText().toString()+"\"");
		
		if(rSet.isClosed()) {
			
			//Habilita los campos
			tfRecEfectivo.setEnabled(true);
			tfRecTarjeta.setEnabled(true);
			tfFondo.setEnabled(true);
			//Los inicia a 0
			tfRecEfectivo.setText("0.00");
			tfRecTarjeta.setText("0.00");
			
			//Habilita el boton de cierre de caja
			btnCerrarCaja.setEnabled(true);		
			
		}else {
			
			while(rSet.next()) {
				//Rellena los campos 
				tfRecEfectivo.setText(rSet.getString("recuento_efectivo"));
				tfRecTarjeta.setText( rSet.getString("recuento_tarjeta"));
				tfFondo.setText(rSet.getString("fondo"));			

			}
			//Deshabilita los campos
			tfRecEfectivo.setEnabled(false);
			tfRecTarjeta.setEnabled(false);
			tfFondo.setEnabled(false);
		
			//Deshabilira el boton de cierre de caja
			btnCerrarCaja.setEnabled(false);
			
			
		}
		
	}

}
