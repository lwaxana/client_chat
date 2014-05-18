/**
 * 
 */
package client;

import ihm.Fenetre;
import ihm.InscriptionFrame;
import ihm.Parametres;
import ihm.WelcomeInscirptionIFrame;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import message.Message;
import message.Utilisateur;
import typemessage.TypeMessage;
import utils.UtilsMessage;
import utils.UtilsSHA256pwd;
import utils.UtilsValidation;
import comm.Read;

/**
 * @author Lwaxana
 *
 */
public class Client implements Observer  {

	private Socket socket;
	private Read read;
	private int port;
	private String host;
	private SSLSocket sslsocket;
	private Message message;
	private boolean connect;
	private boolean auth;
	private Fenetre fenetre;
	private boolean sslup;
	private MyInfos user;
	private TypeMessage typemessage;	
	private BufferedWriter bw;
	private Socket currentsocket;
	
	private boolean inscr = false;
	private boolean nickok = false;
	

	public Client(){
		connect = false;
		auth = false;
		sslup = false;
	}


	public void connect(){
		try {
			socket = new Socket(host,port);
			currentsocket = socket;
			bw = new BufferedWriter(new OutputStreamWriter(currentsocket.getOutputStream()));
			read = new Read(currentsocket);
			read.addObserver(this);
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					fenetre.writeWelcomeIFrame("Connection serveur "+socket.getInetAddress()+" acceptée" );						
				}
			});				
			Thread t = new Thread(read);
			t.start();
			connect = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			fenetre.writeWelcomeIFrame("Serveur injoignable");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			fenetre.writeWelcomeIFrame("Serveur injoignable");
		}					
	}

	/**
	 * 
	 */
	public void envoyerMessage(Message message){
		try {
			String tosend = UtilsMessage.toString(message);
			bw.write(tosend+'\n');
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void upgradeToSSL(){
		read.close();
		System.setProperty("javax.net.ssl.trustStore", "c:/serveur/cacerts");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");		
		System.setProperty("javax.net.ssl.keyStore", "c:/serveur/mykeystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		TrustManager[] trustAllCerts = { new X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
		} };
		SSLContext context = null;
		try {
			context = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			context.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
		sslsocket = null;
		try {
			sslsocket = (SSLSocket)sslSocketFactory.createSocket(socket,socket.getInetAddress().getHostAddress(),socket.getPort(),false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sslsocket.setUseClientMode(true);
		try {
			sslsocket.startHandshake();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentsocket = sslsocket;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(currentsocket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		read = new Read(currentsocket);
		read.addObserver(this);
		Thread t = new Thread(read);
		t.start();
		sslup = true;
	}

	/**
	 * 
	 */
	public void downSSL(){
		read.close();
		currentsocket = socket;
		try {
			sslsocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bw = new BufferedWriter(new OutputStreamWriter(currentsocket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		read = new Read(currentsocket);
		read.addObserver(this);
		sslup = false;
		Thread t = new Thread(read);
		t.start();

	}

	/**
	 * 
	 */
	public void traiterMessage(final Message message){
		typemessage = message.getTypeMessage();

		if ( typemessage == TypeMessage.ConnexionOk){
			connect = true;
			if (inscr){
				inscription();
			}
			else{
				login();
			}
		}

		if ( typemessage == TypeMessage.UserInfo){
			user = new MyInfos();
			user.fillUserInfo(message);
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					fenetre.writeWelcomeIFrame("Bienvenue "+user.getPrenom()+ " "+user.getNom());
					

				}
			});			
		}


		if ( typemessage == TypeMessage.Authentificationok){
			    auth = true;				
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						fenetre.writeWelcomeIFrame("Login succesfull");
						fenetre.closeWelcomeInscriptionFrame();
						String s = (String)JOptionPane.showInputDialog(fenetre.getFrame(), "Entrez un nick : ( 4-20) caracteres ", "Entrez votre pseudo", JOptionPane.QUESTION_MESSAGE);
						while ( !UtilsValidation.checkNick(s)){
							JOptionPane.showMessageDialog(fenetre.getFrame(), "Erreur nick", "Alerte ", JOptionPane.OK_OPTION);
							s = (String)JOptionPane.showInputDialog(fenetre.getFrame(), "Entrez un nick : ( 4-20) caracteres ", "Entrez votre pseudo", JOptionPane.QUESTION_MESSAGE);
							System.out.println(s);
						}
						changerNick(s);
						
					}
				});
		}

		if ( typemessage == TypeMessage.AuthentificationFail){			
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						fenetre.writeWelcomeIFrame("Echec Login");
						fenetre.writeWelcomeIFrame("Raison : "+ message.getMessage());					
					}
				});			
		}


		if ( typemessage == TypeMessage.ServerInfo){
			final String[] liste = message.getInfos();
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					fenetre.InfoServeur(liste);

				}
			});			
		}


		if ( typemessage == TypeMessage.ServerInfoChannel){
			final String[] liste = message.getInfos();
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					fenetre.InfoServeurAjouterChannel(liste);

				}
			});			
		}

		if ( typemessage == TypeMessage.JoinChannelOk){
			final String nomChannel = message.getNomsalon();
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					fenetre.ajouterFrame(nomChannel);

				}
			});
		}

		if ( typemessage == TypeMessage.MessageChannel){
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					fenetre.ajouterMessageIFrame(message.getNomsalon(), message.getMessage(), typemessage);

				}
			});
		}

		if ( typemessage == TypeMessage.ChannelUserList){
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					int droits = 0;
					if ( message.getUsers().length > 1){
						for (Utilisateur u : message.getUsers()){
							if ( u.getNom().equals(user.getNick())){
								user.ajouterDroits(message.getNomsalon(), u.getType());
								droits = u.getType();
							}
						}
						fenetre.ajouterListUserChannel(message.getNomsalon(), message.getUsers());
						fenetre.getInternalFrame(message.getNomsalon()).setdroits(droits);
					}
					if ( message.getUsers().length == 1){
						for (Utilisateur u : message.getUsers()){
							if ( u.getNom().equals(user.getNick())){
								user.ajouterDroits(message.getNomsalon(), u.getType());
								droits = u.getType();
							}
						}
						fenetre.ajouterListUserChannel(message.getNomsalon(), message.getUsers()[0]);
						fenetre.getInternalFrame(message.getNomsalon()).setdroits(droits);
					}


				}
			});
		}

		if ( typemessage == TypeMessage.NickUsed){
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					String s = (String)JOptionPane.showInputDialog(fenetre.getFrame(), "Entrez un nick :", "Pseudo déjà utilisé", JOptionPane.WARNING_MESSAGE);
					while ( !UtilsValidation.checkNick(s)){
						JOptionPane.showMessageDialog(fenetre.getFrame(), "Erreur nick", "Alerte ", JOptionPane.OK_OPTION);
						s = (String)JOptionPane.showInputDialog(fenetre.getFrame(), "Entrez un nick :", "Pseudo déjà utilisé", JOptionPane.WARNING_MESSAGE);
					}
					changerNick(s);
				}
			});
		}

		if ( typemessage == TypeMessage.NewUserOk){
			inscr = false;
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					fenetre.fermerInscrFrame();
					fenetre.writeWelcomeIFrame("L'inscription s'est déroulée sans problèmes. Login plz");
					fenetre.afficherWIF();
				}
			});
		}

		if ( typemessage == TypeMessage.ChannelLeaveUser){
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					fenetre.retirerUtilisateur(message.getNomsalon(), message.getUsers()[0]);

				}
			});
		}

		if ( typemessage == TypeMessage.ChannelAddUser){
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					Utilisateur u = message.getUsers()[0];
					fenetre.ajouterListUserChannel(message.getNomsalon(), message.getUsers()[0]);

				}
			});
		}
		if ( typemessage == TypeMessage.NickOk){
			this.nickok = true;
			user.setNick(message.getNick());
		}
		if ( typemessage == TypeMessage.ChangementNickUser){
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					fenetre.changerNick(message.getNomsalon(), message.getUsers());
					fenetre.ajouterMessageIFrame(message.getNomsalon(), message.getMessage(), typemessage);

				}
			});
		}


	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		if ( o instanceof Read){
			message = (Message)arg;
			traiterMessage(message);
		}

	}
	/**
	 * 
	 * @param fen
	 */
	public void setFenetre(Fenetre fen){
		this.fenetre = fen;
	}

	/**
	 * 
	 */
	public void connected(){
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				fenetre.activerOptionConnect();

			}
		});

	}

	/**
	 * 
	 * @param login
	 * @param pwd
	 */
	public void login(){
		WelcomeInscirptionIFrame wif = fenetre.getWf();
		if ( connect ){
			message = new Message();
			message.setTypeMessage(TypeMessage.Authentification);
			message.setEmail(wif.getTextField());
			message.setPassword(UtilsSHA256pwd.encoderMotDePasse(wif.getPasswordField()));
			envoyerMessage(message);	
		}
		else{
			connect();
			message = new Message();
			message.setTypeMessage(TypeMessage.Authentification);
			message.setEmail(wif.getTextField());
			message.setPassword(UtilsSHA256pwd.encoderMotDePasse(wif.getPasswordField()));
			envoyerMessage(message);	

		}

	}

	public void deconnexion(){
		message = new Message();
		message.setTypeMessage(TypeMessage.ConnexionStop);
		envoyerMessage(message);
		if ( connect ){
			try {
				socket.close();
				currentsocket.close();
				connect = false;
				fenetre.desactiverOptionConnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public boolean isConnect() {
		return connect;
	}

	public void rejoindreChannel(String str){
		if ( nickok){
			message = new Message();
			message.setTypeMessage(TypeMessage.JoinChannel);
			message.setNomsalon(str);
			envoyerMessage(message);	
		}

	}

	public void envoyerMessageChannel(String nomChannel, String txt){
		message = new Message();
		message.setTypeMessage(TypeMessage.MessageChannel);
		message.setNomsalon(nomChannel);
		message.setMessage(txt);
		envoyerMessage(message);

	}

	/**
	 * @param inscr the inscr to set
	 */
	public void setInscr(boolean inscr) {
		this.inscr = inscr;
	}


	public void inscription(){
		InscriptionFrame iF = fenetre.getInscrFrame();
		message = new Message();
		message.setTypeMessage(TypeMessage.NewUser);
		message.setEmail(iF.getTextField_uname());
		message.setNom(iF.getTextField_nom());
		message.setPrenom(iF.getTextField_prenom());
		message.setPassword(UtilsSHA256pwd.encoderMotDePasse(iF.getPasswordField()));
		message.setRue(iF.getTextField_rue());
		message.setNumero(iF.getTextField_num());
		message.setZip(iF.getTextField_cp());
		message.setVille(iF.getTextField_ville());
		message.setPays(iF.getPays());

		System.out.println(message);
		envoyerMessage(message);
	}

	/**
	 * 
	 * @param str - Envoi demande de Change pseudo
	 */
	public void changerNick(String str){
		message = new Message();
		message.setTypeMessage(TypeMessage.ChangerNick);
		message.setNick(str);
		envoyerMessage(message);
	}
	
	
	
	/**
	 * @return the user
	 */
	public MyInfos getUser() {
		return user;
	}


	/**
	 * 
	 * @param nomSalon - Envoi demande Quitter un channel
	 */
	public void leavechannel(String nomSalon){
		message = new Message();
		message.setTypeMessage(TypeMessage.LeaveChannel);
		message.setNomsalon(nomSalon);
		envoyerMessage(message);
	}

	/**
	 * Rempli les champs de la fenetre parametres / mes infos
	 */
	public void setParametres(){
		if ( auth ){
			Parametres p = fenetre.getParametres();
			p.setTxt_nick(user.getNick());
			p.setTxt_nom(user.getNom());
			p.setTxt_prenom(user.getPrenom());
			p.setTxt_rue(user.getRue());
			p.setTxt_numero(user.getNumero());
			p.setTxt_cp(user.getZip());
			p.setTxt_ville(user.getVille());
			p.setPays(user.getPays());
		}

	}

	/**
	 * Récupère les infos de connection vers le serveur ( IP + PORT ) + VALIDATION
	 */
	public void connexionInfos(){
		final Parametres p = fenetre.getParametres();
		host = p.getTxt_IP();
		String port2 = p.getTxt_port();
		String regexIP = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
		String regexPort = "\\d{1,5}";
		Pattern pattern = Pattern.compile(regexIP);
		Matcher matcher = pattern.matcher(host);
		Pattern pattern2 = Pattern.compile(regexPort);
		Matcher matcher2 = pattern2.matcher(port2);
		if ( matcher.matches()){
			if ( matcher2.matches()){
				port = Integer.parseInt(port2);
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						p.dispose();
					}
				});
			}
			else{
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						JOptionPane.showMessageDialog(fenetre.getFrame(), "Port incorrect", "Alerte ", JOptionPane.OK_OPTION);
						Parametres p = fenetre.getParametres();
						p.setTxt_port("");
						p.focusPort();

					}
				});
			}

		}else{
			if ( matcher2.matches()){
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						JOptionPane.showMessageDialog(fenetre.getFrame(), "IP incorrecte", "Alerte ", JOptionPane.OK_OPTION);
						Parametres p = fenetre.getParametres();
						p.setTxt_IP("");
						p.focusIP();
					}
				});
			}
			else{
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						JOptionPane.showMessageDialog(fenetre.getFrame(), "Infos incorrectes", "Alerte ", JOptionPane.OK_OPTION);
						Parametres p = fenetre.getParametres();
						p.setTxt_IP("");
						p.setTxt_port("");
						p.focusIP();
					}
				});
			}
			
		}



	}
	
	


}

