package erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SubVentanaAlbaran extends JInternalFrame {

	private static final long serialVersionUID = -6590411417912431894L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				String albaran = null;
				try {
					SubVentanaAlbaran frame = new SubVentanaAlbaran(albaran);
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
	public SubVentanaAlbaran(String albaran) {
		setBounds(100, 100, 488, 642);
		setClosable(true);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 11, 452, 590);
		panel.add(textArea);
		
		textArea.setText(albaran);
		

	}
}
