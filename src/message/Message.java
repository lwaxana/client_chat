package message;

import java.io.Serializable;
import java.util.Arrays;

import typemessage.TypeMessage;

public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8006817488241702192L;
	private String message;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String rue;
	private String numero;
	private String ville;
	private String zip;
	private String pays;
	private String nick;
	private String nomsalon;
	private TypeMessage typeMessage;
	private String[] infos;
	private Utilisateur[] users;
	private int droits;
	private String oldnick;
	
	public Message(){
		
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the typeMessage
	 */
	public TypeMessage getTypeMessage() {
		return typeMessage;
	}

	/**
	 * @param typeMessage the typeMessage to set
	 */
	public void setTypeMessage(TypeMessage typeMessage) {
		this.typeMessage = typeMessage;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}

	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the nomsalon
	 */
	public String getNomsalon() {
		return nomsalon;
	}

	/**
	 * @param nomsalon the nomsalon to set
	 */
	public void setNomsalon(String nomsalon) {
		this.nomsalon = nomsalon;
	}

	

	/**
	 * @return the infos
	 */
	public String[] getInfos() {
		return infos;
	}

	/**
	 * @param infos the infos to set
	 */
	public void setInfos(String[] infos) {
		this.infos = infos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [message=" + message + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", password=" + password
				+ ", rue=" + rue + ", numero=" + numero + ", ville=" + ville
				+ ", zip=" + zip + ", pays=" + pays + ", nick=" + nick
				+ ", nomsalon=" + nomsalon + ", typeMessage=" + typeMessage
				+ ", infos=" + Arrays.toString(infos) + "]";
	}

	/**
	 * @return the users
	 */
	public Utilisateur[] getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Utilisateur[] users) {
		this.users = users;
	}

	/**
	 * @return the droits
	 */
	public int getDroits() {
		return droits;
	}

	/**
	 * @param droits the droits to set
	 */
	public void setDroits(int droits) {
		this.droits = droits;
	}

	/**
	 * @return the oldnick
	 */
	public String getOldnick() {
		return oldnick;
	}

	/**
	 * @param oldnick the oldnick to set
	 */
	public void setOldnick(String oldnick) {
		this.oldnick = oldnick;
	}

	

	
	
	

	

	

	
	
	
	
	
}
