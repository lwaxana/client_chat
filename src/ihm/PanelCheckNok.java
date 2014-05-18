package ihm;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class PanelCheckNok extends JPanel {

	private String chemin = "src/img/xcheck.png";
	private Image image;
	
	public PanelCheckNok(){
		super();
		image = getToolkit().getImage(chemin);
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(image, 0,0,23,20, this);
	}
}
