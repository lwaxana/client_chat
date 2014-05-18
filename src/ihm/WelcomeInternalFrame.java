/**
 * 
 */
package ihm;

import java.awt.Dimension;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Lwaxana
 *
 */
public class WelcomeInternalFrame extends JInternalFrame {
	
	private Pane2 pane;
	private JTextField textfield;
	
	public WelcomeInternalFrame(String nom){
		super(nom, true, false, false, true);
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		setBounds(width-500, height-400, 500, 300);
		build();
		this.setVisible(true);
		
	}

	public void build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		pane = new Pane2();	
		panel.add(pane);
		textfield = new JTextField();
		textfield.setSize(new Dimension(50,100));
		panel.add(textfield);
		this.add(panel);	
		
	}
	
	public void setText(String str){
		pane.setDoc(str);
	}
	
	

}
