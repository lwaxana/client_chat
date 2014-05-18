/**
 * 
 */
package ihm;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameListener;

import typemessage.TypeMessage;
import listener.PopupListener;
import message.Utilisateur;
import control.Control;

/**
 * @author Lwaxana
 *
 */
public class InternalFrame extends JInternalFrame {
	
	private Pane pane;
	private JTextField textfield;
	private Control control;
	private String nomChannel;
	
	
	public InternalFrame(String nom, Control control){
		super(nom, true, true, true, true);
		this.setTitle(nom);
		this.nomChannel = nom;
		this.control = control;
		addInternalFrameListener((InternalFrameListener)control);
		this.setSize(new Dimension(800,600));	
		this.setLocation(100, 100);
		
		build();
		this.setVisible(true);
		
	}

	/**
	 * 
	 */
	private void build() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		int droits = 0;
		pane = new Pane(droits);	
		panel.add(pane);
		textfield = new JTextField();
		textfield.setSize(new Dimension(50,100));
		panel.add(textfield);
		this.add(panel);
		pane.setListener(control);
		textfield.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if ( textfield.isFocusOwner() && arg0.getKeyCode() == KeyEvent.VK_ENTER){
					control.envoyerMessageChannel(nomChannel, textfield.getText());
					textfield.setText("");					
				}
				
			}
		});
	}

	/**
	 * @return the nomChannel
	 */
	public String getNomChannel() {
		return nomChannel;
	}

	/**
	 * @param nomChannel the nomChannel to set
	 */
	public void setNomChannel(String nomChannel) {
		this.nomChannel = nomChannel;
	}
	
	public void setDoc(String txt, TypeMessage type){
		this.pane.setDoc(txt, type);
	}
	
	public void ajoutListeUser(Utilisateur[] liste){
		pane.ajouterListUser(liste);
	}
	
	public void ajouterUser(Utilisateur user){
		pane.ajouterUser(user);
	}
	
	public void retirerUser(Utilisateur user){
		pane.retirerUser(user);
	}
	
	public void changerNick(Utilisateur[] users){
		pane.changerNick(users);
	}
	
	public Utilisateur getSelectedUtilisateur(){
		return pane.getSelectedUtilisateur();
	}
	
	public void setdroits(int droits){
		pane.setDroits(droits);
	}
	
}
