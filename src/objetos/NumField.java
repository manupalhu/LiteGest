package objetos;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class NumField extends JTextField {

	private static final long serialVersionUID = 8012773039617714679L;

	public NumField() {

		addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != '\b') && (c != '\n')&&(c!='.')&&(c!='-')) {

					e.consume();
				}
			}
		});
		
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				if (getText().isEmpty()) setText("0");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				selectAll();
				
			}
		});

	}

	

}
