/**
 * 
 */
package client;

import java.util.ArrayList;
import java.util.List;

import message.Message;



/**
 * @author Lwaxana
 *
 */
public class MyInfos {

	private String prenom;
	private String nom;
	private String nick;
	private String rue;
	private String numero;
	private String ville;
	private String zip;
	private String pays;
	private String email;
	private List<Droits> listedroits;
	
	public MyInfos(){
		listedroits = new ArrayList<>();
	}
	
	public void fillUserInfo(Message message){
		this.prenom = message.getPrenom();
		this.nom = message.getNom();
		this.nick = message.getNick();
		this.rue = message.getRue();
		this.numero = message.getNumero();
		this.ville = message.getVille();
		this.zip = message.getZip();
		this.pays = message.getPays();
		this.email = message.getEmail();
				
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [prenom=" + prenom + ", nom=" + nom + ", nick=" + nick
				+ ", rue=" + rue + ", numero=" + numero + ", ville=" + ville
				+ ", zip=" + zip + ", pays=" + pays + ", email=" + email + "]";
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public String getNick() {
		return nick;
	}

	public String getRue() {
		return rue;
	}

	public String getNumero() {
		return numero;
	}

	public String getVille() {
		return ville;
	}

	public String getZip() {
		return zip;
	}

	public String getPays() {
		return pays;
	}

	public String getEmail() {
		return email;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the listedroits
	 */
	public void ajouterDroits(String nomChannel, int droits){
		listedroits.add(new Droits(nomChannel, droits));
		System.out.println(listedroits);
	}

	/**
	 * @return the listedroits
	 */
	public List<Droits> getListedroits() {
		return listedroits;
	}

	/**
	 * @param listedroits the listedroits to set
	 */
	public void setListedroits(List<Droits> listedroits) {
		this.listedroits = listedroits;
	}
	
	

	
	
	
	
}
