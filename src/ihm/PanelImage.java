package ihm;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class PanelImage extends JPanel {

	private String chemin = "src/img/icon_kitty.jpg";
	private Image image;
	
	public PanelImage(){
		super();
		image = getToolkit().getImage(chemin);		
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(image, 0,0,50,50, this);
	}
}
