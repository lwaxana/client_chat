package ihm;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class PanelCheckOk extends JPanel {

	private String chemin = "src/img/checkmark2.png";
	private Image image;
	
	public PanelCheckOk() {
		super();
		image = getToolkit().getImage(chemin);	
	}
		
	public void paintComponent(Graphics g){
		g.drawImage(image, 0,0,23,20, this);
	}
}
