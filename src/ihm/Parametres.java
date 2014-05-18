/**
 * 
 */
package ihm;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EventListener;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

/**
 * @author Lwaxana
 *
 */
public class Parametres extends JInternalFrame {

	private JTabbedPane tabbedpane;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField txt_IP;
	private JButton btn_ok_serv;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel_1;
	private JTextField txt_nick;
	private JButton btn_changer_nick;
	private JPanel panel_3;
	private JLabel lblNewLabel_2;
	private JPasswordField passwordField;
	private JTextField txt_prenom;
	private JTextField txt_nom;
	private JTextField txt_rue;
	private JTextField txt_numero;
	private JTextField txt_cp;
	private JTextField txt_ville;
	private Set<String> pays;
	private JComboBox<String> comboBox;
	private JLabel lblNewLabel_10;
	private JTextField txt_port;
	private JButton btn_maj_infos;
	private JButton btn_annuler_infos;
	private JButton btn_changer_pwd;
	
	
	
	public Parametres(String nom){
		super(nom);
		setSize(500,500);
		setResizable(false);
		setClosable(true);
		setIconifiable(true);
		build();
		setVisible(true);
	}
	
	public void build(){
		tabbedpane = new JTabbedPane();
		buildPanel1();
		buildPanel2();
		buildPanel3();
		tabbedpane.add("Serveur",panel1);
		panel1.setLayout(null);
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "IP Connexion Serveur", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 21, 447, 159);
			panel1.add(panel);
			panel.setLayout(null);
			{
				lblNewLabel = new JLabel("IP Serveur :");
				lblNewLabel.setBounds(33, 39, 92, 14);
				panel.add(lblNewLabel);
			}
			{
				txt_IP = new JTextField();
				txt_IP.setBounds(120, 36, 169, 20);
				panel.add(txt_IP);
				txt_IP.setColumns(15);
			}
			{
				btn_ok_serv = new JButton("Ok");
				btn_ok_serv.setBounds(208, 108, 89, 23);
				panel.add(btn_ok_serv);
			}
			
			lblNewLabel_10 = new JLabel("Port :");
			lblNewLabel_10.setBounds(33, 66, 46, 14);
			panel.add(lblNewLabel_10);
			
