package erp;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import objetos.Conexion;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class SubVentanaFinanzas extends JInternalFrame {

	private static final long serialVersionUID = 8961023746842692754L;
	private JTextField tfDesde;
	private JTextField tfHasta;
	private JTable tabla;
	private DefaultTableModel model = (new DefaultTableModel(null,
			new String[] { "Tipo", "Origen", "Fecha", "Montante" }));
	private Conexion cnn = new Conexion();
	private ResultSet rSet;
	private JTextField tfIngresos;
	private JTextField tfGastos;
	private JTextField tfBalance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubVentanaFinanzas frame = new SubVentanaFinanzas();
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
	public SubVentanaFinanzas() {
		setClosable(true);
		setBounds(145, 0, 630, 566);
		this.setTitle("Finanzas");

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 12, 567, 45);
		panel.add(panel_1);

		JLabel lblNewLabel = new JLabel("Desde");
		panel_1.add(lblNewLabel);

		tfDesde = new JTextField();
		tfDesde.setToolTipText("Formato AAAA-MM-DD");
		panel_1.add(tfDesde);
		tfDesde.setColumns(7);

		JLabel lblNewLabel_1 = new JLabel("Hasta");
		panel_1.add(lblNewLabel_1);

		tfHasta = new JTextField();
		tfHasta.setToolTipText("Formato AAAA-MM-DD");
		panel_1.add(tfHasta);
		tfHasta.setColumns(7);

		JButton btnBuscar = new JButton("Buscar");
		panel_1.add(btnBuscar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 69, 567, 350);
		panel.add(scrollPane);

		tabla = new JTable();
		tabla.setEnabled(false);
		scrollPane.setViewportView(tabla);
		tabla.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabla.setModel(model);
		tabla.getColumnModel().getColumn(0).setMaxWidth(110);
		tabla.getColumnModel().getColumn(1).setMaxWidth(240);
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(3).setMaxWidth(100);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 431, 567, 45);
		panel.add(panel_2);

		JLabel lblNewLabel_2 = new JLabel("Ingresos");
		panel_2.add(lblNewLabel_2);

		tfIngresos = new JTextField();
		tfIngresos.setHorizontalAlignment(SwingConstants.RIGHT);
		tfIngresos.setEditable(false);
		panel_2.add(tfIngresos);
		tfIngresos.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Gastos");
		panel_2.add(lblNewLabel_3);

		tfGastos = new JTextField();
		tfGastos.setHorizontalAlignment(SwingConstants.RIGHT);
		tfGastos.setEditable(false);
		panel_2.add(tfGastos);
		tfGastos.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Balance");
		panel_2.add(lblNewLabel_4);

		tfBalance = new JTextField();
		tfBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		tfBalance.setEditable(false);
		panel_2.add(tfBalance);
		tfBalance.setColumns(10);
		
		// Recoge la fecha de hoy
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
		tfDesde.setText(formateador.format(ahora));
		tfHasta.setText(formateador.format(ahora));
		
		/*
		 * implementado bot�n buscar
		 */
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				buscar();
			}
		});

	}

	private void buscar() {

		limpiar();

		try {

			String desde = "\"" + tfDesde.getText().toString() + "\"";
			String hasta = "\"" + tfHasta.getText().toString() + "\"";
			float ingresos = 0.00F;
			float gastos = 0.00F;
			float balance = 0.00F;

			rSet = cnn.SentenciaRset("select * from finanzas where fecha between " + desde + " and " + hasta);

			while (rSet.next()) {

				String tipo = rSet.getString("tipo");
				String origen = rSet.getString("origen");
				String fecha = rSet.getString("fecha");
				String montante = rSet.getString("montante");

				model.addRow(new Object[] { tipo, origen, fecha, montante });
				if (tipo.equals("Ingreso")) {

					ingresos += Float.parseFloat(montante);

				} else if (tipo.equals("Gasto")) {

					gastos += Float.parseFloat(montante);

				}
				balance = ingresos + gastos;

				BigDecimal bdIngresos = new BigDecimal(ingresos);
				BigDecimal bdGastos = new BigDecimal(gastos);
				BigDecimal bdBalance = new BigDecimal(balance);

				tfIngresos.setText(bdIngresos.setScale(2, RoundingMode.HALF_EVEN).toString());
				tfGastos.setText(bdGastos.setScale(2, RoundingMode.HALF_EVEN).toString());
				tfBalance.setText(bdBalance.setScale(2, RoundingMode.HALF_EVEN).toString());

			}

			rSet.close();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Compruebe las fechas");

		}

	}

	public void limpiar() {

		while (model.getRowCount() > 0) {

			model.removeRow(0);

		}

	}

}
