/**
 * 
 */
package control;

import ihm.Fenetre;
import ihm.InscriptionFrame;
import ihm.InternalFrame;
import ihm.Parametres;
import ihm.WelcomeInscirptionIFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import message.Message;
import typemessage.TypeMessage;
import utils.UtilsValidation;
import client.Client;
import client.Droits;

/**
 * @author Lwaxana
 *
 */
public class Control implements ActionListener, WindowListener, InternalFrameListener{

	private Fenetre fen;
	private Client client;
	private Message message;
	
	
	public Control(){
		
	}
	
	
	
	/**
	 * @return the fen
	 */
	public Fenetre getFen() {
		return fen;
	}



	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}



	/**
	 * @param fen the fen to set
	 */
	public void setFen(Fenetre fen) {
		this.fen = fen;
	}



	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}



	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent arg0) {
		if ( client.isConnect()){
			client.deconnexion();	
		}
		else{
			System.exit(0);
		}
				
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//MENU OUTILS/PARAMETRES
		if ( arg0.getActionCommand() == "params"){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					fen.afficherParametres();	
					client.setParametres();
				}
			});
			
		}		
		//MENU FICHIER/EXIT
		if ( arg0.getActionCommand() == "exit"){
			System.exit(0);
		}
		
		//login welcomeFrame
		if (arg0.getActionCommand() =="loginwf"){
			if ( !client.isConnect()){
				
				SwingWorker sw = new SwingWorker(){

					@Override
					protected Object doInBackground() throws Exception {
						client.connect();						
						return null;					
					}
					
					public void done(){
						message = new Message();
						message.setTypeMessage(TypeMessage.ConnexionStart);
						client.envoyerMessage(message);			
					}				
				};
				sw.execute();
			}
			else if ( client.isConnect()){
				
				client.login();
			}
			else{
				System.out.println("rien");
			}
						
			
		}
		//continuer welcomeFrame
		if (arg0.getActionCommand() =="continuerwf"){
			WelcomeInscirptionIFrame wif = fen.getWf();
			if ( wif.validationOk()){
				if ( !fen.isShowInscriptFrame()){
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							String[] infos = fen.getInfosInscription();
							fen.closeWelcomeInscriptionFrame();
							fen.afficherInscriptionFrame();
							fen.setInfosInscrFrame(infos);						
						}
					});
				}
			}
			else{
				JOptionPane.showMessageDialog(fen.getFrame(), "Données incorrectes", "Alerte ", JOptionPane.OK_OPTION);
			}
			
		}
		if ( arg0.getActionCommand() == "deconnexion"){
			client.deconnexion();
		}
		
		if ( arg0.getActionCommand() == "inscriptionannuler"){
			
		}
		
		if ( arg0.getActionCommand() == "inscriptionvalider"){
			InscriptionFrame iF = fen.getInscrFrame();
			if ( iF.validationOk()){
				SwingWorker sw = new SwingWorker(){

					@Override
					protected Object doInBackground() throws Exception {
						client.connect();
						return null;
					}
					
					public void done(){
						message = new Message();
						message.setTypeMessage(TypeMessage.ConnexionStart);
						client.envoyerMessage(message);
						client.setInscr(true);
					}
					
				};
				if ( !client.isConnect()){
					sw.execute();
				}
				else{
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							fen.writeWelcomeIFrame("Déja connecté");
							
						}
					});
				}					
			}
			else{
				JOptionPane.showMessageDialog(fen.getFrame(), "y'a une saquée ki n'va nié biloute", "Alerte ", JOptionPane.OK_OPTION);
			}
			
			
			
		}
		// OUTILS // PARAMETRES // INFO SERVEUR
		if ( arg0.getActionCommand() == "ok_serv"){
			client.connexionInfos();
		}
		// OUTILS // PARAMETRES // ANNULER 
		if ( arg0.getActionCommand() == "annuler_infos"){
			
		}
		//OUTILS // PARAMETRES // MES INFOS // NICK
		if (arg0.getActionCommand() == "changer_nick"){
			Parametres p = fen.getParametres();
			while (!UtilsValidation.checkNick(p.getTxt_nick())){
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						JOptionPane.showMessageDialog(fen.getFrame(), "Erreur nick", "Alerte ", JOptionPane.OK_OPTION);
						
					}
				});
				
				
			}
			client.changerNick(p.getTxt_nick());
		}
		// OUTILS // PARAMETRES // MES INFOS // MAJ
		if ( arg0.getActionCommand() == "maj_infos"){
			
		}
		//POPUP MESSAGE PRIVE 
		if ( arg0.getActionCommand() == "message_prive"){
		
		}
		//POPUP CONVERSATION PRIVEE
		if ( arg0.getActionCommand() == "conv_prive"){
			
		}
		if ( arg0.getActionCommand() == "start"){
			if ( !fen.isShowWIIF()){
				fen.afficherWIF();
			}
			
		}
		
	}

	
	public void selectChannel(String str){
		client.rejoindreChannel(str);
	}

	public void envoyerMessageChannel(String nomChannel, String txt){
		if ( UtilsValidation.checkMessage(txt)){
			client.envoyerMessageChannel(nomChannel, txt);
		}
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		
		if ( e.getInternalFrame() instanceof InscriptionFrame){
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					fen.setShowInscriptFrame(false);
					
				}
			});
			
		}
		if ( e.getInternalFrame() instanceof InternalFrame){
			InternalFrame iF = (InternalFrame) e.getInternalFrame();
			client.leavechannel(iF.getNomChannel());
		}
		if (e.getInternalFrame() instanceof WelcomeInscirptionIFrame){
			fen.setShowWIIF(false);
		}
	}



	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public int getDroits(String nomChannel){
		List<Droits> listedroits = client.getUser().getListedroits();
		System.out.println("control" +listedroits);
		int droit = 0;
		for ( Droits d : listedroits){
			if ( d.getNomChannel().equals(nomChannel)){
				droit = d.getDroits();
				System.out.println("droit " + droit + " chann "+nomChannel);
			}
		}
		return droit;
	}

	
}