			txt_port = new JTextField();
			txt_port.setBounds(120, 64, 86, 20);
			panel.add(txt_port);
			txt_port.setColumns(10);
		}
		tabbedpane.add("Mes infos",panel2);
		panel2.setLayout(null);
		{
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Mes Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(10, 11, 459, 421);
			panel2.add(panel_1);
			panel_1.setLayout(null);
			{
				panel_2 = new JPanel();
				panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Mon nick", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_2.setBounds(10, 22, 439, 55);
				panel_1.add(panel_2);
				panel_2.setLayout(null);
				{
					lblNewLabel_1 = new JLabel("Mon nick :");
					lblNewLabel_1.setBounds(22, 20, 53, 14);
					panel_2.add(lblNewLabel_1);
				}
				{
					txt_nick = new JTextField();
					txt_nick.setBounds(85, 17, 172, 20);
					panel_2.add(txt_nick);
					txt_nick.setColumns(20);
					txt_nick.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent arg0) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub
							
						}
					});
				}
				{
					btn_changer_nick = new JButton("Changer");
					btn_changer_nick.setBounds(287, 16, 89, 23);
					panel_2.add(btn_changer_nick);
				}
			}
			{
				panel_3 = new JPanel();
				panel_3.setBorder(new TitledBorder(null, "Mot de passe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_3.setBounds(10, 81, 439, 73);
				panel_1.add(panel_3);
				panel_3.setLayout(null);
				{
					lblNewLabel_2 = new JLabel("Nouveau mot de passe");
					lblNewLabel_2.setBounds(10, 18, 120, 14);
					panel_3.add(lblNewLabel_2);
				}
				
				passwordField = new JPasswordField();
				passwordField.setBounds(25, 37, 231, 20);
				panel_3.add(passwordField);
				
				btn_changer_pwd = new JButton("Changer");
				btn_changer_pwd.setBounds(287, 34, 89, 23);
				panel_3.add(btn_changer_pwd);
			}
			
			JPanel panel_4 = new JPanel();
			panel_4.setBorder(new TitledBorder(null, "Mes infos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_4.setBounds(10, 165, 439, 245);
			panel_1.add(panel_4);
			panel_4.setLayout(null);
			
			JLabel lblNewLabel_3 = new JLabel("Prenom :");
			lblNewLabel_3.setBounds(10, 25, 46, 14);
			panel_4.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Nom :");
			lblNewLabel_4.setBounds(10, 50, 46, 14);
			panel_4.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Rue :");
			lblNewLabel_5.setBounds(10, 75, 46, 14);
			panel_4.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("Numéro :");
			lblNewLabel_6.setBounds(10, 100, 46, 14);
			panel_4.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("CP :");
			lblNewLabel_7.setBounds(10, 125, 46, 14);
			panel_4.add(lblNewLabel_7);
			
			JLabel lblNewLabel_8 = new JLabel("Ville :");
			lblNewLabel_8.setBounds(10, 150, 46, 14);
			panel_4.add(lblNewLabel_8);
			
			JLabel lblNewLabel_9 = new JLabel("Pays :");
			lblNewLabel_9.setBounds(10, 175, 46, 14);
			panel_4.add(lblNewLabel_9);
			
			btn_maj_infos = new JButton("Mise à jour");
			btn_maj_infos.setBounds(223, 211, 89, 23);
			panel_4.add(btn_maj_infos);
			
			btn_annuler_infos = new JButton("Annuler");
			btn_annuler_infos.setBounds(327, 211, 89, 23);
			panel_4.add(btn_annuler_infos);
			
			txt_prenom = new JTextField();
			txt_prenom.setBounds(77, 22, 284, 20);
			panel_4.add(txt_prenom);
			txt_prenom.setColumns(10);
			
			txt_nom = new JTextField();
			txt_nom.setBounds(77, 47, 284, 20);
			panel_4.add(txt_nom);
			txt_nom.setColumns(10);
			
			txt_rue = new JTextField();
			txt_rue.setBounds(77, 72, 284, 20);
			panel_4.add(txt_rue);
			txt_rue.setColumns(10);
			
			txt_numero = new JTextField();
			txt_numero.setBounds(77, 97, 86, 20);
			panel_4.add(txt_numero);
			txt_numero.setColumns(10);
			
			txt_cp = new JTextField();
			txt_cp.setBounds(77, 122, 86, 20);
			panel_4.add(txt_cp);
			txt_cp.setColumns(10);
			
			txt_ville = new JTextField();
			txt_ville.setBounds(77, 147, 193, 20);
			panel_4.add(txt_ville);
			txt_ville.setColumns(10);
			
			comboBox = new JComboBox<String>();
			comboBox.setBounds(77, 172, 193, 20);
			panel_4.add(comboBox);
			
			pays = new TreeSet<>();
			String fichier ="src/lib/pays.txt";
			InputStream ips = null;
			BufferedReader br1 = null;
			try {
				ips = new FileInputStream(fichier);
				InputStreamReader ipsr=new InputStreamReader(ips);
				br1=new BufferedReader(ipsr);
				String ligne;
				while ((ligne=br1.readLine())!=null){
					pays.add(ligne.trim());				
				}
				br1.close();
				ipsr.close();
				ips.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for ( String s : pays){
				comboBox.addItem(s);
			}
		}
		tabbedpane.add("N/A",panel3);
		getContentPane().add(tabbedpane);
		
		txt_IP.setToolTipText("IP du serveur XXX.XXX.XXX.XXX");
		txt_port.setToolTipText("Port du serveur XXXXX");
		
		btn_annuler_infos.setActionCommand("annuler_infos");
		btn_changer_nick.setActionCommand("changer_nick");
		btn_maj_infos.setActionCommand("maj_infos");
		btn_ok_serv.setActionCommand("ok_serv");
		
		tabbedpane.setEnabledAt(2, false);
	}
	
	public JPanel buildPanel1(){
		panel1 = new JPanel();
		return panel1;
	}
	
	public JPanel buildPanel2(){
		panel2 = new JPanel();
		return panel2;
	}
	
	public JPanel buildPanel3(){
		panel3 = new JPanel();
		return panel3;
	}

	/**
	 * @return the txt_nick
	 */
	public String getTxt_nick() {
		return txt_nick.getText();
	}

	/**
	 * @return the passwordField
	 */
	public char[] getPasswordField() {
		return passwordField.getPassword();
	}

	/**
	 * @return the txt_prenom
	 */
	public String getTxt_prenom() {
		return txt_prenom.getText();
	}

	/**
	 * @return the txt_nom
	 */
	public String getTxt_nom() {
		return txt_nom.getText();
	}

	/**
	 * @return the txt_rue
	 */
	public String getTxt_rue() {
		return txt_rue.getText();
	}

	/**
	 * @return the txt_numero
	 */
	public String getTxt_numero() {
		return txt_numero.getText();
	}

	/**
	 * @return the txt_cp
	 */
	public String getTxt_cp() {
		return txt_cp.getText();
	}

	/**
	 * @return the txt_ville
	 */
	public String getTxt_ville() {
		return txt_ville.getText();
	}
	
	public String getPays(){
		return comboBox.getSelectedItem().toString();
	}

	/**
	 * @param txt_nick the txt_nick to set
	 */
	public void setTxt_nick(String txt_nick) {
		this.txt_nick.setText(txt_nick);
	}

	/**
	 * @param passwordField the passwordField to set
	 */
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	/**
	 * @param txt_prenom the txt_prenom to set
	 */
	public void setTxt_prenom(String txt_prenom) {
		this.txt_prenom.setText(txt_prenom);
	}

	/**
	 * @param txt_nom the txt_nom to set
	 */
	public void setTxt_nom(String txt_nom) {
		this.txt_nom.setText(txt_nom);
	}

	/**
	 * @param txt_rue the txt_rue to set
	 */
	public void setTxt_rue(String txt_rue) {
		this.txt_rue.setText(txt_rue);
	}

	/**
	 * @param txt_numero the txt_numero to set
	 */
	public void setTxt_numero(String txt_numero) {
		this.txt_numero.setText(txt_numero);
	}

	/**
	 * @param txt_cp the txt_cp to set
	 */
	public void setTxt_cp(String txt_cp) {
		this.txt_cp.setText(txt_cp);
	}

	/**
	 * @param txt_ville the txt_ville to set
	 */
	public void setTxt_ville(String txt_ville) {
		this.txt_ville.setText(txt_ville);
	}
	
	public void setPays(String str){
		comboBox.setSelectedItem(str);		
	}

	/**
	 * @return the txt_IP
	 */
	public String getTxt_IP() {
		return txt_IP.getText();
	}

	/**
	 * @return the txt_port
	 */
	public String getTxt_port() {
		return txt_port.getText();
	}

	/**
	 * @param txt_IP the txt_IP to set
	 */
	public void setTxt_IP(String txt_IP) {
		this.txt_IP.setText(txt_IP);
	}

	/**
	 * @param txt_port the txt_port to set
	 */
	public void setTxt_port(String txt_port) {
		this.txt_port.setText(txt_port);
	}

	public void setListener(EventListener al){
		btn_annuler_infos.addActionListener((ActionListener)al);
		btn_changer_nick.addActionListener((ActionListener)al);
		btn_maj_infos.addActionListener((ActionListener)al);
		btn_ok_serv.addActionListener((ActionListener)al);
	}
	
	public void focusPort(){
		txt_port.requestFocus();
	}
	
	public void focusIP(){
		txt_IP.requestFocus();
	}
	
	public void activerOnglet(int val){
		tabbedpane.setEnabledAt(val, true);
	}
	
	
}
