/**
 * 
 */
package ihm;

import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.util.EventListener;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import typemessage.TypeMessage;
import message.Utilisateur;
import control.Control;

/**
 * @author Lwaxana
 *
 */
public class Fenetre  {

	private JFrame frame;
	private JDesktopPane desktop;
	private Menu menu;
	private Control control;
	private Parametres parametres;
	private WelcomeInscirptionIFrame wf;
	private WelcomeInternalFrame wif;
	private Infos2 infos2;
	private InscriptionFrame inscripframe;
	private boolean showInscriptFrame;
	private int droits;
	private boolean showWIIF;
	
	public Fenetre(){
		
		frame = new JFrame();
		frame.setTitle("Chat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		
		
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		build();	
		desktop = new JDesktopPane();
		/*
		//Trouvé sur le net // Empeche l'internal frame de sortir du desktop pane
		DesktopManager manager = new DefaultDesktopManager() {
	        
	        @Override
	        public void setBoundsForFrame(JComponent f, int newX, int newY, int newWidth, int newHeight) {
	            boolean didResize = (f.getWidth() != newWidth || f.getHeight() != newHeight);
	            if (!inBounds((JInternalFrame) f, newX, newY, newWidth, newHeight)) return;
	            f.setBounds(newX, newY, newWidth, newHeight);
	            if(didResize) {
	                f.validate();
	            } 
	        }

	        protected boolean inBounds(JInternalFrame f, int newX, int newY, int newWidth, int newHeight) {
	            if (newX < 0 || newY < 0) return false;
	            if (newX + newWidth > f.getDesktopPane().getWidth()) return false;
	            if (newY + newHeight > f.getDesktopPane().getHeight()) return false;
	            return true;
	        }

	    };
	    
	    desktop.setDesktopManager(manager);
	    */
		panel.add(desktop);
	
		frame.setContentPane(panel);
		desktop.add(wf);
		desktop.add(wif);	
	
		desktop.add(infos2);
		
		
		wf.moveToFront();
		showWIIF = true;	
		
	}
	/**
	 * 
	 */
	private void build() {
		menu = new Menu();
		frame.setJMenuBar(menu);
		menu.disconnect();
		wf = new WelcomeInscirptionIFrame();
		wif = new WelcomeInternalFrame("Welcome");
		infos2 = new Infos2();
		
	}
	
		
	/**
	 * 
	 * @param nom
	 */
	public void ajouterFrame(String nom){
		desktop.add(new InternalFrame(nom, control));
		
		
	}
		
	/**
	 * 
	 */
	public InternalFrame getInternalFrame(String nomChannel){
		InternalFrame iF = null;
		JInternalFrame[] jifs = desktop.getAllFrames();
		for ( int i = 0; i < jifs.length; i++){
			if ( jifs[i] instanceof InternalFrame ){
				if ( ((InternalFrame)jifs[i]).getNomChannel().equals(nomChannel) ){
					iF = (InternalFrame)jifs[i];
				}
			}
		}
		System.out.println(iF.getNomChannel());
		return iF;
		
	}
	
	/**
	 * 
	 * @param al
	 */
	public void setListener(EventListener al){
		menu.setListener(al);
		frame.addWindowListener((WindowListener)al);
		wf.setListener(al);
	}
	
	/**
	 * 
	 */
	public void setcontrol(Control ctrl){
		this.control = ctrl;
		infos2.setControl(ctrl);
	}
	
	/**
	 * 
	 */
	public void afficher(){
		frame.setVisible(true);
	}
		
	public void afficherParametres(){
		parametres = new Parametres("Parametres");
		parametres.setListener(control);
		desktop.add(parametres);
	}
		
	/**
	 * 
	 */
	public void fermerFrame(){
		JInternalFrame jif = desktop.getSelectedFrame();
		jif.dispose();
	}
	
	public void activerOptionConnect(){
		menu.connect();
	}
	
	public void desactiverOptionConnect(){
		menu.disconnect();
	}
	
	

	public void writeWelcomeIFrame(String str){
		wif.setText(str);
	}
	
	public void InfoServeur(String[] liste){
		infos2.infosServeur(liste);
	}
	
	public void InfoServeurAjouterChannel(String[] liste){
		infos2.ajouterChannelTree(liste);
	}
	
	public void ajouterMessageIFrame(String nomChannel, String txt, TypeMessage type){
		InternalFrame iF = getInternalFrame(nomChannel);
		iF.setDoc(txt, type);
	}
	
	public void ajouterListUserChannel(String nomChannel, Utilisateur[] liste){
		InternalFrame iF = getInternalFrame(nomChannel);
		iF.ajoutListeUser(liste);
	}
	
	public void ajouterListUserChannel(String nomChannel, Utilisateur user){
		InternalFrame iF = getInternalFrame(nomChannel);
		iF.ajouterUser(user);
	}
	
	
	public void closeWelcomeInscriptionFrame(){
		wf.dispose();
		showWIIF = false;
	}
	
	
	public void afficherInscriptionFrame(){
		inscripframe = new InscriptionFrame();
		inscripframe.setListener(control);
		desktop.add(inscripframe);
		inscripframe.moveToFront();
		showInscriptFrame = true;
		
		
	}

	/**
	 * @return the showInscriptFrame
	 */
	public boolean isShowInscriptFrame() {
		return showInscriptFrame;
	}

	/**
	 * @param showInscriptFrame the showInscriptFrame to set
	 */
	public void setShowInscriptFrame(boolean showInscriptFrame) {
		this.showInscriptFrame = showInscriptFrame;
	}
	
	public void selectWIIF(){
		try {
			wf.setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public String[] getInfosInscription(){
		String[] infos;
		infos = new String[3];
		infos[0] = wf.getTxtPrnom();
		infos[1] = wf.getTxtNomDeFamille();
		infos[2] = wf.getTxtEmailUsername();
		return infos;
	}
	
	public void setInfosInscrFrame(String[] liste){
		inscripframe.setTextField_nom(liste[1]);
		inscripframe.setTextField_prenom(liste[0]);
		inscripframe.setTextField_uname(liste[2]);
	}
	
	public InscriptionFrame getInscrFrame(){
		return inscripframe;
	}
		
	
	public void fermerInscrFrame(){
		inscripframe.dispose();
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public void retirerUtilisateur(String nomChannel, Utilisateur user){
		InternalFrame iF = getInternalFrame(nomChannel);
		iF.retirerUser(user);
	}

	/**
	 * @return the parametres
	 */
	public Parametres getParametres() {
		return parametres;
	}
	
	public void changerNick(String nom, Utilisateur[] users){
		InternalFrame iF = getInternalFrame(nom);
		iF.changerNick(users);
	}
	
	public Utilisateur getSelectedUtilisateur(){
		InternalFrame iF = (InternalFrame) desktop.getSelectedFrame();
		return iF.getSelectedUtilisateur();
	}

	/**
	 * @return the wf
	 */
	public WelcomeInscirptionIFrame getWf() {
		return wf;
	}

	

	public void afficherWIF(){
		if ( !showWIIF){
			wf = new WelcomeInscirptionIFrame();
			wf.setListener(control);
			desktop.add(wf);
			wf.moveToFront();
		}
	}

	/**
	 * @param showWIIF the showWIIF to set
	 */
	public void setShowWIIF(boolean showWIIF) {
		this.showWIIF = showWIIF;
	}

	/**
	 * @return the showWIIF
	 */
	public boolean isShowWIIF() {
		return showWIIF;
	}
	
	

		

	
	
}




