package erp;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import objetos.Conexion;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SubVentanaInventario extends JInternalFrame {

	private static final long serialVersionUID = 2224663952800127365L;
	private JTextField tfCodigo;
	private JTable tabla;
	private DefaultTableModel model = (new DefaultTableModel(null,
			new String[] { "Codigo", "Descripcion", "Cantidad", "Recuento", "Diferencia" }));
	private Conexion cnn = new Conexion();
	private ResultSet rSet;
	private JTextField tfDescripcion;
	private JCheckBox chbxCompleto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubVentanaInventario frame = new SubVentanaInventario();
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
	public SubVentanaInventario() {
		
		setClosable(true);
		setBounds(145, 0, 585, 578);
		getContentPane().setLayout(null);
		this.setTitle("Inventario");
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 544, 36);
		getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		panel.add(lblNewLabel);
		
		tfCodigo = new JTextField();
		panel.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfDescripcion = new JTextField();
		tfDescripcion.setEnabled(false);
		panel.add(tfDescripcion);
		tfDescripcion.setColumns(20);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 82, 544, 382);
		getContentPane().add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 476, 548, 57);
		getContentPane().add(panel_1);
		
		JButton btnEjecutar = new JButton("<html><p>Ejecutar Ajuste</p></b><p aligment: center>Inventario</p></html>");
		panel_1.add(btnEjecutar);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 45, 544, 36);
		getContentPane().add(panel_2);
		
		chbxCompleto = new JCheckBox("Inventario completo");
		panel_2.add(chbxCompleto);
		
		JButton btnIncluir = new JButton("Incluir");
		panel_2.add(btnIncluir);
		
		tabla = new JTable();
		tabla.setEnabled(true);
		scrollPane.setViewportView(tabla);
		tabla.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabla.setModel(model);
		tabla.getColumnModel().getColumn(0).setMaxWidth(120);
		tabla.getColumnModel().getColumn(1).setMaxWidth(230);
		tabla.getColumnModel().getColumn(2).setMaxWidth(60);
		tabla.getColumnModel().getColumn(3).setMaxWidth(60);
		tabla.getColumnModel().getColumn(4).setMaxWidth(60);
		
		
		/*
		 * Cuando tfCodigo pierda el foco rellene el TfDescripcion
		 */
		tfCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				if (!tfCodigo.getText().toString().equals(""))
				try {
					
					String codigo ="\""+ tfCodigo.getText().toString()+"\"";
					rSet = cnn.SentenciaRset("select nombre from producto where id_producto = "+codigo);
					rSet.next();
					tfDescripcion.setText(rSet.getString("nombre"));
					
					rSet.close();	
					
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, "No existe ningun producto con ese codigo");
					tfCodigo.setText("");
				}
				
							
			}
		});
	
		 
		/*
		 * Implementado check
		 */
		chbxCompleto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(chbxCompleto.isSelected()) {
					
					tfCodigo.setEnabled(false);	
					
				}else {
					
					limpiar();
					tfCodigo.setEnabled(true);	
					
				}
			}
		});
		/*
		 * Implementado boton ejecutar inventario
		 */
		btnEjecutar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int opcion = JOptionPane.showOptionDialog(null, "Esta accion no se puede deshacer, �continuar?", "Advertencia",
						JOptionPane.OK_CANCEL_OPTION, 1, null, null, null);
				
				if (opcion==0) {
					
					try {
						ejecutarCambios();
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		/*
		 * Actualiza los cambio en la tabla
		 */
		tabla.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {

				if (tabla.getRowCount() > 0) {

					int filaSelecionada = tabla.getSelectedRow();
					int cantidad = Integer.parseInt(tabla.getValueAt(filaSelecionada, 2).toString());
					int recuento =  Integer.parseInt(tabla.getValueAt(filaSelecionada, 3).toString());
					int diferencia = recuento-cantidad;

					tabla.setValueAt( diferencia,filaSelecionada, 4);

				}

			}
		});
		
		/*
		 * Implementado boton Incluir
		 */
		btnIncluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					buscar();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	}
	
	/*
	 * 
	 * Funciones
	 * 
	 */
	private void buscar() throws SQLException {
		
		String codigo ="\""+ tfCodigo.getText().toString()+"\"";

		if (chbxCompleto.isSelected()) {
			
			rSet = cnn.SentenciaRset("Select * from producto" );
			limpiar();
			
		}else {
		
			rSet = cnn.SentenciaRset("Select * from producto where id_producto ="+ codigo);
			
		}	
		
		while (rSet.next()) {
			
			codigo = rSet.getString("id_producto");
			String descripcion = rSet.getString("nombre");
			int stock =Integer.parseInt( rSet.getString("stock"));

			model.addRow(new Object[] {codigo,descripcion,stock,stock,0});
			
		}
		
		tfCodigo.setText("");
		tfDescripcion.setText("");
		
		rSet.close();

	}
	
	private void ejecutarCambios() throws SQLException {
		
		int nFilas =model.getRowCount();
		
		if (nFilas>0) {
			
			for (int i = 0; i<nFilas;i++) {
				
				String codigo = "\""+model.getValueAt(i, 0).toString()+"\"";
				String diferencia=model.getValueAt(i, 4).toString();
				
				cnn.sentencia("update producto set  stock  = stock +" + diferencia
						+ " where id_producto=" + codigo);
				
			}

			limpiar();
		}
		
	}
	private void limpiar() {
		
		while (model.getRowCount() > 0) {

			model.removeRow(0);

		}
		
		
	}
	
}
