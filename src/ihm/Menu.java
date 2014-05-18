/**
 * 
 */
package ihm;

import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.Control;

/**
 * @author Lwaxana
 *
 */
public class Menu extends JMenuBar {

	private JMenu fichier;
	private JMenu outils;
	private JMenu aide;
	private JMenuItem deconex;
	private JMenuItem exit;
	private JMenuItem params;
	private JMenuItem start;

	
	public Menu(){
		build();		
	}


	/**
	 * 
	 */
	public void build() {
		fichier = new JMenu("Fichier");
		outils = new JMenu("Outils");
		aide = new JMenu("Aide");		
		deconex = new JMenuItem("Deconnexion");
		exit = new JMenuItem("Exit");
		start = new JMenuItem("Start");	
		
		fichier.add(start);
		
		fichier.add(deconex);
		fichier.add(exit);		
		params = new JMenuItem("Parametres");		
		outils.add(params);		
		this.add(fichier);
		this.add(outils);
		this.add(aide);		
		exit.setActionCommand("exit");
		params.setActionCommand("params");		
		start.setActionCommand("start");
		
		deconex.setEnabled(false);
		
	}
	
	public void setListener(EventListener al){
		exit.addActionListener((ActionListener)al);
		params.addActionListener((ActionListener)al);
		start.addActionListener((ActionListener)al);
		
		
	}
	
	public void connect(){
		deconex.setEnabled(true);
		start.setEnabled(false);
	}
	
	public void disconnect(){
		deconex.setEnabled(false);
		
	}
	
}
