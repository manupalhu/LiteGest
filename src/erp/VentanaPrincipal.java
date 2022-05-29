package erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = -2486939625761411526L;
	private JPanel contentPane;
	public static JDesktopPane desktopPane= new JDesktopPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1107, 739);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(desktopPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 145, 1500);
		desktopPane.add(panel);

		JButton btnNewButton = new JButton("Finanzas");

		JButton btnProveedores = new JButton("Proveedores");

		JButton btnNewButton_3 = new JButton("Caja");

		JButton btnTpv = new JButton("TPV");

		JButton btnClientes = new JButton("Clientes");
	
		
		@SuppressWarnings("rawtypes")
		JComboBox<?> cbAlmacen = new JComboBox();
		cbAlmacen.setModel(
				new DefaultComboBoxModel(new String[] { "    Almacen", "Alta/Baja Productos","Entrada mercancia ", "Inventario" }));
		
		

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnProveedores, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cbAlmacen, 0, 0, Short.MAX_VALUE))
					.addContainerGap(27, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnClientes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(27))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnTpv, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
					.addGap(27))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addGap(25))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(35)
					.addComponent(btnNewButton)
					.addGap(22)
					.addComponent(btnProveedores)
					.addGap(22)
					.addComponent(cbAlmacen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(btnClientes)
					.addGap(22)
					.addComponent(btnNewButton_3)
					.addGap(22)
					.addComponent(btnTpv)
					.addContainerGap(1187, Short.MAX_VALUE))
		);

		panel.setLayout(gl_panel);

		btnTpv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {
					SubVentanaTPV tpv = new SubVentanaTPV();
					desktopPane.add(tpv);
					tpv.show();
					
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, e2, "ERROR", JOptionPane.ERROR_MESSAGE);	
				}
				

			}
		});

		btnProveedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SubVentanaProveedor proveedor = new SubVentanaProveedor();
				desktopPane.add(proveedor);
				proveedor.show();
			}
		});

		cbAlmacen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int seleccion= cbAlmacen.getSelectedIndex();

				switch (seleccion) {
				
				case 1:
					
					SubVentanaProducto svProduto = new SubVentanaProducto();
					desktopPane.add(svProduto);
					svProduto.show();
					cbAlmacen.setSelectedIndex(0);
					break;
				
				case 2:
					
					SubVentanaAlmacen svAlmacen= new SubVentanaAlmacen();
					desktopPane.add(svAlmacen);
					svAlmacen.show();
					cbAlmacen.setSelectedIndex(0);
					break;
					
				case 3:
					
					SubVentanaInventario svInventario = new SubVentanaInventario();
					desktopPane.add(svInventario);
					svInventario.show();
					cbAlmacen.setSelectedIndex(0);
					break;

				}
				
			}
		}) ;
	
		/*
		 * Boton finazas
		 */
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			
				SubVentanaFinanzas svFinanzas = new SubVentanaFinanzas();
				desktopPane.add(svFinanzas);
				svFinanzas.show();
				
				
			}
		});
		
		/*
		 * Boton Caja
		 */

		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				SubVentanaCaja svCaja = new SubVentanaCaja();
				desktopPane.add(svCaja);
				svCaja.show();
				
			}
		});
		/*
		 * Boton cliente
		 */
		btnClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				SubVentanaCliente svCliente = new SubVentanaCliente();
				desktopPane.add(svCliente);
				svCliente.show();
				
			}
		});
		
	}
	
	
}
