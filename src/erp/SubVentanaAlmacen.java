package erp;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import objetos.Conexion;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SubVentanaAlmacen extends JInternalFrame {
	
	private static final long serialVersionUID = 3780432862543987715L;
	private JTable tabla;
	private DefaultTableModel model = (new DefaultTableModel(null, new String[] { "Albaran", "Proveedor", "Fecha" }));
	private Conexion cnn=new Conexion();
	private ResultSet rSet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubVentanaAlmacen frame = new SubVentanaAlmacen();
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
	public SubVentanaAlmacen() {
		setClosable(true);
		setBounds(145, 0, 713, 559);
		getContentPane().setLayout(null);
		this.setTitle("Albaranes de proveedor");
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 12, 667, 42);
		getContentPane().add(panel);

		JButton btnNuevo = new JButton("Nuevo");
		panel.add(btnNuevo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 667, 449);
		getContentPane().add(scrollPane);

		/*
		 * Configuracion de la tabla
		 */

		tabla = new JTable();
		scrollPane.setViewportView(tabla);
		tabla.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabla.setModel(model);
		tabla.getColumnModel().getColumn(0).setMaxWidth(120);
		tabla.getColumnModel().getColumn(1).setMaxWidth(440);
		tabla.getColumnModel().getColumn(2).setMaxWidth(90);

		try {

			cargarAlbaranes();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		/**
		 * Implementado boton Nuevo.
		 */
		btnNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				SubVentanaMercancia svMercancia;
				try {

					svMercancia = new SubVentanaMercancia();
					VentanaPrincipal.desktopPane.add(svMercancia);
					svMercancia.show();

				} catch (SQLException ex) {

					JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);

				}

			}
		});

	}

	/*
	 * Funciones
	 */

	/**
	 * Cargan los albaranes en la tabla
	 */
	private void cargarAlbaranes() throws SQLException {

		try {

			rSet = cnn.SentenciaRset("select distinct albaran, fecha, id_proveedor  from entrada_mercancia ");

			ArrayList<String[]> albaran = new ArrayList<String[]>();
			

			while (rSet.next()) {
				
				String[] datos = new String[3];
				datos[0] = rSet.getString("albaran");
				datos[1] = rSet.getString("fecha");
				datos[2] = rSet.getString("id_proveedor");
				albaran.add(datos);

			}
			rSet.close();

			Iterator<String[]> iterator = albaran.iterator();

			/**
			 * Rellena la tabla con los resultados.
			 */
			while (iterator.hasNext()) {

				String[] datos = new String[3];
				datos = iterator.next();

				rSet =cnn.SentenciaRset("select  nombre from proveedor where id_proveedor = \"" + datos[2] + "\"");

				model.addRow(new Object[] { datos[0], rSet.getString("nombre"), datos[1] });
			
				rSet.close();
			}

		    } catch (Exception ex) {

			JOptionPane.showMessageDialog(null, ex, "ERROR", JOptionPane.ERROR_MESSAGE);

		}
	

	}
}
